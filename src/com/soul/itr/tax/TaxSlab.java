package com.soul.itr.tax;

public class TaxSlab implements Comparable<TaxSlab>{

	private Float lowerLimit;
	private Float upperLimit;
	private Float perentage;
	
	
	public TaxSlab() {
		// TODO Auto-generated constructor stub
	}
	
	
	public TaxSlab(String range, Float percentage){
		this.perentage = percentage;
		parseRange(range);
	}
	
	// TODO : Add check upperLimit is not less than or equal to lowerLimit
	
	public Float getLowerLimit() {
		return lowerLimit;
	}
	
	public Float getUpperLimit() {
		return upperLimit;
	}
	
	public Float getPerentage() {
		return perentage;
	}
	
	@Override
	public int compareTo(TaxSlab o) {
		// TODO Auto-generated method stub
		return o.lowerLimit.compareTo(this.lowerLimit);
	}
	
	
	private void parseRange(String range) {
		String limits[];
		if(range.contains("-")) {
			limits = range.split("-");
			lowerLimit = Float.parseFloat(limits[0].trim());
			upperLimit = Float.parseFloat(limits[1].trim());
			if (lowerLimit > upperLimit) {
				throw new IllegalArgumentException(String.format("Lower Limt(%s) is greater than uperLimit(%s)", lowerLimit, upperLimit));
			}
		}else if(range.contains("+")) {
			limits = range.split("\\+");
			lowerLimit = Float.parseFloat(limits[0].trim());
			upperLimit = Float.MAX_VALUE;
		}
	}
	
}
