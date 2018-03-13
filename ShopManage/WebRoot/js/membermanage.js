document.onreadystatechange = completeLoading;
var windowHeight=$(window).height();
var _PageHeight = document.documentElement.clientHeight,
_PageWidth = document.documentElement.clientWidth;
var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0,
_LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2 : 0;
var _LoadingHtml = '<div id="loadingDiv"'+ 
		'style="position:absolute;left:0;width:100%;height:' + _PageHeight + 'px;top:0;background:white;opacity:0.8;filter:alpha(opacity=80);z-index:10000;">'
			+'<div style="position: absolute; cursor: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width:200px; height: 57px; line-height: 57px; background: #fff url(image/preloader.gif) no-repeat;background-size:100%,100%;border-radius:15px; background-position: 10px -71px;color: #696969; font-family:\'Microsoft YaHei\';"></div></div>';
document.write(_LoadingHtml);
document.onreadystatechange = completeLoading;
function completeLoading() {
	//文档和所有子资源已完成加载 执行动作
	    if (document.readyState == "complete") {
	    	var windowHeight=$(window).height();
	    	$("body").css("height",windowHeight);
	    	$(".my-container").css("height",windowHeight*0.8+"px");
	    	$(".my-header").css("height",windowHeight*0.1+"px");
	    	var loadingMask = document.getElementById('loadingDiv');

	        loadingMask.parentNode.removeChild(loadingMask);
	    }

	}



$(document).ready(function(){
	$(".my-container").eq(0).fadeIn(3000);
});


$(".my-a").click(function(){
	var index=$(".my-a").index($(this));
	for(var i=0;i<$(".my-a").length;i++){
		$(".my-a").eq(i).removeClass("my-a-active");
		$(".my-mainbody").eq(i).css("display","none");
	}
	$(".my-a").eq(index).attr("class",$(".my-a").eq(index).attr("class")+" my-a-active");
	$(".my-mainbody").eq(index).fadeIn(1000);
});

var memberData=new Array();
var memberAllData=new Array();
var memberValidData=new Array();
//ajax获取会员信息
$.ajax({
	  type: 'POST',
	  url: "CMemberManage",
	  data: "event=getAllMemberInfo&fresh=" + Math.random(),
	  //dataType : "json",  
	  cache:false, 
	  success: function(data){
		  memberData=eval(data);
		  var text="";
		  for(var i=0;i<memberData.length;i++){
			 text=text+"<tr class=\"memTrData\">";
			 text=text+"<td class=\"memTdId\">"+memberData[i]["userId"]+"</td>";
			 text=text+"<td class=\"memTdName\">"+memberData[i]["userName"]+"</td>";
			 var childName="";
			 for(var jz=0;jz<memberData[i]["childen"].length;jz++){
				 childName=childName+" "+memberData[i]["childen"][jz]["childName"];
			 }
			 memberData[i]["childName"]=childName;
			 text=text+"<td class=\"memTdChildName\">"+memberData[i]["childName"]+"</td>";
			 text=text+"<td class=\"memTdIntegral\">"+memberData[i]["integral"]+"</td>";
			 text=text+"<td class=\"memTdIntegralTotal\">"+memberData[i]["integralTotal"]+"</td>";
			 text=text+"<td class=\"memTdAddress\">"+memberData[i]["address"]+"</td>";
			 text=text+"<td class=\"memTdDetail\">"+memberData[i]["detail"]+"</td>";
			 text=text+"<td class=\"memTdUpdateTime\">"+ new Date(memberData[i]["updateTime"]*1).toLocaleDateString()+"</td>";
			 text=text+"<td class=\"memTdCreateTime\">"+ new Date(memberData[i]["createTime"]*1).toLocaleDateString()+"</td>";
			 text=text+"</tr>";
			 
		  }
		  $(".my-tbody").eq(0).append(text);
		  memberAllData=memberData;
		  memberValidData=memberAllData;
    },
    error: function(jqXHR, error, errorThrown) {
        alert(jqXHR.status);          
    }
	  
});

