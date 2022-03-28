package aarhusBryghus.application.model;

import java.util.ArrayList;

public class Kunde {

    private String fornavn;
    private String efternavn;
    private int telefon;
    private String adresse;
    private int vejnummer;
    private int postnummer;
    private String by;
    private ArrayList<Ordre> ordrer = new ArrayList<>(); // todo: bør slettes pga. enkeltrettet

    public Kunde(String fornavn, String efternavn, int telefon) {
        this.fornavn = fornavn;
        this.efternavn = efternavn;
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

    public String getAdresse() {
        return adresse;
    }

    public int getVejnummer() {
        return vejnummer;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public String getBy() {
        return by;
    }

    public ArrayList<Ordre> getOrdrer() {
        return new ArrayList<>(ordrer);
    }

    @Override
    public String toString() {
        return fornavn + " " + efternavn + " Tlf: " + telefon;
    }
}
