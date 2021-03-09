package com.code.userobjects;

public class UserPatient {
	public String emailId;
	public String name;
	public String special;
	public String ward;
	public String password;
	public String diagnosis;
	public String treatmentProtocol;
	
	public UserPatient() {
		
	}
	
	public UserPatient(String emailId, String name, String special, String ward, String password) {
		this.emailId = emailId;
		this.name = name;
		this.special = special;
		this.ward = ward;
		this.password = password;
	}
	
	public UserPatient(String emailId, String name, String special, String ward, String password, 
			String diagnosis, String treatmentProtocol) {
		this.emailId = emailId;
		this.name = name;
		this.special = special;
		this.ward = ward;
		this.password = password;
		this.diagnosis = diagnosis;
		this.treatmentProtocol = treatmentProtocol;
	}
}
