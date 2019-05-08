package com.cqut.recruitPortal.entity;

import java.io.*;

public class Permission implements Serializable {

	private static final long serialVersionUID = 1389357906741L;

	private java.lang.Long permissionID;

	private java.lang.Long operator;

	private java.lang.Long newsType;

	public Permission(){}

	public java.lang.Long getPermissionID() {
		return permissionID;
	}

	public void setPermissionID(java.lang.Long permissionID) {
		this.permissionID = permissionID;
	}

	public java.lang.Long getOperator() {
		return operator;
	}

	public void setOperator(java.lang.Long operator) {
		this.operator = operator;
	}

	public java.lang.Long getNewsType() {
		return newsType;
	}

	public void setNewsType(java.lang.Long newsType) {
		this.newsType = newsType;
	}

}