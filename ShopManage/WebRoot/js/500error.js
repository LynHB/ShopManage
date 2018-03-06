var height=document.body.clientHeight;
var proportion=height/640;
document.getElementsByClassName("backgroundImage")[0].style.height=height+"px";
document.getElementsByClassName("backgroundImage")[0].style.width=proportion*960+"px";

document.getElementsByClassName("errorCode")[0].style.top=proportion*150+"px";
document.getElementsByClassName("errorCode")[0].style.right=proportion*100+"px";
document.getElementsByClassName("errorCode")[0].style.fontSize=proportion*100+"px";

document.getElementsByClassName("errorExplain")[0].style.top=proportion*300+"px";
document.getElementsByClassName("errorExplain")[0].style.right=proportion*100+"px";
document.getElementsByClassName("errorExplain")[0].style.fontSize=proportion*20+"px";
document.getElementsByClassName("errorExplain")[0].style.height=proportion*300+"px";
document.getElementsByClassName("errorExplain")[0].style.width=proportion*200+"px";

document.getElementsByClassName("errorReturn")[0].style.top=proportion*500+"px";
document.getElementsByClassName("errorReturn")[0].style.right=proportion*100+"px";
document.getElementsByClassName("errorReturn")[0].style.fontSize=proportion*20+"px";
document.getElementsByClassName("errorReturn")[0].style.height=proportion*50+"px";
document.getElementsByClassName("errorReturn")[0].style.width=proportion*200+"px";

document.getElementsByClassName("errorReturn")[0].onclick=function(){
	window.history.go(-1);
}
