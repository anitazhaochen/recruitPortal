package com.cqut.recruitPortal.servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.recruitPortal.entity.Operator;
import com.cqut.recruitPortal.service.NewsService;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class NewsAdd
 */
public class NewsAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NewsService service = new NewsService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Map params = uploadImg(request,response);

		boolean isOK = true;
//		String title = request.getParameter("title");
        String title = (String) params.get("title");

		String titleMessage = "";
		if(title==null || title.equals("")){
			isOK = false;
			titleMessage = "标题不能为空";
		}
//		String content = request.getParameter("content");
		String content = (String) params.get("content");

		String contentMessage = "";
		if(content==null || content.equals("")){
			isOK = false;
			contentMessage = "内容不能为空";
		}

//		String deadLine = request.getParameter("deadLine");
		String deadLine = (String) params.get("deadLine");

		String deadLineMessage = "";
		
//		String type = request.getParameter("type");
		String type = (String) params.get("type");
		String typeMessage ="";
		if(type==null || type.equals("")){
			typeMessage = "请选择";
		}
		
		Long operatorID = ((Operator)request.getSession().getAttribute("loginOperator")).getOperatorID();
		
		Date date = new Date();
		java.text.SimpleDateFormat sdf =
				new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String publishTime = sdf.format(date);
		Date deadLineTime = null;

		String imgPath = (String) request.getSession().getAttribute("imgPath");
		String addMessage = "";
		if(isOK){
			String sql = "insert into news(`title`,`content`,`type`,`publishTime`,`count`,`operator`,`status`,`img`) values(?,?,?,?,?,?,?,?)";
			Object objs[]={title,content,Long.parseLong(type),publishTime,0,operatorID,1,imgPath};

			int updateCount = service.cd.executeUpdate(sql, objs);
			if(updateCount!=1){
				addMessage = "新增失败";
			}else{
				request.getServletContext().getRequestDispatcher("/admin/NewsServlet").forward(request, response);
				return;
			}
		}

		request.setAttribute("titleMessage", titleMessage);
		request.setAttribute("contentMessage", contentMessage);
		request.setAttribute("deadLineMessage", deadLineMessage);
		request.setAttribute("typeMessage", typeMessage);
		request.setAttribute("addMessage", addMessage);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("deadLine", deadLine);
		//request.setAttribute("title", title);
		String phtml=  service.createOperationPermissionHtml(type, operatorID);
		request.setAttribute("phtml",phtml);
		request.getServletContext().getRequestDispatcher("/admin/newsAdd.jsp").forward(request, response);
	}

	public Map uploadImg(HttpServletRequest req, HttpServletResponse response) throws UnsupportedEncodingException {
		Map params = new HashMap();
		req.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String timestamp = req.getParameter("timestamp");
		System.out.println("执行图片上传 wwwwwwwwwww");
		//为解析类提供配置信息
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//创建解析类的实例
		ServletFileUpload sfu = new ServletFileUpload(factory);
		//开始解析
		sfu.setFileSizeMax(1024 * 4000);
		//每个表单域中数据会封装到一个对应的FileItem对象上

		try {
			List<FileItem> items = sfu.parseRequest(req);
			//区分表单域
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				//isFormField为true，表示这不是文件上传表单域
				params.put(item.getFieldName(), item.getString("utf-8"));//如果你页面编码是utf-8的
				if (!item.isFormField()) {
					//获得存放文件的物理路径
					//upload下的某个文件夹   得到当前在线的用户  找到对应的文件夹

					String currentPath = this.getServletConfig().getServletContext().getRealPath("/");
					int index = currentPath.lastIndexOf("out");
					currentPath = currentPath.substring(0,index);
					String path = currentPath+"WebContent/image/logo";
					//获得文件名
					System.out.println(item.getName());
					String[] fileNames = item.getName().split("[.]");
					System.out.println(fileNames.length);
					System.out.println(fileNames.toString());
					String suffix = fileNames[fileNames.length-1];
					UUID uuid = UUID.randomUUID();
					System.out.println(suffix);
					String fileName = uuid.toString()+"."+suffix;
					System.out.println(fileName);
					//该方法在某些平台(操作系统),会返回路径+文件名
//					fileName = fileName.substring(fileName.lastIndexOf("/") + 1);

					System.out.println(path+"/"+fileName);
					req.getSession().setAttribute("imgPath", "image/logo/"+fileName);
					File file = new File(path + "/" + fileName);
					File out_file = new File(req.getSession().getServletContext().getRealPath("/")+"image/logo/"+fileName);
					if (!file.exists()) {
						item.write(file);
						item.write(out_file);
					}
				}
			}
			String send_time = (String) params.get("timestamp");
			System.out.println(send_time);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return params;

	}


}
