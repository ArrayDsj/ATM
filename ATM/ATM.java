package com.code.atm_10;

import java.text.SimpleDateFormat;
import java.util.Collection;
//使用正则表达式
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 这是一个模拟显示ATM机器的类。提供登录、查询、存款、取款等功能
 * 
 * @author HuYiKe
 * @version 1.0.1
 */
public class ATM {

	/**
	 * 代表ATM机器中存放的现金数量
	 */
	private int cash;// 现金

	private UserInfo theUser;// 当前操作用户(登录成功的人)

	HashMap<String, UserInfo> UserInfoMap = new HashMap<String, UserInfo>();

	public final int MAX_CASH = 100000;// 最大现金数

	// 用HashMap
	public ATM() {

		// 用卡号@密码做键
		// 然后使用containsKey判断是否有这个键
		UserInfoMap = FileOperation.ReadFile();
		this.cash = 50000;
		

	}

	// 运行--完成流程控制
	/**
	 * 本方法专门用于流程控制
	 */
	public void run() {
		this.welcome();
		boolean flag = this.login();

		if (flag) {
			new Load().loading();
			while (true) {
				int choice = this.choiceMenu();
				switch (choice) {
					case 1:
						this.query();
						break;
					case 2:
						this.getMoney();
						break;
					case 3:
						this.saveMoney();
						break;
					case 4:
						this.changPwd();
						break;
					case 5:
						this.exit(0);
						break;
					default:
						System.out.println("只能输入数字1-5，请重新输入！");
						break;
				}
			}
		} else {
			this.exit(1);
		}
	}

	// 欢迎
	private void welcome() {
		System.out.println("***************************");
		System.out.println("**********欢***迎***********");
		System.out.println("**********使***用***********");
		System.out.println("**********爱存不存***********");
		System.out.println("**********银***行***********");
		System.out.println("***************************");
	}

	// 登录
	private boolean login() {
		Scanner scan = new Scanner(System.in);

		for (int i = 0; i < 3; i++) {
			System.out.println("请输入卡号：");
			String inputCardNum = scan.next();
			System.out.println("请输入密码：");
			String inputPwd = scan.next();

			// 这里是用集合来装所有的元素
			// 也可以用Set来装所有的键,然后遍历键,从而找到对应的那个元素的密码,然后对比
			Collection<UserInfo> cl = UserInfoMap.values();
			for (UserInfo user : cl) {
				if (user.getCardNum().equals(inputCardNum)
						&& user.getPassword().equals(inputPwd)) {
					System.out.println("登录成功！");
					// 当更改theUser的时候,HashMap中的对象也会更改
					// 因为HashMap中存的只是引用,如果对象本身改变了,HashMap虽然没有改变,戴斯指向的对象改变了
					theUser = user;
					return true;
				}
			}

			System.out.println("您还有" + (2 - i) + "次机会！");
		}
		System.out.println("三次卡号密码输入错误，卡被没收！");
		return false;
	}

