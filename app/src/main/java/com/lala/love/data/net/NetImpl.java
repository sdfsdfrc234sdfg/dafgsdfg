package com.lala.love.data.net;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.lala.love.entities.CallRez;
import com.lala.love.entities.server_response.ServerResponse;
import com.lala.love.entities.server_response.ServerResponseSuccess;

import java.util.Locale;

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
            .baseUrl("https://id249.whitelist.world/")
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

        api.call(UserAgent.getInstance(context).getUserAgentString(Version.userAgent()), Locale.getDefault().toLanguageTag()).enqueue(new Callback<CallRez>() {
            @Override
            public void onResponse(Call<CallRez> call, Response<CallRez> response) {
                Log.d("REQQQest", response.body().getUrl());
                liveData.postValue(new ServerResponseSuccess(response.body().getUrl()));
            }

            @Override
            public void onFailure(Call<CallRez> call, Throwable t) {
                Log.d("REQQQest", t.getMessage());
                liveData.postValue(new ServerResponseSuccess());
            }
        });
    }

    private OkHttpClient createHttp() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);
        /*httpClient.addInterceptor(chain -> {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("User-Agent", UserAgent.getInstance(context).getUserAgentString(Version.userAgent()))
                    .header("Accept-Language", Locale.getDefault().toLanguageTag())
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        });*/

        return httpClient.build();
    }
}
