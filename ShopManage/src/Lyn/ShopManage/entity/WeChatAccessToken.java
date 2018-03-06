package Lyn.ShopManage.entity;

public class WeChatAccessToken {
	private String token;
	private int expiresIn;
	private int errcode;
	private String errmsg;
	private String tokenUpdateTime;
	
	public String getTokenUpdateTime() {
		return tokenUpdateTime;
	}
	public void setTokenUpdateTime(String tokenUpdateTime) {
		this.tokenUpdateTime = tokenUpdateTime;
	}
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
