package com.tallybook.DAO.DAOimpl;

import java.sql.Connection;
import java.util.List;


import com.tallybook.DAO.BaseDAO;
import com.tallybook.DAO.RecordsDAO;
import com.tallybook.bean.Page;
import com.tallybook.bean.RecordBean;



public class RecordsDAOImpl extends BaseDAO<RecordBean> implements RecordsDAO {

	@Override
	public Page<RecordBean> getRecordByPage(Connection conn, Page<RecordBean> page,int userid) {
		String sql="select count(*) from records where userid=?";
		
		page.setTotalRecord(Integer.parseInt(getValue(conn,sql,userid)+""));
		sql="SELECT record_id recordid,timing,type_name type,amount,paytype_id paytype,remark \r\n" + 
				"FROM records re\r\n" + 
				"INNER JOIN `types` tp\r\n" + 
				"ON  re.`type_id`=tp.`type_id`\r\n" + 
				"INNER JOIN users us\r\n" + 
				"ON re.`userid`=us.`userid`\r\n" + 
				"WHERE us.`userid`=?\r\n" + 
				"ORDER BY timing DESC LIMIT ?,?;";
			List<RecordBean> list = getForList(conn,sql,userid,(page.getPageNO()-1)*Page.PAGE_SIZE,Page.PAGE_SIZE);
			page.setList(list);
			return page;
	}
	
	@Override
	public Page<RecordBean> getAllRecordsByPage(Connection conn, Page<RecordBean> page,int permission) {
		String sql="SELECT COUNT(*) \r\n" + 
				"FROM records re\r\n" + 
				"INNER JOIN users us\r\n" + 
				"ON re.`userid`=us.`userid`\r\n" + 
				"WHERE us.`permission`>?;";
		
		page.setTotalRecord(Integer.parseInt(getValue(conn,sql,permission)+""));
		sql="SELECT record_id recordid,timing,type_name type,amount,paytype_id paytype,remark,username\r\n" + 
				"FROM records re\r\n" + 
				"INNER JOIN `types` tp\r\n" + 
				"ON  re.`type_id`=tp.`type_id`\r\n" + 
				"INNER JOIN users us\r\n" + 
				"ON re.`userid`=us.`userid`\r\n" + 
				"WHERE us.`permission`>?\r\n" + 
				"ORDER BY timing DESC LIMIT ?,?;";
		List<RecordBean> list = getForList(conn,sql,permission,(page.getPageNO()-1)*Page.PAGE_SIZE,Page.PAGE_SIZE);
		page.setList(list);
		return page;
	}

	@Override
	public void delRecordByRecordid(Connection conn, String recordid) {
		String sql = "DELETE FROM records WHERE record_id = ?;";
		update(conn, sql, recordid);
	}

	@Override
	public double getTotalout(Connection conn, int userid) {
		String sql = "SELECT SUM(amount)	\r\n" + 
				"FROM records\r\n" + 
				"WHERE userid = ?\r\n" + 
				"AND type_id IN (\r\n" + 
				"	SELECT type_id\r\n" + 
				"	FROM `types`\r\n" + 
				"	WHERE paytype_id = 1\r\n" + 
				");";
		
		if(getValue(conn, sql, userid)==null)
			return 0;
		else
			return (double)getValue(conn, sql, userid);
	}

	@Override
	public double getTotalin(Connection conn, int userid) {
		String sql = "SELECT SUM(amount)	\r\n" + 
				"FROM records\r\n" + 
				"WHERE userid = ?\r\n" + 
				"AND type_id IN (\r\n" + 
				"	SELECT type_id\r\n" + 
				"	FROM `types`\r\n" + 
				"	WHERE paytype_id = 2\r\n" + 
				");";
		if(getValue(conn, sql, userid)==null)
			return 0;
		else
			return (double)getValue(conn, sql, userid);
	}

	@Override
	public double getAccount(Connection conn, int userid) {
		String sql = "SELECT account_amount\r\n" + 
				"FROM users\r\n" + 
				"WHERE userid = ?;";
		return (double)getValue(conn, sql, userid);
	}


	
	

}