$(".select-user-button").eq(0).click(function(){
	$.ajax({
		  type: 'POST',
		  url: "CMemberManage",
		  data: "event=useUserIdGetInfo&fresh=" + Math.random()+"&userId="+$(".select-user-id").eq(0).val(),
		  dataType : "json",  
		  cache:false, 
		  success: function(data){
			  var tmpData=eval(data);
			  if(tmpData["userId"]==""){
				  $(".my-alert-div").eq(0).slideToggle(500);
			  }else{
				  $("#update-userId").val(tmpData["userId"]);
				  $("#update-userName").val(tmpData["userName"]);
				  $("#update-userAddress").val(tmpData["address"]);
				  $("#update-userIntegral").val(tmpData["integral"]);
				  $("#update-detail").val(tmpData["detail"]);
				  
				  $("#update-childName").val(tmpData["childen"][0]["childName"]);
				  $("#update-childBirthday").val(tmpData["childen"][0]["birthday"]);
				  $("#update-childSex").val(tmpData["childen"][0]["sex"]);
				  if(tmpData["childen"].length==2){
					  $("#update-childName2").val(tmpData["childen"][1]["childName"]);
					  $("#update-childBirthday2").val(tmpData["childen"][1]["birthday"]);
					  $("#update-childSex2").val(tmpData["childen"][1]["sex"]);
				  }else{
					  $("#update-childName2").val("");
					  $("#update-childBirthday2").val("");
					  
				  }
			  }
				 
			 
	    },
	    error: function(jqXHR, error, errorThrown) {
	        alert(jqXHR.status);          
	    }
		  
	});
});


//会员剩余积分排序
$(".ThIntegral").click(function(){
	memberSort("integral","ThIntegral");
	
});

//会员总总积分排序
$(".ThIntegralTotal").click(function(){
	memberSort("integralTotal","ThIntegralTotal");
	
});

//会员信息筛选
$(".ThUserId,.ThUserName,.ThUserChildName,.ThAddress,.ThDetail").click(function(){
	$(".my-mask").eq(0).css("display","block");
	$(".shopDivMask").eq(0).fadeIn(1000);	
});

$(".my-mask-cancel").eq(0).click(function(){
	$(".my-mask").eq(0).fadeOut(1000);
	$(".shopDivMask").eq(0).css("display","none");
	
	
});



//会员排序函数
function memberSort(attributeName,labelName){
	var text="";
	if($("."+labelName).css("background-color")=="rgba(0, 0, 0, 0)"){
		$("."+labelName).css("background-color","green");
		for(var i=0;i<memberValidData.length;i++){
			for(var j=i;j<memberValidData.length;j++){
				if(memberValidData[i][attributeName]<memberValidData[j][attributeName]){
					var temp=memberValidData[i];
					memberValidData[i]=memberValidData[j];
					memberValidData[j]=temp;
				}
			}
		}
	}else if($("."+labelName).css("background-color")=="rgb(0, 128, 0)"){
		$("."+labelName).css("background-color","red");
		var text="";
		for(var i=0;i<memberValidData.length;i++){
			for(var j=i;j<memberValidData.length;j++){
				if(memberValidData[i][attributeName]>memberValidData[j][attributeName]){
					var temp=memberValidData[i];
					memberValidData[i]=memberValidData[j];
					memberValidData[j]=temp;
				}
			}
		}
	}else{
		$("."+labelName).css("background-color","rgba(0, 0, 0, 0)");
	}
	 for(var i=0;i<memberValidData.length;i++){
		 text=text+"<tr class=\"memTrData\">";
		 text=text+"<td class=\"memTdId\">"+memberValidData[i]["userId"]+"</td>";
		 text=text+"<td class=\"memTdName\">"+memberValidData[i]["userName"]+"</td>";
		 var childName="";
		 for(var jz=0;jz<memberValidData[i]["childen"].length;jz++){
			 childName=childName+" "+memberValidData[i]["childen"][jz]["childName"];
		 }
		 text=text+"<td class=\"memTdChildName\">"+childName.trim()+"</td>";
		 text=text+"<td class=\"memTdIntegral\">"+memberValidData[i]["integral"]+"</td>";
		 text=text+"<td class=\"memTdIntegralTotal\">"+memberValidData[i]["integralTotal"]+"</td>";
		 text=text+"<td class=\"memTdAddress\">"+memberValidData[i]["address"]+"</td>";
		 text=text+"<td class=\"memTdDetail\">"+memberValidData[i]["detail"]+"</td>";
		 text=text+"<td class=\"memTdUpdateTime\">"+ new Date(memberValidData[i]["updateTime"]*1).toLocaleDateString()+"</td>";
		 text=text+"<td class=\"memTdCreateTime\">"+ new Date(memberValidData[i]["createTime"]*1).toLocaleDateString()+"</td>";
		 text=text+"</tr>";
		 
	  }
	  $(".my-tbody").eq(0).html(text);
}

