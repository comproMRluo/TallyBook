package com.tallybook.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tallybook.utils.JDBCUtils;


/**
 * Servlet Filter implementation class TransactionFilter
 */
public class TransactionFilter extends HttpFilter implements Filter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection3();

			conn.setAutoCommit(false);

			chain.doFilter(request, response);

			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
			}
			
		}finally {
			

			JDBCUtils.closeResource();
		}
	}
    
}
