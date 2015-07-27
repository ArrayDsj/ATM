package com.code.atm_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class ATMJFrame extends JFrame {
	private Container	contentP;
	private CardPage	cardPage;

	public ATMJFrame() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		this.setTitle("ATM");
		this.setSize(1024, 768);
		// 设置窗体的位置
		this.setLocation(((int) tk.getScreenSize().getWidth() - 1024) / 2,
			((int) tk.getScreenSize().getHeight() - 768) / 2);
		// 设置窗体尺寸不可更改
		this.setResizable(false);
		// 设置logo
		// this.setIconImage(tk.createImage("img/hp.JPG"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.addContent();

		// 显示窗口
		this.setVisible(true);
	}

	private void addContent() {
		// TODO Auto-generated method stub
		// 得到Container框架层

		this.contentP = this.getContentPane();
		this.contentP.setLayout(new BorderLayout());
		this.contentP.setBackground(Color.BLUE);

		cardPage = new CardPage();
		this.contentP.add(cardPage);
	}
}
