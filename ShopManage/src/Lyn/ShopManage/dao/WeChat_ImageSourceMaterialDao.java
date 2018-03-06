package Lyn.ShopManage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Lyn.ShopManage.entity.WeChatImageSourceMaterial;
import Lyn.ShopManage.util.LogPrintFormat;
import Lyn.ShopManage.util.MysqlUtil;

public class WeChat_ImageSourceMaterialDao {
	
	public void insertOneData(WeChatImageSourceMaterial wcism) throws SQLException{
		Connection conn=MysqlUtil.getConnection();
		String sql="INSERT INTO WeChatImagesourceMaterial"+
				"(media_id,name,update_time,url,SName) "+
				"values(?,?,?,?,?);";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1,wcism.getMedia_id());
		ptmt.setString(2,wcism.getName());
		ptmt.setString(3,wcism.getUpdate_time());
		ptmt.setString(4, wcism.getUrl());
		ptmt.setString(5, wcism.getSName());
		ptmt.execute();
		LogPrintFormat.logPrint("Lyn", "执行WeChat_ImageSourceMaterialDao类方法insertOneData()");
	}
	
	public WeChatImageSourceMaterial getOneData(String media_id){
		Connection conn=MysqlUtil.getConnection();
		String sql ="SELECT media_id,name,update_time,url,SName "+
				"FROM WeChatImageSourceMaterial "+
				"WHERE media_id=\""+media_id+"\";";
		Statement stmt=null;
		ResultSet rs=null;
		WeChatImageSourceMaterial wcism=new WeChatImageSourceMaterial();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				wcism.setMedia_id(rs.getString("media_id"));
				wcism.setName(rs.getString("name"));
				wcism.setUpdate_time(rs.getString("update_time"));
				wcism.setUrl(rs.getString("url"));
				wcism.setSName(rs.getString("SName"));
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
	
	public ArrayList<WeChatImageSourceMaterial> getAllData(){
		ArrayList<WeChatImageSourceMaterial> wcismList=new ArrayList<WeChatImageSourceMaterial>();
		Connection conn=MysqlUtil.getConnection();
		String sql ="SELECT * "+
				"FROM WeChatImageSourceMaterial ";
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				WeChatImageSourceMaterial wcism=new WeChatImageSourceMaterial();
				wcism.setMedia_id(rs.getString("media_id"));
				wcism.setName(rs.getString("name"));
				wcism.setUpdate_time(rs.getString("update_time"));
				wcism.setUrl(rs.getString("url"));
				wcism.setSName(rs.getString("SName"));
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
