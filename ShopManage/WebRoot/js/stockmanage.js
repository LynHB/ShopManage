document.onreadystatechange = completeLoading;
var windowHeight=$(window).height();

function completeLoading() {
	//文档和所有子资源已完成加载 执行动作
	    if (document.readyState == "complete") {
	    	var windowHeight=$(window).height();
	    	$("body").css("height",windowHeight);
	    	$(".my-container").css("height",windowHeight*0.8+"px");
	    	$(".my-header").css("height",windowHeight*0.1+"px");
	    }

	}



$(document).ready(function(){
	$(".my-container").eq(0).fadeIn(3000);
});


//渲染商品信息
function shopRendering(){
	 for(var i=0;i<$(".shopTrData").length;i++){
			var value= ($(".shopTdPrice").eq(i).text()-$(".shopTdCost").eq(i).text())/$(".shopTdCost").eq(i).text()
		  	if(value<=0.2){
		  		$(".shopTrData").eq(i).addClass("danger");
		  	}else if(value<=0.4){
		  		$(".shopTrData").eq(i).addClass("success");
		  	}else if(value<=0.6){
		  		$(".shopTrData").eq(i).addClass("info");
		  	}else{
		  		$(".shopTrData").eq(i).addClass("danger");
		  	}
		  }
}


$(".my-a").click(function(){
	var index=$(".my-a").index($(this));
	for(var i=0;i<$(".my-a").length;i++){
		$(".my-a").eq(i).removeClass("my-a-active");
		$(".my-mainbody").eq(i).css("display","none");
	}
	$(".my-a").eq(index).attr("class",$(".my-a").eq(index).attr("class")+" my-a-active");
	$(".my-mainbody").eq(index).fadeIn(1000);
});


//ajax获取分类信息
$.ajax({
	  type: 'POST',
	  url: "CStockManage",
	  data: "event=getClassification&fresh=" + Math.random(),
	  //dataType : "json",  
	  cache:false, 
	  success: function(data){
		  var arrayData=eval(data);
		  var text="";
		  for(var i=0;i<arrayData.length;i++){
			  text=text+"<option value=\""+arrayData[i]["id"]+"\">";
			  text=text+arrayData[i]["name"]+"</option>";
		  }
		  
		  $(".my-select").append(text);
		 
    },
    error: function(jqXHR, error, errorThrown) {
        alert(jqXHR.status);          
    }
	  
});

//ajax获取所有商品信息
var shopData="";
var validData=""; 
$.ajax({
	  type: 'POST',
	  url: "CStockManage",
	  data: "event=getAllShopping&fresh=" + Math.random(),
	  //dataType : "json",  
	  cache:false, 
	  success: function(data){
		  shopData=eval(data);
		  var text="";
		  for(var i=0;i<shopData.length;i++){
			 var tmp=Math.round((shopData[i]["marketingPrice"]-shopData[i]["averageCost"])/shopData[i]["averageCost"]*100);
			 text=text+"<tr class=\"shopTrData\">";
			 text=text+"<td class=\"shopTdId\">"+shopData[i]["cid"]+"</td>";
			 text=text+"<td class=\"shopTdName\">"+shopData[i]["name"]+"</td>";
			 text=text+"<td class=\"shopTdBalance\">"+shopData[i]["stockBalance"]+"</td>";
			 text=text+"<td class=\"shopTdSell\">"+shopData[i]["stockSell"]+"</td>";
			 text=text+"<td class=\"shopTdCost\">"+shopData[i]["averageCost"]+"</td>";
			 text=text+"<td class=\"shopTdPrice\">"+shopData[i]["marketingPrice"]+"</td>";
			 text=text+"<td class=\"shopTdRate\">"+Math.round((shopData[i]["marketingPrice"]-shopData[i]["averageCost"])/shopData[i]["averageCost"]*100)+"%</td>";
			 text=text+"<td class=\"shopTdClass\">"+shopData[i]["classification"]+"</td>";
			 text=text+"<td class=\"shopTdSpeci\">"+shopData[i]["specifications"]+"</td>";
			 text=text+"<td class=\"shopTdBrand\">"+shopData[i]["brand"]+"</td>";
			 text=text+"<td class=\"shopTdDetail\">"+shopData[i]["detail"]+"</td>";
			 text=text+"</tr>";
			 shopData[i]["rate"]=tmp;
		  }
		  validData=shopData;
		  originData=shopData;
		  $(".my-tbody").eq(0).append(text);
		//对表格进行渲染
		  shopRendering();
		 
  },
  error: function(jqXHR, error, errorThrown) {
      alert(jqXHR.status);          
  }
	  
});


