package aarhusBryghus.application.model;

import java.util.ArrayList;

public class Prisliste {
    private String navn;
    private ArrayList<Pris> priser = new ArrayList<>();

    public Prisliste(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public ArrayList<Pris> getPriser() {
        return priser;
    }

    public void createPris(Produkt produkt, double varePris, int antalKlip){
        Pris pris = new Pris(varePris,produkt, this);
        if(antalKlip > 0){
            pris.setKlippekortPris(antalKlip);
        }
        priser.add(pris);
    }

    @Override
    public String toString() {
        return "Prisliste{" +
                "navn='" + navn + '\'' +
                '}';
    }
}
