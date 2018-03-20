package Lyn.ShopManage.entity;

import java.util.Calendar;

public class CollectFlowWater {
	private String FlowWaterId;
	private String CreateTime;
	private String ShopId;
	private String ShopName;
	private String Specifications;
	private String Brand;
	private String Classification;
	private String UserId;
	private String UserName;
	private String Price;
	private String Cost;
	private String ShopTransaction;
	private String ShopNumber;
	private String Integral;
	private String Turnover;
	private String DiscountType;
	private String Profit;
	private String FlowWaterType;
	
	
	public CollectFlowWater(){
		String createTime=String.valueOf(Calendar.getInstance().getTimeInMillis());
		String randomStr=String.valueOf((int)((Math.random()*9+1)*100000));
		setCreateTime(createTime); 
		setFlowWaterId(createTime+randomStr);
		
	}
	public String getFlowWaterId() {
		return FlowWaterId;
	}
	public void setFlowWaterId(String folwWaterId) {
		FlowWaterId = folwWaterId;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getShopId() {
		return ShopId;
	}
	public void setShopId(String shopId) {
		ShopId = shopId;
	}
	public String getShopName() {
		return ShopName;
	}
	public void setShopName(String shopName) {
		ShopName = shopName;
	}
	public String getSpecifications() {
		return Specifications;
	}
	public void setSpecifications(String specifications) {
		Specifications = specifications;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public String getClassification() {
		return Classification;
	}
	public void setClassification(String classification) {
		Classification = classification;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getCost() {
		return Cost;
	}
	public void setCost(String cost) {
		Cost = cost;
	}
	public String getShopTransaction() {
		return ShopTransaction;
	}
	public void setShopTransaction(String shopTransaction) {
		ShopTransaction = shopTransaction;
	}
	public String getShopNumber() {
		return ShopNumber;
	}
	public void setShopNumber(String shopNumber) {
		ShopNumber = shopNumber;
	}
	public String getIntegral() {
		return Integral;
	}
	public void setIntegral(String integral) {
		Integral = integral;
	}
	public String getTurnover() {
		return Turnover;
	}
	public void setTurnover(String turnover) {
		Turnover = turnover;
	}
	public String getDiscountType() {
		return DiscountType;
	}
	public void setDiscountType(String discountType) {
		DiscountType = discountType;
	}
	public String getProfit() {
		return Profit;
	}
	public void setProfit(String profit) {
		Profit = profit;
	}
	public String getFlowWaterType() {
		return FlowWaterType;
	}
	public void setFlowWaterType(String flowWaterType) {
		FlowWaterType = flowWaterType;
	}
	
	
}
