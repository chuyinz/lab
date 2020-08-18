package org.jboss.as.quickstarts.ejb.remote.entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;


import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigInteger;

@Entity  //表明这是一个实体Bean
@Table (name = "admin" ) //和数据库表admin 建立映射
public class Admin implements Serializable{
    @Id // 表明是该实体的id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id生成策略
    @Column(name = "id" )//对应data表id字段
    private BigInteger id; // 数据编号

    @Column(name = "account" ) // 对应data表account字段
    private String account; // 用户账号

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public Admin() {
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(String account, String password) {
        this.account = account;
        this.password = password;
    }

    @Column(name = "password" ) // 对应data表password字段
    private String password; // 用户密码



}
