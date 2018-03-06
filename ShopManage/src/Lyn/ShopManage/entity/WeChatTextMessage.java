package Lyn.ShopManage.entity;

public class WeChatTextMessage extends WeChatBaseMessage{
	private String Content;
	private String MsgId;
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId2) {
		MsgId = msgId2;
	}
	
}
