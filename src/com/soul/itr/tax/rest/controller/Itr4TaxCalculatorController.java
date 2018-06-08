package com.soul.itr.tax.rest.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soul.itr.tax.DefaultItr4TaxCalculator;

@RestController
public class Itr4TaxCalculatorController {
	public static DefaultItr4TaxCalculator cal;
		static {
			 try {
				cal = new DefaultItr4TaxCalculator();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		@RequestMapping("/getTaxSlab")
		public Map<String,Float> getTaxSlab() {
			return cal.getSlab();
		}
}
