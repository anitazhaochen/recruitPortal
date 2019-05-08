package com.cqut.recruitPortal.dto;

import java.util.List;
import java.util.Map;

import com.cqut.recruitPortal.entity.Operator;

/**
* 项目名称：recruitPortal
* 类名称：OperatorPermission
* 类描述：用于封装封装用户权限   
* @version 1.0
* Copyright (c) 2014 ChongQing University Of Technology
 */
public class OperatorPermission {

	private Operator operator;
	
	private List<Map<String,Object>> permissions;

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public List<Map<String, Object>> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Map<String, Object>> permissions) {
		this.permissions = permissions;
	}

}
