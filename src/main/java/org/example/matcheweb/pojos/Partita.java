package org.example.matcheweb.pojos;

import java.time.LocalDate;
import java.util.Random;

public class Partita {

    private int id;
    private String id_squadracasa;
    private String id_squadrafuori;
    private String giorno;
    private int risultato;
    private String sport;

    public Partita() {
        this.id=0;
        this.id_squadracasa="";
        this.id_squadrafuori="";
        this.giorno="";
        this.risultato=0;
        this.sport="";
    }
    public Partita(String id_squadracasa, String id_squadrafuori, String sport) {
        this.id=0;
        this.id_squadracasa = id_squadracasa;
        this.id_squadrafuori = id_squadrafuori;
        this.giorno = LocalDate.now().toString();
        this.sport = sport;
        Random random = new Random();
        this.risultato = random.nextInt(3);
    }
    public Partita(int id, String id_squadracasa, String id_squadrafuori, String giorno, int risultato, String sport) {
        this.id = id;
        this.id_squadracasa = id_squadracasa;
        this.id_squadrafuori = id_squadrafuori;
        this.giorno = giorno;
        this.risultato = risultato;
        this.sport = sport;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_squadracasa() {
        return id_squadracasa;
    }

    public void setId_squadracasa(String id_squadracasa) {
        this.id_squadracasa = id_squadracasa;
    }

    public String getId_squadrafuori() {
        return id_squadrafuori;
    }

    public void setId_squadrafuori(String id_squadrafuori) {
        this.id_squadrafuori = id_squadrafuori;
    }

    public String getGiorno() {
        return giorno;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

    public int getRisultato() {
        return risultato;
    }

    public void setRisultato(int risultato) {
        this.risultato = risultato;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}


