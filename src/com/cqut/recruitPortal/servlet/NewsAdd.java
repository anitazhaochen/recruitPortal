package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.entity.Operator;
import com.cqut.recruitPortal.service.NewsService;

/**
 * Servlet implementation class NewsAdd
 */
public class NewsAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NewsService service = new NewsService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		boolean isOK = true;
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
		}else{
			
		}
		
		String deadLine = request.getParameter("deadLine");
		String deadLineMessage = "";
		
		String type = request.getParameter("type");
		String typeMessage ="";
		if(type==null || type.equals("")){
			typeMessage = "请选择";
		}
		
		Long operatorID = ((Operator)request.getSession().getAttribute("loginOperator")).getOperatorID();
		
		Date publishTime = new Date();
		
		Date deadLineTime = null;
		
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
			String sql = "insert into news(`title`,`content`,`type`,`publishTime`,`deadLine`,`count`,`operator`,`status`) values(?,?,?,?,?,?,?,?)";
			Object objs[]={title,content,Long.parseLong(type),publishTime,deadLineTime,0,operatorID,1};
			int updateCount = service.cd.executeUpdate(sql, objs);
			if(updateCount!=1){
				addMessage = "新增失败";
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
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("deadLine", deadLine);
		//request.setAttribute("title", title);
		String phtml=  service.createOperationPermissionHtml(type, operatorID);
		request.setAttribute("phtml",phtml);
		request.getServletContext().getRequestDispatcher("/admin/newsAdd.jsp").forward(request, response);
		
		
	}

}
