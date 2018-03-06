package Lyn.ShopManage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import Lyn.ShopManage.entity.WeChatAccessToken;
import Lyn.ShopManage.util.MysqlUtil;

public class WeChat_AccessTokenDao {

	
//		public static void main(String[] args) {
//		WeChat_AccessTokenDao atd=new WeChat_AccessTokenDao();
//		WeChatAccessToken at=atd.getAccessToken();
//		System.out.println(at.getErrcode());
//		at.setToken("dsadsa");
//		atd.setAccessToken(at);
//		
//	}
	
	
	public WeChatAccessToken getAccessToken(){
		Connection conn=MysqlUtil.getConnection();
		String sql = "select * from WeChat_AccessToken";
		ResultSet rs=null;
		WeChatAccessToken at=new WeChatAccessToken();
		Statement stmt=null;
		try {
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				at.setToken(rs.getString("AccessToken"));
				at.setExpiresIn(rs.getInt("ExpiresIn"));
				at.setErrcode(rs.getInt("ErrCode"));
				at.setErrmsg(rs.getString("ErrMsg"));
				at.setTokenUpdateTime(rs.getString("TokenUpdateTime"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			rs=null;
			stmt=null;
			conn=null;
			
		}
		return at;
		
	}
	
	public void setAccessToken(WeChatAccessToken at){
		Connection conn = MysqlUtil.getConnection();
		Statement st=null;
		String sql="UPDATE WeChat_AccessToken "
				+"SET AccessToken=\""+at.getToken()+"\","
				+"ExpiresIn="+at.getExpiresIn()+","
				+"ErrCode="+at.getErrcode()+","
				+"ErrMsg=\""+at.getErrmsg()+"\","
				+"TokenUpdateTime=\""+at.getTokenUpdateTime()+"\" ";
		try {
			st = conn.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			st=null;
			conn=null;
		}
		
	}
}
