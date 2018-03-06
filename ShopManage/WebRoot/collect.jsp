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

<title>收银系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
<meta name="viewport"
	content="width=device-width,initial-scale=1, maximum-scale=1,minimum-scale-1,user-scalable=no">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
</style>
<link rel="stylesheet"
	href="./js/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/collect.css">
</head>

<body style="padding-top:50px;overflow-x:hidden;overflow-y:hidden;">
	<div class="navbar-fixed-top navbar navbar-inverse">
		<div class="my-navbar-header navbar-header">
			<button class="navbar-toggle" type="button" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="sr-only">Toggle Navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!-- 确保无论是宽屏还是窄屏，navbar-brand都显示 -->
			<a href="##" class="navbar-brand">店图标</a>
		</div>
		<div class="collapse navbar-collapse navbar-responsive-collapse">
			<ul class="nav navbar-nav" style="text-align:center;">
				<li><a href="##">奶粉</a></li>
				<li><a href="##">米粉</a></li>
				<li><a href="##">保健品</a></li>
				<li><a href="##">玩具</a></li>
				<li><a href="##">纸尿布</a></li>
				<li><a href="##">衣服</a></li>
				<li><a href="##">搜索图标</a></li>
				<li><a href="##">购物篮图标</a></li>
			</ul>
		</div>
	</div>
	<div class=" my-3DSpace">
		<div class="my-3D-menu">
			<img class="my-baby-photo" src="./image/collectMenu.jpg"> </img>
			<div class="my-photo-title">一个小宝宝</div>
			<div class="my-photo-content">和四只巴巴爸爸的故事。。。</div>
			<button class="my-video-button btn btn-primary" type="button" data-toggle="modal" data-target="#mymodal" >了解我</button>
		</div>
		<div class="my-3D-title">
			<img class="my-baby-photo" src="./image/collectTitle.jpg"></img>
			<canvas id="my-sun" class="my-sun" width="300" height="300" style="z-index:100;"></canvas>
			<canvas id="my-sun2" class="my-sun" width="300" height="300" style="z-index:50;"></canvas>
			<canvas id="my-sun3" class="my-sun" width="300" height="300" style="z-index:10;"></canvas>
			<canvas id="my-sun4" class="my-sun" width="300" height="300" style="z-index:10;"></canvas>
			<canvas id="my-sun5" class="my-sun" width="300" height="300" style="z-index:200;"></canvas>
			<audio class="backgroundMusic" src="image/backgroundMusic.mp3" controls="controls"   hidden="true"></audio>
			<button class="btn btn-info my-btn my-btn-collectStart" onclick="window.open('./CollectStart')">开始收银</button>
			<button class="btn btn-warning my-btn my-btn-stockManage" onclick="window.open('./CStockManage')">库存管理</button>
			<button class="btn btn-success my-btn my-btn-flowingWater" onclick="window.open('./CFlowingWater')">流水账单</button>
			<button class="btn btn-primary my-btn my-btn-memberManage" onclick="window.open('./CMemberManage')">会员管理</button>
			<button class="btn btn-danger my-btn my-btn-returnMenu" onclick="javascrtpt:window.location.href='./menu.jsp'">返回菜单</button>
		</div>
	</div>
		<div class="modal fade" id="mymodal">
				<div class="modal-dialog">
					<div class="my-modal-content modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">你是一树一树的花开,是燕在梁间的呢喃</h4>
						</div>
						<div class="my-modal-body modal-body">
							<video width="300px" height="240px" style="text-align:center;" controls="controls">
  								<source src="./image/collectBaby.mp4" type="video/mp4">
									你的浏览器不支持video标签
							</video>
						</div>
						<div class="modal-footer">
							<pre style="text-align:left;">When ambition ends,happiness begins.
								Author:Lyn</pre>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary">保存</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
	<script src="./js/jquery-3.2.1.js"></script>
	<script src="./js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="./js/collect.js"></script>
</body>
</html>
