package com.tallybook.JUNITEST;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.tallybook.DAO.TypeDAO;
import com.tallybook.DAO.DAOimpl.TypeDAOImpl;
import com.tallybook.bean.TypeBean;
import com.tallybook.utils.JDBCUtils;

public class TypeDAOTexxt {

	@Test
	public void test() {
		Connection conn = null;
		TypeDAO td = new TypeDAOImpl();
		
		try {
			conn = JDBCUtils.getConnection3();
			List<TypeBean> list = td.getTypes(conn, 1);
			for (TypeBean typeBean : list) {
				System.out.println(typeBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
