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
				<li class="list-unstyled my-li col-sm-4 col-xs-4"><a class="my-a " href="javascript:void(0)">添加未有商品</a></li>
				<li class="list-unstyled my-li col-sm-4 col-xs-4"><a class="my-a my-a-active" href="javascript:void(0)">库存总览</a></li>
				<li class="list-unstyled my-li col-sm-4 col-xs-4"><a class="my-a" href="javascript:void(0)">修改商品库存</a></li>
			</div>
		</div>
		<div class="my-mainbody my-body1" style="display:none;">
			<form  style="padding-top:10px;" class="form-horizontal" action="./CStockManage?event=insertOneData" method="post">
				<div class="form-group my-form-group">
					<label for="shopId" class="col-sm-2 col-xs-5" style="text-align:right;">商品编号：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="cid" tyle="text" class="form-control" placeholder="请输入商品编号（必填）" id="collectId">
					</div>	
					<label for="Classification"class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">分类选择：</label>
					<div class="col-sm-3 col-xs-6">
						<select class="form-control my-select" id="Classification" name="classification">
						</select>
					</div>
				</div>
				
				<div class="form-group my-form-group">
					<label for="shopName" class="col-sm-2 col-xs-5" style="text-align:right;">商品名称：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="name" tyle="text" class="form-control" placeholder="请输入商品名称（必填）" id="shopName">
					</div>
					
					<label for="StockBalance"class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">库存数量：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="stockBalance" tyle="text" class="form-control" placeholder="请输入库存数量（必填）" id="StockBalance">
					</div>
				</div>
				
				<div class="form-group my-form-group">
					<label for="averageCost" class="col-sm-2 col-xs-5" style="text-align:right;">成本均价：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="averageCost" tyle="text" class="form-control" placeholder="请输入成本均价（必填）" id="averageCost">
					</div>
					
					<label for="MarketingPrice" class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">销售均价：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="marketingPrice" tyle="text" class="form-control" placeholder="请输入销售均价（必填）" id="MarketingPrice">
					</div>
				</div>
	
				<div class="form-group my-form-group">
					<label for="Specifications" class="col-sm-2 col-xs-5" style="text-align:right;">参数规格：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="specifications" tyle="text" class="form-control" placeholder="请输入规格参数（必填）" id="Specifications">
					</div>
					
					<label for="Brand" class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">厂家名称：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="brand" tyle="text" class="form-control" placeholder="请输入厂家名称（必填）" id="Brand">
					</div>
				
				</div>
				
				<div style="width:100%;text-align:center;">
					<textarea style="width:80%;margin:0 auto;" name="detail"placeholder="请输入附加信息（选填）"></textarea>
				</div>
				
				<div style="width:100%;margin-top:3%;text-align:center;">
					<input style="margin:0 auto;" type="submit" value="确认无误，提交商品信息" class="btn btn-warning">
				</div>
			</form>
		</div>
		<div class="my-mainbody my-body2" >
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>商品编号</th>
						<th>商品名称</th>
						<th>库存余额</th>
						<th>已售库存</th>
						<th>成本均价</th>
						<th>商品售价</th>
						<th>商品分类</th>
						<th>规格信息</th>
						<th>公司厂商</th>
						<th>附加信息</th>
					</tr>
				</thead>
				<tbody class="my-tbody">
					
				</tbody>
			</table>
		</div>
		<div class="my-mainbody my-body3" style="display:none;">3</div>
	</div>
	<script src="./js/jquery-3.2.1.js"></script>
	<script src="./js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="./js/stockmanage.js"></script>
  </body>
</html>
