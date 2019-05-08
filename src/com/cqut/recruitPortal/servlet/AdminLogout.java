package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* 项目名称：recruitPortal
* 类名称：AdminLogout
* 类描述：   该类用户处理用户注销登录的请求
* 修改备注：   
* @version 1.0
* Copyright (c) 2014 ChongQing University Of Technology
 */
public class AdminLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//注销很简单 ，直接调用 HttpSession 的 invalidate方法就可以清空Session里面的所有信息
		request.getSession().invalidate();
		request.getServletContext().getRequestDispatcher("/admin/login.jsp").forward(request, response);
	}

}
