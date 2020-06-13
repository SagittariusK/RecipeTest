package com.recipe.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectUtils {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public HashMap<String, Object> toHashmapLower(HashMap<String, Object> hashmap){
		HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
		for (String mapkey : hashmap.keySet()) {
			if(!"".equals("" + hashmap.get(mapkey)) && null != hashmap.get(mapkey)) {
				returnHashMap.put(mapkey.toLowerCase(), hashmap.get(mapkey));
			}
		}
		return returnHashMap;
	}
	
	public HashMap<String, Object> JsonToHashMap(String jsonStream){
		HashMap<String, Object> jsonHashMap = new HashMap<String, Object>();
		try {
			jsonHashMap = new ObjectMapper().readValue(jsonStream, new TypeReference<HashMap<String, Object>>(){});
		} catch (Exception e) {
			logger.error("JsonStream to HashMap Error:");
			new CatchUtils(e);
		}
		return jsonHashMap;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> toHashMap(HashMap<String, Object> hashmap, String hashmapKey){
		return (HashMap<String, Object>) hashmap.get(hashmapKey);
	}
	
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> toListInHashMap(HashMap<String, Object> hashmap, String hashmapKey){
		return (List<HashMap<String, Object>>) hashmap.get(hashmapKey);
	}
	
	public String hashMapGetStr(HashMap<String, Object> hashmap, String hashmapKey) {
		return toStr(hashmap.get(hashmapKey));
	}
	
	public int toInt(String string) {
		return Integer.parseInt((null == string || "".equals(string)) ? "0" : string);
	}
	
	public int toInt(Object obj) {
		return Integer.parseInt((null == obj || "".equals(obj.toString())) ? "0" : obj.toString());
	}
	
	public String toStr(int integer) {
		return String.valueOf(integer);
	}
	
	public String toStr(String string) {
		return (null == string || "".equals(string)) ? "" : string;
	}
	
	public String toStr(Object obj) {
		return (null == obj || "".equals(obj.toString())) ? "" : obj.toString();
	}
	
	public Map<String, Object> toMap(Object obj){
		try {
			//Field[] fields = obj.getClass().getFields(); //private field는 나오지 않음.
			Field[] fields = obj.getClass().getDeclaredFields();
			Map<String, Object> returnMap = new HashMap<String, Object>();
			for(int i=0; i<=fields.length-1;i++){
				fields[i].setAccessible(true);
				returnMap.put(fields[i].getName(), fields[i].get(obj));
			}
			return returnMap;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public HashMap<String, Object> toHashMap(Object obj){
		try {
			//Field[] fields = obj.getClass().getFields(); //private field는 나오지 않음.
			Field[] fields = obj.getClass().getDeclaredFields();
			HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
			for(int i=0; i<=fields.length-1;i++){
				fields[i].setAccessible(true);
				returnHashMap.put(fields[i].getName(), fields[i].get(obj));
			}
			return returnHashMap;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object toObject(Map<String, Object> map, Object objClass){
		String keyAttribute = null;
		String setMethodString = "set";
		String methodString = null;
		Iterator<?> itr = map.keySet().iterator();
		while(itr.hasNext()){
			keyAttribute = (String) itr.next();
			methodString = setMethodString + keyAttribute.substring(0,1).toUpperCase() + keyAttribute.substring(1);
			try {
				Method[] methods = objClass.getClass().getDeclaredMethods();
				for(int i = 0; i <= methods.length-1; i++){
					if(methodString.equals(methods[i].getName())){
//						System.out.println("invoke: " + methodString);
						methods[i].invoke(objClass, map.get(keyAttribute));
					}
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return objClass;
	}
	
	public Object toObject(HashMap<String, Object> hashmap, Object objClass){
		String keyAttribute = null;
		String setMethodString = "set";
		String methodString = null;
		Iterator<?> itr = hashmap.keySet().iterator();
		while(itr.hasNext()){
			keyAttribute = (String) itr.next();
			methodString = setMethodString + keyAttribute.substring(0,1).toUpperCase() + keyAttribute.substring(1);
			try {
				Method[] methods = objClass.getClass().getDeclaredMethods();
				for(int i = 0; i <= methods.length-1; i++){
					if(methodString.equals(methods[i].getName())){
//						System.out.println("invoke: " + methodString);
						methods[i].invoke(objClass, hashmap.get(keyAttribute));
					}
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return objClass;
	}
	
	public boolean isEmpty(String string) {
		return (null == string || "".equals(string) || "null".equals(string) || "undefined".equals(string)) ? true : false;
	}
	
	public boolean isEmpty(int integer) {
		String string = toStr(integer);
		return (null == string || "".equals(string) || "null".equals(string) || "undefined".equals(string)) ? true : false;
	}
	
	public boolean isEmpty(HashMap<String, Object> hashmap) {
		return (null == hashmap || hashmap.isEmpty()) ? true : false;
	}
	
	public boolean isEmpty(List<?> list) {
		return (null == list || 0 == list.size() || list.isEmpty()) ? true : false;
	}
	
}
