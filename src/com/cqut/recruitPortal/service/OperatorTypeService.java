package com.cqut.recruitPortal.service;

import java.util.List;
import java.util.Map;

import com.cqut.recruitPortal.dao.CommonDAO;
import com.cqut.recruitPortal.entity.OperatorType;

public class OperatorTypeService {

	public CommonDAO commonDAO = new CommonDAO();
	
	//分页得到数据条数
	public List<Map<String,Object>> operatorTypeList(int page,int limit){
		String sql = "select * from operatorType limit ?,?";
		List<Map<String,Object>> list = commonDAO.executeQuery(sql, new Object[]{(page-1)*limit,limit});
		return list;
	}
	
	//得到 operatorType 表中的中的数据的条数
	public int count(){
		String sql = "select count(*) as size from operatorType";
		List<Map<String,Object>> list = commonDAO.executeQuery(sql, new Object[0]);
		//System.out.println(Arrays.deepToString(list.toArray()));
		return Integer.parseInt(list.get(0).get("size").toString());
	}
	
	//新增一个operatorType
	public int add(String name){
		String sql = "insert into operatorType(name) values(?)";
		int updateCount = commonDAO.executeUpdate(sql, new Object[]{name});
		return updateCount; 
	}
	
	public int updateById(Long operatorTypeID,String name){
		String sql = "update operatorType set name=? where operatorTypeID=?";
		int updateCount = commonDAO.executeUpdate(sql, new Object[]{name,operatorTypeID});
		return updateCount;
	}
	
	
	//判断给定的用户类型名称是否在数据库中存在
	public boolean validateNameIsExist(String name){
		String sql = "select count(*) as size from operatorType where name=?";
		List<Map<String,Object>> list = commonDAO.executeQuery(sql, new Object[]{name});
		int size = Integer.parseInt(list.get(0).get("size").toString());
		return size==0 ? false : true;
	}
	
	public OperatorType getOperatorById(Long operatorTypeID){
		OperatorType operatorType = new OperatorType();
		String sql = "select * from operatorType where operatorTypeID=?";
		List<Map<String,Object>> resultlist = commonDAO.executeQuery(sql, new Object[]{operatorTypeID});
		if(resultlist.size()>0){
			Map<String,Object> map = resultlist.get(0);
			operatorType.setOperatorTypeID(Long.parseLong(map.get("operatorTypeID").toString()));
			operatorType.setName(map.get("name").toString());
			return operatorType;
		}else{
			return null;
		}
	}
	
	public int deleteByID(Long operatorTypeID){
		String sql = "delete from operatorType where operatorTypeID=?";
		int updateCount = commonDAO.executeUpdate(sql, new Object[]{operatorTypeID});
		return updateCount;
	}
	
}
