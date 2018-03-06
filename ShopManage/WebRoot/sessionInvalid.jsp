<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>跳转至首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
	<!--
	<link rel="stylesheet" type="text/css"  href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/sessionInvalid.css">
  </head>
  
  <body>
    <div class="body_div">您的会话可能已经超时失效，或者该网页无法直接访问，还剩下<b id="time">3</b>秒返回登陆页。
    	<br><a href="login" style="color:green;">点击直接跳转</a>
    </div>
  </body>
  <script type="text/javascript">
	delayURL();      
	function delayURL() {   
		var delay = document.getElementById("time").innerHTML;  
		var t = setTimeout("delayURL()", 1000);  
		if (delay > 0) {  
			delay--;  
			document.getElementById("time").innerHTML = delay;  
		} else {  
			clearTimeout(t);   
			window.location.href = "login";  
        }          
    }   
  </script>
</html>
