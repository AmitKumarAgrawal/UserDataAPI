package com.project.user.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.project.user.constants.UserDataConstants;
import com.project.user.db.utils.DBConnectionUtil;
import com.project.user.valueobjects.User;

/**
 * @author Amit
 *
 */
@Stateless
public class UserSessionBean implements UserSessionBeanLocal{
	
	/* (non-Javadoc)
	 * @see com.project.user.beans.UserSessionBeanLocal#addUser(com.project.user.valueobjects.User)
	 */
	@Override
	public void addUser(User user) throws SQLException {

		Connection conn = null;
		PreparedStatement preparedStmt = null;
		try {
			String email = "";
			String userName = "";
			String password = "";

			if (user == null || user.getEmailId() == null || user.getUserName() == null || user.getPassword() == null) {
				System.out.println("Validation failed. Please provide proper inputs !!!");
			} else {
				email = user.getEmailId();
				userName = user.getUserName();
				password = user.getPassword();

				conn = new DBConnectionUtil().getDBConnection();

				// the mysql insert statement
				String query = UserDataConstants.INSERT_QUERY_FOR_USER_TABLE;

				// create the mysql insert preparedstatement
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, email);
				preparedStmt.setString(2, userName);
				preparedStmt.setString(3, password);

				// execute the preparedstatement
				preparedStmt.execute();

				System.out.println("****************************************************");
				System.out.println("user has been added successfully");
				System.out.println("****************************************************");

			}

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		} finally {

			if (preparedStmt != null) {
				preparedStmt.close();
			}

			if (conn != null) {
				conn.close();
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.project.user.beans.UserSessionBeanLocal#getUsers()
	 */
	@Override
	public List<User> getUsers() throws SQLException {
		
		System.out.println("inside the getUsers() method of session bean.");

		List<User> userList = new ArrayList<User>();

		Connection conn = null;
		try {

			conn = new DBConnectionUtil().getDBConnection();

			String query = UserDataConstants.SELECT_QUERY_FOR_USER_TABLE;
			
			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java result set
			ResultSet rs = st.executeQuery(query);

			int id = 0;
			String emailId = "";
			String userName = "";
			String password = "";

			// iterate through the java result set
			while (rs.next()) {
				id = rs.getInt("user_id");
				emailId = rs.getString("email");
				userName = rs.getString("username");
				password = rs.getString("password");

				User user = new User();
				user.setUserId(id);
				user.setEmailId(emailId);
				user.setUserName(userName);
				user.setPassword(password);

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

	/* (non-Javadoc)
	 * @see com.project.user.beans.UserSessionBeanLocal#deleteUser(java.lang.Long)
	 */
	@Override
	public void deleteUser(Long userId) throws SQLException {
		Connection conn = null;

		PreparedStatement preparedStmtDeleteForUserPhone = null;
		PreparedStatement preparedStmtDeleteForUserHobby = null;
		PreparedStatement preparedStmtDeleteForUserRole = null;
		PreparedStatement preparedStmtDeleteForUser = null;

		try {

			if (userId == null) {
				System.out.println("Validation failed. Please provide proper inputs !!!");
			} else {

				conn = new DBConnectionUtil().getDBConnection();

				// we are doing this so that we will commit the transaction only
				// when every dependent records are deleted successfully
				// to satisfy the foreign key constraints
				conn.setAutoCommit(false);

				// create the java mysql delete preparedstatement
				String query = "delete from userphone where user_id = ?";
				preparedStmtDeleteForUserPhone = conn.prepareStatement(query);
				preparedStmtDeleteForUserPhone.setInt(1, userId.intValue());
				preparedStmtDeleteForUserPhone.execute();

				// deleting the hobby records for the user now
				query = "delete from userhobby where user_id = ?";
				preparedStmtDeleteForUserHobby = conn.prepareStatement(query);
				preparedStmtDeleteForUserHobby.setInt(1, userId.intValue());
				preparedStmtDeleteForUserHobby.execute();

				// deleting the userrole records for the user now
				query = "delete from userrole where user_id = ?";
				preparedStmtDeleteForUserRole = conn.prepareStatement(query);
				preparedStmtDeleteForUserRole.setInt(1, userId.intValue());
				preparedStmtDeleteForUserRole.execute();

				// finally deleting the user records for the user
				query = "delete from user where user_id = ?";
				preparedStmtDeleteForUser = conn.prepareStatement(query);
				preparedStmtDeleteForUser.setInt(1, userId.intValue());
				preparedStmtDeleteForUser.execute();

				conn.commit();

				conn.close();

				System.out.println("****************************************************");
				System.out.println("user " + userId + " has been deleted successfully ");
				System.out.println("****************************************************");

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			conn.rollback();

		} finally {

			if (preparedStmtDeleteForUserPhone != null) {
				preparedStmtDeleteForUserPhone.close();
			}

			if (preparedStmtDeleteForUserHobby != null) {
				preparedStmtDeleteForUserHobby.close();
			}

			if (preparedStmtDeleteForUserRole != null) {
				preparedStmtDeleteForUserRole.close();
			}

			if (preparedStmtDeleteForUser != null) {
				preparedStmtDeleteForUser.close();
			}

			if (conn != null) {
				conn.close();
			}

		}

	}

	/* (non-Javadoc)
	 * @see com.project.user.beans.UserSessionBeanLocal#updateUser(com.project.user.valueobjects.User)
	 */
	@Override
	public void updateUser(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStmt = null;
		try {

			if (user == null || user.getUserId() <= 0 || user.getEmailId() == null || user.getUserName() == null
					|| user.getPassword() == null) {
				System.out.println("Validation failed. Please provide proper inputs !!!");
			} else {

				conn = new DBConnectionUtil().getDBConnection();

				// create the java mysql update preparedstatement
				String query = "update user set email = ? , username = ?, password = ? where user_id = ?";

				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, user.getEmailId());
				preparedStmt.setString(2, user.getUserName());
				preparedStmt.setString(3, user.getPassword());
				preparedStmt.setInt(4, user.getUserId());

				// execute the preparedstatement
				preparedStmt.execute();

				conn.close();
				System.out.println("****************************************************");
				System.out.println("user record for user_id :- " + user.getUserId() + " has been updated successfully ");
				System.out.println("****************************************************");

			}

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		} finally {

			if (preparedStmt != null) {
				preparedStmt.close();
			}

			if (conn != null) {
				conn.close();
			}

		}

	}
}
