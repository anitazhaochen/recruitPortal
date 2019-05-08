package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.service.NewsService;

/**
 * Servlet implementation class NewsCanclePublish
 */
public class NewsCanclePublish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NewsService service = new NewsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String newsID = request.getParameter("newsID");
		
		//更新状态为3
		String sql = "update news set status=3 where newsID=?";
		service.cd.executeUpdate(sql, new Object[]{Long.parseLong(newsID)});
		
		request.getServletContext().getRequestDispatcher("/admin/NewsServlet").forward(request, response);
		
	}

}
