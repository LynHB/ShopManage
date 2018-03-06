package Lyn.ShopManage.entity;

public class WeChatArtiles {
	private String title;
	private String thumb_media_id;
	private String author;
	private String digest;
	private int show_cover_pic;
	private String content;
	private String content_source_url;
	private int need_open_comment;
	private int only_fans_can_comment;
	private String url;
	private String thumb_url;
	private String order;
	
	
	public int getNeed_open_comment() {
		return need_open_comment;
	}
	public int setNeed_open_comment(int need_open_comment) {
		return this.need_open_comment = need_open_comment;
	}
	public int getOnly_fans_can_comment() {
		return only_fans_can_comment;
	}
	public int setOnly_fans_can_comment(int only_fans_can_comment) {
		return this.only_fans_can_comment = only_fans_can_comment;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getThumb_url() {
		return thumb_url;
	}
	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumb_media_id() {
		return thumb_media_id;
	}
	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public int getShow_cover_pic() {
		return show_cover_pic;
	}
	public void setShow_cover_pic(int show_cover_pic) {
		this.show_cover_pic = show_cover_pic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent_source_url() {
		return content_source_url;
	}
	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}
	
	
}
