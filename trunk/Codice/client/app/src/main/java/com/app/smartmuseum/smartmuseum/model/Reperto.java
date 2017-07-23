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
    private double peso;
    private String luogo_scoperta;
    private String data_scoperta;
    private String bibliografia;
    private String descrizione;
    private int id_multimedia;
    private String pubblicato;

    /**
     * costruttore parametrizzato
     * @param id
     * @param pubblicato
     * @param id_multimedia
     * @param descrizione
     * @param bibliografia
     * @param data_scoperta
     * @param luogo_scoperta
     * @param peso
     * @param data_acquisizione
     * @param dimensioni
     * @param valore
     * @param titolo
     * @param tipo
     * @param nome_autore
     */
    public Reperto(int id, String pubblicato, int id_multimedia, String descrizione, String bibliografia,
                   String data_scoperta, String luogo_scoperta, double peso, String data_acquisizione,
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

    /**
     * costruttore
     */
    public Reperto(){}

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
     * ritora la data acquisizione
     * @return
     */
    public String getData_acquisizione() {
        return data_acquisizione;
    }

    /**
     * setta la data acquisizione
     * @param data_acquisizione
     */
    public void setData_acquisizione(String data_acquisizione) {
        this.data_acquisizione = data_acquisizione;
    }

    /**
     * ritorna le dimensioni
     * @return
     */
    public String getDimensioni() {
        return dimensioni;
    }

    /**
     * setta le dimensioni
     * @param dimensioni
     */
    public void setDimensioni(String dimensioni) {
        this.dimensioni = dimensioni;
    }

    /**
     * ritorna il valore
     * @return
     */
    public float getValore() {
        return valore;
    }

    /**
     * setta il valore
     * @param valore
     */
    public void setValore(float valore) {
        this.valore = valore;
    }

    /**
     * ritorna il titolo
     * @return
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * setta il titolo
     * @param titolo
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * ritorna il tipo
     * @return
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * setta il tipo
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * ritorna il nome dell'autore
     * @return
     */
    public String getNome_autore() {
        return nome_autore;
    }

    /**
     * setta il nome dell'autore
     * @param nome_autore
     */
    public void setNome_autore(String nome_autore) {
        this.nome_autore = nome_autore;
    }

    /**
     * ritorna il peso
     * @return
     */
    public double getPeso() {
        return peso;
    }

    /**
     * setta il peso
     * @param peso
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * ritorna il luogo scoperta
     * @return
     */
    public String getLuogo_scoperta() {
        return luogo_scoperta;
    }

    /**
     * setta il luogo scoperta
     * @param luogo_scoperta
     */
    public void setLuogo_scoperta(String luogo_scoperta) {
        this.luogo_scoperta = luogo_scoperta;
    }

    /**
     * ritorna la data scoperta
     * @return
     */
    public String getData_scoperta() {
        return data_scoperta;
    }

    /**
     * setta la data scoperta
     * @param data_scoperta
     */
    public void setData_scoperta(String data_scoperta) {
        this.data_scoperta = data_scoperta;
    }

    /**
     * ritorna la bibliografia
     * @return
     */
    public String getBibliografia() {
        return bibliografia;
    }

    /**
     * setta la bibliografia
     * @param bibliografia
     */
    public void setBibliografia(String bibliografia) {
        this.bibliografia = bibliografia;
    }

    /**
     * ritorna la descrizione
     * @return
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * setta la descrizione
     * @param descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * ritorna l id' multimedia
     * @return
     */
    public int getId_multimedia() {
        return id_multimedia;
    }

    /**
     * setta l'id multimedia
     * @param id_multimedia
     */
    public void setId_multimedia(int id_multimedia) {
        this.id_multimedia = id_multimedia;
    }

    /**
     * controlla se pubblicato o meno
     * @return
     */
    public String getPubblicato() {
        return pubblicato;
    }

    /**
     * setta pubblicato o meno
     * @param pubblicato
     */
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
