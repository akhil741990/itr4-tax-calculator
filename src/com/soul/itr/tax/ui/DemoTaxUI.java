package com.soul.itr.tax.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *  Compact UI to take inputs from User
 *  1. label and textArea mapped 
 *  2. Tab support
 * 
 * 
 * @author @pmishra
 *
 */
public class DemoTaxUI {

	public static void main(String[] args) {

		final JFrame frame = new JFrame("Tax Calculator");

		JLabel lBasicSal = new JLabel("Annual Basic Salary");
		JTextField tBasicSal = new JTextField(20);
		lBasicSal.setLabelFor(tBasicSal);

		JLabel lHra = new JLabel("HRA");
		JTextField tHra = new JTextField(20);
		lHra.setLabelFor(tHra);

		JLabel lConveyanceAllowance = new JLabel("Conveyance Allowance");
		JTextField tConveyanceAllowance = new JTextField(20);
		lConveyanceAllowance.setLabelFor(tConveyanceAllowance);
		
		
		JLabel lTotalIncome = new JLabel("Total Income");
		JTextField tTotalIncome = new JTextField(20);
		lTotalIncome.setLabelFor(tTotalIncome);
		
		
		JLabel l80cPpf = new JLabel("80c PPF");
		JTextField t80cPpf = new JTextField(20);
		l80cPpf.setLabelFor(t80cPpf);
		
		JLabel l80cPf = new JLabel("80c PF");
		JTextField t80cPf = new JTextField(20);
		l80cPf.setLabelFor(t80cPf);
		
		JLabel l80cEduLoan = new JLabel("80c Edu Loan");
		JTextField t80cEduLoan = new JTextField(20);
		l80cEduLoan.setLabelFor(t80cEduLoan);
		
		JLabel l80cLic = new JLabel("80c LIC");
		JTextField t80cLic = new JTextField(20);
		l80cLic.setLabelFor(l80cLic);
		
		JLabel lTaxEmptyLabel = new JLabel("");
		JButton b=new JButton("Compute Tax");//creating instance of JButton  l80cLic
		lTaxEmptyLabel.setLabelFor(b);
		
		JPanel panel = new JPanel();
		panel.setLayout(new SpringLayout());

		panel.add(lBasicSal);
		panel.add(tBasicSal);
		panel.add(lHra);
		panel.add(tHra);
		panel.add(lConveyanceAllowance);
		panel.add(tConveyanceAllowance);
		panel.add(lTotalIncome);
		panel.add(tTotalIncome);
		panel.add(l80cPpf);
		panel.add(t80cPpf);
		panel.add(l80cPf);
		panel.add(t80cPf);
		panel.add(l80cEduLoan);
		panel.add(t80cEduLoan);
		panel.add(l80cLic);
		panel.add(t80cLic);
		panel.add(lTaxEmptyLabel);
		panel.add(b);
	
		
		
		b.addActionListener(new ComputeTaxListener(tBasicSal));
		
		//Add button click action listener and use different class to listen to event
		//call Calcuate computeTax and final return the results and comments
		
		SpringUtilities.makeCompactGrid(panel,
                9, 2,  //rows, cols
                6, 6,  //initX, initY
                6, 6); //xPad, yPad
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(panel);
        frame.setVisible(true);

	}
}