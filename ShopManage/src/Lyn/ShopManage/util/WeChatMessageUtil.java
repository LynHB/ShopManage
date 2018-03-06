package Lyn.ShopManage.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Lyn.ShopManage.dao.WeChat_EventDao;
import Lyn.ShopManage.dao.WeChat_KWReplyTextRuleDao;
import Lyn.ShopManage.dao.WeChat_MediaIdSourceMaterialDao;
import Lyn.ShopManage.dao.WeChat_VideoSourceMaterialDao;
import Lyn.ShopManage.entity.WeChatArtiles;
import Lyn.ShopManage.entity.WeChatEvent;
import Lyn.ShopManage.entity.WeChatImage;
import Lyn.ShopManage.entity.WeChatImageMessage;
import Lyn.ShopManage.entity.WeChatNews;
import Lyn.ShopManage.entity.WeChatNewsMaterial;
import Lyn.ShopManage.entity.WeChatNewsMessage;
import Lyn.ShopManage.entity.WeChatTextMessage;
import Lyn.ShopManage.entity.WeChatVideo;
import Lyn.ShopManage.entity.WeChatVideoMessage;
import Lyn.ShopManage.entity.WeChatVideoSourceMaterial;

import com.thoughtworks.xstream.XStream;

public class WeChatMessageUtil {
	public static final String MESSAGE_TEXT="text";
	public static final String MESSAGE_NEWS="news";
	public static final String MESSAGE_IMAGE="image";
	public static final String MESSAGE_VOICE="voice";
	public static final String MESSAGE_VIDEO="video";
	public static final String MESSAGE_LINK="link";
	public static final String MESSAGE_LOCATION="location";
	public static final String MESSAGE_EVENT="event";
	public static final String MESSAGE_SUBSCRIBE="subscribe";
	public static final String MESSAGE_UNSUBSCRIBE="unsubscribe";
	public static final String MESSAGE_CLICK="click";
	public static final String MESSAGE_VIEW="view";
	
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		Map<String,String>map = new HashMap<String,String>();
		SAXReader reader = new SAXReader();
		
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		
		Element root = doc.getRootElement();
		
		List<Element> list = root.elements();
		
