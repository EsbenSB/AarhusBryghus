package aarhusBryghus.application.model;

public class Ordrelinje {

    private Produkt produkt;
    private Ordre ordre;
    private int linjenummer;
    private int antal;
    private double pris;

    public Ordrelinje(int linjenummer, Produkt produkt, int antal, Ordre ordre, Prisliste prisliste) {
        this.linjenummer = linjenummer;
        this.produkt = produkt;
        this.ordre = ordre;
        this.antal = antal;
        this.pris = produkt.enkeltPris(prisliste);
    }

    @Override
    public String toString() {
        return produkt.getNavn() +". Antal: " + antal + " stk pris: " + pris + " samlet pris: " + pris * antal;
    }

    public Produkt getProdukt(){
        return produkt;
    }

    public int getAntal(){
        return antal;
    }

    public void setPrisMedProcentRabat(double procent){
        pris = pris*((100.0-procent)/100.0);
    }

    public void setAftaltPris(double pris){
        this.pris = pris;
    }



}
