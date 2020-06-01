package com.tallybook.DAO;

import java.sql.Connection;
import java.util.List;

import com.tallybook.bean.Page;
import com.tallybook.bean.RecordBean;

import com.tallybook.bean.TypeBean;

public interface RecordsDAO {
//	获取详细信息界面数据
	public Page<RecordBean> getRecordByPage(Connection conn,Page<RecordBean> page,int userid);
//	获取管理员查看的所有用户数据(除了上级和同级的人员数据)
	public Page<RecordBean> getAllRecordsByPage(Connection conn,Page<RecordBean> page ,int permission);
//	根据记录id删除一条记录
	public void delRecordByRecordid(Connection conn,String recordid);
//	根据userid就算所有的支出	
	public double getTotalout(Connection conn,int userid);
	
//	根据userid就算所有的收入		
	public double getTotalin(Connection conn,int userid);
//  获取当前用户的余额	
	public double getAccount(Connection conn,int userid);
	

}
