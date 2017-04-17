package com.project.user.valueobjects;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Amit
 *
 * value object class for user table
 */
@XmlRootElement(name = "user") 
public class User implements Serializable {
	
	private int userId;
	private String emailId;
	private String userName;
	private String password;
	
	private List<UserHobby> userHobby;
	private UserRole userRole;
	private List<UserPhone> userPhone;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return emailId;
	}
	
	@XmlElement
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	@XmlElement
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<UserHobby> getUserHobby() {
		return userHobby;
	}
	
	@XmlElement
	public void setUserHobby(List<UserHobby> userHobby) {
		this.userHobby = userHobby;
	}
	
	public UserRole getUserRole() {
		return userRole;
	}
	
	@XmlElement
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	public List<UserPhone> getUserPhone() {
		return userPhone;
	}
	
	@XmlElement
	public void setUserPhone(List<UserPhone> userPhone) {
		this.userPhone = userPhone;
	}

}
