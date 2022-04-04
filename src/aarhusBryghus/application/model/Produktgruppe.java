package aarhusBryghus.application.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Produktgruppe implements Serializable {
    private final ArrayList<Produkt> produkter = new ArrayList<>();
    private String navn;

    public Produktgruppe(String navn) {
        this.navn = navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Produkt createProdukt(String navn, Maaleenhed maaleEnhed){
        Produkt produkt = new Produkt(navn, maaleEnhed);
        produkter.add(produkt);
        return produkt;
    }

    public String getNavn() {
        return navn;
    }

    public Klippekort createKlippekort(String navn, Maaleenhed maaleEnhed, int antalKlip){
        Klippekort klippekort = new Klippekort(navn, maaleEnhed, antalKlip);
        produkter.add(klippekort);
        return klippekort;
    }

    public Rundvisning createRundvisning(String navn, Maaleenhed maaleEnhed, LocalDateTime tidspunkt){
        Rundvisning rundvisning = new Rundvisning(navn, maaleEnhed, tidspunkt);
        produkter.add(rundvisning);
        return rundvisning;
    }

    public PantProdukt createPantProdukt(String navn, Maaleenhed maaleEnhed, int pant){
        PantProdukt pantProdukt = new PantProdukt(navn,maaleEnhed,pant);
        produkter.add(pantProdukt);
        return pantProdukt;
    }

    public ArrayList<Produkt> getProdukter() {
        return new ArrayList<>(produkter);
    }

    // fjerner et enkelt produkt fra dets produktgruppe.
    public void removeProdukt(Produkt produkt){
        if(produkter.contains(produkt)){
            produkter.remove(produkt);
        }
    }

    @Override
    public String toString() {
        return "" + navn;
    }
}
