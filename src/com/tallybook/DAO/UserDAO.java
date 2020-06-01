package com.tallybook.DAO;

import java.sql.Connection;

import com.tallybook.bean.Page;
import com.tallybook.bean.UserBean;

public interface UserDAO {
	/**
	 * 登陆时请求数据库获得该用户信息进行核对
	 * @return
	 */
	UserBean getUser(Connection conn,UserBean user);
	
	/**
	 * 	注册时查看账号是否存在
	 * @param conn
	 * @param user
	 * @return
	 */
	UserBean getUserName(Connection conn,UserBean user);
	
	/*
	 * 注册信息无误时向数据课添加新一行数据(账号,密码,手机号)
	 */
	void insertUser(Connection conn,UserBean user);
	/*
	 * 注册信息无误时向数据课添加新一行数据(账号,密码,手机号,名字)
	 */
	void insertUserAndUserName(Connection conn,UserBean user);
	/*
	 * 注册信息无误时向数据课添加新一行数据(账号,密码,手机号,邮箱)
	 */
	void insertUserAndEmail(Connection conn,UserBean user);
	/*
	 * 注册信息无误时向数据课添加新一行数据(账号,密码,手机号,名字,邮箱)
	 */
	void insertUserAndUserNameEmail(Connection conn,UserBean user);
	
//	记一笔时,根据支出还是收入修改users表中用户的金额
	void addAccount(Connection conn,int userid, double amount);
	
//	获取用户的金额,用于处理用户记一笔时支出金额大于账号金额的情况
	double getAccountAmount(Connection conn,int userid);
	
//	管理员查看所有的用户
	Page<UserBean> checkAllUsers(Connection conn,Page<UserBean> page);
//	管理员删除用户
	void delUser(Connection conn,String userid);
//	管理员修改用户的权限
	void alterUserStatus(Connection conn,String userid,String permission);
}
