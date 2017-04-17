package com.project.user.constants;

/**
 * @author Amit
 * 
 * This class contains all the database related constants, which are used across all the classes in this application.
 *
 */
public class UserDataConstants {

	/**
	 * database connection url part
	 */
	public static final String DATABASE_CONNECTION_URL_STARTING_PART = "jdbc:mysql://";

	/**
	 * database driver class name
	 */
	public static final String DATABASE_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

	/**
	 * database host address
	 */
	public static final String MYSQL_DATABASE_HOST_ADDRESS = "localhost";

	/**
	 * database port number
	 */
	public static final String MYSQL_DATABASE_PORT_NUMBER = "3306";

	/**
	 * database name
	 */
	public static final String MYSQL_DATABASE_NAME = "project_db";

	/**
	 * database user name
	 */
	public static final String MYSQL_DATABASE_USER_NAME = "amit";

	/**
	 * database password
	 */
	public static final String MYSQL_DATABASE_PASSWORD = "password1";
	
	/**
	 * insert query for USER table
	 */
	public static final String INSERT_QUERY_FOR_USER_TABLE = " insert into user (email, username, password)" + " values (?, ?, ?)";
	
	
	/**
	 * SELECT query for USER table
	 */
	public static final String SELECT_QUERY_FOR_USER_TABLE = "select * from user";
	
	
	/**
	 * insert query for userHobby table
	 */
	public static final String INSERT_QUERY_FOR_USER_HOBBY_TABLE = " insert into userhobby(user_id, hobby, createdBy, createdOn) values (?, ?, ?, ?)";
	
	
	/**
	 * delete query for userHobby table
	 */
	public static final String DELETE_QUERY_FOR_USER_HOBBY_TABLE = "delete from userhobby where id = ?";
	
	
	/**
	 * UPDATE query for userHobby table
	 */
	public static final String UPDATE_QUERY_FOR_USER_HOBBY_TABLE = "update userhobby set hobby = ? where id = ?";
	
	
	/**
	 * SELECT query for userHobby table
	 */
	public static final String SELECT_QUERY_FOR_USER_HOBBY_TABLE = "select * from userhobby where user_id = ?";
	
	

	/**
	 * insert query for userrole table
	 */
	public static final String INSERT_QUERY_FOR_USER_ROLE_TABLE = " insert into userrole (user_id, rolename)" + " values (?, ?)";
	
	
	/**
	 * delete query for userrole table
	 */
	public static final String DELETE_QUERY_FOR_USER_ROLE_TABLE = "delete from userrole where role_id = ?";
	
	
	/**
	 * UPDATE query for userrole table
	 */
	public static final String UPDATE_QUERY_FOR_USER_ROLE_TABLE = "update userrole set rolename = ? where role_id = ?";
	
	
	/**
	 * SELECT query for userrole table
	 */
	public static final String SELECT_QUERY_FOR_USER_ROLE_TABLE = "select * from userrole where user_id = ?";
	
	
	/**
	 * insert query for userPhone table
	 */
	public static final String INSERT_QUERY_FOR_USER_PHONE_TABLE = " insert into userphone (user_id, type, phoneNumber, createdBy, createdOn)"
	+ " values (?, ?, ?, ?, ?)";
	
	/**
	 * delete query for userPhone table
	 */
	public static final String DELETE_QUERY_FOR_USER_PHONE_TABLE = "delete from userphone where user_id = ? and type = ?";
	
	
	/**
	 * UPDATE query for userPhone table
	 */
	public static final String UPDATE_QUERY_FOR_USER_PHONE_TABLE = "update userphone set phoneNumber = ? where user_id = ? and type = ?";
	
	/**
	 * SELECT query for userPhone table
	 */
	public static final String SELECT_QUERY_FOR_USER_PHONE_TABLE = "select * from userphone where user_id = ?";
	
	

}
