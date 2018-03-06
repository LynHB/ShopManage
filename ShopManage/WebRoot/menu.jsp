<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<link rel="stylesheet" type="text/css" href="css/menu.css">
	<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div class="header_menu">
			<ul>
				<li class="header_menu_ul_li_first"><a href="">店图标</a></li>
				<li><a href="">奶粉</a></li>
				<li><a href="">米粉</a></li>
				<li><a href="">保健品</a></li>
				<li><a href="">玩具</a></li>
				<li><a href="">纸尿布</a></li>
				<li><a href="">衣服</a></li>
				<li><a href="">技术支持</a></li>
				<li><a href="">搜索图标</a></li>
				<li class="header_menu_ul_li_last"><a href="">购物篮图标</a></li>
			</ul>
	</div>
	<div style="overflow:hidden;">
		<div class="bodyer_image_title">
		</div>
	</div>
	<div class="bodyer_menu">
		<div class="bodyer_menu_userinfo">
			<div class="bodyer_userName">${sessionName}</div>
			<div class="bodyer_userDescript">您的美贝ID是${sessionAccountId} </div>
			<div class="bodyer_cancel"><a href="menu?type=cancel">注销</a></div>
		</div>
		<div class="bodyer_content">
			<div class="bodyer_content_left"><div>账户</div></div>
			<div class="bodyer_content_center">
				<div>美贝ID
					<p>${sessionAccountId}</p>
				</div>
				<div>联系方式
					<p>${sessionPhone}</p>	
				</div>
				<div>出生日期
					<p>${seesionBirthday}</p>
					</div>
				<div>省地
					<p>待SID计算。。。</p>
					</div>
				<div>E-MAIL
					<p>${sessionE_mail}</p>
				</div>
			</div>
			<div class="bodyer_content_right">
				<div>编辑</div>
			</div>
		</div>
		<div class="bodyer_content">
			<div class="bodyer_content_left"><div>微信公众号</div></div>
			<div class="bodyer_content_center bodyer_content_wechat">
				<span class="bodyer_content_span_link"><a href="wechat">操作更多请点击</a></span>
				<div>微信名
					<p>测试号</p>
				</div>
				<div>Token
					<p>Lynn</p>
				</div>
				<div>URL
					<p>http://lynn2013.free.ngrok.cc/WeChat/servlet/FirstWc</p>
				</div>
				<div>appID
					<p>wxf18136a87dee4730</p>
				</div>
			</div>
			<div class="bodyer_content_right"><div>编辑</div></div>
		</div>
		<div class="bodyer_content">
			<div class="bodyer_content_left"><div>收银系统</div></div>
			<div class="bodyer_content_center bodyer_content_wechat">
				<span class="bodyer_content_span_link"><a href="collectMoney">操作更多请点击</a></span>
				
			</div>
			<div class="bodyer_content_right"><div>编辑</div></div>
		</div>
		<div class="footer_menu"></div>
	</div>	
  </body>
</html>
