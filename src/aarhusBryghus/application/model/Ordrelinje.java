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

    @Override
    public String toString() {
        return produkt.getNavn() + " " + produkt.getMaaleEnhed() + ". Antal: " + antal + " stk pris: " + pris + " samlet pris: " + pris * antal;
    }
}
