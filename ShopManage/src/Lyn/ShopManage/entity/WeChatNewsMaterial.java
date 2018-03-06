package Lyn.ShopManage.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WeChatNewsMaterial {
	private ArrayList<WeChatArtiles> articles=null;
	private ArrayList<WeChatArtiles> news_item=new ArrayList<WeChatArtiles>();
	private String update_time;
	private String create_time;
	private String media_id;
	
	public WeChatNewsMaterial(){
		
	}
	
	public WeChatNewsMaterial(String mediaId,JSONObject json){
		this.update_time=json.getString("update_time");
		this.create_time=json.getString("create_time");
		this.media_id=mediaId;
		Iterator it=((JSONArray)(json.get("news_item"))).iterator();
		ArrayList<WeChatArtiles> tmp_news=new ArrayList<WeChatArtiles>();
		while(it.hasNext()){
			JSONObject chiJson =(JSONObject)it.next();
			System.out.println(chiJson);
			WeChatArtiles wca=new WeChatArtiles();
			wca.setTitle(chiJson.getString("title"));
			wca.setAuthor(chiJson.getString("author"));
			wca.setDigest(chiJson.getString("digest"));
			wca.setContent(chiJson.getString("content"));
			wca.setContent_source_url(chiJson.getString("content_source_url"));
			wca.setThumb_media_id(chiJson.getString("thumb_media_id"));
			wca.setShow_cover_pic(chiJson.getInt("show_cover_pic"));
			wca.setUrl(chiJson.getString("url"));
			wca.setThumb_url(chiJson.getString("thumb_url"));
			wca.setNeed_open_comment(chiJson.getInt("need_open_comment"));
			wca.setOnly_fans_can_comment(chiJson.getInt("only_fans_can_comment"));
			tmp_news.add(wca);
			}
		this.setArticles(tmp_news);
	}
	


	public String getMedia_id() {
		return media_id;
	}


	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}


	public ArrayList<WeChatArtiles> getArticles() {
		return articles;
	}


	public void setArticles(ArrayList<WeChatArtiles> articles) {
		this.articles = articles;
	}


	public ArrayList<WeChatArtiles> getNews_item() {
		return news_item;
	}


	public void setNews_item(ArrayList<WeChatArtiles> news_item) {
		this.news_item = news_item;
	}


	public String getUpdate_time() {
		return update_time;
	}


	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}


	public String getCreate_time() {
		return create_time;
	}


	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}




//	public static void main(String[] args) {
//		WeChatNewsMaterial wcnm=new WeChatNewsMaterial();
//		WeChatArtiles wca =new WeChatArtiles();
//		wca.setTitle("title");
//		wca.setThumb_media_id("thumb");
//		WeChatArtiles[] wcas={wca};
//		wcnm.setArticles(wcas);
//		JSONObject json=JSONObject.fromObject(wcnm);
//		System.out.println(json);
//	}
}
