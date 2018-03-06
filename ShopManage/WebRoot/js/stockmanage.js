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
