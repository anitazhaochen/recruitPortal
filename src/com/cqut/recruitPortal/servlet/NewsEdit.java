package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.entity.Operator;
import com.cqut.recruitPortal.service.NewsService;

/**
 * Servlet implementation class NewsEdit
 */
public class NewsEdit extends HttpServlet {
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
		boolean isOK = true;
		
		String newsID = request.getParameter("newsID");
		
		String title = request.getParameter("title");
		String titleMessage = "";
		if(title==null || title.equals("")){
			isOK = false;
			titleMessage = "标题不能为空";
		}
		
		String content = request.getParameter("content");
		String contentMessage = "";
		if(content==null || content.equals("")){
			isOK = false;
			contentMessage = "内容不能为空";
		}
		
		String type = request.getParameter("type");
		String typeMessage ="";
		if(type==null || type.equals("")){
			typeMessage = "请选择";
		}
		
		Long operatorID = ((Operator)request.getSession().getAttribute("loginOperator")).getOperatorID();
		
		Date publishTime = new Date();
		
		Date deadLineTime = null;
		String deadLine = request.getParameter("deadLine");
		String deadLineMessage = "";
		if(deadLine!=null && !deadLine.equals("")){
			try {
				deadLineTime = SysUtil.praseDate(deadLine);
				if(deadLineTime.getTime()<publishTime.getTime()){
					isOK = false;
					deadLineMessage = "截至时间不能小于当前时间";
				}
			} catch (ParseException e) {
				isOK = false;
				deadLineMessage = "截至时间格式不正确，应为:2014-01-14 22:51:10";
				e.printStackTrace();
			}
		}
		
		String addMessage = "";
		if(isOK){
			
			String updateSql = "update news set `title`=?, `content`=?, `type`=?, `deadLine`=? where newsID=?";
			Object objs[]={title,content,Long.parseLong(type),deadLineTime,Long.parseLong(newsID)};
			
			int updateCount = service.cd.executeUpdate(updateSql, objs);
			if(updateCount!=1){
				addMessage = "编辑失败";
			}else{
				request.getServletContext().getRequestDispatcher("/admin/NewsServlet").forward(request, response);
				return;
			}
		}
		
		request.setAttribute("titleMessage", titleMessage);
		request.setAttribute("contentMessage", contentMessage);
		request.setAttribute("deadLineMessage", deadLineMessage);
		request.setAttribute("typeMessage", typeMessage);
		request.setAttribute("addMessage", addMessage);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("title", title);
		map.put("content", content);
		map.put("deadLine", deadLine);
		map.put("newsID", newsID);
		
		request.setAttribute("news",map);
		//request.setAttribute("title", title);
		String phtml=  service.createOperationPermissionHtml(type, operatorID);
		request.setAttribute("phtml",phtml);
		request.getServletContext().getRequestDispatcher("/admin/newsEdit.jsp").forward(request, response);
	}

}
