package com.code.PatientIdEntry.backend;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONObject;

import com.code.accesscontrol.CPABEDecrypt;
import com.code.accesscontrol.CPABEEncrypt;
import com.code.userobjects.UserPatient;
import com.code.utility.Connect;
import com.code.utility.StaticElements;

public class PatientIdEntry {
	public UserPatient uPat;
	public String resourceAttributes = "";
	public String requesterAttributes = "";
	public String actionAttribute = "a:view";

	public PatientIdEntry() {
		uPat = new UserPatient();
	}

	public void processRequest() {
		if(transmitFile()) {
			receiveFile();
		}
	}

	public Boolean transmitFile() {
		if (getResourceAttributes() == 1) {
			if(generateJSONfilewithPatientDetails() == 1) {
				CPABEEncrypt cpabe = new CPABEEncrypt();
				cpabe.getPolicySet();
				cpabe.encryptFile();
				return true;
			}
		}
		return false;
	}

	public int receiveFile() {
		String result = "Access denied!";
		getRequesterAttributes();
		String attrSet = requesterAttributes + " " + resourceAttributes + " " + actionAttribute;
		CPABEDecrypt cpabe = new CPABEDecrypt(attrSet);
		if(cpabe.decryptFile()) {
			result = "Access permitted!";
			System.out.println(result);
			return StaticElements.ACCESS_PERMIT;
		} else {
			System.out.println(result);
			return StaticElements.ACCESS_DENIED;
		}
	}

	public int getResourceAttributes() {
		Connection conn = Connect.startConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "Select * from patient where emailid ='" + uPat.emailId + "';";
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.next()) {
				return StaticElements.NO_PATIENT_WITH_ID;
			} else {
				resourceAttributes += "p:" + rs.getString(1) + " ";
				resourceAttributes += "p:" + rs.getString(3) + " ";
				resourceAttributes += "p:" + rs.getString(4);
				return 1;
			}
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			Connect.endConnection(conn);
		}
		return StaticElements.UNKNOWN_ERROR;
	}

	public int getRequesterAttributes() {
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
		return StaticElements.UNKNOWN_ERROR;
	}

	public int generateJSONfilewithPatientDetails() {
		Connection conn = Connect.startConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "Select * from patient where emailid ='" + uPat.emailId + "';";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", rs.getString(1));
				jsonObject.put("name", rs.getString(2));
				jsonObject.put("special", rs.getString(3));
				jsonObject.put("ward", rs.getString(4));
				jsonObject.put("diagnosis", rs.getString(6));
				jsonObject.put("treatmentProtocol", rs.getString(7));
				try {
					FileWriter file = new FileWriter(StaticElements.TEMP_FOLDER_PATH + "transmit.json");
					file.write(jsonObject.toJSONString());
					file.close();
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					return StaticElements.FILE_ERROR;
				}
			}
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			Connect.endConnection(conn);
		}
		return StaticElements.UNKNOWN_ERROR;
	}
}
