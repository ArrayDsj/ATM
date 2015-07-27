package com.code.atm_gui;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class SmallJPanel extends JPanel {
	private JLabel imageLabel;

	public JLabel getImageLabel() {
		return imageLabel;
	}

	public void setImageLabel(JLabel imageLabel) {
		this.imageLabel = imageLabel;
	}

	public SmallJPanel() {
		this.setSize(620, 200);
		this.setLayout(new BorderLayout());
		imageLabel = new JLabel(new ImageIcon("image/pane.jpg"));
		imageLabel.setLayout(null);
		this.add(imageLabel);
	}
}
