package com.jaxrs.rest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SingletonConcurrentHashMap {

	private static Map<String,Integer> hashMapInstance;

	SingletonConcurrentHashMap(){ }

	/* Static 'instance' method */
	public static Map<String,Integer> getInstance( ) {
		if(hashMapInstance == null) {
			hashMapInstance =  new ConcurrentHashMap<String,Integer>();
	      }
	      return hashMapInstance;
	}
}
