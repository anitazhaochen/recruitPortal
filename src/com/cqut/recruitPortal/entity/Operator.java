package com.cqut.recruitPortal.entity;

import java.io.*;

public class Operator implements Serializable {

	private static final long serialVersionUID = 1389357906724L;

	private java.lang.Long operatorID;

	private java.lang.String name;

	private java.lang.String account;

	private java.lang.String password;

	private java.lang.Long type;

	private java.lang.String email;

	private java.lang.String gender;

	private java.lang.String photo;

	public Operator(){}

	public java.lang.Long getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(java.lang.Long operatorID) {
		this.operatorID = operatorID;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getAccount() {
		return account;
	}

	public void setAccount(java.lang.String account) {
		this.account = account;
	}

	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.Long getType() {
		return type;
	}

	public void setType(java.lang.Long type) {
		this.type = type;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getGender() {
		return gender;
	}

	public void setGender(java.lang.String gender) {
		this.gender = gender;
	}

	public java.lang.String getPhoto() {
		return photo;
	}

	public void setPhoto(java.lang.String photo) {
		this.photo = photo;
	}

}