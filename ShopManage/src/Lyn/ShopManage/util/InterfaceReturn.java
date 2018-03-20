package Lyn.ShopManage.util;

import java.util.Calendar;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.sf.json.JSON;

public class InterfaceReturn {
	@JsonProperty(value = "ErrorCode")
	private int ErrorCode;
	
	@JsonProperty(value = "ErrorMsg")
	private String ErrorMsg;
	
	@JsonProperty(value = "CreateTime")
	private String CreateTime;
	
	@JsonProperty(value = "UpdateTime")
	private String UpdateTime;
	
	@JsonIgnore
	public InterfaceReturn(){
		CreateTime=String.valueOf(Calendar.getInstance().getTimeInMillis());
		UpdateTime=String.valueOf(Calendar.getInstance().getTimeInMillis());
	}
	
	@JsonIgnore
	public int getErrorCode() {
		return ErrorCode;
	}
	
	@JsonIgnore
	public void setErrorCode(int errorCode) {
		ErrorCode = errorCode;
	}
	
	@JsonIgnore
	public String getErrorMsg() {
		return ErrorMsg;
	}
	
	@JsonIgnore
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}
	
	@JsonIgnore
	public String getCreateTime() {
		return CreateTime;
	}
	
	@JsonIgnore
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	
	@JsonIgnore
	public String getUpdateTime() {
		return UpdateTime;
	}
	
	@JsonIgnore
	public void setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
	}
}
