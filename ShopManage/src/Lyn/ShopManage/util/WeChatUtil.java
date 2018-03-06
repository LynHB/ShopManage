package Lyn.ShopManage.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import sun.net.www.http.HttpClient;
import sun.security.provider.VerificationProvider;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import Lyn.ShopManage.dao.WeChatProgressBarDao;
import Lyn.ShopManage.dao.WeChat_AccessTokenDao;
import Lyn.ShopManage.dao.WeChat_ImageSourceMaterialDao;
import Lyn.ShopManage.dao.WeChat_MediaIdSourceMaterialDao;
import Lyn.ShopManage.dao.WeChat_VideoSourceMaterialDao;
import Lyn.ShopManage.dao.WeChat_VoiceSourceMaterialDao;
import Lyn.ShopManage.entity.ProgressBar;
import Lyn.ShopManage.entity.WeChatAccessToken;
import Lyn.ShopManage.entity.WeChatArtiles;
import Lyn.ShopManage.entity.WeChatButton;
import Lyn.ShopManage.entity.WeChatClickButton;
import Lyn.ShopManage.entity.WeChatImageSourceMaterial;
import Lyn.ShopManage.entity.WeChatMenu;
import Lyn.ShopManage.entity.WeChatNewsMaterial;
import Lyn.ShopManage.entity.WeChatVideoSourceMaterial;
import Lyn.ShopManage.entity.WeChatViewButton;
import Lyn.ShopManage.entity.WeChatVoiceSourceMaterial;

public class WeChatUtil {
	//private static final String APPID="wxf18136a87dee4730";
	//private static final String APPID = "wxecbeb7a6e241455a";
	private static final String APPID = "wxa32e9d3765d99ff4";
	//private static final String APPSECRET="cc133b5da47e3b9e7f5d022b2a4e0b25";
	//private static final String APPSECRET = "697275a4f2023a7c1160fed8fec36c3e";
	private static final String APPSECRET="800daa2ff3e9ce18c636cfdf0ff3969d";
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	// 永久素材获取
	private static final String GET_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
	private static final String GET_MATERIAL_LIST_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
	private static final String GET_MATERIAL_COUNTER_URL = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN";

	// 非图文消息，永久素材上传地址
	private static final String ADD_MATERIAL_COMMON_URL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";

	// 图文消息，永久素材上传地址
	private static final String ADD_MATERIAL_NEWS_URL = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";

	//获取用户列表
	private static final String GET_USERALL_OPENID="https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
	
	//消息群发
	private static final String SEND_MESSAGE="https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
	
	//预览消息群发
	private static final String PREVIEW_SEND_MESSAGE="https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";
	
	//消息发送全部或者分组发送
	private static final String SEND_ALL_MESSAGE="https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	
	//微信菜单查询
	
	private static final String SELECT_MENU="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	
	public static void main(String[] args) throws ClientProtocolException,
			IOException, SQLException {
			JSONObject json=JSONObject.fromObject(initSampleMenu());
			System.out.println(json);
		
	}

	public static String getMenu() throws ClientProtocolException, IOException{
		String url=SELECT_MENU.replaceAll("ACCESS_TOKEN", verifyAccessToken());
		JSONObject json=doGetStr(url);
		return json.toString();
	}
	
