package com.code.login.backend;

import java.sql.*;

import com.code.utility.Connect;

public class Login {
	private String userType;
	private String userName;
	private String password;

	public Login(String userName, String password, String userType) {
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}

	public Boolean checkLoginDetailsCorrect() {
		Connection conn = Connect.startConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM "+userType+" WHERE "
					+ "emailid='" + userName + "' and password='" + password + "';";
			ResultSet rs = stmt.executeQuery(query);
			return rs.first();
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			Connect.endConnection(conn);
		}
		return false;
	}
}
