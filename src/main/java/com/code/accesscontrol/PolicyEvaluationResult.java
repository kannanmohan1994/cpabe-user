package com.code.accesscontrol;

import java.io.Serializable;

public class PolicyEvaluationResult implements Serializable {
	private static final long serialVersionUID = 1L;
	public boolean isPolicySatisfy = false;
	public byte[] encFile = {};
	public byte[] encFileKey = {};
	
	public PolicyEvaluationResult(boolean isPolicySatisfy, byte[] encFile, byte[] encFileKey) {
		this.isPolicySatisfy = isPolicySatisfy;
		this.encFile = encFile;
		this.encFileKey = encFileKey;
	}
}
