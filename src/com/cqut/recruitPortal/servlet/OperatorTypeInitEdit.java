package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.entity.OperatorType;
import com.cqut.recruitPortal.service.OperatorTypeService;


public class OperatorTypeInitEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	OperatorTypeService service = new OperatorTypeService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operatorTypeID = request.getParameter("operatorTypeID");
		OperatorType operatorType = service.getOperatorById(Long.parseLong(operatorTypeID));
		request.setAttribute("operatorType", operatorType);
		request.getServletContext().getRequestDispatcher("/admin/operatorTypeEdit.jsp").forward(request, response);
		
	}

}
