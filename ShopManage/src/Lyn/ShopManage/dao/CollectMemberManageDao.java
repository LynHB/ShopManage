package Lyn.ShopManage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import Lyn.ShopManage.entity.CollectMemberChild;
import Lyn.ShopManage.entity.CollectMemberManage;
import Lyn.ShopManage.util.LogPrintFormat;
import Lyn.ShopManage.util.MysqlUtil;

public class CollectMemberManageDao {
	
	public static void main(String[] args) {
//		CollectMemberManage cmm=new CollectMemberManage();
//		cmm.setUserId("18039863649");
//		cmm.setAddress("西门村");
//		cmm.setCreateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
//		cmm.setUpdateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
//		cmm.setDetail("无");
//		cmm.setIntegral(100000);
//		cmm.setUserName("黄");
//		cmm.setIntegralTotal(10000);
//		ArrayList<CollectMemberChild> childList=new ArrayList<CollectMemberChild>();
//		CollectMemberChild cmc1=new CollectMemberChild();
//		CollectMemberChild cmc2=new CollectMemberChild();
//		String time1=String.valueOf(Calendar.getInstance().getTimeInMillis())+String.valueOf((int)((Math.random()*9+1)*100000));
//		String time2=String.valueOf(Calendar.getInstance().getTimeInMillis())+String.valueOf((int)((Math.random()*9+1)*100000));
//		
//		cmc1.setChilderId(time1);
//		cmc1.setBirthday("2017-01-02");
//		cmc1.setChildName("西瓜");
//		cmc1.setCreateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
//		cmc1.setUpdateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
//		cmc1.setSex(0);
//		cmc1.setUserId("18039863649");
//		cmc2.setChilderId(time2);
//		cmc2.setBirthday("2017-01-03");
//		cmc2.setChildName("饺子");
//		cmc2.setCreateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
//		cmc2.setUpdateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
//		cmc2.setSex(1);
//		cmc2.setUserId("18039863649");
//		
//		childList.add(cmc1);
//		childList.add(cmc2);
//		cmm.setChilden(childList);
//		cmm.setUserChildId(cmc1.getChilderId()+" "+cmc2.getChilderId());
//		cmm.setUserChildName(cmc1.getChildName()+" "+cmc2.getChildName());
//		CollectMemberManageDao cmmDao=new CollectMemberManageDao();
//		cmmDao.insertOneData(cmm);
//		CollectMemberChildDao cmcDao=new CollectMemberChildDao();
//		cmcDao.insertChildenData(cmc1);
//		cmcDao.insertChildenData(cmc2);
		
		
		CollectMemberManageDao cmmDao=new CollectMemberManageDao();
		CollectMemberManage cmm=cmmDao.useIdGetOneData("18039863649");
		System.out.println(cmm.getChilden().get(0).getBirthday());
		
	}
	
	public int upDataOneData(String id,CollectMemberManage cmm){
		Connection conn =MysqlUtil.getConnection();
		String sql ="UPDATE CollectMemberManage "+
				"SET UserId=?,UserName=?,UserChildId=?,Address=?,Integral=?,UpdateTime=?,Detail=?,IntegralTotal=? "+
				"WHERE UserId=?";
		PreparedStatement ptmt=null;
		try {
			ptmt=conn.prepareStatement(sql);
			ptmt.setString(1,cmm.getUserId());
			ptmt.setString(2,cmm.getUserName());
			ptmt.setString(3, cmm.getUserChildId());
			ptmt.setString(4, cmm.getAddress());
			ptmt.setInt(5, cmm.getIntegral());
			ptmt.setString(6, cmm.getUpdateTime());
			ptmt.setString(7, cmm.getDetail());
			ptmt.setInt(8, cmm.getIntegralTotal());
			ptmt.setString(9,id);
			ptmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogPrintFormat.logPrint("Lyn", "CollectMemberManageDao类使用upDataOneData方法执行失败，id："+cmm.getUserId()+"修改失败。");
			return 1;
		}finally{
			ptmt=null;
			conn=null;
		}
		LogPrintFormat.logPrint("Lyn", "CollectMemberManageDao类使用upDataOneData方法执行成功，id："+cmm.getUserId()+"修改成功。");
		return 0;
	}
	
