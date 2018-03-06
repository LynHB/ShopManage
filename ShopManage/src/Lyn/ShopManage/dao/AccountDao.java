package Lyn.ShopManage.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import Lyn.ShopManage.entity.Account;
import Lyn.ShopManage.util.MysqlUtil;


public class AccountDao {
	public Account confirmId(String accountId) throws SQLException{
		Account at = null;
		Connection conn=MysqlUtil.getConnection();
		String sql =""+
				"select userPassWord  from account "+
				"where accountId=? limit 1";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1,accountId);
		ResultSet rs = ptmt.executeQuery();
		
		while(rs.next()){
			at = new Account();
			at.setAccountId(accountId);
			at.setUserPassWord(rs.getString("userPassWord"));
		}
		return at;
	}
	
	public Account get(String id) throws Exception{
		Account at = null;
		Connection conn=MysqlUtil.getConnection();
		String sql=""+
				"select userName,SId,birthday,sex,TypeId,Content,PhoneId,E_mail,Authority from account "+
				"where accountId = ? limit 1;";
		PreparedStatement ptmp=conn.prepareStatement(sql);
		ptmp.setString(1,id);
		ResultSet rs = ptmp.executeQuery();
		
		while(rs.next()){
			at = new Account();
			at.setAccountId(id);
			at.setUserName(rs.getString("userName"));
			at.setSId(rs.getInt("SId"));
			at.setBirthday(rs.getString("birthday"));
			at.setSex(rs.getString("sex"));
			at.setContent(rs.getString("Content"));
			at.setPhoneId(rs.getString("PhoneId"));
			at.setE_mail(rs.getString("E_mail"));
			at.setAuthority(rs.getString("Authority"));
		}
		
		return at;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Connection conn=MysqlUtil.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select a from test;");
			while(rs.next()){
				System.out.println(rs.getString("a"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
