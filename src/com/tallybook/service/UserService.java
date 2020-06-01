package com.tallybook.service;

import java.sql.Connection;

import com.tallybook.bean.UserBean;

public interface UserService {
	UserBean getUser(Connection conn,UserBean user);
	
	UserBean getUserName(Connection conn,UserBean user);
	
	void insertUser(Connection conn,UserBean user);
}
