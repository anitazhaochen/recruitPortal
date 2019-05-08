package com.cqut.recruitPortal.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.cqut.recruitPortal.entity.Operator;

/**
 * Servlet Filter implementation class BaseFilter
 */
public class BaseFilter implements Filter {

    /**
     * Default constructor. 
     */
    public BaseFilter() {
        
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		System.out.println("RemoteAddr:"+httpRequest.getRequestURL());
		
		//在这个过滤器里面验证登录
		//只有登录后的人员才可以访问 /admin/ 路径下面的Servlet 和jsp
		String requestURL = httpRequest.getRequestURL().toString();
		
		String loginPage = "/admin/login.jsp";
		
		//是否是登录请求
		boolean isLogin = requestURL.contains("/admin/AdminLogin") || requestURL.contains(loginPage);
		//是否是登录请求管理员页面
		boolean isManagePage = requestURL.contains("/admin/") || requestURL.contains("/AdminServlet");
		//System.out.println(isLogin+","+isManagePage);
		
		if(!isLogin && isManagePage){
			Operator operator = (Operator) httpRequest.getSession().getAttribute("loginOperator");
			if(operator==null || (operator.getType()!=1L && operator.getType()!=2L)){
				httpRequest.getServletContext().getRequestDispatcher("/admin/login.jsp").forward(httpRequest, response);
			}else{
				chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
