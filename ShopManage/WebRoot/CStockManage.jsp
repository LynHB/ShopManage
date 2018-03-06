<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>库存管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport"
	content="width=device-width,initial-scale=1, maximum-scale=1,minimum-scale-1,user-scalable=no">
	<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="./js/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="./css/stockmanage.css">
	
  </head>
  
  <body >
  	<div class="my-header"></div>
	<div class="my-container container">
		<div class="my-top">
		<div class="my-row row">
			<li class="list-unstyled my-li col-sm-4 col-xs-4"><a class="my-a" href="javascript:void(0)">添加未有商品</a></li>
			<li class="list-unstyled my-li col-sm-4 col-xs-4"><a class="my-a my-a-active" href="javascript:void(0)">库存总览</a></li>
			<li class="list-unstyled my-li col-sm-4 col-xs-4"><a class="my-a" href="javascript:void(0)">修改商品库存</a></li>
		</div>
		</div>
		<div class="my-mainbody my-body1" style="display:none;">1</div>
		<div class="my-mainbody my-body2" >2</div>
		<div class="my-mainbody my-body3" style="display:none;">3</div>
	</div>
	<script src="./js/jquery-3.2.1.js"></script>
	<script src="./js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="./js/stockmanage.js"></script>
  </body>
</html>
