package com.code.utility;

import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.code.userobjects.UserPatient;

public class JSONHelper {
	public static int generateJSONfilewithPatientDetails(UserPatient uPat) {
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

	public static UserPatient fetchPatientDetailsfromJSON() {
		JSONParser parser = new JSONParser();
		UserPatient up = new UserPatient();
		try {
			Object obj = parser.parse(new FileReader(StaticElements.TEMP_FOLDER_PATH + "PHRFile.json"));
			JSONObject patientObject = (JSONObject) obj;
			up.emailId = (String) patientObject.get("id");
			up.name = (String) patientObject.get("name");
			up.special = (String) patientObject.get("special");
			up.ward = (String) patientObject.get("ward");
			up.diagnosis = (String) patientObject.get("diagnosis");
			up.treatmentProtocol = (String) patientObject.get("treatmentProtocol");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return up;
	}
}
