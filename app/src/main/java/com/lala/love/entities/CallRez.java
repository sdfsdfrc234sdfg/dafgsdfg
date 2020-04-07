package com.lala.love.entities;

import com.google.gson.annotations.SerializedName;

public class CallRez {

    @SerializedName("url")
    private String url;
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
