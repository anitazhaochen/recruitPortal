package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.service.OperatorTypeService;


public class OperatorTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	OperatorTypeService service = new OperatorTypeService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String currentPageStr = request.getParameter("currentPage");
		int currentPage = 1;
		if(currentPageStr!=null && !currentPageStr.equals("")){
			currentPage = Integer.parseInt(currentPageStr);
		}
		
		List<Map<String,Object>> list = service.operatorTypeList(currentPage, 10);
		
		request.setAttribute("list", list);
		int size = service.count();
		System.out.println(size);
		
		String url = "admin/OperatorTypeServlet";
		
		String pageHtml = SysUtil.createPage(size, currentPage, 10, url);
		
		request.setAttribute("pages", pageHtml);
		
		request.getServletContext().getRequestDispatcher("/admin/operatorTypeList.jsp").forward(request, response);
	}
}
