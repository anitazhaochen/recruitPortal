package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.entity.Operator;
import com.cqut.recruitPortal.service.OperatorService;

/**
* 项目名称：recruitPortal
* 类名称：AdminLogin
* 类描述：该类负责处理 用户登录 ，它是一个Serlvet，为什么 ？英文它继承至  HttpServlet，要写一个serlvet 程序，
* 必须实现 doGet 和  doPost 方法。这两个方法是用来处理客户端发送的http请求，分别处理get请求和post请求 
* 修改备注：   
* @version 1.0
* Copyright (c) 2014 ChongQing University Of Technology
 */
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	OperatorService service = new OperatorService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//为了方便我们统一将页面的get请求交给doPost方法来处理
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//通过HttpServletRequest 可以得到用户传递过来的信息
		//得到 登录帐号
		String account = request.getParameter("account");
		//得到登录密码
		String password = request.getParameter("password");
		
		//调用OperatorService 来处理登录
		List<Object> result = service.login(account, password);
		
		boolean accountIsOk = Boolean.parseBoolean(result.get(0).toString());
		boolean passwordIsOk = Boolean.parseBoolean(result.get(1).toString());
		
		String accountMessage = "";
		String passwordMessage = "";
		if(!accountIsOk){
			accountMessage = "该帐号不存在";
		}
		
		if(!passwordIsOk){
			passwordMessage = "密码错误";
		}
		
		System.out.println(accountIsOk+","+passwordIsOk);
		
		//登录成功
		if(accountIsOk && passwordIsOk){
			//把登录成功的用户信息存入Session里面。方便以后的调用 
			request.getSession().setAttribute("loginOperator", (Operator)result.get(2));
			
			//如果登录成功就去跳转到 /admin/AdminServlet (只是一个Serlvet  在web.xml里面经行了配置的) 
			//跳转到登录界面。为了避免 F5 刷新页面提交表单    我们用 response对象的 sendRedirect方法跳转页面
			response.sendRedirect(SysConstant.serverBasePath(request)+"/AdminServlet");
			
		}
		//登录失败 ，还是跳转到登录界面 
		else{
			request.setAttribute("account", account);
			request.setAttribute("accountMessage", accountMessage);
			
			request.setAttribute("password", password);
			request.setAttribute("passwordMessage", passwordMessage);
			request.getServletContext().getRequestDispatcher("/admin/login.jsp").forward(request, response);
		}
		
	}

}
