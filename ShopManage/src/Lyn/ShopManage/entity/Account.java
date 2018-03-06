package Lyn.ShopManage.entity;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

import Lyn.ShopManage.dao.AccountDao;

public class Account {
	private int SId;
	private String accountId;
	private String userName;
	private String userPassWord;
	private String create_time;
	private String update_time;
	private String user2PassWord;
	private String birthday;
	private String sex;
	private String TypeId;
	private String Content;
	private String PhoneId;
	private String E_mail;
	private String authority;
	
	public Account(String accountId,String userPassWord){
		this.accountId=accountId;
		this.userPassWord=userPassWord;
	}
	public Account(){
		
	}
	public String AccountConfirm() throws Exception{
		AccountDao ad=new AccountDao();
		MessageDigest md5=MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		Account a=ad.confirmId(this.accountId);
		if(a==null){
			return "请检查美贝ID是否正确";
		}
		String userPassWordR=base64en.encode(md5.digest(a.getUserPassWord().getBytes("utf-8")));
		if(userPassWordR.equals(base64en.encode(md5.digest(this.userPassWord.getBytes("utf-8"))))){
			return "0";
		}
		return "请检查美贝密码是否正确";
	}
	
	public String getInfo() throws Exception{
		AccountDao ad = new AccountDao();
		Account a=ad.get(this.getAccountId());
		this.birthday=a.getBirthday();
		this.userName=a.getUserName();
		this.E_mail=a.getE_mail();
		this.PhoneId=a.getPhoneId();
		this.sex=a.getSex();
		this.authority=a.getAuthority();
		if(a==null){
			return "数据异常，请重新登陆。";
		}
		return "0";
	}
	
	public int getSId() {
		return SId;
	}

	public void setSId(int sId) {
		SId = sId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassWord() {
		return userPassWord;
	}

	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getUser2PassWord() {
		return user2PassWord;
	}

	public void setUser2PassWord(String user2PassWord) {
		this.user2PassWord = user2PassWord;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTypeId() {
		return TypeId;
	}
	public void setTypeId(String typeId) {
		TypeId = typeId;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getPhoneId() {
		return PhoneId;
	}
	public void setPhoneId(String phoneId) {
		PhoneId = phoneId;
	}
	public String getE_mail() {
		return E_mail;
	}
	public void setE_mail(String e_mail) {
		E_mail = e_mail;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
