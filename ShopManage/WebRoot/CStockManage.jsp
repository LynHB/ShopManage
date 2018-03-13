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
					<label for="Classification" class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">分类选择：</label>
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
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th class="shopThId">商品编号</th>
						<th class="shopThName">商品名称</th>
						<th class="shopThBalance">库存余额</th>
						<th class="shopThSell">已售库存</th>
						<th class="shopThCost">成本均价</th>
						<th class="shopThPrice">商品售价</th>
						<th class="shopThRate">产品利率</th>
						<th class="shopThClass">商品分类</th>
						<th class="shopThSpeci">规格信息</th>
						<th class="shopThBrand">公司厂商</th>
						<th class="shopThDetail">附加信息</th>
					</tr>
				</thead>
				<tbody class="my-tbody">
					
				</tbody>
			</table>
		</div>
		<div class="my-mainbody my-body3" style="display:none;">
			<form  style="padding-top:10px;" class="form-horizontal" action="./CStockManage?event=upDataOneData" method="post">
				<div class="form-group" style="margin-top:10px;">
					<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
						<input type="text" name="primitive-cid" class="form-control select-shop-id" placeholder="请输入商品编号，以便进行修改"> 
					</div>
					
					<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1" style="text-align:center;">
						<button type="button" class="btn btn-success  select-shop-button" style="margin:0 auto;">信息查询</button>
					</div>
					
				</div>
				
				<div class="alert alert-danger alert-dismissable my-alert-div" style="width:40%;text-align:center;margin:0 auto;display:none;">
    				<button class="close" type="button" data-dismiss="alert" >&times;</button>  
    				查询失败，请检查商品编号是否存在
				</div>
				
				<div class="form-group my-form-group">
					<label for="update-shopId" class="col-sm-2 col-xs-5" style="text-align:right;">商品编号：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="cid" tyle="text" class="form-control" placeholder="请输入商品编号（必填）" id="update-shopId">
					</div>	
					<label for="update-Classification" class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">分类选择：</label>
					<div class="col-sm-3 col-xs-6">
						<select class="form-control my-select" id="update-Classification" name="classification">
						</select>
					</div>
				</div>
				
				<div class="form-group my-form-group">
					<label for="update-shopName" class="col-sm-2 col-xs-5" style="text-align:right;">商品名称：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="name" tyle="text" class="form-control" placeholder="请输入商品名称（必填）" id="update-shopName">
					</div>
					
					<label for="update-StockBalance"class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">库存数量：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="stockBalance" tyle="text" class="form-control" placeholder="请输入库存数量（必填）" id="update-StockBalance">
					</div>
				</div>
				
				<div class="form-group my-form-group">
					<label for="update-averageCost" class="col-sm-2 col-xs-5" style="text-align:right;">成本均价：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="averageCost" tyle="text" class="form-control" placeholder="请输入成本均价（必填）" id="update-averageCost">
					</div>
					
					<label for="update-MarketingPrice" class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">销售均价：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="marketingPrice" tyle="text" class="form-control" placeholder="请输入销售均价（必填）" id="update-MarketingPrice">
					</div>
				</div>
	
				<div class="form-group my-form-group">
					<label for="update-Specifications" class="col-sm-2 col-xs-5" style="text-align:right;">参数规格：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="specifications" tyle="text" class="form-control" placeholder="请输入规格参数（必填）" id="update-Specifications">
					</div>
					
					<label for="update-Brand" class="col-sm-offset-1 col-sm-2 col-xs-5" style="text-align:right;">厂家名称：</label>
					<div class="col-sm-3 col-xs-6">
						<input name="brand" tyle="text" class="form-control" placeholder="请输入厂家名称（必填）" id="update-Brand">
					</div>
				
				</div>
				
				<div style="width:100%;text-align:center;">
					<textarea style="width:80%;margin:0 auto;" name="detail" id="update-detail" placeholder="请输入附加信息（选填）"></textarea>
				</div>
				
				<div style="width:100%;margin-top:3%;text-align:center;">
					<input style="margin:0 auto;" type="submit" value="确认无误，修改商品信息" class="btn btn-success">
				</div>
			</form>
			
		</div>
	</div>
	<div class="my-mask" style="z-index:10;background-color:gray;height:100%;width:100%;position:absolute;top:0px;left:0px;opacity:0.8;display:none;"></div>
	<div class="shopDivMask" >
		<div style="text-align:center;margin-top:5px;font-size:20px;font-weight:bold;">商品信息筛选</div>
		<form class="form-horizontal">
			<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="my-mask-id form-control"  placeholder="商品编号">
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="my-mask-name form-control"  placeholder="商品名称">
				</div>
			</div>
			
			<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="form-control my-mask-brand"  placeholder="公司厂商">
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="form-control my-mask-detail"  placeholder="附加信息">
				</div>
			</div>
			
			<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					<select class="my-mask-select-balance  form-control">
						<option value="1">&gt;</option>
						<option value="-1">&lt;</option>
						<option value="0">=</option>
					</select>
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="form-control my-mask-balance"  placeholder="库存余额">
				</div>
			</div>
			
			<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					<select class="form-control my-mask-select-rate">
						<option value="1">&gt;</option>
						<option value="-1">&lt;</option>
						<option value="0">=</option>
					</select>
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="form-control my-mask-rate"  placeholder="(销售价-进价)/进价">
				</div>
			</div>
			
			<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-10 col-xs-10 col-xs-offset-1">
					<select class="form-control my-select my-select-class">
						<option value="0"></option>
					</select>
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
	<script src="./js/stockmanage.js"></script>
  </body>
</html>
