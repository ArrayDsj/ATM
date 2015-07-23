import java.util.Scanner;

/**
 * 异常处理机制的使用
 */

class ATM {
    // 此刻ATM内的现金
    private int     cash;
    // 用户信息
    private UserInfo    theUser;
    // 定义一个数组,保存全部用户信息
    private UserInfo[]  allUsers;
    // ATM能保存的最大现金数
    public final int    MAX_CASH    = 100000;

    public ATM() {
        allUsers = new UserInfo[5];
        for(int i = 0 ; i < allUsers.length; i++){
            allUsers[i] = new UserInfo("zhang"+i,
                                        "12345"+i,
                                        "12345"+i,
                                        10000 + i * 1000)
        }
        cash = 50000;
    }

    // 运行--完成流程控制
    public void run() {
        this.welcome();
        boolean flag = this.login();

        if (flag) {
            while (true) {
                int choice = this.choiceMenu();
                switch (choice) {
                    case 1:
                        query();
                        break;
                    case 2:
                        getMoney();
                        break;
                    case 3:
                        saveMoney();
                        break;
                    case 4:
                        changPwd();
                        break;
                    case 5:
                        exit(0);
                        break;
                    default:
                        System.out.println("只能输入数字1-5，请重新输入！");
                }
            }
        } else {
            this.exit(1);
        }
    }

    // 欢迎
    private void welcome() {
        System.out.println("************************");
        System.out.println("**********欢**迎*********");
        System.out.println("**********使**用**********");
        System.out.println("*********爱存不存*********");
        System.out.println("**********银**行**********");
        System.out.println("************************");
        System.out.println("************************");
    }

