package aarhusBryghus.application.model;

public class Klippekort extends Produkt{
    private int antalKlip;

    public Klippekort(String navn, Maaleenhed maaleEnhed, int antalKlip) {
        super(navn, maaleEnhed);
        this.antalKlip = antalKlip;
    }

    @Override
    public int getAntalKlip() {
        return antalKlip;
    }
}
