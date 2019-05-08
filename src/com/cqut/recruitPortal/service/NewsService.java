package com.cqut.recruitPortal.service;

import java.util.List;
import java.util.Map;

import com.cqut.recruitPortal.dao.CommonDAO;

public class NewsService {

	public CommonDAO cd = new CommonDAO();
	
	
	/**
	* @Title: createOperationPermissionHtml 
	* @Description: 得到给定Operator的可以发表新闻的列表 。 
	* @param type
	* @param operatorID
	* @return 
	* @return String
	 */
	public String createOperationPermissionHtml(String type,Long operatorID){
		String sql = "select p.newstype as newstype , nt.`name` as `name` from permission as p LEFT JOIN newstype as nt ON p.newstype = nt.newsTypeID where p.operator=?";
		
		List<Map<String,Object>> pList = cd.executeQuery(sql, new Object[]{operatorID});
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<select name='type' style='width:150px;'>");
		for(Map<String,Object> map:pList){
			if(type!=null && type.equals(map.get("newstype").toString())){
				buffer.append("<option value='"+map.get("newstype")+"' selected=\"selected\">"+map.get("name")+"</option>");				
			}else{
				buffer.append("<option value='"+map.get("newstype")+"'>"+map.get("name")+"</option>");								
			}
		}
		buffer.append("</select>");
		
		return buffer.toString();
	}
}
