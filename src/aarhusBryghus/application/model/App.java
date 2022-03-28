package aarhusBryghus.application.model;

import java.time.LocalDate;

public class App {

    // bare lige til at teste med :)

    public static void main(String[] args) {
        ProduktGruppe pg = new ProduktGruppe("Flaskeøl");
        ProduktGruppe pg2 = new ProduktGruppe("Fadøl");
        MaaleEnhed me1 = new MaaleEnhed("Cl.", 60);
        MaaleEnhed me2 = new MaaleEnhed("Cl.",40);
        Produkt produkt1 =  pg.createProdukt("Klosterbryg", me1);
        Produkt produkt2 =  pg.createProdukt("Julebryg", me1);
        Produkt produkt3 =  pg.createProdukt("Klosterbryg", me2);
        Prisliste pl1 = new Prisliste("Fredagsbar");
        Prisliste pl2 = new Prisliste("Butik");
        Pris pris1 = new Pris(70,produkt1,pl1);
        Pris pris2 = new Pris(38,produkt3,pl1);
        Pris pris3 = new Pris(70,produkt2,pl1);
        Pris pris4 = new Pris(36,produkt1,pl2);
        System.out.println(produkt1.getPriser());
        Ordre ordre1 = new Ordre("Fadølsanlæg",false, LocalDate.parse("2022-03-05"));
        ordre1.createOrdrelinje(produkt1);
        System.out.println(ordre1.getOrdrelinjer());

    }
}
