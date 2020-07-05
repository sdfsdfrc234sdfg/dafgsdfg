package com.llav_ad.zv_t.dbNet.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.llav_ad.zv_t.ent.dbData.ProfileInfo;
import com.llav_ad.zv_t.ent.dbData.UrlItem;
import com.llav_ad.zv_t.ent.dbData.UserInfo;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user_info WHERE id = 0 LIMIT 1")
    LiveData<UserInfo> getUserData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(UserInfo userInfo);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUserInfo(UserInfo userInfo);

    @Delete
    void delete(UserInfo userInfo);

    @Query("SELECT * FROM profileinfo WHERE id = 0 LIMIT 1")
    LiveData<ProfileInfo> getProfileInfo();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProfileInfo(ProfileInfo profileInfo);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateProfileInfo(ProfileInfo profileInfo);

    @Delete
    void deleteProfileInfo(ProfileInfo profileInfo);

    @Insert
    void addUrlToHistory(UrlItem urlItem);

    @Delete
    void deleteUrlItem(UrlItem urlItem);

    @Query("DELETE FROM url_item WHERE id=(SELECT MAX(id) FROM url_item)")
    void deleteLastUrl();

    @Query("SELECT * FROM url_item ORDER BY id DESC LIMIT 1")
    LiveData<UrlItem> getLastElement();
}
