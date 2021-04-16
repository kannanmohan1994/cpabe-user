package com.code.accesscontrol.edgeserver;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;  
import java.net.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.code.accesscontrol.CPABEDecrypt;
import com.code.accesscontrol.PolicyEvaluationResult;
import com.code.accesscontrol.PolicyInformationPoint;
import com.code.userobjects.UserPatient;
import com.code.utility.Helper;
import com.code.utility.StaticElements;  

public class EdgeServer extends JFrame implements ActionListener{  
	Container container = getContentPane();
	JLabel statusLabel = new JLabel("Running server...");
	JButton offServerBtn = new JButton("ServerOFF");
	public boolean serverRunning = true;
	public UserPatient uPat;
	public String resourceAttributes = "";
	public String requesterAttributes = "";
	public String actionAttribute = "";
	public PolicyInformationPoint pip = new PolicyInformationPoint();

	public EdgeServer() {
		initialSetup();
		addComponentsToContainer();
		addActionEvents();
		getPHRFilefromCloudServer();
	}

	public void initialSetup() {
		setTitle("Edge Server");
		setVisible(true);
		setSize(new Dimension(250, 125));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		container.setLayout(new BorderLayout());
	}

	public void addComponentsToContainer() {
		container.add(setupPanel());
	}
	
	public void addActionEvents() {
		offServerBtn.addActionListener(this);
	}
	
	public Component setupPanel() {
		GridBagLayout grid = new GridBagLayout();
		JPanel p = new JPanel(grid);
		Insets inset = new Insets(5, 10, 5, 10);
		Helper.addCompenenttoGrid(p, statusLabel, 0, 1, 1, 1, 1, 1, inset, GridBagConstraints.HORIZONTAL);
		Helper.addCompenenttoGrid(p, offServerBtn, 0, 2, 1, 1, 1, 1, inset, GridBagConstraints.CENTER);
		return p;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == offServerBtn) {
			serverRunning = false;
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	} 

	public void getPHRFilefromCloudServer() {
		try{  
			while(serverRunning) {
			ServerSocket ss=new ServerSocket(6666);  
				Socket s=ss.accept();
				PrintStream ps = new PrintStream(s.getOutputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String str = br.readLine();
				String[] contents = str.split(" ");
				statusLabel.setText("Got client request...");
				PolicyEvaluationResult per = policyEvaluateCloudServerFile(contents);
				String serialized = Helper.objectToString(per);
				statusLabel.setText("Response given to client...");
				if(per != null) {
					ps.println(serialized);
				} else {
					ps.println("");
				} 
				ss.close();
			}
		}catch(Exception e){System.out.println(e);}
	}

	public PolicyEvaluationResult policyEvaluateCloudServerFile(String[] contents) {
		String result = "Access denied!";
		requesterAttributes = pip.getRequesterAttributes(Boolean.parseBoolean(contents[0]), contents[1]);
		resourceAttributes = pip.getResourceAttributes(contents[2]);
		actionAttribute = contents[3];
		String attrSet = requesterAttributes + " " + resourceAttributes + " " + actionAttribute;
		System.out.println(attrSet);
		CPABEDecrypt cpabe = new CPABEDecrypt(attrSet);
		return cpabe.edgeServerDecryption();
	} 

	public static void main(String[] args){  
		EdgeServer es = new EdgeServer();
	}
}  
