package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.service.OperatorService;

/**
 * Servlet implementation class OperatorEdit
 */
public class OperatorEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	OperatorService service  = new OperatorService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean isOK = true;
		
		String operatorID = request.getParameter("operatorID");
		System.out.println("operatorID eidt page:"+operatorID);
		
		String name = request.getParameter("name");
		String nameMessage = "";
		
		//验证是否输入了姓名
		if(name==null || name.equals("")){
			isOK = false;
			nameMessage = "姓名不能为空";
		}
		String gender = request.getParameter("gender");
		
		String account = request.getParameter("account");
		String oldaccount = request.getParameter("oldaccount");//原来的帐号
		String accountMessage =  "";
		if(account==null || account.equals("")){
			accountMessage = "帐号不能为空";
			isOK = false;
		}else{
			
			//到数据库中验证该帐号是否存在
			if(!account.equals(oldaccount)){
				String sql = "select count(`account`) as count from operator where account=?";
				int count = Integer.parseInt(service.cd.executeQuery(sql, new Object[]{account}).get(0).get("count").toString());
				if(count>0){
					accountMessage = "该帐号已经存在";
					isOK = false;
				}
			}
			
			
		}
		
		String email = request.getParameter("email");
		String type = request.getParameter("type");
		
		//String password = request.getParameter("password");
		//String repassword = request.getParameter("repassword");
		//String passwordMessage = "";
		//String repasswordMessage = "";
//		//判断密码是否为空
//		if(password==null || password.equals("")){
//			passwordMessage = "密码不能为空";
//			isOK = false;
//		}
//		//判断两次密码输入是否一致
//		else{
//			if(!password.equals(repassword)){
//				repasswordMessage = "输入密码不一致";
//				isOK = false;
//			}
//		}
		
		if(isOK){
			//保存到数据库
			//String insertSql = "insert into operator(`name`,`gender`,`account`,`email`,`type`,`password`) values(?,?,?,?,?,?)";
			
			String updateSql = "update operator set " +
					"`name`=?, " +
					"`gender`=?, " +
					"`account`=?, " +
					"`email`=?, " +
					"`type`=? " +
					"where `operatorID`=?";
			
			int updateCount = service.cd.executeUpdate(updateSql, new Object[]{name,gender,account,email,type,Long.parseLong(operatorID)});
			//等于1 表示添加成功
			if(updateCount==1){
				//这里设置一个参数表示是由 这个页面 跳转到 OperatorServlet或 OperatorEdit 页面跳转过去的去的。
				request.setAttribute("redirect", "1");
				request.getServletContext().getRequestDispatcher("/admin/OperatorServlet").forward(request, response);
				//response.sendRedirect("/admin/OperatorServlet");
				//return;
			}else{
				request.setAttribute("addMessage", "编辑失败");
				request.getServletContext().getRequestDispatcher("/admin/OperatorInitEdit?operatorID"+operatorID).forward(request, response);
			}
			
		}else{
			request.setAttribute("nameMessage", nameMessage);
			request.setAttribute("accountMessage", accountMessage);
			//request.setAttribute("passwordMessage", passwordMessage);
			//request.setAttribute("repasswordMessage", repasswordMessage);
			request.getServletContext().getRequestDispatcher("/admin/OperatorInitEdit?operatorID"+operatorID).forward(request, response);
		}
		
		//通过调用request.getParameterMap() 可以得到浏览器传递的所有参数
		//Map<String,String[]> parameterMap = request.getParameterMap();
		//System.out.println(JSON.toJSONString(parameterMap));
		
	}

}
