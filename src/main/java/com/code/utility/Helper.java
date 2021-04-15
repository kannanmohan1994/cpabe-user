package com.code.utility;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Vector;

import javax.swing.*;

import com.code.accesscontrol.CPABEEncrypt;

public class Helper {
	public static void addCompenenttoGrid(JPanel panel, Component comp, int x, int y, int height, int width,
			double weightx, double weighty, Insets inset, int align) {
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridx = x;
		constraint.gridy = y;
		constraint.gridheight = height;
		constraint.gridwidth = width;
		constraint.weightx = weightx;
		constraint.weighty = weighty;
		constraint.insets = inset;
		constraint.fill = align;
		panel.add(comp, constraint);
	}

	public static void showWarningBox(JFrame frame, String message, int messageType) {
		JOptionPane.showMessageDialog(frame, message, "Information", messageType);
	}

	public static void addFreshItemsComboBox(JComboBox jc, Vector<String> items) {
		jc.removeAllItems();
		for (int i = 0; i < items.size(); i++) {
			jc.addItem(items.get(i));
		}
	}

	/** Read the object from Base64 string. */
	public static Object objectFromString(String s) {
		Object o = null;
		try {
			byte[] data = Base64.getDecoder().decode(s);
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
			o = ois.readObject();
			ois.close();
		}
		catch(Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return o;
	}
	
	public static String readStringfromFile(String path) {
		String content = "";
		try {
			content = Files.readString(Paths.get(path));
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return content;
	}
}
