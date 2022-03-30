package aarhusBryghus.application.controller;

import aarhusBryghus.application.model.*;
import aarhusBryghus.storage.Storage;

public class ControlleApp {
    public static void main(String[] args) {
        Controller.init();
        Prisliste prisliste = Controller.createPrisliste("Frugthandler");
        Produktgruppe pg = Controller.createProduktGruppe("Frugt");
        MaaleEnhed me = Controller.createMaaleEnhed("gram",50);
        Produkt produkt = Controller.createProdukt("Æble",pg,me);
        prisliste.createPris(produkt,5,2);
        Ordre ordre = Controller.createSalg(prisliste);
        Ordrelinje ordrelinje1 = Controller.createOrdrelinjeSalg(ordre,produkt,2,prisliste);
        Ordrelinje ordrelinje2 = Controller.createOrdrelinjeSalg(ordre,produkt,2,prisliste);
        System.out.println("Samlet pris: " + ordre.getSamletPris()); // virker
        System.out.println("Samlet klip: " + ordre.getSamletPrisKlip()); // virker
        System.out.println("Antal klip på produkt: " + prisliste.getAntalKlip(produkt)); // virker
    }
}