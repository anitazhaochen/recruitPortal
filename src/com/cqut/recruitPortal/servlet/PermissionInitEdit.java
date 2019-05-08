package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.cqut.recruitPortal.service.PermissionService;

/**
 * Servlet implementation class PermissionInitEdit
 */
public class PermissionInitEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PermissionService service = new PermissionService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operatorID = request.getParameter("operatorID");
		
		String sql = "select * from operator where operatorID=?";
		
		List<Map<String,Object>> operatorList = service.cd.executeQuery(sql, new Object[]{Long.parseLong(operatorID)});
		
		request.setAttribute("operator", operatorList.get(0));
		
		//查询该用户已经分配的权限
		String allNewsType = "select `newsTypeID` as newsTypeID, `name` as name from newsType";
		List<Map<String,Object>> allNewsTypeList = service.cd.executeQuery(allNewsType, new Object[0]);
		System.out.println("all:"+JSON.toJSONString(allNewsTypeList));
		
		String operatorPermissionsSql = "select `permissionID` as permissionID ,`newsType` as newsType  from permission,newsType where newsType = newsTypeID and `operator`=?";
		List<Map<String,Object>> operatorPermissions = service.cd.executeQuery(operatorPermissionsSql,new Object[]{Long.parseLong(operatorID)});
		System.out.println("op:"+JSON.toJSONString(operatorPermissions));
		
		//生成一组checkbox
		StringBuffer buffer = new StringBuffer();
		int index = 0;
		for(Map<String,Object> m:allNewsTypeList){
			boolean hasFind = false;
			String newsTypeID = m.get("newsTypeID").toString();
			for(int i=0;i<operatorPermissions.size();i++){
				if(operatorPermissions.get(i).get("newsType").toString().equals(newsTypeID)){
					hasFind = true;  
				}
			}
			
			if(hasFind){
				buffer.append("<input type='checkbox' name='type' value='"+newsTypeID+"' id='cb"+index+"' checked='checked'/><label for='cb"+(index++)+"'>"+m.get("name")+"</label><br/>");
			}else{
				buffer.append("<input type='checkbox' name='type' value='"+newsTypeID+"' id='cb"+index+"'/><label for='cb"+(index++)+"'>"+m.get("name")+"</label><br/>");
			}
		}
		request.setAttribute("allPermission", buffer.toString());
		request.getServletContext().getRequestDispatcher("/admin/permissionEdit.jsp").forward(request, response);
		
	}

}