$(document).on("click",".shopThId",function(){
	$(".my-mask").eq(0).css("display","block");
	$(".shopDivMask").eq(0).fadeIn(1000);	
});

$(document).on("click",".shopThBalance",function(){
	if($(".shopThBalance").css("background-color")=="rgba(0, 0, 0, 0)"){
		$(".shopThBalance").css("background-color","green");
		var text="";
		for(var i=0;i<validData.length;i++){
			for(var j=i;j<validData.length;j++){
				if(validData[i]["stockBalance"]<validData[j]["stockBalance"]){
					var temp=validData[i];
					validData[i]=validData[j];
					validData[j]=temp;
				}
			}
		}
		for(var i=0;i<validData.length;i++){
			 text=text+"<tr class=\"shopTrData\">";
			 text=text+"<td class=\"shopTdId\">"+validData[i]["cid"]+"</td>";
			 text=text+"<td class=\"shopTdName\">"+validData[i]["name"]+"</td>";
			 text=text+"<td class=\"shopTdBalance\">"+validData[i]["stockBalance"]+"</td>";
			 text=text+"<td class=\"shopTdSell\">"+validData[i]["stockSell"]+"</td>";
			 text=text+"<td class=\"shopTdCost\">"+validData[i]["averageCost"]+"</td>";
			 text=text+"<td class=\"shopTdPrice\">"+validData[i]["marketingPrice"]+"</td>";
			 text=text+"<td class=\"shopTdRate\">"+Math.round((validData[i]["marketingPrice"]-validData[i]["averageCost"])/validData[i]["averageCost"]*100)+"%</td>";
			 text=text+"<td class=\"shopTdClass\">"+validData[i]["classification"]+"</td>";
			 text=text+"<td class=\"shopTdSpeci\">"+validData[i]["specifications"]+"</td>";
			 text=text+"<td class=\"shopTdBrand\">"+validData[i]["brand"]+"</td>";
			 text=text+"<td class=\"shopTdDetail\">"+validData[i]["detail"]+"</td>";
			 text=text+"</tr>";
		  }
	}else if($(".shopThBalance").css("background-color")=="rgb(0, 128, 0)"){
		$(".shopThBalance").css("background-color","red");
		var text="";
		for(var i=0;i<validData.length;i++){
			for(var j=i;j<validData.length;j++){
				if(validData[i]["stockBalance"]>validData[j]["stockBalance"]){
					var temp=validData[i];
					validData[i]=validData[j];
					validData[j]=temp;
				}
			}
		}
		for(var i=0;i<validData.length;i++){
			 text=text+"<tr class=\"shopTrData\">";
			 text=text+"<td class=\"shopTdId\">"+validData[i]["cid"]+"</td>";
			 text=text+"<td class=\"shopTdName\">"+validData[i]["name"]+"</td>";
			 text=text+"<td class=\"shopTdBalance\">"+validData[i]["stockBalance"]+"</td>";
			 text=text+"<td class=\"shopTdSell\">"+validData[i]["stockSell"]+"</td>";
			 text=text+"<td class=\"shopTdCost\">"+validData[i]["averageCost"]+"</td>";
			 text=text+"<td class=\"shopTdPrice\">"+validData[i]["marketingPrice"]+"</td>";
			 text=text+"<td class=\"shopTdRate\">"+Math.round((validData[i]["marketingPrice"]-validData[i]["averageCost"])/validData[i]["averageCost"]*100)+"%</td>";
			 text=text+"<td class=\"shopTdClass\">"+validData[i]["classification"]+"</td>";
			 text=text+"<td class=\"shopTdSpeci\">"+validData[i]["specifications"]+"</td>";
			 text=text+"<td class=\"shopTdBrand\">"+validData[i]["brand"]+"</td>";
			 text=text+"<td class=\"shopTdDetail\">"+validData[i]["detail"]+"</td>";
			 text=text+"</tr>";
		  }
	}else{
		var text="";
		$(".shopThBalance").css("background-color","rgba(0, 0, 0, 0)");
		for(var i=0;i<originData.length;i++){
			 text=text+"<tr class=\"shopTrData\">";
			 text=text+"<td class=\"shopTdId\">"+originData[i]["cid"]+"</td>";
			 text=text+"<td class=\"shopTdName\">"+originData[i]["name"]+"</td>";
			 text=text+"<td class=\"shopTdBalance\">"+originData[i]["stockBalance"]+"</td>";
			 text=text+"<td class=\"shopTdSell\">"+originData[i]["stockSell"]+"</td>";
			 text=text+"<td class=\"shopTdCost\">"+originData[i]["averageCost"]+"</td>";
			 text=text+"<td class=\"shopTdPrice\">"+originData[i]["marketingPrice"]+"</td>";
			 text=text+"<td class=\"shopTdRate\">"+Math.round((originData[i]["marketingPrice"]-originData[i]["averageCost"])/originData[i]["averageCost"]*100)+"%</td>";
			 text=text+"<td class=\"shopTdClass\">"+originData[i]["classification"]+"</td>";
			 text=text+"<td class=\"shopTdSpeci\">"+originData[i]["specifications"]+"</td>";
			 text=text+"<td class=\"shopTdBrand\">"+originData[i]["brand"]+"</td>";
			 text=text+"<td class=\"shopTdDetail\">"+originData[i]["detail"]+"</td>";
			 text=text+"</tr>";
		  }
	}
	console.log($(".shopThBalance").css("background-color"));
	$(".my-tbody").eq(0).html(text);
	//对表格进行渲染
	shopRendering();
	
	
});

