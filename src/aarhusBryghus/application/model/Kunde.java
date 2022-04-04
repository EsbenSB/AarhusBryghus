package aarhusBryghus.application.model;

import java.io.Serializable;

public class Kunde implements Serializable {

    private String fornavn;
    private String efternavn;
    private int telefon;

    public Kunde(String fornavn, String efternavn, int telefon) {
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.telefon = telefon;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public int getTelefon() {
        return telefon;
    }

    @Override
    public String toString() {
        return fornavn + " " + efternavn + " Tlf: " + telefon;
    }
}
