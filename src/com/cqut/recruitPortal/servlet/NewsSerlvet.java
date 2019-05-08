package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.service.NewsService;

/**
 * Servlet implementation class NewsSerlvet
 */
public class NewsSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	NewsService service = new NewsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("querytitle");
		String titleSql = "";
		if(title!=null && !title.equals("")){
			
			if(request.getMethod().equals("GET")){
				title = new String(title.getBytes("ISO-8859-1"),"UTF-8");
			}
			
			titleSql = "where title like '%"+title+"%'";
		}else{
			title = "";
		}
		
		String currentPageStr = request.getParameter("currentPage");
		int limit = SysConstant.DEFAULT_PAGE_LIMIT;
		int currentPage = 1;
		if(currentPageStr!=null && !currentPageStr.equals("")){
			currentPage = Integer.parseInt(currentPageStr);
			if(currentPage<=0){
				currentPage = 1;
			}
		}
		
		int index = (currentPage-1)*limit;
		
		//查询新闻 ，按照时间的逆序排列
		String sql = "select n.newsID AS newsID, " +
				"n.title AS title, " +
				"n.publishTime AS publishTime, " +
				"n.deadLine AS deadLine, " +
				"n.count AS count, " +
				"n.operator AS operator, " +
				"n.type AS type, " +
				"nt.`name` AS typeName, " +
				"o.`name` AS operatorName, " +
				"n.`status` AS status "+
				"from news n LEFT JOIN newstype AS nt ON n.type = nt.newsTypeID " +
				"LEFT JOIN operator AS o ON o.operatorID = n.operator "+titleSql+" ORDER BY n.publishTime DESC limit ?,?";  
		
		List<Map<String,Object>> news = service.cd.executeQuery(sql, new Object[]{index,limit});
		List<Map<String,Object>> newsFomated = new ArrayList<Map<String,Object>>();
		
		//循环遍历 把listm 里面的数据按照一定的格式装配到 list 里面方便传递到页面
		int size = news.size();
		for(int i=0;i<size;i++){
			Map<String,Object> map = news.get(i);
			Set<String> set = map.keySet();
			Map<String,Object> newmap = new HashMap<String,Object>(); 
			for(String key:set){
				if(key.equals("publishTime") || key.equals("deadLine")){
					if(map.get(key)!=null){
						//取时间的 yyyy-MM-dd 部分
						newmap.put(key, SysUtil.formatDate((Date)map.get(key)).substring(0, 10));
					}
				}
				//设置状态和操作列信息
				else if(key.equals("status")){
					String newsID = map.get("newsID").toString();
					if(map.get(key).toString().equals("1")){
						newmap.put(key, "<font color='blue'>新建</font>");
						newmap.put("operate", getDetailUrl(newsID) +" | " +getEditlUrl(newsID)+" | "+ gePublicUrl(newsID)+" | "+ getdeleteURL(newsID));
					}else if(map.get(key).toString().equals("2")){
						newmap.put(key, "<font color='green'>已发布</font");
						//newmap.put("operate", detailUrl+newsID +" | " +editUrl+newsID+" | "+ canclePublishURL+newsID+" | "+ deleteURL+newsID);
						newmap.put("operate", getDetailUrl(newsID) +" | " +getEditlUrl(newsID)+" | "+ getCanclePublishUrl(newsID)+" | "+ getdeleteURL(newsID));
					}else if(map.get(key).toString().equals("3")){
						newmap.put(key, "<font color='BlueViolet'>已取消发布</font>");
						newmap.put("operate", getDetailUrl(newsID) +" | " +getEditlUrl(newsID)+" | "+ gePublicUrl(newsID)+" | "+ getdeleteURL(newsID));
					}else{
						newmap.put(key, "<font color='yellow'>其他</font>");
						newmap.put("operate", getDetailUrl(newsID) +" | " +getEditlUrl(newsID)+" | "+ getdeleteURL(newsID));
					}
					
					if(map.get("deadLine")!=null && !map.get(key).toString().equals("3")){
						Long deadLine = ((Date)map.get("deadLine")).getTime();
						Long now = new Date().getTime(); 
						if(now>deadLine){
							newmap.put(key, "<font color='red'>已截至</font>");
							newmap.put("operate", getDetailUrl(newsID) +" | " +getEditlUrl(newsID)+" | "+ getdeleteURL(newsID));
						}
					}
					
				}else{
					newmap.put(key, map.get(key));
				}
			}
			newsFomated.add(newmap);
		}
		
		
		//创建分页
		String countSql = "select count(`newsID`) as `count` from news "+titleSql;
		int count = Integer.parseInt(service.cd.executeQuery(countSql, new Object[0]).get(0).get("count").toString()); 
		
		String url = "admin/NewsServlet?querytitle="+title;
		String paginationHtml = SysUtil.createPage(count, currentPage, limit, url);
		request.setAttribute("paginationHtml", paginationHtml);
		request.setAttribute("querytitle", title);
		request.setAttribute("list", newsFomated);
		request.getServletContext().getRequestDispatcher("/admin/newsList.jsp").forward(request, response);
	}
	
	//详细
	String detailUrl = "admin/NewsDetail?newsID=";
	//编辑
	String editUrl = "admin/NewsInitEdit?newsID=";
	//发布
	String publishUrl = "admin/NewsPublish?newsID=";
	//取消发布
	String canclePublishURL = "admin/NewsCanclePublish?newsID=";
	//删除
	String deleteURL = "admin/NewsDelete?newsID=";
	private final String getDetailUrl(String newsID){
		return "<a href='"+detailUrl+newsID+"'>详细</a>";
	}
	
	private final String getEditlUrl(String newsID){
		return "<a href='"+editUrl+newsID+"'>编辑</a>";
	}
	
	private final String gePublicUrl(String newsID){
		return "<a href='"+publishUrl+newsID+"'>发布</a>";
	}
	
	private final String getCanclePublishUrl(String newsID){
		return "<a href='"+canclePublishURL+newsID+"'>取消发布</a>";
	}
	
	private final String getdeleteURL(String newsID){
		return "<a href='"+deleteURL+newsID+"'>删除</a>";
	}
	

}
