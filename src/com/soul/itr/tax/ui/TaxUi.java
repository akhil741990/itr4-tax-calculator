package com.soul.itr.tax.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.soul.itr.tax.DefaultItr4TaxCalculator;
import com.soul.itr.tax.utils.Form16Constants;


public class TaxUi {
	public static void main(String args[]){
		
		
		
		JFrame f=new JFrame();//creating instance of JFrame  
		
				
		Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

		
		JLabel lBasicSal = new JLabel("Annual Basic Salary");
		lBasicSal.setBounds(30, 100, 150, 40);
		JTextArea tBasicSal = new JTextArea();   
	    tBasicSal.setBounds(160, 100, 100, 40);
	    tBasicSal.setBorder(border);
		
		JLabel lHra = new JLabel("HRA");
		lHra.setBounds(30, 160, 150, 40);
		JTextArea tHra = new JTextArea();
		tHra.setBounds(160, 160, 100, 40);
		tHra.setBorder(border);
		
		JLabel lConveyanceAllowance = new JLabel("Conveyance Allowance");
		lConveyanceAllowance.setBounds(30, 220, 150, 40);
		JTextArea tConveyanceAllowance = new JTextArea();
		tConveyanceAllowance.setBounds(160, 220, 100, 40);
		tConveyanceAllowance.setBorder(border);
		
		JLabel lTotalIncome = new JLabel("Total Income");
		lTotalIncome.setBounds(30, 280, 150, 40);
		JTextArea tTotalIncome = new JTextArea();
		tTotalIncome.setBounds(160, 280, 100, 40);
		tTotalIncome.setBorder(border);
		
		
		JLabel l80cPpf = new JLabel("80c PPF");
		l80cPpf.setBounds(30, 340, 150, 40);
		JTextArea t80cPpf = new JTextArea();
		t80cPpf.setBounds(160, 340, 100, 40);
		t80cPpf.setBorder(border);
		
		
		JLabel l80cPf = new JLabel("80c PF");
		l80cPf.setBounds(30, 400, 150, 40);
		JTextArea t80cPf = new JTextArea();
		t80cPf.setBounds(160, 400, 100, 40);
		t80cPf.setBorder(border);
		
		
		JLabel l80cEduLoan = new JLabel("80c Edu Loan");
		l80cEduLoan.setBounds(30, 460, 150, 40);
		JTextArea t80cEduLoan = new JTextArea();
		t80cEduLoan.setBounds(160, 460, 100, 40);
		t80cEduLoan.setBorder(border);
		
		JLabel l80cLic = new JLabel("80c LIC");
		l80cLic.setBounds(30, 520, 150, 40);
		JTextArea t80cLic = new JTextArea();
		t80cLic.setBounds(160, 520, 100, 40);
		t80cLic.setBorder(border);
		
		
		JLabel lMonthlyRent = new JLabel("Monthly rent");
		lMonthlyRent.setBounds(30, 580, 150, 40);
		JTextArea tMonthlyRent = new JTextArea();
		tMonthlyRent.setBounds(160, 580, 100, 40);
		tMonthlyRent.setBorder(border);
		
		
		
		
		JButton b=new JButton("Compute Tax");//creating instance of JButton  
		b.setBounds(350,620,200, 40);//x axis, y axis, width, height  
		
		
		
		
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					//TODO @Ashutosh : Add Controller that will receive the allowance, investments 
					// all other form 16 parameters
					
					
					
					// TODO @Ashutosh : Try to apply as much as MVC u know,
					
					DefaultItr4TaxCalculator cal = new DefaultItr4TaxCalculator();
					Map<Form16Constants, List<Double>> investments = new HashMap<>();
					//t80cEduLoan.getText()
					investments.put(Form16Constants.EDU_LOAN_80C, Arrays.asList( Double.parseDouble(t80cEduLoan.getText())));
					investments.put(Form16Constants.PPF_80C,Arrays.asList( Double.parseDouble(t80cPpf.getText())));
					investments.put(Form16Constants.LIC_80C, Arrays.asList( Double.parseDouble(t80cLic.getText())));
					investments.put(Form16Constants.PF_80C, Arrays.asList( Double.parseDouble(t80cPf.getText())));
					cal.set80cInvestments(investments);
					
					Map<Form16Constants, List<Double>> allowances = new HashMap<>();
					
					allowances.put(Form16Constants.HRA, Arrays.asList(Double.parseDouble(tHra.getText())));
					allowances.put(Form16Constants.CONVEYANCE_ALLOWANCE, Arrays.asList(Double.parseDouble(tConveyanceAllowance.getText())));
					cal.setBasicSalary(Double.parseDouble(tBasicSal.getText()));
					cal.setTotalIncome(Double.parseDouble(tTotalIncome.getText()));
					cal.setAllowances(allowances);
					
					// Add Monthly rent
					cal.setMonthlyRent(Double.parseDouble(tMonthlyRent.getText()));
					
					
					cal.calculateTax();
					
					// TODO : Think of a way to display the Computed Tax
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
//				JOptionPane.showMessageDialog("hello",
//					    "WARNING.",
//					    "Warning",
//					    JOptionPane.WARNING_MESSAGE);
				clearAllFields();
			}
			
			private void clearAllFields(){
				tHra.setText("");
				tConveyanceAllowance.setText("");
				t80cPpf.setText("");
			}
		});
		
		
		//TODO Need to refactor, as the code is cluttered
		
		
		f.add(lBasicSal);
		f.add(lHra);
		f.add(lConveyanceAllowance);
		f.add(l80cPpf);
		f.add(lTotalIncome);
		f.add(l80cPf);
		f.add(l80cEduLoan);
		f.add(l80cLic);
		f.add(lMonthlyRent);
		
		f.add(tBasicSal);
		f.add(tHra);
		f.add(tConveyanceAllowance);
		f.add(t80cPpf);
		f.add(tTotalIncome);
		f.add(t80cPf);
		f.add(t80cEduLoan);
		f.add(t80cLic);
		f.add(tMonthlyRent);
		
		
		f.add(b);//adding button in JFrame
		f.setSize(1000,1500);//400 width and 500 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
	
		
	}
}
