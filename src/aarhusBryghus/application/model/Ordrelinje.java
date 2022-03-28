package aarhusBryghus.application.model;

public class Ordrelinje {

    private Produkt produkt;
    private Ordre ordre;
    private int linjenummer;

    public Ordrelinje(int linjenummer, Produkt produkt, Ordre ordre) {
        this.linjenummer = linjenummer;
        this.produkt = produkt;
        this.ordre = ordre;
    }

    @Override
    public String toString() {
        return produkt+" ";
    }
}
