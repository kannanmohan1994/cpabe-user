package com.code.editUser.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.*;

import com.code.editUser.backend.EditUser;
import com.code.userobjects.UserDoctor;
import com.code.userobjects.UserPatient;
import com.code.utility.DatabaseFetch;
import com.code.utility.Helper;
import com.code.utility.JSONHelper;

public class EditUserFrame extends JFrame implements ActionListener {
	UserPatient up;
	String userTypeString[] = { "Doctor", "Patient" };
	Vector<String> specialTypeString = new Vector<String>();
	Vector<String> wardTypeString = new Vector<String>();
	Boolean isTypeDoctor = true;
	Container container = getContentPane();
	JLabel emailLabel = new JLabel("Email id: ");
	JLabel userTypeLabel = new JLabel("User Type: ");
	JLabel nameLabel = new JLabel("Name: ");
	JLabel specialLabel = new JLabel("Speciality: ");
	JLabel wardLabel = new JLabel("Ward No: ");
	JLabel diagnosisLabel = new JLabel("Diagnosis: ");
	JLabel treatmentLabel = new JLabel("Treatment: ");
	JTextField emailTextField = new JTextField();
	JTextField nameTextField = new JTextField();
	JTextArea diagnosisTextField = new JTextArea();
	JTextArea treatmentTextField = new JTextArea();
	JComboBox userTypeDropDown = new JComboBox(userTypeString);
	JComboBox specialDropDown = new JComboBox();
	JComboBox wardDropDown = new JComboBox();
	JButton updateBtn = new JButton("Update");

	public EditUserFrame() {
		setupComponents();
		setComponentValues();
		initialSetup();
		addComponentsToContainer();
		addActionListeners();
	}

	public void initialSetup() {
		setTitle("Edit user");
		setVisible(true);
		setSize(new Dimension(320, 340));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		BorderLayout borderLayout = new BorderLayout();
		container.setLayout(borderLayout);
	}

	public void setComponentValues() {
		up = JSONHelper.fetchPatientDetailsfromJSON();

		nameTextField.setText(up.name);
		emailTextField.setText(up.emailId);
		diagnosisTextField.setText(up.diagnosis);
		treatmentTextField.setText(up.treatmentProtocol);

		specialTypeString = DatabaseFetch.fetchSpecialDetails();
		wardTypeString = DatabaseFetch.fetchWardDetails(up.special);
		Helper.addFreshItemsComboBox(wardDropDown, wardTypeString);
		Helper.addFreshItemsComboBox(specialDropDown, specialTypeString);
		specialDropDown.setSelectedIndex(specialTypeString.indexOf(up.special));
		wardDropDown.setSelectedIndex(wardTypeString.indexOf(up.ward));
	}

	public void setupComponents() {
		nameTextField.setEditable(false);
		emailTextField.setEditable(false);
		diagnosisTextField.setLineWrap(true);
		treatmentTextField.setLineWrap(true);
	}

	public void addComponentsToContainer() {
		container.add(setupPanel(), BorderLayout.NORTH);
	}

	public void addActionListeners() {
		specialDropDown.addActionListener(this);
		updateBtn.addActionListener(this);
	}
	
	public Component setupPanel() {
		GridBagLayout grid = new GridBagLayout();
		JPanel p = new JPanel(grid);
		Insets inset = new Insets(8, 10, 8, 20);
		Helper.addCompenenttoGrid(p, userTypeLabel, 0, 0, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, userTypeDropDown, 1, 0, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, nameLabel, 0, 1, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, nameTextField, 1, 1, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, specialLabel, 0, 2, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, specialDropDown, 1, 2, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, wardLabel, 0, 3, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, wardDropDown, 1, 3, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, emailLabel, 0, 4, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, emailTextField, 1, 4, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, diagnosisLabel, 0, 5, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, diagnosisTextField, 1, 5, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, treatmentLabel, 0, 6, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, treatmentTextField, 1, 6, 1, 1, 1, 0, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, updateBtn, 0, 7, 2, 2, 1, 0, inset, GridBagConstraints.CENTER);
		return new JScrollPane(p);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == specialDropDown) {
			wardTypeString = DatabaseFetch.fetchWardDetails(String.valueOf(specialDropDown.getSelectedItem()));
			Helper.addFreshItemsComboBox(wardDropDown, wardTypeString);
			wardDropDown.setSelectedIndex(0);
		} else if (e.getSource() == updateBtn) {
			attemptUpdateUser();
		}
	}

	public void attemptUpdateUser() {
		String email = emailTextField.getText().trim();
		String userName = nameTextField.getText().trim();
		String special = specialTypeString.get(specialDropDown.getSelectedIndex());
		String ward = wardTypeString.get(wardDropDown.getSelectedIndex());
		String diagnosis = diagnosisTextField.getText();
		String treatmentProtocol = treatmentTextField.getText();
		UserPatient uPat = new UserPatient(email, userName, special, ward, 
				up.password, diagnosis, treatmentProtocol);
		if (EditUser.editPatient(uPat)) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			Helper.showWarningBox(this, "Patient updated successfully", JOptionPane.PLAIN_MESSAGE);
		} else {
			Helper.showWarningBox(this, "Update failed!", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] a) {
		EditUserFrame frame = new EditUserFrame();
	}
}