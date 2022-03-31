package aarhusBryghus.application.model;

import aarhusBryghus.storage.Storage;

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

    public int getAntalKlip(Produkt produkt){
        int antalKlip = 0;
        for(Pris p: produkt.getPriser()){
            for(Pris pris: this.getPriser()){
                if(p == pris){
                    antalKlip = pris.getKlippekort();
                }
            }
        }
        return antalKlip;
    }

    /*
    returnerer alle priser, som er forbundet med denne prisliste
     */
    public ArrayList<Pris> getPriser() {
        return new ArrayList<>(priser);
    }

    /*
    Laver en pris på et produkt.
    Parameter varePris er prisen i kroner. antalKlip er antallet af klip, som varen koster.
     */
    public Pris createPris(Produkt produkt, double varePris, int antalKlip){
        Pris pris = new Pris(varePris,produkt, this);
        if(antalKlip > 0){
            pris.setKlippekortPris(antalKlip);
        }
        priser.add(pris);
        return pris;
    }

    @Override
    public String toString() {
        return navn;
    }
}
