package com.llav_ad.zv_t.dbNet.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.llav_ad.zv_t.ent.dbData.ProfileInfo;
import com.llav_ad.zv_t.ent.dbData.UrlItem;
import com.llav_ad.zv_t.ent.dbData.UserInfo;

@Database(entities = {UserInfo.class, ProfileInfo.class, UrlItem.class}, version = 8, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
