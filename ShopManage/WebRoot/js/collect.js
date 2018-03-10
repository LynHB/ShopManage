var my3dMenu=document.getElementsByClassName("my-3D-menu")[0];
var my3dTitle=document.getElementsByClassName("my-3D-title")[0];
var audio = document.getElementsByClassName('backgroundMusic')[0];
document.onreadystatechange = completeLoading;
var windowHeight=$(window).height();

//当 DOM（文档对象模型） 已经加载，并且页面（包括图像）已经完全呈现时，会发生 ready 事件
$(document).ready(function(){
	var windowHeight=$(window).height();
	
	
	//文字特效
	$(".my-photo-title").eq(0).css("top","20%");
	$(".my-photo-content").eq(0).css("top","60%");
	$(".my-video-button").eq(0).fadeIn(2000);
	
	//画布旋转特效
	$(".my-photo-content").eq(0).css("-webkit-transform","rotateX(360deg)");
});

function completeLoading() {
//文档和所有子资源已完成加载 执行动作
    if (document.readyState == "complete") {
    		//初始化3D画布高度、origin信息
    		my3dMenu.style.cssText="-webkit-transform-origin:"+((windowHeight-50)/2)+"px "+((windowHeight-50)/2)+"px -"+((windowHeight-50)/2)+"px;";
    		my3dTitle.style.cssText="-webkit-transform-origin:"+((windowHeight-50)/2)+"px "+((windowHeight-50)/2)+"px -"+((windowHeight-50)/2)+"px;";
    		$(".my-3D-title").eq(0).css("height",windowHeight-50);
    		$(".my-3D-menu").eq(0).css("height",windowHeight-50);
    		$(".my-3DSpace").eq(0).css("height",windowHeight-50);
    		
    		
    }

}

//监听滚轴事件
$(document).on("mousewheel DOMMouseScroll", function (e) {   
    var delta = (e.originalEvent.wheelDelta && (e.originalEvent.wheelDelta > 0 ? 1 : -1)) ||  // chrome & ie &其它  
                (e.originalEvent.detail && (e.originalEvent.detail > 0 ? -1 : 1));              // firefox  
  
    //var delta = (event.originalEvent.wheelDelta) ? event.originalEvent.wheelDelta : -(event.originalEvent.detail || 0); //合并写法  
    
    if (delta > 0) {  
       //向上滚
    	$(".my-3D-menu").eq(0).css("-webkit-transform","rotateX(0deg)");
    	$(".my-3D-title").eq(0).css("-webkit-transform","rotateX(-90deg)");
    	
    	//文字特效
    	$(".my-photo-title").eq(0).css("top","20%");
    	$(".my-photo-content").eq(0).css("top","60%");
    	$(".my-video-button").eq(0).fadeIn(2000);
    	
    	//画布旋转特效
    	$(".my-photo-content").eq(0).css("-webkit-transform","rotateX(360deg)");
    	$(".my-btn").fadeOut(1000);
    } else if (delta < 0) {  
        // 向下滚  
    	$(".my-3D-title").eq(0).css("-webkit-transition","-webkit-transform 1s linear");
    	$(".my-3D-menu").eq(0).css("-webkit-transform","rotateX(90deg)");
    	$(".my-3D-title").eq(0).css("-webkit-transform","rotateX(0deg)");
    	
    	$(".my-photo-title").eq(0).css("top","100%");
		$(".my-photo-content").eq(0).css("top","-20%");
		//画布旋转特效
    	$(".my-photo-content").eq(0).css("-webkit-transform","rotateX(0deg)");
    	
    	//太阳出来东方红！
    	var sunPri="-"+(windowHeight*0.5-25)+"px";
 
    	if($(".my-sun").css("top")==sunPri){
    		audio.play();
    	}
    	$(".my-sun").css("top","0%");
    	$(".my-btn").fadeIn(5000);
    	
    }  
});  



//绘制太阳动画
var canvas = document.getElementById("my-sun");
var context = canvas.getContext("2d");
context.save();


//太阳脸
context.save();
context.beginPath();
context.translate(150,150);
context.arc(0,0,80,0,2*Math.PI);
context.fillStyle="#FFFF00";
context.fill();

