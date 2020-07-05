package com.llav_ad.zv_t;

import android.app.Application;
import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.llav_ad.zv_t.dbNet.database.AppDatabase;
import com.llav_ad.zv_t.dbNet.net.Net;
import com.llav_ad.zv_t.dbNet.net.NetImpl;
import com.llav_ad.zv_t.ent.sev_resp.ServerResponse;
import com.llav_ad.zv_t.other_files.AppData;

public class MyApplication extends Application {

    public static MyApplication instance;
    private AppDatabase database;
    private Net net;
    private MutableLiveData<ServerResponse> serverResponseLiveData;
    private AppData appData;
    public FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, null);

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