<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
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
	<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
		${navigateCss} 
		${rightCss}
	</style> 
	<link type="text/css" rel="stylesheet" href="css/wechat.css" />
	<script type="text/javascript" src="js/pageOnload.js"></script>
  </head>
  	
  <body style="text-align:left;background-color:#F6F8F9">
    ${navigateHtml}
    <div style="font-size:0px;width:1200px;margin:0 0;">
    <div class="bodyer_left">
     
    	<a class="left_commonPage" href="wechat">
    		<p class="left_commonPage_p left_homePage">首页</p>
    	</a>
    	
    	<a class="left_commonPage">
    		<p class="left_commonPage_p left_tool">功能</p>
    	</a>
    	<a class="left_commonFun userDefinedReply" href="wechat?type=userDefinedReply">
    		<p class="left_commonFun_p userDefinedReply_p">自定义回复</p>
    	</a>
    	<a class="left_commonFun userDefinedMenu" href="wechat?type=userDefinedMenu">
    		<p class="left_commonFun_p userDefinedMenu_p">自定义菜单</p>
    	</a>
    	
    	<!--  <a class="left_commonFun massText" href="wechat?type=massText">
    		<p class="left_commonFun_p massText_p">群发消息</p>
    	</a>
    	-->
    	
    	<a class="left_commonFun sourceMaterial" href="wechat?type=sourceMaterial">
    		<p class="left_commonFun_p sourceMaterial_p">素材管理</p>
    	</a>
    
    	<a class="left_commonPage">
    		<p class="left_commonPage_p left_config">配置</p>
    	</a>
    	<a class="left_commonFun serverConfig" href="wechat?type=serverConfig">
    		<p class="left_commonFun_p serverConfig_p">资源同步</p>
    	</a>
    	
    </div>
    <div class="return_menu">返回主菜单 </div>
    <div class="bodyer_right">
    	${right_content}	
    	
    </div>
    </div>
    <div class="footer"></div>
  </body>
  <script type="text/javascript" src="js/jquery-3.2.1.js"></script>
  <script type="text/javascript" src="js/wechat.js"></script>
</html>
