package com.code.accesscontrol.client;

import java.io.*;  
import java.net.*;

public class EdgeDevice {
	public EdgeDevice() {
		try{      
			Socket s=new Socket("localhost",6666);  
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
			dout.writeUTF("true abcde patient a:view");  
			dout.flush();  
			dout.close();  
			s.close();  
		}catch(Exception e){System.out.println(e);}  
	}
	public static void main(String[] args) {  
		EdgeDevice ed = new EdgeDevice();
	}  
}
