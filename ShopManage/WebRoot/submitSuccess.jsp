<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>提交成功</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta name="viewport"
	content="width=device-width,initial-scale=1, maximum-scale=1,minimum-scale-1,user-scalable=no">
	<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
	<style>
  	.errorReturn:hover{
  		cursor:pointer;
  	}
  </style>
  <link rel="stylesheet" type="text/css" href="css/sessionInvalid.css">
  </head>
  
  <body>
    <div class="body_div">提交成功。还剩下<b id="time">3</b>秒返回上一页。
    	<br><span  class="errorReturn" style="color:green;">点击直接跳转</span>
    </div>
  </body>
  <script type="text/javascript">
  document.getElementsByClassName("errorReturn")[0].onclick=function(){
	window.location.href = ${returnSuccessPage};  
}

	delayURL();      
	function delayURL() {   
		var delay = document.getElementById("time").innerHTML;  
		var t = setTimeout("delayURL()", 1000);  
		if (delay > 0) {  
			delay--;  
			document.getElementById("time").innerHTML = delay;  
		} else {  
			clearTimeout(t);   
			window.location.href = ${returnSuccessPage};  
        }          
    }   
  </script>
</html>
