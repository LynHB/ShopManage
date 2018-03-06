package Lyn.ShopManage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Lyn.ShopManage.entity.WeChatVoiceSourceMaterial;
import Lyn.ShopManage.util.LogPrintFormat;
import Lyn.ShopManage.util.MysqlUtil;

public class WeChat_VoiceSourceMaterialDao {
	public void insertOneData(WeChatVoiceSourceMaterial wcism) throws SQLException{
		Connection conn=MysqlUtil.getConnection();
		String sql="INSERT INTO WeChatVoiceSourceMaterial"+
				"(media_id,name,update_time,url) "+
				"values(?,?,?,?);";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1,wcism.getMedia_id());
		ptmt.setString(2,wcism.getName());
		ptmt.setString(3,wcism.getUpdate_time());
		ptmt.setString(4, wcism.getUrl());
		ptmt.execute();
		LogPrintFormat.logPrint("Lyn", "执行WeChat_VoiceSourceMaterialDao类方法insertOneData()");
	}
	
	public WeChatVoiceSourceMaterial getOneData(String media_id){
		Connection conn=MysqlUtil.getConnection();
		String sql ="SELECT media_id,name,update_time,url "+
				"FROM WeChatVoiceSourceMaterial "+
				"WHERE media_id=\""+media_id+"\";";
		Statement stmt=null;
		ResultSet rs=null;
		WeChatVoiceSourceMaterial wcism=new WeChatVoiceSourceMaterial();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				wcism.setMedia_id(rs.getString("media_id"));
				wcism.setName(rs.getString("name"));
				wcism.setUpdate_time(rs.getString("update_time"));
				wcism.setUrl(rs.getString("url"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			rs=null;
			stmt=null;
			conn=null;
		}
		return wcism;
		
	}
	
	public ArrayList<WeChatVoiceSourceMaterial> getAllData(){
		ArrayList<WeChatVoiceSourceMaterial> wcismList=new ArrayList<WeChatVoiceSourceMaterial>();
		Connection conn=MysqlUtil.getConnection();
		String sql ="SELECT * "+
				"FROM WeChatVoiceSourceMaterial ";
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				WeChatVoiceSourceMaterial wcism=new WeChatVoiceSourceMaterial();
				wcism.setMedia_id(rs.getString("media_id"));
				wcism.setName(rs.getString("name"));
				wcism.setUpdate_time(rs.getString("update_time"));
				wcism.setUrl(rs.getString("url"));
				wcismList.add(wcism);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			rs=null;
			stmt=null;
			conn=null;
		}
		return wcismList;
		
	}
	
}
