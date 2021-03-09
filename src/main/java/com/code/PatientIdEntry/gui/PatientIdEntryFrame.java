package com.code.PatientIdEntry.gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.code.PatientIdEntry.backend.PatientIdEntry;
import com.code.utility.Helper;

public class PatientIdEntryFrame  extends JFrame implements ActionListener {
	public Boolean isView = true;
	Container container = getContentPane();
	JLabel idLabel = new JLabel("Patient Id: ");
	JTextField idField = new JTextField();
	JButton confirmBtn = new JButton("Confirm");
	public PatientIdEntryFrame() {
		initialSetup();
		addComponentsToContainer();
		addActionEvents();
	}
	
	public void initialSetup() {
		setTitle("Enter patient Id");
		setVisible(true);
		setSize(new Dimension(250, 125));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		container.setLayout(new BorderLayout());
	}
	
	public void addComponentsToContainer() {
		container.add(setupPanel());
	}

	public void addActionEvents() {
		confirmBtn.addActionListener(this);
	}
	
	public Component setupPanel() {
		GridBagLayout grid = new GridBagLayout();
		JPanel p = new JPanel(grid);
		Insets inset = new Insets(5, 10, 5, 10); 
		Helper.addCompenenttoGrid(p, idLabel, 0, 1, 1, 1, 1, 1, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, idField, 1, 1, 1, 1, 1, 1, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, confirmBtn, 0, 2, 1, 2, 2, 1, inset, GridBagConstraints.CENTER);
		return p;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==confirmBtn) {
			String result = idField.getText();
			if(!result.isBlank()) {
				PatientIdEntry pId = new PatientIdEntry();
				pId.uPat.emailId = result;
				pId.actionAttribute = (isView) ? "a:view" : "a:edit";
				pId.processRequest();
			} else {
				Helper.showWarningBox(this, "Empty username or password", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public static void main(String[] args) {
		new PatientIdEntryFrame();
	}
}
	