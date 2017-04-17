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
import com.project.user.valueobjects.UserPhone;

/**
 * @author Amit
 *
 */
@Stateless
public class UserPhoneSessionBean implements UserPhoneSessionBeanLocal {

	/* (non-Javadoc)
	 * @see com.project.user.beans.UserPhoneSessionBeanLocal#deleteUserPhone(java.lang.Long, java.lang.String)
	 */
	@Override
	public void deleteUserPhone(Long userId, String phoneType) throws SQLException {
		Connection conn = null;

		PreparedStatement preparedStmt = null;

		try {

			if (userId == null || phoneType == null) {
				System.out.println("Validation failed. Please provide proper inputs !!!");
			} else {

				conn = new DBConnectionUtil().getDBConnection();

				// deleting the userrole records for the role_id
				String query = UserDataConstants.DELETE_QUERY_FOR_USER_PHONE_TABLE;
				
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1, userId.intValue());
				preparedStmt.setString(2, phoneType);
				preparedStmt.execute();

				conn.close();

				System.out.println("****************************************************");
				System.out.println(phoneType + " phone number for userId " + userId + " has been deleted successfully ");
				System.out.println("****************************************************");

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
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
	 * @see com.project.user.beans.UserPhoneSessionBeanLocal#updateUserPhone(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateUserPhone(Long userId, String type, String phoneNumber) throws SQLException {

		Connection conn = null;
		PreparedStatement preparedStmt = null;
		try {

			if (userId == null || type == null || phoneNumber == null) {
				System.out.println("Validation failed. Please provide proper inputs !!!");
			} else {

				conn = new DBConnectionUtil().getDBConnection();

				// create the java mysql update preparedstatement
				String query = UserDataConstants.UPDATE_QUERY_FOR_USER_PHONE_TABLE;
				

				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, phoneNumber);
				preparedStmt.setInt(2, userId.intValue());
				preparedStmt.setString(3, type);

				// execute the preparedstatement
				preparedStmt.execute();

				conn.close();

				System.out.println("****************************************************");
				System.out.println(type + " phone number for user_id :- " + userId + " has been updated successfully ");
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
	 * @see com.project.user.beans.UserPhoneSessionBeanLocal#getUserPhones(java.lang.Long)
	 */
	@Override
	public List<UserPhone> getUserPhones(Long userId) throws SQLException {
		List<UserPhone> userPhoneList = new ArrayList<UserPhone>();

		Connection conn = null;
		PreparedStatement preparedStmt = null;
		try {

			conn = new DBConnectionUtil().getDBConnection();

			String query = UserDataConstants.SELECT_QUERY_FOR_USER_PHONE_TABLE; 

			// create the mysql insert preparedstatement
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, userId.intValue());

			// execute the query, and get a java result set
			ResultSet rs = preparedStmt.executeQuery();

			int id = 0;
			String phoneNumberType = "";

			// iterate through the java result set
			while (rs.next()) {

				id = rs.getInt("id");
				phoneNumberType = rs.getString("type");
				String phoneNumber = rs.getString("phoneNumber");
				String createdBy = rs.getString("createdby");
				Date createdOn = rs.getDate("createdon");

				UserPhone userPhone = new UserPhone();
				userPhone.setId(id);
				userPhone.setUserId(userId.intValue());
				userPhone.setPhoneNumber(phoneNumber);
				userPhone.setCreatedBy(createdBy);
				userPhone.setCreatedOn(createdOn);
				userPhone.setPhoneNumberType(phoneNumberType);

				userPhoneList.add(userPhone);
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

		return userPhoneList;
	}

	/* (non-Javadoc)
	 * @see com.project.user.beans.UserPhoneSessionBeanLocal#addUserPhone(java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addUserPhone(Long userId, String phoneNumber, String type, String createdBy) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStmt = null;
		try {
			String email = "";
			String userName = "";
			String password = "";

			if (userId == null || phoneNumber == null || type == null || createdBy == null) {
				System.out.println("Validation failed. Please provide proper inputs !!!");
			} else {

				conn = new DBConnectionUtil().getDBConnection();

				// the mysql insert statement
				String query = UserDataConstants.INSERT_QUERY_FOR_USER_PHONE_TABLE;

				// create the mysql insert preparedstatement
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1, userId.intValue());
				preparedStmt.setString(2, phoneNumber);
				preparedStmt.setString(3, createdBy);

				Date dt = new Date();
				preparedStmt.setDate(4, (java.sql.Date) dt);

				// execute the preparedstatement
				preparedStmt.execute();

				System.out.println("****************************************************");
				System.out.println("user phone has been added successfully");
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
