package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.entity.Operator;
import com.cqut.recruitPortal.service.NewsService;


public class NewsInitEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NewsService service = new NewsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String newsID = request.getParameter("newsID");
		
		//
		String sql = "select * from news where newsID=?";
		List<Map<String,Object>> newsList = service.cd.executeQuery(sql, new Object[]{Long.parseLong(newsID)});
		
		if(newsList.size()>0){
			Map<String,Object> map = newsList.get(0);
			
			if(map.get("deadLine")!=null){
				String deadLine = SysUtil.formatDate((Date)map.get("deadLine"));
				map.put("deadLine", deadLine);
			}
			
			//查询自己的可以发布的
			String type = map.get("type").toString();
			
			Long operatorID = ((Operator)request.getSession().getAttribute("loginOperator")).getOperatorID();
			
			String phtml = service.createOperationPermissionHtml(type, operatorID);
			
			request.setAttribute("phtml", phtml);
			request.setAttribute("news", map);
			
		}
		
		request.getServletContext().getRequestDispatcher("/admin/newsEdit.jsp").forward(request, response);
		
	}

}
