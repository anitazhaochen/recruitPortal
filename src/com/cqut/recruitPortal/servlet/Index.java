package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.dto.NewsTypeNews;
import com.cqut.recruitPortal.service.IndexService;

/**
* 类描述：这个类负责处理首页请求，也就是 当在浏览器地址栏输入http://localhost:8080/recruitPortal/ 会跳转到这个Servlet
 */

public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IndexService service = new IndexService();
	
	/*我们让前面的所有的get请求都交给doPost方法来处理*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String actionType =request.getParameter("actionType");
		
		System.out.println("actionType:"+actionType);
		
		if(actionType==null || actionType.equals("")){
			List<NewsTypeNews> list = service.pageInit();
			request.setAttribute("list", list);
			request.getSession().setAttribute("category_list", list);
			request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		//首页上的查询
		else if(actionType.equals("indexQuery")){
			indexQueryHandler(request,response);
		}
		//分类查询
		else if(actionType.equals("newsTypeQuery")){
			newsTypeQueryHander(request,response);
		}
		else if(actionType.equals("detail")){
			detailHander(request,response);
		}else if (actionType.equals("recent")){
			recentHander(request, response);
		}
		
	}

	private void recentHander(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/indexRecent.jsp").forward(request, response);
	}

	private void detailHander(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String newsID = request.getParameter("newsID");



		//更新该新闻的浏览数量
		String updateCountSql = "update news set `count`=`count`+1 where newsID=?";
		service.cd.executeUpdate(updateCountSql, new Object[]{Long.parseLong(newsID)});
		
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
				"n.`status` AS status, "+
				"n.`img` AS img "+
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
			}else{
				news.put("deadLine","无");
			}
			
			//为了在jsp页面上有格式我们需要做一定的处理
			String content = news.get("content").toString();
			content = content.replaceAll(" ", "&nbsp;");
			content = content.replaceAll("\t", "&nbsp;&nbsp;");
			content = content.replaceAll("\r\n", "<br/>");
			news.put("content", content);
			
			request.setAttribute("news", news);

			// 最近浏览
			ArrayList<Map<String, Object>> news_list = (ArrayList<Map<String, Object>>) request.getSession().getAttribute("news_list");
			if (news_list == null) {
				news_list = new ArrayList<Map<String, Object>>();
			}
			news_list.add(0,news);
			request.getSession().setAttribute("news_list", news_list);

			request.getServletContext().getRequestDispatcher("/newsDetail.jsp").forward(request, response);
			
			//设置面包屑导航栏
			String newsType = request.getParameter("newsType");
			if(newsType!=null && !newsType.equals("")){
				String selectTypeName = "select `name` as name from newsType where `newsTypeID`=?";
				String typeName = service.cd.executeQuery(selectTypeName, new Object[]{Long.parseLong(newsType)}).get(0).get("name").toString();
				
				String newsTypeLink = "<a href=\"Index?actionType=newsTypeQuery&newsType="+newsType+"\">"+typeName+"</a>";
				request.setAttribute("newsTypeLink", newsTypeLink);
				
				String titleLink = "<a href=\"Index?actionType=detail&newsID="+news.get("newsID")+"&newsType="+news.get("type")+"\">"+news.get("title")+"</a>";
				request.setAttribute("titleLink", titleLink);
			}else{
				String titleLink = "<a href=\"Index?actionType=detail&newsID="+news.get("newsID")+"\">"+news.get("title")+"</a>";
				request.setAttribute("titleLink", titleLink);
			}
		}


		
	}


	private void newsTypeQueryHander(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//得到查询的关键字
		String title = request.getParameter("title");
		String newsType = request.getParameter("newsType");  
		String currentPageStr = request.getParameter("currentPage");
		int limit = SysConstant.DEFAULT_PAGE_LIMIT;
		int currentPage = 1;
		if(currentPageStr!=null && !currentPageStr.equals("")){
			currentPage = Integer.parseInt(currentPageStr);
		}
		int index = (currentPage-1)*limit;
		
		if(title!=null && !title.equals("")){
			if(request.getMethod().equals("GET")){
				title = new String(title.getBytes("ISO-8859-1"),"UTF-8");
			}
		}
		
		
		String condition = "n.`type`=? and ";
		if(title!=null && !title.equals("")){
			condition +=  "n.`title` like '%"+title+"%' and ";
		}else{
			title="";
		}
		
		String selectTypeName = "select `name` as name from newsType where `newsTypeID`=?";
		String typeName = service.cd.executeQuery(selectTypeName, new Object[]{Long.parseLong(newsType)}).get(0).get("name").toString();
		
		//String newsTypeID = request.getParameter("newsTypeID");
		String querysql = "select n.newsID AS newsID, " +
				"n.`title` AS title, " +
				"n.`publishTime` AS publishTime, " +
				"n.`deadLine` AS deadLine, " +
				"n.`count` AS count, " +
				"n.`img` AS img, " +
				"n.`type` AS type, " +
				"n.`status` AS status "+
				"from news n LEFT JOIN newstype AS nt ON n.type = nt.newsTypeID " +
				"LEFT JOIN operator AS o ON o.operatorID = n.operator " +
				"where "+condition+" (n.deadLine>NOW() or n.deadLine IS NULL) and n.`status` = 2 " +
				"ORDER BY n.publishTime DESC LIMIT ?,?";
		List<Map<String,Object>> list = service.cd.executeQuery(querysql, new Object[]{Long.parseLong(newsType),index,limit});
		
		String querySize = "select count(n.`newsID`) AS size " +
				"from news n LEFT JOIN newstype AS nt ON n.type = nt.newsTypeID " +
				"LEFT JOIN operator AS o ON o.operatorID = n.operator " +
				"where "+condition+" (n.deadLine>NOW() or n.deadLine IS NULL) and n.`status` = 2";
		int total = Integer.parseInt(service.cd.executeQuery(querySize, new Object[]{Long.parseLong(newsType)}).get(0).get("size").toString());
		
		String url = "Index?title="+title+"&actionType=newsTypeQuery&newsType="+newsType;
		String paginationHtml = SysUtil.createPage(total, currentPage, limit, url);
		
		request.setAttribute("paginationHtml", paginationHtml);
		request.setAttribute("list", list);
		request.setAttribute("title", title);
		request.setAttribute("typeName", typeName);
		request.setAttribute("newsType", newsType);
		request.getServletContext().getRequestDispatcher("/indexNewsTypeQuery.jsp").forward(request, response);
	}


	public void indexQueryHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到查询的关键字
		String title = request.getParameter("title");
		
		String currentPageStr = request.getParameter("currentPage");
		int limit = SysConstant.DEFAULT_PAGE_LIMIT;
		int currentPage = 1;
		if(currentPageStr!=null && !currentPageStr.equals("")){
			currentPage = Integer.parseInt(currentPageStr);
		}
		int index = (currentPage-1)*limit;
		
		String condition = "";
		if(title!=null && !title.equals("")){
			if(request.getMethod().equals("GET")){
				title = new String(title.getBytes("ISO-8859-1"),"UTF-8");
			}
		}
		
		
		if(title!=null && !title.equals("")){
			//title= new String(title.getBytes("ISO-8859-1"), "UTF-8");
			condition =  " n.`title` like '%"+title+"%' and ";
		}else{
			title = "";
		}
		
		//String newsTypeID = request.getParameter("newsTypeID");
		String querysql = "select n.newsID AS newsID, " +
				"n.`title` AS title, " +
				"n.`publishTime` AS publishTime, " +
				"n.`deadLine` AS deadLine, " +
				"n.`count` AS count, " +
				"n.`operator` AS operator, " +
				"n.`img` AS img, " +
				"n.`type` AS type, " +
				"nt.`name` AS typeName, " +
				"o.`name` AS operatorName, " +
				"n.`status` AS status "+
				"from news n LEFT JOIN newstype AS nt ON n.type = nt.newsTypeID " +
				"LEFT JOIN operator AS o ON o.operatorID = n.operator " +
				"where "+condition+" (n.deadLine>NOW() or n.deadLine IS NULL) and n.`status` = 2 " +
				"ORDER BY n.publishTime DESC LIMIT ?,?";
		List<Map<String,Object>> list = service.cd.executeQuery(querysql, new Object[]{index,limit});
		
		String querySize = "select count(n.`newsID`) AS size " +
				"from news n LEFT JOIN newstype AS nt ON n.type = nt.newsTypeID " +
				"LEFT JOIN operator AS o ON o.operatorID = n.operator " +
				"where "+condition+" (n.deadLine>NOW() or n.deadLine IS NULL) and n.`status` = 2";
		
		int total = Integer.parseInt(service.cd.executeQuery(querySize, new Object[0]).get(0).get("size").toString());
		//由于直接通过get方式传递会出现乱码，所以我们在分页的URL里面添加一个ST的参数，来表示表示分页查询，取查询参数的时候就从Session里面取
		String url = "Index?actionType=indexQuery&title="+title;
		String paginationHtml = SysUtil.createPage(total, currentPage, limit, url);
		request.setAttribute("paginationHtml", paginationHtml);
		request.setAttribute("list", list);
		request.setAttribute("title", title);
		request.getServletContext().getRequestDispatcher("/indexQuery.jsp").forward(request, response);
		
	}

}
