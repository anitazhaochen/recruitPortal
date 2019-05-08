package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.dto.OperatorPermission;
import com.cqut.recruitPortal.service.PermissionService;


public class PermissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PermissionService service = new PermissionService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");  
		String currentPageStr = request.getParameter("currentPage");
		int limit = SysConstant.DEFAULT_PAGE_LIMIT;
		int currentPage = 1;
		if(currentPageStr!=null && !currentPageStr.equals("")){
			currentPage = Integer.parseInt(currentPageStr);
		}
		//int index = (currentPage-1)*limit;
		
		String operatorCondition ="";
		if(name!=null && !name.equals("")){
			operatorCondition = "name like '%"+name+"%'";
		}
		
		List<OperatorPermission> list = service.getPermission(operatorCondition, currentPage, limit);
		request.setAttribute("list", list);
		
		int total=  service.getTotal(operatorCondition);
		String url = "admin/PermissionServlet";
		String paginationHtml = SysUtil.createPage(total, currentPage, limit, url);
		request.setAttribute("paginationHtml", paginationHtml);
		request.setAttribute("name", name);
		request.getServletContext().getRequestDispatcher("/admin/permissionList.jsp").forward(request, response);
	}

}
