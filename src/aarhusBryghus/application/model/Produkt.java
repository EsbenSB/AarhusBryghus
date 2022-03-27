package aarhusBryghus.application.model;

import java.util.HashSet;

public class Produkt {

    private String navn;

    // Associering: -> 0..* (Skal det være hashset? - hvorfor?)
    private HashSet<ProduktGruppe> produktGrupper = new HashSet<>();
    private HashSet<MaaleEnhed> maaleEnheder = new HashSet<>();
    private HashSet<Pris> priser = new HashSet<Pris>();

    //TODO Lav klasse (Hvis navnet skal ændres så husk refactor, ellers bliver gui fucked)
    /*
    kunne man lave constructoren, så man "automatisk" tilvælger måleenhed og produktgruppen, såfremt vi kender dem?

     */
    public Produkt(String navn){
        this.navn = navn;
    }
}
