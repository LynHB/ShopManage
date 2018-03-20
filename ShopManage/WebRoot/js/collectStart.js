document.onreadystatechange = completeLoading;
var windowHeight = $(window).height();
var windowWidth = $(window).width();
var _PageHeight = document.documentElement.clientHeight, _PageWidth = document.documentElement.clientWidth;
var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0, _LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2
		: 0;
var _LoadingHtml = '<div id="loadingDiv"'
		+ 'style="position:absolute;left:0;width:100%;height:'
		+ _PageHeight
		+ 'px;top:0;background:white;opacity:0.8;filter:alpha(opacity=80);z-index:10000;">'
		+ '<div style="position: absolute; cursor: wait; left: '
		+ _LoadingLeft
		+ 'px; top:'
		+ _LoadingTop
		+ 'px; width:200px; height: 57px; line-height: 57px; background: #fff url(image/preloader.gif) no-repeat;background-size:100%,100%;border-radius:15px; background-position: 10px -71px;color: #696969; font-family:\'Microsoft YaHei\';"></div></div>';
document.write(_LoadingHtml);
document.onreadystatechange = completeLoading;
function completeLoading() {
	// 文档和所有子资源已完成加载 执行动作
	if (document.readyState == "complete") {
		var windowHeight = $(window).height();
		$("body").css("height", windowHeight);
		$(".my-container").css("height", windowHeight * 0.9 + "px");
		$(".my-header").css("height", windowHeight * 0.05 + "px");

		var loadingMask = document.getElementById('loadingDiv');

		loadingMask.parentNode.removeChild(loadingMask);

	}

}

$(document).ready(
		function() {
			// $(".my-container").eq(0).fadeIn(3000);

			var settlementHeight = $(".my-settlement").eq(0).css("height");
			$(".my-total").eq(0).css("line-height", settlementHeight);
			$(".my-settlement").eq(0).css("line-height", settlementHeight);
			$(".my-baby-submitMoney").eq(0).css("height", (windowWidth*0.2*1.5)+"px");
			$(".my-container").css("display","none");
			// 绘制图形
			$(".my-canvas-1").attr("height", windowHeight * 0.4);
			$(".my-canvas-1").attr("width", windowWidth * 0.4);
			$(".my-canvas-submit").attr("height", windowHeight * 0.4);
			$(".my-canvas-submit").attr("width", windowWidth * 0.4);
			
			var canvasHeight = windowHeight * 0.4;
			var canvasWidth = windowWidth * 0.4
			var canvas = document.getElementById("canvas1");
			var context = canvas.getContext("2d");

			context.strokeStyle = "#F0FFFF";
			context.fillStyle = "#F0FFFF";
			context.lineWidth = "1";
			// 绘制圆
			context.beginPath();
			context.arc(canvasWidth / 16, (canvasHeight - canvasWidth / 16),
					canvasWidth / 32, 0, 2 * Math.PI);
			context.stroke();
			context.fill();

			var canvas2 = document.getElementById("canvas2");
			var context2 = canvas2.getContext("2d");
			context2.strokeStyle = "#F0FFFF";
			context2.fillStyle = "#F0FFFF";
			context2.lineWidth = "1";
			context2.beginPath();
			context2.arc(canvasWidth / 8, (canvasHeight - canvasWidth / 8),
					canvasWidth / 20, 0, 2 * Math.PI);
			context2.stroke();
			context2.fill();

			var canvas3 = document.getElementById("canvas3");
			var context3 = canvas3.getContext("2d");
			context3.strokeStyle = "#F0FFFF";
			context3.fillStyle = "#F0FFFF";
			context3.lineWidth = "1";
			context3.beginPath();
			context3.font = ' 20px consolas';
			context3.textAlign = 'left';
			context3.textBaseline = 'top';
			createRoundRect(context3, canvasWidth / 7, canvasHeight / 8,
					canvasWidth / 2, canvasHeight / 2, 10);
			context3.stroke();
			context3.fill();
			context3.fillStyle = "#000000";
			context3.fillText('春哥好好看店，不要吃鸡啦！', canvasWidth / 7 + 2,
					canvasHeight / 3);
			
			$("#canvas1").fadeIn(2000);
			$("#canvas2").fadeIn(3000);
			$("#canvas3").fadeIn(4000);

			$("#canvas1").fadeOut(2000);
			$("#canvas2").fadeOut(3000);
			$("#canvas3").fadeOut(4000);
			setTimeout(function() {
				$(".my-container").fadeIn(1000);
			}, 7000);
			
			
			var canvasSubmit = document.getElementById("canvasSubmit");
			var contextSubmit = canvasSubmit.getContext("2d");
			contextSubmit.strokeStyle = "#F0FFFF";
			contextSubmit.fillStyle = "#F0FFFF";
			contextSubmit.lineWidth = "1";
			contextSubmit.beginPath();
			contextSubmit.font = ' 20px consolas';
			contextSubmit.textAlign = 'left';
			contextSubmit.textBaseline = 'top';
			createRoundRect(contextSubmit, canvasWidth / 7, canvasHeight / 8,
					canvasWidth / 2, canvasHeight / 3+5, 10);
			contextSubmit.stroke();
			contextSubmit.fill();
			contextSubmit.fillStyle = "#FFBC00";
			contextSubmit.fillText('奶奶叫我盯着你们收钱。', canvasWidth / 6 + 2,
					canvasHeight / 5);
			contextSubmit.fillText('都是本宝宝的奶粉钱！！！', canvasWidth / 6 + 2,
					canvasHeight / 3);
		});

