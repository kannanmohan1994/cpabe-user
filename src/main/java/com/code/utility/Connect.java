package com.code.utility;
import java.sql.*;

public class Connect {
	public static Connection startConnection(){
		Connection conn = null;
		String userName = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/";
        String driver = "com.mysql.jdbc.Driver";
        String dbName = "cpabe";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection (url+dbName, userName, password);
            System.out.println ("Database connection established");
        }
        catch (Exception e) {
            System.err.println ("Cannot connect to database server");
            System.out.print(e);
        }
        return conn;
	}
	public static void endConnection(Connection conn){
		if (conn != null) {
            try {
                conn.close ();
                System.out.println ("Database connection terminated");
            }
            catch (Exception e) { 
            	System.out.print(e); 
            }
        }
	}
	public static void main(String[] args) {
		Connection c = startConnection();
		endConnection(c);
	}

}
