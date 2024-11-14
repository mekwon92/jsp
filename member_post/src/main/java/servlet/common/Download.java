package servlet.common;
//servlet은 파라미터 수집

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Commons;
@WebServlet("/download")
public class Download extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 파라미터 수집
		String uuid = req.getParameter("uuid");
		String origin = req.getParameter("origin");
		String path = req.getParameter("path");
		
		if(uuid == null || origin == null || path == null) {
			Commons.printMsg("잘못된 접근입니다.", null, resp);
			return;
		}
		
		File file = new File(Commons.UPLOAD_PATH, path); //업로드된 폴더
		file = new File(file, uuid); 
		
		if(!file.exists()) {
			Commons.printMsg("잘못된 접근입니다.", null, resp);
			return;
		}
		//2. 실제 이름 처리
		String fileName = new String(origin.getBytes("utf-8"), "8859_1");
		
		//3. 응답처리
		resp.setContentType("application/octet-stream"); // 콘텐트타입변경
		resp.setHeader("Content-Disposition", String.format("attachment; filename=%s", fileName)); 
		
		FileInputStream fis = new FileInputStream(file);
		byte[] bytes = fis.readAllBytes(); //20gb
		resp.getOutputStream().write(bytes);
		fis.close();
		
	}
	

}
