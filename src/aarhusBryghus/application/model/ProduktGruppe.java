package aarhusBryghus.application.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

public class ProduktGruppe {
    private final HashSet<Produkt> produkter = new HashSet<>();
    private String navn;

    public ProduktGruppe(String navn) {
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

    public HashSet<Produkt> getProdukter() {
        return new HashSet<>(produkter);
    }

    @Override
    public String toString() {
        return "" + navn;
    }
}
