package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.service.NewsTypeService;

/**
 * Servlet implementation class NewsTypeDelete
 */
public class NewsTypeDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NewsTypeService service = new NewsTypeService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String newsTypeID = request.getParameter("newsTypeID");
		
		int updateCount = service.deleteByID(Long.parseLong(newsTypeID));
		
		if(updateCount==1){
			request.getServletContext().getRequestDispatcher("/admin/NewsTypeServlet").forward(request, response);			
		}else{
			PrintWriter out= response.getWriter();
			out.write("删除失败");
			out.flush();
			out.close();
		}
	}

}
