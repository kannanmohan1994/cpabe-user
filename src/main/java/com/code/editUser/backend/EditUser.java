package com.code.editUser.backend;

import java.sql.Connection;
import java.sql.Statement;

import com.code.userobjects.UserPatient;
import com.code.utility.Connect;

public class EditUser {
	public static Boolean editPatient(UserPatient uPat) {
		Connection conn = Connect.startConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "UPDATE PATIENT set special = '"+uPat.special+"', ward = '"+uPat.ward+"', diagnosis = '"
					+uPat.diagnosis+"', treatment = '"+uPat.treatmentProtocol+"' where emailid = '"
					+uPat.emailId+"';";
			return (stmt.executeUpdate(query))>0;
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			Connect.endConnection(conn);
		}
		return false;
	}
}
