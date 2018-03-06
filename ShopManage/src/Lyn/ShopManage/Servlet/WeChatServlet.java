package Lyn.ShopManage.Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import sun.misc.BASE64Encoder;

import Lyn.ShopManage.dao.WeChatProgressBarDao;
import Lyn.ShopManage.dao.WeChat_EventDao;
import Lyn.ShopManage.dao.WeChat_ImageSourceMaterialDao;
import Lyn.ShopManage.dao.WeChat_KWReplyTextRuleDao;
import Lyn.ShopManage.dao.WeChat_MediaIdSourceMaterialDao;
import Lyn.ShopManage.dao.WeChat_MenuDao;
import Lyn.ShopManage.dao.WeChat_VideoSourceMaterialDao;
import Lyn.ShopManage.entity.KWReplyTextRule;
import Lyn.ShopManage.entity.ProgressBar;
import Lyn.ShopManage.entity.WeChatEvent;
import Lyn.ShopManage.entity.WeChatImageSourceMaterial;
import Lyn.ShopManage.entity.WeChatNewsMaterial;
import Lyn.ShopManage.entity.WeChatVideoSourceMaterial;
import Lyn.ShopManage.util.BashPath;
import Lyn.ShopManage.util.CommonHtml;
import Lyn.ShopManage.util.JsonSplitNull;
import Lyn.ShopManage.util.LogPrintFormat;
import Lyn.ShopManage.util.MysqlUtil;
import Lyn.ShopManage.util.WeChatUtil;

public class WeChatServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WeChatServlet() {
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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");  
		HttpSession session = request.getSession();
		String type = request.getParameter("type")!=null?request.getParameter("type"):"null";
		String event = request.getParameter("event")!=null?request.getParameter("event"):"null";
		String rootPath = this.getServletConfig().getServletContext().getRealPath("/WEB-INF");
		String weChatSourceMaterialPath=this.getServletConfig().getServletContext().getRealPath("/WeChatSourceMaterial").toString();
		String right_content = new String();
		String rightCss = new String();
		
		right_content="<image style=\"margin-top:50px;margin-left:100px;width:800px;height:480px; background-repeat:no-repeat; background-size:100% 100%;-moz-background-size:100% 100%;background-image:url(./image/wechat/wechatFrameWork.png)\"></image>";
		String num=request.getParameter("num")!=null?request.getParameter("num"):"null";
		//读取type数据，并渲染到模板文件中
		if (!type.equals("null")) {
			File file = new File(rootPath + BashPath.ResponseHtml + "WeChat/"
					+ type + ".html");
			RandomAccessFile randomFile = new RandomAccessFile(file, "r");
			String tmpLine = null;
			right_content="";
			while ((tmpLine = randomFile.readLine()) != null) {
				right_content += new String(tmpLine.getBytes("ISO-8859-1"),
						"utf-8");
			}

			file = null;
			file = new File(rootPath + BashPath.ResponseHtml + "WeChat/css/"
					+ type + ".css");
			randomFile = new RandomAccessFile(file, "r");
			while ((tmpLine = randomFile.readLine()) != null) {
				rightCss += new String(tmpLine.getBytes("ISO-8859-1"), "utf-8");
			}
		}
		
