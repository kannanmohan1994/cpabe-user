package com.code.accesscontrol;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.code.cpabe_api.junwei.bswabe.BswabePolicy;
import com.code.utility.Connect;
import com.code.utility.Helper;
import com.code.utility.StaticElements;

public class PolicyAdministrationPoint {
	public BswabePolicy getPolicySet() { 
		String objectasString = Helper.readStringfromFile(StaticElements.policytree);
		BswabePolicy bswPolicy = (BswabePolicy)Helper.objectFromString(objectasString);
		return bswPolicy;
	}
}
