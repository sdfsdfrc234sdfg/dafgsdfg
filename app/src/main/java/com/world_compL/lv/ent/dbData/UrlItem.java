package com.world_compL.lv.ent.dbData;

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

    public UrlItem(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public UrlItem(String url) {
        this.url = url;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String url;
}
