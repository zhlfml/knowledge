package me.thomas.knowledge.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;

import me.thomas.knowledge.utils.FileUtil;

public class ConvertDB2ToOracle_v2 {

	/**
	 * first use 'db2look -d DQA002DI -e -a -x -o ddlfile.sql' command load db2's ddl structure,
	 * then use this class to convert db2 to oracle.
	 * If you want only convert parts of tables, you can provide a file which contains tables.
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ConvertDB2ToOracle_v2 convert = getInstance();
		convert.provide("D:\\others\\temp\\db2-extra-tables.txt");
		convert.extractSQL("D:\\others\\temp\\ddlfile.sql");
	}
	
	public void provide(String tablePath) throws Exception {
		tables = FileUtil.read(tablePath).replace('\n', '|').replaceFirst("^", "|");
	}
	
	public void extractSQL(String path) throws Exception {
		String s;
		String line = "";
		StringBuffer sb = new StringBuffer();
		BufferedReader in = new BufferedReader(new FileReader(new File(path)));
		
		if (tables.trim().isEmpty()) {
			while ((s = in.readLine()) != null) {
				sb.append(convert(s, in));
				sb.append("\n");
			}
		} else {
			while ((s = in.readLine()) != null) {
				if (s.matches("-+")) {
					line = s;
					break;
				}
			}
			while (true) {
				if ((s = in.readLine()) != null) {
					if (s.contains("DDL Statements for table")
							&& tables.contains(s.substring(s.lastIndexOf(".") + 1).replace('"', '|'))) {
						sb.append(line);
						sb.append("\n");
						sb.append(convert(s, null));
						sb.append("\n");
						sb.append(in.readLine());
						sb.append("\n");

						while ((s = in.readLine()) != null) {
							if (!s.matches("-+")) {
								sb.append(convert(s, in));
								sb.append("\n");
							} else {
								break;
							}
						}
					}
				} else {
					break;
				}
			}
		}
		
		in.close();
		FileUtil.write(FileUtil.getParent(path) + "migration_oracle.sql", sb.toString());
	}
	
	public String convert(String sql, BufferedReader in) throws Exception {
		int pos = 0;
		// 1. trim
		sql = sql.trim();
		// 2. remove double quotes
		sql = sql.replace("\"", "");
		// 3. remove extra spaces
		sql = sql.replaceAll("\\s+", " ");
		// 4. remove space which before comma(,) semicolon(;) or point(.)
		sql = sql.replaceAll(" (?=[,;.])", "");
		// 5. add \t to the line which is not begin with command
		sql = sql.replaceFirst("^(?!(" + commands + "|--) )", "\t");
		// 6. add CHAR
		sql = sql.replaceAll("(?<=CHAR\\(\\d{1,4})(?=\\))", " CHAR");
		// 7. remove db2's schema name
		sql = sql.replaceAll("\\w+\\.(?!\\d)", "");
		// 8. remove db2's automatic increment option
		if ((pos = sql.indexOf("GENERATED ALWAYS")) > -1) {
			sql = sql.substring(0, pos -1).concat(",");
			while (true) {
				if (in.readLine().trim().endsWith(",")) {
					break;
				}
			}
		}
		
		if (sql.startsWith("CREATE TABLE")) { // get table name
			table = sql.substring(13, sql.length() - 2); // "CREATE TABLE ".length = 13
		} else if (sql.startsWith("\t") && sql.contains(" ")) { // get field name
			field = sql.substring(1, sql.indexOf(" "));
		}
		
		// 9. add constraint
		for (Map.Entry<String, String> entry : check.entrySet()) {
			if (sql.matches(".*\\b" + entry.getKey() + "\\b.*")) {
				sql = sql.replaceFirst("(?=,$)", entry.getValue().replace("field", field));
				break;
			}
		}
		
		// 10. mapping data type
		for (Map.Entry<String, String> entry : typeMap.entrySet()) {
			if (sql.matches(".*\\b" + entry.getKey() + "\\b.*")) {
				sql = sql.replaceFirst("\\b" + entry.getKey() + "\\b", entry.getValue());
				break;
			}
		}
		
		// 11. deal with defalut value
		String detail = "";
		if ((pos = sql.indexOf("WITH DEFAULT")) > -1) {
			detail = sql.substring(pos + 12, sql.length() - 1).trim(); // "WITH DEFAULT".length = 12
			if (detail.isEmpty()) {
				for (Map.Entry<String, String> entry : defaultValue.entrySet()) {
					if (sql.matches(".*\\b" + entry.getKey() + "\\b.*")) {
						detail = entry.getValue();
						break;
					}
				}
			} else if (detail.contains("CURRENT TIMESTAMP")) {
				detail = detail.replace("CURRENT TIMESTAMP", "CURRENT_TIMESTAMP");
			}
			sql = sql.replaceFirst("WITH DEFAULT.*(?=(,| \\))$)", "DEFAULT " + detail);
			
			if (sql.contains("NOT NULL")) { // move "NOT NULL" to end
				sql = sql.replace(" NOT NULL", "");
				sql = sql.replaceFirst("(?=(,| \\))$)", " NOT NULL");
			}
		}
		
		// 12. remove db2's private options
		if (sql.endsWith(")")) {
			sql = sql.concat(";");
			
			in.readLine(); // omit the next line
		} else if (sql.endsWith(";") && !sql.endsWith(");")) {
			sql = sql.replace(sql.substring(sql.lastIndexOf(")") + 1, sql.lastIndexOf(";")), "");
		}
		
		return sql;
	}
	
	public static ConvertDB2ToOracle_v2 getInstance() {
		return new ConvertDB2ToOracle_v2();
	}
	
	private ConvertDB2ToOracle_v2() {
		typeMap.put("VARCHAR", "VARCHAR2");
		typeMap.put("CHAR", "VARCHAR2");
		typeMap.put("DECIMAL", "NUMBER");
		typeMap.put("SMALLINT", "NUMBER(5,0)");
		typeMap.put("INTEGER", "NUMBER(10,0)");
		typeMap.put("TIME", "DATE");
		
		check.put("SMALLINT", " CHECK (field BETWEEN -32768 AND 32767)");
		check.put("INTEGER", " CHECK (field BETWEEN -2147483648 and 2147483647)");
		
		defaultValue.put("CHAR", "' '");
		defaultValue.put("VARCHAR2", "' '");
		defaultValue.put("NUMBER", "0");
		defaultValue.put("DATE", "CURRENT_DATE");
		defaultValue.put("TIMESTAMP", "CURRENT_TIMESTAMP");
	}
	
	String table = "";
	String field = "";
	String tables = "";
	String commands = "CREATE|ALTER";
	Map<String, String> typeMap = new LinkedHashMap<String, String>();
	Map<String, String> check = new LinkedHashMap<String, String>();
	Map<String, String> defaultValue = new LinkedHashMap<String, String>();

}