function createRoundRect(context, x1, y1, width, height, radius) {
	// 移动到左上角
	context.moveTo(x1 + radius, y1);
	// 添加一条连接到右上角的线段
	context.lineTo(x1 + width - radius, y1);
	// 添加一段圆弧
	context.arcTo(x1 + width, y1, x1 + width, y1 + radius, radius);
	// 添加一条连接到右下角的线段
	context.lineTo(x1 + width, y1 + height - radius);
	// 添加一段圆弧
	context.arcTo(x1 + width, y1 + height, x1 + width - radius, y1 + height,
			radius);
	// 添加一条连接到左下角的线段
	context.lineTo(x1 + radius, y1 + height);
	// 添加一段圆弧
	context.arcTo(x1, y1 + height, x1, y1 + height - radius, radius);
	// 添加一条连接到左上角的线段
	context.lineTo(x1, y1 + radius);
	// 添加一段圆弧
	context.arcTo(x1, y1, x1 + radius, y1, radius);
	context.closePath();
}

$(".my-member-collect-btn").click(
		function() {
			if ($(".my-phone-id").eq(0).val().replace(/[0-9]/g, "") == ""
					&& $(".my-phone-id").eq(0).val().length == 11) {
				$.ajax({
					type : 'POST',
					url : "CollectStart",
					data : "event=getOneMemberInfo&fresh=" + Math.random()
							+ "&userId=" + $(".my-phone-id").eq(0).val(),
					dataType : "json",
					cache : false,
					success : function(data) {
						var tmpData = eval(data);
						console.log(tmpData);
						if (tmpData["ErrorCode"] == "0") {
							$(".member-id").eq(0).text(tmpData["UserPhoneId"]);
							$(".member-name").eq(0).text(tmpData["UserName"]);
							$(".member-Integral").eq(0).text(
									tmpData["Integral"]);
							$(".member-detail").eq(0).text(tmpData["Detail"]);
							$(".member-info").eq(0).css("background-color",
									"#BFEFFF");
						} else {
							alert(tmpData["ErrorMsg"]);
						}

					},
					error : function(jqXHR, error, errorThrown) {
						alert(jqXHR.status);
					}

				});
			} else {
				alert("请填写正确的手机号码！");
			}
		});

// 获取扫码器事件
var shopId = "";
var nowTime;
var lastTime;
var flag = 0;
$(document).keydown(function(event) {
	if (flag == 0) {
		lastTime = new Date().getTime();
		nowTime = new Date().getTime();
		flag = 1;
	} else {
		nowTime = new Date().getTime();
	}
	if (event.keyCode == 13 && shopId.length == 13) {
		$(".shopId").eq(0).val(shopId);
		shopId = "";
		flag = 0;
		shopAdd($(".shopId").eq(0).val());
	} else if (nowTime - lastTime < 30) {
		shopId += String.fromCharCode(event.keyCode);
	} else {
		shopId = String.fromCharCode(event.keyCode);
		flag = 0;
	}
	lastTime = nowTime;
});

