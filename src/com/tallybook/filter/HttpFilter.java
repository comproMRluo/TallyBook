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


/**
 * 	类似于HttpServlet便于Filter直接使用doFilter而过滤掉一些细节
 * 	不需要也不能够注册在web.xml中
 * @author Luo
 *
 */
public abstract class HttpFilter implements Filter {
	
	private FilterConfig filterConfig = null;
	
	

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public HttpFilter() {
    }

	public void destroy() {
	}
	/**
	 * 	为继承HttpFilter的类做转换功能
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		doFilter(req,res,chain);
	}
	
	/**
	 * 	抽象出doFilter 谁继承谁来实现doFilter的内容
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

	/**
	 * 	初始化web.xml中的init-paramz中的内容配置
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		filterConfig = fConfig;
	}

}
