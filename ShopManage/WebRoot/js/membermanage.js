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