	/**
	 * 通过微信接口，获得accessToken字符串
	 * 
	 * @param None
	 * @return accessToken's String
	 * @author Lyn-Rebirth 2018.1.26
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String getAccessToken() throws ClientProtocolException,
			IOException {
		WeChatAccessToken at = new WeChatAccessToken();
		String url = ACCESS_TOKEN_URL.replaceAll("APPID", APPID).replaceAll(
				"APPSECRET", APPSECRET);
		JSONObject jObject = doGetStr(url);
		if (jObject != null) {
			if (!jObject.has("errcode")) {
				at.setExpiresIn(jObject.getInt("expires_in"));
				at.setToken(jObject.getString("access_token"));
				at.setTokenUpdateTime(String.valueOf(new Date().getTime()));
				at.setErrmsg("");
			} else {
				at.setErrcode(jObject.getInt("errcode"));
				at.setErrmsg(jObject.getString("errmsg"));
				at.setToken("");
				at.setTokenUpdateTime(String.valueOf(new Date().getTime()));

			}
		}
		WeChat_AccessTokenDao atd = new WeChat_AccessTokenDao();
		atd.setAccessToken(at);
		return at.getToken();
	}

	/**
	 * 判断accessToken是否在有效期内并获取accesstoken，如果没有则刷新accessToken
	 * 
	 * @return accessToken
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @author Lyn-Rebirth 2018.1.30
	 */
	public static String verifyAccessToken() throws ClientProtocolException,
			IOException {
		WeChat_AccessTokenDao atd = new WeChat_AccessTokenDao();
		WeChatAccessToken at = atd.getAccessToken();
		if ((Long.parseLong(at.getTokenUpdateTime()) + at.getExpiresIn()*1000) < new Date()
				.getTime()) {
			at.setToken(getAccessToken());
		}
		return at.getToken();
	}

	/**
	 * GET方式请求URL，并返回JSON对象
	 * 
	 * @param url
	 * @return JSONObject
	 * @author Lyn-Rebirth 2018.1.26
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static JSONObject doGetStr(String url)
			throws ClientProtocolException, IOException {
		HttpClientBuilder hcb = HttpClientBuilder.create();
		HttpGet hg = new HttpGet(url);
		JSONObject jObject = null;
		HttpResponse response = hcb.build().execute(hg);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity);
			jObject = JSONObject.fromObject(result);
		}
		return jObject;
	}
 
	/**
	 * POST方法请求URL，并返回JSON对象
	 * 
	 * @param String
	 *            url,String params
	 * @return JSONObject
	 * @author Lyn-Rebirth 2018.1.29
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static JSONObject doPostStr(String url, String params)
			throws ClientProtocolException, IOException {
		HttpClientBuilder hcb = HttpClientBuilder.create();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsObject = null;
		httpPost.setEntity(new StringEntity(params, "UTF-8"));
		HttpResponse response = hcb.build().execute(httpPost);
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");
		jsObject = JSONObject.fromObject(result);
		return jsObject;
	}

	/**
	 * 微信菜单列表初始化
	 * 
	 * @param None
	 * @return WeChatMenu
	 * @author Lyn-Rebirth 2018.1.26
	 */
	public static WeChatMenu initSampleMenu() {
		WeChatMenu menu = new WeChatMenu();
		WeChatClickButton button1 = new WeChatClickButton();
		button1.setName("门店详情");
		button1.setType("click");
		button1.setKey("door");

		WeChatViewButton button2 = new WeChatViewButton();
		button2.setName("历史消息");
		button2.setType("view");
		button2.setUrl("http://www.immoc.com");

		WeChatViewButton button3 = new WeChatViewButton();
		button3.setName("会员绑定");
		button3.setType("view");
		button3.setUrl("http://www.baidu.com");

		WeChatClickButton button4 = new WeChatClickButton();
		button4.setName("会员详情");
		button4.setType("click");
		button4.setKey("mem");

		WeChatButton button = new WeChatButton();
		button.setName("会员中心");
		button.setSub_button(new WeChatButton[] { button4, button3 });

		menu.setButton(new WeChatButton[] { button1, button2, button });

		return menu;

	}

