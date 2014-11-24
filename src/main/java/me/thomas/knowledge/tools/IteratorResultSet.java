package me.thomas.knowledge.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import me.thomas.knowledge.utils.DbHelper;
import me.thomas.knowledge.utils.FileUtil;

public class IteratorResultSet {
	
	public static void main(String[] avgs) throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jetspeed?useUnicode=true&characterEncoding=UTF-8";
		String username = "root";
		String password = "root";
		
		Connection conn = DbHelper.getConnection(driver, url, username, password);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("show tables");
			
		StringBuffer sb = new StringBuffer(1000);
		while (rs.next()) {
			String table = rs.getString(1);
			String alter = "alter table " + table + " rename to " + table.toUpperCase() + ";\n";
			sb.append(alter);
		}
		rs.close();
		stmt.close();
		conn.close();
		
		FileUtil.write("/home/thomas/Downloads/alter.sql", sb.toString());
	}
}
