package com.tallybook.DAO.DAOimpl;

import java.sql.Connection;
import java.util.List;

import com.tallybook.DAO.BaseDAO;
import com.tallybook.DAO.UserDAO;
import com.tallybook.bean.Page;

import com.tallybook.bean.UserBean;

public class UserDAOImpl extends BaseDAO<UserBean> implements UserDAO {

	@Override
	public UserBean getUser(Connection conn, UserBean user) {
		String sql = "select userid id,account_management accountManagement,account_password accountPassword,username,permission from users where account_management=? and `account_password`=?";
		return getInstance(conn,sql,user.getAccountManagement(),user.getAccountPassword());
	}

	@Override
	public UserBean getUserName(Connection conn, UserBean user) {
		String sql = "select account_management accountManagement from users where account_management=?";
		return getInstance(conn,sql,user.getAccountManagement());
	}

	@Override
	public void insertUser(Connection conn, UserBean user) {
		String sql = "insert into users(account_management,account_password,phone_number) values(?,?,?)";
		update(conn,sql,user.getAccountManagement(),user.getAccountPassword(),user.getPhoneNumber());
	}

	@Override
	public void insertUserAndUserName(Connection conn, UserBean user) {
		
		String sql = "insert into users(account_management,account_password,phone_number,username) values(?,?,?,?)";
		update(conn,sql,user.getAccountManagement(),user.getAccountPassword(),user.getPhoneNumber(),user.getUsername());
	}

	@Override
	public void insertUserAndEmail(Connection conn, UserBean user) {
		String sql = "insert into users(account_management,account_password,phone_number,email) values(?,?,?,?)";
		update(conn,sql,user.getAccountManagement(),user.getAccountPassword(),user.getPhoneNumber(),user.getEmail());
		
	}

	@Override
	public void insertUserAndUserNameEmail(Connection conn, UserBean user) {
		String sql = "insert into users(account_management,account_password,phone_number,username,email) values(?,?,?,?,?)";
		update(conn,sql,user.getAccountManagement(),user.getAccountPassword(),user.getPhoneNumber(),user.getUsername(),user.getEmail());
	}

	@Override
	public void addAccount(Connection conn, int userid, double amount) {
		String sql = "UPDATE users \r\n" + 
				"SET account_amount =? \r\n" + 
				"WHERE userid =?;";
		update(conn, sql, amount,userid);
	}

	@Override
	public double getAccountAmount(Connection conn, int userid) {
		String sql = "SELECT account_amount FROM users\r\n" + 
				"WHERE userid = ?;";
		return getValue(conn, sql, userid);
	}

	@Override
	public Page<UserBean> checkAllUsers(Connection conn,Page<UserBean> page) {
		
		String sql = "SELECT COUNT(*) FROM users;";
		page.setTotalRecord(Integer.parseInt(getValue(conn, sql)+""));
		
		sql = "SELECT account_management accountManagement,username,permission permission,userid id\r\n" + 
				"FROM users ORDER BY userid DESC limit ?,?;";
		List<UserBean> list =getForList(conn, sql,(page.getPageNO()-1)*page.PAGE_SIZE,page.PAGE_SIZE);
		page.setList(list);
		return page;
	}

	@Override
	public void delUser(Connection conn, String userid) {
		String sql = "DELETE FROM users\r\n" + 
				"WHERE userid = ?;";
		update(conn, sql, userid);
	}

	@Override
	public void alterUserStatus(Connection conn, String userid ,String permission) {
		String sql = "UPDATE users\r\n" + 
				"SET permission = ?\r\n" + 
				"WHERE userid = ?;";
		update(conn, sql,permission, userid);
	}

}
