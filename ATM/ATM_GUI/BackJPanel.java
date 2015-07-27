package com.code.atm_gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


//背景面板
public class BackJPanel extends JPanel{
	private JLabel imageLabel;

	public JLabel getImageLabel() {
		return imageLabel;
	}

	public void setImageLabel(JLabel imageLabel) {
		this.imageLabel = imageLabel;
	}
	
	public BackJPanel() {
		this.setSize(1024, 768);
		this.setLayout(null);
		imageLabel = new JLabel(new ImageIcon("image/background.jpg"));
		imageLabel.setBounds(0, 0, 1024, 768);
		imageLabel.setLayout(null);
		this.add(imageLabel);
	}
}

