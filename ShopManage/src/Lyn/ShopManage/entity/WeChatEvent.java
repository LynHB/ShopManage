package Lyn.ShopManage.entity;

public class WeChatEvent {
	private String eventType;
	private String EvetReply;
	private String ReplyType;
	private String EventCode;
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEvetReply() {
		return EvetReply;
	}
	public void setEvetReply(String evetReply) {
		EvetReply = evetReply;
	}
	public String getReplyType() {
		return ReplyType;
	}
	public void setReplyType(String replyType) {
		ReplyType = replyType;
	}
	public String getEventCode() {
		return EventCode;
	}
	public void setEventCode(String eventCode) {
		EventCode = eventCode;
	}
	
}
