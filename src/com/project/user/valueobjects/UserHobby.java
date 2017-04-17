package com.project.user.valueobjects;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Amit
 *
 *
 *value object class for userHobby table
 */
@XmlRootElement(name = "userhobby") 
public class UserHobby {
	
	private int id;
	private int userId;
	private String hobby;
	private String createdBy;
	private Date createdOn;
	
	public int getId() {
		return id;
	}
	
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	@XmlElement
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getHobby() {
		return hobby;
	}
	
	@XmlElement
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	@XmlElement
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}
	
	@XmlElement
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


}
