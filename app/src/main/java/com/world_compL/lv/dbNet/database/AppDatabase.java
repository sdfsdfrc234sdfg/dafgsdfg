package com.world_compL.lv.dbNet.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.world_compL.lv.ent.dbData.ProfileInfo;
import com.world_compL.lv.ent.dbData.UrlItem;
import com.world_compL.lv.ent.dbData.UserInfo;

@Database(entities = {UserInfo.class, ProfileInfo.class, UrlItem.class}, version = 8, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
