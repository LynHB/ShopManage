package Lyn.ShopManage.entity;

public class WeChatButton {
	private String type;
	private String name;
	private WeChatButton[] sub_button;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WeChatButton[] getSub_button() {
		return sub_button;
	}
	public void setSub_button(WeChatButton[] sub_button) {
		this.sub_button = sub_button;
	}
}
