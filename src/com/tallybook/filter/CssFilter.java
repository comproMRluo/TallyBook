package com.tallybook.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CssFilter extends HttpFilter implements Filter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 //        设置请求编码格式
		request.setCharacterEncoding("utf-8");  //post 改变(请求实体)
        //        设置响应编码格式
		response.setContentType("text/css;charset=utf-8");//修改响应编码
        chain.doFilter(request, response);
	}
       
    
}
