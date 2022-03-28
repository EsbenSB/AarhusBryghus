package aarhusBryghus.application.model;

import java.util.HashSet;

public class Produkt {

    private String navn;

    // Associering: -> 0..* (Skal det være hashset? - hvorfor?)
    private ProduktGruppe produktgruppe; // todo: bør ikke være her pga. enkeltrettet
    private MaaleEnhed maaleEnhed;
    private HashSet<Pris> priser = new HashSet<Pris>();
    private Pant pant;

    public Produkt(String navn, ProduktGruppe produktgruppe, MaaleEnhed maaleEnhed){
        this.navn = navn;
        this.produktgruppe = produktgruppe;
        this.maaleEnhed = maaleEnhed;
    }

    public Pant getPant() {
        return pant;
    }

    public void setPant(Pant pant) {
        this.pant = pant;
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

    public ProduktGruppe getProduktgruppe() {
        return produktgruppe;
    }

    public void setProduktgruppe(ProduktGruppe produktgruppe) {
        this.produktgruppe = produktgruppe;
    }

    public MaaleEnhed getMaaleEnhed() {
        return maaleEnhed;
    }

    public void setMaaleEnhed(MaaleEnhed maaleEnhed) {
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
        if(prisliste.getPriser() != null){
            for(Pris p: this.getPriser()){
                if(p.getPrisliste() == prisliste){
                    pris = p.getPris();
                    return pris;
                }
            }
        }
        return pris;
    }

    @Override
    public String toString() {
        return navn + "";
    }
}
