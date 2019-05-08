package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.service.NewsService;


public class NewsDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NewsService service = new NewsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String newsID = request.getParameter("newsID");
		//查询给定ID的 新闻
		String sql = "select n.newsID AS newsID, " +
				"n.title AS title, " +
				"n.publishTime AS publishTime, " +
				"n.deadLine AS deadLine, " +
				"n.count AS count, " +
				"n.operator AS operator, " +
				"n.type AS type, " +
				"nt.`name` AS typeName, " +
				"o.`name` AS operatorName, " +
				"n.`content` AS content, " +
				"n.`status` AS status "+
				"from news n LEFT JOIN newstype AS nt ON n.type = nt.newsTypeID " +
				"LEFT JOIN operator AS o ON o.operatorID = n.operator where newsID=?";  
		List<Map<String,Object>> listMap = service.cd.executeQuery(sql, new Object[]{Long.parseLong(newsID)});  
		
		if(listMap.size()==1){
			Map<String,Object> news = listMap.get(0);
			if(news.get("publishTime")!=null){
				news.put("publishTime", SysUtil.formatDate((Date)news.get("publishTime")));				
			}
			if(news.get("deadLine")!=null){
				news.put("deadLine", SysUtil.formatDate((Date)news.get("deadLine")));
			}
			
			//为了在jsp页面上有格式我们需要做一定的处理
			String content = news.get("content").toString();
			content = content.replaceAll(" ", "&nbsp;");
			content = content.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
			content = content.replaceAll("\r\n", "<br/>");
			news.put("content", content);
			
			request.setAttribute("news", news);
		}
		
		request.getServletContext().getRequestDispatcher("/admin/newsDetail.jsp").forward(request, response);
		
	}

}
