package com.project.user.beans;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.project.user.valueobjects.User;

/**
 * @author Amit
 *
 */
@Local
public interface UserSessionBeanLocal{

	/**
	 * @param user
	 * @throws SQLException
	 */
	public void addUser(User user) throws SQLException;

	/**
	 * @param userId
	 * @throws SQLException
	 */
	public void deleteUser(Long userId) throws SQLException;

	/**
	 * @param user
	 * @throws SQLException
	 */
	public void updateUser(User user) throws SQLException;

	/**
	 * @return
	 * @throws SQLException
	 */
	public List<User> getUsers() throws SQLException;

}
