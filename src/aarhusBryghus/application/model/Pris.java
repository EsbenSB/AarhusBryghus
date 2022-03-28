package aarhusBryghus.application.model;

public class Pris {

    private double pris;
    private int klippekort;
    private Produkt produkt;
    private Prisliste prisliste;

    public Pris(double pris, Produkt produkt, Prisliste prisliste) {
        this.pris = pris;
        this.produkt = produkt;
        this.prisliste = prisliste;
        this.produkt.addPris(this);
    }

    public double getPris() {
        return pris;
    }

    public int getKlippekort() {
        return klippekort;
    }

    public void setKlippekortPris(int klippekortPris){
        klippekort = klippekortPris;
    }

    @Override
    public String toString() {
        return "Pris{" +
                "pris=" + pris + prisliste +
                '}';
    }

    public Prisliste getPrisliste(){
        return prisliste;
    }
}
