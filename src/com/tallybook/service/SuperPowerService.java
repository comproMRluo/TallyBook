package com.tallybook.service;

import java.sql.Connection;

import com.tallybook.bean.Page;
import com.tallybook.bean.TypeBean;
import com.tallybook.bean.UserBean;

public interface SuperPowerService {
	public Page<TypeBean> getTypesAndPaytypeId(Connection conn ,String pageNo);
	public void delType(Connection conn ,String typeid);
	public void alterType(Connection conn ,String typeid,String typename,String paytype);
	public void addNewType(Connection conn ,String typename,String paytype);
	Page<UserBean> checkAllUsers(Connection conn,String pageNo);
	public void delUser(Connection conn ,String userid);
	public void alterUserStatus(Connection conn ,String userid,String permission);
}
