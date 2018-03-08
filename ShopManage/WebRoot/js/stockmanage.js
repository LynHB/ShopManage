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
		  
		  $(".my-select").eq(0).append(text);
		 
    },
    error: function(jqXHR, error, errorThrown) {
        alert(jqXHR.status);          
    }
	  
});

//ajax获取所有商品信息
$.ajax({
	  type: 'POST',
	  url: "CStockManage",
	  data: "event=getAllShopping&fresh=" + Math.random(),
	  //dataType : "json",  
	  cache:false, 
	  success: function(data){
		  var arrayData=eval(data);
		  console.log(arrayData);
		  var text="";
		  for(var i=0;i<arrayData.length;i++){
			 text=text+"<tr>";
			 text=text+"<td>"+arrayData[i]["cid"]+"</td>";
			 text=text+"<td>"+arrayData[i]["name"]+"</td>";
			 text=text+"<td>"+arrayData[i]["stockBalance"]+"</td>";
			 text=text+"<td>"+arrayData[i]["stockSell"]+"</td>";
			 text=text+"<td>"+arrayData[i]["averageCost"]+"</td>";
			 text=text+"<td>"+arrayData[i]["marketingPrice"]+"</td>";
			 text=text+"<td>"+arrayData[i]["classification"]+"</td>";
			 text=text+"<td>"+arrayData[i]["specifications"]+"</td>";
			 text=text+"<td>"+arrayData[i]["brand"]+"</td>";
			 text=text+"<td>"+arrayData[i]["detail"]+"</td>";
			 
			 
			 text=text+"</tr>";
		  }
		  
		  $(".my-tbody").eq(0).append(text);
		 
  },
  error: function(jqXHR, error, errorThrown) {
      alert(jqXHR.status);          
  }
	  
});