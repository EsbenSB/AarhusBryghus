package aarhusBryghus.application.model;

import java.util.HashSet;

public class MaaleEnhed {
    private String navn;
    private int tal;

    private final HashSet<Produkt> produkter = new HashSet<>(); // todo: tror ikke den her skal være her

    // opretter en måleenhed, som skal bruges til produkterne
    // produkterne har forskellige måleenheder
    // fx. centiliter, liter, eller kilo.
    public MaaleEnhed(String navn, int tal) {
        this.navn = navn;
        this.tal = tal;
    }

    @Override
    public String toString() {
        return tal + " " + navn;
    }
}
