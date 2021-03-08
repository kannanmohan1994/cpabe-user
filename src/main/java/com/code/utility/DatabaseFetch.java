package com.code.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class DatabaseFetch {
	public static Vector<String> fetchColumnNames(String table){
		Connection conn = Connect.startConnection();
		Vector<String> result = new Vector<String>();
		try {
			Statement stmt = conn.createStatement();
			String query = "SHOW columns FROM "+table+";";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			Connect.endConnection(conn);
		}
		return result;
	}
	
	public static Vector<String> fetchAllValuesinColumn(String table, String col){
		Connection conn = Connect.startConnection();
		Vector<String> result = new Vector<String>();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT DISTINCT("+col+") FROM "+table+";";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			Connect.endConnection(conn);
		}
		return result;
	}
	
	public static Vector<String> fetchSpecialDetails() {
		Connection conn = Connect.startConnection();
		Vector<String> result = new Vector<String>();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT DISTINCT(special) FROM category;";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			Connect.endConnection(conn);
		}
		return result;
	}
	
	public static Vector<String> fetchWardDetails(String special) {
		Connection conn = Connect.startConnection();
		Vector<String> result = new Vector<String>();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT ward FROM category WHERE special='"+special+"';";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			Connect.endConnection(conn);
		}
		return result;
	}
}
