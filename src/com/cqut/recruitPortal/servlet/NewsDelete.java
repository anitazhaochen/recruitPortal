package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.service.NewsService;

/**
 * Servlet implementation class NewsDelete
 */
public class NewsDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NewsService service = new NewsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newsID = request.getParameter("newsID");
		
		//删除
		String sql = "delete from news where newsID=?";
		service.cd.executeUpdate(sql, new Object[]{Long.parseLong(newsID)});
		
		request.getServletContext().getRequestDispatcher("/admin/NewsServlet").forward(request, response);
		
	}

}
