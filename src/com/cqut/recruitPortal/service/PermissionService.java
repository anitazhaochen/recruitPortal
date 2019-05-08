package com.cqut.recruitPortal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cqut.recruitPortal.dao.CommonDAO;
import com.cqut.recruitPortal.dto.OperatorPermission;
import com.cqut.recruitPortal.entity.Operator;

public class PermissionService {

	public CommonDAO cd = new CommonDAO();
	
	/**
	* @Title: getPermission 
	* @Description: 查询用户，用户权限 
	* @param operatorCondition 查询用户的查询条件
	* @param currentPage 当前页
	* @param limit 每页显示数据量
	* @param permissionCondition 权限的查询条件
	* @return 
	* @return List<OperatorPermission>
	 */
	public List<OperatorPermission> getPermission(String operatorCondition,int currentPage,int limit){
		
		//type= 2 表示管理员
		Long type = 2L; 
		
		String sql = "select `name`,`type`,`operatorID` from operator where type=?";
		
		if(operatorCondition!=null && !operatorCondition.equals("")){
			sql+= " and "+ operatorCondition;
		}
		
		sql += " limit ?,?";
		
		int index = (currentPage-1)*limit;
		
		List<Map<String,Object>> list= cd.executeQuery(sql, new Object[]{type,index,limit});
		
		List<OperatorPermission> result = new ArrayList<OperatorPermission>();
		
		for(Map<String,Object> m:list){
			
			String permissionSql = "select `permissionID`,`name`,`newsType` from permission,newsType where `newsType` = `newsTypeID` and operator=?";
			List<Map<String,Object>> permissionList = cd.executeQuery(permissionSql, 
					new Object[]{Long.parseLong(m.get("operatorID").toString())}
			); 
			
			OperatorPermission op = new OperatorPermission();
			
			Operator o = new Operator();
			o.setName(m.get("name").toString());
			o.setOperatorID(Long.parseLong(m.get("operatorID").toString()));
			
			op.setOperator(o);
			op.setPermissions(permissionList);
			result.add(op);
		}
		
		return result;
	}
	
	//查询数量
	public int getTotal(String operatorCondition){
		//type= 2 表示管理员
		Long type = 2L;
		String sql = "select count(`operatorID`) as count from operator where type=?";
		
		if(operatorCondition!=null && !operatorCondition.equals("")){
			sql+= " and "+ operatorCondition;
		}
		
		List<Map<String,Object>> list = cd.executeQuery(sql, new Object[]{type});
		return Integer.parseInt(list.get(0).get("count").toString());
	}
	
}
