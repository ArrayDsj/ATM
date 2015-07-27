package com.code.atm_gui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//注册页,使用HashMap保存用户信息
public class RegisterPage extends BackJPanel {
	private HashMap<String, UserInfo>	userInfoMap;
	private CardPage cardPage;
	private JTextField					userName, repetPwd, userPwd;
	private UserInfo					newUser;

	public RegisterPage(CardPage cardPage) {
		userInfoMap = FileOperation.ReadFile();
		// 清除
		JButton clearButton = new JButton();
		clearButton.setBounds(22, 471, 140, 50);
		clearButton.setIcon(new ImageIcon("image/buttonClear.jpg"));
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userName.setText("");
				userPwd.setText("");
				repetPwd.setText("");
			}
		});
		this.getImageLabel().add(clearButton);

		// 退出
		JButton OffButton = new JButton("退出");
		OffButton.setBounds(446, 649, 130, 50);
		OffButton.setIcon(new ImageIcon("image/buttonOff.jpg"));
		OffButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (JOptionPane.showConfirmDialog(null,
					"是否放弃注册?",
					"注册",
					JOptionPane.YES_NO_OPTION) == 0) {
					// 确认退出
					userName.setText("");
					userPwd.setText("");
					repetPwd.setText("");
					CardLayout layout = (CardLayout) cardPage.getLayout();
					layout.show(cardPage, "homePage");
				}
			}
		});
		this.getImageLabel().add(OffButton);

		// 确定
		JButton sureButton = new JButton("确定");
		sureButton.setBounds(852, 471, 130, 50);
		sureButton.setIcon(new ImageIcon("image/buttonSure.jpg"));
		sureButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

//				if (!userName.getText().isEmpty()
//						&& !userName.getText().trim().equals("")
//						&& !userPwd.getText().isEmpty()
//						&& !userPwd.getText().trim().equals("")
//						&& !repetPwd.getText().isEmpty()
//						&& !repetPwd.getText().trim().equals("")) {
				if (userName.getText().matches("^[a-zA-Z0-9]{6,11}$") &&
						userPwd.getText().matches("^[0-9]{6,11}$") &&
						repetPwd.getText().matches("^[0-9]{6,11}$")) {
					if (repetPwd.getText()
						.trim()
						.equals(userPwd.getText().trim())) {
						newUser = new UserInfo(userName.getText(),
							userPwd.getText());
						userInfoMap.put(userName.getText(), newUser);
						if (FileOperation.SaveFile(userInfoMap)) {
							JOptionPane.showMessageDialog(null, "注册成功,谢谢使用");
							userName.setText("");
							userPwd.setText("");
							repetPwd.setText("");
							CardLayout layout = (CardLayout) cardPage
								.getLayout();
							layout.show(cardPage, "optionPage");
						}
					} else {
						JOptionPane.showMessageDialog(null, "两次密码不一致,请重新输入");
					}

				} else
					JOptionPane.showMessageDialog(null, "输入不合法,请重新输入");
			}
		});
		this.getImageLabel().add(sureButton);

		// 添加一个小的图片
		JLabel sam = new JLabel(new ImageIcon("image/pane.jpg"));
		sam.setBounds(195, 260, 620, 200);
		sam.setLayout(null);
		this.getImageLabel().add(sam);



		JLabel userNameJLabel = new JLabel("请输入用户名");
		userNameJLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		userNameJLabel.setBounds(65, 45, 133, 15);
		sam.add(userNameJLabel);

		JLabel yonghuming = new JLabel("请输入密码");
		yonghuming.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		yonghuming.setBounds(65, 95, 133, 15);
		sam.add(yonghuming);

		JLabel newmima = new JLabel("请重新输入密码");
		newmima.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		newmima.setBounds(65, 145, 133, 15);
		sam.add(newmima);

		userName = new JTextField();
		userName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		userName.setBounds(200, 40, 234, 25);
		userName.setColumns(10);
		sam.add(userName);

		userPwd = new JTextField();
		userPwd.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		userPwd.setBounds(200, 90, 234, 25);
		userPwd.setColumns(10);
		sam.add(userPwd);

		repetPwd = new JTextField();
		repetPwd.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		repetPwd.setBounds(200, 140, 234, 25);
		repetPwd.setColumns(10);
		sam.add(repetPwd);

		JLabel label_1 = new JLabel("6到11位数字");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_1.setBounds(450, 95, 102, 15);
		sam.add(label_1);

		JLabel label = new JLabel("6到11位字母和数字");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setBounds(450, 45, 130, 15);
		sam.add(label);
	}
}
