<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>开始收银</title>
    
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
	<link rel="stylesheet" type="text/css" href="./css/collectStart.css">
  </head>
  
  <body>
    <div class="my-header">
    </div>
    <div class="my-container container">
    	<div class="my-table">
			<table class="table table-bordered table-hover">
					<thead>
						<tr class="titleTr">
							<th class="shopThId">商品编号</th>
							<th class="shopThName">商品图片</th>
							<th class="shopThBalance">商品名称</th>
							<th class="shopThSell">原单价</th>
							<th class="shopThCost">成交单价</th>
							<th class="shopThPrice">商品数量</th>
							<th class="shopThRate">商品总价</th>
							<th class="shopThDiscount">折扣类型</th>
						</tr>
					</thead>
					<tbody class="my-tbody">
						
					</tbody>
			</table>
		</div>
    	<div class="form-horizontal my-serach-form">
			<div class="form-group my-form-group">
					<div class="col-sm-3 col-sm-offset-1 col-xs-offset-3 col-xs-4">
						<input name="shopId"  tyle="text" class="shopId form-control" maxlength="13" placeholder="商品编号" id="shopId">
					</div>
					
					<div class="col-sm-1 col-xs-3">
						<button class="my-shoip-collect-btn btn btn-success">确认</button>
					</div>
					
					<div class="col-sm-3 col-sm-offset-1 col-xs-offset-3 col-xs-4">
						<input type="text" class="my-phone-id form-control"  maxlength="11" placeholder="会员手机号码">
					</div>
			
					<div class="col-sm-1 col-xs-3">
						<button class="my-member-collect-btn btn btn-success">会员收银</button>
					</div>
				</div>
		</div>
		<div class="other-info">
			<div class="member-info ">
				<div >会员名：<span class="member-name">暂无</span></div>
				<div >会员ID：<span class="member-id">暂无</span></div>
				<div >会员剩余积分：<span class="member-Integral">暂无</span></div>
				<div >会员备注信息：<span class="member-detail">暂无</span></div>
			</div>
			
			<button class="my-button my-button-1 btn btn-info" type="button">今日活动</button>
			<button class="my-button my-button-2 btn btn-warning" type="button">重置订单</button>
			<button class="my-button my-button-3 btn btn-danger" type="button">开启钱箱</button>
			<button class="my-button my-button-4 btn btn-primary" type="button">其他设置</button>
			<button class="my-button my-button-5 btn btn-muted" type="button">+</button>
			<button class="my-button my-button-6 btn btn-muted" type="button">+</button>
			<div class="my-total">总计:00.00</div>
			<div class="my-settlement">结算</div>
		</div>
    </div>
    <canvas id="canvas1" class="my-canvas-1" width="100" height="100"></canvas>
    <canvas id="canvas2" class="my-canvas-1" width="100" height="100"></canvas>
    <canvas id="canvas3" class="my-canvas-1" width="100" height="100"></canvas>
    <span class="return-collect" onclick="window.location.href='./collectMoney';return false">返回上一页</span>
    <div class="my-mask" style="z-index:10;background-color:gray;height:100%;width:100%;position:absolute;top:0px;left:0px;opacity:0.8;display:none;"></div>
	<div class="shopDivMask" >
		<div style="text-align:center;margin-top:5px;font-size:20px;font-weight:bold;">商品议价</div>
		<form class="form-horizontal" style="padding-top:2%;">
			<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					商品编号：<input type="text" class="my-mask-id form-control"  readonly="readonly">
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					商品名称：<input type="text" class="my-mask-name form-control"  readonly="readonly">
				</div>
			</div>
			
			<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					商品原价：
					<input type="text" class="form-control my-mask-price"  readonly="readonly">
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					商品成交价：
					<input type="text" class="form-control my-mask-turnover"  placeholder="成交价修改">
				</div>
			</div>
			
			
			<div class="form-group" style="margin-top:5px;">
				<div class="col-sm-offset-1 col-sm-4 col-xs-10 col-xs-offset-1">
					商品数量：
					<input type="text" class="form-control my-mask-number"  placeholder="商品数量">
				</div>
			
				<div class="col-sm-offset-2 col-sm-4 col-xs-10 col-xs-offset-1">
					总价修改：
					<input type="text" class="form-control my-mask-totalMoney"  placeholder="总价修改">
				</div>
			</div>
			
			<div  class="col-sm-offset-1 col-sm-10 col-xs-10 col-xs-offset-1">
				<button class="btn btn-success form-control my-mask-confirm" type="button">确定</button>
			</div>
			
			<div class="col-sm-offset-1 col-sm-10 col-xs-10 col-xs-offset-1" style="margin-top:10px;">
				<button class="btn btn-info form-control my-mask-cancel" type="button">取消</button>
			</div>
			
			<div class="col-sm-offset-1 col-sm-10 col-xs-10 col-xs-offset-1" style="margin-top:10px;">
				<button class="btn btn-warning form-control my-mask-delete" type="button">删除</button>
			</div>
		</form>
		
	</div>
	<div class="my-baby-submitMoney" >
		
	</div>
	<canvas id="canvasSubmit" class="my-canvas-submit" width="400" height="100"></canvas>
    
	<textarea rows="15" id="textarea01" cols="80" style="display:none;"></textarea>
    <script language="javascript" src="./js/LodopFuncs.js"></script>
    <script src="./js/jquery-3.2.1.js"></script>
	<script src="./js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./js/pinyinjs/dict/pinyin_dict_notone.js"></script>
	<script type="text/javascript" src="./js/pinyinjs/dict/pinyin_dict_withtone.js"></script>
	<script type="text/javascript" src="./js/pinyinjs/pinyinUtil.js"></script>
	<script type="text/javascript" src="./js/pinyinjs/simple-input-method/simple-input-method.js"></script>
	<script src="./js/collectStart.js"></script>
  </body>
</html>
