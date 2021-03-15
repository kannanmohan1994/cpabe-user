package com.code.PatientIdEntry.backend;

import com.code.accesscontrol.AccessControlClient;
import com.code.accesscontrol.AccessControlServer;
import com.code.userobjects.UserPatient;
import com.code.utility.StaticElements;

public class PatientIdEntry {
	public UserPatient uPat;
	public String actionAttribute = "a:view";
	
	public PatientIdEntry() {
		uPat = new UserPatient();
	}
	public int processRequest() {
		AccessControlServer server = new AccessControlServer(uPat);
		AccessControlClient client = new AccessControlClient();
		if(server.transmitFile()) {
			client.actionAttribute = actionAttribute;
			System.out.println("mail: "+StaticElements.Patientmail);
			if((uPat.emailId.trim().equals(StaticElements.Patientmail)) || (client.receiveFile(uPat.emailId) == 1)) {
				return 1; //Access permit
			} else {
				return 0; //Access denied
			}
		} else {
			return -1; //Couldn't transmit file. Patient id doesn't exist.
		}
	}
	
}