		if (session.getAttribute("authority") != null) {
			request.setAttribute("right_content", right_content);
			request.setAttribute("rightCss", rightCss);
			request.setAttribute("navigateHtml", CommonHtml.navigateHtml);
			request.setAttribute("navigateCss", CommonHtml.navigateCss);
			request.setAttribute("title", "微信公众号管理");
			JSONArray jsonObject =null;
			if (session.getAttribute("authority").equals("0") && type.equals("null") &&event.equals("null")) {
				LogPrintFormat.logPrint("Lyn", "进入微信管理");
				request.getRequestDispatcher("/wechat.jsp").forward(request,response);
			} else if (session.getAttribute("authority").equals("0")
					&& type.equals("userDefinedReply")) {
				LogPrintFormat.logPrint("Lyn", "进入微信管理 自定义回复模块");
				request.getRequestDispatcher("/wechat.jsp").forward(request,response);
			} else if (session.getAttribute("authority").equals("0")
					&& type.equals("userDefinedMenu")) {
				LogPrintFormat.logPrint("Lyn", "进入微信管理 自定义菜单");
				request.getRequestDispatcher("/wechat.jsp").forward(request,response);
			} else if (session.getAttribute("authority").equals("0")
					&& type.equals("serverConfig")) {
				LogPrintFormat.logPrint("Lyn", "进入微信管理 服务器配置");
				try {
					WeChatUtil.sourceMaterialSyncEnd("WeChatSourceMaterialSync");
					WeChatUtil.sourceMaterialSyncEnd("WeChatNewsSourceMaterialSync");
					WeChatUtil.sourceMaterialSyncEnd("WeChatImageSourceMaterialSync");
					WeChatUtil.sourceMaterialSyncEnd("WeChatVoiceSourceMaterialSync");
					WeChatUtil.sourceMaterialSyncEnd("WeChatVideoSourceMaterialSync");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.getRequestDispatcher("/wechat.jsp").forward(request,response);
			}else if (session.getAttribute("authority").equals("0")&& event.equals("addKeywordReply")){
				boolean isTrue = false; 
				try {
					isTrue=addKeywordReply(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(isTrue==true){ 	
					request.getRequestDispatcher("/wechat.jsp?type=userDefinedReply").forward(request,response);
				}else{
					request.getRequestDispatcher("/wechat.jsp?type=userDefinedReply").forward(request,response);
				}
				
			}else if(session.getAttribute("authority").equals("0")&& event.equals("followSubscribe")){
				String followTextTextarea=request.getParameter("followTextTextarea")!=null?request.getParameter("followTextTextarea"):"null";
				String followMediaId=request.getParameter("followMediaId")!=null?request.getParameter("followMediaId"):"null";
				String mediaType=request.getParameter("mediaType")!=null?request.getParameter("mediaType"):"null";
				if(!followTextTextarea.equals("null")&&!followTextTextarea.equals("")){
					WeChatEvent wce=new WeChatEvent();
					wce.setReplyType("text");
					wce.setEvetReply((String)request.getParameter("followTextTextarea"));
					WeChat_EventDao ed= new WeChat_EventDao();
					try {
						ed.setSubscribe(wce);
						LogPrintFormat.logPrint("Lyn", "修改微信关注回复成功");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(!followMediaId.equals("null")&&!followMediaId.equals("")&&!mediaType.equals("")&&!mediaType.equals("null")){
					WeChatEvent wce=new WeChatEvent();
					wce.setReplyType(mediaType);
					wce.setEvetReply(followMediaId);
					WeChat_EventDao ed= new WeChat_EventDao();
					try {
						ed.setSubscribe(wce);
						LogPrintFormat.logPrint("Lyn", "修改微信关注回复成功");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				request.getRequestDispatcher("/wechat.jsp?type=userDefinedReply").forward(request,response);
				
			}else if (session.getAttribute("authority").equals("0")&& event.equals("init")){
				//ajax请求该接口获取关键字回复数据
				WeChat_KWReplyTextRuleDao kw = new WeChat_KWReplyTextRuleDao();
				jsonObject =JSONArray.fromObject(kw.selectAllData());
				LogPrintFormat.logPrint("Lyn", "ajax 获取关键字规则数据");
				kw = null;
				PrintWriter out=response.getWriter();
				out.println(jsonObject);
				out.flush();
				out.close();
			}else if (session.getAttribute("authority").equals("0")&& event.equals("event")){
				WeChat_EventDao eventDao=new WeChat_EventDao();
				ArrayList<WeChatEvent> eventList=eventDao.selectEventTypeData("click");
				JSONArray json=JSONArray.fromObject(eventList);
				LogPrintFormat.logPrint("Lyn", "ajax 获取事件回复规则数据");
				PrintWriter out=response.getWriter();
				out.println(json);
				out.flush();
				out.close();
			}else if(session.getAttribute("authority").equals("0")&& event.equals("getMenu")){
				WeChat_MenuDao menuDao=new WeChat_MenuDao();
				String menu=menuDao.select();
				PrintWriter out=response.getWriter();
				out.println(menu);
				out.flush();
				out.close();
			}else if(session.getAttribute("authority").equals("0")&& event.equals("subscribe")){
				//ajax请求该接口获取关注信息数据
				WeChat_EventDao ed=new WeChat_EventDao();
				WeChatEvent wce=null;
				wce=ed.getSubscribe();
				jsonObject=JSONArray.fromObject(wce);
				LogPrintFormat.logPrint("Lyn", "ajax 获取关注回复信息数据");
				ed=null;
				PrintWriter out=response.getWriter();
				out.print(jsonObject);
				out.flush();
				out.close();
			}else if(session.getAttribute("authority").equals("0")&& event.equals("alterMenu")){
				
				String jsonStr=request.getParameter("textJson")!=null?request.getParameter("textJson"):"null";
				WeChat_MenuDao menuDao=new WeChat_MenuDao();
				try{
					JSONObject jsStr = JSONObject.fromObject(jsonStr);
					menuDao.update(jsonStr);
					if(WeChatUtil.creatMenu(WeChatUtil.verifyAccessToken(), jsonStr)==0){
						LogPrintFormat.logPrint("Lyn", "成功修改微信菜单");
					}else{
						LogPrintFormat.logPrint("Lyn", "微信菜单修改失败");
					}
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				request.getRequestDispatcher("/wechat.jsp?type=userDefinedMenu").forward(request,response);
				
				
			}else if(session.getAttribute("authority").equals("0")&& event.equals("addevent")){
				int result=addEvent(request, response);
				if(result==0){
					LogPrintFormat.logPrint("Lyn", "成功添加一条事件回复");
				}else{
					LogPrintFormat.logPrint("Lyn", "添加一条事件回复失败");
				}
				request.getRequestDispatcher("/wechat.jsp?type=userDefinedReply").forward(request,response);
			}else if(session.getAttribute("authority").equals("0")&&type.equals("sourceMaterial")){
				//素材管理响应

				LogPrintFormat.logPrint("Lyn", "进入微信管理 素材管理模块");
				
				request.getRequestDispatcher("/wechat.jsp").forward(request,response);
			}else if(session.getAttribute("authority").equals("0")&&event.equals("newsSourceMaterial")){
				//ajax请求该接口获取永久图文信息
				WeChat_MediaIdSourceMaterialDao msmd=new WeChat_MediaIdSourceMaterialDao();
				ArrayList<WeChatNewsMaterial> newsList=new ArrayList<WeChatNewsMaterial>();
				ArrayList<String> mediaIdList=msmd.useTypeGetMedia("news");
				Iterator<String> it =mediaIdList.iterator();
				while(it.hasNext()){
					newsList.add(msmd.useNewsMediaIdGetNews(it.next()));
				}
				JSONArray json=JSONArray.fromObject(newsList);
				json=JsonSplitNull.regularJsonSplitNull(json);
				PrintWriter out=response.getWriter();
				out.println(json);
				out.flush();
				out.close();
				LogPrintFormat.logPrint("Lyn", "ajax 获取图文永久素材信息数据");
			}else if(session.getAttribute("authority").equals("0")&&event.equals("imageSourceMaterial")){
				WeChat_ImageSourceMaterialDao wcismd=new WeChat_ImageSourceMaterialDao();
				ArrayList<WeChatImageSourceMaterial> wcismList=new ArrayList<WeChatImageSourceMaterial>();
				wcismList=wcismd.getAllData();
				JSONArray json=JSONArray.fromObject(wcismList);
				json=JsonSplitNull.regularJsonSplitNull(json);
				PrintWriter out=response.getWriter();
				out.println(json);
				out.flush();
				out.close();
				
				LogPrintFormat.logPrint("Lyn", "ajax 获取图片永久素材信息数据");
			}else if(session.getAttribute("authority").equals("0")&&event.equals("videoSourceMaterial")){
				WeChat_VideoSourceMaterialDao wcismd=new WeChat_VideoSourceMaterialDao();
				ArrayList<WeChatVideoSourceMaterial> wcismList=new ArrayList<WeChatVideoSourceMaterial>();
				wcismList=wcismd.getAllData();
				JSONArray json=JSONArray.fromObject(wcismList);
				json=JsonSplitNull.regularJsonSplitNull(json);
				PrintWriter out=response.getWriter();
				out.println(json);
				out.flush();
				out.close();
				LogPrintFormat.logPrint("Lyn", "ajax 获取视频永久素材信息数据");
			}else if(session.getAttribute("authority").equals("0")&&event.equals("asyncSourceMaterial")&&num.equals("0")){
				WeChatProgressBarDao wcpb=new WeChatProgressBarDao();
				ProgressBar pb=wcpb.useNameGetData("WeChatSourceMaterialSync");
				if(pb.getProgressLock().equals("0") ){
					//开始执行程序
					try {
						WeChatUtil.sourceMaterialSync(weChatSourceMaterialPath);
						PrintWriter out=response.getWriter();
						JSONObject json =JSONObject.fromObject(pb); 
						out.println(json);
						out.flush();
						out.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(pb.getProgressLock().equals("1")){
					//读取进度表
					PrintWriter out=response.getWriter();
					JSONObject json =JSONObject.fromObject(pb); 
					out.println(json);
					out.flush();
					out.close();
					
				}else{
					//未知异常，数据库数据混乱
				}
				;
				
			}else if(session.getAttribute("authority").equals("0")&&event.equals("asyncSourceMaterial")&&num.equals("1")){
				WeChatProgressBarDao wcpb=new WeChatProgressBarDao();
				ProgressBar pb=wcpb.useNameGetData("WeChatNewsSourceMaterialSync");
				if(pb.getProgressLock().equals("0") ){
					//开始执行程序
					try {
						WeChatUtil.syncNewsSourceMaterial(wcpb);
						
						PrintWriter out=response.getWriter();
						JSONObject json =JSONObject.fromObject(pb); 
						out.println(json);
						out.flush();
						out.close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(pb.getProgressLock().equals("1")){
					//读取进度表
					PrintWriter out=response.getWriter();
					JSONObject json =JSONObject.fromObject(pb); 
					out.println(json);
					out.flush();
					out.close();
					
				}else{
					//未知异常，数据库数据混乱
				}
				;
			}else if(session.getAttribute("authority").equals("0")&&event.equals("asyncSourceMaterial")&&num.equals("2")){
				WeChatProgressBarDao wcpb=new WeChatProgressBarDao();
				ProgressBar pb=wcpb.useNameGetData("WeChatImageSourceMaterialSync");
				if(pb.getProgressLock().equals("0") ){
					//开始执行程序
					try {
						WeChatUtil.syncImagesSourceMaterial(wcpb,weChatSourceMaterialPath);
						PrintWriter out=response.getWriter();
						JSONObject json =JSONObject.fromObject(pb); 
						out.println(json);
						out.flush();
						out.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(pb.getProgressLock().equals("1")){
					//读取进度表
					PrintWriter out=response.getWriter();
					JSONObject json =JSONObject.fromObject(pb); 
					out.println(json);
					out.flush();
					out.close();
					
				
				}else{
					//未知异常，数据库数据混乱
				}
				;
			}else if(session.getAttribute("authority").equals("0")&&event.equals("asyncSourceMaterial")&&num.equals("3")){
				WeChatProgressBarDao wcpb=new WeChatProgressBarDao();
				ProgressBar pb=wcpb.useNameGetData("WeChatVoiceSourceMaterialSync");
				if(pb.getProgressLock().equals("0") ){
					//开始执行程序
					try {
						WeChatUtil.syncVoiceSourceMaterial(wcpb,weChatSourceMaterialPath);
						PrintWriter out=response.getWriter();
						JSONObject json =JSONObject.fromObject(pb); 
						out.println(json);
						out.flush();
						out.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(pb.getProgressLock().equals("1")){
					//读取进度表
					PrintWriter out=response.getWriter();
					JSONObject json =JSONObject.fromObject(pb); 
					out.println(json);
					out.flush();
					out.close();
					
					
				}else{
					//未知异常，数据库数据混乱
				}
				;
			}else if(session.getAttribute("authority").equals("0")&&event.equals("asyncSourceMaterial")&&num.equals("4")){
				WeChatProgressBarDao wcpb=new WeChatProgressBarDao();
				ProgressBar pb=wcpb.useNameGetData("WeChatVideoSourceMaterialSync");
				if(pb.getProgressLock().equals("0") ){
					//开始执行程序
					try {
						WeChatUtil.syncVideoSourceMaterial(wcpb,weChatSourceMaterialPath);
						PrintWriter out=response.getWriter();
						JSONObject json =JSONObject.fromObject(pb); 
						out.println(json);
						out.flush();
						out.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(pb.getProgressLock().equals("1")){
					//读取进度表
					PrintWriter out=response.getWriter();
					JSONObject json =JSONObject.fromObject(pb); 
					out.println(json);
					out.flush();
					out.close();
					
					
				}else{
					//未知异常，数据库数据混乱
				}
				;
			}else{
				LogPrintFormat.logPrint("Lyn", "IP:" + request.getRemoteAddr()+" 非法登陆微信管理");
				response.sendRedirect(request.getContextPath() + "/sessionInvalid.jsp");
			}
		} else {
			LogPrintFormat.logPrint("Lyn", "IP:" + request.getRemoteAddr()+" 非法登陆微信管理");
			response.sendRedirect(request.getContextPath() + "/sessionInvalid.jsp");
		}
	}



	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	public int addEvent(HttpServletRequest request,HttpServletResponse response){
		String eventCode=request.getParameter("eventCode")!=null?request.getParameter("eventCode"):"null";
		String eventMediaId=request.getParameter("eventMediaId")!=null?request.getParameter("eventMediaId"):"null";
		String mediaType=request.getParameter("mediaType")!=null?request.getParameter("mediaType"):"null";
		if(!eventCode.equals("")&&!eventCode.equals("null")&&!eventMediaId.equals("")&&!eventMediaId.equals("null")
				&&!mediaType.equals("")&&!mediaType.equals("null")){
			WeChatEvent event=new WeChatEvent();
			WeChat_EventDao evenDao=new WeChat_EventDao();
			event.setEventCode(eventCode);
			event.setEventType("click");
			event.setEvetReply(eventMediaId);
			event.setReplyType(mediaType);
			evenDao.insertOneData(event);
			return 0;
		}
		return 1;
	
	
	}
	
	public boolean  addKeywordReply(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String ruleName=request.getParameter("ruleName")!=null?request.getParameter("ruleName"):"null";
		String match=request.getParameter("match")!=null?request.getParameter("match"):"null";
		String saytext=request.getParameter("saytext")!=null?request.getParameter("saytext"):"null";
		String imageMediaId=request.getParameter("imageMediaId")!=null?request.getParameter("imageMediaId"):"null";
		String videoMediaId=request.getParameter("videoMediaId")!=null?request.getParameter("videoMediaId"):"null";
		String newsMediaId=request.getParameter("newsMediaId")!=null?request.getParameter("newsMediaId"):"null";
		
		String keyWord=request.getParameter("keyword")!=null?request.getParameter("keyword"):"null";
		if(!ruleName.equals("null")&&!match.equals("null")&&!saytext.equals("null")&&!saytext.equals("")){
			KWReplyTextRule kwrtr = new KWReplyTextRule(ruleName, keyWord, saytext, match);
			String tmp;
			if((tmp=kwrtr.saveRule()).equals("null")){
				LogPrintFormat.logPrint("Lyn", "插入微信公众号关键字回复文本规则表成功 TEXT类型回复");
				return true;
			}else{
				LogPrintFormat.logPrint("Lyn", "插入微信公众号关键字回复文本规则表失败"+tmp);
				return false;
			}
		}else if(!ruleName.equals("null")&&!match.equals("null")&&!imageMediaId.equals("null")&&!imageMediaId.equals("")){
			KWReplyTextRule kwrtr = new KWReplyTextRule(ruleName, keyWord, "mediaId Image "+imageMediaId, match);
			String tmp;
			if((tmp=kwrtr.saveRule()).equals("null")){
				LogPrintFormat.logPrint("Lyn", "插入微信公众号关键字回复文本规则表成功 IMAGE类型回复");
				return true;
			}else{
				LogPrintFormat.logPrint("Lyn", "插入微信公众号关键字回复文本规则表失败"+tmp);
				return false;
			}
		}else if(!ruleName.equals("null")&&!match.equals("null")&&!videoMediaId.equals("null")&&!videoMediaId.equals("")){
			KWReplyTextRule kwrtr = new KWReplyTextRule(ruleName, keyWord, "mediaId Video "+videoMediaId, match);
			String tmp;
			if((tmp=kwrtr.saveRule()).equals("null")){
				LogPrintFormat.logPrint("Lyn", "插入微信公众号关键字回复文本规则表成功 Video类型回复");
				return true;
			}else{
				LogPrintFormat.logPrint("Lyn", "插入微信公众号关键字回复文本规则表失败"+tmp);
				return false;
			}
		}else if(!ruleName.equals("null")&&!match.equals("null")&&!newsMediaId.equals("null")&&!newsMediaId.equals("")){
			KWReplyTextRule kwrtr = new KWReplyTextRule(ruleName, keyWord, "mediaId News "+newsMediaId, match);
			String tmp;
			if((tmp=kwrtr.saveRule()).equals("null")){
				LogPrintFormat.logPrint("Lyn", "插入微信公众号关键字回复文本规则表成功 News类型回复");
				return true;
			}else{
				LogPrintFormat.logPrint("Lyn", "插入微信公众号关键字回复文本规则表失败"+tmp);
				return false;
			}
		}
		return false;
		
	}
}