		for(Element e:list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	
	
	public static String textMessageToXml(WeChatTextMessage textMessage){
		XStream xStream = new XStream();
		xStream.alias("xml",textMessage.getClass());
		return xStream.toXML(textMessage);
	}
	
	public static String imageMessageToXml(WeChatImageMessage textMessage){
		XStream xStream = new XStream();
		xStream.alias("xml",textMessage.getClass());
		return xStream.toXML(textMessage);
	}
	
	public static String videoMessageToXml(WeChatVideoMessage textMessage){
		XStream xStream = new XStream();
		xStream.alias("xml",textMessage.getClass());
		return xStream.toXML(textMessage);
	}
	
	
	public static String newsMessageToXml(WeChatNewsMessage textMessage){
		XStream xStream = new XStream();
		xStream.alias("xml",textMessage.getClass());
		xStream.alias("item",new WeChatNews().getClass());
		return xStream.toXML(textMessage);
	}
	
	public static String subscribe(String msgId,String toUserName,String fromUserName,String subflag){
		WeChatTextMessage text = new WeChatTextMessage();
		text.setMsgId(msgId);
		
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		//text.setMsgType("text");
		WeChat_EventDao ed=new WeChat_EventDao();
		WeChatEvent wce=null;
		if(subflag.equals("subscribe")){
			wce=ed.getSubscribe();
			if(wce.getReplyType().equals("text")){
				text.setMsgType(wce.getReplyType()); 
				text.setCreateTime(new Date().getTime());
				text.setContent(wce.getEvetReply());
			}else if(wce.getReplyType().equals("news")){
				return initNews(msgId, toUserName, fromUserName, wce.getEvetReply());
			}else if(wce.getReplyType().equals("image")){
				return initImage(msgId, toUserName, fromUserName, wce.getEvetReply());
			}else if(wce.getReplyType().equals("video")){
				return initVideo(msgId, toUserName, fromUserName, wce.getEvetReply());
			}else if(wce.getReplyType().equals("voice")){
				//return initVoice(msgId, toUserName, fromUserName, wce.getEvetReply());
			}
		}else if(subflag.equals("unsubscribe")){
			wce=ed.getUnSubscribe();
			if(wce.getReplyType().equals("text")){
				text.setMsgType(wce.getReplyType()); 
				text.setCreateTime(new Date().getTime());
				text.setContent(wce.getEvetReply());
			}
		}
		
		
		return WeChatMessageUtil.textMessageToXml(text);
	}
	
	public static String initText(String msgId,String toUserName,String fromUserName,String content ) throws SQLException{
		WeChatTextMessage text = new WeChatTextMessage();
		text.setMsgId(msgId);
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType("text");
		text.setCreateTime(new Date().getTime());
		WeChat_KWReplyTextRuleDao kwDao=new WeChat_KWReplyTextRuleDao();
		HashMap<String,String> hm=kwDao.getReplyContent(content);
		if(hm!=null){
			String contentReply=hm.get("replyContent");
			LogPrintFormat.logPrint("Lyn", contentReply);
			if(contentReply.split(" ")[0].toLowerCase().equals("mediaid")&&contentReply.split(" ")[1].toLowerCase().equals("image")){
				 return initImage(msgId,toUserName,fromUserName,contentReply.split(" ")[2]);
			}else if(contentReply.split(" ")[0].toLowerCase().equals("mediaid")&&contentReply.split(" ")[1].toLowerCase().equals("video")){
				return initVideo(msgId,toUserName,fromUserName,contentReply.split(" ")[2]);
			}else if(contentReply.split(" ")[0].toLowerCase().equals("mediaid")&&contentReply.split(" ")[1].toLowerCase().equals("news")){
				return initNews(msgId,toUserName,fromUserName,contentReply.split(" ")[2]);
			}else{
				text.setContent(hm.get("replyContent"));
			}
		}else{
			text.setContent("嗯？");
		}
		return WeChatMessageUtil.textMessageToXml(text);
	}
	
	public static String initImage(String msgId,String toUserName,String fromUserName,String mediaId ){
		WeChatImageMessage imageMessage=new WeChatImageMessage();
		WeChatImage Image=new WeChatImage();
		Image.setMediaId(mediaId);
		imageMessage.setImage(Image);
		imageMessage.setFromUserName(toUserName);
		imageMessage.setToUserName(fromUserName);
		imageMessage.setMsgType("image");
		imageMessage.setCreateTime(new Date().getTime());
		return WeChatMessageUtil.imageMessageToXml(imageMessage);
	}
	
	public static String initVideo(String msgId,String toUserName,String fromUserName,String mediaId){
		WeChat_VideoSourceMaterialDao wcvsmd=new WeChat_VideoSourceMaterialDao();
		WeChatVideoSourceMaterial wcvsm=wcvsmd.getOneData(mediaId);
		WeChatVideo video=new WeChatVideo();
		video.setMediaId(mediaId);
		video.setTitle(wcvsm.getTitle());
		video.setDescription(wcvsm.getDescription());
		WeChatVideoMessage videoMessage=new WeChatVideoMessage();
		videoMessage.setVideo(video);
		videoMessage.setFromUserName(toUserName);
		videoMessage.setToUserName(fromUserName);
		videoMessage.setMsgType("video");
		videoMessage.setCreateTime(new Date().getTime());
		return WeChatMessageUtil.videoMessageToXml(videoMessage);
		
	}
	
	public static String initNews(String msgId,String toUserName,String fromUserName,String mediaId){
		WeChat_MediaIdSourceMaterialDao wcmsmd=new WeChat_MediaIdSourceMaterialDao();
		WeChatNewsMaterial wcnm= wcmsmd.useNewsMediaIdGetNews(mediaId);
		WeChatNewsMessage newsMessage=new WeChatNewsMessage();
		List<WeChatNews> news=new ArrayList<WeChatNews>();
		newsMessage.setFromUserName(toUserName);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType("news");
		newsMessage.setArticleCount(wcnm.getNews_item().size());
		Iterator it =wcnm.getNews_item().iterator();
		while(it.hasNext()){
			WeChatArtiles wca=(WeChatArtiles) it.next();
			WeChatNews wcn=new WeChatNews();
			wcn.setUrl(wca.getUrl());
			wcn.setTitle(wca.getTitle());
			wcn.setPicUrl(wca.getThumb_url());
			wcn.setDescription(wca.getDigest());
			news.add(wcn);
		}
		newsMessage.setArticles(news);
		return WeChatMessageUtil.newsMessageToXml(newsMessage);
		
	}

	
	public static String clickEvent(String msgId, String toUserName,
			String fromUserName, String eventKey) {
		WeChat_EventDao evenDao=new WeChat_EventDao();
		WeChatEvent event=evenDao.selectOneData("click", eventKey);
		if(event.getReplyType().equals("text")){
			WeChatTextMessage text = new WeChatTextMessage();
			text.setMsgId(msgId);
			text.setFromUserName(toUserName);
			text.setToUserName(fromUserName);
			text.setMsgType("text");
			text.setCreateTime(new Date().getTime());
			text.setContent(event.getEvetReply());
			return WeChatMessageUtil.textMessageToXml(text);
		}else if(event.getReplyType().equals("news")){
			return initNews(msgId, toUserName, fromUserName, event.getEvetReply());
		}else if(event.getReplyType().equals("image")){
			return initImage(msgId, toUserName, fromUserName, event.getEvetReply());
		}else if(event.getReplyType().equals("video")){
			return initVideo(msgId, toUserName, fromUserName, event.getEvetReply());
		}else if(event.getReplyType().equals("voice")){
			//return initVoice(msgId, toUserName, fromUserName, event.getEvetReply());
		}
		
		return "";
	}
}
