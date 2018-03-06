package Lyn.ShopManage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Lyn.ShopManage.entity.WeChatEvent;
import Lyn.ShopManage.util.LogPrintFormat;
import Lyn.ShopManage.util.MysqlUtil;

public class WeChat_EventDao {
	public static void main(String[] args) throws SQLException {
		WeChat_EventDao ed= new WeChat_EventDao();
		WeChatEvent we=ed.selectOneData("click", "door");
		System.out.println(we.getEvetReply());
	}
	
	public ArrayList<WeChatEvent> selectEventTypeData(String eventType){
		Connection conn = MysqlUtil.getConnection();
		String sql=" "
				+"SELECT * FROM WeChat_Event "
				+"WHERE EventType=\""+eventType+"\"";
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<WeChatEvent> eventList=new ArrayList<WeChatEvent>();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				WeChatEvent event=new WeChatEvent();
				event.setEventCode(rs.getString("EventCode"));
				event.setEventType(rs.getString("EventType"));
				event.setEvetReply(rs.getString("EvetReply"));
				event.setReplyType(rs.getString("ReplyType"));
				eventList.add(event);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			rs=null;
			stmt=null;
			conn=null;
		}
		
		return eventList;
	}
	
	public WeChatEvent selectOneData(String eventType,String eventCode){
		Connection conn = MysqlUtil.getConnection();
		String sql=" "
				+"SELECT * FROM WeChat_Event "
				+"WHERE EventType=\""+eventType+"\" AND EventCode=\""+eventCode+"\"";
		Statement stmt=null;
		ResultSet rs=null;
		WeChatEvent event=new WeChatEvent();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				event.setEventCode(rs.getString("EventCode"));
				event.setEventType(rs.getString("EventType"));
				event.setEvetReply(rs.getString("EvetReply"));
				event.setReplyType(rs.getString("ReplyType"));
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			rs=null;
			stmt=null;
			conn=null;
		}
		
		return event;
	}
	
	public void insertOneData(WeChatEvent event){
		Connection conn=MysqlUtil.getConnection();
		String sql =" "
				+"INSERT INTO WeChat_Event(EventType,EvetReply,ReplyType,EventCode)"
				+" VALUES(?,?,?,?)";
		PreparedStatement ptmt;
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1,event.getEventType());
			ptmt.setString(2,event.getEvetReply());
			ptmt.setString(3,event.getReplyType());
			ptmt.setString(4,event.getEventCode());
			
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ptmt=null;
			
		}
		LogPrintFormat.logPrint("Lyn", "执行WeChat_EventDao类方法insertOneData()");
	}
	
	
	public void setSubscribe(WeChatEvent wce) throws SQLException{
		Connection conn = MysqlUtil.getConnection();
		String sql=" "
				+"update WeChat_Event "
				+"set EvetReply=\""+wce.getEvetReply()+"\",ReplyType=\""+wce.getReplyType()+"\" "
				+"where EventType='subscribe'";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.execute();
	}
	
	public WeChatEvent getUnSubscribe(){
		Connection conn = MysqlUtil.getConnection();
		String sql=" " 
				+"select EvetReply,ReplyType "
				+"from WeChat_Event "
				+"where EventType='unsubscribe'";
		ResultSet rs= null;
		PreparedStatement ptmt = null;
		WeChatEvent event =new WeChatEvent();
		try{
			ptmt= conn.prepareStatement(sql);
			rs=ptmt.executeQuery();
			while(rs.next()){
				event.setReplyType(rs.getString("ReplyType"));
				event.setEvetReply(rs.getString("EvetReply"));
			} 
		}catch(Exception ex){
			ex.printStackTrace();
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
		
		return event;
	}
	
	public void setUnSubscribe(WeChatEvent wce) throws SQLException{
		Connection conn = MysqlUtil.getConnection();
		String sql=" "
				+"update WeChat_Event "
				+"set EvetReply=\""+wce.getEvetReply()+"\",ReplyType=\""+wce.getReplyType()+"\" "
				+"where EventType='unsubscribe'";
		System.out.println(sql);
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.execute();
	}
	
	public WeChatEvent getSubscribe(){
		Connection conn = MysqlUtil.getConnection();
		String sql=" " 
				+"select EvetReply,ReplyType "
				+"from WeChat_Event "
				+"where EventType='subscribe'";
		ResultSet rs= null;
		PreparedStatement ptmt = null;
		WeChatEvent event =new WeChatEvent();
		try{
			ptmt= conn.prepareStatement(sql);
			rs=ptmt.executeQuery();
			while(rs.next()){
				event.setReplyType(rs.getString("ReplyType"));
				event.setEvetReply(rs.getString("EvetReply"));
			} 
		}catch(Exception ex){
			ex.printStackTrace();
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
		
		return event;
	}
}
