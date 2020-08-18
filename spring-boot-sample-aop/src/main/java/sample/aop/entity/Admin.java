package sample.aop.entity;

public class Admin {
    private String account;

    public String getAccount() {
        return account;
    }

    public Admin() {
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Admin(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;



}