$(document).on("click",".shopThRate",function(){
	if($(".shopThRate").css("background-color")=="rgba(0, 0, 0, 0)"){
		$(".shopThRate").css("background-color","green");
		var text="";
		for(var i=0;i<validData.length;i++){
			for(var j=i;j<validData.length;j++){
				if(validData[i]["rate"]<validData[j]["rate"]){
					var temp=validData[i];
					validData[i]=validData[j];
					validData[j]=temp;
				}
			}
		}
		for(var i=0;i<validData.length;i++){
			 text=text+"<tr class=\"shopTrData\">";
			 text=text+"<td class=\"shopTdId\">"+validData[i]["cid"]+"</td>";
			 text=text+"<td class=\"shopTdName\">"+validData[i]["name"]+"</td>";
			 text=text+"<td class=\"shopTdBalance\">"+validData[i]["stockBalance"]+"</td>";
			 text=text+"<td class=\"shopTdSell\">"+validData[i]["stockSell"]+"</td>";
			 text=text+"<td class=\"shopTdCost\">"+validData[i]["averageCost"]+"</td>";
			 text=text+"<td class=\"shopTdPrice\">"+validData[i]["marketingPrice"]+"</td>";
			 text=text+"<td class=\"shopTdRate\">"+Math.round((validData[i]["marketingPrice"]-validData[i]["averageCost"])/validData[i]["averageCost"]*100)+"%</td>";
			 text=text+"<td class=\"shopTdClass\">"+validData[i]["classification"]+"</td>";
			 text=text+"<td class=\"shopTdSpeci\">"+validData[i]["specifications"]+"</td>";
			 text=text+"<td class=\"shopTdBrand\">"+validData[i]["brand"]+"</td>";
			 text=text+"<td class=\"shopTdDetail\">"+validData[i]["detail"]+"</td>";
			 text=text+"</tr>";
		  }
	}else if($(".shopThRate").css("background-color")=="rgb(0, 128, 0)"){
		$(".shopThRate").css("background-color","red");
		var text="";
		for(var i=0;i<validData.length;i++){
			for(var j=i;j<validData.length;j++){
				if(validData[i]["rate"]>validData[j]["rate"]){
					var temp=validData[i];
					validData[i]=validData[j];
					validData[j]=temp;
				}
			}
		}
		for(var i=0;i<validData.length;i++){
			 text=text+"<tr class=\"shopTrData\">";
			 text=text+"<td class=\"shopTdId\">"+validData[i]["cid"]+"</td>";
			 text=text+"<td class=\"shopTdName\">"+validData[i]["name"]+"</td>";
			 text=text+"<td class=\"shopTdBalance\">"+validData[i]["stockBalance"]+"</td>";
			 text=text+"<td class=\"shopTdSell\">"+validData[i]["stockSell"]+"</td>";
			 text=text+"<td class=\"shopTdCost\">"+validData[i]["averageCost"]+"</td>";
			 text=text+"<td class=\"shopTdPrice\">"+validData[i]["marketingPrice"]+"</td>";
			 text=text+"<td class=\"shopTdRate\">"+Math.round((validData[i]["marketingPrice"]-validData[i]["averageCost"])/validData[i]["averageCost"]*100)+"%</td>";
			 text=text+"<td class=\"shopTdClass\">"+validData[i]["classification"]+"</td>";
			 text=text+"<td class=\"shopTdSpeci\">"+validData[i]["specifications"]+"</td>";
			 text=text+"<td class=\"shopTdBrand\">"+validData[i]["brand"]+"</td>";
			 text=text+"<td class=\"shopTdDetail\">"+validData[i]["detail"]+"</td>";
			 text=text+"</tr>";
		  }
	}else{
		var text="";
		$(".shopThRate").css("background-color","rgba(0, 0, 0, 0)");
		for(var i=0;i<originData.length;i++){
			 text=text+"<tr class=\"shopTrData\">";
			 text=text+"<td class=\"shopTdId\">"+originData[i]["cid"]+"</td>";
			 text=text+"<td class=\"shopTdName\">"+originData[i]["name"]+"</td>";
			 text=text+"<td class=\"shopTdBalance\">"+originData[i]["stockBalance"]+"</td>";
			 text=text+"<td class=\"shopTdSell\">"+originData[i]["stockSell"]+"</td>";
			 text=text+"<td class=\"shopTdCost\">"+originData[i]["averageCost"]+"</td>";
			 text=text+"<td class=\"shopTdPrice\">"+originData[i]["marketingPrice"]+"</td>";
			 text=text+"<td class=\"shopTdRate\">"+Math.round((originData[i]["marketingPrice"]-originData[i]["averageCost"])/originData[i]["averageCost"]*100)+"%</td>";
			 text=text+"<td class=\"shopTdClass\">"+originData[i]["classification"]+"</td>";
			 text=text+"<td class=\"shopTdSpeci\">"+originData[i]["specifications"]+"</td>";
			 text=text+"<td class=\"shopTdBrand\">"+originData[i]["brand"]+"</td>";
			 text=text+"<td class=\"shopTdDetail\">"+originData[i]["detail"]+"</td>";
			 text=text+"</tr>";
		  }
	}
	console.log($(".shopThRate").css("background-color"));
	$(".my-tbody").eq(0).html(text);
	//对表格进行渲染
	shopRendering();
	
	
});