//memberAllData
//memberValidData
$(".my-mask-confirm").eq(0).click(function(){
	//validData=shopData;
	memberValidData=new Array();
	var tmpData=new Array();
	$(".my-mask").eq(0).fadeOut(1000);
	$(".shopDivMask").eq(0).css("display","none");
	//账户id过滤
	for(var i=0;i<memberAllData.length;i++){
		if($(".my-mask-id").eq(0).val()!="" && $(".my-mask-id").eq(0).val()!="undefined"){
			if(memberAllData[i]["userId"].indexOf($(".my-mask-id").eq(0).val())>=0){
				memberValidData.push(memberAllData[i]);
			}
		}else{
			memberValidData.push(memberAllData[i]);
		}
	}
	//用户名过滤
	for(var i=0;i<memberValidData.length;i++){
		if($(".my-mask-name").eq(0).val()!="" && $(".my-mask-name").eq(0).val()!="undefined"){
			var namePin = pinyinUtil.getPinyin(memberValidData[i]["userName"], ' ', false, "");
			var namePin2 = pinyinUtil.getFirstLetter(memberValidData[i]["userName"],"");
			if(memberValidData[i]["userName"].indexOf($(".my-mask-name").eq(0).val())>=0||namePin.indexOf($(".my-mask-name").eq(0).val())>=0||namePin2.indexOf($(".my-mask-name").eq(0).val().toUpperCase())>=0){
				tmpData.push(memberValidData[i]);
				
			}
		}else{
			tmpData.push(memberValidData[i]);
		}
	}
	memberValidData=tmpData;
	tmpData=[];
	
	//宝宝名过滤
	for(var i=0;i<memberValidData.length;i++){
		if($(".my-mask-babyName").eq(0).val()!="" && $(".my-mask-babyName").eq(0).val()!="undefined"){
			var brandPin = pinyinUtil.getPinyin(memberValidData[i]["childName"], ' ', false, "");
			var brandPin2 = pinyinUtil.getFirstLetter(memberValidData[i]["childName"],"");
			if(memberValidData[i]["childName"].indexOf($(".my-mask-babyName").eq(0).val())>=0||brandPin.indexOf($(".my-mask-babyName").eq(0).val())>=0||brandPin2.indexOf($(".my-mask-babyName").eq(0).val().toUpperCase())>=0){
				tmpData.push(memberValidData[i]);
			}
		}else{
			tmpData.push(memberValidData[i]);
		}
	}
	memberValidData=tmpData;
	tmpData=[];
	
	
	//备注信息过滤
	for(var i=0;i<memberValidData.length;i++){
		var detailPin = pinyinUtil.getPinyin(memberValidData[i]["detail"], ' ', false, "");
		var detailPin2 = pinyinUtil.getFirstLetter(memberValidData[i]["detail"],"");
		if($(".my-mask-detail").eq(0).val()!="" && $(".my-mask-detail").eq(0).val()!="undefined"){
			if(memberValidData[i]["detail"].indexOf($(".my-mask-detail").eq(0).val())>=0||detailPin.indexOf($(".my-mask-detail").eq(0).val())>=0||detailPin2.indexOf($(".my-mask-detail").eq(0).val().toUpperCase())>=0){
				tmpData.push(memberValidData[i]);
				
			}
		}else{
			tmpData.push(memberValidData[i]);
		}
	}
	memberValidData=tmpData;
	tmpData=[];
	
	
	//积分余额过滤
	for(var i=0;i<memberValidData.length;i++){
		if($(".my-mask-integral").eq(0).val()!="" && $(".my-mask-integral").eq(0).val()!="undefined"){
			if($(".my-mask-select-integral").eq(0).val()=="1"&&memberValidData[i]["integral"]>$(".my-mask-integral").eq(0).val()){
				tmpData.push(memberValidData[i]);
			}else if($(".my-mask-select-integral").eq(0).val()=="-1"&&memberValidData[i]["integral"]<$(".my-mask-integral").eq(0).val()){
				tmpData.push(memberValidData[i]);
			}else if($(".my-mask-select-integral").eq(0).val()=="0"&&memberValidData[i]["integral"]==$(".my-mask-integral").eq(0).val()){
				tmpData.push(memberValidData[i]);
			}else{
				
			}
		}else{
			tmpData.push(memberValidData[i]);
		}
	}
	memberValidData=tmpData;
	tmpData=[];
	
	//积分总数过滤
	for(var i=0;i<memberValidData.length;i++){
		if($(".my-mask-integralTotal").eq(0).val()!="" && $(".my-mask-integralTotal").eq(0).val()!="undefined"){
			if($(".my-mask-select-integralTotal").eq(0).val()=="1"&&memberValidData[i]["integralTotal"]>$(".my-mask-integralTotal").eq(0).val()){
				tmpData.push(memberValidData[i]);
			}else if($(".my-mask-select-integralTotal").eq(0).val()=="-1"&&memberValidData[i]["integralTotal"]<$(".my-mask-integralTotal").eq(0).val()){
				tmpData.push(memberValidData[i]);
			}else if($(".my-mask-select-integralTotal").eq(0).val()=="0"&&memberValidData[i]["integralTotal"]==$(".my-mask-integralTotal").eq(0).val()){
				tmpData.push(memberValidData[i]);
			}else{
				
			}
		}else{
			tmpData.push(memberValidData[i]);
		}
	}
	memberValidData=tmpData;
	tmpData=[];
	
	var text="";
	for(var i=0;i<memberValidData.length;i++){
		 text=text+"<tr class=\"memTrData\">";
		 text=text+"<td class=\"memTdId\">"+memberValidData[i]["userId"]+"</td>";
		 text=text+"<td class=\"memTdName\">"+memberValidData[i]["userName"]+"</td>";
		 var childName="";
		 for(var jz=0;jz<memberValidData[i]["childen"].length;jz++){
			 childName=childName+" "+memberValidData[i]["childen"][jz]["childName"];
		 }
		 text=text+"<td class=\"memTdChildName\">"+childName.trim()+"</td>";
		 text=text+"<td class=\"memTdIntegral\">"+memberValidData[i]["integral"]+"</td>";
		 text=text+"<td class=\"memTdIntegralTotal\">"+memberValidData[i]["integralTotal"]+"</td>";
		 text=text+"<td class=\"memTdAddress\">"+memberValidData[i]["address"]+"</td>";
		 text=text+"<td class=\"memTdDetail\">"+memberValidData[i]["detail"]+"</td>";
		 text=text+"<td class=\"memTdUpdateTime\">"+ new Date(memberValidData[i]["updateTime"]*1).toLocaleDateString()+"</td>";
		 text=text+"<td class=\"memTdCreateTime\">"+ new Date(memberValidData[i]["createTime"]*1).toLocaleDateString()+"</td>";
		 text=text+"</tr>";
		 
	  }
	  $(".my-tbody").eq(0).html(text);


});


