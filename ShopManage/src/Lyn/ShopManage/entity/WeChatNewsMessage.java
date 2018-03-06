package Lyn.ShopManage.entity;

import java.util.List;


public class WeChatNewsMessage extends WeChatBaseMessage {
	private int ArticleCount;
	private List<WeChatNews> Articles;
	public int getArticleCount(){
		return ArticleCount;
	}
	public List<WeChatNews > getArticles() {
		return Articles;
	}
	public void setArticles(List<WeChatNews> articles) {
		Articles = articles;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
}
