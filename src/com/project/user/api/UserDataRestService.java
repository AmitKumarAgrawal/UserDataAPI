package com.project.user.api;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.project.user.db.utils.UserDAO;
import com.project.user.db.utils.UserHobbyDAO;
import com.project.user.db.utils.UserPhoneDAO;
import com.project.user.valueobjects.User;
import com.project.user.valueobjects.UserHobby;

/**
 * @author Amit
 *
 *This class contains REST full methods to perform various user management use case
 *
 */
@Path("/UserDataRestService")
public class UserDataRestService {
	

	/**
	 * @return
	 * @throws SQLException
	 * 
	 * returns all the users from the database (user table)
	 */
	@GET
	@Path("/getusers")
	@Produces(MediaType.APPLICATION_XML)
	public List<User> getUsers() throws SQLException {
		UserDAO userDao = new UserDAO();
		return userDao.getAllUsersFromDB();
	}

	/**
	 * @param userid
	 * @return
	 * 			all the hobbies for the provided user_id
	 * @throws SQLException
	 * 
	 * 
	 */
	@GET
	@Path("/getuserhobbies/{userid}")
	@Consumes("text/plain")
	@Produces(MediaType.APPLICATION_XML)
	public List<UserHobby> getHobbies(@PathParam("userid") Long userid) throws SQLException {
		
		System.out.println("userId ::: " + userid);
		
		UserHobbyDAO userHobbyDAO = new UserHobbyDAO();
		return userHobbyDAO.getHobbiesForUser(userid);
	}
	
/*	*//**
	 * @param userid
	 * @param type
	 * @throws SQLException
	 * 
	 * deletes the specific phone number type for the provided user
	 *//*
	@GET
	@Path("/deletephonenumber1/{userid}/{type}")
	@Produces("text/plain")
	@Consumes("text/plain") 
	public void deletePhone1(@PathParam("userid") Long userid, @PathParam("type") String type) throws SQLException{
		System.out.println("userId ::: " + userid + " type :: " + type );
		
		UserPhoneDAO userPhoneDAO = new UserPhoneDAO();
		userPhoneDAO.deletePhoneForUser(userid, type);
		
	}*/
	
	/**
	 * @param userid
	 * @param type
	 * @throws SQLException
	 * 
	 * deletes the specific phone number type for the provided user
	 */
	@DELETE
	@Path("/deletephonenumber/{userid}/{type}")
	@Produces("text/plain")
	@Consumes("text/plain") 
	public void deletePhone(@PathParam("userid") Long userid, @PathParam("type") String type) throws SQLException{
		System.out.println("userId ::: " + userid + " type :: " + type );
		
		UserPhoneDAO userPhoneDAO = new UserPhoneDAO();
		userPhoneDAO.deletePhoneForUser(userid, type);
		
	}

	
	
	/**
	 * @param userid
	 * @param type
	 * @param phonenumber
	 * @throws SQLException
	 * 
	 * updates the specific phone number type for the provided user_id
	 */
	@PUT
	@Path("/updatephonenumber/{userid}/{type}/{phonenumber}")
	@Produces("text/plain")
	@Consumes("text/plain") 
	public void updatePhone(@PathParam("userid") Long userid, @PathParam("type") String type, @PathParam("phonenumber") String phonenumber) throws SQLException{
		System.out.println("userId ::: " + userid + " type :: " + type  + " phonenumber :: " + phonenumber);
		
		UserPhoneDAO userPhoneDAO = new UserPhoneDAO();
		userPhoneDAO.updatePhoneForUser(userid, type, phonenumber);
	}
	
	
	/**
	 * @param email
	 * @param username
	 * @param password
	 * @throws SQLException
	 * 
	 * creates a new user in the database by using the provided inputs in the Path parameter
	 */
	@POST
	@Path("/adduser1/{email}/{username}/{password}")
	@Produces("text/plain")
	@Consumes("text/plain") 
	public void addUser(@PathParam("email") String email, @PathParam("username") String username, @PathParam("password") String password) throws SQLException{
		System.out.println("email ::: " + email + " username :: " + username  + " password :: " + password);
		
		UserDAO userDao = new UserDAO();
		userDao.addUser(email, username, password);
	}
	
	/**
	 * @param user
	 * @throws Exception
	 * 
	 * 	 creates a new user in the database by using the provided xml object
	 */
	@POST
	@Path("/adduser")
	@Produces("text/plain")
	@Consumes(MediaType.APPLICATION_XML) 
	public void addUser(User user) throws Exception {

	    String email = (String) user.getEmailId();
	    String username = (String) user.getUserName();
	    String password = (String) user.getPassword();
	    
		System.out.println("email ::: " + email + " username :: " + username + " password :: " + password);
		
		UserDAO userDao = new UserDAO();
		userDao.addUser(email, username, password);
	}


}
