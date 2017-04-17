package com.project.user.db.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.project.user.beans.UserHobbySessionBeanLocal;
import com.project.user.valueobjects.UserHobby;

/**
 * @author Amit
 *
 *This class contains the methods to perform CRUD operation on UserHobby table
 *
 */
public class UserHobbyDAO {

	@EJB
	private UserHobbySessionBeanLocal usrHobbySessionBeanLocal;

	/**
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public List<UserHobby> getHobbiesForUser(Long userId) throws SQLException {

		// Retrieve the initial context for JNDI. 
		// No properties needed when local interface is used
		Context context;
		List<UserHobby> userHobbyList = new ArrayList<UserHobby>();

		try {
			context = new InitialContext();

			// Retrieve the home interface using a JNDI lookup
			usrHobbySessionBeanLocal = (UserHobbySessionBeanLocal) context.lookup("java:module/UserHobbySessionBean");

			if (usrHobbySessionBeanLocal != null) {
				System.out.println("usrHobbySessionBeanLocal is NOT NULL ");

				userHobbyList = usrHobbySessionBeanLocal.getUserHobbies(userId);

				return userHobbyList;
			} else {

				System.out.println("usrHobbySessionBeanLocal is NULL ");
				return userHobbyList;
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userHobbyList;
	}

}