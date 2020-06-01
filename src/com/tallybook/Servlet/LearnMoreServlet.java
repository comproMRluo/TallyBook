package com.tallybook.Servlet;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LearnMoreServlet
 */
public class LearnMoreServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected void learnMore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文件在服务器上  WEB-INF/video

		String realPath = request.getServletContext().getRealPath("/WEB-INF/video");
		response.setContentType("video/mp4");
		
		FileInputStream fis = new FileInputStream(realPath+"/武汉加油！.mp4");
		ServletOutputStream ops = response.getOutputStream();
		byte[] b = new byte[1024];
//		3.进行边读边输出文件资源
		try {
			while(fis.read(b)!=-1) {
				ops.write(b);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			
		}finally {
			fis.close();
			ops.close();
			
		}
//		4.关闭流
		
		
	}  	

}
