package com.tallybook.service.serviceImpl;

import java.sql.Connection;
import java.util.List;

import com.tallybook.DAO.TypeDAO;
import com.tallybook.DAO.DAOimpl.TypeDAOImpl;
import com.tallybook.DAO.DAOimpl.UserDAOImpl;
import com.tallybook.bean.TypeBean;
import com.tallybook.service.TypeService;
import com.tallybook.service.UserService;

public class TypeServiceImpl implements TypeService {

	@Override
	public List<TypeBean> getTypes(Connection conn, int select) {
		TypeDAO td = new TypeDAOImpl();
		return td.getTypes(conn, select);
	}

	@Override
	public void addRecord(Connection conn, double amount, String type, String remark, int userid) {
		TypeDAO td = new TypeDAOImpl();
		UserDAOImpl ud = new UserDAOImpl();
		
		int typeId = td.getTypeId(conn, type);
		if("".equals(amount+"")) {
			amount=0.00;
		}if("".equals(remark)){
			remark="无";
		}
		td.addRecord(conn, amount, typeId, remark, userid);
		
		double Amount = ud.getAccountAmount(conn, userid);
//		获取paytype_id
		int payTypeId = td.getPayTypeId(conn, type);
		if(payTypeId == 1) {
			ud.addAccount(conn, userid, Amount-amount);
		}
		else {
			ud.addAccount(conn, userid,Amount+amount);
			
		}
		
	}



}
