package com.project.user.beans;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.project.user.valueobjects.UserPhone;

/**
 * @author Amit
 *
 */
@Local
public interface UserPhoneSessionBeanLocal {

	/**
	 * @param userId
	 * @param phoneType
	 * @throws SQLException
	 */
	public void deleteUserPhone(Long userId, String phoneType) throws SQLException;

	/**
	 * @param id
	 * @param type
	 * @param phoneNumber
	 * @throws SQLException
	 */
	public void updateUserPhone(Long id, String type, String phoneNumber) throws SQLException;

	/**
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public List<UserPhone> getUserPhones(Long userId) throws SQLException;

	/**
	 * @param userId
	 * @param phoneNumber
	 * @param type
	 * @param createdBy
	 * @throws SQLException
	 */
	void addUserPhone(Long userId, String phoneNumber, String type, String createdBy) throws SQLException;

}