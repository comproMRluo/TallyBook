package com.tallybook.service.serviceImpl;

import java.sql.Connection;

import com.tallybook.DAO.TypeDAO;
import com.tallybook.DAO.UserDAO;
import com.tallybook.DAO.DAOimpl.TypeDAOImpl;
import com.tallybook.DAO.DAOimpl.UserDAOImpl;
import com.tallybook.bean.Page;
import com.tallybook.bean.TypeBean;
import com.tallybook.bean.UserBean;
import com.tallybook.service.SuperPowerService;

public class SuperPowerServiceImpl implements SuperPowerService {

	@Override
	public Page<TypeBean> getTypesAndPaytypeId(Connection conn ,String pageNo) {
		Page<TypeBean> page = new Page<>();
		int defaultpage = 1;
		try {
			defaultpage = Integer.parseInt(pageNo); 
		}catch(NumberFormatException e){
		}
//		设置page的页号
		page.setPageNO(defaultpage);
		TypeDAO td = new TypeDAOImpl();
//		设置page的总记录条数
		page.setTotalRecord(td.countType(conn));
//		将取得的数据放在page的list里
		page.setList(td.getTypesAndPaytypeId(conn,(page.getPageNO()-1)*page.PAGE_SIZE,page.PAGE_SIZE));
		return page; 
	}

	@Override
	public void delType(Connection conn, String typeid) {
		TypeDAO td = new TypeDAOImpl();
		td.delType(conn, typeid);
		
	}

	@Override
	public void alterType(Connection conn, String typeid,String typename,String paytype) {
		TypeDAO td = new TypeDAOImpl();
//		因为前端select的每一个option的value与数据库对应paytype_id不同,需要处理
		if("0".equals(paytype)) {
			paytype="1";
		}
		else{
			paytype="2";
		}
		td.alterType(conn, typeid, typename, paytype);
	}

	@Override
	public void addNewType(Connection conn, String typename, String paytype) {
		TypeDAO td = new TypeDAOImpl();
		if("0".equals(paytype)) {
			paytype="1";
		}
		else {
			paytype="2";
		}
		td.addNewType(conn, typename, paytype);
	}

	@Override
	public Page<UserBean> checkAllUsers(Connection conn, String pageNo) {
		Page<UserBean> page = new Page<>();
		UserDAO ud = new UserDAOImpl();
		int defaultpage = 1;
		try {
			defaultpage = Integer.parseInt(pageNo); 
		}catch(NumberFormatException e){
		}
		page.setPageNO(defaultpage);
		return ud.checkAllUsers(conn, page);
	}

	@Override
	public void delUser(Connection conn, String userid) {
		UserDAO ud =  new UserDAOImpl();
		ud.delUser(conn, userid);
	}

	@Override
	public void alterUserStatus(Connection conn, String userid,String permission) {
		UserDAO ud =  new UserDAOImpl();
		ud.alterUserStatus(conn, userid ,permission );
	}

}
