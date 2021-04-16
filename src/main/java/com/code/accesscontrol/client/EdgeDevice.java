package com.code.accesscontrol.client;

import java.io.*;  
import java.net.*;

import com.code.accesscontrol.CPABEDecrypt;
import com.code.accesscontrol.PolicyEvaluationResult;
import com.code.cpabe_api.junwei.bswabe.BswabePolicy;
import com.code.utility.Helper;

public class EdgeDevice {
	public boolean getPHRFilefromEdgeServer(String inputStringbyEdgeDevice) {
		try{      
			Socket s=new Socket("localhost",6666); 
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			dout.writeBytes(inputStringbyEdgeDevice + "\n");
            String response = br.readLine().trim();
            if(!response.trim().isEmpty()) {
            	CPABEDecrypt cpabe = new CPABEDecrypt("");
            	PolicyEvaluationResult per = (PolicyEvaluationResult)Helper.objectFromString(response);
                cpabe.edgeDeviceDecryption(per);
                s.close(); 
                return true;
            }
            s.close(); 
		}catch(Exception e){System.out.println(e);}  
		return false;
	}
	
	public static void main(String[] args) {  
		EdgeDevice ed = new EdgeDevice();
	}  
}
