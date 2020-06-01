package com.tallybook.JUNITEST;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.tallybook.DAO.DAOimpl.RecordsDAOImpl;
import com.tallybook.DAO.RecordsDAO;
import com.tallybook.bean.Page;
import com.tallybook.bean.RecordBean;
import com.tallybook.bean.UserBean;
import com.tallybook.utils.JDBCUtils;

public class RecordsDemo {

	@Test
	public void test() {
//		RecordsDAO rd = new RecordsDAOImpl();
//		Connection conn = null;
//		try {
//			conn = JDBCUtils.getConnection3();
////			Page<RecordBean> recordByPage = rd.getRecordByPage(conn, new Page<RecordBean>(2,8,5,null), "1");
//			for (RecordBean recordBean : recordByPage.getList()) {
//				System.out.println(recordBean.toString());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			JDBCUtils.closeResource(conn, null,null);
//		}
	}

}