	public int insertOneData(CollectMemberManage cmm){
		Connection conn=MysqlUtil.getConnection();
		String sql="INSERT INTO CollectMemberManage(UserId,UserName,UserChildId,Address,Integral,CreateTime,UpdateTime,Detail,IntegralTotal) "+
				"VALUES(?,?,?,?,?,?,?,?,?);";
		
		PreparedStatement ptmt=null;
		try {
			ptmt=conn.prepareStatement(sql);
			ptmt.setString(1,cmm.getUserId());
			ptmt.setString(2,cmm.getUserName());
			ptmt.setString(3, cmm.getUserChildId());
			ptmt.setString(4, cmm.getAddress());
			ptmt.setInt(5, cmm.getIntegral());
			ptmt.setString(6, cmm.getCreateTime());
			ptmt.setString(7, cmm.getUpdateTime());
			ptmt.setString(8, cmm.getDetail());
			ptmt.setInt(9, cmm.getIntegralTotal());
			ptmt.execute();
			
		} catch (SQLException e) {
			LogPrintFormat.logPrint("Lyn", "CollectMemberManageDao类使用insertOneData方法执行失败，id："+cmm.getUserId()+"添加失败。");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		} finally{
			ptmt=null;
			conn=null;
		}
		
		
		LogPrintFormat.logPrint("Lyn", "CollectMemberManageDao类使用insertOneData方法执行成功，id："+cmm.getUserId()+"添加成功。");
		return 0;
		
	}
	
	public ArrayList<CollectMemberManage> selectAllData(){
		Connection conn=MysqlUtil.getConnection();
		Statement stmt=null;
		String sql="SELECT * FROM CollectMemberManage";
		ArrayList<CollectMemberManage> cmmList=new ArrayList<CollectMemberManage>();
		try {
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				CollectMemberManage cmm=new CollectMemberManage();
				cmm.setUserId(rs.getString("UserId"));
				cmm.setUserName(rs.getString("UserName"));
				cmm.setUserChildId(rs.getString("UserChildId"));
				cmm.setAddress(rs.getString("Address"));
				cmm.setIntegral(rs.getInt("Integral"));
				cmm.setUpdateTime(rs.getString("UpdateTime"));
				cmm.setCreateTime(rs.getString("CreateTime"));
				cmm.setIntegralTotal(rs.getInt("IntegralTotal"));
				cmm.setDetail(rs.getString("Detail"));
				cmmList.add(cmm);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			stmt=null;
			conn=null;
		}
		
		ArrayList<CollectMemberManage> cmmList2=new ArrayList<CollectMemberManage>();
		Iterator it=cmmList.iterator();
		CollectMemberChildDao childDao=new CollectMemberChildDao();
		while(it.hasNext()){
			CollectMemberManage cmm=(CollectMemberManage) it.next();
			ArrayList<CollectMemberChild> childList=new ArrayList<CollectMemberChild>();
			String[] array=cmm.getUserChildId().split(" ");
			for(int i=0;i<array.length;i++){
				String id=array[i];
				CollectMemberChild cmc=childDao.useCidGetCInfo(id);
				childList.add(cmc);
			}
			cmm.setChilden(childList);
			cmmList2.add(cmm);
		}
		LogPrintFormat.logPrint("Lyn", "CollectMemberManageDao类使用selectAllData方法执行成功");
		return cmmList2;
	}
	
	
	public CollectMemberManage useIdGetOneData(String userId){
		Connection conn=MysqlUtil.getConnection();
		Statement stmt=null;
		String sql="SELECT * FROM CollectMemberManage WHERE UserId=\""+userId+"\"";
		CollectMemberManage cmm=new CollectMemberManage();
		try {
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				cmm.setUserId(rs.getString("UserId"));
				cmm.setUserName(rs.getString("UserName"));
				cmm.setUserChildId(rs.getString("UserChildId"));
				cmm.setAddress(rs.getString("Address"));
				cmm.setIntegral(rs.getInt("Integral"));
				cmm.setUpdateTime(rs.getString("UpdateTime"));
				cmm.setCreateTime(rs.getString("CreateTime"));
				cmm.setIntegralTotal(rs.getInt("IntegralTotal"));
				cmm.setDetail(rs.getString("Detail"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			stmt=null;
			conn=null;
		}
		if(cmm.getUserChildId()==null){
			return cmm;
		}else{
			CollectMemberChildDao childDao=new CollectMemberChildDao();
			ArrayList<CollectMemberChild> childList=new ArrayList<CollectMemberChild>();
			String[] array=cmm.getUserChildId().split(" ");
			for(int i=0;i<array.length;i++){
				String id=array[i];
				CollectMemberChild cmc=childDao.useCidGetCInfo(id);
				childList.add(cmc);
			}
			cmm.setChilden(childList);
			
			LogPrintFormat.logPrint("Lyn", "CollectMemberManageDao类使用useIdGetOneData方法执行成功");
			return cmm;
		}
	}
}
