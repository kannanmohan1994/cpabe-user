package com.code.accesscontrol;

import com.code.userobjects.UserPatient;
import com.code.utility.JSONHelper;
import com.code.utility.StaticElements;

public class AccessControlServer {
	public UserPatient uPat;

	public AccessControlServer(UserPatient uPat) {
		this.uPat = uPat;
	}

	public Boolean transmitFile() {
		PolicyInformationPoint pip = new PolicyInformationPoint();
		if (!pip.getResourceAttributes(uPat.emailId).isBlank()) {
			if (JSONHelper.generateJSONfilewithPatientDetails(uPat) == 1) {
				if(uPat.emailId == StaticElements.Patientmail) {
					return true; //Patient accessing is on file condition bypass everything.
				}
				CPABEEncrypt cpabe = new CPABEEncrypt();
				cpabe.encryptFile();
				return true;
			} else {
				System.out.println("Exception occurred. Could not create JSON file.");
			}
		} else {
			System.out.println("Patient with the id doesn't exist.");
		}
		return false;
	}
}
