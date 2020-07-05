package com.llav_ad.zv_t.ent.dbData;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "url_item")
public class UrlItem {


    public UrlItem() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {return this.ip;}

    public UrlItem(int id, String url, String ip) {
        this.id = id;
        this.url = url;
        this.ip = ip;
    }

    public UrlItem(String url, String ip) {
        this.url = url;
        this.ip = ip;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String url;
    public String ip;
}
