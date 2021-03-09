package com.code.utility;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONObject;

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
}
