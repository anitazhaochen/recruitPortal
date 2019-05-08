package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.service.OperatorTypeService;

public class OperatorTypeAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	OperatorTypeService service = new OperatorTypeService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		
		String errorMessage = "";
		boolean available = true;
		if(name!=null && name.equals("")){
			errorMessage = "用户类型名称不能为空";
			available = false;
			
		}
		
		if(available){
			//判断是否存在
			boolean isExist =  service.validateNameIsExist(name);
			System.err.println(isExist + "？？");
			if(isExist){
				errorMessage= "该用户类型名称已经存在";
				available = false;
			}
		}
		
		boolean isOK = false;
		
		if(available){
			int updateCount = service.add(name);
			System.out.println(updateCount);
			if(updateCount==1){
				isOK = true;
			}else{
				errorMessage = "新增失败";
			}
		}
		
		if(isOK){
			request.getServletContext().getRequestDispatcher("/admin/OperatorTypeServlet").forward(request, response);
		}else{
			request.setAttribute("errorMessage", errorMessage);
			request.getServletContext().getRequestDispatcher("/admin/operatorTypeAdd.jsp").forward(request, response);
		}
		
	}

}
