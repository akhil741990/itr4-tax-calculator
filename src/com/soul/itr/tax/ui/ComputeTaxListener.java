package com.soul.itr.tax.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class ComputeTaxListener implements ActionListener{
	
	private JTextField tSal;
	
	public ComputeTaxListener(JTextField tSal) {
		this.tSal = tSal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action performed : " + tSal.getText());
		
	}

}
