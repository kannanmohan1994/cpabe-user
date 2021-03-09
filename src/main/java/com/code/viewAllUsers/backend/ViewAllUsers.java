package com.code.viewAllUsers.backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.code.userobjects.UserDoctor;
import com.code.userobjects.UserPatient;
import com.code.utility.Connect;

public class ViewAllUsers {
	public Vector<UserDoctor> doctorList = new Vector<UserDoctor>();
	public Vector<UserPatient> patientList = new Vector<UserPatient>();

	public ViewAllUsers() {

	}
	
	public void fetchAllDoctors() {
		Connection conn = Connect.startConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM doctor;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				UserDoctor temp = new UserDoctor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				doctorList.add(temp);
			}
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			Connect.endConnection(conn);
		}
	}

	public void fetchAllPatients() {
		Connection conn = Connect.startConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM patient;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				UserPatient temp = new UserPatient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				patientList.add(temp);
			}
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			Connect.endConnection(conn);
		}
	}
}
