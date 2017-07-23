package com.app.smartmuseum.smartmuseum.model;

/**
 * Created by PaoloPepe on 20/07/2017.
 */

public class Multimedia {
    private int id;
    private String url;

    /**
     * costruttore parametrizzato
     * @param id
     * @param url
     */
    public Multimedia(int id, String url) {
        this.id = id;
        this.url = url;
    }

    /**
     * costruttore
     */
    public Multimedia(){}

    /**
     * ritorna l'id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * setta l'id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * ritorna l'url
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * setta l'url
     * @param url
     */
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
