package aarhusBryghus.application.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

public class ProduktGruppe {
    private final ArrayList<Produkt> produkter = new ArrayList<>();
    private String navn;

    public ProduktGruppe(String navn) {
        this.navn = navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Produkt createProdukt(String navn, MaaleEnhed maaleEnhed){
        Produkt produkt = new Produkt(navn, maaleEnhed);
        produkter.add(produkt);
        return produkt;
    }

    public Klippekort createKlippekort(String navn, MaaleEnhed maaleEnhed, int antalKlip){
        Klippekort klippekort = new Klippekort(navn, maaleEnhed, antalKlip);
        produkter.add(klippekort);
        return klippekort;
    }

    public Rundvisning createRundvisning(String navn, MaaleEnhed maaleEnhed, LocalDateTime tidspunkt){
        Rundvisning rundvisning = new Rundvisning(navn, maaleEnhed, tidspunkt);
        produkter.add(rundvisning);
        return rundvisning;
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
