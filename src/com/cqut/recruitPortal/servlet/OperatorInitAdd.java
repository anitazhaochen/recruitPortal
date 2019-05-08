package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.service.OperatorService;

/**
 * Servlet implementation class OperatorAdd
 */
public class OperatorInitAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	OperatorService service  = new OperatorService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//读取所有的人员类型
		String sql = "select * from operatorType";
		List<Map<String,Object>> listMap = service.cd.executeQuery(sql, new Object[0]);
		
		request.setAttribute("list", listMap);
		request.getServletContext().getRequestDispatcher("/admin/operatorAdd.jsp").forward(request, response);;
		
	}

}
