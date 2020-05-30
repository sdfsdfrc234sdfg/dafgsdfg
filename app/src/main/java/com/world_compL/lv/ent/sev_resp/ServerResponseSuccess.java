package com.world_compL.lv.ent.sev_resp;

public class ServerResponseSuccess implements ServerResponse {

    public ServerResponseSuccess() {}
    public ServerResponseSuccess(String  url, String ip) {
        this.url = url;
        this.ip = ip;
    }

    private String url;
    private String ip;

    public String getUrl() {
        return url;
    }

    public String getIp() {return ip;}

    public void setIp(String ip) {this.ip = ip;}

    public void setUrl(String url) {
        this.url = url;
    }
}
