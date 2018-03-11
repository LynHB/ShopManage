package Lyn.ShopManage.entity;

import java.util.ArrayList;

public class CollectMemberChild {
	private String childerId;
	private String birthday;
	private int sex;
	private String createTime;
	private String updateTime;
	private String userId;
	private ArrayList<CollectDiagnosis> cd;
	
	
	public ArrayList<CollectDiagnosis> getCd() {
		return cd;
	}
	public void setCd(ArrayList<CollectDiagnosis> cd) {
		this.cd = cd;
	}
	public String getChilderId() {
		return childerId;
	}
	public void setChilderId(String childerId) {
		this.childerId = childerId;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
