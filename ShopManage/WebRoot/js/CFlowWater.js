var windowHeight=$(window).height();
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
	//文档和所有子资源已完成加载 执行动作
	    if (document.readyState == "complete") {
	    	var windowHeight=document.body.clientHeight ;
	    	$("body").css("height",windowHeight);
	    	var loadingMask = document.getElementById('loadingDiv');
			loadingMask.parentNode.removeChild(loadingMask);
	    	$(".my-container").css("height",windowHeight*0.9+"px");
	    	$(".my-header").css("height",windowHeight*0.05+"px");
	    }

	}



$(document).ready(function(){
	$(".my-container").eq(0).fadeIn(3000);
});
var allDataArray=new Array;
var validData=new Array;
$.ajax({
	type : 'POST',
	url : "CFlowingWater",
	data : "event=getOneMonthData&fresh=" + Math.random(),
	dataType : "json",
	cache : false,
	success : function(data) {
		var tmpData = eval(data);
		console.log(tmpData);
		if (tmpData["errorCode"] == "0") {
			allDataArray=tmpData["Item"];
			validData=tmpData["Item"];
			tableDataToTbody(tmpData["Item"],$(".my-tbody").eq(0));
		} else {
			alert(tmpData["errorMsg"]);
		}

	},
	error : function(jqXHR, error, errorThrown) {
		alert(jqXHR.status);
	}

});

$(".ThFlowWaterId").eq(0).click(function(){
	$(".my-mask").eq(0).css("display","block");
	$(".shopDivMask").eq(0).fadeIn(1000);	
})

$(".my-mask-reset").eq(0).click(function(){
	$("input").val("");
});

$(".my-mask-cancel").eq(0).click(function(){
	$(".my-mask").eq(0).css("display","none");
	$(".shopDivMask").eq(0).fadeOut(1000);	
});