context.restore();
context.save();
//太阳眼睛
context.beginPath();
context.translate(150,150);
context.arc(-40,-40,10,0,2*Math.PI);
context.fillStyle="#242424";
context.fill();

context.restore();
context.save();

context.beginPath();
context.translate(150,150);
context.arc(40,-40,10,0,2*Math.PI);
context.fillStyle="#242424";
context.fill();

context.restore();
context.save();


//太阳的边
var canvas2 = document.getElementById("my-sun2");
var context2 = canvas2.getContext("2d");
context2.translate(150,150); 
for (var i = 0; i < 5; i++) {
	 context2.lineTo(Math.cos((18+i*72)/180*Math.PI)*130,-Math.sin((18+i*72)/180*Math.PI)*130);
	 context2.lineTo(Math.cos((54+i*72)/180*Math.PI)*80,-Math.sin((54+i*72)/180*Math.PI)*80);
}
context2.closePath();
context2.fillStyle="#FF0000";
context2.fill();


//太阳嘴巴
var canvas3 = document.getElementById("my-sun3");
var context3 = canvas3.getContext("2d");
context3.beginPath();
context3.translate(150,150);
context3.lineWidth="3";
context3.strokeStyle="#FF0000";
context3.bezierCurveTo(-60,10,0,70,60,10);
context3.closePath();
context3.fillStyle="#FF0000";
context3.fill();
context3.stroke();

//太阳嘴巴2
var canvas4 = document.getElementById("my-sun4");
var context4 = canvas4.getContext("2d");
context4.beginPath();
context4.translate(150,150);
context4.lineWidth="3";
context4.strokeStyle="#FF0000";
context4.quadraticCurveTo(-40,10,40,10);
context4.fillStyle="#FF0000";
context4.fill();
context4.stroke();

//太阳嘴巴3
var canvas5 = document.getElementById("my-sun5");
var context5 = canvas5.getContext("2d");
context5.beginPath();
context5.translate(150,150);
context5.lineWidth="3";
context5.strokeStyle="#FF0000";
context5.bezierCurveTo(-50,10,0,30,50,10);
context5.closePath();
context5.fillStyle="#FF0000";
context5.fill();
context5.stroke();

var mouse=0;
function sunMouse(){
	var index=$("#my-sun4").css("z-index");
	$("#my-sun4").css("z-index",$("#my-sun3").css("z-index"));
	$("#my-sun3").css("z-index",$("#my-sun5").css("z-index"));
	$("#my-sun5").css("z-index",index);
	if(mouse==0){
		$("#my-sun4").css("z-index",10);
		$("#my-sun3").css("z-index",200);
		$("#my-sun5").css("z-index",10);
	}else if(mouse==1){
		$("#my-sun4").css("z-index",10);
		$("#my-sun3").css("z-index",10);
		$("#my-sun5").css("z-index",200);
	}else if(mouse==2){
		$("#my-sun4").css("z-index",200);
		$("#my-sun3").css("z-index",10);
		$("#my-sun5").css("z-index",10);
	}else if(mouse==3){
		$("#my-sun4").css("z-index",10);
		$("#my-sun3").css("z-index",10);
		$("#my-sun5").css("z-index",200);
	}
	mouse++;
	if(mouse==4){
		mouse=0;
	}
}


//旋转的太阳
var sun_number=0;

function sunRotate(){
	$("#my-sun2").eq(0).css("-webkit-transform","rotateZ("+sun_number+"deg)");
	sun_number++;
}

var sun_timer=setInterval("sunMouse()",500);
var sun2_timer=setInterval("sunRotate()",10);
//音乐暂停播放
$(document).on("click","canvas",function(){
	if(audio!==null){             
	    //检测播放是否已暂停.audio.paused 在播放器播放时返回false.
	  if(audio.paused){                 
	      audio.play();//audio.play();// 这个就是播放  
	      sun_timer=setInterval("sunMouse()",500);
	      sun2_timer=setInterval("sunRotate()",10);
	      
	  }else{
	   audio.pause();// 这个就是暂停
	   clearInterval(sun_timer);
	   clearInterval(sun2_timer);
	  }
	}
});
