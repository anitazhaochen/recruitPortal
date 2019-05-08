package com.cqut.recruitPortal.entity;

import java.io.*;

public class News implements Serializable {

	private static final long serialVersionUID = 1389357906737L;

	private java.lang.Long newsID;

	private java.lang.String title;

	private java.lang.String content;

	private java.util.Date publishTime;

	private java.util.Date deadLine;

	private java.lang.Integer count;

	private java.lang.Long operator;

	private java.lang.Long type;
	
	//新闻状态 1.新建 2.已发布 3.取消发布
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public News(){}

	public java.lang.Long getNewsID() {
		return newsID;
	}

	public void setNewsID(java.lang.Long newsID) {
		this.newsID = newsID;
	}

	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public java.util.Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(java.util.Date publishTime) {
		this.publishTime = publishTime;
	}

	public java.util.Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(java.util.Date deadLine) {
		this.deadLine = deadLine;
	}

	public java.lang.Integer getCount() {
		return count;
	}

	public void setCount(java.lang.Integer count) {
		this.count = count;
	}

	public java.lang.Long getOperator() {
		return operator;
	}

	public void setOperator(java.lang.Long operator) {
		this.operator = operator;
	}

	public java.lang.Long getType() {
		return type;
	}

	public void setType(java.lang.Long type) {
		this.type = type;
	}

}