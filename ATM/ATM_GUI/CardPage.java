package com.code.atm_gui;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class CardPage extends JPanel {
	private BackJPanel			backJPanel;
	private LoginPage			loginPage;
	private CheckPage			checkPage;
	private SaveMoneyPage		saveMoneyPage;
	private GetMoneyPage		getMoneyPage;
	private ChangePassWordPage	changePassWordPage;
	private OptionPage			optionPage;
	private HomePage			homePage;
	private RegisterPage		registerPage;

	public CardPage() {
		// 卡片布局
		this.setLayout(new CardLayout());
		// 主页
		homePage = new HomePage(this);
		this.add(homePage, "homePage");
		// 登录
		loginPage = new LoginPage(this);
		this.add(loginPage, "loginPage");
		// 注册
		registerPage = new RegisterPage(this);
		this.add(registerPage, "registerPage");
		// 存钱
		saveMoneyPage = new SaveMoneyPage(this);
		this.add(saveMoneyPage, "saveMoneyPage");
		// 查询
		checkPage = new CheckPage(this, loginPage);
		this.add(checkPage, "checkPage");
		// 选择业务
		optionPage = new OptionPage(this);
		this.add(optionPage, "optionPage");

		// 改密码
		changePassWordPage = new ChangePassWordPage(this, loginPage);
		this.add(changePassWordPage, "changePassWordPage");
		// 取钱
		getMoneyPage = new GetMoneyPage(this);
		this.add(getMoneyPage, "getMoneyPage");

	}
}
