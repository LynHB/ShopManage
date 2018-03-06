package Lyn.ShopManage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Lyn.ShopManage.util.MysqlUtil;


public class WeChat_MenuDao {

	public void update(String data) throws SQLException{
		Connection conn=MysqlUtil.getConnection();
		String sql="UPDATE WeChatMenu "+
				"SET Content=?;";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		try {
			ptmt.setString(1,data);
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ptmt=null;
		}
	}
	
	public String select(){
		Connection conn=MysqlUtil.getConnection();
		String sql="SELECT * FROM WeChatMenu ";
		ResultSet rs=null;
		Statement stmt=null;
		String result="";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				result=rs.getString("Content");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			stmt=null;
			rs=null;
		}
		return result;
	}
}
