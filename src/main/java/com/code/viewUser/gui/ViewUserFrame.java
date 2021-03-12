package com.code.viewUser.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.code.userobjects.UserPatient;
import com.code.utility.DatabaseFetch;
import com.code.utility.Helper;
import com.code.utility.JSONHelper;

public class ViewUserFrame extends JFrame {
	Container container = getContentPane();
	JLabel emailLabel = new JLabel("Email id: ");
	JLabel nameLabel = new JLabel("Name: ");
	JLabel specialLabel = new JLabel("Speciality: ");
	JLabel wardLabel = new JLabel("Ward No: ");
	JLabel diagnosisLabel = new JLabel("Diagnosis: ");
	JLabel treatmentLabel = new JLabel("Treatment: ");
	JTextField emailTextField = new JTextField();
	JTextField nameTextField = new JTextField();
	JTextField specialField = new JTextField();
	JTextField wardField = new JTextField();
	JTextArea diagnosisTextField = new JTextArea();
	JTextArea treatmentTextField = new JTextArea();

	public ViewUserFrame() {
		setupComponents();
		setComponentValues();
		initialSetup();
		addComponentsToContainer();
	}

	public void initialSetup() {
		setTitle("View user");
		setVisible(true);
		setSize(new Dimension(320, 300));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		BorderLayout borderLayout = new BorderLayout();
		container.setLayout(borderLayout);
	}

	public void setComponentValues() {
		UserPatient up = JSONHelper.fetchPatientDetailsfromJSON();
		nameTextField.setText(up.name);
		emailTextField.setText(up.emailId);
		specialField.setText(up.special);
		wardField.setText(up.ward);
		diagnosisTextField.setText(up.diagnosis);
		treatmentTextField.setText(up.treatmentProtocol);
	}

	public void setupComponents() {
		nameTextField.setEditable(false);
		emailTextField.setEditable(false);
		specialField.setEditable(false);
		wardField.setEditable(false);
		treatmentTextField.setEditable(false);
		diagnosisTextField.setEditable(false);
		diagnosisTextField.setLineWrap(true);
		treatmentTextField.setLineWrap(true);
	}

	public void addComponentsToContainer() {
		container.add(setupPanel(), BorderLayout.NORTH);
	}

	public Component setupPanel() {
		GridBagLayout grid = new GridBagLayout();
		JPanel p = new JPanel(grid);
		Insets inset = new Insets(8, 10, 8, 20);
		Helper.addCompenenttoGrid(p, nameLabel, 0, 0, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, nameTextField, 1, 0, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, specialLabel, 0, 1, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, specialField, 1, 1, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, wardLabel, 0, 2, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, wardField, 1, 2, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, emailLabel, 0, 3, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, emailTextField, 1, 3, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, diagnosisLabel, 0, 4, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, diagnosisTextField, 1, 4, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, treatmentLabel, 0, 5, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, treatmentTextField, 1, 5, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		return new JScrollPane(p);
	}

	public static void main(String[] a) {
		ViewUserFrame frame = new ViewUserFrame();
	}
}
