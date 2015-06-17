package com.example.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcHashMap {
	public static void main(String[] args) {
		
		ConcurrentHashMap<String, String> concMap= new ConcurrentHashMap<String, String>();

		concMap.put("City", "Delhi");
		concMap.put("State", "Delhi");
		concMap.put("Province", "National Territory");
		concMap.put("Country","India");
		concMap.put("Pin","110053");
		
		Iterator iterator= concMap.entrySet().iterator();
		while(iterator.hasNext())
		{
			Map.Entry<String, String> obj= (Map.Entry<String, String>)iterator.next();
		}
		
		ArrayList<String> myArray = new ArrayList<String>();
		myArray.add("Delhi");
		myArray.add("Noida");
		myArray.add("Bangalore");
		
		System.out.println(myArray.get(2));
		
		System.out.println(10/3);
	}

}
