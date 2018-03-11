package Lyn.ShopManage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		CollectMemberChild cmc1=new CollectMemberChild();
		CollectMemberChild cmc2=new CollectMemberChild();
		ArrayList<CollectMemberChild> cmcList=new ArrayList<CollectMemberChild>();
		CollectMemberManage cmm=new CollectMemberManage();
		CollectMemberChildDao cmcDao=new CollectMemberChildDao();
		CollectMemberManageDao cmmDao=new CollectMemberManageDao();
		cmm.setUserId("18039863649");
		cmm.setUserName("黄");
		String childId1=String.valueOf(Calendar.getInstance().getTimeInMillis());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String childId2=String.valueOf(Calendar.getInstance().getTimeInMillis());
		cmm.setUserChildId(childId1+" "+childId2);
		cmm.setAddress("西门村");
		cmm.setIntegral(10000);
		cmm.setCreateTime(childId1);
		cmm.setUpdateTime(childId1);
		
		cmc1.setBirthday("2017-01-01");
		cmc1.setChilderId(childId1);
		cmc1.setCreateTime(childId1);
		cmc1.setSex(1);
		cmc1.setUpdateTime(childId1);
		cmc1.setUserId(cmm.getUserId());
		
		cmc2.setBirthday("2017-01-01");
		cmc2.setChilderId(childId2);
		cmc2.setCreateTime(childId2);
		cmc2.setSex(1);
		cmc2.setUpdateTime(childId2);
		cmc2.setUserId(cmm.getUserId());
		cmcList.add(cmc1);
		cmcList.add(cmc2);
		cmm.setChilden(cmcList);
		cmmDao.insertOneData(cmm);
		Iterator it =cmcList.iterator();
		while(it.hasNext()){
			CollectMemberChild tmp=(CollectMemberChild) it.next();
			cmcDao.insertChildenData(tmp);
			
		}
		
		
		
	}
	
	public int insertOneData(CollectMemberManage cmm){
		Connection conn=MysqlUtil.getConnection();
		String sql="INSERT INTO CollectMemberManage(UserId,UserName,UserChildId,Address,Integral,CreateTime,UpdateTime,Detail) "+
				"VALUES(?,?,?,?,?,?,?,?);";
		
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
			ptmt.execute();
			
		} catch (SQLException e) {
			LogPrintFormat.logPrint("Lyn", "CollectMemberManageDao类使用insertOneData方法执行失败，id"+cmm.getUserId()+"添加失败。");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		} finally{
			ptmt=null;
			conn=null;
		}
		
		
		LogPrintFormat.logPrint("Lyn", "CollectMemberManageDao类使用insertOneData方法执行成功，id"+cmm.getUserId()+"添加成功。");
		return 0;
		
	}
}
