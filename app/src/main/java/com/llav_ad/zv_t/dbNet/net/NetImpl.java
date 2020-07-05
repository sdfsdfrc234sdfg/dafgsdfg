package com.llav_ad.zv_t.dbNet.net;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.llav_ad.zv_t.MyApplication;
import com.llav_ad.zv_t.ent.CallRez;
import com.llav_ad.zv_t.ent.sev_resp.ServerResponse;
import com.llav_ad.zv_t.ent.sev_resp.ServerResponseError;
import com.llav_ad.zv_t.ent.sev_resp.ServerResponseSuccess;

import java.util.Locale;
import java.util.Objects;

import cz.ackee.useragent.UserAgent;
import okhttp3.OkHttpClient;
import okhttp3.internal.Version;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetImpl implements Net {


    MutableLiveData<ServerResponse> liveData;
    private Context context;
    private Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl("https://id283.whitelist.world/")
            .client(createHttp())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private Api api = retrofit.create(Api.class);

    public NetImpl(MutableLiveData<ServerResponse> liveData, Context context) {
        this.liveData = liveData;
        this.context = context;
    }

    @Override
    public void createRequest() {

        api.call(UserAgent.getInstance(context).getUserAgentString(Version.userAgent), Locale.getDefault().toLanguageTag()).enqueue(new Callback<CallRez>() {

            @Override
            public void onResponse(@NonNull Call<CallRez> call, @NonNull Response<CallRez> response) {

                assert response.body() != null;
                liveData.postValue(new ServerResponseSuccess(response.body().getUrl(), response.body().getIp()));
            }

            @Override
            public void onFailure(@NonNull Call<CallRez> call, @NonNull Throwable t) {


                Bundle bundle = new Bundle();
                bundle.putString("error_message", t.getMessage());
                MyApplication.getInstance().mFirebaseAnalytics.logEvent("response_error", bundle);
                liveData.postValue(new ServerResponseError());
            }
        });
    }

    private OkHttpClient createHttp() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);

        return httpClient.build();
    }
}
