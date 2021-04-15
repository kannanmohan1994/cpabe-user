package com.code.accesscontrol;

public class PolicyEvaluationResult {
	public boolean isPolicySatisfy = false;
	public byte[] encFile = {};
	public byte[] encFileKey = {};
	
	public PolicyEvaluationResult(boolean isPolicySatisfy, byte[] encFile, byte[] encFileKey) {
		this.isPolicySatisfy = isPolicySatisfy;
		this.encFile = encFile;
		this.encFileKey = encFileKey;
	}
}
