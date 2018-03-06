window.onload = function() { 
	//$(".test").css("display","none");
}; 
$(document).ready(function(){
	var type = getUrlParam('type');
	$('.'+type).eq(0).css("background-color","#c0c0c0");
	
	$('.'+type+"_p").eq(0).css("color","green");
	
	
	
	$(".title_choose").click(function(){
		$(".title_choose").css("color","black");
		$(".title_choose").css("border-bottom","0px");
		$(".title_choose").eq($(".title_choose").index(this)).css("color","green");
		$(".title_choose").eq($(".title_choose").index(this)).css("border-bottom","1px solid green");
		$(".bodyer_right_title").eq(0).text($(".title_choose").eq($(".title_choose").index(this)).text());
	});
	
	$(document).on("click",".div_create_keywordReply",function(){
		$(".keywordReply_list").slideToggle(1000);
		$(".keywordReplay_create").fadeIn(1000);
	});
	
	$(document).on("click",".keywordReply_cancel",function(){
		$(".keywordReplay_create").fadeOut(1000);
		$(".keywordReply_list").slideToggle(1000);
	});
	
	//点击消息类型弹出框
	$(document).on("click",".keywordReplyTextMessage",function(){
		$(".mask").eq(0).css("height",$(document).height());
		$(".mask").eq(0).css("width",$(document).width());
		$(".mask").eq(0).fadeIn(1000);
		$(".keywordReplyTextMessageMaskDiv").eq(0).css("top","100px");
		$(".keywordReplyTextMessageMaskDiv").eq(0).css("left",($(window).width()-$(".keywordReplyTextMessageMaskDiv").eq(0).width())/2);
		$(".keywordReplyTextMessageMaskDiv").eq(0).fadeIn(1000);
	});
	
	$(document).on("click",".keywordReplyImageMessage",function(){
		$(".mask").eq(0).css("height",$(document).height());
		$(".mask").eq(0).css("width",$(document).width());
		$(".mask").eq(0).fadeIn(1000);
		$(".keywordReplyImageMessageMaskDiv").eq(0).css("top","100px");
		$(".keywordReplyImageMessageMaskDiv").eq(0).css("left",($(window).width()-$(".keywordReplyImageMessageMaskDiv").eq(0).width())/2);
		$(".keywordReplyImageMessageMaskDiv").eq(0).fadeIn(1000);
	});
	
	$(document).on("click",".keywordReplyVideoMessage",function(){
		$(".mask").eq(0).css("height",$(document).height());
		$(".mask").eq(0).css("width",$(document).width());
		$(".mask").eq(0).fadeIn(1000);
		$(".keywordReplyVideoMessageMaskDiv").eq(0).css("top","100px");
		$(".keywordReplyVideoMessageMaskDiv").eq(0).css("left",($(window).width()-$(".keywordReplyVideoMessageMaskDiv").eq(0).width())/2);
		$(".keywordReplyVideoMessageMaskDiv").eq(0).fadeIn(1000);
	});
	
	$(document).on("click",".keywordReplyNewsMessage",function(){
		$(".mask").eq(0).css("height",$(document).height());
		$(".mask").eq(0).css("width",$(document).width());
		$(".mask").eq(0).fadeIn(1000);
		$(".keywordReplyNewsMessageMaskDiv").eq(0).css("top","100px");
		$(".keywordReplyNewsMessageMaskDiv").eq(0).css("left",($(window).width()-$(".keywordReplyNewsMessageMaskDiv").eq(0).width())/2);
		$(".keywordReplyNewsMessageMaskDiv").eq(0).fadeIn(1000);
	});
	
	//退出消息类型弹出框
	$(document).on("click",".keywordReplyTextMessageMaskDiv_cancel",function(){
		$(".mask").eq(0).css("display","none");
		$(".keywordReplyTextMessageMaskDiv").eq(0).fadeOut(1000);
		$(".saytext").eq(0).val("");
	});
	$(document).on("click",".keywordReplyImageMessageMaskDiv_cancel",function(){
		$(".mask").eq(0).css("display","none");
		$(".keywordReplyImageMessageMaskDiv").eq(0).fadeOut(1000);
		$(".keywordReplyImageMessageImageInput").eq(0).val("");
	});
	$(document).on("click",".keywordReplyVideoMessageMaskDiv_cancel",function(){
		$(".mask").eq(0).css("display","none");
		$(".keywordReplyVideoMessageMaskDiv").eq(0).fadeOut(1000);
		$(".keywordReplyVideoMessageImageInput").eq(0).val("");
	});
	
	$(document).on("click",".keywordReplyNewsMessageMaskDiv_cancel",function(){
		$(".mask").eq(0).css("display","none");
		$(".keywordReplyNewsMessageMaskDiv").eq(0).fadeOut(1000);
		$(".keywordReplyNewsMessageImageInput").eq(0).val("");
	});
	//通过右上方×退出
	$(document).on("click",".keywordReplyTextMessageMaskDiv_fork",function(){
		$(".mask").eq(0).css("display","none");
		$(".keywordReplyTextMessageMaskDiv").eq(0).fadeOut(1000);
		$(".saytext").eq(0).val("");
	});
	
	$(document).on("click",".keywordReplyImageMessageMaskDiv_fork",function(){
		$(".mask").eq(0).css("display","none");
		$(".keywordReplyImageMessageMaskDiv").eq(0).fadeOut(1000);
		$(".keywordReplyImageMessageImageInput").eq(0).val("");
	});
	
	$(document).on("click",".keywordReplyVideoMessageMaskDiv_fork",function(){
		$(".mask").eq(0).css("display","none");
		$(".keywordReplyVideoMessageMaskDiv").eq(0).fadeOut(1000);
		$(".keywordReplyVideoMessageImageInput").eq(0).val("");
	});
	
	$(document).on("click",".keywordReplyNewsMessageMaskDiv_fork",function(){
		$(".mask").eq(0).css("display","none");
		$(".keywordReplyNewsMessageMaskDiv").eq(0).fadeOut(1000);
		$(".keywordReplyNewsMessageImageInput").eq(0).val("");
	});
	
	//确认退出
	$(document).on("click",".keywordReplyTextMessageMaskDiv_confirm",function(){
		$(".mask").eq(0).css("display","none");
		$(".keywordReplyTextMessageMaskDiv").eq(0).fadeOut(1000);
		if($(".saytext").eq(0).val()!=""){
			$(".keywordReply_message").css("border","1px solid #cccccc");
			$(".keywordReplyTextMessage").eq(0).css("border","1px solid green");
			$(".keywordReplyImageMessageImageInput").eq(0).val("");
			$(".keywordReplyNewsMessageImageInput").eq(0).val("");
			$(".keywordReplyVideoMessageImageInput").eq(0).val("");
		}
	});
	
	$(document).on("click",".keywordReplyImageMessageMaskDiv_confirm",function(){
		$(".mask").eq(0).css("display","none");
		$(".keywordReplyImageMessageMaskDiv").eq(0).fadeOut(1000);
		if($(".keywordReplyImageMessageImageInput").eq(0).val()!=""){
			$(".keywordReply_message").css("border","1px solid #cccccc");
			$(".saytext").eq(0).val("");
			$(".keywordReplyVideoMessageImageInput").eq(0).val("");
			$(".keywordReplyNewsMessageImageInput").eq(0).val("");
			$(".keywordReplyImageMessage").eq(0).css("border","1px solid green");
		}
	});
	
	$(document).on("click",".keywordReplyVideoMessageMaskDiv_confirm",function(){
		$(".mask").eq(0).css("display","none");
		$(".keywordReplyVideoMessageMaskDiv").eq(0).fadeOut(1000);
		if($(".keywordReplyVideoMessageImageInput").eq(0).val()!=""){
			$(".keywordReply_message").css("border","1px solid #cccccc");
			$(".keywordReplyImageMessageImageInput").eq(0).val("");
			$(".keywordReplyNewsMessageImageInput").eq(0).val("");
			$(".saytext").eq(0).val("");
			$(".keywordReplyVideoMessage").eq(0).css("border","1px solid green");
		}
	});
	
	$(document).on("click",".keywordReplyNewsMessageMaskDiv_confirm",function(){
		$(".mask").eq(0).css("display","none");
		$(".keywordReplyNewsMessageMaskDiv").eq(0).fadeOut(1000);
		if($(".keywordReplyNewsMessageImageInput").eq(0).val()!=""){
			$(".keywordReply_message").css("border","1px solid #cccccc");
			$(".keywordReplyVideoMessageImageInput").eq(0).val("");
			$(".keywordReplyImageMessageImageInput").eq(0).val("");
			$(".saytext").eq(0).val("");
			$(".keywordReplyNewsMessage").eq(0).css("border","1px solid green");
		}
	});
	
	
	//微信被关注回复
	$(document).on("click",".title_followReply",function(){
		$(".keywordReplyTextMessageMaskDiv").css("display","none");
		$(".mask").css("display","none");
		if($(".bodyer_right_external_follow_div").eq(0).css("display")=="none"){
			$(".bodyer_right_external_div").eq(0).fadeOut(1000);
			$(".bodyer_right_external_event_div").eq(0).fadeOut(1000);
			$(".bodyer_right_external_follow_div").eq(0).fadeIn(1000);
		}
		
	});
	
	//微信自动回复
	$(document).on("click",".title_keywordReply",function(){
		$(".keywordReplyTextMessageMaskDiv").css("display","none");
		$(".mask").css("display","none");
		if($(".bodyer_right_external_div").eq(0).css("display")=="none"){
			$(".bodyer_right_external_follow_div").eq(0).fadeOut(1000);
			$(".bodyer_right_external_event_div").eq(0).fadeOut(1000);
			$(".bodyer_right_external_div").eq(0).fadeIn(1000);
		}
	});
	
	//微信事件回复
	$(document).on("click",".title_eventReply",function(){
		$(".keywordReplyTextMessageMaskDiv").css("display","none");
		$(".mask").css("display","none");
		if($(".bodyer_right_external_event_div").eq(0).css("display")=="none"){
			$(".bodyer_right_external_div").eq(0).fadeOut(1000);
			$(".bodyer_right_external_follow_div").eq(0).fadeOut(1000);
			$(".bodyer_right_external_event_div").eq(0).fadeIn(1000);
		}
		
		
	});
	
	//微信素材管理模块JS代码
	$(document).on("click",".sourceMaterial_title_li",function(){
		$(".sourceMaterial_title_li").css("color","black");
		$(".sourceMaterial_title_li").css("border-bottom","0px");
		$(".sourceMaterial_title_li").eq($(".sourceMaterial_title_li").index(this)).css("color","green");
		$(".sourceMaterial_title_li").eq($(".sourceMaterial_title_li").index(this)).css("border-bottom","1px solid green");
		//$(".bodyer_right_title").eq(0).text($(".title_choose").eq($(".title_choose").index(this)).text());
	})
	
	
});

