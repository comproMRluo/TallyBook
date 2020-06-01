package com.tallybook.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

import com.tallybook.bean.UserBean;
import com.tallybook.service.UserService;
import com.tallybook.service.serviceImpl.UserServiceImpl;
import com.tallybook.utils.JDBCUtils;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService us = new UserServiceImpl();
		
		HttpSession session = request.getSession();
		String id = session.getId();
		
		Connection conn = null;
		UserBean userBean = null;
		Cookie cookie = null;
		//获取登陆输入的信息
		String user = request.getParameter("user");
		String pw = request.getParameter("pw");
		String rembpsw = request.getParameter("rembpsw");
		
		
		//账号信息
//		session.setAttribute("accountManagement", user);
		session.setAttribute("accountManagement", user);
		//解决重启浏览器登陆数据持久化问题(cookie持久化记录账号和密码)session无法解决
//		accountManagement = new Cookie("accountManagement",user);		
//		accountManagement.setMaxAge(60*60*24*7);
//		response.addCookie(accountManagement);
		
		
		
		
		try {
			 conn = JDBCUtils.getConnection3();
			 userBean = us.getUser(conn,new UserBean(0,user,pw,null,null,0,null));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			
		}
		if(userBean != null) {
//			password = new Cookie("password",pw);	
			if(rembpsw.equals("1")) {
//				session.setAttribute("password", pw);
//				password.setMaxAge(60*60*24*7);
				session.setAttribute("password", pw);
				session.setMaxInactiveInterval(60*60*24*7);
			}else {
//				session.removeAttribute("password");
//				password.setMaxAge(1);
				session.removeAttribute("password");
				session.setMaxInactiveInterval(60*60*24*30);
			}
//			session.setMaxInactiveInterval(60*60*24*7);
//			response.addCookie(password);
			
			session.setAttribute("User", userBean);
			cookie = new Cookie("JSESSIONID",id);
			cookie.setMaxAge(60*60*24*7);
			response.addCookie(cookie);

			new RecordServlet().showConsumption(request, response);
		}else {
			session.removeAttribute("password");
			session.removeAttribute("User");
			request.setAttribute("msg","您输入的账号或密码有误!请重新输入!");
			request.getRequestDispatcher("/page/login.jsp").forward(request, response);
		}
	}
	
	protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		session.removeAttribute("User");
		
		new RecordServlet().showConsumption(request, response);
		
		
	}
	
	
	protected void checkName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService us = new UserServiceImpl();
		String accountManagement = request.getParameter("accountManagement");
		Connection conn = null;
		UserBean user = null;
		PrintWriter writer = response.getWriter();
		try {
			conn = JDBCUtils.getConnection3();
			user = us.getUserName(conn, new UserBean(0,accountManagement,null,null,null,0,null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(user!=null && accountManagement.equals(user.getAccountManagement())) {
			writer.print(true);
		}else {
			writer.print(false);
		}
		
	}
	
	
	
	protected void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		UserService us = new UserServiceImpl();
		UserBean ub = null;
		
		String accountManagement = request.getParameter("accountManagement");
		String accountPassword = request.getParameter("accountPassword");
		String phone = request.getParameter("phone");
		String username = request.getParameter("username");
		String email = request.getParameter("email");

		try {
			ub = new UserBean(0,accountManagement,accountPassword,phone,email,0,username);
			conn = JDBCUtils.getConnection3();
			us.insertUser(conn, ub);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/page/registersuccess.jsp").forward(request, response);

	}
	
	protected void checkCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取输入验证码
		String code = request.getParameter("code");
		//获取kaptcha生成的验证码,存在session域中name为KAPTCHA_SESSION_KEY
		HttpSession session = request.getSession();
		Object ocode = session.getAttribute("KAPTCHA_SESSION_KEY");
		PrintWriter writer = response.getWriter();
		if(ocode!=null && code.equals(ocode.toString())) {
			writer.print(true);
		}else {
			writer.print(false);
		}
		
		
	}
	
}
