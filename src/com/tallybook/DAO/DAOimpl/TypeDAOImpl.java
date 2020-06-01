package com.tallybook.DAO.DAOimpl;

import java.sql.Connection;
import java.util.List;

import com.tallybook.DAO.BaseDAO;
import com.tallybook.DAO.TypeDAO;
import com.tallybook.bean.TypeBean;

public class TypeDAOImpl extends BaseDAO<TypeBean> implements TypeDAO {

	@Override
	public List<TypeBean> getTypes(Connection conn, int select) {
		String sql = "SELECT type_name typename FROM `types` WHERE paytype_id = ?;";
		return getForList(conn, sql, select);
	}

	@Override
	public void addRecord(Connection conn, double amount, int paytypeid, String remark, int userid) {
		String sql = "INSERT INTO records(timing,amount,type_id,userid,remark) VALUES(NOW(),?,?,?,?)";
		update(conn, sql, amount ,paytypeid,userid,remark);
	}

	@Override
	public int getPayTypeId(Connection conn, String typename) {
		String sql = "SELECT paytype_id\r\n" + 
				"FROM `types`\r\n" + 
				"WHERE type_name =?;";
		return getValue(conn, sql, typename);
	}
	@Override
	public int getTypeId(Connection conn, String typename) {
		String sql = "SELECT type_id\r\n" + 
				"FROM `types`\r\n" + 
				"WHERE type_name =?;";
		return getValue(conn, sql, typename);
	}
	
	@Override
	public List<TypeBean> getTypesAndPaytypeId(Connection conn,int pageNo,int pageSize) {
		String sql = "SELECT type_name typename,paytype_id paytypeid,type_id typeid FROM `types` ORDER BY type_id DESC limit ?,?";
		return getForList(conn, sql,pageNo,pageSize);
	}

	@Override
	public int countType(Connection conn) {
		String sql = "SELECT count(*) FROM `types`";
		return ((Long)getValue(conn, sql)).intValue();
	}

	@Override
	public void delType(Connection conn,String typeid) {
		String sql = "DELETE FROM `types`\r\n" + 
				"WHERE type_id=?;";
		update(conn, sql, typeid);
	}

	@Override
	public void alterType(Connection conn, String typeid,String typename,String paytype) {
		String sql = "UPDATE `types`\r\n" + 
				"SET type_name=?,paytype_id=?\r\n" + 
				"WHERE type_id=?";
		update(conn, sql, typename,paytype,typeid);
	}

	@Override
	public void addNewType(Connection conn, String typename, String paytype) {
		String sql = "insert into `types`\r\n" + 
				"values(null,?,?)";
		update(conn, sql, typename,paytype);
	}

}
