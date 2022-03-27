package aarhusBryghus.application.model;

import java.util.HashSet;

public class ProduktGruppe {
    private final HashSet<Produkt> produkter = new HashSet<>();
    private String navn;

    public ProduktGruppe(String navn) {
        this.navn = navn;
    }
}
