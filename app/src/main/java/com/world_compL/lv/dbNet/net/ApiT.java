package com.world_compL.lv.dbNet.net;

import com.world_compL.lv.ent.CallRez;
import com.world_compL.lv.ent.CallRezT;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiT {

    @GET("?format=json")
    Call<CallRezT> call();
}
