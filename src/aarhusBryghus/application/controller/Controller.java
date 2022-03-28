package aarhusBryghus.application.controller;

import aarhusBryghus.application.model.MaaleEnhed;
import aarhusBryghus.application.model.Prisliste;
import aarhusBryghus.application.model.Produkt;
import aarhusBryghus.application.model.ProduktGruppe;
import aarhusBryghus.storage.Storage;

import java.util.ArrayList;

public class Controller {

    private static Controller uniqueInstance;
    private Storage storage;

    public static Controller getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Controller();
        }
        return uniqueInstance;
    }

    private Controller() {
        storage = Storage.getInstance();
    }

    //------------------------------------------------------------------------------------------------------------------
    //TODO Eventuelt tilføje så man kan updateProdukt og deleteProdukt
    public static Produkt createProdukt(String navn, ProduktGruppe produktgruppe, MaaleEnhed maaleEnhed) {
        Produkt produkt = new Produkt(navn,produktgruppe,maaleEnhed);
        Storage.addProdukt(produkt);
        return produkt;
    }

    public static ArrayList<Produkt> getProdukt() {
        return Storage.getProdukter();
    }

    //------------------------------------------------------------------------------------------------------------------
    //TODO Eventuelt tilføje så man kan updateProdukt og deleteProdukt
    public static ProduktGruppe createProduktGruppe(String navn) {
        ProduktGruppe produktGruppe = new ProduktGruppe(navn);
        Storage.addProduktGruppe(produktGruppe);
        return produktGruppe;
    }

    public static ArrayList<ProduktGruppe> getProduktGruppe() {
        return Storage.getProduktGrupper();
    }

    //------------------------------------------------------------------------------------------------------------------
    //TODO Eventuelt tilføje så man kan updateProdukt og deleteProdukt

    public static MaaleEnhed createMaaleEnhed(String navn, int tal) {
        MaaleEnhed maaleEnhed = new MaaleEnhed(navn,tal);
        Storage.addMaaleEnhed(maaleEnhed);
        return maaleEnhed;
    }

    public static ArrayList<MaaleEnhed> getMaaleEnhed() {
        return Storage.getMaaleEnheder();
    }

    //------------------------------------------------------------------------------------------------------------------
    //TODO Eventuelt tilføje så man kan updateProdukt og deleteProdukt
    public static Prisliste createPrisliste(String navn) {
        Prisliste prisliste = new Prisliste(navn);
        Storage.addPrisliste(prisliste);
        return prisliste;
    }

    public static ArrayList<Prisliste> getPrisliste() {
        return Storage.getPrislister();
    }

    //------------------------------------------------------------------------------------------------------------------

    private static void initStorage() {
        // Prislister
        Prisliste fredagsbar = Controller.createPrisliste("Fredagsbar");
        Prisliste butik = Controller.createPrisliste("Butik");

        // Produktgrupper
        ProduktGruppe flaske = Controller.createProduktGruppe("Flaske");
        ProduktGruppe fadoel = Controller.createProduktGruppe("Fadøl 40 cl");
        ProduktGruppe madOgDrikke = Controller.createProduktGruppe("Mad og drikke");

        // Måleenheder
        MaaleEnhed fyrreCl = Controller.createMaaleEnhed("40 cl", 40);

        // Flaske produkter
        Produkt klosterbrygFlaske = Controller.createProdukt("Klosterbryg",flaske,null);
        Produkt sweetGeorgiaBrown = Controller.createProdukt("Sweet Georgia Brown",flaske,null);
        Produkt extraPilsnerFlaske = Controller.createProdukt("Extra Pilsner",flaske,null);
        Produkt celebrationFlaske = Controller.createProdukt("Celebration",flaske,null);
        Produkt blondieFlaske = Controller.createProdukt("Blondie",flaske,null);
        Produkt foraarsbrygFlaske = Controller.createProdukt("Forårsbryg",flaske,null);
        Produkt indiaPaleAleFlaske = Controller.createProdukt("India Pale Ale",flaske ,null);
        Produkt julebrygFlaske = Controller.createProdukt("Julebryg",flaske,null);
        Produkt juletoenden = Controller.createProdukt("Juletønden", flaske, null);
        Produkt oldStrongAle = Controller.createProdukt("Old Strong Ale", flaske, null);
        Produkt fregattenJylland = Controller.createProdukt("Fregatten Jylland", flaske, null);
        Produkt imperialStoutFlaske = Controller.createProdukt("Imperial Stout", flaske, null);
        Produkt tribute = Controller.createProdukt("Tribute", flaske, null);
        Produkt blackMonster = Controller.createProdukt("Black Monster", flaske, null);

        // Fadøl, 40 cl produkter
        Produkt klosterbrygFadoel = Controller.createProdukt("Klosterbryg", fadoel,fyrreCl);
        Produkt jazzClassic = Controller.createProdukt("Jazz Classic", fadoel,fyrreCl);
        Produkt extraPilsnerFadoel = Controller.createProdukt("Extra Pilsner", fadoel, fyrreCl);
        Produkt celebrationFadoel = Controller.createProdukt("Celebration", fadoel,fyrreCl);
        Produkt blondieFadoel = Controller.createProdukt("Blondie", fadoel, fyrreCl);
        Produkt foraarsbrygFadoel = Controller.createProdukt("Forårsbryg", fadoel, fyrreCl);
        Produkt indiaPaleAleFadoel = Controller.createProdukt("India Pale Ale", fadoel, fyrreCl);
        Produkt julebrygFadoel = Controller.createProdukt("Julebryg", fadoel, fyrreCl);
        Produkt imperialStoutFadoel = Controller.createProdukt("Imperial Stout", fadoel, fyrreCl);
        Produkt special = Controller.createProdukt("Special", fadoel, fyrreCl);

        // Mad og drikke produkter
        Produkt eablebrus = Controller.createProdukt("Æblebrus", madOgDrikke, null);
        Produkt chips = Controller.createProdukt("Chips", madOgDrikke, null);
        Produkt peanuts = Controller.createProdukt("Peanuts", madOgDrikke, null);
        Produkt cola = Controller.createProdukt("Cola", madOgDrikke, null);
        Produkt nikoline = Controller.createProdukt("Nikoline", madOgDrikke, null);
        Produkt sevenUp = Controller.createProdukt("7-Up", madOgDrikke, null);
        Produkt vand = Controller.createProdukt("Vand", madOgDrikke, null);
        Produkt oelpoelser = Controller.createProdukt("Ølpølser", madOgDrikke, null);

        // Fredagsbar prisliste
        fredagsbar.createPris(klosterbrygFlaske, 70,2);
        fredagsbar.createPris(sweetGeorgiaBrown, 70, 2);
        fredagsbar.createPris(extraPilsnerFlaske, 70, 2);
        fredagsbar.createPris(celebrationFlaske, 70, 2);
        fredagsbar.createPris(blondieFlaske, 70, 2);
        fredagsbar.createPris(foraarsbrygFlaske, 70, 2);
        fredagsbar.createPris(indiaPaleAleFlaske, 70, 2);
        fredagsbar.createPris(julebrygFlaske, 70, 2);
        fredagsbar.createPris(juletoenden,70,2);
    }

    public static void init() {
        initStorage();
    }
}
