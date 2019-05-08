package com.cqut.recruitPortal.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
* 项目名称：recruitPortal
* 类名称：ListMapHander
* 类描述： 实现<code>ResultSetHander</code>接口,用于将结果集每一行里面的的放入Map<String,Object>中，然后在将这个Map存入List中。
* @version 1.0
* Copyright (c) 2014 ChongQing University Of Technology
 */
public class ListMapHander implements ResultSetHander {

	@Override
	public Object doHander(ResultSet rs) throws SQLException {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(); 
		ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();
        
        HashMap<String,Object> m = null;
        
        //System.out.println("list 结果：");
        //遍历结果集
        while(rs.next()){
        	m = new HashMap<String,Object>();
        	//将结果集中的数据保存到HashMap中
        	for (int i = 1; i <= cols; i++) {
        		//System.out.println("Label:"+rsmd.getColumnLabel(i));
        		//System.out.println(rsmd.getColumnName(i)+","+rs.getObject(i));
				m.put(rsmd.getColumnLabel(i), rs.getObject(i));
			}
        	resultList.add(m);
        }
		return resultList;
	}

}
