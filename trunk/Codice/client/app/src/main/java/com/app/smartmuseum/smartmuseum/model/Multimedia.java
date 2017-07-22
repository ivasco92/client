package com.app.smartmuseum.smartmuseum.model;

/**
 * Created by PaoloPepe on 20/07/2017.
 */

public class Multimedia {
    private int id;
    private String url;

    public Multimedia(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public Multimedia(){}

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

    @Override
    public String toString() {
        return "Multimedia{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
