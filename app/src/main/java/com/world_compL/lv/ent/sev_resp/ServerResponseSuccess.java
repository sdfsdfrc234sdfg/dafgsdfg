package com.world_compL.lv.ent.sev_resp;

public class ServerResponseSuccess implements ServerResponse {

    public ServerResponseSuccess() {}
    public ServerResponseSuccess(String  url) {
        this.url = url;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
