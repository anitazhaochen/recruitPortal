package com.cqut.recruitPortal.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.service.OperatorTypeService;


public class OperatorTypeEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	OperatorTypeService service = new OperatorTypeService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String originalName = request.getParameter("originalName");
		System.out.println(name+";"+originalName);
		
		//判断输入的文本是否改变
		boolean isNotChange = originalName.equals(name); 
		System.out.println("hasChange:"+isNotChange);
		String operatorTypeID = request.getParameter("operatorTypeID");
		String errorMessage = "";
		boolean available = true;
		if(name!=null && name.equals("")){
			errorMessage = "用户类型名称不能为空";
			available = false;
		}
		
		if(available && !isNotChange){
			//判断是否存在
			boolean isExist =  service.validateNameIsExist(name);
			if(isExist){
				errorMessage= "该用户类型名称已经存在";
				available = false;
			}
		}
		
		boolean isOK = false;
		
		if(available && !isNotChange){
			int updateCount = service.updateById(Long.parseLong(operatorTypeID),name);
			if(updateCount==1){
				isOK = true;
			}else{
				errorMessage = "编辑失败";
			}
		}
		
		if(isOK || isNotChange){
			request.getServletContext().getRequestDispatcher("/admin/OperatorTypeServlet").forward(request, response);
		}else{
			request.setAttribute("errorMessage", errorMessage);
			request.getServletContext().getRequestDispatcher("/admin/operatorTypeEdit.jsp").forward(request, response);
		}
	}

}
