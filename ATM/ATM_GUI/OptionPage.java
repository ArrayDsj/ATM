package com.code.atm_gui;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class OptionPage extends BackJPanel {
	private ATMJFrame atmJFrame;
	private CardPage	cardPage;

	public OptionPage(CardPage cardPage) {
		this.cardPage = cardPage;
		// 添加一个小的图片
		JLabel sam = new JLabel(new ImageIcon("image/option.png"));
		sam.setBounds(214, 260, 600, 150);
		sam.setLayout(null);
		this.getImageLabel().add(sam);

		JButton changeButton = new JButton("改密");
		changeButton.setBounds(21, 241, 130, 50);
		changeButton.setIcon(new ImageIcon("image/buttonModify.jpg"));
		changeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CardLayout layout = (CardLayout) cardPage.getLayout();
				layout.show(cardPage, "changePassWordPage");
			}
		});
		this.getImageLabel().add(changeButton);

		JButton check = new JButton("查询");
		check.setBounds(21, 341, 130, 50);
		check.setIcon(new ImageIcon("image/buttonFind.jpg"));
		check.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CardLayout layout = (CardLayout) cardPage.getLayout();
				layout.show(cardPage, "checkPage");
			}
		});
		this.getImageLabel().add(check);

		JButton saveMoneyButton = new JButton("存款");
		saveMoneyButton.setBounds(851, 241, 130, 50);
		saveMoneyButton.setIcon(new ImageIcon("image/buttonSave.jpg"));
		saveMoneyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CardLayout layout = (CardLayout) cardPage.getLayout();
				layout.show(cardPage, "saveMoneyPage");
			}
		});
		this.getImageLabel().add(saveMoneyButton);

		JButton getMoneyButton = new JButton("取款");
		getMoneyButton.setBounds(851, 341, 130, 50);
		getMoneyButton.setIcon(new ImageIcon("image/buttonGet.jpg"));
		getMoneyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CardLayout layout = (CardLayout) cardPage.getLayout();
				layout.show(cardPage, "getMoneyPage");
			}
		});
		this.getImageLabel().add(getMoneyButton);

		JButton offButton = new JButton("退出");
		offButton.setBounds(447, 652, 130, 50);
		offButton.setIcon(new ImageIcon("image/buttonOff.jpg"));
		offButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CardLayout layout = (CardLayout) cardPage
					.getLayout();
				layout.show(cardPage, "homePage");
			}
		});
		this.getImageLabel().add(offButton);
	}
}
