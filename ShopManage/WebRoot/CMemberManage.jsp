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
				<li class="list-unstyled my-li col-sm-4 col-xs-4"><a class="my-a my-a-active" href="javascript:void(0)">新增会员</a></li>
				<li class="list-unstyled my-li col-sm-4 col-xs-4"><a class="my-a " href="javascript:void(0)">会员总览</a></li>
				<li class="list-unstyled my-li col-sm-4 col-xs-4"><a class="my-a" href="javascript:void(0)">修改会员资料</a></li>
			</div>
		</div>
		<div class="my-mainbody my-body1" style="display:block;">
			<form  style="padding-top:10px;" class="form-horizontal" action="./CMemberManage?event=insertOneData" method="post">
				<div class="form-group my-form-group">
					<label for="userId" class="col-sm-2 col-xs-5" style="text-align:right;">会员编号：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="userId" tyle="text" class="form-control" placeholder="请输入手机号码作为会员号（必填）" id="userId">
					</div>	
					<label for="userName"class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">用户名：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="userName" tyle="text" class="form-control" placeholder="请输入会员名字（必填）" id="userName">
					</div>
				</div>
				
				<div class="form-group my-form-group">
					<label for="userAddress" class="col-sm-2 col-xs-5" style="text-align:right;">家庭地址：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="userAddress" tyle="text" class="form-control" placeholder="请输入家庭地址（选填）" id="userAddress">
					</div>
					
					<label for="userIntegral"class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">初始积分：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="userIntegral" tyle="text" class="form-control" placeholder="请输入初始积分（选填）" id="userIntegral">
					</div>
				</div>
				
				<div class="form-group my-form-group">
					<label for="childName" class="col-sm-2 col-xs-5" style="text-align:right;">宝宝姓名：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="childName" tyle="text" class="form-control" placeholder="请输入宝宝名（必填）" id="childName">
					</div>
					
					<label for="childBirthday" class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">出生日期：</label>
					<div class="col-sm-3 col-xs-6">
						<input type="date" name="childBirthday" class="form-control" id="childBirthday">
					</div>
				</div>
	
				<div class="form-group my-form-group">
					<label for="childSex" class="col-sm-2 col-xs-5" style="text-align:right;">性别：</label>
					<div class="col-sm-3 col-xs-6">
						<select class="form-control my-select" id="childSex" name="childSex">
							<option value="1">金宝宝（女）</option>
							<option value="0">银宝宝（男）</option>
						</select>
					</div>
				</div>
				
				<div class="form-group my-form-group">
					<label for="childName2" class="col-sm-2 col-xs-5" style="text-align:right;">第二个宝宝姓名：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="childName2" tyle="text" class="form-control" placeholder="请输入宝宝名（选填）" id="childName2">
					</div>
					
					<label for="childBirthday2" class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">出生日期：</label>
					<div class="col-sm-3 col-xs-6">
						<input type="date" name="childBirthday2" class="form-control" id="childBirthday2">
					</div>
				</div>
	
				<div class="form-group my-form-group">
					<label for="childSex2" class="col-sm-2 col-xs-5" style="text-align:right;">性别：</label>
					<div class="col-sm-3 col-xs-6">
						<select class="form-control my-select" id="childSex2" name="childSex2">
							<option value="1">金宝宝（女）</option>
							<option value="0">银宝宝（男）</option>
						</select>
					</div>
				</div>
				<div style="width:100%;text-align:center;">
					<textarea style="width:80%;margin:0 auto;" name="detail"placeholder="请输入附加信息（选填）"></textarea>
				</div>
				
				<div style="width:100%;margin-top:3%;text-align:center;">
					<input style="margin:0 auto;" type="submit" value="确认无误，提交注册会员信息" class="btn btn-warning">
				</div>
			</form>
		</div>
		<div class="my-mainbody my-body2" style="display:none;">
					<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th class="ThUserId">账号ID</th>
						<th class="ThUserName">会员姓名</th>
						<th class="ThUserChildName">宝宝姓名</th>
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
