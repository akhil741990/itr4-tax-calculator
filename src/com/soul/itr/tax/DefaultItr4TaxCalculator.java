package com.soul.itr.tax;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import com.soul.itr.tax.test.SlabConfigTest;
import com.soul.itr.tax.utils.Form16Constants;
import com.soul.itr.tax.utils.TaxComputationUtils;
import com.soul.itr.tax.utils.TaxSlabUtils;

public class DefaultItr4TaxCalculator implements Itr4Tax{

	
	
	private Map<String,Float> slab;
	private Map<Form16Constants,List<Double>> form16;
	boolean hasMultipleForm16;

	
	//TODO add metro/non-metro calculations
	
	public DefaultItr4TaxCalculator() throws IOException {
		
		form16 = new HashMap<>();
		hasMultipleForm16 = false;
		setSlabFromConfig();
	}
	
	
	public DefaultItr4TaxCalculator(Map<String, Float> slab) throws IOException {
		
		form16 = new HashMap<>();
		hasMultipleForm16 = false;
		this.slab = slab;
	}
	
	
	private void setSlabFromConfig() throws IOException {
		
		slab = new HashMap<>();
		InputStream is = SlabConfigTest.class.getClassLoader().getResourceAsStream("tax.properties");
		Properties props = new Properties();
        props.load(is);
        
        for( Object key: props.keySet()) {
        	  System.out.println(key.toString() +":"+ props.getProperty(key.toString()));
        	  slab.put(key.toString(), Float.parseFloat(props.getProperty(key.toString()).trim()));
        }
	}
	
	
	@Override
	public Map<String, Float> getSlab() {

		if(slab == null) {
			try {
				setSlabFromConfig();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return slab;
	}

	@Override
	public Double calculateTax() {
		
		
		// Add check if all the required parameters are present in form16Map
		
		
		// Add all total income  <----------------A
		Double totalIncome = addAllInList(Form16Constants.TOTAL_INCOME);
		
		
		// Add all the allowances<----------------B
		Double totalAllowances = addAllAllowances(); 
		
		// Add all investments   <----------------C
		Double total80cInvestments = addAll80cInvestments();
		
		
		if(total80cInvestments > 150000) {
			
			System.out.println("Investements more than upper limit, hence setting total80cInvestments as upper limit");
			total80cInvestments = 150000d;
		}
		
		//TODO Currently home loan interest is not considered as we need to add a check if rented house & home are in different city
		
		
		// Add all Tax Deducted 
		Double totalTaxDeducted = addAllInList(Form16Constants.TAX_DEDUCTED);
		
		
		Double taxableIncome = totalIncome - (totalAllowances + total80cInvestments);
		
		
		Map<String, Float> slab = getSlab();
		
		List<Double> totalTax = null;
		
		List<TaxSlab> taxSlabList = TaxSlabUtils.getTaxSlabs(slab);
		
		Collections.sort(taxSlabList);
		
		totalTax  = Arrays.asList(TaxSlabUtils.calTax(taxableIncome, taxSlabList));
		
		
		
		form16.put(Form16Constants.TOTAL_TAX,totalTax);		
		return totalTax.get(0);
	}
	
	public boolean isHasMultipleForm16() {
		return hasMultipleForm16;
	}


	public void setHasMultipleForm16(boolean hasMultipleForm16) {
		this.hasMultipleForm16 = hasMultipleForm16;
	}


	public Map<Form16Constants, List<Double>> getForm16() {
		return form16;
	}

	public void setSlab(Map<String, Float> slab) {
		this.slab = slab;
	}
	
	
	public void set80cInvestments(Map<Form16Constants,List<Double>> investments) {
		addToForm16(investments);	
	}
	
	
	public void setAllowances(Map<Form16Constants, List<Double>> allowances) {		
		addToForm16(allowances);
	}
	
	private void addToForm16(Map<Form16Constants, List<Double>> subMap) {
		if(hasMultipleForm16) {
			
			for(Entry<Form16Constants, List<Double>> entry : subMap.entrySet()){
				
				List<Double> value = form16.get(entry.getKey());
				if(value == null){
					form16.put(entry.getKey(), new ArrayList<>());
				}

				form16.get(entry.getKey()).addAll(entry.getValue());
			}
		}else {
			this.form16.putAll(subMap);
		}
	}
	
	public void setTotalIncome(Double totalIncome) {
		
		addEntityToForm16(Form16Constants.TOTAL_INCOME,totalIncome);
	}
	
	public void setTaxableIncome(Double taxableIncome) {
		
		addEntityToForm16(Form16Constants.TAXABLE_INCOME,taxableIncome);
	
	}
	
	private void addEntityToForm16(Form16Constants entityKey, Double entityValue) {
		List<Double> entyityValueList = form16.get(entityKey);
		if(hasMultipleForm16) {
			entyityValueList.add(entityValue);
		}else {
			if(entyityValueList == null) {
				entyityValueList = new ArrayList<>();
				entyityValueList.add(entityValue);
				form16.put(entityKey, entyityValueList);
			}else {
				// updating the total income 
				entyityValueList.set(0,entityValue);
			}
		}
	}
	
	
	public void setBasicSalary(Double basicSalary) {
		addEntityToForm16(Form16Constants.BASIC_SALARY, basicSalary);
	}
	

	public void setMonthlyRent(Double monthlyRent) {
		addEntityToForm16(Form16Constants.MONTHLY_RENT, monthlyRent);
	}
	
	public void setTaxDeducted(Double taxDeducted) {
		addEntityToForm16(Form16Constants.TAX_DEDUCTED, taxDeducted);
	}
	
	private Double addAllInList(Form16Constants key) {
		List<Double> value = form16.get(key);
		Double total = 0d;;
		if(value == null) {
		    System.out.println("List is null");
		}else {
			for(Double listElement : value) {
				total = total + listElement;
			}
		}
		return total;
	}
	
	
	private Double addAll80cInvestments() {
		
		// TODO : Add PF
		Double total80Investments =  addAllInList(Form16Constants.EDU_LOAN_80C) +
				addAllInList(Form16Constants.HOME_LOAN_PRINCIPLE_80C) +
				addAllInList(Form16Constants.HOME_STAMP_DUTY_80C)+
				addAllInList(Form16Constants.LIC_80C)+
				addAllInList(Form16Constants.PPF_80C);
		return total80Investments;
	}
	
	
	private Double addAllAllowances() {
		Double totalAllowances = 0d;
		
		// Compute HRA Exemption <-----------------B
		Double hraExemption = TaxComputationUtils.computeHraExcemption(form16.get(Form16Constants.BASIC_SALARY).get(0)/12,   //Assumption HRA will be is single value
					form16.get(Form16Constants.HRA).get(0), form16.get(Form16Constants.MONTHLY_RENT).get(0), false);
		
		totalAllowances = hraExemption + addAllInList(Form16Constants.CONVEYANCE_ALLOWANCE) + addAllInList(Form16Constants.MEDICAL_ALLOWANCE);
		
		return totalAllowances;
	}

}
