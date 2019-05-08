package com.cqut.recruitPortal.entity;

import java.io.*;

public class OperatorType implements Serializable {

	private static final long serialVersionUID = 1389357906730L;

	private java.lang.Long operatorTypeID;

	private java.lang.String name;

	public OperatorType(){}

	public java.lang.Long getOperatorTypeID() {
		return operatorTypeID;
	}

	public void setOperatorTypeID(java.lang.Long operatorTypeID) {
		this.operatorTypeID = operatorTypeID;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

}