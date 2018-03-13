package Lyn.ShopManage.entity;

import java.util.ArrayList;

public class CollectMemberManage {
	private String userId;
	private String userName;
	private String userChildName;
	private String userChildId;
	private String address;
	private int integral;
	private int integralTotal;
	private String createTime;
	private String updateTime;
	private String detail;
	private ArrayList<CollectMemberChild> childen;
	
	
	public int getIntegralTotal() {
		return integralTotal;
	}
	public void setIntegralTotal(int integralTotal) {
		this.integralTotal = integralTotal;
	}
	public ArrayList<CollectMemberChild> getChilden() {
		return childen;
	}
	public void setChilden(ArrayList<CollectMemberChild> childen) {
		this.childen = childen;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserChildName() {
		return userChildName;
	}
	public void setUserChildName(String userChildName) {
		this.userChildName = userChildName;
	}
	public String getUserChildId() {
		return userChildId;
	}
	public void setUserChildId(String userChildId) {
		this.userChildId = userChildId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
