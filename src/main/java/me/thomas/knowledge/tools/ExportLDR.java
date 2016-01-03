package me.thomas.knowledge.tools;

import me.thomas.knowledge.utils.DbHelper;
import me.thomas.knowledge.utils.FileUtil;
import me.thomas.knowledge.utils.UUIDUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * export the sql result data as loader format.
 *
 * Created by thomas on 11/20/14.
 */
public class ExportLDR {

    public static void main(String[] args) throws Exception {
        ExportLDR export = new ExportLDR();
        Connection conn = null;
        Statement stmt = null;
        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            String url = "jdbc:oracle:thin:@192.168.111.82:1521:orcl";
            String username = "ctpat_drop3_prd";
            String password = "g360";

            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
            DateFormat timestampFormat = new SimpleDateFormat("dd-MMM-yy hh.mm.ss.SSS000000 a");

            String exportPath = "/home/thomas/DATA/";
            String dataFolder = "data/";
            String dataPath = exportPath + dataFolder;
            FileUtil.rmdir(dataPath);
            FileUtil.mkdir(dataPath);

            // the table which contain '\n' in its field.
            List<String> hasEnterKeys = Arrays.asList("GW_EMAIL_TEMPLATE");

            conn = DbHelper.getConnection(driver, url, username, password);
            stmt = conn.createStatement();

            String sql;
            BufferedReader in = new BufferedReader(new FileReader(new File("/home/thomas/Documents/minus.sql")));
            while (!"".equals(sql = export.grepSQL(in))) {
                boolean hasData = false;
                String tableName = export.grepTableName(sql).toUpperCase();
                StringBuilder ldr = new StringBuilder();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    hasData = true;
                    ldr.append(" ");
                    String lobFile = tableName + UUIDUtil.genUUID();
                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnLabel = metaData.getColumnLabel(i);
                        String columnTypeName = metaData.getColumnTypeName(i);
                        Object value = null;
                        switch (columnTypeName) {
                            case "NVARCHAR2":
                                value = rs.getNString(i);
                                if (value != null) {
                                    ldr.append("\"").append(((String) value).replace("\"", "\"\"")).append("\"");
                                }
                                break;
                            case "NUMBER":
                                value = rs.getLong(i);
                                ldr.append(value);
                                break;
                            case "VARCHAR2":
                                value = rs.getString(i);
                                if (value != null) {
                                    ldr.append("\"").append(((String) value).replace("\"", "\"\"")).append("\"");
                                }
                                break;
                            case "DATE":
                                value = rs.getDate(i);
                                if (value != null) {
                                    ldr.append(dateFormat.format(value).toUpperCase());
                                }
                                break;
                            case "TIMESTAMP":
                                value = rs.getTimestamp(i);
                                if (value != null) {
                                    ldr.append("\"").append(timestampFormat.format(value).toUpperCase()).append("\"");
                                }
                                break;
                            case "BLOB":
//                                value = rs.getBlob(i);
//                                if (value != null) {
//                                    InputStream binaryStream = ((BLOB) value).getBinaryStream();
//                                    String blobFile = lobFile + "_" + i + ".ldr";
//                                    FileUtil.write(dataPath + blobFile, binaryStream);
//                                    ldr.append(dataFolder + blobFile);
//                                }
                                break;
                            case "CLOB":
                                value = rs.getClob(i);
//                                if (value != null) {
//                                    String content = ((CLOB) value).getSubString(1, (int) ((CLOB) value).length());
//                                    String clobFile = lobFile + "_" + i + ".ldr";
//                                    FileUtil.write(dataPath + clobFile, content);
//                                    ldr.append(dataFolder + clobFile);
//                                }
                                break;
                            default:
                                System.err.println(tableName + "." + columnLabel + " -> " + columnTypeName);
                        }
                        ldr.append("~");
                    }
                    if (hasEnterKeys.contains(tableName)) {
                        ldr.append("|");
                    }
                    ldr.append("\n");
                }
                if (hasData) {
                    FileUtil.write(dataPath + tableName + ".ldr", ldr.toString());
                } else {
                    System.out.println(tableName + " has no data exported!");
                }
            }
        } catch(SQLException e) {
            System.out.println("errorCode => " + e.getErrorCode());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String grepSQL(BufferedReader in) throws IOException {
        String s;
        StringBuilder sql = new StringBuilder();
        while ((s = in.readLine()) != null) {
            s = s.trim(); // trim sql here
            if (!"".equals(s) && !s.startsWith("--")) {
                int end = s.indexOf(";");
                if (end == -1) {
                    sql.append(s).append("\n");
                } else {
                    sql.append(s.substring(0, end));
                    break;
                }
            }
        }
        return sql.toString();
    }

    public String grepTableName(String sql) {
        String tableName = "";
        sql = sql.replace("\n", " "); // sql has been trimmed in grepSQL()
        Pattern pattern = Pattern.compile("(?i)(?<= from )\\w+");
        Matcher m = pattern.matcher(sql);
        if (m.find()) {
            tableName = m.group(0);
        }
        return tableName;
    }
}
