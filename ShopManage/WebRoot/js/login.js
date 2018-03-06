$(document).ready(function () {
   // 记住用户名和密码
   $("#checkbox_input").click(function () {
    if ($("#userName").val() == ""||$("#userName").val() == "美贝ID") {
     alert("美贝ID不能为空！");
    }else if($("#passWord").val() == ""||$("#passWord").val() == "请输入您的密码"){
     alert("密码不能为空！");
    }else {
     if ($("#checkbox_input").prop("checked")) {
      setCookie("uname", $("#userName").val(), 60);
      setCookie("upwd", $("#passWord").val(), 60);
     }else {
      delCookie("uname");
      delCookie("upwd");
     }
    }
   });
	if (getCookie("uname") != null)
	   {
		$("#passWord").attr("type","password");
	    $('#checkbox_input').prop("checked", "checked");
		$('#userName').css("color","black");
	    $('#passWord').css("color","black");
	    $('#userName').val(getCookie("uname"));
	    $('#passWord').val(getCookie("upwd"));
	   }
	
  })
// 写cookies
  function setCookie(name, value) {
   var Days = 30;
   var exp = new Date();
   exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
   document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
  }
  // 读取cookies
  function getCookie(name) {
   var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
   if (arr = document.cookie.match(reg)) return unescape(arr[2]);
   else return null;
  }
  // 删除cookies
  function delCookie(name) {
   var exp = new Date();
   exp.setTime(exp.getTime() - 1);
   var cval = getCookie(name);
   if (cval != null) document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
  }
