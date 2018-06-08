package com.soul.itr.tax.utils;

public class TaxComputationUtils {

	
	
	/*
	 * 
	 * Example: Suppose Mr Pankaj lives in Mumbai and earns a basic salary of Rs. 40,000 per month. The HRA component of his salary is Rs. 20,000 but the actual rent paid by him is Rs. 12,000. How much exemption can he get?

		Solution: To solve this problem, first let us look at the factors affecting HRA calculation.
		
		Actual HRA received is (Rs. 20,000 x 12) = Rs. 2,40,000
		
		Actual rent paid (Rs. 12,000 x 12) – 10% of salary [(Rs. 40,000 x 12) x 10%] = Rs. 96,000
		
		50% of basic salary [(Rs. 40,000 x 12) x 50%] = Rs. 2,40,000
		
		Rs. 96,000 is the least among the above obtained figures so Mr Pankaj can get Rs. 96,000 exempt.
		
		
		
		Example: I stay in a rented apartment in Hyderabad, and my basic salary is Rs. 20,000. The total rent that I pay is Rs. 7,000 and my actual HRA is Rs. 10,000. How much tax can be exempted from this?
		
		Solution: Since you live in a non-metro city, you will be allowed 40% of basic salary as HRA. To solve this problem, first, let us look at the factors affecting HRA calculation.
		
		Actual HRA received is (Rs. 10,000 x 12) = Rs. 1,20,000
		
		Actual rent paid (Rs. 7,000 x 12) – 10% of salary [(Rs. 20,000 x 12) x 10%] = Rs. 60,000
		
		You will receive 40% of basic salary [(Rs. 20,000 x 12) x 40%] = Rs. 96,000
		
		Rs. 60,000 is the least among the above obtained figures so you can get Rs. 60,000 exempt.
	 * 
	 * 
	 */
	
	
	public static Double computeHraExcemption(Double montlyBasicSalary, Double hra, Double monthlyRent, boolean isMetroCity) {
			
			Double hraExempton;
			Double annualRent = monthlyRent *12;
			Double excessRentPaid = annualRent - (montlyBasicSalary * 12 * 0.1);
			if(isMetroCity) {
				Double fifytPerCentOfBasic = montlyBasicSalary * 12 * 0.5;
				if( fifytPerCentOfBasic < excessRentPaid) {
					hraExempton = fifytPerCentOfBasic;
				}else {
					hraExempton = excessRentPaid;
				}
			}else {
				Double fortyPercentOfBasic = montlyBasicSalary * 12 * 0.4;
				if(fortyPercentOfBasic < excessRentPaid) {
					hraExempton = fortyPercentOfBasic;
				}else {
					hraExempton = excessRentPaid;
				}
			}
			return hraExempton;
		}
}
