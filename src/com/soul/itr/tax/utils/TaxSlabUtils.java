package com.soul.itr.tax.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.soul.itr.tax.TaxSlab;

public class TaxSlabUtils {

	
	public static List<TaxSlab> getTaxSlabs(Map<String, Float> slabMap){
		List<TaxSlab> slabs = new ArrayList<>();
		
		for(Entry<String, Float> slab : slabMap.entrySet()) {
			slab.getKey();
			slab.getValue();
			slabs.add(new TaxSlab(slab.getKey(),slab.getValue()));
		}
		return slabs;
	}
	
	
	
	public static Double calTax(final Double totalIncome, List<TaxSlab> taxSlabList){
		
		Double tempTotalIncome = totalIncome;
		Double tax = 0d;
		for(TaxSlab taxSlab : taxSlabList){
			if(tempTotalIncome > taxSlab.getLowerLimit()){
				tax = tax + (tempTotalIncome - taxSlab.getLowerLimit()) * taxSlab.getPerentage() * 0.01;
				tempTotalIncome = taxSlab.getLowerLimit().doubleValue();
			}
		}
		
		return tax;
	}
}