// 重置订单
$(".my-button-2").eq(0).click(function() {
	$(".member-info").eq(0).css("background-color", "white");
	$(".member-id").eq(0).text("暂无");
	$(".member-name").eq(0).text("暂无");
	$(".member-Integral").eq(0).text("暂无");
	$(".member-detail").eq(0).text("暂无");
	$(".my-tbody").eq(0).html("");
	$(".my-phone-id").eq(0).val("");
	$(".my-total").eq(0).text("总计:00.00");
});

var LODOP
// 打开钱箱
$(".my-button-3").eq(0).click(function() {
	LODOP = getLodop();
	if (LODOP.SEND_PRINT_RAWDATA(String.fromCharCode(27, 112, 1, 128, 128))) {
	} else {
		alert("开启钱箱失败");
	}

});

//动态绑定事件
$(document).delegate('.shopTr','click',function(){
	$(".my-mask").eq(0).css("display","block");
	$(".shopDivMask").eq(0).fadeIn(1000);
	var index=$(".shopTr").index($(this));
	$(".my-mask-id").eq(0).val($(".shopTdId").eq(index).text());
	$(".my-mask-name").eq(0).val($(".shopTdName").eq(index).text());
	$(".my-mask-price").eq(0).val($(".shopTdPrice").eq(index).text());
	$(".my-mask-number").eq(0).val($(".shopTdNum").eq(index).text());
});

$(".my-mask-delete").click(function(){
	for(var i=0;i<$(".shopTr").length;i++){
		if($(".shopTdId").eq(i).text()==$(".my-mask-id").eq(0).val()){
			$(".shopTr").eq(i).remove();
			break;
		}
	}
	totalMoneyCalculation();
	$(".my-mask").eq(0).css("display","none");
	$(".shopDivMask").eq(0).css("display","none");
});
$(".my-mask-confirm").click(function(){
	if($(".my-mask-totalMoney").eq(0).val()!=""){
		for(var i=0;i<$(".shopTr").length;i++){
			if($(".shopTdId").eq(i).text()==$(".my-mask-id").eq(0).val()){
				if(parseFloat($(".shopTdTransaction").eq(i).text()).toFixed(2)<50.0){
					$(".shopTr").eq(i).removeClass("success");
				}else if(parseFloat($(".shopTdTransaction").eq(i).text()).toFixed(2)<100.0){
					$(".shopTr").eq(i).removeClass("info");
				}else if(parseFloat($(".shopTdTransaction").eq(i).text()).toFixed(2)<300.0){
					$(".shopTr").eq(i).removeClass("warning");
				}else if(parseFloat($(".shopTdTransaction").eq(i).text()).toFixed(2)>=300.0){
					$(".shopTr").eq(i).removeClass("danger");
				}
				$(".shopTdTransaction").eq(i).text(parseFloat($(".my-mask-totalMoney").eq(0).val()).toFixed(2));
				
				break;
			}
		}
		$(".shopTdDiscount").eq(i).text("一口价");
	}else if($(".my-mask-turnover").eq(0).val()!="" && $(".my-mask-number").eq(0).val()!="" && $(".my-mask-number").eq(0).val().replace(/[0-9]/g, "") == ""){
		for(var i=0;i<$(".shopTr").length;i++){
			if(parseFloat($(".shopTdTransaction").eq(i).text()).toFixed(2)<50.0){
				$(".shopTr").eq(i).removeClass("success");
			}else if(parseFloat($(".shopTdTransaction").eq(i).text()).toFixed(2)<100.0){
				$(".shopTr").eq(i).removeClass("info");
			}else if(parseFloat($(".shopTdTransaction").eq(i).text()).toFixed(2)<300.0){
				$(".shopTr").eq(i).removeClass("warning");
			}else if(parseFloat($(".shopTdTransaction").eq(i).text()).toFixed(2)>=300.0){
				$(".shopTr").eq(i).removeClass("danger");
			}
			if($(".shopTdId").eq(i).text()==$(".my-mask-id").eq(0).val()){
				$(".shopTdNum").eq(i).text($(".my-mask-number").eq(0).val());
				$(".shopTdTurnover").eq(i).text($(".my-mask-turnover").eq(0).val());
				$(".shopTdTransaction").eq(i).text((parseFloat($(".my-mask-turnover").eq(0).val())*parseInt($(".my-mask-number").eq(0).val())).toFixed(2));
				break;
			}
			
		}
		$(".shopTdDiscount").eq(i).text("优惠单价");
	}else{
		
	}
	if(parseFloat($(".shopTdTransaction").eq(i).text()).toFixed(2)<50.0){
		$(".shopTr").eq(i).addClass("success");
	}else if(parseFloat($(".shopTdTransaction").eq(i).text()).toFixed(2)<100.0){
		$(".shopTr").eq(i).addClass("info");
	}else if(parseFloat($(".shopTdTransaction").eq(i).text()).toFixed(2)<300.0){
		$(".shopTr").eq(i).addClass("warning");
	}else if(parseFloat($(".shopTdTransaction").eq(i).text()).toFixed(2)>=300.0){
		$(".shopTr").eq(i).addClass("danger");
	}
	totalMoneyCalculation();
	$(".my-mask").eq(0).css("display","none");
	$(".shopDivMask").eq(0).css("display","none");
});

