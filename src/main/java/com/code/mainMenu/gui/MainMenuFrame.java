package com.code.mainMenu.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.code.PatientIdEntry.gui.PatientIdEntryFrame;
import com.code.utility.Helper;
import com.code.utility.StaticElements;
import com.code.viewAllUsers.gui.ViewAllUsersFrame;

public class MainMenuFrame extends JFrame implements ActionListener {
	
	Container container = getContentPane();
	JMenuBar menuBar = new JMenuBar();  
	JMenu userMgmtMenu = new JMenu("Patient Mgmt");
	JMenu helpMenu = new JMenu("Help");
	JMenuItem viewUser = new JMenuItem("View patient detail");
	JMenuItem editUser = new JMenuItem("Edit patient detail");
	JMenuItem viewAllUser = new JMenuItem("View patients list");
	JMenuItem about = new JMenuItem("About");
	
	public MainMenuFrame() {
		initialSetup();
		addComponentsToContainer();
		addActionEvents();
	}

	public void initialSetup() {
		setTitle("User App");
		setVisible(true);
		setSize(new Dimension(320, 240));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		container.setLayout(new BorderLayout());
	}

	public void addComponentsToContainer() {
		userMgmtMenu.add(viewUser);
		if(StaticElements.Patientmail.isBlank()) {
			userMgmtMenu.add(editUser);
			userMgmtMenu.add(viewAllUser);
		}
		helpMenu.add(about);
		menuBar.add(userMgmtMenu);
		menuBar.add(helpMenu);
		this.setJMenuBar(menuBar);
	}

	public void addActionEvents() {
		viewUser.addActionListener(this);
		if(StaticElements.Patientmail.isBlank()) {
			editUser.addActionListener(this);
			viewAllUser.addActionListener(this);
		}
		about.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == viewUser) {
			PatientIdEntryFrame frame = new PatientIdEntryFrame();
			frame.isView = true;
		} else if(e.getSource() == editUser) {
			PatientIdEntryFrame frame = new PatientIdEntryFrame();
			frame.isView = false;
		} else if(e.getSource() == viewAllUser) {
			ViewAllUsersFrame frame = new ViewAllUsersFrame();
		} else if(e.getSource() == about) {
			
		}
	}

	public static void main(String[] args) {
		MainMenuFrame mm = new MainMenuFrame();
	}
}
