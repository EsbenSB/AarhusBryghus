package aarhusBryghus.application.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Ordre  implements Serializable {

    private String type;
    private boolean erOrdrenLukket;
    private LocalDate oprettelsesDato;
    private LocalDate afslutningsDato;
    private static int ordrenummerCounter = 0;
    private int ordrenummer;
    private ArrayList<Ordrelinje> ordrelinjer = new ArrayList<>();
    private Prisliste prisliste;
    private int ordrelinjeNr = 0;
    private Betalingsform betalingsform;
    private Kunde kunde;

    public Ordre(String type, boolean status, LocalDate oprettelsesDato, Prisliste prisliste) {
        ordrenummerCounter++;
        this.ordrenummer = ordrenummerCounter;
        this.type = type;
        this.erOrdrenLukket = status;
        this.oprettelsesDato = oprettelsesDato;
        this.ordrelinjeNr = 0;
        this.prisliste = prisliste;
        this.betalingsform = null;
    }

    public boolean isErOrdrenLukket() {
        return erOrdrenLukket;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        if (this.kunde != kunde) {
            this.kunde = kunde;
        }
    }

    public void setBetalingsform(Betalingsform betalingsform) {
        this.betalingsform = betalingsform;
    }

    public Prisliste getPrisliste() {
        return prisliste;
    }

    public Ordrelinje createOrdrelinje(int antal, Produkt produkt) {
        ordrelinjeNr++;
        Ordrelinje ordrelinje = new Ordrelinje(produkt, antal, getPrisliste());
        ordrelinjer.add(ordrelinje);
        return ordrelinje;
    }

    public Betalingsform getBetalingsform() {
        return betalingsform;
    }

    public String getType() {
        return type;
    }

    public boolean erOrdrenLukket() {
        return erOrdrenLukket;
    }

    public void setOrdreStatus(boolean erOrdrenLukket) {
        this.erOrdrenLukket = erOrdrenLukket;
    }

    public LocalDate getOprettelsesDato() {
        return oprettelsesDato;
    }

    public LocalDate getAfslutningsDato() {
        return afslutningsDato;
    }

    public void setAfslutningsDato(LocalDate afslutningsDato) {
        this.afslutningsDato = afslutningsDato;
    }

    public int getOrdrenummer() {
        return ordrenummer;
    }

    public ArrayList<Ordrelinje> getOrdrelinjer() {
        return new ArrayList<>(ordrelinjer);
    }

    public int getSamletPrisKlip() {
        int klip = 0;
        for (Ordrelinje ol : ordrelinjer) {
            if (ol.getKlip() > 0) {
                klip += ol.getSamletPrisKlip();
            }
        }
        return klip;
    }

    public double getSamletPris() {
        int klip = 0;
        double samletPris = 0;
        for (Ordrelinje ol : ordrelinjer) {
            samletPris += ol.getPris() * ol.getAntal();
        }
        return samletPris;
    }

    public int getOrdrelinjeNr() {
        return ordrelinjeNr;
    }

    @Override
    public String toString() {
        if (kunde == null) {
            return "Ordrenr: " + ordrenummer + ". Antal Linjer: " + ordrelinjer.size() + ". Samlet salg: " + this.getSamletPris();

        } else {
            return "OrdreNr: " + ordrenummer + ". Antal Linjer: " + ordrelinjer.size() + ". Kunde navn: " + kunde.getFornavn() + " " + kunde.getEfternavn() + ", Kunde Mobil: " + kunde.getTelefon();

        }
    }

    public int getSamletPantPaaOrdre(){
        int samletPant = 0;
        for(Ordrelinje ol: this.getOrdrelinjer()){
            samletPant += ol.getSamletPant();
        }
        return samletPant;
    }

    public void removeOrdrelinje(Ordrelinje ordrelinje) {
        if (ordrelinjer.contains(ordrelinje)) {
            ordrelinjer.remove(ordrelinje);
            System.out.println("Removed: " + ordrelinje + ". From ordre");
        }
    }
}
