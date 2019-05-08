package com.cqut.recruitPortal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cqut.recruitPortal.dao.CommonDAO;
import com.cqut.recruitPortal.dto.NewsTypeNews;
import com.cqut.recruitPortal.entity.NewsType;

/**
* 项目名称：recruitPortal
* 类名称：IndexService
* 修改备注：处理数据查询   
* @version 1.0
* Copyright (c) 2014 ChongQing University Of Technology
 */
public class IndexService {

	public CommonDAO cd = new CommonDAO();
	
	public List<NewsTypeNews> pageInit(){
		
		List<NewsTypeNews> list = new ArrayList<NewsTypeNews>();
		
		//查询所有的新闻类型
		String allNewsTypeSql = "select `newsTypeID` as newsTypeID, `name` as name from newsType";
		List<Map<String,Object>> newsTypeList = cd.executeQuery(allNewsTypeSql, new Object[0]);
		
		String querysql = "select n.newsID AS newsID, " +
				"n.`title` AS title, " +
				"n.`publishTime` AS publishTime, " +
				"n.`deadLine` AS deadLine, " +
				"n.`count` AS count, " +
				"n.`operator` AS operator, " +
				"n.`type` AS type, " +
				"nt.`name` AS typeName, " +
				"o.`name` AS operatorName, " +
				"n.`status` AS status "+
				"from news n LEFT JOIN newstype AS nt ON n.type = nt.newsTypeID " +
				"LEFT JOIN operator AS o ON o.operatorID = n.operator " +
				"where n.type = ? and (n.deadLine>NOW() or n.deadLine IS NULL) and n.`status` = 2 ORDER BY n.publishTime DESC LIMIT ?,?";
		
		//查询时间逆序、按照不同类型查询已经发布的新闻 type=2 、截至时间为空或者截至日期大于当前时间 表示已经发布
		
		int newsTypeListSize=  newsTypeList.size();
		for(int i = 0;i<newsTypeListSize;i++){
			NewsTypeNews newsTypeNews = new NewsTypeNews();
			
			Map<String,Object> tempMap = newsTypeList.get(i);
			NewsType newsType = new NewsType();
			newsType.setName(tempMap.get("name").toString());
			newsType.setNewsTypeID(Long.parseLong(tempMap.get("newsTypeID").toString()));
			List<Map<String,Object>> news = cd.executeQuery(querysql, new Object[]{newsType.getNewsTypeID(),0,10});
			//data.put(newsType, listMap);
			
			newsTypeNews.setNewsType(newsType);
			newsTypeNews.setNews(news);
			list.add(newsTypeNews);
		}
		return list;
	}
	
}