    // 登录
    private boolean login() {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("请输入卡号：");
            String inputCardNum = scan.next();
            System.out.println("请输入密码：");
            String inputPwd = scan.next();
            //用String类型接收数据时,不需要用到异常处理
            for (int j = 0; j < allUsers.length; j++) {
                System.out.println(allUsers[j].getCardNum());
                if (inputCardNum.equals(this.allUsers[j].getCardNum()) &&
                        inputPwd.equals(this.allUsers[j].getPassword())) {
                    System.out.println("登录成功！");
                    // 知道是谁登录了系统
                    theUser = allUsers[j];
                    return true;
                }
            } // for循环体
            System.out.println("卡号或密码错误,您还有" + (2 - i) + "次机会！");
        }
        System.out.println("三次卡号密码输入错误，卡被没收！");
        return false;
    }

    // 选择菜单
    private int choiceMenu() {
        while(true){
            try{
                Scanner scan = new Scanner(System.in);
                System.out.println("请选择您要操作的业务：");
                System.out.println("1、查询余额；2、取款业务；3、存款业务；4、修改密码；5、退出");
                int choice = scan.nextInt();
                return choice;
            } catch(InputMismatchException e){
                    // e.printStackTrace();
                    // System.out.println(e.getMessage());
                    System.out.println("请输入1-5之间的数字,是否继续(Y/N)");
                    String s = new Scanner(System.in).next();
                    if (s.equals("Y") || s.equals("Y")) {
                        continue;
                    }
                    if (s.equals("N") || s.equals("n")) {
                        exit(0);
                    }
                    else{
                        System.out.println("输入错误,谢谢使用");
                        exit(0);
                    }
            } catch (Exception e) {
                    System.out.println("未知错误!请核实后与柜台人员联系");
                        exit(0);
            }
        }
    }


    // 查询
    private void query() {
        System.out.println("账户余额:"
                + theUser.getAccount() + "元.");
    }

    // 存钱
    private void saveMoney() {
        while (true) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("请输入你要存入的金额：");
                int inputMoney = scan.nextInt();// 如果在这里出现异常,则跳到catch语句,后面不执行;
                if (inputMoney < 0) {
                    System.out.println("你输入的钱是负数，无法操作！");
                    return;
                }
                if (inputMoney == 0) {
                    System.out.println("他妈你没钱还来存钱?滚粗!");
                    return;
                }
                if (inputMoney % 100 != 0) {
                    System.out.println("你输入的钱不是100的整数，无法操作！");
                    return;
                }
                if (this.cash + inputMoney > MAX_CASH) {
                    System.out.println("本机容量不足，无法操作！");
                    return;
                }
                this.cash += inputMoney;
                this.theUser.setAccount(this.theUser.getAccount() + inputMoney);
                System.out.println("存钱操作已经成功，谢谢！");
                return;
            } catch (InputMismatchException e) {
                System.out.println("输入错误,请不要输入字符或小数,是否继续(Y/N)");
                String s = new Scanner(System.in).next();
                if (s.equals("Y") || s.equals("Y")) {
                    continue;
                }
                if (s.equals("N") || s.equals("n")) {
                    exit(0);
                } else {
                    System.out.println("输入错误,谢谢使用");
                    exit(0);
                }
            }catch (Exception e) {
                System.out.println("未知错误!请核实后与柜台人员联系");
                exit(0);
            }
        }
    }

    // 取钱
    private void getMoney() {
        while(true){
            try{
                Scanner scan = new Scanner(System.in);
                System.out.println("请输入你要取出的金额：");
                int outputMoney = scan.nextInt();//只能输入整数
                if (outputMoney < 0) {
                    System.out.println("你输入的钱是负数，无法操作！");
                    return;
                }
                if (outputMoney == 0) {
                    System.out.println("不取钱你来干嘛,滚粗！");
                    return;
                }
                if (outputMoney % 100 != 0) {
                    System.out.println("你输入的钱不是100的整数，无法操作！");
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
                this.theUser.setAccount(this.theUser.getAccount() - outputMoney);
                this.cash -= outputMoney;
                System.out.println("取钱操作已经成功，请收好现金！");
                return;
            } catch (InputMismatchException e) {
                System.out.println("输入不能为字符或小数,是否继续(Y/N)");
                String s = new Scanner(System.in).next();
                if (s.equals("Y") || s.equals("y")) {
                    continue;
                }
                if (s.equals("N") || s.equals("n")) {
                    exit(0);
                } else {
                    System.out.println("输入错误,谢谢使用");
                    exit(0);
                }
            }catch (Exception e) {
                System.out.println("未知错误!请核实后与柜台人员联系");
                exit(0);
            }
        }
    }

    // 修改密码
    private void changPwd() {
        while (true) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("请输入原密码：");
                String oldPwd = scan.next();
                System.out.println("请输入新密码：");
                String newPwd = scan.next();
                System.out.println("请确认新密码：");
                String reNewPwd = scan.next();

                if (!oldPwd.equals(theUser.getPassword())) {
                    System.out.println("原密码输入错误！");
                    return;
                }
                if (!newPwd.equals(reNewPwd)) {
                    System.out.println("两次新密码输入不一致！");
                    return;
                }
                this.theUser.setPassword(newPwd);
                System.out.println("新密码设置成功！");
            } catch (InputMismatchException e) {
                System.out.println("输入不能为字符或小数,是否继续(Y/N)");
                String s = new Scanner(System.in).next();
                if (s.equals("Y") || s.equals("Y")) {
                    continue;
                }
                if (s.equals("N") || s.equals("n")) {
                    exit(0);
                } else {
                    System.out.println("输入错误,谢谢使用");
                    exit(0);
                }
            } catch (Exception e) {
                System.out.println("未知错误!请核实后与柜台人员联系");
                exit(0);
            }
        }
    }

    // 退出
    private void exit(int status) {
        if (status == 1) {
            System.out.println("本机停止为您服务，请去柜台操作处理！");
            System.exit(0);
        } else if (status == 0) {
            System.out.println("谢谢您的使用，期待下次为您服务！");
            System.exit(0);
        }
    }
}
