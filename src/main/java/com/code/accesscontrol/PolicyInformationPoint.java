package com.code.accesscontrol;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.code.utility.Connect;
import com.code.utility.StaticElements;

public class PolicyInformationPoint {

	public String getRequesterAttributes() {
		String requesterAttributes = "";
		Boolean isRequesterDoctor = (!StaticElements.Doctormail.isBlank());
		String requesterMail = (isRequesterDoctor) ? StaticElements.Doctormail : StaticElements.Patientmail;
		String requesterTable = (isRequesterDoctor) ? "doctor" : "patient";
		String attributePrefix = (isRequesterDoctor) ? "d:" : "p:";
		Connection conn = Connect.startConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "Select * from " + requesterTable + " where emailid ='" + requesterMail + "';";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				requesterAttributes += attributePrefix + rs.getString(1) + " ";
				requesterAttributes += attributePrefix + rs.getString(3) + " ";
				requesterAttributes += attributePrefix + rs.getString(4);
			}
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			Connect.endConnection(conn);
		}
		return requesterAttributes;
	}

	public String getResourceAttributes(String emailId) {
		String resourceAttributes = "";
		Connection conn = Connect.startConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "Select * from patient where emailid ='" + emailId + "';";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				resourceAttributes += "p:" + rs.getString(1) + " ";
				resourceAttributes += "p:" + rs.getString(3) + " ";
				resourceAttributes += "p:" + rs.getString(4);
			}
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			Connect.endConnection(conn);
		}
		return resourceAttributes;
	}
}
