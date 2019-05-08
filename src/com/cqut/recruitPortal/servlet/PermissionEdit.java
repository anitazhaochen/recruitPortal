package com.cqut.recruitPortal.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.service.PermissionService;

public class PermissionEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PermissionService service = new PermissionService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//得到需要分配权限的管理员的ID
		String operatorID = request.getParameter("operatorID");
		
		//得到前台 checkbox传递的值       
		String[] types = request.getParameterValues("type");
		
		//System.out.println(operatorID + Arrays.deepToString(types));
		
		//先删除 再保存
		String deleteSql = "delete from permission where operator = ?";
		service.cd.executeUpdate(deleteSql, new Object[]{Long.parseLong(operatorID)});
	
		//types == null表示没有分配任何的权限 ，types ！= null 表示分类权限的 
		if(types!=null){
			String insertSql = "insert into permission(`operator`,`newstype`) values(?,?)";
			for(String type:types){
				service.cd.executeUpdate(insertSql, new Object[]{Long.parseLong(operatorID),Long.parseLong(type)});
			}
		}

		request.getServletContext().getRequestDispatcher("/admin/PermissionServlet").forward(request, response);
		
	}

}
