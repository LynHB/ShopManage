package Lyn.ShopManage.util;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;


public class ReadConfigFileUtil {
	public static HashMap<String,HashMap<String,String>> hmConfig = new HashMap();
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public void readConfigFile(File file) throws Exception{
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		String temp="";
		String content;
		String key;
		String value;
		int flag=0;
		while(true){
			if(flag==0||temp==null){	
				if((temp=raf.readLine())!=null){
					flag=1;
				}else{
					break;
				}
			}
			if(temp.indexOf("[")==0){
				String title="";
				title=temp.substring(temp.indexOf("[")+1,temp.lastIndexOf("]"));
				HashMap<String,String>hm = new HashMap();
				while((temp=raf.readLine())!=null && temp.indexOf("[")==-1){
					if(!temp.trim().equals("")){
						key=temp.substring(0,temp.indexOf("=")).trim();
						value=temp.substring(temp.indexOf("=")+1,temp.length()).trim();
						hm.put(key,value);
					}
				}
				hmConfig.put(title,hm);
			}
		}
		raf.close();
		//System.out.println("Lyn["+sdf.format(new Date())+"] "+hmConfig);
		System.out.println("Lyn["+sdf.format(new Date())+"] "+"执行"+this.getClass().getName()+"获取到配置文件信息成功");
	}
	
}
