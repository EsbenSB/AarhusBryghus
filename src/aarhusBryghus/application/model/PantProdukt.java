package aarhusBryghus.application.model;

public class PantProdukt extends Produkt{
    private int pant;

    public PantProdukt(String navn, MaaleEnhed maaleEnhed, int pant) {
        super(navn, maaleEnhed);
        this.pant = pant;
    }

    public int getPant() {
        return pant;
    }

    @Override
    public String toString() {
        return pant+"";
    }
}
