package Lyn.ShopManage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Lyn.ShopManage.entity.ProgressBar;
import Lyn.ShopManage.util.MysqlUtil;

public class WeChatProgressBarDao {
	public static void main(String[] args) {
		WeChatProgressBarDao wcpbd=new WeChatProgressBarDao();
		ProgressBar pb=wcpbd.useNameGetData("WeChatSourceMaterialSync");
		System.out.println(pb.getProgressBarName());
	}
	public ProgressBar useNameGetData(String progressBarName){
		Connection conn=MysqlUtil.getConnection();
		String sql=" "
				+"SELECT *  "
				+"FROM WeChatProgressBar "
				+"WHERE ProgressBarName=\""+progressBarName+"\"";
		ResultSet rs=null;
		Statement stmt=null;
		ProgressBar pb=new ProgressBar();
		pb.setProgressBarName(progressBarName);
		try{
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				pb.setProgressDescribe(rs.getString("ProgressDescribe"));
				pb.setProgressLock(rs.getString("ProgressLock"));
				pb.setProgressValue(rs.getString("ProgressValue"));
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			rs=null;
			stmt=null;
			conn=null;
		}
		
		return pb;
	}
	
	public void updataInfo(ProgressBar pb) throws SQLException{
		Connection conn=MysqlUtil.getConnection();
		String sql=" "
				+"UPDATE WeChatProgressBar  "
				+"SET ProgressDescribe=?,"
				+"ProgressValue=?,"
				+"ProgressLock=?"
				+"WHERE ProgressBarName=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, pb.getProgressDescribe());
		ptmt.setString(2,pb.getProgressValue());
		ptmt.setString(3,pb.getProgressLock());
		ptmt.setString(4,pb.getProgressBarName());	
		ptmt.execute();

		
	}
	
	
	
}
