package com.llav_ad.zv_t.ent.dbData;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_info")
public class UserInfo {

    @Ignore
    public UserInfo () {}
    @Ignore
    public UserInfo (String webViewUrl, Boolean auth, String ip) {
        this.webViewUrl = webViewUrl;
        this.auth = auth;
        this.ip = ip;
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
    public String ip;
    public String login;
    public String pass;
    public String email;
    public String phone;
    public String gender;
    public String age;
    public String webViewUrl;
    public Boolean auth;
}
