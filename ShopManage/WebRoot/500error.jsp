<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"  isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>服务器报错</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/500error.css">
	<script type="text/javascript" src="js/pageOnload.js"></script>
  </head>
  
  <body style="text-align:center;">
  	<div class="backgroundImage">
  		<div class="errorCode">500</div>
  		<div class="errorExplain">&nbsp;&nbsp;&nbsp;&nbsp;哭唧唧， 页面出错了，万能的程序员哥哥，会在第一时间赶完现场，并修复该报错问题。
    	</div>
    	<div class="errorReturn">返回上一层</div>
  	</div>
  </body>
  <script type="text/javascript" src="js/500error.js"></script>
</html>
