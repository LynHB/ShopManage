package Lyn.ShopManage.entity;

import java.math.BigInteger;


public class CollectShop {
	private String cid;
	private BigInteger sid;
	private String Name;
	private int stockBalance;
	private int stockSell;
	private String averageCost;
	private String marketingPrice;
	private String classification;
	private String specifications;
	private String brand;
	private String detail;
	
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public BigInteger getSid() {
		return sid;
	}
	public void setSid(BigInteger sid) {
		this.sid = sid;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getStockBalance() {
		return stockBalance;
	}
	public void setStockBalance(int stockBalance) {
		this.stockBalance = stockBalance;
	}
	public int getStockSell() {
		return stockSell;
	}
	public void setStockSell(int stockSell) {
		this.stockSell = stockSell;
	}
	public String getAverageCost() {
		return averageCost;
	}
	public void setAverageCost(String averageCost) {
		this.averageCost = averageCost;
	}
	public String getMarketingPrice() {
		return marketingPrice;
	}
	public void setMarketingPrice(String marketingPrice) {
		this.marketingPrice = marketingPrice;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
