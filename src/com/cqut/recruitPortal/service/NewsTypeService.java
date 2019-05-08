package com.cqut.recruitPortal.service;

import java.util.List;
import java.util.Map;

import com.cqut.recruitPortal.dao.CommonDAO;
import com.cqut.recruitPortal.entity.NewsType;

public class NewsTypeService {
	
	public CommonDAO commonDAO = new CommonDAO();
	
	//分页得到数据条数
	public List<Map<String,Object>> newsTypeList(int page,int limit){
		String sql = "select * from newsType limit ?,?";
		List<Map<String,Object>> list = commonDAO.executeQuery(sql, new Object[]{(page-1)*limit,limit});
		return list;
	}
	
	//得到 newsType 表中的中的数据的条数
	public int count(){
		String sql = "select count(*) as size from newsType";
		List<Map<String,Object>> list = commonDAO.executeQuery(sql, new Object[0]);
		//System.out.println(Arrays.deepToString(list.toArray()));
		return Integer.parseInt(list.get(0).get("size").toString());
	}
	
	//新增一个newsType
	public int add(String name){
		String sql = "insert into newsType(name) values(?)";
		int updateCount = commonDAO.executeUpdate(sql, new Object[]{name});
		return updateCount; 
	}
	
	public int updateById(Long newsTypeID,String name){
		String sql = "update newsType set name=? where newsTypeID=?";
		int updateCount = commonDAO.executeUpdate(sql, new Object[]{name,newsTypeID});
		return updateCount;
	}
	
	
	//判断给定的用户类型名称是否在数据库中存在
	public boolean validateNameIsExist(String name){
		String sql = "select count(*) as size from newsType where name=?";
		List<Map<String,Object>> list = commonDAO.executeQuery(sql, new Object[]{name});
		int size = Integer.parseInt(list.get(0).get("size").toString());
		return size==0 ? false : true;
	}
	
	public NewsType getNewsById(Long newsTypeID){
		NewsType newsType = new NewsType();
		String sql = "select * from newsType where newsTypeID=?";
		List<Map<String,Object>> resultlist = commonDAO.executeQuery(sql, new Object[]{newsTypeID});
		if(resultlist.size()>0){
			Map<String,Object> map = resultlist.get(0);
			newsType.setNewsTypeID(Long.parseLong(map.get("newsTypeID").toString()));
			newsType.setName(map.get("name").toString());
			return newsType;
		}else{
			return null;
		}
	}
	
	public int deleteByID(Long newsTypeID){
		String sql = "delete from newsType where newsTypeID=?";
		int updateCount = commonDAO.executeUpdate(sql, new Object[]{newsTypeID});
		return updateCount;
	}
}
