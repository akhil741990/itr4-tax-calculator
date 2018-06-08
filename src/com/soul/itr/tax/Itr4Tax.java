package com.soul.itr.tax;

import java.util.Map;

public interface Itr4Tax {
	public Map<String,Float> getSlab();
	public Double calculateTax();
}
