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
 * Servlet implementation class OperatorDetail
 */
public class OperatorDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	OperatorService service = new OperatorService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operatorID = request.getParameter("operatorID");
		String sql = "Select o.`name` as name, o.account as account,o.gender as gender,o.operatorID as operatorID, o.type as type, ot.`name` as typeName,o.email as email,o.photo as photo from operator as o LEFT JOIN operatortype as ot on o.type = ot.operatorTypeID where o.operatorID=?";
		
		List<Map<String,Object>> listMap = service.cd.executeQuery(sql, new Object[]{Long.parseLong(operatorID)});
		
		if(listMap.size()>0){
			request.setAttribute("operator", listMap.get(0));
			request.getServletContext().getRequestDispatcher("/admin/operatorDetail.jsp").forward(request, response);
		}else{
			try {
				throw new Exception("提取用户信息失败");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
