package com.cqut.recruitPortal.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.entity.NewsType;
import com.cqut.recruitPortal.service.NewsTypeService;


public class NewsTypeInitEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	NewsTypeService service = new NewsTypeService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String newsTypeID = request.getParameter("newsTypeID");
		NewsType newsType = service.getNewsById(Long.parseLong(newsTypeID));
		System.out.println(newsType.getName());
		request.setAttribute("newsType", newsType);
		request.getServletContext().getRequestDispatcher("/admin/newsTypeEdit.jsp").forward(request, response);
		
	}

}
