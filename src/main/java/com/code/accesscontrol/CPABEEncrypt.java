package com.code.accesscontrol;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.code.cpabe_api.junwei.cpabe.Cpabe;
import com.code.utility.Connect;
import com.code.utility.StaticElements;

public class CPABEEncrypt {
	Cpabe cpabe = new Cpabe();
	String policySet = "";
	
	public String encryptFile() {
		PolicyAdministrationPoint pap = new PolicyAdministrationPoint();
		policySet = pap.getPolicySet();
		System.out.println("Policy: "+policySet);
		String result = "";
		try {
			cpabe.setup(StaticElements.pubfile, StaticElements.mskfile);
		} catch (Exception e) {
			result = "Exception occured at setup phase";
		}
		try {
			cpabe.enc(StaticElements.pubfile, policySet, StaticElements.inputfile, StaticElements.encfile);
		} catch (Exception e) {
			result = "Exception occured at encryption phase";
		}
		return result;
	}
	
	
}
