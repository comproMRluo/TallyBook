package com.tallybook.service;

import java.sql.Connection;

import com.tallybook.bean.Page;
import com.tallybook.bean.RecordBean;
import com.tallybook.bean.RecordsBean;

public interface RecordsService {
	public Page<RecordBean> getRecordByPage(Connection conn,String pageNo,int userid);
	public Page<RecordBean> getAllRecordsByPage(Connection conn,String pageNo,int permission);
	public void delRecordByRecordid(Connection conn,String recordid);
	public RecordsBean showConsumption(Connection conn,int userid);
	public double getAccountAmount(Connection conn, int userid);
}
