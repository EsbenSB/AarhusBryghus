package aarhusBryghus.application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Ordre {

    private String type;
    private boolean status;
    private LocalDate oprettelsesDato;
    private LocalDate afslutningsDato;
    private static int ordreNummer;
    private ArrayList<Ordrelinje> ordrelinjer = new ArrayList<>();
    private Prisliste prisliste;
    private int ordrelinjeNr = 0;
    private Betalingsform betalingsform;
    private Kunde kunde;

    public Ordre(String type, boolean status, LocalDate oprettelsesDato, Prisliste prisliste) {
        ordreNummer++;
        this.type = type;
        this.status = status;
        this.oprettelsesDato = oprettelsesDato;
        this.ordrelinjeNr = 0;
        this.prisliste = prisliste;
        this.betalingsform = null;
    }

    public void setKunde(Kunde kunde){
        this.kunde = kunde;
    }

    public void setBetalingsform(Betalingsform betalingsform){
        this.betalingsform = betalingsform;
    }

    public Prisliste getPrisliste() {
        return prisliste;
    }

    public Ordrelinje createOrdrelinje(int antal, Produkt produkt){
        ordrelinjeNr++;
        Ordrelinje ordrelinje = new Ordrelinje(ordrelinjeNr, produkt, antal, this, getPrisliste());
        ordrelinjer.add(ordrelinje);
        return ordrelinje;
    }

    public Betalingsform getBetalingsform() {
        return betalingsform;
    }

    public String getType() {
        return type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public static int getOrdreNummer() {
        return ordreNummer;
    }

    public ArrayList<Ordrelinje> getOrdrelinjer() {
        return new ArrayList<>(ordrelinjer);
    }

    public int getOrdrelinjeNr() {
        return ordrelinjeNr;
    }

    @Override
    public String toString() {
        return "Ordre{" +
                "type='" + type + '\'' +
                ", status=" + status +
                ", oprettelsesDato=" + oprettelsesDato +
                ", afslutningsDato=" + afslutningsDato +
                ", ordrelinjer=" + ordrelinjer +
                ", ordrelinjeNr=" + ordrelinjeNr +
                '}';
    }
}
