package org.jboss.as.quickstarts.ejb.remote.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.Year;
import java.util.Date;

@Entity  //表明这是一个实体Bean
@Table(name = "alumni" ) //和数据库表alumin 建立映射
public class Alumni implements Serializable {
    @Id // 表明是该实体的id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id生成策略
    @Column(name = "id" )//对应data表id字段
    private BigInteger id; // 数据编号

    @Column(name = "name" )
    private String name;

    @Column(name = "sex" )
    private Integer sex;

    @Column(name="birthday")
    private String date;

    @Column(name="enrollment_year")
    private String year;

    @Column(name="work_area")
    private String work_area;

    @Column(name="work_address")
    private String work_address;

    @Column(name="position")
    private String position;

    @Column(name="phone")
    private String phone;

    public Alumni(String name, Integer sex, String date, String year, String work_area, String work_address, String position, String phone, String email, String weixin) {
        this.name = name;
        this.sex = sex;
        this.date = date;
        this.year = year;
        this.work_area = work_area;
        this.work_address = work_address;
        this.position = position;
        this.phone = phone;
        this.email = email;
        this.weixin = weixin;
    }

    @Column(name="email")

    private String email;

    @Column(name="weixin")
    private String weixin;

    public Alumni() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWork_area() {
        return work_area;
    }

    public void setWork_area(String work_area) {
        this.work_area = work_area;
    }

    public String getWork_address() {
        return work_address;
    }

    public void setWork_address(String work_address) {
        this.work_address = work_address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }







}
