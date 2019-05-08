package com.cqut.recruitPortal.dto;

import java.util.List;
import java.util.Map;

import com.cqut.recruitPortal.entity.NewsType;

public class NewsTypeNews {

	private NewsType newsType;
	
	private List<Map<String,Object>> news;

	public NewsType getNewsType() {
		return newsType;
	}

	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	public List<Map<String, Object>> getNews() {
		return news;
	}

	public void setNews(List<Map<String, Object>> news) {
		this.news = news;
	}
	
	
}
