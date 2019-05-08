package com.cqut.recruitPortal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cqut.recruitPortal.dao.CommonDAO;
import com.cqut.recruitPortal.entity.Operator;

public class OperatorService {
	
	public CommonDAO cd = new CommonDAO();
	
	public List<Object> login(String account,String password){
		String sql = "select * from operator where account=? and password=?";
		List<Map<String,Object>> lm = cd.executeQuery(sql, new Object[]{account,password});
		
		List<Object> rl = new ArrayList<Object>();
		
		//登录成功
		if(lm.size()>0){
			Map<String,Object> operatorMap = lm.get(0);
			Operator operator = new Operator();
			operator.setAccount(operatorMap.get("account").toString());
			operator.setEmail(operatorMap.get("email").toString());
			operator.setPhoto(operatorMap.get("photo").toString());
			operator.setName(operatorMap.get("name").toString());
			operator.setOperatorID(Long.parseLong(operatorMap.get("operatorID").toString()));
			operator.setType(Long.parseLong(operatorMap.get("type").toString()));
			rl.add(true);
			rl.add(true);
			rl.add(operator);
		}
		//登录不成功 可有有两种愿意，1.帐号不存在 2.密码错误
		else{
			sql=  "select * from operator where account=?";
			lm = cd.executeQuery(sql, new Object[]{account});
			if(lm.size()==0){//用户不不存在错误
				System.out.println("this");
				rl.add(false);
				rl.add(true);
			}else{//密码错误
				rl.add(true);
				rl.add(false);
			}
			rl.add(null);
		}
		
		return rl;
	}
	
}
