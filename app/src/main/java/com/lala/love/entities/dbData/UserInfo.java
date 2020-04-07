package com.lala.love.entities.dbData;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_info")
public class UserInfo {


    public UserInfo () {}

    public UserInfo (String webViewUrl, Boolean auth) {
        this.webViewUrl = webViewUrl;
        this.auth = auth;
    }

    public UserInfo(int id, String login, String pass, String email, String phone, String gender, String age, Boolean auth) {
        this.id = id;
        this.pass = pass;
        this.login = login;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.auth = auth;
    }

    @PrimaryKey
    public int id;
    public String login;
    public String pass;
    public String email;
    public String phone;
    public String gender;
    public String age;
    public String webViewUrl;
    public Boolean auth;
}
