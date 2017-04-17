package com.project.user.db.utils;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.project.user.beans.UserPhoneSessionBeanLocal;

/**
 * @author Amit
 * 
 *This class contains the methods to perform CRUD operation on UserPhone table
 *
 */
public class UserPhoneDAO {
	
	@EJB
	private UserPhoneSessionBeanLocal usrPhoneSessionBeanLocal;
	
	/**
	 * @param userId
	 * @param phoneType
	 * @throws SQLException
	 */
	public void deletePhoneForUser(Long userId, String phoneType) throws SQLException {

		// Retrieve the Home Interface using a JNDI Lookup
		// Retrieve the initial context for JNDI. // No properties needed when local
		Context context;

		try {
			context = new InitialContext();

			// Retrieve the home interface using a JNDI lookup using
			usrPhoneSessionBeanLocal = (UserPhoneSessionBeanLocal) context.lookup("java:module/UserPhoneSessionBean");

			if(usrPhoneSessionBeanLocal != null){
				System.out.println("usrPhoneSessionBeanLocal is NOT NULL ");

				usrPhoneSessionBeanLocal.deleteUserPhone(userId, phoneType);;
			} else{
				System.out.println("usrPhoneSessionBeanLocal is NULL ");
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param userId
	 * @param phoneType
	 * @throws SQLException
	 */
	public void updatePhoneForUser(Long userId, String phoneType, String phoneNumber) throws SQLException {

		// Retrieve the Home Interface using a JNDI Lookup
		// Retrieve the initial context for JNDI. // No properties needed when local
		Context context;

		try {
			context = new InitialContext();

			// Retrieve the home interface using a JNDI lookup using
			usrPhoneSessionBeanLocal = (UserPhoneSessionBeanLocal) context.lookup("java:module/UserPhoneSessionBean");

			if(usrPhoneSessionBeanLocal != null){
				System.out.println("usrPhoneSessionBeanLocal is NOT NULL ");

				usrPhoneSessionBeanLocal.updateUserPhone(userId, phoneType, phoneNumber);;
			} else{
				System.out.println("usrPhoneSessionBeanLocal is NULL ");
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}