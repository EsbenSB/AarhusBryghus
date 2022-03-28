package aarhusBryghus.application.model;

import java.util.HashSet;

public class Produkt {

    private String navn;

    // Associering: -> 0..* (Skal det være hashset? - hvorfor?)
    private ProduktGruppe produktgruppe;
    private MaaleEnhed maaleEnhed;
    private HashSet<Pris> priser = new HashSet<Pris>();

    public Produkt(String navn, ProduktGruppe produktgruppe, MaaleEnhed maaleEnhed){
        this.navn = navn;
        this.produktgruppe = produktgruppe;
        this.maaleEnhed = maaleEnhed;
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

    public HashSet<Pris> getPriser() {
        return priser;
    }

    public double enkeltPris(Prisliste prisliste){
       // todo
        return 0;
    }

    public void setPriser(HashSet<Pris> priser) {
        this.priser = priser;
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "navn='" + navn + '\'' +
                ", produktGrupper=" + produktgruppe +
                ", maaleEnhed=" + maaleEnhed +
                ", priser=" + priser +
                '}';
    }
}
