package aarhusBryghus.application.model;

import java.util.HashSet;

public class MaaleEnhed {
    private String navn;
    private int tal;

    private final HashSet<Produkt> produkter = new HashSet<>();

    // opretter en måleenhed, som skal bryges til produkterne
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
