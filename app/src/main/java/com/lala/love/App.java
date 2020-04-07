package com.lala.love;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.lala.love.data.database.AppDatabase;
import com.lala.love.data.net.Net;
import com.lala.love.data.net.NetImpl;
import com.lala.love.entities.server_response.ServerResponse;
import com.lala.love.util.AppData;

public class App extends Application {

    public static App instance;
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

    public static App getInstance() {
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