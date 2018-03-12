package com.lixinxin.imageproject.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by android on 2018/3/9.
 */

@Entity(tableName = "user", indices = {@Index(value = {"userId"}, unique = true)})
public class User {

    @PrimaryKey/*(autoGenerate = true)*/
    private long userId;
    private String name;
    private String password;
    private int age;
    private String address;
    private boolean sex;
    private String phone;
    @Ignore  //忽略
    private String like;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", like='" + like + '\'' +
                '}';
    }
}
