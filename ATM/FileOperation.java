package com.code.atm_10;

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
			pro.load(new FileInputStream("UserInfo.properties"));
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
			hap.put(tmp[1],
				new UserInfo(tmp[0], tmp[1], tmp[2], Float.parseFloat(tmp[3])));
		}
		return hap;
	}

	public static boolean SaveFile(HashMap<String, UserInfo> UserInfo) {
		Properties pro = new Properties();
		//修改元素,存入HashMap()
		Collection<UserInfo> col = UserInfo.values();
		
		for (UserInfo theUser : col) {
			pro.setProperty(theUser.getCardNum(),
				theUser.getUsername() + "@" + theUser.getCardNum() + "@"
						+ theUser.getPassword() + "@" + theUser.getAccount());
		}
		try {
			pro.store(new FileOutputStream("UserInfo.properties"), null);
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
