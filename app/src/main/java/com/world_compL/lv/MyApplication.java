package com.world_compL.lv;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.world_compL.lv.dbNet.database.AppDatabase;
import com.world_compL.lv.dbNet.net.Net;
import com.world_compL.lv.dbNet.net.NetImpl;
import com.world_compL.lv.ent.sev_resp.ServerResponse;
import com.world_compL.lv.other_files.AppData;

public class MyApplication extends Application {

    public static MyApplication instance;
    private AppDatabase database;
    private Net net;
    private MutableLiveData<ServerResponse> serverResponseLiveData;
    private AppData appData;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        serverResponseLiveData = new MutableLiveData<>();
        net = new NetImpl(serverResponseLiveData, this);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {

        if (database == null) {
            database = Room.databaseBuilder(this, AppDatabase.class, "database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public MutableLiveData<ServerResponse> getServerResponseLiveData() {
        return serverResponseLiveData;
    }

    public AppData getAppData() {

        if (appData == null) {
            appData = new AppData();
        }
        return appData;
    }

    public void request() {
        net.createRequest();
    }
}