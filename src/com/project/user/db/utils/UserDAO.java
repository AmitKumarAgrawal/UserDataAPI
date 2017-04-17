package com.project.user.db.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.project.user.beans.UserSessionBeanLocal;
import com.project.user.valueobjects.User;
import com.project.user.valueobjects.UserHobby;
import com.project.user.valueobjects.UserPhone;
import com.project.user.valueobjects.UserRole;

/**
 * @author Amit
 *
 *This class contains the methods to perform CRUD operation on User table
 */
public class UserDAO {

	@EJB
	private UserSessionBeanLocal usrSessionBeanLocal;

	/**
	 * @return
	 * @throws SQLException
	 */
	public List<User> getAllUsersFromDB() throws SQLException {

		// Retrieve the Home Interface using a JNDI Lookup
		// Retrieve the initial context for JNDI. // No properties needed when local
		Context context;
		List<User> userList = new ArrayList<User>();

		try {
			context = new InitialContext();

			// Retrieve the home interface using a JNDI lookup using
			usrSessionBeanLocal = (UserSessionBeanLocal) context.lookup("java:module/UserSessionBean");

			if(usrSessionBeanLocal != null){
				System.out.println("usrSessionBeanLocal is NOT NULL ");

				userList = usrSessionBeanLocal.getUsers();
				return userList;
			} else{
				System.out.println("usrSessionBeanLocal is NULL ");
				return userList;
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	
	/**
	 * @param emailId
	 * @param userName
	 * @param password
	 * @throws SQLException
	 * 
	 * 
	 */
	public void addUser(String emailId, String userName, String password) throws SQLException {

		// Retrieve the Home Interface using a JNDI Lookup
		// Retrieve the initial context for JNDI. // No properties needed when local
		Context context;

		try {
			context = new InitialContext();

			User user = new User();
			user.setEmailId(emailId);
			user.setUserName(userName);
			user.setPassword(password);
			
			// Retrieve the home interface using a JNDI lookup using
			usrSessionBeanLocal = (UserSessionBeanLocal) context.lookup("java:module/UserSessionBean");

			if(usrSessionBeanLocal != null){
				System.out.println("usrSessionBeanLocal is NOT NULL ");

				usrSessionBeanLocal.addUser(user);
			} else{
				System.out.println("usrSessionBeanLocal is NULL ");
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 * @throws SQLException
	 * 
	 *             todo need to finish this method
	 */
	public List<User> getAllUsersWithDependmetDeatilsFromDB() throws SQLException {

		List<User> userList = new ArrayList<User>();

		Connection conn = null;
		try {

			conn = new DBConnectionUtil().getDBConnection();

			// String query = "select * from user";

			String query = "select usr.user_id, usr.email, usr.username, usr.password, usrrl.role_id, "
					+ "rolename, usrhby.id as hobbyId, hobby, usrhby.createdBy as hobbyCreatedBy,"
					+ "usrhby.createdOn as hobbyCreatedOn, usrphn.id as phoneId, type, phonenumber, usrphn.createdBy as phoneCreatedBy,"
					+ " usrphn.createdOn as phoneCreatedOn  " + "from user usr " + "	join userrole usrrl "
					+ "		on usr.user_id = usrrl.user_id " + "	join userhobby usrhby  "
					+ "		on usr.user_id = usrhby.user_id " + " join userphone usrphn  "
					+ "		on usr.user_id = usrphn.user_id";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java result set
			ResultSet rs = st.executeQuery(query);

			int userId = 0;
			String emailId = "";
			String userName = "";
			String password = "";

			int roleId = 0;
			String roleName = "";

			int phoneId = 0;
			String phoneNumber = "";
			String phoneNumberType = "";

			int hobbyId = 0;
			String hobbyName = "";
			String hobbyCreatedBy = "";
			Date hobbyCreatedOn = null;
			String phoneCreatedBy = "";
			Date phoneCreatedOn = null;

			List<UserPhone> userPhoneList = new ArrayList<UserPhone>();
			List<UserHobby> userHobbyList = new ArrayList<UserHobby>();

			User user = new User();
			// iterate through the java result set
			while (rs.next()) {

				userId = rs.getInt("user_id");
				emailId = rs.getString("email");
				userName = rs.getString("username");
				password = rs.getString("password");
				hobbyName = rs.getString("hobby");

				roleId = rs.getInt("role_id");
				roleName = rs.getString("rolename");
				hobbyId = rs.getInt("hobbyId");
				phoneId = rs.getInt("phoneId");

				phoneNumber = rs.getString("phonenumber");
				phoneNumberType = rs.getString("type");

				hobbyName = rs.getString("hobby");
				hobbyCreatedBy = rs.getString("hobbyCreatedBy");
				hobbyCreatedOn = rs.getDate("hobbyCreatedOn");
				phoneCreatedBy = rs.getString("phoneCreatedBy");
				phoneCreatedOn = rs.getDate("phoneCreatedOn");

				if (user.getEmailId() == null) {
					user.setEmailId(emailId);
				}

				if (user.getUserName() == null) {
					user.setUserName(userName);
				}

				if (user.getPassword() == null) {
					user.setPassword(password);
				}

				if (user.getUserId() == 0) {
					user.setUserId(userId);
				}

				UserRole userRole = new UserRole();
				userRole.setRoleId(roleId);
				userRole.setRoleName(roleName);
				user.setUserRole(userRole);

				UserPhone userPhone = new UserPhone();
				userPhone.setUserId(userId);
				userPhone.setId(1);
				userPhone.setPhoneNumber("11111111");
				userPhone.setPhoneNumberType("office");
				userPhoneList.add(userPhone);
				user.setUserPhone(userPhoneList);

				UserHobby userHobby = new UserHobby();
				userHobby.setHobby("golfing");
				userHobby.setId(1);
				userHobby.setUserId(userId);
				userHobby.setCreatedBy("user1");

				userHobbyList.add(userHobby);
				user.setUserHobby(userHobbyList);
				userList.add(user);

			}

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		} finally {

			if (conn != null) {
				conn.close();
			}
		}

		return userList;

	}

}