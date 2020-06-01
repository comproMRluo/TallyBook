package com.tallybook.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tallybook.bean.UserBean;

/**
 * Servlet Filter implementation class CheckLoginFilter
 */
public class CheckLoginFilter extends HttpFilter implements Filter {
       
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute("User");
		String type = request.getParameter("select2");
		if(type==null) {
			chain.doFilter(request, response);
		}else {
			if(user != null) {
				chain.doFilter(request, response);
			}else {
				request.setAttribute("msg", "请先登陆(*^__^*)");
				request.getRequestDispatcher("/page/login.jsp").forward(request, response);
			}
		}
		
	}

}
