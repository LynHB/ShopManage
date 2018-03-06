package Lyn.ShopManage.util;

import java.util.ArrayList;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonSplitNull {
	public static void main(String[] args) {
		
	}
	public static JSONObject splitNull(JSONObject json){
		Iterator iterator = json.keys();
		String key="";
		String value="";
		ArrayList<String> array=new ArrayList<String>();
		while(iterator.hasNext()){
	        key = (String) iterator.next();
	        value = json.getString(key);
	        System.out.println(key+" "+value);
	        if(value.equals("")){
	        	//json.remove(key);
	        	array.add(key);
	        }else if(isJson(value)){
	        	
	        	
	        }else if(isJsonArray(value)){
	        	
	        }else{
	        	 
	        }
	       
		}
		 Iterator it1 = array.iterator();
	        while(it1.hasNext()){
	            json.remove(it1);
	        }
		return json;
		
	}
	
	public static JSONObject regularJsonSplitNull(JSONObject json){
		String str=json.toString();
		return JSONObject.fromObject(str.replaceAll("\"\\w+\":\"\"", "").replaceAll("\"\\w+\":\\[\\]", "").replaceAll(",,", ",").replaceAll(",}", "}"));
	}
	
	public static JSONArray regularJsonSplitNull(JSONArray json){
		String str=json.toString();
		return JSONArray.fromObject(str.replaceAll("\"\\w+\":\"\"", "").replaceAll("\"\\w+\":\\[\\]", "").replaceAll(",,+", ",").replaceAll(",}", "}"));
	}
	
	public static boolean isJson(String str){
		try{
			JSONObject json=JSONObject.fromObject(str);
			return true;
		}catch(Exception ex){
			
		}
		return false;
	}
	
	public static boolean isJsonArray(String str){
		try{
			JSONArray json=JSONArray.fromObject(str);
			return true;
		}catch(Exception ex){
			
		}
		return false;
	}
}
