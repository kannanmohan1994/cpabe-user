package com.code.accesscontrol;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.code.cpabe_api.junwei.bswabe.BswabePolicy;
import com.code.cpabe_api.junwei.cpabe.Cpabe;
import com.code.utility.Connect;
import com.code.utility.StaticElements;

public class CPABEEncrypt {
	Cpabe cpabe = new Cpabe();
	BswabePolicy policyTree;
	
	public String encryptFile() {
		PolicyAdministrationPoint pap = new PolicyAdministrationPoint();
		policyTree = pap.getPolicySet();
		String result = "";
		try {
			File pubFile = new File(StaticElements.pubfile);
			File mskfile = new File(StaticElements.mskfile);
			if(!pubFile.exists() || !mskfile.exists()) {
				cpabe.setup(StaticElements.pubfile, StaticElements.mskfile);
			}
		} catch (Exception e) {
			result = "Exception occured at setup phase";
		}
		try {
			cpabe.enc(StaticElements.pubfile, policyTree, StaticElements.inputfile, StaticElements.encfile);
		} catch (Exception e) {
			result = "Exception occured at encryption phase";
		}
		return result;
	}
	
	
}
