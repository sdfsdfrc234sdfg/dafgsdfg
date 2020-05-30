package com.world_compL.lv.other_files;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.world_compL.lv.dbNet.net.ApiT;
import com.world_compL.lv.ent.CallRezT;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetApi {

    public MutableLiveData<CallRezT> liveData;

    public GetApi() {
        liveData = new MutableLiveData<>();
        createRequest();
    }

    private Context context;

    private Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl("https://api.ipify.org")
            .client(createHttp())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private ApiT api = retrofit.create(ApiT.class);

    public void createRequest() {

        api.call().enqueue(new Callback<CallRezT>() {
            @Override
            public void onResponse(Call<CallRezT> call, Response<CallRezT> response) {
                liveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<CallRezT> call, Throwable t) {
                liveData.postValue(null);
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
