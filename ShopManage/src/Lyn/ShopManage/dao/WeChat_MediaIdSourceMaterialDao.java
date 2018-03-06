package Lyn.ShopManage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import Lyn.ShopManage.entity.WeChatArtiles;
import Lyn.ShopManage.entity.WeChatNewsMaterial;
import Lyn.ShopManage.util.JsonSplitNull;
import Lyn.ShopManage.util.MysqlUtil;

public class WeChat_MediaIdSourceMaterialDao {
	
	public static void main(String[] args) {
		WeChat_MediaIdSourceMaterialDao msmd=new WeChat_MediaIdSourceMaterialDao();
		ArrayList<WeChatNewsMaterial> newsList=new ArrayList<WeChatNewsMaterial>();
		ArrayList<String> mediaIdList=msmd.useTypeGetMedia("news");
		Iterator<String> it =mediaIdList.iterator();
		while(it.hasNext()){
			newsList.add(msmd.useNewsMediaIdGetNews(it.next()));
		}
		JSONArray json=JSONArray.fromObject(newsList);
		System.out.println(json);
		
	}
	/**
	 * 通过type获得对应的mediaId arrayList列表
	 * @param type
	 * @return ArrayList<String>
	 * @author Lyn-Rebirth 2018.2.8
	 */
	public   ArrayList<String> useTypeGetMedia(String type){
		Connection conn=MysqlUtil.getConnection();
		String sql="select media_Id from WeChat_MediaIdSourceMaterial where type=\""+type+"\"";
		ResultSet rs=null;
		ArrayList<String>  strArray = new ArrayList<String> (); 
		Statement stmt=null;
		try{
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				strArray.add(rs.getString("media_id"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			rs=null;
			stmt=null;
			conn=null;
		}
		
		return strArray;
	}
	public ArrayList<String> useMediaIdGetTime(String mediaId){
		Connection conn=MysqlUtil.getConnection();
		String sql="SELECT update_time,create_time FROM WeChat_MediaIdSourceMaterial where media_id=\""+mediaId+"\"";
		ResultSet rs=null;
		Statement stmt=null;
		ArrayList<String> arrayList=new ArrayList<String>();
		try{
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				arrayList.add(rs.getString("create_time"));
				arrayList.add(rs.getString("update_time"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			rs=null;
			stmt=null;
			conn=null;
		}
		return arrayList;
	}
	
	public WeChatNewsMaterial useNewsMediaIdGetNews(String mediaId){
		Connection conn=MysqlUtil.getConnection();
		String sql = "SELECT * FROM WeChat_ArtileSourceMaterial WHERE media_id=\""+mediaId+"\" ORDER BY orderNum";
		ResultSet rs=null;
		ArrayList<WeChatArtiles> wcaList=new ArrayList<WeChatArtiles>();
		WeChatNewsMaterial wcnm=new WeChatNewsMaterial();
		Statement stmt=null;
		try{
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				WeChatArtiles wca =new WeChatArtiles();
				wca.setTitle(rs.getString("title"));
				wca.setThumb_media_id(rs.getString("thumb_media_id"));
				wca.setAuthor(rs.getString("author"));
				wca.setDigest(rs.getString("digest"));
				wca.setShow_cover_pic(rs.getInt("show_cover_pic"));
				wca.setContent(rs.getString("content"));
				wca.setContent_source_url(rs.getString("content_source_url"));
				wca.setNeed_open_comment(rs.getInt("need_open_comment"));
				wca.setOnly_fans_can_comment(rs.getInt("only_fans_can_comment"));
				wca.setUrl(rs.getString("url"));
				wca.setThumb_url(rs.getString("thumb_url"));
				wcaList.add(wca);
			}
			wcnm.setMedia_id(mediaId);
			wcnm.setNews_item(wcaList);
			wcnm.setCreate_time(useMediaIdGetTime(mediaId).get(0));
			wcnm.setUpdate_time(useMediaIdGetTime(mediaId).get(1));
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			rs=null;
			stmt=null;
			conn=null;
		}
		
		return wcnm;
	}
	
	public String mediaIdIsNeedUpdate(String mediaId,String updateTime){
		Connection conn=MysqlUtil.getConnection();
		String sql="SELECT update_time "+
				"FROM WeChat_MediaIdSourceMaterial "+
				"WHERE media_id=\""+mediaId+"\"; ";
		ResultSet rs=null;
		Statement stmt =null;
		String updateTime2=null;
		try{
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				updateTime2 =rs.getString("update_time");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			rs=null;
			stmt=null;
			conn=null;
		}
		if(updateTime2==null){
			return "null";
		}else if(updateTime2.equals(updateTime)){
			return "true";
		}else if(!updateTime2.equals(updateTime)){
			return "false";
		}
		return "null";
	}
	
	public void insertIntoArtileTable(WeChatArtiles wca,String mediaId) throws SQLException{
		Connection conn=MysqlUtil.getConnection();
		String sql="INSERT INTO "+
				"WeChat_ArtileSourceMaterial(media_id,title,thumb_media_id,author,digest,show_cover_pic,content,content_source_url,need_open_comment,only_fans_can_comment,url,thumb_url,orderNum) "+
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1,mediaId);
		ptmt.setString(2,wca.getTitle());
		ptmt.setString(3,wca.getThumb_media_id());
		ptmt.setString(4,wca.getAuthor());
		ptmt.setString(5, wca.getDigest());
		ptmt.setInt(6, wca.getShow_cover_pic());
		ptmt.setString(7, wca.getContent());
		ptmt.setString(8, wca.getContent_source_url());
		ptmt.setInt(9,wca.getNeed_open_comment());
		ptmt.setInt(10, wca.getOnly_fans_can_comment());
		ptmt.setString(11,wca.getUrl());
		ptmt.setString(12, wca.getThumb_url());
		ptmt.setString(13, wca.getOrder());
		ptmt.execute();
		
	}
	
	public void insertIntoMediaIdSourceMaterialTable(WeChatNewsMaterial wcnm,int OrderNum) throws SQLException{
		Connection conn = MysqlUtil.getConnection();
		String sql="INSERT INTO "+
				"WeChat_MediaIdSourceMaterial(media_id,type,media_number,create_time,update_time) "+
				"VALUES(?,?,?,?,?)";
		PreparedStatement ptmt =conn.prepareStatement(sql);
		ptmt.setString(1, wcnm.getMedia_id());
		ptmt.setString(2, "news");
		ptmt.setString(3,Integer.toString(OrderNum));
		ptmt.setString(4, wcnm.getCreate_time());
		ptmt.setString(5, wcnm.getUpdate_time());
		ptmt.execute();
	}
	
	public void deleteNewsInfo(String mediaId) throws SQLException{
		Connection conn=MysqlUtil.getConnection();
		String sql="DELETE * FROM WeChat_MediaIdSourceMaterial "+
				"WHERE media_id=?;";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, mediaId);
		ptmt.execute();
	}
	
	public void deleteArtileInfo(String mediaId) throws SQLException{
		Connection conn=MysqlUtil.getConnection();
		String sql="DELETE * FROM WeChat_MediaIdSourceMaterial "+
				"WHERE media_id=?;";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, mediaId);
		ptmt.execute();
	}
}
