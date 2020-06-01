package com.tallybook.service;

import java.sql.Connection;
import java.util.List;

import com.tallybook.bean.TypeBean;

public interface TypeService {
	public List<TypeBean> getTypes(Connection conn,int select);
	
	
	public void addRecord(Connection conn,double amount,String type,String remark,int userid);
}
