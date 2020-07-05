package com.llav_ad.zv_t.ent;

import com.google.gson.annotations.SerializedName;

public class CallRez {

    @SerializedName("url")
    private String url;
    @SerializedName("ip")
    private String ip;


    public void setIp(String ip) {this.ip = ip;}
    public String getIp() {return this.ip;}
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
