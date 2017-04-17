package com.project.user.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.project.user.constants.UserDataConstants;
import com.project.user.db.utils.DBConnectionUtil;
import com.project.user.valueobjects.UserRole;

/**
 * @author Amit
 *
 */
@Stateless
public class UserRoleSessionBean implements UserRoleSessionBeanLocal {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.project.user.beans.UserRoleSessionBeanLocal#addUserRole(java.lang.Long, java.lang.String)
	 */
	@Override
	public void addUserRole(Long userId, String roleName) throws SQLException {

		Connection conn = null;
		PreparedStatement preparedStmt = null;
		try {
			String email = "";
			String userName = "";
			String password = "";

			if (userId == null || roleName == null) {
				System.out.println("Validation failed. Please provide proper inputs !!!");
			} else {

				conn = new DBConnectionUtil().getDBConnection();

				// the mysql insert statement
				String query = UserDataConstants.INSERT_QUERY_FOR_USER_ROLE_TABLE;
				

				// create the mysql insert preparedstatement
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1, userId.intValue());
				preparedStmt.setString(2, roleName);

				// execute the preparedstatement
				preparedStmt.execute();

				System.out.println("****************************************************");
				System.out.println("user role has been added successfully");
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
	 * com.project.user.beans.UserRoleSessionBeanLocal#deleteUserRole(java.lang.Long)
	 */
	@Override
	public void deleteUserRole(Long roleId) throws SQLException {
		Connection conn = null;

		PreparedStatement preparedStmtDeleteForUserRole = null;

		try {

			if (roleId == null) {
				System.out.println("Validation failed. Please provide proper inputs !!!");
			} else {

				conn = new DBConnectionUtil().getDBConnection();

				// deleting the userrole records for the role_id
				String query = UserDataConstants.DELETE_QUERY_FOR_USER_ROLE_TABLE;
				
				preparedStmtDeleteForUserRole = conn.prepareStatement(query);
				preparedStmtDeleteForUserRole.setInt(1, roleId.intValue());
				preparedStmtDeleteForUserRole.execute();

				conn.close();

				System.out.println("****************************************************");
				System.out.println("userrole " + roleId + " has been deleted successfully ");
				System.out.println("****************************************************");

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			if (preparedStmtDeleteForUserRole != null) {
				preparedStmtDeleteForUserRole.close();
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
	 * com.project.user.beans.UserRoleSessionBeanLocal#updateUserRole(java.lang.Long, java.lang.String)
	 */
	@Override
	public void updateUserRole(Long roleId, String roleName) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStmt = null;
		try {

			if (roleId == null || roleName == null) {
				System.out.println("Validation failed. Please provide proper inputs !!!");
			} else {

				conn = new DBConnectionUtil().getDBConnection();

				// create the java mysql update preparedstatement
				String query = UserDataConstants.UPDATE_QUERY_FOR_USER_ROLE_TABLE;
				

				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, roleName);
				preparedStmt.setInt(2, roleId.intValue());

				// execute the preparedstatement
				preparedStmt.execute();

				conn.close();

				System.out.println("****************************************************");
				System.out.println("userrole record for roleId :- " + roleId + " has been updated successfully ");
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
	 * com.project.user.beans.UserRoleSessionBeanLocal#getUserRoles(java.lang.Long)
	 */
	@Override
	public List<UserRole> getUserRoles(Long userId) throws SQLException {
		List<UserRole> userRoleList = new ArrayList<UserRole>();

		Connection conn = null;
		PreparedStatement preparedStmt = null;
		try {

			conn = new DBConnectionUtil().getDBConnection();

			String query = UserDataConstants.SELECT_QUERY_FOR_USER_ROLE_TABLE;

			// create the mysql insert preparedstatement
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, userId.intValue());

			// execute the query, and get a java result set
			ResultSet rs = preparedStmt.executeQuery();

			int id = 0;
			String roleName = "";
			String userName = "";
			String password = "";

			// iterate through the java result set
			while (rs.next()) {

				id = rs.getInt("role_id");
				roleName = rs.getString("rolename");

				UserRole userRole = new UserRole();
				userRole.setUserId(id);
				userRole.setRoleName(roleName);

				userRoleList.add(userRole);
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

		return userRoleList;
	}

}
