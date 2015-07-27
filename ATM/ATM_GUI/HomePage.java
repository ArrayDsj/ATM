package com.code.atm_gui;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class HomePage extends BackJPanel {
	// 登录按钮
	private JButton		loginButton;
	private CardPage	cardPage;
	// 退出按钮
	private JButton		offButton;
	// 注册按钮
	private JButton		registerButton;

	// 继承BackJPanel,布局为空 组件需要决定定位
	public HomePage(CardPage cardPage) {
		this.cardPage = cardPage;

		// 登录按钮
		loginButton = new JButton();
		loginButton.setBounds(23, 661, 140, 50);
		loginButton.setIcon(new ImageIcon("image/buttonLogin.jpg"));
		// 添加监听器
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 卡片布局,如果登录成功,则跳转到登录页
				CardLayout layout = (CardLayout) cardPage.getLayout();
				layout.show(cardPage, "loginPage");
			}
		});
		// 按钮添加到BackJPanel的标签上,不然会被覆盖,导致不显示按钮
		this.getImageLabel().add(loginButton);

		// 退出按钮
		offButton = new JButton();
		offButton.setBounds(447, 661, 140, 50);
		offButton.setIcon(new ImageIcon("image/buttonOff.jpg"));
		// 直接退出
		offButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (JOptionPane.showConfirmDialog(null,
					"是否退出?",
					"退出",
					JOptionPane.YES_NO_OPTION) == 0) {
					// 确认退出
					JOptionPane.showMessageDialog(null, "谢谢使用");
					System.exit(0);
				}

			}
		});
		this.getImageLabel().add(offButton);

		// 注册按钮
		registerButton = new JButton();
		registerButton.setBounds(832, 661, 140, 50);
		registerButton.setIcon(new ImageIcon("image/buttonRegister.jpg"));
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CardLayout layout = (CardLayout) cardPage.getLayout();
				layout.show(cardPage, "registerPage");
			}
		});

		this.getImageLabel().add(registerButton);
	}
}