$(".my-mask-confirm").eq(0).click(function(){
	validData=new Array();
	var tmpData=new Array();
	$(".my-mask").eq(0).fadeOut(1000);
	$(".shopDivMask").eq(0).css("display","none");
	
	//流水id过滤
	for(var i=0;i<allDataArray.length;i++){
		if($(".my-mask-flowWaterId").eq(0).val()!="" && $(".my-mask-flowWaterId").eq(0).val()!="undefined"){
			if(allDataArray[i]["flowWaterId"].indexOf($(".my-mask-flowWaterId").eq(0).val())>=0){
				validData.push(allDataArray[i]);
			}
		}else{
			validData.push(allDataArray[i]);
		}
	}
	//会员名字过滤
	for(var i=0;i<validData.length;i++){
		if($(".my-mask-userName").eq(0).val()!="" && $(".my-mask-userName").eq(0).val()!="undefined"){
			var namePin = pinyinUtil.getPinyin(validData[i]["userName"], ' ', false, "");
			var namePin2 = pinyinUtil.getFirstLetter(validData[i]["userName"],"");
			if(validData[i]["userName"].indexOf($(".my-mask-userName").eq(0).val())>=0||namePin.indexOf($(".my-mask-userName").eq(0).val())>=0||namePin2.indexOf($(".my-mask-userName").eq(0).val().toUpperCase())>=0){
				tmpData.push(validData[i]);
				
			}
		}else{
			tmpData.push(validData[i]);
		}
	}
	validData=tmpData;
	tmpData=[];
	//商品编号
	for(var i=0;i<validData.length;i++){
		if($(".my-mask-shopId").eq(0).val()!="" && $(".my-mask-shopId").eq(0).val()!="undefined"){
			var brandPin = pinyinUtil.getPinyin(validData[i]["shopId"], ' ', false, "");
			var brandPin2 = pinyinUtil.getFirstLetter(validData[i]["shopId"],"");
			if(validData[i]["shopId"].indexOf($(".my-mask-shopId").eq(0).val())>=0||brandPin.indexOf($(".my-mask-shopId").eq(0).val())>=0||brandPin2.indexOf($(".my-mask-shopId").eq(0).val().toUpperCase())>=0){
				tmpData.push(validData[i]);
				
			}
		}else{
			tmpData.push(validData[i]);
		}
	}
	validData=tmpData;
	tmpData=[];
	
	//商品名称
	for(var i=0;i<validData.length;i++){
		var detailPin = pinyinUtil.getPinyin(validData[i]["shopName"], ' ', false, "");
		var detailPin2 = pinyinUtil.getFirstLetter(validData[i]["shopName"],"");
		if($(".my-mask-ShopName").eq(0).val()!="" && $(".my-mask-ShopName").eq(0).val()!="undefined"){
			if(validData[i]["shopName"].indexOf($(".my-mask-ShopName").eq(0).val())>=0||detailPin.indexOf($(".my-mask-ShopName").eq(0).val())>=0||detailPin2.indexOf($(".my-mask-ShopName").eq(0).val().toUpperCase())>=0){
				tmpData.push(validData[i]);
				
			}
		}else{
			tmpData.push(validData[i]);
		}
	}
	validData=tmpData;
	tmpData=[];
	
	//公司厂商
	for(var i=0;i<validData.length;i++){
		var detailPin = pinyinUtil.getPinyin(validData[i]["brand"], ' ', false, "");
		var detailPin2 = pinyinUtil.getFirstLetter(validData[i]["brand"],"");
		if($(".my-mask-brand").eq(0).val()!="" && $(".my-mask-brand").eq(0).val()!="undefined"){
			if(validData[i]["brand"].indexOf($(".my-mask-brand").eq(0).val())>=0||detailPin.indexOf($(".my-mask-brand").eq(0).val())>=0||detailPin2.indexOf($(".my-mask-brand").eq(0).val().toUpperCase())>=0){
				tmpData.push(validData[i]);
				
			}
		}else{
			tmpData.push(validData[i]);
		}
	}
	validData=tmpData;
	tmpData=[];

	//会员手机号
	for(var i=0;i<validData.length;i++){
		if($(".my-mask-phoneId").eq(0).val()!="" && $(".my-mask-phoneId").eq(0).val()!="undefined"){
			if(validData[i]["userId"].indexOf($(".my-mask-phoneId").eq(0).val())>=0){
				tmpData.push(validData[i]);
				
			}
		}else{
			tmpData.push(validData[i]);
		}
	}
	validData=tmpData;
	tmpData=[];
	
	for(var i=0;i<validData.length;i++){
		if($(".my-mask-beginTime").eq(0).val()!="" && $(".my-mask-beginTime").eq(0).val()!="undefined" || $(".my-mask-endTime").eq(0).val()!="" && $(".my-mask-endTime").eq(0).val()!="undefined" ){
			var nowTime=validData[i]["createTime"];
			var beginTime=new Date($(".my-mask-beginTime").eq(0).val()).getTime()-28800000;
			var endTime=new Date($(".my-mask-endTime").eq(0).val()).getTime()-28800000;
			console.log(beginTime);
			console.log(nowTime);
			console.log(endTime);
			if($(".my-mask-beginTime").eq(0).val()!="" && $(".my-mask-beginTime").eq(0).val()!="undefined" && $(".my-mask-endTime").eq(0).val()!="" && $(".my-mask-endTime").eq(0).val()!="undefined" ){
				if(beginTime<nowTime&&(endTime+86400000)>nowTime){
					tmpData.push(validData[i]);
				}
				
			}else if($(".my-mask-beginTime").eq(0).val()!="" && $(".my-mask-beginTime").eq(0).val()!="undefined"){
				if(beginTime<nowTime){
					tmpData.push(validData[i]);
				}
			}else{
				if((endTime+86400000)>nowTime){
					tmpData.push(validData[i]);
				}
			}
		}else{
			tmpData.push(validData[i]);
		}
	}
	validData=tmpData;
	tmpData=[];
	tableDataToTbody(validData,$("tbody").eq(0))
});



//动态绑定事件
$(document).delegate(".flowWaterType","click",function(){
	$(".my-mask").eq(0).css("display","block");
	$(".updateFlowWaterDiv").eq(0).fadeIn(1000);
	var index=$(".flowWaterType").index($(this));
	$(".updateFlowWater-id").eq(0).val($(".flowWaterId").eq(index).text());
	$(".updateFlowWater-time").eq(0).val($(".createTime").eq(index).text());
	$(".updateFlowWater-userName").eq(0).val($(".userName").eq(index).text());
	$(".updateFlowWater-userId").eq(0).val($(".userId").eq(index).text());
	$(".updateFlowWater-shopName").eq(0).val($(".shopName").eq(index).text());
	$(".updateFlowWater-shopNum").eq(0).val($(".shopNumber").eq(index).text());
	$(".updateFlowWaterDiv").attr("shopId",$(".shopId").eq(index).text());
	$(".updateFlowWaterDiv").attr("shopType",$(".flowWaterType").eq(index).text());
		
});

