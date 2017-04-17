package com.project.user.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.project.user.constants.UserDataConstants;

/**
 * @author Amit
 * 
 * This class contains various utilities that are used for establishing database connections
 *
 */
public class DBConnectionUtil {

	
	/**
	 * @return
	 * 		returns valid database connection to the calling method
	 * 
	 */
	public Connection getDBConnection() {

		System.out.println("-------- creating MySQL JDBC Connection ------------");
		try {
			Class.forName(UserDataConstants.DATABASE_DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		Connection connection = null;

		try {
			String connectionUrl = UserDataConstants.DATABASE_CONNECTION_URL_STARTING_PART
					+ UserDataConstants.MYSQL_DATABASE_HOST_ADDRESS + ":"
					+ UserDataConstants.MYSQL_DATABASE_PORT_NUMBER + "/" + UserDataConstants.MYSQL_DATABASE_NAME;

			connection = DriverManager.getConnection(connectionUrl, UserDataConstants.MYSQL_DATABASE_USER_NAME,
					UserDataConstants.MYSQL_DATABASE_PASSWORD);

		} catch (SQLException e) {
			System.out.println("connection to the MySQL database could not be established!");
			e.printStackTrace();
			return null;
		}

		System.out.println("connection to the MySQL database established!");

		return connection;
	}

}
