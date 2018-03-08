package Lyn.ShopManage.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Lyn.ShopManage.entity.CollectStockClassification;
import Lyn.ShopManage.util.MysqlUtil;

public class CollectStockClassificationDao {

	public static void main(String[] args) {
		CollectStockClassificationDao cfDao=new CollectStockClassificationDao();
		System.out.println(cfDao.useIdGetName(1));
		System.out.println(cfDao.selectAllData().get(0).getId());
	}
	public String useIdGetName(int id){
		Connection conn=MysqlUtil.getConnection();
		String sql="SELECT ClassName FROM CollectStockClassification WHERE ClassId="+id;
		Statement stmt=null;
		ResultSet rs=null;
		String name="";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				name=rs.getString("ClassName");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			stmt=null;
			rs=null;
			conn=null;
			
		}
		return name;
		
	}
	
	
	public ArrayList<CollectStockClassification> selectAllData(){
		Connection conn=MysqlUtil.getConnection();
		String sql="select * from CollectStockClassification ORDER BY CLASSID;";
		ResultSet rs=null;
		Statement stmt=null;
		ArrayList<CollectStockClassification> classList=new ArrayList<CollectStockClassification>();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				CollectStockClassification classfi=new CollectStockClassification();
				classfi.setId(rs.getString("ClassId"));
				classfi.setName(rs.getString("ClassName"));
				classList.add(classfi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			rs=null;
			stmt=null;
			conn=null;
		}
		
		return classList;
	}
}
