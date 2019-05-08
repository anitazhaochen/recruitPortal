package com.cqut.recruitPortal.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.service.NewsService;

/**
 * Servlet implementation class NewsPublish
 */
public class NewsPublish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NewsService service = new NewsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String newsID = request.getParameter("newsID");
		
		String sql = "update news set status=2 where newsID=?";
		service.cd.executeUpdate(sql, new Object[]{Long.parseLong(newsID)});
		
		request.getServletContext().getRequestDispatcher("/admin/NewsServlet").forward(request, response);
		
	}

}