$(".my-mask").click(function(){
	$(".my-mask").eq(0).css("display","none");
	$(".shopDivMask").eq(0).css("display","none");
});

$(".my-mask-cancel").eq(0).click(function(){
	$(".my-mask").eq(0).fadeOut(1000);
	$(".shopDivMask").eq(0).css("display","none");
	
	
});


// 商品信息ajax返回
function shopAdd(shopId) {
	$.ajax({
		type : 'POST',
		url : "CollectStart",
		data : "event=getOneShopInfo&fresh=" + Math.random() + "&shopId="
				+ shopId,
		dataType : "json",
		cache : false,
		success : function(data) {
			var tmpData = eval(data);
			var flag=1;
			if (tmpData["errorCode"] == 0) {
				for(var i=0;i<$(".shopTr").length;i++){
					if($(".shopTdId").eq(i).text()==tmpData["Item"]["shopId"]){
						flag=0;
						$(".shopTdNum").eq(i).text(parseInt($(".shopTdNum").eq(i).text())+1);
						$(".shopTdTransaction").eq(i).text((parseFloat($(".shopTdTransaction").eq(i).text())+parseFloat($(".shopTdTurnover").eq(i).text())).toFixed(2));
						break;
					}else{
						flag=1;
					}
				}
				
				if(flag==1){	
					var text = "";
					text = "<tr class=\"shopTr " + tmpData["updateTime"]
							+ "\" style=\"display:none;\">";
					text = text + "<td class=\"shopTdId\">"
							+ tmpData["Item"]["shopId"] + "</td>";
					text = text + "<td class=\"shopTdImage\"></td>";
					text = text + "<td class=\"shopTdName\">"
							+ tmpData["Item"]["shopName"] + "</td>";
					text = text + "<td class=\"shopTdPrice\">"
							+ tmpData["Item"]["shopPrice"] + "</td>";
					text = text + "<td class=\"shopTdTurnover\">"+tmpData["Item"]["shopPrice"]+"</td>";
					text = text + "<td class=\"shopTdNum\">1</td>";
					text = text + "<td class=\"shopTdTransaction "
							+ tmpData["updateTime"] + "td\">"
							+ tmpData["Item"]["shopPrice"] + "</td>";
					text = text + "<td class=\"shopTdDiscount\">未折扣</td>";
					text = text + "</tr>";
					$(".my-tbody").eq(0).append(text);
					// 开始渲染
					if (parseFloat($("." + tmpData["updateTime"] + "td").eq(0)
							.text()) > 300.0) {
						$("." + tmpData["updateTime"]).eq(0).addClass("danger");
					} else if (parseFloat($("." + tmpData["updateTime"] + "td").eq(
							0).text()) > 100.0) {
						$("." + tmpData["updateTime"]).eq(0).addClass("warning");
					} else if (parseFloat($("." + tmpData["updateTime"] + "td").eq(
							0).text()) > 50.0) {
						$("." + tmpData["updateTime"]).eq(0).addClass("info");
					} else {
						$("." + tmpData["updateTime"]).eq(0).addClass("success");
					}
					$("." + tmpData["updateTime"]).eq(0).fadeIn(1000);
				}else if(flag==0){
					if (parseFloat($("." + tmpData["updateTime"] + "td").eq(0)
							.text()) >= 300.0) {
						$("." + tmpData["updateTime"]).eq(0).addClass("danger");
					} else if (parseFloat($("." + tmpData["updateTime"] + "td").eq(
							0).text()) >= 100.0) {
						$("." + tmpData["updateTime"]).eq(0).addClass("warning");
					} else if (parseFloat($("." + tmpData["updateTime"] + "td").eq(
							0).text()) >= 50.0) {
						$("." + tmpData["updateTime"]).eq(0).addClass("info");
					} else {
						$("." + tmpData["updateTime"]).eq(0).addClass("success");
					}
				}
				totalMoneyCalculation();
			} else {
				alert(tmpData["errorMsg"]);
			}
		},
		error : function(jqXHR, error, errorThrown) {
			alert(jqXHR.status);
		}

	});
}

