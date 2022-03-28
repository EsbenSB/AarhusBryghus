package aarhusBryghus.application.model;

import java.util.HashSet;

public class ProduktGruppe {
    private final HashSet<Produkt> produkter = new HashSet<>();
    private String navn;

    public ProduktGruppe(String navn) {
        this.navn = navn;
    }

    public Produkt createProdukt(String navn, MaaleEnhed maaleEnhed){
        Produkt produkt = new Produkt(navn, this, maaleEnhed);
        produkter.add(produkt);
        return produkt;
    }

    @Override
    public String toString() {
        return "" + navn;
    }
}
