package com.tallybook.service.serviceImpl;

import java.sql.Connection;

import com.tallybook.DAO.UserDAO;
import com.tallybook.DAO.DAOimpl.UserDAOImpl;
import com.tallybook.bean.UserBean;
import com.tallybook.service.UserService;

public class UserServiceImpl implements UserService {

	UserDAO ud = new UserDAOImpl();
	
	@Override
	public UserBean getUser(Connection conn, UserBean user) {
		return ud.getUser(conn, user);
	}

	@Override
	public UserBean getUserName(Connection conn, UserBean user) {
		
		return ud.getUserName(conn, user);
	}

	@Override
	public void insertUser(Connection conn, UserBean user) {
		
		//判断填写username的情况
		if("".equals(user.getEmail()) && "".equals(user.getUsername())) {
			ud.insertUser(conn, user);
			
		}else if( !("".equals(user.getEmail())) && "".equals(user.getUsername())) {
			ud.insertUserAndEmail(conn, user);
		}else if("".equals(user.getEmail()) && !("".equals(user.getUsername()))){
			ud.insertUserAndUserName(conn, user);
		}else {
			ud.insertUserAndUserNameEmail(conn, user);
		}
		
	}

}
