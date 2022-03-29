package aarhusBryghus.application.model;

public class Klippekort extends Produkt{
    private int antalKlip;

    public Klippekort(String navn, MaaleEnhed maaleEnhed, int antalKlip) {
        super(navn, maaleEnhed);
        this.antalKlip = antalKlip;
    }


}
