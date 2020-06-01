package com.tallybook.JUNITEST;

import static org.junit.Assert.*;

import org.junit.Test;

import java.sql.Connection;


import com.tallybook.DAO.UserDAO;
import com.tallybook.DAO.DAOimpl.UserDAOImpl;
import com.tallybook.bean.UserBean;
import com.tallybook.utils.JDBCUtils;

public class LoginTesst {

	@Test
	public void test() {
		UserDAO ud = new UserDAOImpl();
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection3();
			UserBean user = ud.getUser(conn, new UserBean(0,"1216770825","jd20010116",null,null,0,null));
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeResource(conn, null,null);
		}
		
	}

}
