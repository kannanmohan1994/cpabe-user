package com.code.accesscontrol;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.code.cpabe_api.junwei.cpabe.Cpabe;
import com.code.utility.Connect;
import com.code.utility.StaticElements;

public class CPABEDecrypt {
	Cpabe cpabe = new Cpabe();
	String attributeSet = "";

	public CPABEDecrypt(String attributeSet) {
		this.attributeSet = attributeSet;
	}

	public PolicyEvaluationResult edgeServerDecryption() {
		PolicyEvaluationResult per = new PolicyEvaluationResult(false, null, null);
		try {
			cpabe.keygen(StaticElements.pubfile, StaticElements.prvfile, StaticElements.mskfile, attributeSet);
		} catch (Exception e) {
			System.out.print(e);
		}
		try {
			per = cpabe.partialDecrypt_1(StaticElements.pubfile, StaticElements.prvfile, StaticElements.encfile);
			if(per.isPolicySatisfy) {
				return per;
			}
		} catch (Exception e) {
			System.out.print(e);
		}
		return null;
	}
	
	public Boolean edgeDeviceDecryption(PolicyEvaluationResult per) {
		if(per == null) {
			return false;
		}
		try {
			cpabe.partialDecrypt_2(per, StaticElements.decfile);
			return true;
		} catch (Exception e) {
			System.out.print(e);
		}
		return false;
	}
}
