package Lyn.ShopManage.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import sun.misc.GC;

import Lyn.ShopManage.entity.KWReplyTextRule;
import Lyn.ShopManage.util.LogPrintFormat;
import Lyn.ShopManage.util.MysqlUtil;


public class WeChat_KWReplyTextRuleDao extends Object{
	public WeChat_KWReplyTextRuleDao(){
	}
	
	protected void finalize() throws java.lang.Throwable
	{
		// LogPrintFormat.logPrint("Lyn", "the WeChat_KWReplyTextRuleDao object running finalize");
		super.finalize();
	// finalization code here
	}
//	public static void main(String[] args) {
//		WeChat_KWReplyTextRuleDao kw =new WeChat_KWReplyTextRuleDao();
//		System.out.println(kw.selectAllData());
//		kw=null;
//		System.gc();
//	}

	//查询所有数据展示
	public ArrayList<KWReplyTextRule> selectAllData(){
		Connection conn = MysqlUtil.getConnection();
		String sql=""
				+"select ruleName,matchWord,replyContent,matchRule "
				+"from WeChat_KWReplyTextRule;";
		ResultSet rs= null;
		PreparedStatement ptmt = null;
		ArrayList<KWReplyTextRule> kwList= new ArrayList<KWReplyTextRule>();
		try {
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			while(rs.next()){
				KWReplyTextRule kw=new KWReplyTextRule(rs.getString("ruleName"), rs.getString("matchWord"), rs.getString("replyContent"), rs.getString("matchRule"));
				kwList.add(kw);
			}
			return kwList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (ptmt != null) {
				try {
					ptmt.close();
					ptmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return kwList;
	}
	
	//插入单条记录
	public void insertOneData(KWReplyTextRule kwrtr) throws SQLException{
		Connection conn = MysqlUtil.getConnection();
		String sql=""
				+"insert into WeChat_KWReplyTextRule"
				+"(ruleName,matchWord,replyContent,matchRule) "
				+"values(?,?,?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, kwrtr.getRuleName());
		ptmt.setString(2,kwrtr.getMatchWord());
		ptmt.setString(3,kwrtr.getReplyContent());
		ptmt.setString(4,kwrtr.getMatchRule());
		ptmt.execute();
	}
	
	//判断ruleName在数据库中是否已经存在
	public boolean selectNameNotLive(String ruleName) throws SQLException{
		Connection conn = MysqlUtil.getConnection();
		String sql=""
				+"select ruleName from WeChat_KWReplyTextRule "
				+"where ruleName=?;";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, ruleName);
		ResultSet rs = ptmt.executeQuery();
		if(rs.next()){
			return false;
		}else{
			return true;
		}
	}
	
	//获取全部的ruleName
	
	//通过matchword去获取对应的replyContent值
	public HashMap getReplyContent(String matchWord) throws SQLException{
		Connection conn = MysqlUtil.getConnection();
		HashMap<String,String> hm=new HashMap<String,String>();
		String sql=""
				+"select replyContent,matchRule from WeChat_KWReplyTextRule "
				+"where matchWord=?;";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1,matchWord);
		ResultSet rs = ptmt.executeQuery();
		if(rs.next()){
			hm.put("replyContent", rs.getString("replyContent"));
			conn=null;
			return hm;
		}else{
			sql=""
				+"select replyContent,matchRule from WeChat_KWReplyTextRule "
				+"where matchWord like \"%"+matchWord+"%\" and matchRule=0;";
			Statement stmt = conn.createStatement();
			rs=null;
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				hm=new HashMap();
				hm.put("replyContent", rs.getString("replyContent"));
				conn=null;
				return hm;
			}else{
				conn=null;
				return null;
			}
		}
	}
}
