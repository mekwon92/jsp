<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="image/png"
    pageEncoding="UTF-8"%>
<%
	FileInputStream fis = new FileInputStream("C:/Users/tj/Desktop/dev/pngwing.png");
	byte[] bs = fis.readAllBytes();
	ServletOutputStream sos = response.getOutputStream();
	sos.write(bs);
%>
