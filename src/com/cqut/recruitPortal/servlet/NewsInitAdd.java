package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.entity.Operator;
import com.cqut.recruitPortal.service.NewsService;

/**
 * Servlet implementation class NewsInitAdd
 */
public class NewsInitAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NewsService service  = new NewsService(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//加载当前登录用户可以发布的新闻类型
		
		Long operatorID = ((Operator)request.getSession().getAttribute("loginOperator")).getOperatorID();
		
		String phtml=  service.createOperationPermissionHtml("", operatorID);
		request.setAttribute("phtml",phtml);

		long time = new Date().getTime();
		request.setAttribute("timestamp", time);
		

		request.getServletContext().getRequestDispatcher("/admin/newsAdd.jsp").forward(request, response);
		
		
		
	}

}
