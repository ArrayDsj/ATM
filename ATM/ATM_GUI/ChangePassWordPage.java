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


public class ChangePassWordPage extends BackJPanel {
	private CardPage	cardPage;
	private JTextField	oldPwd;
	private JTextField	newPwd;
	private JTextField					repetNewPwd;
	private HashMap<String, UserInfo>	userInfoMap;
	private UserInfo					theUser;

	public ChangePassWordPage(CardPage cardPage, LoginPage loginUser) {
		this.cardPage = cardPage;
		this.userInfoMap = loginUser.getUserInfoMap();
		System.out.println(userInfoMap.size());
		// 确定
		JButton sureButton = new JButton("确定");
		sureButton.setBounds(852, 471, 130, 50);
		sureButton.setIcon(new ImageIcon("image/buttonSure.jpg"));
		sureButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 取得当前登录系统的用户信息
				theUser = loginUser.getTheUser();
				// 确认密码
				if (theUser.getPassword().equals(oldPwd.getText().trim())) {
					// 确认输入密码格式
					if (oldPwd.getText().trim().matches("^[0-9]{6,11}$")) {
						// 对比新旧密码
						if (!newPwd.getText()
							.trim()
							.equals(oldPwd.getText().trim())) {
							// 对比2次密码
							if (newPwd.getText()
								.trim()
								.equals(repetNewPwd.getText().trim())) {
								theUser
									.setPassword(repetNewPwd.getText().trim());
								// HashMap中保存了登录用户的引用,更改用户信息后,
								// 直接保存文件就可以了,因为用户本身的信息已经改变了
								if (FileOperation.SaveFile(userInfoMap)) {
									JOptionPane.showMessageDialog(null,
										"密码修改成功,请重新登陆");
									oldPwd.setText("");
									newPwd.setText("");
									repetNewPwd.setText("");
									CardLayout layout = (CardLayout) cardPage
										.getLayout();
									layout.show(cardPage, "loginPage");
								} else {
									JOptionPane.showMessageDialog(null,
										"密码修改失败,请联系柜台");
									oldPwd.setText("");
									newPwd.setText("");
									repetNewPwd.setText("");
								}

							} else {
								JOptionPane.showMessageDialog(null, "两次密码不一致");
								oldPwd.setText("");
								newPwd.setText("");
								repetNewPwd.setText("");
							}
						}else{
							JOptionPane.showMessageDialog(null, "新密码不能跟旧密码相同");
							oldPwd.setText("");
							newPwd.setText("");
							repetNewPwd.setText("");
						}
					}else{
						JOptionPane.showMessageDialog(null, "密码格式错误");
						oldPwd.setText("");
						newPwd.setText("");
						repetNewPwd.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null, "密码错误");
					oldPwd.setText("");
				}
			}
		});
		this.getImageLabel().add(sureButton);

		// 清除
		JButton clearButton = new JButton();
		clearButton.setBounds(22, 471, 140, 50);
		clearButton.setIcon(new ImageIcon("image/buttonClear.jpg"));
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				oldPwd.setText("");
				newPwd.setText("");
				repetNewPwd.setText("");
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
				CardLayout layout = (CardLayout) cardPage.getLayout();
				layout.show(cardPage, "homePage");
			}
		});
		this.getImageLabel().add(OffButton);

		// 添加一个小的图片
		JLabel sam = new JLabel(new ImageIcon("image/pane.jpg"));
		sam.setBounds(214, 245, 620, 200);
		sam.setLayout(null);
		this.getImageLabel().add(sam);

		JLabel lblNewLabel = new JLabel("请输入旧密码");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setBounds(30, 40, 101, 24);
		sam.add(lblNewLabel);

		JLabel label = new JLabel("请输入新密码");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setBounds(30, 85, 101, 24);
		sam.add(label);

		JLabel label1 = new JLabel("请再次新密码");
		label1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label1.setBounds(30, 130, 101, 24);
		sam.add(label1);

		oldPwd = new JTextField();
		oldPwd.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		oldPwd.setBounds(160, 40, 258, 21);
		sam.add(oldPwd);
		oldPwd.setColumns(10);

		newPwd = new JTextField();
		newPwd.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		newPwd.setColumns(10);
		newPwd.setBounds(160, 85, 258, 21);
		sam.add(newPwd);

		repetNewPwd = new JTextField();
		repetNewPwd.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		repetNewPwd.setColumns(10);
		repetNewPwd.setBounds(160, 130, 258, 21);
		sam.add(repetNewPwd);

		JLabel label_1 = new JLabel("6到11位数字");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_1.setBounds(450, 85, 102, 15);
		sam.add(label_1);
	}
}
