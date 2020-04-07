package com.lala.love.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.lala.love.entities.dbData.ProfileInfo;
import com.lala.love.entities.dbData.UserInfo;

@Database(entities = {UserInfo.class, ProfileInfo.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