$(document).on("click",".shopThSell",function(){
	if($(".shopThSell").css("background-color")=="rgba(0, 0, 0, 0)"){
		$(".shopThSell").css("background-color","green");
		var text="";
		for(var i=0;i<validData.length;i++){
			for(var j=i;j<validData.length;j++){
				if(validData[i]["stockSell"]<validData[j]["stockSell"]){
					var temp=validData[i];
					validData[i]=validData[j];
					validData[j]=temp;
				}
			}
		}
		for(var i=0;i<validData.length;i++){
			 text=text+"<tr class=\"shopTrData\">";
			 text=text+"<td class=\"shopTdId\">"+validData[i]["cid"]+"</td>";
			 text=text+"<td class=\"shopTdName\">"+validData[i]["name"]+"</td>";
			 text=text+"<td class=\"shopTdBalance\">"+validData[i]["stockBalance"]+"</td>";
			 text=text+"<td class=\"shopTdSell\">"+validData[i]["stockSell"]+"</td>";
			 text=text+"<td class=\"shopTdCost\">"+validData[i]["averageCost"]+"</td>";
			 text=text+"<td class=\"shopTdPrice\">"+validData[i]["marketingPrice"]+"</td>";
			 text=text+"<td class=\"shopTdRate\">"+Math.round((validData[i]["marketingPrice"]-validData[i]["averageCost"])/validData[i]["averageCost"]*100)+"%</td>";
			 text=text+"<td class=\"shopTdClass\">"+validData[i]["classification"]+"</td>";
			 text=text+"<td class=\"shopTdSpeci\">"+validData[i]["specifications"]+"</td>";
			 text=text+"<td class=\"shopTdBrand\">"+validData[i]["brand"]+"</td>";
			 text=text+"<td class=\"shopTdDetail\">"+validData[i]["detail"]+"</td>";
			 text=text+"</tr>";
		  }
	}else if($(".shopThSell").css("background-color")=="rgb(0, 128, 0)"){
		$(".shopThSell").css("background-color","red");
		var text="";
		for(var i=0;i<validData.length;i++){
			for(var j=i;j<validData.length;j++){
				if(validData[i]["stockSell"]>validData[j]["stockSell"]){
					var temp=validData[i];
					validData[i]=validData[j];
					validData[j]=temp;
				}
			}
		}
		for(var i=0;i<validData.length;i++){
			 text=text+"<tr class=\"shopTrData\">";
			 text=text+"<td class=\"shopTdId\">"+validData[i]["cid"]+"</td>";
			 text=text+"<td class=\"shopTdName\">"+validData[i]["name"]+"</td>";
			 text=text+"<td class=\"shopTdBalance\">"+validData[i]["stockBalance"]+"</td>";
			 text=text+"<td class=\"shopTdSell\">"+validData[i]["stockSell"]+"</td>";
			 text=text+"<td class=\"shopTdCost\">"+validData[i]["averageCost"]+"</td>";
			 text=text+"<td class=\"shopTdPrice\">"+validData[i]["marketingPrice"]+"</td>";
			 text=text+"<td class=\"shopTdRate\">"+Math.round((validData[i]["marketingPrice"]-validData[i]["averageCost"])/validData[i]["averageCost"]*100)+"%</td>";
			 text=text+"<td class=\"shopTdClass\">"+validData[i]["classification"]+"</td>";
			 text=text+"<td class=\"shopTdSpeci\">"+validData[i]["specifications"]+"</td>";
			 text=text+"<td class=\"shopTdBrand\">"+validData[i]["brand"]+"</td>";
			 text=text+"<td class=\"shopTdDetail\">"+validData[i]["detail"]+"</td>";
			 text=text+"</tr>";
		  }
	}else{
		var text="";
		$(".shopThSell").css("background-color","rgba(0, 0, 0, 0)");
		for(var i=0;i<originData.length;i++){
			 text=text+"<tr class=\"shopTrData\">";
			 text=text+"<td class=\"shopTdId\">"+originData[i]["cid"]+"</td>";
			 text=text+"<td class=\"shopTdName\">"+originData[i]["name"]+"</td>";
			 text=text+"<td class=\"shopTdBalance\">"+originData[i]["stockBalance"]+"</td>";
			 text=text+"<td class=\"shopTdSell\">"+originData[i]["stockSell"]+"</td>";
			 text=text+"<td class=\"shopTdCost\">"+originData[i]["averageCost"]+"</td>";
			 text=text+"<td class=\"shopTdPrice\">"+originData[i]["marketingPrice"]+"</td>";
			 text=text+"<td class=\"shopTdRate\">"+Math.round((originData[i]["marketingPrice"]-originData[i]["averageCost"])/originData[i]["averageCost"]*100)+"%</td>";
			 text=text+"<td class=\"shopTdClass\">"+originData[i]["classification"]+"</td>";
			 text=text+"<td class=\"shopTdSpeci\">"+originData[i]["specifications"]+"</td>";
			 text=text+"<td class=\"shopTdBrand\">"+originData[i]["brand"]+"</td>";
			 text=text+"<td class=\"shopTdDetail\">"+originData[i]["detail"]+"</td>";
			 text=text+"</tr>";
		  }
	}
	$(".my-tbody").eq(0).html(text);
	//对表格进行渲染
	shopRendering();
	
	
});

