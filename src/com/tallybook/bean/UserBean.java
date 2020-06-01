package com.tallybook.bean;

import java.io.Serializable;


/**
 * 	用户数据存放的封装对象
 * @author Luo
 *
 */
public class UserBean implements Serializable{
	private int id;
	private String accountManagement;
	private String accountPassword;
	private String phoneNumber; 
	private String email; 
	private int permission;
	private String username;
	public UserBean(int id, String accountManagement, String accountPassword, String phoneNumber, String email,
			int permission ,String username) {
		this.id = id;
		this.accountManagement = accountManagement;
		this.accountPassword = accountPassword;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.permission = permission;
		this.username = username;
	}
	public UserBean() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccountManagement() {
		return accountManagement;
	}
	public void setAccountManagement(String accountManagement) {
		this.accountManagement = accountManagement;
	}
	public String getAccountPassword() {
		return accountPassword;
	}
	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	@Override
	public String toString() {
		return "UserBean [id=" + id + ", accountManagement=" + accountManagement + ", accountPassword="
				+ accountPassword + ", phoneNumber=" + phoneNumber + ", email=" + email + ", permission=" + permission
				+ ", username=" + username + "]";
	}
	
	
}
