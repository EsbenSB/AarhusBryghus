package aarhusBryghus.application.model;

import java.time.LocalDate;

public class App {
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
        Pris pris5 = new Pris(36,produkt2,pl2);
        Betalingsform dankort = new Dankort();
        Betalingsform klippekort = new Klip();
        Betalingsform kontant = new Kontant();
        Betalingsform regning = new Regning();
        Betalingsform mobilepay = new MobilePay();
        System.out.println(produkt1.getPriser());
        Ordre ordre1 = new Ordre("alm. salg",false, LocalDate.parse("2022-03-05"), pl1);
        Ordrelinje ol1 = ordre1.createOrdrelinje(2, produkt1);
        Ordrelinje ol2 = ordre1.createOrdrelinje(1, produkt2);
        for(Ordrelinje o: ordre1.getOrdrelinjer()){
            System.out.println( o.getProdukt().enkeltPris(ordre1.getPrisliste()));
        }
        ordre1.setBetalingsform(klippekort);
        System.out.println(ordre1.getOprettelsesDato() + " " + ordre1.getBetalingsform());

        System.out.println();
        System.out.println(ordre1.getOrdrelinjer());
        ol1.setPrisMedProcentRabat(10);
        System.out.println(ordre1.getOrdrelinjer());
        ol2.setAftaltPris(40);
        System.out.println(ordre1.getOrdrelinjer());

    }
}
