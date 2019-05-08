package com.cqut.recruitPortal.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.cqut.recruitPortal.service.OperatorService;


public class PhotoUpload extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private String uploadPath = SysConstant.HEAD_PATH; // 上传文件的目录  
    //private String tempPath = SysConstant.UPLOAD_TEMP; // 临时文件目录   
	OperatorService service=  new OperatorService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//判断时候是文件上传的请求
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if(isMultipart){
			ServletFileUpload upload = new ServletFileUpload();
			
			FileItemIterator iter;
			byte buffer[]=new byte[1024];
			int c;
			try {
				// Parse the request
				iter = upload.getItemIterator(request);
				String operatorID = "";
				String photo = "";
				while (iter.hasNext()) {
				    FileItemStream item = iter.next();
				    String name = item.getFieldName();
				    InputStream stream = item.openStream();
				    if (item.isFormField()) {
				    	String sName = Streams.asString(stream);
				        System.out.println("Form field " + name + " with value "+ sName + " detected.");
				        operatorID = sName;
				    } else {
				        System.out.println("File field " + name + " with file name "+ item.getName() + " detected.");
				        
				        int index = photo.lastIndexOf(".");
				        String fileType = photo.substring(index+1);
				        String fileName = new Date().getTime()+"."+fileType;
				        File f = new File (uploadPath+File.separator+fileName);
				        photo = fileName;
				        if(!f.exists()){
				        	f.createNewFile();
				        }
				        
				        FileOutputStream out = new FileOutputStream(f);
				        while((c=stream.read(buffer))!=-1){
				             out.write(buffer, 0, c);
				         }
				        
				        out.flush();
				        out.close();
				        stream.close();
				    }
				}
				
				//保存数据到数据库
				String updateSql = "update operator set photo=? where operatorID=?";
				service.cd.executeUpdate(updateSql, new Object[]{photo,Long.parseLong(operatorID)});
				
				request.getServletContext().getRequestDispatcher("/admin/photoUpload.jsp?operatorID="+operatorID+"&photo="+photo).forward(request, response);
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
