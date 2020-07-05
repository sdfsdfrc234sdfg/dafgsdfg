package com.llav_ad.zv_t.dbNet.net;

import com.llav_ad.zv_t.ent.CallRez;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface Api {

    @GET("failik.php")
    Call<CallRez> call(@Header("User-Agent") String userAgent, @Header("Accept-Language") String lang);
}
