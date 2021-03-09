package com.code.viewAllUsers.gui;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.code.userobjects.UserPatient;
import com.code.utility.DatabaseFetch;

public class PatientTableModel extends AbstractTableModel {

	public Vector<UserPatient> users;
	public Vector<String> columnNames;

	public PatientTableModel(Vector<UserPatient> users) {
		this.users = users;
	}

	public int getRowCount() {
		return users.size();
	}

	public int getColumnCount() {
		columnNames = DatabaseFetch.fetchColumnNames("patient");
		return 4;
	}
	
	public String getColumnName(int column) {
		return columnNames.get(column);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = "??";
		UserPatient user = users.get(rowIndex);
		switch (columnIndex) {
		case 0:
			value = user.emailId;
			break;
		case 1:
			value = user.name;
			break;
		case 2:
			value = user.special;
			break;
		case 3:
			value = user.ward;
			break;
		case 4:
			value = user.password;
			break;
		default:
			break;
		}
		return value;
	}

	public UserPatient getUserAt(int row) {
		return users.get(row);
	}

}