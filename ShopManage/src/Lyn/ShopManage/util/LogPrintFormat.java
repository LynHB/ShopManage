package Lyn.ShopManage.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogPrintFormat {
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void logPrint(String name,String content){
		System.out.println(name+"["+sdf.format(new Date())+"] "+content);
	}
}
