package me.thomas.knowledge.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import me.thomas.knowledge.utils.FileUtil;

public class ConvertDB2ToOracle {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// step 1: extract sql ddl
		StringBuffer sb = new StringBuffer();
		String tables = FileUtil
				.read("C:\\Users\\thomas\\Desktop\\db2-extra-tables.txt")
				.replace('\n', '|').replaceFirst("^", "|");
		BufferedReader in = new BufferedReader(new FileReader(new File(
				"C:\\Users\\thomas\\Desktop\\ddlfile.sql")));

		String s;
		String line = "";
		while ((s = in.readLine()) != null) {
			if (s.matches("-+")) {
				line = s;
				break;
			}
		}
		while (true) {
			if ((s = in.readLine()) != null) {
				if (s.trim().length() > 0
						&& tables.indexOf(s.substring(s.lastIndexOf(".") + 1)
								.replace('"', '|')) > -1) {
					sb.append(line);
					sb.append("\n");
					sb.append(s);
					sb.append("\n");
					sb.append(in.readLine());
					sb.append("\n");

					while ((s = in.readLine()) != null) {
						if (!s.matches("-+")) {
							sb.append(s);
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
		in.close();

		FileUtil.write("C:\\Users\\thomas\\Desktop\\extra-tables-1.sql",
				sb.toString());

		// step 2: remove all prefix schema name
		int pos = 0;
		sb = new StringBuffer();
		in = new BufferedReader(new FileReader(new File(
				"C:\\Users\\thomas\\Desktop\\extra-tables-1.sql")));
		while ((s = in.readLine()) != null) {
			while ((pos = s.indexOf(".")) > -1) {
				if (s.charAt(pos - 1) == '"') {
					int beginPos = pos - 2; // 倒退找双引号
					while (s.charAt(beginPos) != '"') {
						beginPos--;
					}
					s = s.replace(s.substring(beginPos, pos + 1), "");
				} else {
					break;
				}
			}
			sb.append(s);
			sb.append("\n");
		}
		in.close();
		FileUtil.write("C:\\Users\\thomas\\Desktop\\extra-tables-2.sql",
				sb.toString());

		// step 3: remove DB2 private options
		sb = new StringBuffer();
		in = new BufferedReader(new FileReader(new File(
				"C:\\Users\\thomas\\Desktop\\extra-tables-2.sql")));
		while ((s = in.readLine()) != null) {
			if ((pos = s.indexOf(")")) > -1) {
				if (s.trim().endsWith(")")) {
					sb.append(s);
					sb.append(";");
					sb.append("\n");
					// omit the next line
					in.readLine();
					continue;
				} else if (s.trim().endsWith(";")) {
					sb.append(s.substring(0, pos + 1) + ";");
					continue;
				}
			}
			if ((pos = s.indexOf("GENERATED ALWAYS")) > -1) {
				s = s.substring(0, pos -1).concat(",");
				while (true) {
					if (in.readLine().trim().endsWith(",")) {
						break;
					}
				}
			}
			
			sb.append(s);
			sb.append("\n");
		}
		in.close();
		FileUtil.write("C:\\Users\\thomas\\Desktop\\extra-tables-3.sql",
				sb.toString());
		
		// step 4: add constraint to smallint and integer
		int seq = 1;
		String fieldName = "";
		String checkSmallint = "CONSTRAINT CHECK_field_name_seq CHECK (field_name BETWEEN -32768 AND 32767)";
		String checkInteger = "CONSTRAINT CHECK_field_name_seq CHECK (field_name BETWEEN -2147483648 and 2147483647)";
		sb = new StringBuffer();
		in = new BufferedReader(new FileReader(new File(
				"C:\\Users\\thomas\\Desktop\\extra-tables-3.sql")));
		while ((s = in.readLine()) != null) {
			if ((pos = s.indexOf(" SMALLINT ")) > -1) {
				fieldName = s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\""));
				s = s.replaceFirst("(?=(,|\\))\\s*$)", checkSmallint.replace("field_name", fieldName).replace("seq", String.valueOf(seq++)));
			} else if ((pos = s.indexOf(" INTEGER ")) > -1) {
				fieldName = s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\""));
				s = s.replaceFirst("(?=(,|\\))\\s*$)", checkInteger.replace("field_name", fieldName).replace("seq", String.valueOf(seq++)));
			}
			sb.append(s);
			sb.append("\n");
		}
		in.close();
		FileUtil.write("C:\\Users\\thomas\\Desktop\\extra-tables-4.sql",
				sb.toString());

		// step 5: convert data types
		sb = new StringBuffer();
		in = new BufferedReader(new FileReader(new File(
				"C:\\Users\\thomas\\Desktop\\extra-tables-4.sql")));
		while ((s = in.readLine()) != null) {
			if (s.indexOf(" VARCHAR") > -1) {
				s = s.replace(" VARCHAR", " CHAR");
			} else if (s.indexOf(" CHAR") > -1) {
				s = s.replace(" CHAR", " VARCHAR2");
			} else if (s.indexOf(" DECIMAL") > -1) {
				s = s.replace(" DECIMAL", " NUMBER");
			} else if (s.indexOf(" SMALLINT") > -1) {
				s = s.replace(" SMALLINT", " NUMBER(5,0)");
			} else if (s.indexOf(" INTEGER") > -1) {
				s = s.replace(" INTEGER", " NUMBER(10,0)");
			} else if (s.indexOf(" TIME ") > -1) {
				s = s.replace(" TIME ", " DATE ");
			} else {
				// log no modify statements
				if (s.trim().startsWith("\"")) {
					System.out.println(s);
				}
			}
			sb.append(s);
			sb.append("\n");
		}
		in.close();
		FileUtil.write("C:\\Users\\thomas\\Desktop\\extra-tables-5.sql",
				sb.toString());
		
		// step 6: add CHAR
		sb = new StringBuffer();
		in = new BufferedReader(new FileReader(new File(
				"C:\\Users\\thomas\\Desktop\\extra-tables-5.sql")));
		while ((s = in.readLine()) != null) {
			if ((pos = s.indexOf("VARCHAR2(")) > -1
					|| (pos = s.indexOf("CHAR(")) > -1) {
				s = s.replaceFirst("\\)", " CHAR)");
			}
			sb.append(s);
			sb.append("\n");
		}
		in.close();
		FileUtil.write("C:\\Users\\thomas\\Desktop\\extra-tables-6.sql",
				sb.toString());

		// step 7: deal with default value
		String detail = "";
		int endPos = 0;
		String WITH_DEFAULT = "WITH DEFAULT";
		int keyWordLength = WITH_DEFAULT.length();
		sb = new StringBuffer();
		in = new BufferedReader(new FileReader(new File(
				"C:\\Users\\thomas\\Desktop\\extra-tables-6.sql")));
		while ((s = in.readLine()) != null) {
			String symbol = ",";
			if ((pos = s.indexOf(WITH_DEFAULT)) > -1) {
				endPos = s.indexOf(",", pos);
				if (endPos < 0) {
					endPos = s.indexOf(")", pos);
					if (endPos < 0) {
						in.close();
						throw new RuntimeException("No ',' No ')'");
					} else {
						symbol = ");";
					}
				} 
				detail = s.substring(pos + keyWordLength, endPos);
				if (detail.trim().isEmpty()) {
					if ((s.indexOf(" CHAR(") > -1) 
							|| (s.indexOf(" VARCHAR2(") > -1)) {
						detail = "' '";
					} else if (s.indexOf(" DATE ") > -1) {
						detail = "CURRENT_DATE";
					} else if (s.indexOf(" TIMESTAMP ") > -1) {
						detail = "CURRENT_TIMESTAMP";
					} else if (s.indexOf(" NUMBER(") > -1) {
						char number = s.charAt(s.indexOf(",", s.indexOf(" NUMBER(")) + 1);
						if (number == '0') {
							detail = "0";
						} else {
							detail = "0.0";
						}
					}
				} else if (detail.indexOf("CURRENT TIMESTAMP") > -1) {
					detail = detail.replace("CURRENT TIMESTAMP", "CURRENT_TIMESTAMP");
				} else {
					detail = detail.trim();
				}
				if ((pos = s.indexOf("NOT NULL WITH DEFAULT")) > -1) {
					s = s.substring(0, pos) + "DEFAULT " + detail
							+ " NOT NULL " + symbol;
				} else {
					s = s.substring(0, s.indexOf(WITH_DEFAULT)) + "DEFAULT " + detail + symbol;
				}
			}
			
			sb.append(s);
			sb.append("\n");
		}
		in.close();
		FileUtil.write("C:\\Users\\thomas\\Desktop\\extra-tables-7.sql",
				sb.toString());
		
		// step 8: beautiful
		sb = new StringBuffer();
		in = new BufferedReader(new FileReader(new File(
				"C:\\Users\\thomas\\Desktop\\extra-tables-7.sql")));
		while ((s = in.readLine()) != null) {
			// 8.1. trim
			s = s.trim();
			// 8.2. remove double quotes
			s = s.replace("\"", "");
			// 8.3. remove extra spaces
			s = s.replaceAll("\\s+", " ");
			// 8.4. remove space which before comma(,) semicolon(;) or point(.)
			s = s.replaceAll(" (?=[,;.])", "");
			// 8.5. add \t to the line which is not begin with command
			if (!s.startsWith("--")) {
				s = s.replaceFirst("^(?!(CREATE|ALTER) )", "\t");
			}
						
			sb.append(s);
			sb.append("\n");
		}
		in.close();
		FileUtil.write("C:\\Users\\thomas\\Desktop\\extra-tables-8.sql",
				sb.toString());
		
	}

}