//提交按钮
$(".my-settlement").eq(0).click(function(){
	if($(".my-total").eq(0).text()=="总计:00.00" || $(".shopTr").length==0){
		alert("总价为空或无商品，无法提交");
		console.log($(".shopTr").length);
	}else{
		var jsonData=new Object();
		jsonData.totalMoney=$(".my-total").eq(0).text().split(":")[1];
		jsonData.memId=$(".member-id").eq(0).text()!="暂无"?$(".member-id").eq(0).text():"";
		var array=[];
		for(var i=0;i<$(".shopTr").length;i++){
			var tmpObject=new Object();
			tmpObject.id=$(".shopTdId").eq(i).text();
			tmpObject.name=$(".shopTdName").eq(i).text();
			tmpObject.turnover=$(".shopTdTurnover").eq(i).text();
			tmpObject.number=$(".shopTdNum").eq(i).text();
			tmpObject.transaction=$(".shopTdTransaction").eq(i).text();
			tmpObject.discount=$(".shopTdDiscount").eq(i).text();
			array.push(tmpObject);
		}
		jsonData.item=array;
		console.log(jsonData);
		$.ajax({
			type : 'POST',
			url : "CollectStart",
			data :{
					jsonData : JSON.stringify(jsonData), 
					event : "submitShopping", 
					fresh : Math.random()
					},  
			dataType : "json",
			cache : false,
			success : function(data) {
				var tmpData = eval(data);
				console.log(tmpData);
				if(tmpData["errorCode"]==0){
					console.log(tmpData["item"]["FlowWater"]);
					$(".my-baby-submitMoney").css("display","block");
					$(".my-canvas-submit").fadeIn(1000);
					var printText='<div class="print-content" style="height:100%;width:100%;" >'+
									'<div class="print-head" style="text-align:center;">'+
										'<h3 style="text-align:center;padding:0px;margin:0px;">美贝美妈透堡店</h3>'+
										'<p style="padding:0px;margin:0px;">13275995098</p>'+
										'<p style="padding:0px;margin:0px;font-size:12px;">流水单号：'+tmpData["item"]["FlowWater"]+'</p>'+
									'</div>'+
									'<div class="print-body" style="text-align:center;margin-top:5px">'+
										'<hr style=" height:2px;border:none;border-top:2px dotted #185598;" />'+
										'<ul style="padding:0px;margin:0px;font-size:12px;">'+
											'<li style="list-style:none;float:left;width:40%;">商品名/条码</li>'+
											'<li style="list-style:none;float:left;width:20%;">数量</li>'+
											'<li style="list-style:none;float:left;width:20%;">单价</li>'+
											'<li style="list-style:none;float:left;width:20%;">小计</li>'+
										'</ul>';
					var number=0;
					var height=1000;
					for(var i=0;i<$(".shopTr").length;i++){
						height=height+50;
						number+=parseInt($(".shopTdNum").eq(i).text());
						printText=printText+'<ul style="padding:0px;margin:0px;font-size:12px;">'+
												'<li style="list-style:none;text-align:left;">'+$(".shopTdName").eq(i).text()+'</li>'+
											'</ul>';
						printText=printText+'<ul style="padding:0px;margin:0px;font-size:12px;">'+
												'<li style="list-style:none;float:left;width:40%;">'+$(".shopTdId").eq(i).text()+'</li>'+
												'<li style="list-style:none;float:left;width:20%;">'+$(".shopTdNum").eq(i).text()+'</li>'+
												'<li style="list-style:none;float:left;width:20%;">'+$(".shopTdTurnover").eq(i).text()+'</li>'+
												'<li style="list-style:none;float:left;width:20%;">'+$(".shopTdTransaction").eq(i).text()+'</li>'+
											'</ul>'+
											'<div style="clear:both">'+'</div>';
						}
					printText=printText+'<hr style=" height:2px;border:none;border-top:2px dotted #185598;" />'+
										'<ul style="padding:0px;margin:2px;font-size:12px;">'+
											'<li style="list-style:none;float:left;width:40%;text-align:left;">合计：</li>'+
											'<li style="list-style:none;float:left;width:20%;">'+number+'</li>'+
											'<li style="list-style:none;float:right;width:20%;text-align:center">'+($(".my-total").eq(0).text().split(":"))[1]+'</li>'+
										'</ul>'+
										'<div style="clear:both">'+'</div>'+
										'<ul style="padding:0px;margin:0pxmargin-top:10px;font-size:12px;">'+
											'<li style="list-style:none;float:left;width:60%;text-align:left;">会员:'+$(".member-id").eq(0).text()+'</li>'+
											'<li style="list-style:none;float:right;width:40%;text-align:right">姓名:'+$(".member-name").eq(0).text()+'</li>'+
										'</ul>'+
										'<ul style="padding:0px;margin:2px;font-size:12px;">'+
											'<li style="list-style:none;float:left;width:50%;text-align:left;">本次积分:'+tmpData["item"]["integral"]+'</li>'+
											'<li style="list-style:none;float:left;width:50%;text-align:right">剩余积分:'+tmpData["item"]["integralTotal"]+'</li>'+
										'</ul>'+
										'<div style="clear:both">'+'</div>'+
										'<div style="padding:0px;margin:0px;margin-top:10px;margin-bottom:10px;font-size:12px;text-align:left;font-weight:bold;" >温馨提示：保存好您的小票，维护消费者权利。^_^</div>'+
										'<ul style="padding:0px;margin:2px;font-size:12px;">'+
											'<li style="list-style:none;float:left;width:100%;text-align:left;">时间：'+new Date(parseInt((new Date()).valueOf()) * 1).toLocaleString().replace(/:\d{1,2}$/,' ')+'</li>'+
										'</ul>'+
										'<div style="padding:0px;margin:2px;font-size:12px;text-align:center;">谢谢惠顾</div>'+
										'<div style="padding:0px;margin:2px;font-size:12px;text-align:center;">地址:连江县透堡政好家园超市旁</div>'+
									'</div>'+
								'</div>';
					$("#textarea01").eq(0).val(printText);
					$("#textarea01").eq(0).html(printText);
					myAddHtml(height);
					LODOP.PRINT();
					setTimeout(function() {
						$(".my-baby-submitMoney").fadeOut(1000);
						$(".my-canvas-submit").css("display","none");
						$(".my-button-2").eq(0).click();
					}, 3000);
					
				}else{
					alert("数据库访问异常，或者商品信息不存在");
				}
			},
			error : function(jqXHR, error, errorThrown) {
				alert(jqXHR.status);
			}

		});
		
	}
});


// 商品编号确认按钮事件
$(".my-shoip-collect-btn").eq(0).click(function() {
	shopAdd($(".shopId").eq(0).val());
});

//总价计算
function totalMoneyCalculation(){
	var totalMoney=0.0;
	for(var i=0;i<$(".shopTr").length;i++){
		totalMoney=(totalMoney+parseFloat($(".shopTdTransaction").eq(i).text()));
	}
	$(".my-total").eq(0).text("总计:"+totalMoney.toFixed(2));	
	
}


//打印函数
function myAddHtml(height){
	LODOP=getLodop();         
		LODOP.PRINT_INIT("");	
	LODOP.SET_PRINT_PAGESIZE(0,"580",height,"A4");		
	LODOP.ADD_PRINT_HTM(0,3,"100%","100%",document.getElementById("textarea01").value);	       

}