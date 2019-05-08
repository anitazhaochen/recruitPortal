package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.entity.Operator;
import com.cqut.recruitPortal.service.OperatorService;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	OperatorService service = new OperatorService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		System.out.println(account+";"+password);
		
		
		
		boolean isAccountError = true;
		boolean isPasswordError = true;
		
		List<Object> loginResult = service.login(account, password);
		isAccountError = Boolean.parseBoolean(loginResult.get(0).toString());
		isPasswordError = Boolean.parseBoolean(loginResult.get(1).toString());
		
		if(!isAccountError && !isPasswordError){
			Operator operator = (Operator) loginResult.get(2);
			System.out.println(operator.getName());
			request.getSession().setAttribute("operator", operator);
			response.sendRedirect(SysConstant.serverBasePath(request)+"/AdminServlet");
			return;
		}else{
			if(isAccountError){
				request.setAttribute("accountMessge", "帐号不存在");
			}
			if(isPasswordError){
				request.setAttribute("passwordMessage", "密码错误");
			}
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}

}
