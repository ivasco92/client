package com.app.smartmuseum.smartmuseum.model;

/**
 * Created by PaoloPepe on 20/07/2017.
 */

public class Reperto {
    private int id;
    private String data_acquisizione;
    private String dimensioni;
    private float valore;
    private String titolo;
    private String tipo;
    private String nome_autore;
    private int peso;
    private String luogo_scoperta;
    private String data_scoperta;
    private String bibliografia;
    private String descrizione;
    private int id_multimedia;
    private String pubblicato;

    public Reperto(int id, String pubblicato, int id_multimedia, String descrizione, String bibliografia,
                   String data_scoperta, String luogo_scoperta, int peso, String data_acquisizione,
                   String dimensioni, float valore, String titolo, String tipo, String nome_autore) {
        this.id = id;
        this.pubblicato = pubblicato;
        this.id_multimedia = id_multimedia;
        this.descrizione = descrizione;
        this.bibliografia = bibliografia;
        this.data_scoperta = data_scoperta;
        this.luogo_scoperta = luogo_scoperta;
        this.peso = peso;
        this.data_acquisizione = data_acquisizione;
        this.dimensioni = dimensioni;
        this.valore = valore;
        this.titolo = titolo;
        this.tipo = tipo;
        this.nome_autore = nome_autore;
    }

    public Reperto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData_acquisizione() {
        return data_acquisizione;
    }

    public void setData_acquisizione(String data_acquisizione) {
        this.data_acquisizione = data_acquisizione;
    }

    public String getDimensioni() {
        return dimensioni;
    }

    public void setDimensioni(String dimensioni) {
        this.dimensioni = dimensioni;
    }

    public float getValore() {
        return valore;
    }

    public void setValore(float valore) {
        this.valore = valore;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome_autore() {
        return nome_autore;
    }

    public void setNome_autore(String nome_autore) {
        this.nome_autore = nome_autore;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getLuogo_scoperta() {
        return luogo_scoperta;
    }

    public void setLuogo_scoperta(String luogo_scoperta) {
        this.luogo_scoperta = luogo_scoperta;
    }

    public String getData_scoperta() {
        return data_scoperta;
    }

    public void setData_scoperta(String data_scoperta) {
        this.data_scoperta = data_scoperta;
    }

    public String getBibliografia() {
        return bibliografia;
    }

    public void setBibliografia(String bibliografia) {
        this.bibliografia = bibliografia;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getId_multimedia() {
        return id_multimedia;
    }

    public void setId_multimedia(int id_multimedia) {
        this.id_multimedia = id_multimedia;
    }

    public String getPubblicato() {
        return pubblicato;
    }

    public void setPubblicato(String pubblicato) {
        this.pubblicato = pubblicato;
    }

    @Override
    public String toString() {
        return "Reperto{" +
                "id=" + id +
                ", data_acquisizione='" + data_acquisizione + '\'' +
                ", dimensioni='" + dimensioni + '\'' +
                ", valore=" + valore +
                ", titolo='" + titolo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", nome_autore='" + nome_autore + '\'' +
                ", peso=" + peso +
                ", luogo_scoperta='" + luogo_scoperta + '\'' +
                ", data_scoperta='" + data_scoperta + '\'' +
                ", bibliografia='" + bibliografia + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", id_multimedia=" + id_multimedia +
                ", pubblicato='" + pubblicato + '\'' +
                '}';
    }
}
