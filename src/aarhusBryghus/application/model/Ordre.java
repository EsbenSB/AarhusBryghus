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

    public Ordre(String type, boolean status, LocalDate oprettelsesDato, Prisliste prisliste) {
        ordreNummer++;
        this.type = type;
        this.status = status;
        this.oprettelsesDato = oprettelsesDato;
        this.ordrelinjeNr = 0;
        this.prisliste = prisliste;
    }

    public Ordrelinje createOrdrelinje(int antal, Produkt produkt){
        ordrelinjeNr++;
        Ordrelinje ordrelinje = new Ordrelinje(ordrelinjeNr, produkt, antal, this);
        ordrelinjer.add(ordrelinje);
        return ordrelinje;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public void setOprettelsesDato(LocalDate oprettelsesDato) {
        this.oprettelsesDato = oprettelsesDato;
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

    public static void setOrdreNummer(int ordreNummer) {
        Ordre.ordreNummer = ordreNummer;
    }

    public ArrayList<String> getOrdrelinjer() {
        ArrayList<String> returliste = new ArrayList<>();
        for(Ordrelinje o: ordrelinjer){
            for(Pris p: o.getProdukt().getPriser()){
                String pris = p.getPris()+"";
                returliste.add(pris);
            }
        }

        return returliste;
    }

    public void setOrdrelinjer(ArrayList<Ordrelinje> ordrelinjer) {
        this.ordrelinjer = ordrelinjer;
    }

    public int getOrdrelinjeNr() {
        return ordrelinjeNr;
    }

    public void setOrdrelinjeNr(int ordrelinjeNr) {
        this.ordrelinjeNr = ordrelinjeNr;
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
