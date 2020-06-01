package com.tallybook.service.serviceImpl;


import java.sql.Connection;
import java.text.DecimalFormat;

import com.tallybook.DAO.RecordsDAO;
import com.tallybook.DAO.UserDAO;
import com.tallybook.DAO.DAOimpl.RecordsDAOImpl;
import com.tallybook.DAO.DAOimpl.UserDAOImpl;
import com.tallybook.bean.Page;
import com.tallybook.bean.RecordBean;
import com.tallybook.bean.RecordsBean;
import com.tallybook.service.RecordsService;

public class RecordsServiceImpl implements RecordsService {
	
	@Override
	public Page<RecordBean> getRecordByPage(Connection conn, String pageNo, int userid) {
		RecordsDAO rd = new RecordsDAOImpl();
		Page<RecordBean> page = new Page<>();
//		避免页数出现问题,设置默认页
		int defaultpage = 1;
		try {
			defaultpage = Integer.parseInt(pageNo); 
		}catch(NumberFormatException e){
		}
		page.setPageNO(defaultpage);
		return rd.getRecordByPage(conn, page, userid);
	}
	@Override
	public Page<RecordBean> getAllRecordsByPage(Connection conn, String pageNo,int permission) {
		RecordsDAO rd = new RecordsDAOImpl();
		Page<RecordBean> page = new Page<>();
		int defaultpage = 1;
		try {
			defaultpage = Integer.parseInt(pageNo); 
		}catch(NumberFormatException e){
		}
		page.setPageNO(defaultpage);
		return rd.getAllRecordsByPage(conn, page,permission);
	}
	@Override
	public void delRecordByRecordid(Connection conn, String recordid) {
		RecordsDAO rd = new RecordsDAOImpl();
		rd.delRecordByRecordid(conn, recordid);
	}
	@Override
	public RecordsBean showConsumption(Connection conn, int userid) {
		RecordsDAO rd = new RecordsDAOImpl();
		RecordsBean recordsBean = new RecordsBean();
		DecimalFormat format = new DecimalFormat("0.00");
		//1.获取支出总金额并赋值
		
		double totalout = rd.getTotalout(conn, userid);
		recordsBean.setTotalout(totalout);
		//2.获取总收入并赋值
		
		double totalin = rd.getTotalin(conn, userid);
		recordsBean.setTotalin(totalin);
//		3.获取当前账户余额并赋值
		
		double rest = rd.getAccount(conn, userid);
		recordsBean.setAccountRest(Double.parseDouble(format.format(rest)));
//		4.计算比并赋
		if(totalin==0) {
			recordsBean.setIoratio(Double.parseDouble(format.format(0.0)));
		}else {
			double ratio=totalin/totalout;
			recordsBean.setIoratio(Double.parseDouble(format.format(ratio)));
		}
		
		 
		return recordsBean;
	}
	
	public double getAccountAmount(Connection conn, int userid) {
		UserDAO ud = new UserDAOImpl();
		double accountAmount = ud.getAccountAmount(conn, userid);
		
		return accountAmount;
	}

}
