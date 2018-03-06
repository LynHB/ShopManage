package Lyn.ShopManage.entity;

import java.sql.SQLException;
import java.util.Iterator;

import Lyn.ShopManage.dao.WeChat_KWReplyTextRuleDao;

public class KWReplyTextRule {
	private String ruleName;
	private String matchWord;
	private String replyContent;
	private String matchRule;
	

	public static void main(String[] args) {
		WeChat_KWReplyTextRuleDao kw=new WeChat_KWReplyTextRuleDao();
		
		kw=null;
		System.gc();
	}
	public KWReplyTextRule(String ruleName,String matchWord,String replyContent,String matchRule){
		this.ruleName=ruleName;
		this.matchWord=matchWord;
		this.replyContent=replyContent;
		this.matchRule=matchRule;
	}
	
	public String saveRule() {
		WeChat_KWReplyTextRuleDao kwrtrd= new WeChat_KWReplyTextRuleDao();
		try {
			if(kwrtrd.selectNameNotLive(this.getRuleName())){
				kwrtrd.insertOneData(this);
			}else{
				return "规则名重复";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "null";
	}
	
	
	
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getMatchWord() {
		return matchWord;
	}
	public void setMatchWord(String matchWord) {
		this.matchWord = matchWord;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getMatchRule() {
		return matchRule;
	}
	public void setMatchRule(String matchRule) {
		this.matchRule = matchRule;
	}
}
