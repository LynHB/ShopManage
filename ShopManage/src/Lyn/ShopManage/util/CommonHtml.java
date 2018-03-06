package Lyn.ShopManage.util;

public class CommonHtml {
	public static final String navigateHtml="<div class=\"header_common\">"+
												"<ul>"+
													"<li><a href=\"\" class=\"header_common_ul_li_first\">店图标</a></li>"+
													"<li><a href=\"\">奶粉</a></li>"+
													"<li><a href=\"\">米粉</a></li>"+
													"<li><a href=\"\">保健品</a></li>"+
													"<li><a href=\"\">玩具</a></li>"+
													"<li><a href=\"\">纸尿布</a></li>"+
													"<li><a href=\"\">衣服</a></li>"+
													"<li><a href=\"\">技术支持</a></li>"+
													"<li><a href=\"\">搜索图标</a></li>"+
													"<li ><a href=\"\" class=\"header_common_ul_li_last\">购物篮图标</a></li>"+
												"</ul>"+
											"</div>";
	public static final String navigateCss=".header_common {width: 100%;height: 44px;background: rgba(0, 0, 0, 1);}"+
											".header_common ul {width: 1054px;height: 100%;text-align: center;margin: 0 auto;}"+
											".header_common ul li {display: inline-block;width: 100px;height: 100%;list-style: none;}"+
											".header_common ul li a {position: relative;top: 25%; /*偏移*/margin: 0 auto;color: white;text-decoration: none;}"+
											".header_common ul li a:hover {color: gray;}"+
											".header_common_ul_li_first {text-align: left;}"+
											".header_common_ul_li_last {text-align: right;}";
}
