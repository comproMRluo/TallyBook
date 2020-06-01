package com.tallybook.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tallybook.bean.Page;
import com.tallybook.bean.RecordBean;
import com.tallybook.bean.RecordsBean;
import com.tallybook.bean.TypeBean;
import com.tallybook.bean.UserBean;
import com.tallybook.service.RecordsService;
import com.tallybook.service.TypeService;
import com.tallybook.service.serviceImpl.RecordsServiceImpl;
import com.tallybook.service.serviceImpl.TypeServiceImpl;
import com.tallybook.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class RecordServlet
 */
public class RecordServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	void getRecordByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn =null;
		Page<RecordBean> page =null;
		RecordsService rs = new RecordsServiceImpl();
		HttpSession session = request.getSession();
		String pageNo = null;
		//1.获得请求的页号
		
		pageNo = request.getParameter("pageNo");
			
		
		if((UserBean)session.getAttribute("User")!=null) {
			int userid = ((UserBean)session.getAttribute("User")).getId();
			//2.请求service
			try {
				
				conn = JDBCUtils.getConnection3();
				page = rs.getRecordByPage(conn, pageNo, userid);
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		
		}
		//3.返回给前端
		request.setAttribute("page", page);
		request.getRequestDispatcher("page/recordlist.jsp").forward(request, response);
	}
	
	void getAllRecordsByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn =null;
		Page<RecordBean> page =null;
		RecordsService rs = new RecordsServiceImpl();
		String pageNo = null;
		HttpSession session = request.getSession();
		//1.获得请求的页号
		
		pageNo = request.getParameter("pageNo");
		
		if((UserBean)session.getAttribute("User")!=null) {
			//2.请求service
			int permission = ((UserBean)session.getAttribute("User")).getPermission();
			System.out.println(permission);
			try {
				
				conn = JDBCUtils.getConnection3();
				page = rs.getAllRecordsByPage(conn, pageNo,permission);
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
			
		//3.返回给前端
		request.setAttribute("page", page);
		request.getRequestDispatcher("page/checkallrecords.jsp").forward(request, response);
	}
	
	void delRecordByRecordid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		RecordsService rs = new RecordsServiceImpl();
		String recordid = request.getParameter("recordid");
		String ismanager = request.getParameter("ismanager");
		
		try {
			conn = JDBCUtils.getConnection3();
			rs.delRecordByRecordid(conn, recordid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if("1".equals(ismanager)) {
			getAllRecordsByPage(request, response);
		}else {
			getRecordByPage(request, response);
		}
	}
	
	
	void showConsumption(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		RecordsService rs = new RecordsServiceImpl();
		RecordsBean recordsBean = null;
		HttpSession session = request.getSession();
		if((UserBean)session.getAttribute("User")!=null) {
			int userid = ((UserBean)session.getAttribute("User")).getId();
			try {
				conn = JDBCUtils.getConnection3();
				recordsBean = rs.showConsumption(conn, userid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("recordsBean", recordsBean);
		request.getRequestDispatcher("homepage.jsp").forward(request, response);
		
	}
	
	
	
	
	void typeSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List<TypeBean> list = null;
		String json = null;
		TypeService ts = new TypeServiceImpl();
		String select = request.getParameter("select1");
		if(!"-1".equals(select)) {
			try {
				conn = JDBCUtils.getConnection3();
				if("0".equals(select)) {
					list = ts.getTypes(conn, 1);
				}
				if("1".equals(select)) {
					list = ts.getTypes(conn, 2);
				}
				//转化为json
				Gson gson = new Gson();
				Type type = new TypeToken<List<TypeBean>>(){}.getType();
				json = gson.toJson(list, type);
				response.getWriter().print(json);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	void addRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Connection conn = null;
		TypeService ts = new TypeServiceImpl();
		HttpSession session = request.getSession();
		double amount =Double.parseDouble(request.getParameter("amount"));
		String type = request.getParameter("select2");
		String remark = request.getParameter("remark");
		if((UserBean)session.getAttribute("User")!=null) {
			int userid = ((UserBean)session.getAttribute("User")).getId();
			conn = JDBCUtils.getConnection3();
			ts.addRecord(conn, amount, type, remark, userid);
		}
		
		showConsumption(request, response);
	}
	
	void getAccountAmount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Connection conn = null;
		
		HttpSession session = request.getSession();
		double amount =Double.parseDouble(request.getParameter("amount"));
		RecordsService rs = new RecordsServiceImpl();
		PrintWriter writer = response.getWriter();
		if((UserBean)session.getAttribute("User")!=null) {
			int userid = ((UserBean)session.getAttribute("User")).getId();
			conn = JDBCUtils.getConnection3();
			double accountAmount = rs.getAccountAmount(conn, userid);
			if(accountAmount<amount) {
				writer.print(false);
			}else {
				writer.print(true);
			}
		}
		

	}
	
	
	

}