//获取url中的参数
function getUrlParam(name) {
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
  var r = window.location.search.substr(1).match(reg); //匹配目标参数
  if (r != null) return unescape(r[2]); return null; //返回参数值
}

var type = getUrlParam('type');
//ajax方式获取自动回复规则数据
if(type=="userDefinedReply"){
	$.ajax({
		  type: 'POST',
		  url: "wechat",
		  data: "event=init&fresh=" + Math.random(),
		  //dataType : "json",  
		  cache:false, 
		  success: function(data){
			  var arrayData=eval(data);
			  for(var i=arrayData.length-1;i>=0;i--){
				  var tmpMatchRule=arrayData[i]["matchRule"]=="1"?"全匹配":"半匹配";
				  var text="<ul class='ruleDataUl'>"
				  text=text+"<li class='ruleDataLi ruleDataLiName'>"+arrayData[i]["ruleName"]+"</li>";
				  text=text+"<li class='ruleDataLi ruleDataLiMatchRule'>"+tmpMatchRule+"</li>";
				  text=text+"<li class='ruleDataLi ruleDataLiMatchWord'>"+arrayData[i]["matchWord"]+"</li>";
				  text=text+"<li class='ruleDataLi ruleDataLiReplyContent'>"+arrayData[i]["replyContent"]+"</li>";
				  $(".ul_keywordReply_title").eq(0).after(text);
			  }
			
			 
          },
          error: function(jqXHR, error, errorThrown) {
              alert(jqXHR.status);          
              }
		  
		});
	$.ajax({
		  type: 'POST',
		  url: "wechat",
		  data: "event=subscribe&fresh=" + Math.random(),
		  //dataType : "json",  
		  cache:false, 
		  success: function(data){
			 var arr=eval(data);
			 if(arr[0]["replyType"].toLowerCase()=="text"){
				 $(".follow_choose").eq(0).css("background-color","green");
				 $(".follow_text_contain").css("display","block");
				 $(".followTextTextarea").eq(0).val(arr[0]["evetReply"]);
			 }else{
				 $(".follow_choose").eq(1).css("background-color","green");
				 $(".follow_mediaId_contain").css("display","block");
				 $(".followMediaId").eq(0).val(arr[0]["evetReply"]);
				 $("."+arr[0]["replyType"].toLowerCase()+"InputRadio").eq(0).attr("checked",true);
			 }
			 
		  },
			
			 
        error: function(jqXHR, error, errorThrown) {
            alert(jqXHR.status);          
            }
		  
		});
	//事件回复
	$.ajax({
		  type: 'POST',
		  url: "wechat",
		  data: "event=event&fresh=" + Math.random(),
		  //dataType : "json",  
		  cache:false, 
		  success: function(data){
			  var arrayData=eval(data);
			  console.log(arrayData);
			  for(var i=arrayData.length-1;i>=0;i--){
				  var text="<ul class='EruleDataUl'>"
				  text=text+"<li class='EruleDataLi EruleDataLiName'>"+arrayData[i]["eventCode"]+"</li>";
				  text=text+"<li class='EruleDataLi EruleDataLiMatchRule'>"+arrayData[i]["replyType"]+"</li>";
				  text=text+"<li class='EruleDataLi EruleDataLiMatchWord'>"+arrayData[i]["evetReply"]+"</li>";
				  $(".ul_event_keywordReply_title").eq(0).after(text);
			  }
			
			 
        },
        error: function(jqXHR, error, errorThrown) {
            alert(jqXHR.status);          
            }
		  
		});
	
	$(document).on("click",".div_create_event_keywordReply",function(){
		
		$(".event_add_Div").eq(0).fadeIn(1000);
		$(".bodyer_right_external_event_div").eq(0).fadeOut(1000);
	});
	$(document).on("click",".follow_choose",function(){
		if($(this).index()==0){
			$(".follow_text_contain").eq(0).fadeIn(1000);
			$(".follow_mediaId_contain").eq(0).css("display","none");
			$(".follow_choose").eq(0).css("background-color","green");
			$(".follow_choose").eq(1).css("background-color","gray");
		}else if($(this).index()==1){
			$(".follow_mediaId_contain").eq(0).fadeIn(1000);
			$(".follow_text_contain").eq(0).css("display","none");
			$(".follow_choose").eq(1).css("background-color","green");
			$(".follow_choose").eq(0).css("background-color","gray");
		}
	});
}else if(type=="sourceMaterial"){
	//news素材ajax
	$.ajax({
		  type: 'POST',
		  url: "wechat",
		  data: "event=newsSourceMaterial&fresh=" + Math.random(),
		  //dataType : "json",  
		  cache:false, 
		  success: function(data){
			  var arrayData=eval(data);
			  console.log(arrayData);
			  $(".sourceMaterial_new_count").eq(0).html("图文消息 （共"+arrayData.length+"条）")
			  for(var i=0;i<arrayData.length;i++){
				  var text="<ul class=\"ULsourceMaterial_news_content\">";
				  text=text+"<li class=\"LIsourceMaterial_news_content LIsourceMaterial_news_content_mediaId\">"+
				  	arrayData[i]["media_id"]+
				  	"</li><li class=\"LIsourceMaterial_news_content LIsourceMaterial_news_content_newNumber\">"+
				  	arrayData[i]["news_item"].length+
				  	"</li><li class=\"LIsourceMaterial_news_content LIsourceMaterial_news_content_mediaIdCreatetime\">"+
				  	new Date(arrayData[i]["create_time"]*1000).toLocaleDateString()+
					"</li><li class=\"LIsourceMaterial_news_content LIsourceMaterial_news_content_mediaIdUpdatetime\">"+
					new Date(arrayData[i]["update_time"]*1000).toLocaleDateString()+
					"</li>"
				  	;
				  text=text+"</ul>"+"<div class=\"news_div_click_info\"style=\"overflow:hidden;text-align:left;margin:0 auto;display:none;width:814px;height:250px;border:1px solid gray;position:relative;\"><br>"+
				  	"&nbsp;媒体ID:"+arrayData[i]["media_id"]+"<br><br>"+
				  	"&nbsp;创建时间："+new Date(arrayData[i]["create_time"]*1000).toLocaleDateString()+
				  	"&nbsp;更新时间："+new Date(arrayData[i]["update_time"]*1000).toLocaleDateString()+"<br><br>"+
				  	"&nbsp;文章数量："+arrayData[i]["news_item"].length+"<br><br>";
				  for(var j=0;j<arrayData[i]["news_item"].length;j++){
					  text=text+"<a target=\"_Blank\" style=\"color:black;text-decoration:none; \" href=\""+arrayData[i]["news_item"][j]["url"]+"\"><pre>	标题："+arrayData[i]["news_item"][j]["title"]+
					  "	摘要："+arrayData[i]["news_item"][j]["digest"]+
					  "	作者："+arrayData[i]["news_item"][j]["author"]+
					  "	允许评论："+((arrayData[i]["news_item"][j]["need_open_comment"]==1)?"否":"是"+"	只允许粉丝评论："+((arrayData[i]["news_item"][j]["only_fans_can_comment"]==0)?"否":"是"))+
					  "</a>";
				  }
				  	
				  text=text+"</div>";
				  $(".newsSourceMaterialDiv").append(text);
			  }
			  
			  
				//ajax后出现各类标签，给这些标签绑定事件
					var ULsourceMaterial_news_content=document.getElementsByClassName("ULsourceMaterial_news_content");	
					for(var i=0;i<ULsourceMaterial_news_content.length;i++){
				     (function(i){
				    	 ULsourceMaterial_news_content[i].onclick=function(){
				    		 $(".news_div_click_info").eq(i).slideToggle(1000);
				    	 }
				     })(i);
					}
			  
        },
        error: function(jqXHR, error, errorThrown) {
            alert(jqXHR.status);          
            }
		  
		});
	
	//picture素材ajax
	$.ajax({
		  type: 'POST',
		  url: "wechat",
		  data: "event=imageSourceMaterial&fresh=" + Math.random(),
		  //dataType : "json",  
		  cache:false, 
		  success: function(data){
			  var arrayData=eval(data);
			  console.log(arrayData);
			  $(".sourceMaterial_picture_count").eq(0).html("图片消息 （共"+arrayData.length+"条）")
			  var text="";
			  for(var i=0;i<arrayData.length;i++){
				  text=text+"<ul class=\"ULsourceMaterial_image_content\">";
				  text=text+"<li class=\"LIsourceMaterial_image_content LIsourceMaterial_image_content_mediaId\">"+
				  	arrayData[i]["media_id"]+
				  	"</li><li class=\"LIsourceMaterial_image_content LIsourceMaterial_image_content_pic\">"+
				  	"<image style=\"margin-top:2px;width:55px;height:55px; background-repeat:no-repeat; background-size:100% 100%;-moz-background-size:100% 100%;background-image:url(./WeChatSourceMaterial/"+arrayData[i]["SName"]+")\"></image>"+
				  	"</li><li class=\"LIsourceMaterial_image_content LIsourceMaterial_image_content_name\">"+
				  	arrayData[i]["name"]+
					"</li><li class=\"LIsourceMaterial_image_content LIsourceMaterial_image_content_mediaIdUpdatetime\">"+
					new Date(arrayData[i]["update_time"]*1000).toLocaleDateString()+
					"</li>"
				  	;
				  text=text+"</ul>"+"<div class=\"image_div_click_info\"style=\"overflow:hidden;text-align:left;margin:0 auto;display:none;width:814px;height:250px;border:1px solid gray;position:relative;\"><br>"+
				  	"&nbsp;媒体ID:"+arrayData[i]["media_id"]+"<br><br>"+
				  	"&nbsp;文件名："+arrayData[i]["name"]+"<br><br>"+
				  	"&nbsp;更新时间："+new Date(arrayData[i]["update_time"]*1000).toLocaleDateString()+"<br><br>"+
				  	"&nbsp;图片链接（不可直接点击，需要复制到浏览器的地址栏中打开）：<br>&nbsp;"+arrayData[i]["url"]+"<br><br>"+
				  	"<image style=\"position:absolute;right:50px;top:10px;width:180px;height:140px; background-repeat:no-repeat; background-size:100% 100%;-moz-background-size:100% 100%;background-image:url(./WeChatSourceMaterial/"+arrayData[i]["SName"]+")\"></image>"+
				  	"</div>";
			  }
			
			 $(".imageSourceMaterialDiv").append(text);
			 
			//ajax后出现各类标签，给这些标签绑定事件
				var ULsourceMaterial_image_content=document.getElementsByClassName("ULsourceMaterial_image_content");	
				for(var i=0;i<ULsourceMaterial_image_content.length;i++){
			     (function(i){
			    	 ULsourceMaterial_image_content[i].onclick=function(){
			    		 $(".image_div_click_info").eq(i).slideToggle(1000);
			    	 }
			     })(i);
				}
        },
        error: function(jqXHR, error, errorThrown) {
            alert(jqXHR.status);          
            }
		  
		});
	
	//video素材
	$.ajax({
		  type: 'POST',
		  url: "wechat",
		  data: "event=videoSourceMaterial&fresh=" + Math.random(),
		  //dataType : "json",  
		  cache:false, 
		  success: function(data){
			  var arrayData=eval(data);
			  console.log(arrayData);
			  $(".sourceMaterial_video_count").eq(0).html("视频消息 （共"+arrayData.length+"条）")
			  var text="";
			  for(var i=0;i<arrayData.length;i++){
				  text=text+"<ul class=\"ULsourceMaterial_video_content\">";
				  text=text+"<li class=\"LIsourceMaterial_video_content LIsourceMaterial_video_content_mediaId\">"+
				  	arrayData[i]["media_id"]+
				  	"</li><li class=\"LIsourceMaterial_video_content LIsourceMaterial_video_content_title\">"+
				  	arrayData[i]["title"]+
				  	"</li><li class=\"LIsourceMaterial_video_content LIsourceMaterial_video_content_description\">"+
				  	arrayData[i]["description"]+
					"</li><li class=\"LIsourceMaterial_video_content LIsourceMaterial_video_content_mediaIdUpdatetime\">"+
					new Date(arrayData[i]["update_time"]*1000).toLocaleDateString()+
					"</li>"
				  	;
				  text=text+"</ul>"+"<div class=\"video_div_click_info\"style=\"text-align:left;margin:0 auto;display:none;width:814px;height:250px;border:1px solid gray;position:relative;\"><br>"+
				  	"&nbsp;媒体ID:"+arrayData[i]["media_id"]+"<br><br>"+
				  	"&nbsp;标题："+arrayData[i]["title"]+"<br><br>"+
				  	"&nbsp;描述信息："+arrayData[i]["description"]+"<br><br>"+
				  	"&nbsp;文件名："+arrayData[i]["name"].substr(18)+"<br><br>"+
				  	"&nbsp;更新时间："+new Date(arrayData[i]["update_time"]*1000).toLocaleDateString()+"<br>"+
				  	"<video style=\"position:absolute;right:0px;top:0px;\" src=\"./WeChatSourceMaterial/"+arrayData[i]["name"]+"\"  width=\"300px\" height=\"240px\" controls=\"controls\">Your browser does not support the video tag.</video>"+
				  	"</div>";
			  }
			
			 $(".videoSourceMaterialDiv").append(text);
			
			 
			//ajax后出现各类标签，给这些标签绑定事件
			var ULsourceMaterial_video_content=document.getElementsByClassName("ULsourceMaterial_video_content");	
			for(var i=0;i<ULsourceMaterial_video_content.length;i++){
		     (function(i){
		    	 ULsourceMaterial_video_content[i].onclick=function(){
		    		 $(".video_div_click_info").eq(i).slideToggle(1000);
		    	 }
		     })(i);
			}
      },
      error: function(jqXHR, error, errorThrown) {
          alert(jqXHR.status);          
          }
		  
		});
}else if(type=="serverConfig"){
	var index=0;
	$(document).on("click",".sourceMaterialSync",function(){
		$(".sourceMaterialSyncConfirm").eq(0).fadeIn(1000);
		$(".coverSync").eq(0).fadeIn(1000);
		index=$(this).index();
	});
	var parm=1;
	var int;
	$(document).on("click",".sourceMaterialSyncConfirmYes",function(){
		$(".sourceMaterialSyncConfirm").eq(0).fadeOut(1000);
		$(".progressBar").eq(0).fadeIn(1000);
		int=setInterval(asyncSourceMaterial(index),1000);
		
	});
	
	$(document).on("click",".sourceMaterialSyncConfirmCancel",function(){
		$(".sourceMaterialSyncConfirm").eq(0).fadeOut(1000);
		$(".coverSync").eq(0).fadeOut(1000);
	});
	for(var i=0;i<=100;i=i+10){
		$(".sourceMaterialSync").eq(0).animate({height:i+"px"},1);
		$(".sourceMaterialSync").eq(0).animate({width:i+"px"},1);
		$(".sourceMaterialSync").eq(0).animate({marginTop:((100-i)/4+50)+"px"},1);
		$(".sourceMaterialSync").eq(0).animate({marginLeft:((100-i)/4+50)+"px"},1);
		
		$(".sourceMaterialSync").eq(1).animate({height:i+"px"},1);
		$(".sourceMaterialSync").eq(1).animate({width:i+"px"},1);
		$(".sourceMaterialSync").eq(1).animate({marginTop:((100-i)/4+50)+"px"},1);
		$(".sourceMaterialSync").eq(1).animate({marginLeft:((100-i)/4+50)+"px"},1);
		
		$(".sourceMaterialSync").eq(2).animate({height:i+"px"},1);
		$(".sourceMaterialSync").eq(2).animate({width:i+"px"},1);
		$(".sourceMaterialSync").eq(2).animate({marginTop:((100-i)/4+50)+"px"},1);
		$(".sourceMaterialSync").eq(2).animate({marginLeft:((100-i)/4+50)+"px"},1);
		
		$(".sourceMaterialSync").eq(3).animate({height:i+"px"},1);
		$(".sourceMaterialSync").eq(3).animate({width:i+"px"},1);
		$(".sourceMaterialSync").eq(3).animate({marginTop:((100-i)/4+50)+"px"},1);
		$(".sourceMaterialSync").eq(3).animate({marginLeft:((100-i)/4+50)+"px"},1);
		
		$(".sourceMaterialSync").eq(4).animate({height:i+"px"},1);
		$(".sourceMaterialSync").eq(4).animate({width:i+"px"},1);
		$(".sourceMaterialSync").eq(4).animate({marginTop:((100-i)/4+50)+"px"},1);
		$(".sourceMaterialSync").eq(4).animate({marginLeft:((100-i)/4+50)+"px"},1);
	}
	
	function asyncSourceMaterial(index){
		return function(){  
			if($(".progressBarInDraw").eq(0).width()<800){
				$.ajax({
				  type: 'POST',
				  url: "wechat",
				  data: "event=asyncSourceMaterial&num=" + index,
				  dataType : "json",  
				  cache:false, 
				  success: function(data){
					  var arrayData=eval(data);
					  console.log(arrayData);
					  $(".progressBarP").eq(0).html(arrayData["progressDescribe"]);
					  $(".progressBarInDraw").eq(0).css("width",(arrayData["progressValue"]*800/100)+"px");
		        },
		        error: function(jqXHR, error, errorThrown) {
		           alert(jqXHR.status);          
		            }
				  
				});
			}else{
				clearInterval(int);
				$(".progressBar").eq(0).css("display","none");
				$(".coverSync").eq(0).css("display","none");
				$(".progressBarInDraw").eq(0).css("width",0+"px");
				
			}
	    }  
	}
}else if(type=="userDefinedMenu"){
	$.ajax({
		  type: 'POST',
		  url: "wechat",
		  data: "event=getMenu&fresh=" + Math.random(),
		  dataType : "json",  
		  cache:false, 
		  success: function(data){
			 var arr=eval(data);
			 console.log(arr);
			 var textJson =JSON.stringify(data);

			 $(".textareaJson").val(textJson);
		  },
			
			 
      error: function(jqXHR, error, errorThrown) {
          alert(jqXHR.status);          
          }
		  
		});
}


//素材管理模块
$(document).on("click",".sourceMaterial_title_li",function(){
	var index=$(this).index();
	for(var i=0;i<$(".sourceMaterial_title_li").length;i++){
		var constrat=i-index;
		$(".bodyer_right_external_div").eq(i).css("-webkit-transform"," rotateX("+constrat*90+"deg)")
	}
});

$(document).on("click",".return_menu",function(){
	window.location.href="./menu?type=menu";
})
$(document).on("")