	/**
	 * 微信菜单创建
	 * 
	 * @param token
	 * @param menu
	 * @return resultCode
	 * @author Lyn-Rebirth 2018.1.29
	 */
	public static int creatMenu(String token, String menu) {
		String url = CREATE_MENU_URL.replaceAll("ACCESS_TOKEN", token);
		JSONObject jsObject;
		int result = 0;
		try {
			jsObject = doPostStr(url, menu);
			if (jsObject != null) {
				result = jsObject.getInt("errcode");
				
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * 获得对应的永久素材列表
	 * 
	 * @param type
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @author Lyn-Rebirth 2018.1.31
	 */
	public static JSONObject getMaterialList(String type,int offset,int count)
			throws ClientProtocolException, IOException {
		String url = GET_MATERIAL_LIST_URL.replaceAll("ACCESS_TOKEN",
				verifyAccessToken());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type", type);
		jsonObject.put("offset", 0);
		jsonObject.put("count", 20);
		JSONObject jo = null;
		jo = doPostStr(url, jsonObject.toString());
		if (jo.get("errcode") != null) {
			LogPrintFormat.logPrint("Lyn", "获得微信素材列表失败，" + jo.get("errcode")
					+ " " + jo.get("errmsg"));
			return null;
		}

		return jo;

	}
	
	/**
	 * 获得视频永久素材地址
	 * @param media
	 * @param type
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @author Lyn-Rebirth 2018.2.7
	 */
	public static JSONObject materialDownload(JSONObject media,String type) throws ClientProtocolException, IOException{
		JSONObject json=null;
		if(type.equals("video")){
			String urlStr = GET_MATERIAL_URL.replaceAll("ACCESS_TOKEN",verifyAccessToken());
			json=doPostStr(urlStr, media.toString());
		}else{
			
		}
		return json;
	}
	
	/**
	 * 下载非图文非视频素材
	 * @param mediaId
	 * @return
	 * @throws IOException
	 */
	public static String materialDownload(String path,JSONObject mediaId)
			throws IOException {
		String urlStr = GET_MATERIAL_URL.replaceAll("ACCESS_TOKEN",
				verifyAccessToken());
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置超时间为3秒
		conn.setConnectTimeout(3 * 1000);
		// 默认是GET方式，指明是POST方式请求
		conn.setRequestMethod("POST");

		// 设置是否向connection输入
		conn.setDoInput(true);

		// 设置是否向connection输出，因为是post请求，参数放在http正文内，因此需要设为true
		conn.setDoOutput(true);

		// post请求不能使用缓存
		conn.setUseCaches(false);

		// 设置本次连接是否自动重定向
		// con.setInstanceFollowRedirects(true);

		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Charset", "UTF-8");

		PrintWriter pw = new PrintWriter(conn.getOutputStream(), true);

		pw.print(mediaId);
		pw.flush();
		pw.close();

		// 使用 connect 方法建立到远程对象的实际连接。
		conn.connect();

		if (conn.getResponseCode() == 200) {
			// 获得响应头字段
			Map<String, List<String>> headers = conn.getHeaderFields();
			String fileName=headers.get("Content-disposition")!=null?Calendar.getInstance().getTimeInMillis()+headers.get("Content-disposition").get(0).split("=")[1].replaceAll("\"", ""):Calendar.getInstance().getTimeInMillis()+headers.get("Content-Disposition").get(0).split("=")[1].replaceAll("\"", "");
			File file = new File(path+"/"+fileName);
			InputStream in = conn.getInputStream();
			OutputStream out = new FileOutputStream(file);
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			return fileName;
		}

		return "null";

	}

	/**
	 * 获得图文永久素材对象
	 * @param media
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @author Lyn-Rebirth 2018.2.7
	 */
	public static JSONObject getNewsMaterial(JSONObject media) throws ClientProtocolException, IOException{
		JSONObject json=null;
		String url=GET_MATERIAL_URL.replaceAll("ACCESS_TOKEN",verifyAccessToken());
		json=doPostStr(url, media.toString());	
		return json;
	}
	
	/**
	 * 上传图片文件永久素材接口
	 * 
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws IOException
	 * @author Lyn-Rebirth 2018.1.31
	 */
	public static String materialUpload(String filePath, String accessToken,
			String type) throws IOException {
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			LogPrintFormat.logPrint("Lyn", "文件上传失败，该路径文件不存在或者文件类型不正确");
			throw new IOException("File is not exists!");
		}
		String url = ADD_MATERIAL_COMMON_URL.replace("ACCESS_TOKEN",
				accessToken).replace("TYPE", type);
		URL urlObj = new URL(url);

		// 打开连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		// 默认是GET方式，指明是POST方式请求
		con.setRequestMethod("POST");

		// 设置是否向connection输入
		con.setDoInput(true);

		// 设置是否向connection输出，因为是post请求，参数放在http正文内，因此需要设为true
		con.setDoOutput(true);

		// post请求不能使用缓存
		con.setUseCaches(false);

		// 设置本次连接是否自动重定向
		// con.setInstanceFollowRedirects(true);

		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		String BOUNDARY = "----------" + System.currentTimeMillis();

		// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
		// 意思是正文是urlencoded编码过的form参数
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
				+ BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"media\";filelength=\""
				+ file.length() + "\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		OutputStream out = new DataOutputStream(con.getOutputStream());

		out.write(head);

		DataInputStream in = new DataInputStream(new FileInputStream(file));

		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");

		out.write(foot);
		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		JSONObject jsonObject = JSONObject.fromObject(result);
		System.out.println(jsonObject);
		String typeName = "media_id";
		String mediaId = jsonObject.getString(typeName);
		return mediaId;

	}

	/**
	 * 视频素材永久素材上传接口
	 * 
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @param title
	 * @param introduction
	 * @return
	 * @throws IOException
	 * @author Lyn-Rebirth 2018.2.4
	 */
	public static String materialUpload(String filePath, String accessToken,
			String type, String title, String introduction) throws IOException {
		String result = null;
		String url=ADD_MATERIAL_COMMON_URL.replaceAll("ACCESS_TOKEN", verifyAccessToken()).replaceAll("TYPE", type);
		HttpURLConnection downloadCon = null;
		InputStream inputStream = null;
		try {
			File file = new File(filePath);
			inputStream = new FileInputStream(file);

			URL urlObj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlObj
					.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Cache-Control", "no-cache");
			String boundary = "-----------------------------"
					+ System.currentTimeMillis();
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + boundary);

			OutputStream output = conn.getOutputStream();
			output.write(("--" + boundary + "\r\n").getBytes());
			String regex = ".*/([^\\.]+)";
			output.write(String
					.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n",
							filePath.replaceAll(regex, "$1")).getBytes());
			output.write("Content-Type: video/mp4 \r\n\r\n".getBytes());
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = inputStream.read(bufferOut)) != -1) {
				output.write(bufferOut, 0, bytes);
			}
			inputStream.close();

