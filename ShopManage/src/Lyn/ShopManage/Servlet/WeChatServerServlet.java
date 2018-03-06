package Lyn.ShopManage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import Lyn.ShopManage.util.LogPrintFormat;
import Lyn.ShopManage.util.WeChatCheckUtil;
import Lyn.ShopManage.util.WeChatMessageUtil;


public class WeChatServerServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WeChatServerServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String signature =request.getParameter("signature");
		String timestamp =request.getParameter("timestamp");
		String nonce =request.getParameter("nonce");
		String echostr =request.getParameter("echostr");
		if(signature!=null && timestamp !=null && nonce !=null && echostr!=null){
			PrintWriter writer = response.getWriter();
			
			LogPrintFormat.logPrint("Lyn", "WeChat:"+signature+" "+timestamp+" "+nonce+" "+echostr);
			if (WeChatCheckUtil.checkSignature(signature,timestamp,nonce)){
				LogPrintFormat.logPrint("Lyn", "WeChat:"+" Generate signature ID:"+signature+";timestamp ID:"+timestamp+";nonce ID:"+nonce+";echostr ID:"+echostr);
				writer.print(echostr);
			}
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =  response.getWriter();
		try {
			Map<String,String> map = WeChatMessageUtil.xmlToMap(request);
			LogPrintFormat.logPrint("Lyn", "-------------------------------------------");
			for(Entry<String,String>entry:map.entrySet()){
				LogPrintFormat.logPrint("Lyn", "WeChat received:"+entry.getKey()+" "+entry.getValue());
			}
			LogPrintFormat.logPrint("Lyn", "-------------------------------------------");
			
			String toUserName =  map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			String msgId = map.get("MsgId");
			String message =null;
			
			if(WeChatMessageUtil.MESSAGE_TEXT.equals(msgType)){
				message =WeChatMessageUtil.initText( msgId, toUserName, fromUserName, content);
			}else if(WeChatMessageUtil.MESSAGE_EVENT.equals(msgType)){
				String event=map.get("Event");
				if(WeChatMessageUtil.MESSAGE_SUBSCRIBE.equals(event)){
					message = WeChatMessageUtil.subscribe( msgId, toUserName, fromUserName,"subscribe");
				}else if(WeChatMessageUtil.MESSAGE_UNSUBSCRIBE.equals(event)){
					message = WeChatMessageUtil.subscribe( msgId, toUserName, fromUserName,"unsubscribe");
				}else if(WeChatMessageUtil.MESSAGE_CLICK.toUpperCase().equals(event)){
					String eventKey=map.get("EventKey");
					message = WeChatMessageUtil.clickEvent( msgId, toUserName, fromUserName,eventKey);
				}
			}
			System.out.println(message);
			
			out.print(message);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
		
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
