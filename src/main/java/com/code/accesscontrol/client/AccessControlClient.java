package com.code.accesscontrol.client;

import com.code.accesscontrol.PolicyInformationPoint;
import com.code.userobjects.UserPatient;
import com.code.utility.StaticElements;

public class AccessControlClient {
	public UserPatient uPat;
	public String resourceAttributes = "";
	public String requesterAttributes = "";
	public String actionAttribute = "";
	public PolicyInformationPoint pip = new PolicyInformationPoint();
	
	public int receiveFile(String resourceMailId) {
		String inputStringbyEdgeDevice = "";
		String result = "";
		if(!StaticElements.Doctormail.isEmpty()) { //if doctor
			inputStringbyEdgeDevice = "true " + StaticElements.Doctormail + " ";
		} else {
			inputStringbyEdgeDevice = "false " + StaticElements.Patientmail + " ";
		}
		inputStringbyEdgeDevice += resourceMailId + " " + actionAttribute;
		EdgeDevice ed = new EdgeDevice();
		if(ed.getPHRFilefromEdgeServer(inputStringbyEdgeDevice)) {
			result = "Access permitted!";
			System.out.println(result);
			return StaticElements.ACCESS_PERMIT;
		} else {
			System.out.println(result);
			return StaticElements.ACCESS_DENIED;
		}
	}
}
