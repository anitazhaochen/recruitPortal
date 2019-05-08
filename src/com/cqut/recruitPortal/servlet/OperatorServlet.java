package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.service.OperatorService;


public class OperatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	OperatorService service  = new OperatorService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("this"); 
		String name = request.getParameter("name");  
		String currentPageStr = request.getParameter("currentPage");
		int limit = SysConstant.DEFAULT_PAGE_LIMIT;
		int currentPage = 1;
		if(currentPageStr!=null && !currentPageStr.equals("")){
			currentPage = Integer.parseInt(currentPageStr);
		}
		int index = (currentPage-1)*limit;
		//查询数据的SQL
		String sql = "";
		
		int total = 0;
		//查询总的数据
		String queryTotalSql = ""; 
		//redirect 是由OperatorAdd Servlet在添加人员成功后传递过来的一个参数
		Object redirect = request.getAttribute("redirect");
		
		if(name!=null && !name.equals("")){
			if(request.getMethod().equals("GET")){
				name = new String(name.getBytes("ISO-8859-1"),"UTF-8"); 
			}
		}
		
		if(name==null || name.equals("") || redirect!=null){
			sql = "Select o.`name` as name, o.account as account,o.gender as gender,o.operatorID as operatorID, o.type as type, ot.`name` as typeName,o.email as email from operator as o LEFT JOIN operatortype as ot on o.type = ot.operatorTypeID limit ?,?";
			queryTotalSql = "select count(*) as count from operator";
			
			String count = service.cd.executeQuery(queryTotalSql, new Object[0]).get(0).get("count").toString();
			total = Integer.parseInt(count);
			
			List<Map<String,Object>> listMap =  service.cd.executeQuery(sql, new Object[]{index,limit});
			request.setAttribute("list", listMap);
			
			System.out.println(Arrays.deepToString(listMap.toArray()));
			
			String url = "admin/OperatorServlet";
			String paginationHtml = SysUtil.createPage(total, currentPage, limit, url);
			request.setAttribute("paginationHtml", paginationHtml);
			
		}else{
			//sql 的模糊查询
			queryTotalSql = "select count(*) as count from operator where name like '%"+name+"%'";
			String count = service.cd.executeQuery(queryTotalSql, new Object[0]).get(0).get("count").toString();
			total = Integer.parseInt(count);
			
			sql = "Select o.`name` as name, o.account as account,o.gender as gender,o.operatorID as operatorID, o.type as type, ot.`name` as typeName,o.email as email from operator o LEFT JOIN operatortype as ot on o.type = ot.operatorTypeID where o.`name` like '%"+name+"%' limit ?,?";
			List<Map<String,Object>> listMap = service.cd.executeQuery(sql, new Object[]{index,limit});
			request.setAttribute("list", listMap);
			
			String url = "admin/OperatorServlet?name="+name;
			String paginationHtml = SysUtil.createPage(total, currentPage, limit, url);
			request.setAttribute("paginationHtml", paginationHtml);
			
		}
		
		request.setAttribute("name", name);
		
		request.getServletContext().getRequestDispatcher("/admin/operatorList.jsp").forward(request, response);
	}

}
