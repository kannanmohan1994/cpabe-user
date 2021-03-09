package com.code.viewAllUsers.gui;

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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.code.userobjects.UserDoctor;
import com.code.userobjects.UserPatient;
import com.code.utility.DatabaseFetch;
import com.code.utility.Helper;
import com.code.viewAllUsers.backend.ViewAllUsers;

public class ViewAllUsersFrame extends JFrame {
	String userTypeString[] = { "Doctor", "Patient" };
	ViewAllUsers viewAll = new ViewAllUsers();
	PatientTableModel ptm = new PatientTableModel(viewAll.patientList);
	Container container = getContentPane();
	JTable table = new JTable();
	
	
	public ViewAllUsersFrame() {
		initialSetup();
		addComponentsToContainer();
	}

	public void initialSetup() {
		setTitle("View All Users");
		setVisible(true);
		setSize(new Dimension(320, 240));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		container.setLayout(new BorderLayout());
	}

	public void addComponentsToContainer() {
		container.add(setupTable());
		this.pack();
		setLocationRelativeTo(null);
	}
	
	public JScrollPane setupTable() {
		viewAll.fetchAllPatients();
        table.setModel(ptm);
        return new JScrollPane(table);
	}
	
	public static void main(String[] a) {
		ViewAllUsersFrame viewAllUsers = new ViewAllUsersFrame();
	}

}
