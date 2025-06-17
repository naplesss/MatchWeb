package org.example.matcheweb.pojos;

public class Squadra {
    private int id;
    private String nomesquadra;
    private String sport;


    public Squadra(int id, String nomesquadra, String sport) {
        this.id = id;
        this.nomesquadra = nomesquadra;
        this.sport = sport;
    }

    public Squadra() {
    this.id=0;
    this.nomesquadra="";
    this.sport="";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomesquadra() {
        return nomesquadra;
    }

    public void setNomesquadra(String nomesquadra) {
        this.nomesquadra = nomesquadra;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}