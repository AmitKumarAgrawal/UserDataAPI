package com.project.user.beans;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.project.user.valueobjects.UserRole;

/**
 * @author Amit
 *
 */
@Local
public interface UserRoleSessionBeanLocal {

	/**
	 * @param roleId
	 * @throws SQLException
	 */
	public void deleteUserRole(Long roleId) throws SQLException;

	/**
	 * @param roleId
	 * @param roleName
	 * @throws SQLException
	 */
	public void updateUserRole(Long roleId, String roleName) throws SQLException;

	/**
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public List<UserRole> getUserRoles(Long userId) throws SQLException;

	/**
	 * @param userId
	 * @param roleName
	 * @throws SQLException
	 */
	void addUserRole(Long userId, String roleName) throws SQLException;

}