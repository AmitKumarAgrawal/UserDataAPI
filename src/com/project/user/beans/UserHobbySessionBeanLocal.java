package com.project.user.beans;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.project.user.valueobjects.UserHobby;

/**
 * @author Amit
 *
 */
@Local
public interface UserHobbySessionBeanLocal {

	/**
	 * @param id
	 * @throws SQLException
	 */
	public void deleteUserHobby(Long id) throws SQLException;

	/**
	 * @param id
	 * @param hobby
	 * @throws SQLException
	 */
	public void updateUserHobby(Long id, String hobby) throws SQLException;

	/**
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public List<UserHobby> getUserHobbies(Long userId) throws SQLException;

	/**
	 * @param userId
	 * @param hobby
	 * @param createdBy
	 * @throws SQLException
	 */
	void addUserHobby(Long userId, String hobby, String createdBy) throws SQLException;

}