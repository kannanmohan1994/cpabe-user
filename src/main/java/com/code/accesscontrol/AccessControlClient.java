package com.code.accesscontrol;

import com.code.userobjects.UserPatient;
import com.code.utility.StaticElements;

public class AccessControlClient {
	public UserPatient uPat;
	public String resourceAttributes = "";
	public String requesterAttributes = "";
	public String actionAttribute = "";
	public PolicyInformationPoint pip = new PolicyInformationPoint();
	
	public int receiveFile(String mailId) {
		String result = "Access denied!";
		resourceAttributes = pip.getResourceAttributes(mailId);
		requesterAttributes = pip.getRequesterAttributes(!StaticElements.Doctormail.isEmpty(), StaticElements.Doctormail);
		String attrSet = requesterAttributes + " " + resourceAttributes + " " + actionAttribute;
		System.out.println(attrSet);
		CPABEDecrypt cpabe = new CPABEDecrypt(attrSet);
		if(cpabe.decryptFile()) {
			result = "Access permitted!";
			System.out.println(result);
			return StaticElements.ACCESS_PERMIT;
		} else {
			System.out.println(result);
			return StaticElements.ACCESS_DENIED;
		}
	}
}
