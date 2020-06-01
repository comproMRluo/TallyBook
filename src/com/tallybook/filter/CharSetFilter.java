package com.tallybook.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet Filter implementation class CharSetFilter
 */
public class CharSetFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		删除了之前创建的一系列无用的cookie
//		Cookie[] cookies = request.getCookies();
//		for (Cookie cookie : cookies) {
//			if(!("JSESSIONID".equals(cookie.getName()))){
//				System.out.println(cookie.getName()+"删除");
//				cookie.setMaxAge(-1);
//				response.addCookie(cookie);
//			}
//		}
		String code = this.getFilterConfig().getInitParameter("code");
		request.setCharacterEncoding(code);
		String ifLearnMore = request.getParameter("LearnMore");
		if("".equals(ifLearnMore)||ifLearnMore==null) {	
			response.setContentType("text/html;charset=UTF-8");
		}
		chain.doFilter(request, response);
	}
       

}
