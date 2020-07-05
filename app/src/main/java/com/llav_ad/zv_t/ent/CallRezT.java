package com.llav_ad.zv_t.ent;

import com.google.gson.annotations.SerializedName;

public class CallRezT {

    @SerializedName("ip")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
