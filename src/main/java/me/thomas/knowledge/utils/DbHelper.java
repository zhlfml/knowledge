package me.thomas.knowledge.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {

	public static Connection getConnection(String driver, String url,
			String userName, String password) throws SQLException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		return DriverManager.getConnection(url, userName, password);
	}

}
