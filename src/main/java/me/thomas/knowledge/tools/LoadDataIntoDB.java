package me.thomas.knowledge.tools;

import me.thomas.knowledge.utils.DbHelper;
import me.thomas.knowledge.utils.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoadDataIntoDB {

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        Statement stmt = null;

        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            String url = "jdbc:oracle:thin:@192.168.111.82:1521:orcl";
            String username = "ctpat_drop3";
            String password = "g360";

            conn = DbHelper.getConnection(driver, url, username, password);
            stmt = conn.createStatement();

            // read file
            BufferedReader in = new BufferedReader(new FileReader(new File("/home/thomas/Documents/BILL_OF_LADING_NO.txt")));
            String s;
            int line = 0;
            StringBuffer hasRecord = new StringBuffer();
            StringBuffer hasNoRecord = new StringBuffer();
            while ((s = in.readLine()) != null && !"".equals(s)) {
                line++;
                System.out.println(line);
                String sql = "SELECT * FROM SHIPPING_DTL WHERE BILL_OF_LADING_NO = '" + s.trim() + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    hasRecord.append("Line " + line + ": " + s + "\n");
                } else {
                    hasNoRecord.append("Line " + line + ": " + s + "\n");
                }
            }
            FileUtil.write("/home/thomas/Documents/hasRecord.txt", hasRecord.toString());
            FileUtil.write("/home/thomas/Documents/hasNoRecord.txt", hasNoRecord.toString());
            in.close();
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

}