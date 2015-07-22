public class UserInfo {
    private String username;// 用户名
    private String cardNum;// 卡号
    private String password;// 密码
    private float account;// 账户余额
    public UserInfo() {

    }

    public UserInfo(String username,
            String cardNum,
            String password,
            float account) {
        this.username = username;
        this.cardNum = cardNum;
        this.password = password;
        this.account = account;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getCardNum() {
        return cardNum;
    }
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public float getAccount() {
        return account;
    }
    public void setAccount(float account) {
        this.account = account;
    }
}
