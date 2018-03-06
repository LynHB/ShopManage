<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>${title}</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link type="text/css" rel="stylesheet" href="css/login.css" />

</head>

<body>
	<div class="login-image">
		<div class="header_login">
			<ul>
				<li class="header_login_ul_li_first"><a href="">店图标</a></li>
				<li><a href="">奶粉</a></li>
				<li><a href="">米粉</a></li>
				<li><a href="">保健品</a></li>
				<li><a href="">玩具</a></li>
				<li><a href="">纸尿布</a></li>
				<li><a href="">衣服</a></li>
				<li><a href="">技术支持</a></li>
				<li><a href="">搜索图标</a></li>
				<li class="header_login_ul_li_last"><a href="">购物篮图标</a></li>
			</ul>
		</div>

		<div class="header2_login">
			<div class="header2_login_contain">
				<div>
					<b>美贝 ID</b>
				</div>
				<ul>
					<li><a href="" style="color:gray;">登陆</a></li>
					<li><a href="">创建您的账户</a></li>
					<li><a href="">常见问题解答</a></li>
				</ul>
			</div>
		</div>
		<div class="bodyer_login">
			<div>
					<b class="bodyer_login_title">美贝 ID</b>
			</div>
			<div class="bodyer_login_descript">
					<p>管理您的美贝ID</p>
			</div>
			<form action="confirm" method="post">
				<input id="userName" name="userName" class="input_common_style" type="text" value="美贝ID"  onfocus='if(this.value=="美贝ID"){this.value="";this.style.color="black";}; '   
            onblur='if(this.value==""){this.value="美贝ID";this.style.color="gray";};'>
				<input id="passWord" name="passWord" class="input_common_style" type="text" value="请输入您的密码"  onfocus='if(this.value=="请输入您的密码"){this.value="";this.style.color="black";this.type="password"}; '   
            onblur='if(this.value==""){this.value="请输入您的密码";this.style.color="gray";this.type="text"};'>
				<input class="input_common_style input_submit" type="submit" value="登陆">
				<div class="bodyer_login_checkbox_remeber_password">
					<input  id="checkbox_input" type="checkbox" >
					<label for="checkbox_input">记住我的美贝ID和密码</label>
				</div>
				${errorInfo}
			</form>
			<div  class="bodyer_login_forget_password_div"><a class="bodyer_login_forget_password" href="">忘记了美贝ID或密码?</a>
			</div>
		</div>
	</div>
	<div class="footer_login"></div>
</body>
<script src="js/jquery-3.2.1.js" type="text/javascript"></script>
<script type="text/javascript" src="js/login.js"></script>
</html>
