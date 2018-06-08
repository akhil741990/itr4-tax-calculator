package com.soul.itr.tax.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.soul.itr.tax.utils.TaxComputationUtils;

public class HraExemptionCalculator {
	
	public static void main(String args[]) {

		JFrame f=new JFrame();//creating instance of JFrame  
		
				
		Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

		
		JLabel lBasicSal = new JLabel("Annual Basic Salary");
		lBasicSal.setBounds(30, 100, 150, 40);
		JTextArea tBasicSal = new JTextArea();   
	    tBasicSal.setBounds(200, 100, 100, 40);
	    tBasicSal.setBorder(border);
		
		JLabel lHra = new JLabel("HRA");
		lHra.setBounds(30, 160, 150, 40);
		JTextArea tHra = new JTextArea();
		tHra.setBounds(200, 160, 100, 40);
		tHra.setBorder(border);
		
		JLabel lMonthlyRent = new JLabel("Monthly rent");
		lMonthlyRent.setBounds(30, 220, 150, 40);
		JTextArea tMonthlyRent = new JTextArea();
		tMonthlyRent.setBounds(200, 220, 100, 40);
		tMonthlyRent.setBorder(border);
	
			
		
		JButton b=new JButton("Compute HRA Exemption");//creating instance of JButton  
		b.setBounds(100,280,200, 40);//x axis, y axis, width, height 
		
		
		
		f.add(lBasicSal);
		f.add(lHra);	
		f.add(lMonthlyRent);
		f.add(tBasicSal);
		f.add(tHra);
		f.add(tMonthlyRent);
		
		
		f.add(b);//adding button in JFrame
		f.setSize(400,600);//400 width and 500 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible
		
		
		
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Double hraExemption = TaxComputationUtils.computeHraExcemption(Double.parseDouble(tBasicSal.getText())/12, 
						Double.parseDouble(tHra.getText()), Double.parseDouble(tMonthlyRent.getText()), false);
				System.out.println("HRA Exempted "+ hraExemption);
				JOptionPane.showMessageDialog(f, "HRA Exempted: "+ hraExemption);
				tMonthlyRent.setText("");
				tBasicSal.setText("");
				tHra.setText("");
			}
		});
		
		
	}
}
