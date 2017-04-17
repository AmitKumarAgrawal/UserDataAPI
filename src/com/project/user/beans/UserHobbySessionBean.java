package com.project.user.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import com.project.user.constants.UserDataConstants;
import com.project.user.db.utils.DBConnectionUtil;
import com.project.user.valueobjects.UserHobby;

/**
 * @author Amit
 *
 */
@Stateless
public class UserHobbySessionBean implements UserHobbySessionBeanLocal {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.project.user.beans.UserHobbySessionBeanLocal#addUserHobby(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public void addUserHobby(Long userId, String hobby, String createdBy) throws SQLException {

		Connection conn = null;
		PreparedStatement preparedStmt = null;
		try {
			String email = "";
			String userName = "";
			String password = "";

			if (userId == null || hobby == null || createdBy == null) {
				System.out.println("Validation failed. Please provide proper inputs !!!");
			} else {

				conn = new DBConnectionUtil().getDBConnection();

				// the mysql insert statement
				String query = UserDataConstants.INSERT_QUERY_FOR_USER_HOBBY_TABLE;

				// create the mysql insert preparedstatement
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1, userId.intValue());
				preparedStmt.setString(2, hobby);
				preparedStmt.setString(3, createdBy);
				Date dt = new Date();
				preparedStmt.setDate(4, (java.sql.Date) dt);

				// execute the preparedstatement
				preparedStmt.execute();
				
				System.out.println("****************************************************");
				System.out.println("user hobby has been added successfully");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.project.user.beans.UserHobbySessionBeanLocal#deleteUserHobby(java.lang.Long)
	 */
	@Override
	public void deleteUserHobby(Long id) throws SQLException {

		Connection conn = null;

		PreparedStatement preparedStmtDeleteForUserHobby = null;

		try {

			if (id == null) {
				System.out.println("Validation failed. Please provide proper inputs !!!");
			} else {

				conn = new DBConnectionUtil().getDBConnection();

				// deleting the userrole records for the role_id
				String query = UserDataConstants.DELETE_QUERY_FOR_USER_HOBBY_TABLE;
				
				preparedStmtDeleteForUserHobby = conn.prepareStatement(query);
				preparedStmtDeleteForUserHobby.setInt(1, id.intValue());
				preparedStmtDeleteForUserHobby.execute();

				conn.close();

				System.out.println("****************************************************");
				System.out.println("userhobby " + id + " has been deleted successfully ");
				System.out.println("****************************************************");

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			if (preparedStmtDeleteForUserHobby != null) {
				preparedStmtDeleteForUserHobby.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.project.user.beans.UserHobbySessionBeanLocal#updateUserHobby(java.lang.Long, java.lang.String)
	 */
	@Override
	public void updateUserHobby(Long id, String hobby) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStmt = null;
		try {

			if (id == null || hobby == null) {
				System.out.println("Validation failed. Please provide proper inputs !!!");
			} else {

				conn = new DBConnectionUtil().getDBConnection();

				// create the java mysql update preparedstatement
				String query = UserDataConstants.UPDATE_QUERY_FOR_USER_HOBBY_TABLE;

				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, hobby);
				preparedStmt.setInt(2, id.intValue());

				// execute the preparedstatement
				preparedStmt.execute();

				conn.close();

				System.out.println("****************************************************");
				System.out.println("userhobby record for id :- " + id + " has been updated successfully ");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.project.user.beans.UserHobbySessionBeanLocal#getUserHobbies(java.lang.Long)
	 * 	  
	 */
	@Override
	public List<UserHobby> getUserHobbies(Long userId) throws SQLException {
		List<UserHobby> userHobbyList = new ArrayList<UserHobby>();

		Connection conn = null;
		PreparedStatement preparedStmt = null;
		try {

			System.out.println("inside getUserHobbies() method and userId is :: " + userId);

			conn = new DBConnectionUtil().getDBConnection();

			String query = UserDataConstants.SELECT_QUERY_FOR_USER_HOBBY_TABLE;

			// create the mysql select preparedstatement
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, userId.intValue());

			// execute the query, and get a java result set
			ResultSet rs = preparedStmt.executeQuery();

			int id = 0;
			String hobby = "";
			String userName = "";
			String password = "";

			// iterate through the java result set
			while (rs.next()) {

				id = rs.getInt("id");
				hobby = rs.getString("hobby");
				String createdBy = rs.getString("createdby");
				Date createdOn = rs.getDate("createdon");

				UserHobby userHbby = new UserHobby();
				userHbby.setId(id);
				userHbby.setHobby(hobby);
				userHbby.setUserId(userId.intValue());
				userHbby.setCreatedBy(createdBy);
				userHbby.setCreatedOn(createdOn);

				userHobbyList.add(userHbby);
			}

			conn.close();

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

		return userHobbyList;
	}

}
