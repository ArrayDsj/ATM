import java.util.Scanner;

class ATM {
    // 此刻ATM内的现金
    private int     cash;
    // 用户信息
    private User    theUser;
    // 定义一个数组,保存全部用户信息
    private User[]  allUsers;
    // ATM能保存的最大现金数
    public final int    MAX_CASH    = 100000;

    public ATM() {
        allUsers = new User[5];
        // 用atm构造器new一个用户信息,然后用户输入信息来跟这个用户信息对比验证
        allUsers[0] = new User("张三", "1", "10", 20000);
        allUsers[1] = new User("李四", "2", "20", 30000);
        allUsers[2] = new User("王五", "3", "30", 40000);
        allUsers[3] = new User("赵六", "4", "40", 50000);
        allUsers[4] = new User("田七", "5", "50", 60000);
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
                        System.out.println("输入错误,请重新输入");
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
            //System.out.println(allUsers.length);
            for (int j = 0; j < allUsers.length; j++) {
                System.out.println(allUsers[j].getCardNum());
                if (inputCardNum.equals(this.allUsers[j].getCardNum()) &&
                        inputPwd.equals(this.allUsers[j].getPassword())) {
                    System.out.println("登录成功！");
                    // 知道是谁登录了系统
                    theUser = allUsers[j];
                    // index = j;
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
        Scanner scan = new Scanner(System.in);
        System.out.println("请选择您要操作的业务：");
        System.out.println("1、查询余额；2、取款业务；3、存款业务；4、修改密码；5、退出");
        int choice = scan.nextInt();
        return choice;
    }

    // 查询
    private void query() {
        System.out.println("用户名:" + theUser.getUsername() + "\n" + "账户余额:"
                + theUser.getAccount());
    }

    // 存钱 准备存进ATM的钱加上ATM现有的钱如果大于了MIX,则....
    private void saveMoney() {
        System.out.println("请输入存款金额:");
        Scanner saveNum = new Scanner(System.in);
        int money = saveNum.nextInt();
        if ((money + cash) > this.MAX_CASH) {
            System.out.println("土豪,存太多了,装不下了,去柜台吧");
        } else {
            theUser.setAccount(theUser.getAccount() + money);
            cash += money;
        }
    }

    // 取钱
    private void getMoney() {
        System.out.println("请输入取款金额:");
        Scanner getNum = new Scanner(System.in);
        int money = getNum.nextInt();
        float temp = theUser.getAccount();
        // 如果用户卡里面的钱比ATM机里面的钱多
        if (temp > cash) {
            temp = cash;
            if (money > temp) {
                System.out.println("对不起,ATM余额不足,请到柜台办理业务");
            } else {
                System.out.println("请拿走现金...");
                theUser.setAccount(theUser.getAccount() - money);
                cash -= money;
            }
        } else {// 卡里的钱比ATM少
            if (money > theUser.getAccount()) {
                System.out.println("对不起,账户余额不足");
            } else {
                System.out.println("请拿走现金...");
                theUser.setAccount(theUser.getAccount() - money);
                cash -= money;
            }
        }
    }

    // 修改密码
    private void changPwd() {
        String temp;
        temp = theUser.getPassword();
        int count = 0;
        while (true) {
            if (count == 3) {
                System.out.println("次数超过3次,密码修改不成功,请到柜台办理");
                break;
            }
            System.out.println("请输入旧密码:");
            Scanner sc = new Scanner(System.in);

            if (sc.next().equals(theUser.getPassword())) {
                System.out.println("请输入新密码");
                String newPwd = sc.next();
                System.out.println("请再次输入密码");
                Scanner repeatnewPwd = new Scanner(System.in);
                String repeat = repeatnewPwd.next();
                if (repeat.equals(newPwd)) {
                    System.out.println("密码修改成功");
                    theUser.setPassword(repeat);
                    break;
                } else {
                    System.out.println("重复密码不正确,请重新输入");
                    count++;
                }
            } else {
                System.out.println("密码不正确,请重新输入");
                count++;
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
