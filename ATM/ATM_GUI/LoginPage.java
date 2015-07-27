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

public class LoginPage extends BackJPanel {
	private JButton		loginButton;
	private CardPage	cardPage;
	private JButton		offButton;
	private JButton		registerButton;
	private JLabel		label;
	private JLabel		label_1;
	private JTextField	userName;
	private JTextField	userPwd;
	private HashMap<String, UserInfo>	userInfoMap;
	private UserInfo					theUser;

	public UserInfo getTheUser() {
		return theUser;
	}

	public void setTheUser(UserInfo theUser) {
		this.theUser = theUser;
	}


	public HashMap<String, UserInfo> getUserInfoMap() {
		return userInfoMap;
	}

	public void setUserInfoMap(HashMap<String, UserInfo> userInfoMap) {
		this.userInfoMap = userInfoMap;
	}

	public LoginPage(CardPage cardPage) {
		userInfoMap = FileOperation.ReadFile();
		this.cardPage = cardPage;


		// 确定按钮
		registerButton = new JButton();
		registerButton.setBounds(852, 471, 130, 50);
		registerButton.setIcon(new ImageIcon("image/buttonSure.jpg"));
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (userName.getText().matches("^[a-zA-Z0-9]{6,11}$") &&
						userPwd.getText().matches("^[0-9]{6,11}$")) {
					// HashMap中是否包含指定的键
					if (userInfoMap.containsKey(userName.getText())) {
						UserInfo user = userInfoMap.get(userName.getText());
						System.out.println(
							user.getUsername() + "  " + user.getPassword());
						// 在对比指定键所对应的值
						if (user.getPassword()
							.trim()
							.equals(userPwd.getText().trim())) {
							theUser = user;
							userName.setText("");
							userPwd.setText("");
							CardLayout layout = (CardLayout) cardPage
								.getLayout();
							layout.show(cardPage, "optionPage");
						} else {
							JOptionPane.showMessageDialog(null, "密码不正确");
							userPwd.setText("");
						}
					} else {
						JOptionPane.showMessageDialog(null, "用户名不正确");
						userName.setText("");
						userPwd.setText("");
					}
				} else
					JOptionPane.showMessageDialog(null, "输入不合法");
			}
		});

		this.getImageLabel().add(registerButton);

		// 清除按钮
		loginButton = new JButton();
		loginButton.setBounds(22, 471, 140, 50);
		loginButton.setIcon(new ImageIcon("image/buttonClear.jpg"));
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userName.setText("");
				userPwd.setText("");
			}
		});
		this.getImageLabel().add(loginButton);

		// 退出按钮
		offButton = new JButton();
		offButton.setBounds(446, 649, 130, 50);
		offButton.setIcon(new ImageIcon("image/buttonOff.jpg"));
		offButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userName.setText("");
				userPwd.setText("");
				CardLayout layout = (CardLayout) cardPage
					.getLayout();
				layout.show(cardPage, "homePage");
			}
		});
		this.getImageLabel().add(offButton);

		// 添加一个小的图片
		JLabel sam = new JLabel(new ImageIcon("image/pane.jpg"));
		sam.setBounds(214, 245, 620, 200);
		sam.setLayout(null);
		this.getImageLabel().add(sam);

		userName = new JTextField("");
		userName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		userName.setBounds(160, 55, 258, 21);
		sam.add(userName);
		userName.setColumns(10);

		userPwd = new JTextField("");
		userPwd.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		userPwd.setColumns(10);
		userPwd.setBounds(160, 110, 258, 21);
		sam.add(userPwd);

		label = new JLabel("请输入用户名");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		sam.add(label);
		label.setBounds(30, 50, 101, 24);

		label_1 = new JLabel("请输入密码");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		sam.add(label_1);
		label_1.setBounds(30, 105, 101, 24);

	}
}