$(".my-mask-cancel").eq(0).click(function(){
	$(".my-mask").eq(0).fadeOut(1000);
	$(".shopDivMask").eq(0).css("display","none");
	
	
});

$(".my-mask-confirm").eq(0).click(function(){
	//validData=shopData;
	validData=new Array();
	var tmpData=new Array();
	$(".my-mask").eq(0).fadeOut(1000);
	$(".shopDivMask").eq(0).css("display","none");
	//商品id过滤
	for(var i=0;i<shopData.length;i++){
		if($(".my-mask-id").eq(0).val()!="" && $(".my-mask-id").eq(0).val()!="undefined"){
			if(shopData[i]["cid"].indexOf($(".my-mask-id").eq(0).val())>=0){
				validData.push(shopData[i]);
			}
		}else{
			validData.push(shopData[i]);
		}
	}
	//商品名字过滤
	for(var i=0;i<validData.length;i++){
		if($(".my-mask-name").eq(0).val()!="" && $(".my-mask-name").eq(0).val()!="undefined"){
			var namePin = pinyinUtil.getPinyin(validData[i]["name"], ' ', false, "");
			var namePin2 = pinyinUtil.getFirstLetter(validData[i]["name"],"");
			if(validData[i]["name"].indexOf($(".my-mask-name").eq(0).val())>=0||namePin.indexOf($(".my-mask-name").eq(0).val())>=0||namePin2.indexOf($(".my-mask-name").eq(0).val().toUpperCase())>=0){
				tmpData.push(validData[i]);
				
			}
		}else{
			tmpData.push(validData[i]);
		}
	}
	validData=tmpData;
	tmpData=[];
	//公司厂商过滤
	for(var i=0;i<validData.length;i++){
		if($(".my-mask-brand").eq(0).val()!="" && $(".my-mask-brand").eq(0).val()!="undefined"){
			var brandPin = pinyinUtil.getPinyin(validData[i]["brand"], ' ', false, "");
			var brandPin2 = pinyinUtil.getFirstLetter(validData[i]["brand"],"");
			if(validData[i]["brand"].indexOf($(".my-mask-brand").eq(0).val())>=0||brandPin.indexOf($(".my-mask-brand").eq(0).val())>=0||brandPin2.indexOf($(".my-mask-brand").eq(0).val().toUpperCase())>=0){
				tmpData.push(validData[i]);
				
			}
		}else{
			tmpData.push(validData[i]);
		}
	}
	validData=tmpData;
	tmpData=[];
	//附加信息过滤
	for(var i=0;i<validData.length;i++){
		var detailPin = pinyinUtil.getPinyin(validData[i]["detail"], ' ', false, "");
		var detailPin2 = pinyinUtil.getFirstLetter(validData[i]["detail"],"");
		if($(".my-mask-detail").eq(0).val()!="" && $(".my-mask-detail").eq(0).val()!="undefined"){
			if(validData[i]["detail"].indexOf($(".my-mask-detail").eq(0).val())>=0||detailPin.indexOf($(".my-mask-detail").eq(0).val())>=0||detailPin2.indexOf($(".my-mask-detail").eq(0).val().toUpperCase())>=0){
				tmpData.push(validData[i]);
				
			}
		}else{
			tmpData.push(validData[i]);
		}
	}
	validData=tmpData;
	tmpData=[];
	
	
	//库存余额过滤
	for(var i=0;i<validData.length;i++){
		if($(".my-mask-balance").eq(0).val()!="" && $(".my-mask-balance").eq(0).val()!="undefined"){
			if($(".my-mask-select-balance").eq(0).val()=="1"&&validData[i]["stockBalance"]>$(".my-mask-balance").eq(0).val()){
				tmpData.push(validData[i]);
			}else if($(".my-mask-select-balance").eq(0).val()=="-1"&&validData[i]["stockBalance"]<$(".my-mask-balance").eq(0).val()){
				tmpData.push(validData[i]);
			}else if($(".my-mask-select-balance").eq(0).val()=="0"&&validData[i]["stockBalance"]==$(".my-mask-balance").eq(0).val()){
				tmpData.push(validData[i]);
			}else{
				
			}
		}else{
			tmpData.push(validData[i]);
		}
	}
	validData=tmpData;
	tmpData=[];
	
	//利率过滤
	for(var i=0;i<validData.length;i++){
		if($(".my-mask-rate").eq(0).val()!="" && $(".my-mask-rate").eq(0).val()!="undefined"){
			if($(".my-mask-select-rate").eq(0).val()=="1"&&validData[i]["rate"]>$(".my-mask-rate").eq(0).val()){
				tmpData.push(validData[i]);
			}else if($(".my-mask-select-rate").eq(0).val()=="-1"&&validData[i]["rate"]<$(".my-mask-rate").eq(0).val()){
				tmpData.push(validData[i]);
			}else if($(".my-mask-select-rate").eq(0).val()=="0"&&validData[i]["rate"]==$(".my-mask-rate").eq(0).val()){
				tmpData.push(validData[i]);
			}else{
				
			}
		}else{
			tmpData.push(validData[i]);
		}
	}
	validData=tmpData;
	tmpData=[];
	
	//分类过滤
	for(var i=0;i<validData.length;i++){
		if($("my-select-class").eq(0).val()!="" && $(".my-select-class").eq(0).val()!="undefined"&&$(".my-select-class").eq(0).val()!="0"){
			if(validData[i]["classification"]==$(".my-select-class option").eq($(".my-select-class").eq(0).val()).text()){
				tmpData.push(validData[i]);
			}
		}else{
			tmpData.push(validData[i]);
		}
	}
	validData=tmpData;
	tmpData=[];
	originData=validData;
	var text="";
	for(var i=0;i<validData.length;i++){
		 text=text+"<tr class=\"shopTrData\">";
		 text=text+"<td class=\"shopTdId\">"+validData[i]["cid"]+"</td>";
		 text=text+"<td class=\"shopTdName\">"+validData[i]["name"]+"</td>";
		 text=text+"<td class=\"shopTdBalance\">"+validData[i]["stockBalance"]+"</td>";
		 text=text+"<td class=\"shopTdSell\">"+validData[i]["stockSell"]+"</td>";
		 text=text+"<td class=\"shopTdCost\">"+validData[i]["averageCost"]+"</td>";
		 text=text+"<td class=\"shopTdPrice\">"+validData[i]["marketingPrice"]+"</td>";
		 text=text+"<td class=\"shopTdRate\">"+Math.round((validData[i]["marketingPrice"]-validData[i]["averageCost"])/validData[i]["averageCost"]*100)+"%</td>";
		 text=text+"<td class=\"shopTdClass\">"+validData[i]["classification"]+"</td>";
		 text=text+"<td class=\"shopTdSpeci\">"+validData[i]["specifications"]+"</td>";
		 text=text+"<td class=\"shopTdBrand\">"+validData[i]["brand"]+"</td>";
		 text=text+"<td class=\"shopTdDetail\">"+validData[i]["detail"]+"</td>";
		 text=text+"</tr>";
	  }
	$(".my-tbody").eq(0).html(text);
	//对表格进行渲染
	shopRendering();
});

$(".select-shop-button").eq(0).click(function(){
	$.ajax({
		  type: 'POST',
		  url: "CStockManage",
		  data: "event=useCidGetShopInfo&fresh=" + Math.random()+"&cid="+$(".select-shop-id").eq(0).val(),
		  dataType : "json",  
		  cache:false, 
		  success: function(data){
			  var tmpData=eval(data);
			  console.log(tmpData);
			  if(tmpData["cid"]==""){
				  $(".my-alert-div").eq(0).fadeIn(1000);
			  }else{
				  $("#update-shopId").val(tmpData["cid"]);
				  $("#update-Classification").val(tmpData["classification"]);
				  $("#update-StockBalance").val(tmpData["stockBalance"]);
				  $("#update-averageCost").val(tmpData["averageCost"]);
				  $("#update-shopName").val(tmpData["name"]);
				  $("#update-MarketingPrice").val(tmpData["marketingPrice"]);
				  $("#update-Specifications").val(tmpData["specifications"]);
				  $("#update-Brand").val(tmpData["brand"]);
				  $("#update-detail").val(tmpData["detail"]);
			  }
			 
	  },
	  error: function(jqXHR, error, errorThrown) {
	      alert(jqXHR.status);          
	  }
		  
	});
});
