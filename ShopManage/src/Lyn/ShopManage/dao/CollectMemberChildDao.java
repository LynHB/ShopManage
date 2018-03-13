package Lyn.ShopManage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Lyn.ShopManage.entity.CollectMemberChild;
import Lyn.ShopManage.util.LogPrintFormat;
import Lyn.ShopManage.util.MysqlUtil;

public class CollectMemberChildDao {
	
	
	public int useIdDeleteData(String id){
		Connection conn=MysqlUtil.getConnection();
		String sql="DELETE  FROM CollectMemberChild WHERE ChilderId=\""+id+"\"";
		Statement stmt=null;
		
		try {
			stmt=conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogPrintFormat.logPrint("Lyn", "CollectMemberChildDao类使用useIdDeleteData方法执行失败，id"+id+" children删除失败。");
			return 1;
		}
		LogPrintFormat.logPrint("Lyn", "CollectMemberChildDao类使用useIdDeleteData方法执行成功，id"+id+" children删除成功。");
		return 0;
		
	}
	public int updateChildenDatat(CollectMemberChild cmc){
		Connection conn=MysqlUtil.getConnection();
		PreparedStatement ptmt=null;
		String sql="UPDATE CollectMemberChild "+
				"SET ChildName=?,Birthday=?,Sex=?,UpdateTime=?,UserId=? "+
				" WHERE ChilderId=?";
		try {
			ptmt=conn.prepareStatement(sql);
			ptmt.setString(1, cmc.getChildName());
			ptmt.setString(2, cmc.getBirthday());
			ptmt.setInt(3, cmc.getSex());
			ptmt.setString(4, cmc.getUpdateTime());
			ptmt.setString(5, cmc.getUserId());
			ptmt.setString(6, cmc.getChilderId());
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogPrintFormat.logPrint("Lyn", "CollectMemberChildDao类使用updateChildenDatat方法执行失败，id"+cmc.getUserId()+"children修改失败。");
			return 1;
		}finally{
			ptmt=null;
			conn=null;
		}
		LogPrintFormat.logPrint("Lyn", "CollectMemberChildDao类使用updateChildenDatat方法执行成功，id"+cmc.getUserId()+"children修改成功。");
		return 0;
	}
	public int insertChildenData(CollectMemberChild cmc){
		Connection conn=MysqlUtil.getConnection();
		PreparedStatement ptmt=null;
		String sql="INSERT INTO CollectMemberChild(ChilderId,Birthday,Sex,CreateTime,UpdateTime,UserId,ChildName)"+
				"VALUES(?,?,?,?,?,?,?)";
		try {
			ptmt=conn.prepareStatement(sql);
			ptmt.setString(1, cmc.getChilderId());
			ptmt.setString(2, cmc.getBirthday());
			ptmt.setInt(3, cmc.getSex());
			ptmt.setString(4, cmc.getCreateTime());
			ptmt.setString(5, cmc.getUpdateTime());
			ptmt.setString(6, cmc.getUserId());
			ptmt.setString(7, cmc.getChildName());
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogPrintFormat.logPrint("Lyn", "CollectMemberChildDao类使用insertChildenData方法执行失败，id"+cmc.getUserId()+"children添加失败。");
			return 1;
		}finally{
			ptmt=null;
			conn=null;
		}
		LogPrintFormat.logPrint("Lyn", "CollectMemberChildDao类使用insertChildenData方法执行成功，id"+cmc.getUserId()+"children添加成功。");
		return 0;
	}
	
	public CollectMemberChild useCidGetCInfo(String id){
		Connection conn=MysqlUtil.getConnection();
		String sql="SELECT *  FROM CollectMemberChild WHERE ChilderId=\""+id+"\"";
		Statement stmt=null;
		CollectMemberChild cmc=new CollectMemberChild();
		try {
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				cmc.setChilderId(id);
				cmc.setBirthday(rs.getString("Birthday"));
				cmc.setCreateTime(rs.getString("CreateTime"));
				cmc.setUpdateTime(rs.getString("UpdateTime"));
				cmc.setUserId(rs.getString("UserId"));
				cmc.setSex(rs.getInt("Sex"));
				cmc.setChildName(rs.getString("ChildName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			stmt=null;
			conn=null;
		}
		LogPrintFormat.logPrint("Lyn", "CollectMemberChildDao类使用useCidGetCInfo方法执行成功,返回ChildId为："+cmc.getChilderId()+"数据");
		return cmc;
	}
}
