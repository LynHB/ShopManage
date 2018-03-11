package Lyn.ShopManage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Lyn.ShopManage.entity.CollectMemberChild;
import Lyn.ShopManage.util.LogPrintFormat;
import Lyn.ShopManage.util.MysqlUtil;

public class CollectMemberChildDao {
	public int insertChildenData(CollectMemberChild cmc){
		Connection conn=MysqlUtil.getConnection();
		PreparedStatement ptmt=null;
		String sql="INSERT INTO CollectMemberChild(ChilderId,Birthday,Sex,CreateTime,UpdateTime,UserId)"+
				"VALUES(?,?,?,?,?,?)";
		try {
			ptmt=conn.prepareStatement(sql);
			ptmt.setString(1, cmc.getChilderId());
			ptmt.setString(2, cmc.getBirthday());
			ptmt.setInt(3, cmc.getSex());
			ptmt.setString(4, cmc.getCreateTime());
			ptmt.setString(5, cmc.getUpdateTime());
			ptmt.setString(6, cmc.getUserId());
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
}
