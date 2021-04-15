package com.code.accesscontrol.edgeserver;

import java.io.*;  
import java.net.*;

import com.code.accesscontrol.CPABEDecrypt;
import com.code.accesscontrol.PolicyInformationPoint;
import com.code.userobjects.UserPatient;
import com.code.utility.StaticElements;  

public class EdgeServer {  
	public UserPatient uPat;
	public String resourceAttributes = "d:MEDICINE";
	public String requesterAttributes = "";
	public String actionAttribute = "a:view";
	public PolicyInformationPoint pip = new PolicyInformationPoint();
	
	public EdgeServer() {
		try{  
			ServerSocket ss=new ServerSocket(6666);  
			Socket s=ss.accept();   
			DataInputStream dis=new DataInputStream(s.getInputStream());  
			String str = (String)dis.readUTF();  
			String[] contents = str.split(" ");
			receiveFile(contents);
			//System.out.println("message= "+str);  
			ss.close();  
		}catch(Exception e){System.out.println(e);}  
	}
	
	public int receiveFile(String[] contents) {
		String result = "Access denied!";
		requesterAttributes = pip.getRequesterAttributes(Boolean.parseBoolean(contents[0]), contents[1]);
		resourceAttributes = pip.getResourceAttributes(contents[2]);
		actionAttribute = contents[3];
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
	
	public static void main(String[] args){  
		EdgeServer es = new EdgeServer();
	}  
}  
