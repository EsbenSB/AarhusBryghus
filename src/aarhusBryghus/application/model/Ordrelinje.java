package aarhusBryghus.application.model;

public class Ordrelinje {

    private Produkt produkt;
    private int linjenummer = 1;
    private int antal;
    private double pris;
    private int Klip; // prisen på klippekort??

    public Ordrelinje(Produkt produkt, int antal, Prisliste prisliste) {
        linjenummer++;
        this.produkt = produkt;
        this.antal = antal;
        this.pris = produkt.enkeltPris(prisliste);
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

    /*
    Reducerer prisen på alle produkterne på ordrelinjen, med en procentdel.
    Pre: procent skal være > 0
     */
    public void setPrisMedProcentRabat(double procent){
        if(procent > 0){
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
        Klip = klip;
    }

    public int getKlip() {
        return Klip;
    }

    @Override
    public String toString() {
        return produkt.getNavn() + " " + produkt.getMaaleEnhed() + ". Antal: " + antal + " stk pris: " + pris + " samlet pris: " + pris * antal;
    }
}
