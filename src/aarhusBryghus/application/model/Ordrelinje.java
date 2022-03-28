package aarhusBryghus.application.model;

public class Ordrelinje {

    private Produkt produkt;
    private Ordre ordre;
    private int linjenummer;
    private int antal;

    public Ordrelinje(int linjenummer, Produkt produkt, int antal, Ordre ordre) {
        this.linjenummer = linjenummer;
        this.produkt = produkt;
        this.ordre = ordre;
        this.antal = antal;
    }

    @Override
    public String toString() {
        return produkt+" ";
    }

    public Produkt getProdukt(){
        return produkt;
    }
    public int getAntal(){
        return antal;
    }

}
