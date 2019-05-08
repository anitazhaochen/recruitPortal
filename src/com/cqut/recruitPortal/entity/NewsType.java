package com.cqut.recruitPortal.entity;

import java.io.*;

public class NewsType implements Serializable {

	private static final long serialVersionUID = 1389357906733L;

	private java.lang.Long newsTypeID;

	private java.lang.String name;

	public NewsType(){}

	public java.lang.Long getNewsTypeID() {
		return newsTypeID;
	}

	public void setNewsTypeID(java.lang.Long newsTypeID) {
		this.newsTypeID = newsTypeID;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

}