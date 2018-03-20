package Lyn.ShopManage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Lyn.ShopManage.entity.CollectFlowWater;
import Lyn.ShopManage.util.LogPrintFormat;
import Lyn.ShopManage.util.MysqlUtil;

public class CollectFlowWaterDao {

	
	public int insertIntoOneData(CollectFlowWater fw){
		Connection conn=MysqlUtil.getConnection();
		String sql="INSERT INTO CollectFlowWater"+
				"(FlowWaterId,CreateTime,ShopId,ShopName,Specifications,Brand,Classification,UserId,UserName,"+
				"Price,Cost,ShopTransaction,ShopNumber,Integral,Turnover,DiscountType,Profit,FlowWaterType) "+
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ptmt=null;
		try {
			ptmt=conn.prepareStatement(sql);
			ptmt.setString(1, fw.getFlowWaterId());
			ptmt.setString(2, fw.getCreateTime());
			ptmt.setString(3, fw.getShopId());
			ptmt.setString(4, fw.getShopName());
			ptmt.setString(5, fw.getSpecifications());
			ptmt.setString(6, fw.getBrand());
			ptmt.setString(7, fw.getClassification());
			ptmt.setString(8, fw.getUserId());
			ptmt.setString(9, fw.getUserName());
			ptmt.setString(10,fw.getPrice());
			ptmt.setString(11,fw.getCost());
			ptmt.setString(12,fw.getShopTransaction());
			ptmt.setString(13,fw.getShopNumber());
			ptmt.setString(14,fw.getIntegral());
			ptmt.setString(15,fw.getTurnover());
			ptmt.setString(16,fw.getDiscountType());
			ptmt.setString(17,fw.getProfit());
			ptmt.setString(18,fw.getFlowWaterType());
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogPrintFormat.logPrint("Lyn", "CollectFlowWaterDao类使用insertIntoOneData方法执行失败，id"+fw.getFlowWaterId()+"流水编号添加失败。");
			return 1;
		}finally{
			ptmt=null;
			conn=null;
		}
		
		
		LogPrintFormat.logPrint("Lyn", "CollectFlowWaterDao类使用insertIntoOneData方法执行成功，id"+fw.getFlowWaterId()+"流水编号添加成功。");
		return 0;
		
	
		
	}
	
	public CollectFlowWater selectOneData(CollectFlowWater cfw){
		Connection conn=MysqlUtil.getConnection();
		String sql ="SELECT * FROM CollectFlowWater WHERE FlowWaterId=\""+cfw.getFlowWaterId()+"\" AND ShopId=\""+cfw.getShopId()+"\"";
		Statement stmt=null;
		ResultSet rs=null;
		CollectFlowWater cf=new CollectFlowWater();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				cf.setFlowWaterId(rs.getString("FlowWaterId"));
				cf.setCreateTime(rs.getString("CreateTime"));
				cf.setShopId(rs.getString("ShopId"));
				cf.setShopName(rs.getString("ShopName"));
				cf.setSpecifications(rs.getString("Specifications"));
				cf.setBrand(rs.getString("Brand"));
				cf.setClassification(rs.getString("Classification"));
				cf.setUserId(rs.getString("UserId"));
				cf.setUserName(rs.getString("UserName"));
				cf.setPrice(rs.getString("Price"));
				cf.setCost(rs.getString("Cost"));
				cf.setShopTransaction(rs.getString("ShopTransaction"));
				cf.setShopNumber(rs.getString("ShopNumber"));
				cf.setIntegral(rs.getString("Integral"));
				cf.setTurnover(rs.getString("Turnover"));
				cf.setDiscountType(rs.getString("DiscountType"));
				cf.setProfit(rs.getString("Profit"));
				cf.setFlowWaterType(rs.getString("FlowWaterType"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			stmt=null;
			conn=null;
		}
		
		return cf;
	}
	
	public ArrayList<CollectFlowWater> GetAllDataList(){
		Connection conn=MysqlUtil.getConnection();
		String sql="SELECT * FROM CollectFlowWater ORDER BY CreateTime DESC";
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<CollectFlowWater> cfList=new ArrayList<CollectFlowWater>();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				CollectFlowWater cf=new CollectFlowWater();
				cf.setFlowWaterId(rs.getString("FlowWaterId"));
				cf.setCreateTime(rs.getString("CreateTime"));
				cf.setShopId(rs.getString("ShopId"));
				cf.setShopName(rs.getString("ShopName"));
				cf.setSpecifications(rs.getString("Specifications"));
				cf.setBrand(rs.getString("Brand"));
				cf.setClassification(rs.getString("Classification"));
				cf.setUserId(rs.getString("UserId"));
				cf.setUserName(rs.getString("UserName"));
				cf.setPrice(rs.getString("Price"));
				cf.setCost(rs.getString("Cost"));
				cf.setShopTransaction(rs.getString("ShopTransaction"));
				cf.setShopNumber(rs.getString("ShopNumber"));
				cf.setIntegral(rs.getString("Integral"));
				cf.setTurnover(rs.getString("Turnover"));
				cf.setDiscountType(rs.getString("DiscountType"));
				cf.setProfit(rs.getString("Profit"));
				cf.setFlowWaterType(rs.getString("FlowWaterType"));
				
				cfList.add(cf);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			stmt=null;
			conn=null;
		}
		
		return cfList;
		
	}
	
	
	public String totalMoney(){
		Connection conn=MysqlUtil.getConnection();
		String sql="SELECT SUM(ShopTransaction) totalMoney FROM CollectFlowWater";
		Statement stmt=null;
		ResultSet rs=null;
		String result="";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				result=rs.getString("totalMoney");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			stmt=null;
			conn=null;
		}
		
		return result;
		
	}
	
}
