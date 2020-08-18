package sample.aop.entity;

import java.math.BigInteger;

public class Alumni {
    private BigInteger id; // Êý¾Ý±àºÅ

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    private String birthday;

    public Alumni(BigInteger id) {
        this.id = id;
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

    public Alumni() {
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


    private String name;


    private Integer sex;





    private String year;

    public Alumni(BigInteger id,  String name, Integer sex,String birthday, String year, String work_area, String work_address, String position, String phone, String email, String weixin) {
        this.id = id;
        this.birthday = birthday;
        this.name = name;
        this.sex = sex;
        this.year = year;
        this.work_area = work_area;
        this.work_address = work_address;
        this.position = position;
        this.phone = phone;
        this.email = email;
        this.weixin = weixin;
    }

    private String work_area;


    private String work_address;


    private String position;


    private String phone;

    private String email;


    private String weixin;
}
