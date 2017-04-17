package com.project.user.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.project.user.constants.UserDataConstants;

/**
 * @author Amit
 * 
 *         This class contains various utilities that are used for establishing
 *         database connections
 *
 */
public class DBConnectionUtil {

	/**
	 * @return returns valid database connection to the calling method by consuming JBoss datasource 
	 * 
	 */
	public Connection getDBConnection() {

		System.out.println("-------- creating datasource connection ------------");

		Connection connection = null;
		try {

			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:jboss/datasources/UserProjectDS");
			connection = ds.getConnection();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		System.out.println("--------  connection to the datasource established! -------- ");

		return connection;
	}

}
