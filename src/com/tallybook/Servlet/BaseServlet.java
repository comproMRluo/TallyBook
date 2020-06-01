package com.tallybook.Servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 * 	基本的Servlet类,有通用的doget和dopost操作
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BaseServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		有CharSetFilter处理字符集问题 
//    	request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");
		
		String method = request.getParameter("method");
//		在复杂的工程中会有很多的的功能方法,因此需要更加灵活的使用的方法来判断
//		利用反射通过方法名来动态获取方法对象并执行
		try {
			this.getClass().getDeclaredMethod(method,HttpServletRequest.class,HttpServletResponse.class).invoke(this,request,response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			throw new RuntimeException(e);
		}
//		if("login".equals(method)) {
//			login(request, response);
//		}else {
//			zhuce(request, response);
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
