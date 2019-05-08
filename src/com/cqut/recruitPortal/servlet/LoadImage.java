package com.cqut.recruitPortal.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadImage
 */
public class LoadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		String fileName = request.getParameter("fileName");
		if(type.equals("head")){
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(SysConstant.HEAD_PATH+File.separator+fileName);
			
			byte buffer[]=new byte[1024];
			int c;
			while((c=in.read(buffer))!=-1){
	             out.write(buffer, 0, c);
	        }
			out.flush();
			out.close();
			in.close();
		}
		
	}

}
