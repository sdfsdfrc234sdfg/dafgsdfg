package com.llav_ad.zv_t.dbNet.net;

import com.llav_ad.zv_t.ent.CallRezT;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiT {

    @GET("?format=json")
    Call<CallRezT> call();
}
