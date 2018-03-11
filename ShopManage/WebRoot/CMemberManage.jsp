<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>会员管理</title>
     
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width,initial-scale=1, maximum-scale=1,minimum-scale-1,user-scalable=no">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="./js/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="./css/membermanage.css">
  </head>
  
  <body>
    <div class="my-header"></div>
    <div class="my-container container">
    	<div class="my-top">
			<div class="my-row row">
				<li class="list-unstyled my-li col-sm-4 col-xs-4"><a class="my-a " href="javascript:void(0)">新增会员</a></li>
				<li class="list-unstyled my-li col-sm-4 col-xs-4"><a class="my-a my-a-active" href="javascript:void(0)">会员总览</a></li>
				<li class="list-unstyled my-li col-sm-4 col-xs-4"><a class="my-a" href="javascript:void(0)">修改会员资料</a></li>
			</div>
		</div>
		<div class="my-mainbody my-body1" style="display:none;"></div>
		<div class="my-mainbody my-body2" style="display:block;">
					<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th class="ThUserId">账号ID</th>
						<th class="ThUserName">会员姓名</th>
						<th class="ThUserChildName">婴儿姓名</th>
						<th class="ThAddress">会员积分</th>
						<th class="ThIntegral">家庭住址</th>
						<th class="ThDetail">备注</th>
						<th class="ThUpdateTime">更新时间</th>
						<th class="ThCreateTime">创建时间</th>
					</tr>
				</thead>
				<tbody class="my-tbody">
					
				</tbody>
			</table>
		
		</div>
		<div class="my-mainbody my-body3" style="display:none;"></div>
    </div>
    <span class="return-collect" onclick="window.location.href='./collectMoney';return false">返回上一页</span>
    <script src="./js/jquery-3.2.1.js"></script>
	<script src="./js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="./js/membermanage.js"></script>
	
  </body>
</html>
