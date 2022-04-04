package aarhusBryghus.application.model;

import java.io.Serializable;

public class Pris implements Serializable {

    private double pris;
    private int klippekort;
    private Produkt produkt;
    private Prisliste prisliste;

    public Pris(double pris, Produkt produkt, Prisliste prisliste) {
        this.pris = pris;
        this.produkt = produkt;
        this.prisliste = prisliste;
        this.produkt.addPris(this);
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public void setPrisliste(Prisliste prisliste) {
        this.prisliste = prisliste;
    }

    public double getPris() {
        return pris;
    }

    public int getKlippekort() {
        return klippekort;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setKlippekortPris(int klippekortPris){
        klippekort = klippekortPris;
    }

    public void removePrisFromPrisliste(Prisliste prisliste){

    }

    @Override
    public String toString() {
        return pris + "";
    }

    public Prisliste getPrisliste(){
        return prisliste;
    }
}