			output.write(("--" + boundary + "\r\n").getBytes());
			output.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n"
					.getBytes());
			output.write(String.format(
					"{\"title\":\"%s\", \"introduction\":\"%s\"}", title,
					introduction).getBytes());
			output.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes());
			output.flush();
			output.close();
			inputStream.close();
			InputStream resp = conn.getInputStream();
			StringBuffer sb = new StringBuffer();
			while ((bytes = resp.read(bufferOut)) > -1)
				sb.append(new String(bufferOut, 0, bytes, "utf-8"));
			resp.close();
			result = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return result;

	}
	
	
	public static JSONObject materialUpload(WeChatNewsMaterial wcnm)
			throws ClientProtocolException, IOException {
		JSONObject json = new JSONObject();
		json = JSONObject.fromObject(wcnm);
		String url = ADD_MATERIAL_NEWS_URL.replaceAll("ACCESS_TOKEN",
				verifyAccessToken());
		JSONObject json2 = doPostStr(url, json.toString());

		return json2;
	}

	
	public static JSONObject getUserListOpenId() throws ClientProtocolException, IOException{
		JSONObject json=null;
		String url=GET_USERALL_OPENID.replaceAll("ACCESS_TOKEN", verifyAccessToken());
		json=doGetStr(url);
		return json;
	}
	
	
	/**
	 * 图文永久素材同步
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws SQLException
	 * @author Lyn-Rebirth 2018.2.20
	 */
	
	public static void syncNewsSourceMaterial(WeChatProgressBarDao wcpbd) throws ClientProtocolException, IOException, SQLException{
		ProgressBar pb=new ProgressBar();
		pb.setProgressBarName("WeChatSourceMaterialSync");
		pb.setProgressDescribe("开始进行图文素材同步");
		pb.setProgressLock("1");
		pb.setProgressValue("0");
		wcpbd.updataInfo(pb);
		
		ProgressBar pb2=new ProgressBar();
		pb2.setProgressBarName("WeChatNewsSourceMaterialSync");
		pb2.setProgressDescribe("开始进行图文素材同步");
		pb2.setProgressLock("1");
		pb2.setProgressValue("0");
		wcpbd.updataInfo(pb2);
		
		//通过微信接口获得图文素材总列表 
		int totalNum=sourceMaterialListCount("news");
		int offSet=0;
		int count=20;
		String mediaId=null;
		String updateTime=null;
		for(int offSetTotal=0;offSetTotal<totalNum;offSetTotal=offSetTotal+count){
			JSONArray newsJson=(JSONArray) getMaterialList("news",offSetTotal,count).get("item");
			WeChat_MediaIdSourceMaterialDao wcmsmd=new WeChat_MediaIdSourceMaterialDao();
			
			//用媒体id和更新时间去判断素材是否增加或者素材是否更新
			//判断函数mediaIdIsNeedUpdate，返回值String ,true无需更改，null代表新增，false代表需要更新
			Iterator it=newsJson.iterator();
			while(it.hasNext()){
				JSONObject json=(JSONObject) it.next();
				mediaId=(String) json.get("media_id");
				updateTime=json.getString("update_time");
				String flag=wcmsmd.mediaIdIsNeedUpdate(mediaId, updateTime);
				if(flag.equals("true")){
					
				}else{
					if(flag.equals("false")){
						wcmsmd.deleteArtileInfo(mediaId);
						wcmsmd.deleteNewsInfo(mediaId);
					}
					JSONObject mediaIdJson=new JSONObject();
					mediaIdJson.put("media_id", mediaId);
					JSONObject newJson=getNewsMaterial(mediaIdJson);
					Iterator itItem=((JSONArray)(newJson.get("news_item"))).iterator();
					int orderNum=0;
					WeChatNewsMaterial wcnm=new WeChatNewsMaterial();
					wcnm.setMedia_id(mediaId);
					wcnm.setUpdate_time(newJson.getString("update_time"));
					wcnm.setCreate_time(newJson.getString("create_time"));
					while(itItem.hasNext()){
						orderNum++;
						JSONObject tempJson=(JSONObject) itItem.next();
						WeChatArtiles wca =new WeChatArtiles();
						wca.setAuthor(tempJson.getString("author"));
						wca.setContent(tempJson.getString("content"));
						wca.setContent_source_url(tempJson.getString("content_source_url"));
						wca.setDigest(tempJson.getString("digest"));
						wca.setNeed_open_comment(tempJson.getInt("need_open_comment"));
						wca.setOnly_fans_can_comment(tempJson.getInt("only_fans_can_comment"));
						wca.setOrder(Integer.toString(orderNum));
						wca.setShow_cover_pic(tempJson.getInt("show_cover_pic"));
						wca.setThumb_media_id(tempJson.getString("thumb_media_id"));
						wca.setThumb_url(tempJson.getString("thumb_url"));
						wca.setTitle(tempJson.getString("title"));
						wca.setUrl(tempJson.getString("url"));
						if(flag.equals("null")){
							wcmsmd.insertIntoArtileTable(wca, mediaId);
							LogPrintFormat.logPrint("Lyn", "开始新增news子类型Artile素材，MediaId为"+mediaId);
						}if(flag.equals("false")){
							wcmsmd.insertIntoArtileTable(wca, mediaId);
							LogPrintFormat.logPrint("Lyn", "开始修改news子类型Artile素材，MediaId为"+mediaId);
						}
					}
					
					if(flag.equals("null")){
						wcmsmd.insertIntoMediaIdSourceMaterialTable(wcnm,orderNum);
						LogPrintFormat.logPrint("Lyn", "开始新增news类型素材，MediaId为"+mediaId);
					}else if(flag.equals("false")){
						wcmsmd.insertIntoMediaIdSourceMaterialTable(wcnm,orderNum);
						LogPrintFormat.logPrint("Lyn", "开始修改news类型素材，MediaId为"+mediaId);
					}
				}
			}
		}
		
		pb.setProgressDescribe("图文素材同步完毕");
		pb.setProgressValue("10");
		wcpbd.updataInfo(pb);
		
		

		pb2.setProgressDescribe("图文素材同步完毕");
		pb2.setProgressValue("100");
		wcpbd.updataInfo(pb2);
		LogPrintFormat.logPrint("Lyn", "图文素材同步完毕");
	}
	
	/**
	 * 图片永久素材同步
	 * @param wcpbd
	 * @throws SQLException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void syncImagesSourceMaterial(WeChatProgressBarDao wcpbd,String path) throws SQLException, ClientProtocolException, IOException{
		ProgressBar pb=new ProgressBar();
		pb.setProgressBarName("WeChatSourceMaterialSync");
		pb.setProgressDescribe("开始进行图片素材同步");
		pb.setProgressValue("20");
		pb.setProgressLock("1");
		wcpbd.updataInfo(pb); 
		
		ProgressBar pb2=new ProgressBar();
		pb2.setProgressBarName("WeChatImageSourceMaterialSync");
		pb2.setProgressDescribe("开始进行图片素材同步");
		pb2.setProgressLock("1");
		pb2.setProgressValue("0");
		wcpbd.updataInfo(pb2);
		
		LogPrintFormat.logPrint("Lyn", "开始进行图片素材同步");
		int totalNum=sourceMaterialListCount("image");
		int count=20;
		JSONArray imageJsonList=null;
		WeChat_ImageSourceMaterialDao wcismd=new WeChat_ImageSourceMaterialDao();
		for(int offSet=0;offSet<totalNum;offSet=offSet+count){
			imageJsonList=(JSONArray) getMaterialList("image",offSet,count).get("item");
			System.out.println(imageJsonList);
			Iterator it=imageJsonList.iterator();
			while(it.hasNext()){
				JSONObject imageJson=(JSONObject) it.next();
				if(wcismd.getOneData(imageJson.getString("media_id")).getMedia_id()==null){
					WeChatImageSourceMaterial wcism=new WeChatImageSourceMaterial();
					wcism.setMedia_id(imageJson.getString("media_id"));
					wcism.setName(imageJson.getString("name"));
					wcism.setUpdate_time(imageJson.getString("update_time"));
					wcism.setUrl(imageJson.getString("url"));
					JSONObject mediaIdJson=new JSONObject();
					mediaIdJson.put("media_id", imageJson.getString("media_id"));
					wcism.setSName(materialDownload(path,mediaIdJson));
					wcismd.insertOneData(wcism);
				}
			}
		}
		
		pb.setProgressDescribe("图片素材同步完毕");
		pb.setProgressValue("40");
		wcpbd.updataInfo(pb); 
		
		pb2.setProgressDescribe("图片素材同步完毕");
		pb2.setProgressLock("1");
		pb2.setProgressValue("100");
		wcpbd.updataInfo(pb2);
		
		LogPrintFormat.logPrint("Lyn", "图片素材同步完毕");
		
	}

	/**
	 * 语音永久素材同步
	 * 
	 * @param wcpbd
	 * @author Lyn-Rebirth 2018.2.21
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void syncVoiceSourceMaterial(WeChatProgressBarDao wcpbd,String path) throws SQLException, ClientProtocolException, IOException{
		ProgressBar pb=new ProgressBar();
		pb.setProgressBarName("WeChatSourceMaterialSync");
		pb.setProgressDescribe("开始进行语音素材同步");
		pb.setProgressValue("45");
		pb.setProgressLock("1");
		wcpbd.updataInfo(pb); 
		
		ProgressBar pb2=new ProgressBar();
		pb2.setProgressBarName("WeChatVoiceSourceMaterialSync");
		pb2.setProgressDescribe("开始进行语音素材同步");
		pb2.setProgressLock("1");
		pb2.setProgressValue("0");
		wcpbd.updataInfo(pb2);
		LogPrintFormat.logPrint("Lyn", "开始进行语音素材同步");
		int totalNum=sourceMaterialListCount("voice");
		int count=20;
		JSONArray voiceJsonList=null;
		WeChat_VoiceSourceMaterialDao wcismd=new WeChat_VoiceSourceMaterialDao();
		for(int offSet=0;offSet<totalNum;offSet=offSet+count){
			voiceJsonList=(JSONArray) getMaterialList("voice",offSet,count).get("item");
			Iterator it=voiceJsonList.iterator();
			while(it.hasNext()){
				JSONObject voiceJson=(JSONObject) it.next();
				if(wcismd.getOneData(voiceJson.getString("media_id")).getMedia_id()==null){
					WeChatVoiceSourceMaterial wcism=new WeChatVoiceSourceMaterial();
					wcism.setMedia_id(voiceJson.getString("media_id"));
					wcism.setName(voiceJson.getString("name"));
					wcism.setUpdate_time(voiceJson.getString("update_time"));
					wcism.setUrl(voiceJson.getString("url"));
					JSONObject mediaIdJson=new JSONObject();
					mediaIdJson.put("media_id", voiceJson.getString("media_id"));
					wcism.setName(materialDownload(path,mediaIdJson));
					wcismd.insertOneData(wcism);
				}
			}
		}
		
		pb.setProgressDescribe("语音素材同步完毕");
		pb.setProgressValue("60");
		wcpbd.updataInfo(pb); 
		
		pb2.setProgressDescribe("语音素材同步完毕");
		pb2.setProgressLock("1");
		pb2.setProgressValue("100");
		wcpbd.updataInfo(pb2);
		LogPrintFormat.logPrint("Lyn", "语音素材同步完毕");
	}

	public static void syncVideoSourceMaterial(WeChatProgressBarDao wcpbd,String path) throws SQLException, ClientProtocolException, IOException{
		ProgressBar pb=new ProgressBar();
		pb.setProgressBarName("WeChatSourceMaterialSync");
		pb.setProgressDescribe("开始进行视频素材同步");
		pb.setProgressValue("65");
		pb.setProgressLock("1");
		wcpbd.updataInfo(pb); 
		
		ProgressBar pb2=new ProgressBar();
		pb2.setProgressBarName("WeChatVideoSourceMaterialSync");
		pb2.setProgressDescribe("开始进行视频素材同步");
		pb2.setProgressLock("1");
		pb2.setProgressValue("0");
		wcpbd.updataInfo(pb2);
		
		
		LogPrintFormat.logPrint("Lyn", "开始进行视频素材同步");
		int totalNum=sourceMaterialListCount("video");
		int count=20;
		JSONArray videoJsonList=null;
		WeChat_VideoSourceMaterialDao wcismd=new WeChat_VideoSourceMaterialDao();
		for(int offSet=0;offSet<totalNum;offSet=offSet+count){
			videoJsonList=(JSONArray) getMaterialList("video",offSet,count).get("item");
			Iterator it=videoJsonList.iterator();
			while(it.hasNext()){
				JSONObject videoJson=(JSONObject) it.next();
				if(wcismd.getOneData(videoJson.getString("media_id")).getMedia_id()==null){
					WeChatVideoSourceMaterial wcism=new WeChatVideoSourceMaterial();
					wcism.setMedia_id(videoJson.getString("media_id"));
					wcism.setUpdate_time(videoJson.getString("update_time"));
					JSONObject mediaIdJson=new JSONObject();
					mediaIdJson.put("media_id", videoJson.getString("media_id"));
					
					JSONObject videoJson1=materialDownload(mediaIdJson, "video");
					wcism.setUrl(videoJson1.getString("down_url"));
					wcism.setDescription(videoJson1.getString("description"));
					wcism.setTitle(videoJson1.getString("title"));
					wcism.setName(commonDownload(wcism.getUrl(),path));
					wcismd.insertOneData(wcism);
				}
			}
		}
		pb.setProgressDescribe("视频素材同步完毕");
		pb.setProgressValue("100");
		wcpbd.updataInfo(pb); 
		
		pb2.setProgressDescribe("视频素材同步完毕");
		pb2.setProgressLock("1");
		pb2.setProgressValue("100");
		wcpbd.updataInfo(pb2);
		LogPrintFormat.logPrint("Lyn", "视频素材同步完毕");
	}
	
	public static void sourceMaterialSyncEnd(String tableName) throws SQLException{
		WeChatProgressBarDao wcpbd=new WeChatProgressBarDao();
		ProgressBar pb=new ProgressBar();
		pb.setProgressBarName(tableName);
		pb.setProgressDescribe("同步完毕");
		pb.setProgressValue("0");
		pb.setProgressLock("0");
		wcpbd.updataInfo(pb); 
		pb=wcpbd.useNameGetData(tableName);
		
		LogPrintFormat.logPrint("Lyn", "微信素材进度条重置完毕");
	}
	
	
	public static int sourceMaterialListCount(String type) throws ClientProtocolException, IOException{
		String url=GET_MATERIAL_COUNTER_URL.replaceAll("ACCESS_TOKEN", verifyAccessToken());
		JSONObject json=doGetStr(url);
		LogPrintFormat.logPrint("Lyn", json.toString());
		int num=(Integer) json.get(type+"_count");
		System.out.println(num);
		return num;
	} 
	
	
	public static void sourceMaterialSync(String path) throws ClientProtocolException, IOException, SQLException{
		WeChatProgressBarDao wcpbd =new WeChatProgressBarDao();
		syncNewsSourceMaterial( wcpbd);
		syncImagesSourceMaterial(wcpbd,path);
		syncVoiceSourceMaterial(wcpbd,path);
		syncVideoSourceMaterial(wcpbd,path);
	}
	
	/**
	 * 视频下载
	 * @param fileName
	 * @param urlStr
	 * @throws IOException
	 * @author Lyn-Rebirth 2018.2.23
	 */
	public static String commonDownload(String urlStr,String path) throws IOException{
		URL url=new URL(urlStr);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		
//		InputStream in = con.getInputStream();
//		OutputStream out= new FileOutputStream(file);
//		byte[] bt=new byte[1024];
//		int len=0;
//		while((len=in.read(bt))!=-1){
//			out.write(bt, 0, len);
//		}
//		out.close();
//		in.close();
		
		con.connect();

		if (con.getResponseCode() == 200) {
			// 获得响应头字段
			Map<String, List<String>> headers = con.getHeaderFields();
			System.out.println(headers);
			String fileName=Calendar.getInstance().getTimeInMillis()+headers.get("Content-Disposition").get(0).split("=")[1].replaceAll("\"", "");
			File file = new File(path+"/"+fileName);
			InputStream in = con.getInputStream();
			OutputStream out = new FileOutputStream(file);
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			return fileName;
		}
		return "null";
	}
}
