package com.cqut.recruitPortal.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.service.OperatorService;


public class OperatorDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    OperatorService service = new OperatorService();
	
    public OperatorDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operatorID = request.getParameter("operatorID");
		
		//删除
		String sql = "delete from operator where operatorID=?";
		
		service.cd.executeUpdate(sql, new Object[]{Long.parseLong(operatorID)});
		
		request.getServletContext().getRequestDispatcher("/admin/OperatorServlet").forward(request, response);
	}

}