$(".update-cancel").click(function(){
	$(".my-mask").eq(0).css("display","none");
	$(".updateFlowWaterDiv").eq(0).fadeOut(1000);
})

$(".update-cancelOrder").click(function(){
	if($(".updateFlowWaterDiv").attr("shopType")=="销售单"){
	$(".my-mask").eq(0).css("display","none");
	$(".updateFlowWaterDiv").eq(0).fadeOut(1000);
	$.ajax({
		type : 'POST',
		url : "CFlowingWater",
		data : "event=cancelOneFlowWater&fresh=" + Math.random()+"&flowWaterId="+$(".updateFlowWater-id").eq(0).val()+"&shopId="+$(".updateFlowWaterDiv").attr("shopId")+"&shopNum="+$(".updateFlowWater-shopNum").eq(0).val(),
		dataType : "json",
		cache : false,
		success : function(data) {
			var tmpData = eval(data);
			console.log(tmpData);
			if (tmpData["errorCode"] == "0") {
				allDataArray=tmpData["Item"];
				validData=tmpData["Item"];
				tableDataToTbody(tmpData["Item"],$(".my-tbody").eq(0));
			} else {
				alert(tmpData["errorMsg"]);
			}

		},
		error : function(jqXHR, error, errorThrown) {
			alert(jqXHR.status);
		}

	});
	
	}else{
		$(".my-mask").eq(0).css("display","none");
		$(".updateFlowWaterDiv").eq(0).fadeOut(1000);
		alert("非销售单，无法操作");
	}
});
function tableDataToTbody(data,classLabel){
	var text="";
	var totalMoney=0;
	var totalNumber=0;
	var totalProfit=0;
	var flowWaterTypeTotalZ=0;
	var flowWaterTypeTotalO=0;
	var shopMaxNum=0;
	var shopMaxNumName="";
	var shopMaxNumProfit="";
	var shopMaxProfit=0;
	var shopMaxProfitName="";
	var shopMaxProfitNumber="";
	var tmpNum={};
	var tmpProfit=new Array();
	for(var i=0;i<data.length;i++){
		
		
		var type;
		if(parseFloat(data[i]["profit"])>=100){
			type="warning";
		}else if(parseFloat(data[i]["profit"])>=50){
			type="info";
		}else if(parseFloat(data[i]["profit"])>=0){
			type="success";
		}else if(parseFloat(data[i]["profit"])<0){
			type="danger";
		}
		
		var flowWaterType="";
		if(data[i]["flowWaterType"]=="0"){
			flowWaterType="销售单";
			flowWaterTypeTotalZ++;
			if(tmpNum[data[i]["shopName"]]!== null && tmpNum[data[i]["shopName"]] !== undefined && tmpNum[data[i]["shopName"]] !== ''){
				tmpNum[data[i]["shopName"]]+=parseInt(data[i]["shopNumber"]);
			}else{
				tmpNum[data[i]["shopName"]]=parseInt(data[i]["shopNumber"]);
			}
			
			if(tmpProfit[data[i]["shopName"]]!== null && tmpProfit[data[i]["shopName"]] !== undefined && tmpProfit[data[i]["shopName"]] !== ''){
				tmpProfit[data[i]["shopName"]]+=parseFloat(data[i]["profit"]);
			}else{
				tmpProfit[data[i]["shopName"]]=parseFloat(data[i]["profit"]);
			}
		}else if(data[i]["flowWaterType"]=="1"){
			flowWaterType="退货单";
			flowWaterTypeTotalO++;
			if(tmpNum[data[i]["shopName"]]!== null && tmpNum[data[i]["shopName"]] !== undefined && tmpNum[data[i]["shopName"]] !== ''){
				tmpNum[data[i]["shopName"]]=parseInt(tmpNum[data[i]["shopName"]])-parseInt(data[i]["shopNumber"]);
			}else{
				tmpNum[data[i]["shopName"]]=parseInt("-"+data[i]["shopNumber"]);
			}
			
			if(tmpProfit[data[i]["shopName"]]!== null && tmpProfit[data[i]["shopName"]] !== undefined && tmpProfit[data[i]["shopName"]] !== ''){
				tmpProfit[data[i]["shopName"]]=parseFloat(tmpProfit[data[i]["shopName"]])+parseFloat(data[i]["profit"]);
			}else{
				tmpProfit[data[i]["shopName"]]=parseFloat(data[i]["profit"]);
				console.log(tmpProfit[data[i]["shopName"]]);
			}
		}
		
		text=text+"<tr class=\"flowWaterData "+type+"\">";
		text=text+"<td class=\"flowWaterId\">"+data[i]["flowWaterId"]+"</td>";
		text=text+"<td class=\"createTime\">"+ new Date(parseInt(data[i]["createTime"]) * 1).toLocaleString().replace(/:\d{1,2}$/,' ')+"</td>";
		text=text+"<td class=\"shopId\">"+data[i]["shopId"]+"</td>";
		text=text+"<td class=\"shopName\">"+data[i]["shopName"]+"</td>";
		text=text+"<td class=\"specifications\">"+data[i]["specifications"]+"</td>";
		text=text+"<td class=\"brand\">"+data[i]["brand"]+"</td>";
		text=text+"<td class=\"classification\">"+data[i]["classification"]+"</td>";
		text=text+"<td class=\"price\">"+data[i]["price"]+"</td>";
		text=text+"<td class=\"turnover\">"+data[i]["turnover"]+"</td>";
		text=text+"<td class=\"discountType\">"+data[i]["discountType"]+"</td>";
		text=text+"<td class=\"shopNumber\">"+data[i]["shopNumber"]+"</td>";
		text=text+"<td class=\"shopTransaction\">"+data[i]["shopTransaction"]+"</td>";
		text=text+"<td class=\"integral\">"+data[i]["integral"]+"</td>";
		text=text+"<td class=\"userId\">"+data[i]["userId"]+"</td>";
		text=text+"<td class=\"userName\">"+data[i]["userName"]+"</td>";
		text=text+"<td class=\"flowWaterType\">"+flowWaterType+"</td>";
		text=text+"<td class=\"cost\">"+data[i]["cost"]+"</td>";
		text=text+"<td class=\"profit\">"+data[i]["profit"]+"</td>";
		text=text+"</tr>";
		totalMoney+=parseFloat(data[i]["shopTransaction"]);
		totalNumber+=parseInt(data[i]["shopNumber"]);
		totalProfit+=parseFloat(data[i]["profit"]);
	}
	
	for(var key in tmpNum){
		if(tmpNum[key]>shopMaxNum){
			shopMaxNum=tmpNum[key];
			shopMaxNumName=key;
		}
	}

	for(var key in tmpProfit){
		if(tmpProfit[key]>shopMaxProfit){
			shopMaxProfit=tmpProfit[key];
			shopMaxProfitName=key;
		}
	}
	text=text+"<tr>";
	text=text+"<td>销售总金额</td>";
	text=text+"<td>"+totalMoney+"</td>";
	text=text+"<td>卖出商品数</td>";
	text=text+"<td>"+totalNumber+"</td>";
	text=text+"<td>卖出数量最多商品</td>";
	text=text+"<td>"+shopMaxNumName+"</td>";
	text=text+"<td>卖出数量</td>";
	text=text+"<td>"+shopMaxNum+"</td>";
	text=text+"<td>盈利最多商品</td>";
	text=text+"<td>"+shopMaxProfitName+"</td>";
	text=text+"<td>盈利总额</td>";
	text=text+"<td>"+parseFloat(shopMaxProfit).toFixed(2)+"</td>";
	text=text+"<td>销售单</td>";
	text=text+"<td>"+flowWaterTypeTotalZ+"</td>";
	text=text+"<td>退货单</td>";
	text=text+"<td>"+flowWaterTypeTotalO+"</td>";
	text=text+"<td>盈利总金额</td>";
	text=text+"<td>"+parseFloat(totalProfit).toFixed(2)+"</td>";
	text=text+"</tr>";
	classLabel.html(text);
}