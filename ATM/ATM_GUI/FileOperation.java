package com.code.atm_gui;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;

public class FileOperation {
	public static HashMap<String, UserInfo> ReadFile() {
		HashMap<String, UserInfo> hap = new HashMap<String, UserInfo>();
		Properties pro = new Properties();
		try {
			pro.load(new FileInputStream("user.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 分解字符串 组装为UserInfo,存入到HashMap中
		Collection col = pro.values();
		for (Object info : col) {
			String str = (String) info;
			String[] tmp = str.split("@");
			hap.put(tmp[0],
				new UserInfo(tmp[0], tmp[1]));
		}
		return hap;
	}

	public static boolean SaveFile(HashMap<String, UserInfo> UserInfo) {
		Properties pro = new Properties();
		//修改元素,存入HashMap()
		Collection<UserInfo> col = UserInfo.values();
		
		for (UserInfo theUser : col) {
			pro.setProperty(theUser.getUsername(),
				theUser.getUsername() + "@" + theUser.getPassword());
		}
		try {
			pro.store(new FileOutputStream("user.properties"), null);
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
