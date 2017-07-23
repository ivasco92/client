package com.app.smartmuseum.smartmuseum.model;

/**
 * Created by PaoloPepe on 20/07/2017.
 */

public class TipoMultimedia {
    private int id;
    private String nome;

    /**
     * costruttore parametrizzato
     * @param id
     * @param nome
     */
    public TipoMultimedia(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    /**
     * costruttore tramite id
     * @param id
     */
    public TipoMultimedia(int id){
        if(id==1) {
            this.id = id;
            this.nome = "audio";
        }
        else if(id==2) {
            this.id = id;
            this.nome = "video";
        }
        else if(id==3) {
            this.id = id;
            this.nome = "foto";
        }
    }

    /**
     * costruttore
     */
    public TipoMultimedia(){}

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
     * ritorna il nome
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * setta il nome
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "TipoMultimedia{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
