package com.tallybook.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tallybook.bean.Page;
import com.tallybook.bean.TypeBean;
import com.tallybook.bean.UserBean;
import com.tallybook.service.SuperPowerService;
import com.tallybook.service.serviceImpl.SuperPowerServiceImpl;
import com.tallybook.utils.JDBCUtils;

/**
 * Servlet implementation class SuperPowerServlet
 */
public class SuperPowerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void getTypesAndPaytypeId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		SuperPowerService sps = new SuperPowerServiceImpl();
		Page<TypeBean> typesAndPaytypeId = null;
		String pageNo = request.getParameter("pageNo");
		try {
			conn = JDBCUtils.getConnection3();
			typesAndPaytypeId = sps.getTypesAndPaytypeId(conn,pageNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("page", typesAndPaytypeId);
		
		request.getRequestDispatcher("page/paytype.jsp").forward(request, response);
	}
	
	protected void delType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;	
		SuperPowerService sps = new SuperPowerServiceImpl();
		String typeid = request.getParameter("typeid");
		try {
			conn = JDBCUtils.getConnection3();
			sps.delType(conn, typeid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getTypesAndPaytypeId(request, response);
	}
	
	protected void alterType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;	
		SuperPowerService sps = new SuperPowerServiceImpl();
		String typename = request.getParameter("typename");
		String paytype = request.getParameter("select3");
		String typeid = request.getParameter("typeid");
		try {
			conn = JDBCUtils.getConnection3();
			sps.alterType(conn, typeid, typename, paytype);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getTypesAndPaytypeId(request, response);
	}
	protected void addNewType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;	
		SuperPowerService sps = new SuperPowerServiceImpl();
		String typename = request.getParameter("addtypename");
		String paytype = request.getParameter("select4");
		try {
			conn = JDBCUtils.getConnection3();
			sps.addNewType(conn,typename, paytype);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getTypesAndPaytypeId(request, response);
	}
	
	protected void checkAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;	
		SuperPowerService sps = new SuperPowerServiceImpl();
		Page<UserBean> page = null;
		String pageNo = request.getParameter("pageNo");
		try {
			conn = JDBCUtils.getConnection3();
			page = sps.checkAllUsers(conn, pageNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("page", page);
		request.getRequestDispatcher("page/usermanager.jsp").forward(request, response);
	}
	
	protected void delUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;	
		SuperPowerService sps = new SuperPowerServiceImpl();
		String userid = request.getParameter("userid");
		try {
			conn = JDBCUtils.getConnection3();
			sps.delUser(conn, userid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		checkAllUsers(request, response);
	}
	
	
	protected void alterUserStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;	
		SuperPowerService sps = new SuperPowerServiceImpl();
		String userid = request.getParameter("userid");
		String permission = request.getParameter("permission");
	
		try {
			conn = JDBCUtils.getConnection3();
			sps.alterUserStatus(conn, userid,permission);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		checkAllUsers(request, response);
	}
	
}
