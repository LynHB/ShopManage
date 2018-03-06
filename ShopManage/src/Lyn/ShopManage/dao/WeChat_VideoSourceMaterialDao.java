package Lyn.ShopManage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Lyn.ShopManage.entity.WeChatVideoSourceMaterial;
import Lyn.ShopManage.util.LogPrintFormat;
import Lyn.ShopManage.util.MysqlUtil;

public class WeChat_VideoSourceMaterialDao {
	public static void main(String[] args) {
		WeChat_VideoSourceMaterialDao wc =new WeChat_VideoSourceMaterialDao();
		if(wc.getOneData("asa").getMedia_id()==null){
			System.out.println("111");
		}
	}
	public void insertOneData(WeChatVideoSourceMaterial wcism) throws SQLException{
		Connection conn=MysqlUtil.getConnection();
		String sql="INSERT INTO WeChatVideoSourceMaterial"+
				"(media_id,name,update_time,url,title,description) "+
				"values(?,?,?,?,?,?);";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1,wcism.getMedia_id());
		ptmt.setString(2,wcism.getName());
		ptmt.setString(3,wcism.getUpdate_time());
		ptmt.setString(4, wcism.getUrl());
		ptmt.setString(5, wcism.getTitle());
		ptmt.setString(6, wcism.getDescription());
		ptmt.execute();
		LogPrintFormat.logPrint("Lyn", "执行WeChat_VideoSourceMaterialDao类方法insertOneData()");
	}
	
	public WeChatVideoSourceMaterial getOneData(String media_id){
		Connection conn=MysqlUtil.getConnection();
		String sql ="SELECT media_id,name,update_time,url,title,description "+
				"FROM WeChatVideoSourceMaterial "+
				"WHERE media_id=\""+media_id+"\";";
		Statement stmt=null;
		ResultSet rs=null;
		WeChatVideoSourceMaterial wcism=new WeChatVideoSourceMaterial();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				wcism.setMedia_id(rs.getString("media_id"));
				wcism.setName(rs.getString("name"));
				wcism.setUpdate_time(rs.getString("update_time"));
				wcism.setUrl(rs.getString("url"));
				wcism.setDescription(rs.getString("description"));
				wcism.setTitle(rs.getString("title"));
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
	
	public ArrayList<WeChatVideoSourceMaterial> getAllData(){
		ArrayList<WeChatVideoSourceMaterial> wcismList=new ArrayList<WeChatVideoSourceMaterial>();
		Connection conn=MysqlUtil.getConnection();
		String sql ="SELECT * "+
				"FROM WeChatVideoSourceMaterial ";
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				WeChatVideoSourceMaterial wcism=new WeChatVideoSourceMaterial();
				wcism.setMedia_id(rs.getString("media_id"));
				wcism.setName(rs.getString("name"));
				wcism.setUpdate_time(rs.getString("update_time"));
				wcism.setUrl(rs.getString("url"));
				wcism.setDescription(rs.getString("description"));
				wcism.setTitle(rs.getString("title"));
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
