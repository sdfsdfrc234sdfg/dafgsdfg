package com.llav_ad.zv_t.other_files;

import com.llav_ad.zv_t.MyApplication;
import com.llav_ad.zv_t.ent.dbData.ProfileInfo;
import com.llav_ad.zv_t.ent.dbData.UrlItem;
import com.llav_ad.zv_t.ent.dbData.UserInfo;

public class DbRequest {

    public static void addUser(UserInfo userInfo) {

        Thread newThread = new Thread(() -> {
            MyApplication.getInstance().getDatabase().userDao().addUser(userInfo);
        });
        newThread.start();
    }
    public static void delete(UserInfo userInfo) {

        Thread newThread = new Thread(() -> {
            MyApplication.getInstance().getDatabase().userDao().delete(userInfo);
        });
        newThread.start();
    }
    public static void update(UserInfo userInfo) {

        Thread newThread = new Thread(() -> {
            MyApplication.getInstance().getDatabase().userDao().updateUserInfo(userInfo);
        });
        newThread.start();
    }

    public static void addProfileInfo(ProfileInfo profileInfo) {

        Thread newThread = new Thread(() -> {
            MyApplication.getInstance().getDatabase().userDao().addProfileInfo(profileInfo);
        });
        newThread.start();
    }
    public static void deleteProfileInfo(ProfileInfo profileInfo) {

        Thread newThread = new Thread(() -> {
            MyApplication.getInstance().getDatabase().userDao().deleteProfileInfo(profileInfo);
        });
        newThread.start();
    }
    public static void updateProfileInfo(ProfileInfo profileInfo) {

        Thread newThread = new Thread(() -> {
            MyApplication.getInstance().getDatabase().userDao().updateProfileInfo(profileInfo);
        });
        newThread.start();
    }

    public static void addUrl(UrlItem item) {
        Thread newThread = new Thread(() -> {
            MyApplication.getInstance().getDatabase().userDao().addUrlToHistory(item);
        });
        newThread.start();
    }

    public static void deleteUrl() {
        Thread newThread = new Thread(() -> {
            MyApplication.getInstance().getDatabase().userDao().deleteLastUrl();
        });
        newThread.start();
    }
}
