package com.soul.itr.tax.test;

import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class SlabConfigTest {

	public static void main(String args[]) {
		
		
		try {
			InputStream is = SlabConfigTest.class.getClassLoader().getResourceAsStream("tax.properties");
			Properties props = new Properties();
	        props.load(is);
	        
	        for( Object key: props.keySet()) {
	        	  System.out.println(key.toString() +":"+ props.getProperty(key.toString()));
	        }
	        
	        
	      
	        
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
