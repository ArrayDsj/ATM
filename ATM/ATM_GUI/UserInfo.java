package com.code.atm_gui;



public class UserInfo {

	private String username;// 用户名



	private String password;// 密码



	public UserInfo() {

	}

	public UserInfo(String username,
			String password) {
		this.username = username;

		this.password = password;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}

