package aarhusBryghus.application.model;

import java.io.Serializable;

public class Ordrelinje implements Serializable {

    private Produkt produkt;
    private int linjenummer = 1;
    private int antal;
    private double pris;
    private int klip; // prisen på klippekort??

    public Ordrelinje(Produkt produkt, int antal, Prisliste prisliste) {
        linjenummer++;
        this.produkt = produkt;
        this.antal = antal;
        this.pris = produkt.enkeltPris(prisliste);
        this.klip = produkt.getklippekortPris(prisliste);
    }

    public Produkt getProdukt(){
        return produkt;
    }

    public int getAntal(){
        return antal;
    }

    public double getPris(){
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    /*
            Reducerer prisen på alle produkterne på ordrelinjen, med en procentdel.
            Pre: procent skal være > 0
             */
    public void setPrisMedProcentRabat(double procent){
        if(procent > 0 && procent <= 100){
            pris = pris*((100.0-procent)/100.0);
        }
    }
    /*
    Sætter prisen til et bestemt beløb.
    Pre: pris skal være > 0
    */
    public void setAftaltPris(double pris){
        if(pris > 0){
            this.pris = pris;
        }
    }

    public void setKlip(int klip) {
        this.klip = klip;
    }

    public int getKlip() {
        return klip;
    }

    public double getSamletPris(){
        double samletPris = pris*antal;
        return samletPris;
    }

    public int getSamletPrisKlip(){
        int samletAntalKlip = klip*antal;
        return samletAntalKlip;
    }

    @Override
    public String toString() {
        return produkt.getNavn() + " " + produkt.getMaaleEnhed() + ". Antal: " + antal + " stk pris: " + pris + " samlet pris: " + pris * antal;
    }

    public int getSamletPant() {
        int pant = this.getProdukt().getPant();
        int antal = this.getAntal();
        return pant*antal;
    }
}
