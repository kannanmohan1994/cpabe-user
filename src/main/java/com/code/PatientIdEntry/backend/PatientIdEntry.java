package com.code.PatientIdEntry.backend;

import com.code.accesscontrol.AccessControlServer;
import com.code.accesscontrol.client.AccessControlClient;
import com.code.userobjects.UserPatient;
import com.code.utility.StaticElements;

public class PatientIdEntry {
	public UserPatient uPat;
	public String actionAttribute = "a:view";
	
	public PatientIdEntry() {
		uPat = new UserPatient();
	}
	public int processRequest() {
		AccessControlServer cloudServer = new AccessControlServer(uPat);
		AccessControlClient edgeClient = new AccessControlClient();
		if(cloudServer.transmitFile()) {
			edgeClient.actionAttribute = actionAttribute;
			if((uPat.emailId.trim().equals(StaticElements.Patientmail)) || (edgeClient.receiveFile(uPat.emailId) == 1)) {
				return 1; //Access permit
			} else {
				return 0; //Access denied
			}
		} else {
			return -1; //Couldn't transmit file. Patient id doesn't exist.
		}
	}
	
}
