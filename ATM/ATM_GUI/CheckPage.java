package com.code.atm_gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class CheckPage extends BackJPanel {

	private CardPage cardPage;

	public CheckPage(CardPage cardPage, LoginPage loginUser) {
		this.cardPage = cardPage;

		// 添加一个小的图片
		JLabel sam = new JLabel(new ImageIcon("image/CheckPage.png"));
		sam.setBounds(214, 245, 620, 200);
		sam.setLayout(null);
		this.getImageLabel().add(sam);


		JButton offButton = new JButton("退出");
		offButton.setBounds(466, 682, 130, 50);
		offButton.setIcon(new ImageIcon("image/buttonOff.jpg"));
		offButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CardLayout layout = (CardLayout) cardPage.getLayout();
				layout.show(cardPage, "optionPage");
			}
		});
		this.getImageLabel().add(offButton);
	}
}
