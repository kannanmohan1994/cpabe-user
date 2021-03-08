package com.code.utility;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;

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
		for(int i=0; i<items.size(); i++) {
			jc.addItem(items.get(i)); 
		}
	}
}
