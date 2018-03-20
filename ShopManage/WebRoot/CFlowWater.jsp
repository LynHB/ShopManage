<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>流水账单</title>
    
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
	<link rel="stylesheet" type="text/css" href="./css/flowWater.css">
  </head>
  
  <body>
  	<div class="my-header"></div>
    <div class="my-container container">
    	<div class="my-container-child">
    	<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th class="ThFlowWaterId">流水单号</th>
						<th class="ThCreateTime">订单时间</th>
						<th class="ThShopId">商品id</th>
						<th class="ThShopName">商品名称</th>
						<th class="ThShopSpecification">商品规格</th>
						<th class="ThShopBrand">所属公司</th>
						<th class="ThShopClass">商品分类</th>
						<th class="ThShopPrice">单价原价</th>
						<th class="Th">单价折扣价</th>
						<th class="Th">打折方式</th>
						<th class="Th">购买数量</th>
						<th class="Th">消费金额</th>
						<th class="Th">获得积分</th>
						<th class="Th">会员电话</th>
						<th class="Th">会员姓名</th>
						<th class="Th">订单类型</th>
						<th class="ThShopCost">单件成本</th>
						<th class="Th">订单盈利</th>
					</tr>
				</thead>
				<tbody class="my-tbody">
					
				</tbody>
			</table>
			</div>
    </div>
    <div class="my-mask" style="z-index:10;background-color:gray;height:100%;width:100%;position:absolute;top:0px;left:0px;opacity:0.8;display:none;"></div>
	<div class="shopDivMask" >
		<div style="text-align:center;margin-top:5px;font-size:20px;font-weight:bold;">流水账单信息筛选</div>
		<form class="form-horizontal">
			<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="form-control my-mask-flowWaterId"  placeholder="流水单号">
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="form-control my-mask-userName"  placeholder="会员名">
				</div>
			</div>
			
			<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="my-mask-shopId form-control "  placeholder="商品编号">
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="my-mask-ShopName form-control"  placeholder="商品名称">
				</div>
			</div>
			
			<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="form-control my-mask-brand"  placeholder="公司厂商">
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					<input type="text" class="form-control my-mask-phoneId"  placeholder="会员手机号">
				</div>
			</div>
			
			<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					<label for="beginTime"  style="text-align:right;">起始时间：</label>
					<input type="date" class="form-control my-mask-beginTime" id="beginTime">
				</div>
				
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					<label for="endTime"  style="text-align:right;">结束时间：</label>
					<input type="date" class="form-control my-mask-endTime" id="endTime">
				</div>
			</div>
			
			<div class="col-sm-offset-1 col-sm-10 col-xs-10 col-xs-offset-1" style="margin-top:10px;">
				<button class="btn btn-danger form-control my-mask-reset" type="button">重置</button>
			</div>
			
			<div  class="col-sm-offset-1 col-sm-10 col-xs-10 col-xs-offset-1" style="margin-top:10px;">
				<button class="btn btn-success form-control my-mask-confirm" type="button">确定</button>
			</div>
			
			<div class="col-sm-offset-1 col-sm-10 col-xs-10 col-xs-offset-1" style="margin-top:10px;">
				<button class="btn btn-warning form-control my-mask-cancel" type="button">取消</button>
			</div>
		</form>
	</div>
	<div class="updateFlowWaterDiv">
		<div style="text-align:center;margin-top:5px;font-size:20px;font-weight:bold;">流水账单退单</div>
		<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					<label for="updateFlowWater-id"  style="text-align:right;">流水单号：</label>
					<input type="text" class="form-control updateFlowWater-id" disabled>
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					<label for="updateFlowWater-time"  style="text-align:right;">单号时间：</label>
					<input type="text" class="form-control updateFlowWater-time"  disabled>
				</div>
		</div>
		<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					<label for="updateFlowWater-id"  style="text-align:right;">会员名</label>
					<input type="text" class="form-control updateFlowWater-userName"  disabled>
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					<label for="updateFlowWater-userId"  style="text-align:right;">会员手机</label>
					<input type="text" class="form-control updateFlowWater-userId"  disabled>
				</div>
		</div>
		
		<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					<label for="updateFlowWater-shopName"  style="text-align:right;">商品名</label>
					<input type="text" class="form-control updateFlowWater-shopName" disabled >
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					<label for="updateFlowWater-shopNum"  style="text-align:right;">退货数量</label>
					<input type="text" class="form-control updateFlowWater-shopNum"  >
				</div>
		</div>
		<div class="col-sm-offset-1 col-sm-10 col-xs-10 col-xs-offset-1" style="margin-top:10px;">
				<button class="update-cancelOrder form-control btn btn-danger" style="margin-top:3%">退单</button>
		</div>
			
		<div  class="col-sm-offset-1 col-sm-10 col-xs-10 col-xs-offset-1" style="margin-top:10px;">
				<button class="update-cancel btn  form-control btn-warning"  style="margin-top:3%">取消</button>
		</div>
	</div>
    <span class="return-collect" onclick="window.location.href='./collectMoney';return false">返回上一页</span>
    <script src="./js/jquery-3.2.1.js"></script>
	<script src="./js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./js/pinyinjs/dict/pinyin_dict_notone.js"></script>
	<script type="text/javascript" src="./js/pinyinjs/dict/pinyin_dict_withtone.js"></script>
	<script type="text/javascript" src="./js/pinyinjs/pinyinUtil.js"></script>
	<script type="text/javascript" src="./js/pinyinjs/simple-input-method/simple-input-method.js"></script>
	<script src="./js/CFlowWater.js"></script>
  </body>
</html>
