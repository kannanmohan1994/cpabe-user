package com.code.accesscontrol;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.code.utility.Connect;

public class PolicyAdministrationPoint {
	public String getPolicySet() {
		String policySet = "";
		Connection conn = Connect.startConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM policyset;";
			ResultSet rs = stmt.executeQuery(query);
			int count = 0;
			while (rs.next()) {
				policySet += rs.getString(2) + " ";
				count++;
			}
			policySet += "1of" + count;
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			Connect.endConnection(conn);
		}
		return policySet;
	}
}
