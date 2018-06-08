package com.soul.itr.tax.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.soul.itr.tax.TaxSlab;
import com.soul.itr.tax.utils.TaxSlabUtils;

public class TaxSlabTest {

	
	private Map<String, Float> slab;
	
	@Before
	public void init() throws IOException{
		slab = new HashMap<>();
		InputStream is = TaxSlabTest.class.getClassLoader().getResourceAsStream("tax.properties");
		Properties props = new Properties();
        props.load(is);
        
        for( Object key: props.keySet()) {
        	  System.out.println(key.toString() +":"+ props.getProperty(key.toString()));
        	  slab.put(key.toString(), Float.parseFloat(props.getProperty(key.toString()).trim()));
        }
	}
	
	@Test
	public void getSlabListTest(){
	
		List<TaxSlab> taxSlabList = TaxSlabUtils.getTaxSlabs(slab);
		assertNotNull(taxSlabList);
		Collections.sort(taxSlabList);
		System.out.println("getSlabListTest Test Passed");
	
	}
	
	@Test
	public void calTaxTest(){
		List<TaxSlab> taxSlabList = TaxSlabUtils.getTaxSlabs(slab);
		Collections.sort(taxSlabList);  // sort descending
		Double taxExpected = 181500.0;
		Double totalIncome = 1650000d;
		Double tax = TaxSlabUtils.calTax(totalIncome, taxSlabList);
		assertEquals(taxExpected,tax,0.00);
		System.out.println("calTaxTest Passed");
	
	}

	
}
