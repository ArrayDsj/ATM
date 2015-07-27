package com.code.atm_gui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SaveMoneyPage extends BackJPanel {
	private CardPage cardPage;
	private JTextField	saveMoney;
	private JButton		offButton;
	private JButton		sureButton;
	private JButton		clearButton;

	public SaveMoneyPage(CardPage cardPage) {
		this.cardPage = cardPage;

		


		// 清除
		clearButton = new JButton();
		clearButton.setBounds(22, 471, 140, 50);
		clearButton.setIcon(new ImageIcon("image/buttonClear.jpg"));
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				saveMoney.setText("");
			}
		});
		this.getImageLabel().add(clearButton);

		// 退出
		offButton = new JButton();
		offButton.setBounds(446, 649, 130, 50);
		offButton.setIcon(new ImageIcon("image/buttonOff.jpg"));
		offButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CardLayout layout = (CardLayout) cardPage
					.getLayout();
				layout.show(cardPage, "optionPage");
			}
		});
		this.getImageLabel().add(offButton);

		// 确定
		sureButton = new JButton("确定");
		sureButton.setBounds(852, 471, 130, 50);
		sureButton.setIcon(new ImageIcon("image/buttonSure.jpg"));
		sureButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "添加代码");
			}
		});
		this.getImageLabel().add(sureButton);

		// 添加一个小的图片
		JLabel sam = new JLabel(new ImageIcon("image/pane.jpg"));
		sam.setBounds(214, 245, 620, 200);
		sam.setLayout(null);
		this.getImageLabel().add(sam);

		JLabel lblNewLabel = new JLabel("请输入存款金额");
		sam.add(lblNewLabel);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblNewLabel.setBounds(58, 70, 150, 50);

		saveMoney = new JTextField();
		saveMoney.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		saveMoney.setBounds(220, 70, 234, 50);
		saveMoney.setColumns(10);
		sam.add(saveMoney);

	}
}
