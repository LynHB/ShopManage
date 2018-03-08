package Lyn.ShopManage.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Lyn.ShopManage.entity.CollectShop;
import Lyn.ShopManage.util.LogPrintFormat;
import Lyn.ShopManage.util.MysqlUtil;

public class CollectShopDao {
	public static void main(String[] args) {
		CollectShopDao csDao=new CollectShopDao();
		CollectShop cs=new CollectShop();
		cs.setCid("000001");
		cs.setName("测试商品");
		cs.setStockBalance(10);
		cs.setStockSell(5);
		cs.setAverageCost("15.3");
		cs.setMarketingPrice("21");
		cs.setClassification("奶粉");
		cs.setSpecifications("550L");
		cs.setBrand("c");
		cs.setDetail("wu");
		System.out.println(csDao.insertOneData(cs));
	}
	
	public ArrayList<CollectShop> selectAllData(){
		Connection conn=MysqlUtil.getConnection();
		String sql="SELECT * FROM CollectStockManage";
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<CollectShop> shopList=new ArrayList<CollectShop>();
		CollectStockClassificationDao classDao=new CollectStockClassificationDao();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				CollectShop cs=new CollectShop();
				cs.setCid(rs.getString("Cid"));
				cs.setName(rs.getString("Name"));
				cs.setStockBalance(rs.getInt("StockBalance"));
				cs.setStockSell(rs.getInt("StockSell"));
				cs.setAverageCost(rs.getString("AverageCost"));
				cs.setMarketingPrice(rs.getString("MarketingPrice"));
				cs.setClassification(classDao.useIdGetName(Integer.valueOf(rs.getString("Classification"))));
				cs.setSpecifications(rs.getString("Specifications"));
				cs.setBrand(rs.getString("Brand"));
				cs.setDetail(rs.getString("Detail"));
				shopList.add(cs);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			rs=null;
			stmt=null;
			conn=null;
		}
		
		return shopList;
	}
	
	
	public int insertOneData(CollectShop cs){
		Connection conn=MysqlUtil.getConnection();
		String sql="INSERT INTO CollectStockManage"+
				"(Cid,Sid,Name,StockBalance,StockSell,AverageCost,MarketingPrice,Classification,Specifications,Brand,Detail) "+
				"VALUES(?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ptmt;
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1,cs.getCid());
			ptmt.setInt(2,(Integer) (cs.getSid()!=null?cs.getSid():0));
			ptmt.setString(3,cs.getName());
			ptmt.setInt(4,cs.getStockBalance());
			ptmt.setInt(5,cs.getStockSell());
			ptmt.setString(6,cs.getAverageCost());
			ptmt.setString(7,cs.getMarketingPrice());
			ptmt.setString(8,cs.getClassification());
			ptmt.setString(9,cs.getSpecifications());
			ptmt.setString(10,cs.getBrand());
			ptmt.setString(11,cs.getDetail());
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}finally{
			ptmt=null;
			conn=null;
		}
		
		LogPrintFormat.logPrint("Lyn", "执行CollectShopDao类方法insertOneData()");
		return 0;
	}
}
