package com.app.smartmuseum.smartmuseum.model;

/**
 * Created by PaoloPepe on 20/07/2017.
 */

public class TipoMultimedia {
    private int id;
    private String nome;

    public TipoMultimedia(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
