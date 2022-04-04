package aarhusBryghus.application.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;

public class Produkt implements Serializable {

    private String navn;

    private Maaleenhed maaleEnhed;
    private HashSet<Pris> priser = new HashSet<Pris>();


    public Produkt(String navn, Maaleenhed maaleEnhed){
        this.navn = navn;
        this.maaleEnhed = maaleEnhed;
    }
    public int getAntalKlip() {
        return 0;
    }

    public LocalDateTime getTidspunkt() {
        return null;
    }


    public LocalTime getKlokkesletForRundvisning(LocalDateTime tidspunkt){
       return  null;
    }
    public LocalDate getDatoForRundvisning(LocalDateTime tidspunkt){
      return null;
    }

    public void addPris(Pris pris){
        priser.add(pris);
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Maaleenhed getMaaleEnhed() {
        return maaleEnhed;
    }

    public void setMaaleEnhed(Maaleenhed maaleEnhed) {
        this.maaleEnhed = maaleEnhed;
    }
    /*
    Returnerer alle priser, som der er forbundet med produktet.
     */
    public HashSet<Pris> getPriser() {
        return priser;
    }
    /*
    Returnerer den pris på produktet, som den har på prislisten, i parameteret
    Returnerer -1 hvis prislisten er tom.
     */
    public double enkeltPris(Prisliste prisliste){
        double pris = -1;
        if(prisliste != null && prisliste.getPriser() != null){
            for(Pris p: this.getPriser()){
                if(p.getPrisliste() == prisliste){
                    pris = p.getPris();
                    return pris;
                }
            }
        }
        return pris;
    }

    /*
    Returnerer den pris på produktet, som den har på prislisten, i parameteret
    Returnerer -1 hvis prislisten er tom.
    */
    public int getklippekortPris(Prisliste prisliste){
        int klip = -1;
        if(prisliste.getPriser() != null){
            for(Pris p: this.getPriser()){
                if(p.getPrisliste() == prisliste){
                    klip = p.getKlippekort();
                    return klip;
                }
            }
        }
        return klip;
    }

    public int getPant() {
        return 0;
    }


    @Override
    public String toString() {
        return navn + "";
    }
}
