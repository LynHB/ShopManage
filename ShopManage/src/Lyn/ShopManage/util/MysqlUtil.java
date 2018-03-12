package Lyn.ShopManage.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Lyn.ShopManage.dao.AccountDao;



public class MysqlUtil {
//	private static final String URL="jdbc:mysql://127.0.0.1:3306/IMOCC_TEST?autoReconnect=true&useUnicode=true&characterEncoding=utf8";
//	private static final String USER="root";
//	private static final String PASSWORD="123456";
	private static  String URL;
	private static  String USER;
	private static  String PASSWORD;
	private static Connection conn=null;
	
	
	static{
		try {
			File file = new File(MysqlUtil.class.getResource("/configure.conf").getPath());
			ReadConfigFileUtil rcf = new ReadConfigFileUtil();
			rcf.readConfigFile(file);
			URL="jdbc:mysql://"+rcf.hmConfig.get("mysql").get("ip")+":"+rcf.hmConfig.get("mysql").get("port")+"/"+rcf.hmConfig.get("mysql").get("database")+"?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false";
			USER=rcf.hmConfig.get("mysql").get("user");
			PASSWORD=rcf.hmConfig.get("mysql").get("password");
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public static Connection getConnection(){
		return conn;
	}
}
