package com.jaxrs.rest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SingletonConcurrentHashMap {

	private static ConcurrentMap<String,Integer> concurrenthashMapInstance =  new ConcurrentHashMap<String, Integer>();

	private SingletonConcurrentHashMap(){ }
	
	private static SingletonConcurrentHashMap  singletonConcurrentHashMapObj = new SingletonConcurrentHashMap();

	public static SingletonConcurrentHashMap getInstance( ) {
	      return singletonConcurrentHashMapObj;
	}
	
	public void setKeyValue(String key, Integer value)
	{
		concurrenthashMapInstance.put(key, value);
	}
	
	public Integer getValue(String key)
	{
		return concurrenthashMapInstance.get(key);
	}
}
