package aarhusBryghus.application.controller;

import aarhusBryghus.application.model.*;

import java.time.LocalDate;

public class ControlleApp {
    public static void main(String[] args) {
        Prisliste prisliste = Controller.getInstance().createPrisliste("Frugthandler");
        Produktgruppe pg = Controller.getInstance().createProduktGruppe("Frugt");
        Maaleenhed me = Controller.getInstance().createMaaleEnhed("gram",50);
        Produkt produkt = Controller.getInstance().createProdukt("Æble",pg,me);
        prisliste.createPris(produkt,5,2);
        Ordre ordre = Controller.getInstance().createSalg(prisliste);
        Ordrelinje ordrelinje1 = Controller.getInstance().createOrdrelinjeSalg(ordre,produkt,3,prisliste);
        Ordrelinje ordrelinje2 = Controller.getInstance().createOrdrelinjeSalg(ordre,produkt,2,prisliste);
        System.out.println("Samlet pris: " + ordre.getSamletPris()); // virker
        System.out.println("Samlet klip: " + ordre.getSamletPrisKlip()); // virker
        System.out.println("Antal klip på produkt: " + prisliste.getAntalKlip(produkt)); // virker
        Produkt klippekort = Controller.getInstance().createKlippekort("Klippekort, 10 klip",pg,null,10);
        ordre.setAfslutningsDato(LocalDate.now());
        System.out.println(Controller.getInstance().getDagensSalg());
        System.out.println(Controller.getInstance().getKundeUdlejninger(Controller.getInstance().findKunde(33333333)));

    }
}
