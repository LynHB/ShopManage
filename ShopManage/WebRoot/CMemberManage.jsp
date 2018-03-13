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
				<li class="list-unstyled my-li col-sm-4 col-xs-4"><a class="my-a " href="javascript:void(0)">修改会员资料</a></li>
			</div>
		</div>
		<div class="my-mainbody my-body1" style="display:none;">
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
					<textarea style="width:80%;margin:0 auto;" name="detail" placeholder="请输入附加信息（选填）"></textarea>
				</div>
				
				<div style="width:100%;margin-top:3%;text-align:center;">
					<input style="margin:0 auto;" type="submit" value="确认无误，提交注册会员信息" class="btn btn-warning">
				</div>
			</form>
		</div>
		<div class="my-mainbody my-body2" style="display:block;">
					<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th class="ThUserId">账号ID</th>
						<th class="ThUserName">会员姓名</th>
						<th class="ThUserChildName">宝宝姓名</th>
						<th class="ThIntegral">剩余积分</th>
						<th class="ThIntegralTotal">总积分</th>
						<th class="ThAddress">家庭住址</th>
						<th class="ThDetail">备注</th>
						<th class="ThUpdateTime">更新时间</th>
						<th class="ThCreateTime">创建时间</th>
					</tr>
				</thead>
				<tbody class="my-tbody">
					
				</tbody>
			</table>
		
		</div>
		<div class="my-mainbody my-body3" style="display:none;">
			<form  style="padding-top:10px;" class="form-horizontal" action="./CMemberManage?event=upDataOneData" method="post">
				<div class="form-group" style="margin-top:10px;">
					<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
						<input type="text" name="primitive-userId" class="form-control select-user-id" placeholder="请输入用户编号，以便进行修改"> 
					</div>
					
					<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1" style="text-align:center;">
						<button type="button" class="btn btn-success  select-user-button" style="margin:0 auto;">信息查询</button>
					</div>
					
				</div>
				
				<div class="alert alert-danger alert-dismissable my-alert-div" style="width:40%;text-align:center;margin:0 auto;display:none;">
    				<button class="close" type="button" data-dismiss="alert" >&times;</button>  
    				查询失败，请检查用户id是否存在
				</div>
				
				<div class="form-group my-form-group">
					<label for="update-userId" class="col-sm-2 col-xs-5" style="text-align:right;">会员编号：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="userId" tyle="text" class="form-control" placeholder="请输入手机号码作为会员号（必填）" id="update-userId">
					</div>	
					<label for="update-userName"class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">用户名：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="userName" tyle="text" class="form-control" placeholder="请输入会员名字（必填）" id="update-userName">
					</div>
				</div>
				
				<div class="form-group my-form-group">
					<label for="update-userAddress" class="col-sm-2 col-xs-5" style="text-align:right;">家庭地址：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="userAddress" tyle="text" class="form-control" placeholder="请输入家庭地址（选填）" id="update-userAddress">
					</div>
					
					<label for="update-userIntegral"class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">初始积分：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="userIntegral" tyle="text" class="form-control" placeholder="请输入初始积分（选填）" id="update-userIntegral">
					</div>
				</div>
				<br>
				<div class="form-group my-form-group">
					<label for="update-childName" class="col-sm-2 col-xs-5" style="text-align:right;">宝宝姓名：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="childName" tyle="text" class="form-control" placeholder="请输入宝宝名（必填）" id="update-childName">
					</div>
					
					<label for="update-childBirthday" class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">出生日期：</label>
					<div class="col-sm-3 col-xs-6">
						<input type="date" name="childBirthday" class="form-control" id="update-childBirthday">
					</div>
				</div>
	
				<div class="form-group my-form-group">
					<label for="update-childSex" class="col-sm-2 col-xs-5" style="text-align:right;">性别：</label>
					<div class="col-sm-3 col-xs-6">
						<select class="form-control my-select" id="update-childSex" name="childSex">
							<option value="1">金宝宝（女）</option>
							<option value="0">银宝宝（男）</option>
						</select>
					</div>
				</div>
				<br>
				<div class="form-group my-form-group">
					<label for="update-childName2" class="col-sm-2 col-xs-5" style="text-align:right;">第二个宝宝姓名：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="childName2" tyle="text" class="form-control" placeholder="请输入宝宝名（选填）" id="update-childName2">
					</div>
					
					<label for="update-childBirthday2" class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">出生日期：</label>
					<div class="col-sm-3 col-xs-6">
						<input type="date" name="childBirthday2" class="form-control" id="update-childBirthday2">
					</div>
				</div>
	
				<div class="form-group my-form-group">
					<label for="update-childSex2" class="col-sm-2 col-xs-5" style="text-align:right;">性别：</label>
					<div class="col-sm-3 col-xs-6">
						<select class="form-control my-select" id="update-childSex2" name="childSex2">
							<option value="1">金宝宝（女）</option>
							<option value="0">银宝宝（男）</option>
						</select>
					</div>
				</div>
				<div style="width:100%;text-align:center;">
					<textarea style="width:80%;margin:0 auto;" id="update-detail" name="detail" placeholder="请输入附加信息（选填）"></textarea>
				</div>
				
				<div style="width:100%;margin-top:3%;text-align:center;">
					<input style="margin:0 auto;" type="submit" value="确认无误，提交修改会员信息" class="btn btn-success">
				</div>
			</form>
		</div>
    </div>
    <div class="my-mask" style="z-index:10;background-color:gray;height:100%;width:100%;position:absolute;top:0px;left:0px;opacity:0.8;display:none;"></div>
	<div class="shopDivMask" >
		<div style="text-align:center;margin-top:5px;font-size:20px;font-weight:bold;">会员信息筛选</div>
		<form class="form-horizontal" style="padding-top:5%;">
			<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="my-mask-id form-control"  placeholder="账号ID">
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="my-mask-name form-control"  placeholder="用户名">
				</div>
			</div>
			
			<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="form-control my-mask-babyName"  placeholder="宝宝名">
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="form-control my-mask-detail"  placeholder="备注">
				</div>
			</div>
			
			<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					<select class="my-mask-select-integral  form-control">
						<option value="1">&gt;</option>
						<option value="-1">&lt;</option>
						<option value="0">=</option>
					</select>
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="form-control my-mask-integral"  placeholder="剩余积分">
				</div>
			</div>
			
			<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					<select class="form-control my-mask-select-integralTotal">
						<option value="1">&gt;</option>
						<option value="-1">&lt;</option>
						<option value="0">=</option>
					</select>
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="form-control my-mask-integralTotal"  placeholder="总积分">
				</div>
			</div>
			
			<div  class="col-sm-offset-1 col-sm-10 col-xs-10 col-xs-offset-1">
				<button class="btn btn-success form-control my-mask-confirm" type="button">确定</button>
			</div>
			
			<div class="col-sm-offset-1 col-sm-10 col-xs-10 col-xs-offset-1" style="margin-top:10px;">
				<button class="btn btn-warning form-control my-mask-cancel" type="button">取消</button>
			</div>
		</form>
	</div>
    <span class="return-collect" onclick="window.location.href='./collectMoney';return false">返回上一页</span>
    <script src="./js/jquery-3.2.1.js"></script>
	<script src="./js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./js/pinyinjs/dict/pinyin_dict_notone.js"></script>
	<script type="text/javascript" src="./js/pinyinjs/dict/pinyin_dict_withtone.js"></script>
	<script type="text/javascript" src="./js/pinyinjs/pinyinUtil.js"></script>
	<script type="text/javascript" src="./js/pinyinjs/simple-input-method/simple-input-method.js"></script>
	<script src="./js/membermanage.js"></script>
	
  </body>
</html>