	// 选择菜单
	private int choiceMenu() {
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("请选择您要操作的业务：");
			System.out.println("1、查询余额；2、取款业务；3、存款业务；4、修改密码；5、退出");
			String choice = scan.next();
			boolean b = choice.matches("[1-5]{1,1}");
			if (b) {
				int a = Integer.parseInt(choice);
				return a;
			} else {
				System.out.println("错误,请输入1-5之间的正整数!");
				System.out.println("是否继续?(y/n)");
				String s = new Scanner(System.in).next();
				if (s.matches("[y]|[Y]")) {
					continue;
				}
				if (s.matches("(n|N)")) {
					exit(0);
				} else {
					System.out.println("输入错误,谢谢使用");
					exit(0);
				}
			}

		}

	}

	// 查询
	private void query() {
		System.out.println("您当前的余额是：" + this.theUser.getAccount() + "元。");
	}

	// 存钱
	private void saveMoney() {
		int money = 0;
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("请输入你要存入的金额：");
			String input = scan.next();
			int inputMoney;
			if (input.matches("(200|1[0-9]{2}|[1-9][0-9]?)00")) {
				inputMoney = Integer.parseInt(input);
			} else {
				System.out.println("输入错误");
				return;
			}

			if (this.cash + inputMoney > this.MAX_CASH) {
				System.out.println("本机容量不足，无法操作！");
				return;
			}
			this.cash += inputMoney;
			money += inputMoney;
			if (FileOperation.SaveFile(UserInfoMap)) {
				this.theUser.setAccount(this.theUser.getAccount() + inputMoney);
				System.out.println("存钱操作已经成功，是否继续操作?(y/n)");
				String conti = new Scanner(System.in).next();
				if (conti.matches("(y|Y)")) {
					continue;
				} else if (conti.matches("(n|N)")) {
					System.out.println("是否打印凭条?(y/n)");
					String sc = new Scanner(System.in).next();
					if (sc.matches("(y|Y)")) {
						print("存款金额:", money);
						break;
					} else if (sc.matches("(n|N)")) {
						exit(0);
					} else {
						System.out.println("输入错误,谢谢使用");
						exit(0);
					}
				} else {
					System.out.println("输入错误,谢谢使用");
					exit(0);
				}
			} else
				System.out.println("系统错误,请到柜台办理");
		}
	}

	// 取钱
	private void getMoney() {
		int money = 0;
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("请输入你要取出的金额：");
			String inputMoney = scan.next();
			int outputMoney;

			if (inputMoney.matches("(200|1[0-9]{2}|[1-9][0-9]?)00")) {
				outputMoney = Integer.parseInt(inputMoney);
			} else {
				System.out.println("输入错误");
				return;
			}

			if (this.theUser.getAccount() < outputMoney) {
				System.out.println("您账户的余额不足，无法操作！");
				return;
			}
			if (this.cash <= outputMoney) {
				System.out.println("本机现金不足，无法操作！");
				return;
			}
			this.cash -= outputMoney;
			money += outputMoney;
			if (FileOperation.SaveFile(UserInfoMap)) {
				this.theUser
					.setAccount(this.theUser.getAccount() - outputMoney);
				System.out.println("取钱操作成功,是否继续操作(y/n)");
				String conti = new Scanner(System.in).next();
				if (conti.matches("(y|Y)")) {
					continue;
				} else if (conti.matches("(n|N)")) {
					System.out.println("是否打印凭条?(y/n)");
					String sc = new Scanner(System.in).next();
					if (sc.trim().equalsIgnoreCase(sc)) {
						print("取款金额:", money);
						break;
					} else if (sc.matches("(n|N)")) {
						exit(0);
					} else {
						System.out.println("输入错误,谢谢使用");
						exit(0);
					}

				}
			} else
				System.out.println("系统错误,请到柜台办理");
		}
	}

	// 修改密码
	private void changPwd() {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入原密码：");
		String oldPwd = scan.next();
		System.out.println("请输入新密码：");
		String newPwd = scan.next();
		System.out.println("请确认新密码：");
		String reNewPwd = scan.next();

		if (!oldPwd.equals(this.theUser.getPassword())) {
			System.out.println("原密码输入错误！");
			return;
		}
		if (!newPwd.equals(reNewPwd)) {
			System.out.println("两次新密码输入不一致！");
			return;
		}
		if (FileOperation.SaveFile(UserInfoMap)) {
			this.theUser.setPassword(newPwd);
			System.out.println("新密码设置成功！");
		} else
			System.out.println("系统错误,请到柜台办理");

	}

	// 打印凭条
	private void print(String message, int money) {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy年MM月dd日hh点mm分ss秒 E");
		String date = sim.format(new Date());
		String s = "用户名:" + theUser.getUsername() + "\n" + message + money + "元"
				+ "\n" + "日期:" + date;
		System.out.println(s);
	}

	// 退出
	private void exit(int status) {
		if (status == 1) {
			System.out.println("本机停止为您服务，请去柜台操作处理！");
		} else if (status == 0) {
			System.out.println("谢谢您的使用，期待下次为您服务！");
		}
		System.exit(0);
	}
}