package aarhusBryghus.application.controller;

import aarhusBryghus.application.model.*;
import aarhusBryghus.storage.Storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

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
        Produkt produkt = produktgruppe.createProdukt(navn, maaleEnhed);
        return produkt;
    }

    public static ArrayList<Produkt> getProdukt() {
        return Storage.getProdukter();
    }

    //------------------------------------------------------------------------------------------------------------------

    public static Klippekort createKlippekort(String navn, ProduktGruppe produktgruppe, MaaleEnhed maaleEnhed, int antalKlip) {
        Klippekort klippekort = produktgruppe.createKlippekort(navn, maaleEnhed, antalKlip);
        return klippekort;
    }

    //------------------------------------------------------------------------------------------------------------------

    public static Rundvisning createRundvisning(String navn, ProduktGruppe produktgruppe, MaaleEnhed maaleEnhed, LocalDateTime tidspunkt) {
        Rundvisning rundvisning = produktgruppe.createRundvisning(navn, maaleEnhed, tidspunkt);
        return rundvisning;
    }

    //------------------------------------------------------------------------------------------------------------------
    //TODO Eventuelt tilføje så man kan updateProdukt og deleteProdukt
    public static ProduktGruppe createProduktGruppe(String navn) {
        ProduktGruppe produktGruppe = new ProduktGruppe(navn);
        Storage.addProduktGruppe(produktGruppe);
        return produktGruppe;
    }

    public static ArrayList<ProduktGruppe> getProduktGrupper() {
        return Storage.getProduktGrupper();
    }

    //------------------------------------------------------------------------------------------------------------------
    //TODO Eventuelt tilføje så man kan updateProdukt og deleteProdukt

    public static MaaleEnhed createMaaleEnhed(String navn, int tal) {
        MaaleEnhed maaleEnhed = new MaaleEnhed(navn, tal);
        Storage.addMaaleEnhed(maaleEnhed);
        return maaleEnhed;
    }

    public static ArrayList<MaaleEnhed> getMaaleEnheder() {
        return Storage.getMaaleEnheder();
    }

    //------------------------------------------------------------------------------------------------------------------
    //TODO Eventuelt tilføje så man kan updateProdukt og deleteProdukt
    public static Prisliste createPrisliste(String navn) {
        Prisliste prisliste = new Prisliste(navn);
        Storage.addPrisliste(prisliste);
        return prisliste;
    }

    public static ArrayList<Prisliste> getPrislister() {
        return Storage.getPrislister();
    }

    //------------------------------------------------------------------------------------------------------------------

    public static HashSet<Produkt> listeOverProdukterProduktgruppePaaPrisliste(ProduktGruppe produktgruppe, Prisliste prisliste) {
        HashSet<Produkt> produkter = new HashSet<>();
        for (Produkt p : produktgruppe.getProdukter()) {
            for (Pris pris : p.getPriser()) {
                if (pris.getPrisliste() == prisliste) {
                    produkter.add(p);
                }
            }
        }
        return produkter;
    }

    public static HashSet<ProduktGruppe> listeProduktgrupperTilValgtePrisliste(Prisliste prisliste) {
        HashSet<ProduktGruppe> produktGrupper = new HashSet<>();
        for (ProduktGruppe g : Storage.getProduktGrupper()) {
            for (Produkt p : g.getProdukter()) {
                for (Pris pris : p.getPriser()) {
                    if (pris.getPrisliste() == prisliste) {
                       if (!produktGrupper.contains(g)) {
                           produktGrupper.add(g);
                       }
                    }
                }
            }
        }
        return produktGrupper;
    }


    private static void initStorage() {
        // Prislister
        Prisliste fredagsbar = Controller.createPrisliste("Fredagsbar");
        Prisliste butik = Controller.createPrisliste("Butik");

        // Produktgrupper
        ProduktGruppe flaske = Controller.createProduktGruppe("Flaske");
        ProduktGruppe fadoel = Controller.createProduktGruppe("Fadøl 40 cl");
        ProduktGruppe madOgDrikke = Controller.createProduktGruppe("Mad og drikke");
        ProduktGruppe spiritus = Controller.createProduktGruppe("Spiritus");
        ProduktGruppe fustage = Controller.createProduktGruppe("Fustage");

        // Måleenheder
        MaaleEnhed fireCl = Controller.createMaaleEnhed("cl", 4);
        MaaleEnhed fyrreCl = Controller.createMaaleEnhed("cl", 40);
        MaaleEnhed halvtredsCl = Controller.createMaaleEnhed("cl", 50);
        MaaleEnhed tyveLiter = Controller.createMaaleEnhed("liter", 20);

        // Flaske produkter todo: er det nok bare at have produktgruppe med som parameter, eller bør den create produktet?
        Produkt klosterbrygFlaske = Controller.createProdukt("Klosterbryg", flaske, null);
        Produkt sweetGeorgiaBrown = Controller.createProdukt("Sweet Georgia Brown", flaske, null);
        Produkt extraPilsnerFlaske = Controller.createProdukt("Extra Pilsner", flaske, null);
        Produkt celebrationFlaske = Controller.createProdukt("Celebration", flaske, null);
        Produkt blondieFlaske = Controller.createProdukt("Blondie", flaske, null);
        Produkt foraarsbrygFlaske = Controller.createProdukt("Forårsbryg", flaske, null);
        Produkt indiaPaleAleFlaske = Controller.createProdukt("India Pale Ale", flaske, null);
        Produkt julebrygFlaske = Controller.createProdukt("Julebryg", flaske, null);
        Produkt juletoenden = Controller.createProdukt("Juletønden", flaske, null);
        Produkt oldStrongAle = Controller.createProdukt("Old Strong Ale", flaske, null);
        Produkt fregattenJylland = Controller.createProdukt("Fregatten Jylland", flaske, null);
        Produkt imperialStoutFlaske = Controller.createProdukt("Imperial Stout", flaske, null);
        Produkt tribute = Controller.createProdukt("Tribute", flaske, null);
        Produkt blackMonster = Controller.createProdukt("Black Monster", flaske, null);

        // Fadøl, 40 cl produkter
        Produkt klosterbrygFadoel = Controller.createProdukt("Klosterbryg", fadoel, fyrreCl);
        Produkt jazzClassic = Controller.createProdukt("Jazz Classic", fadoel, fyrreCl);
        Produkt extraPilsnerFadoel = Controller.createProdukt("Extra Pilsner", fadoel, fyrreCl);
        Produkt celebrationFadoel = Controller.createProdukt("Celebration", fadoel, fyrreCl);
        Produkt blondieFadoel = Controller.createProdukt("Blondie", fadoel, fyrreCl);
        Produkt foraarsbrygFadoel = Controller.createProdukt("Forårsbryg", fadoel, fyrreCl);
        Produkt indiaPaleAleFadoel = Controller.createProdukt("India Pale Ale", fadoel, fyrreCl);
        Produkt julebrygFadoel = Controller.createProdukt("Julebryg", fadoel, fyrreCl);
        Produkt imperialStoutFadoel = Controller.createProdukt("Imperial Stout", fadoel, fyrreCl);
        Produkt special = Controller.createProdukt("Special", fadoel, fyrreCl);

        // Mad og drikke produkter
        Produkt aeblebrus = Controller.createProdukt("Æblebrus", madOgDrikke, null);
        Produkt chips = Controller.createProdukt("Chips", madOgDrikke, null);
        Produkt peanuts = Controller.createProdukt("Peanuts", madOgDrikke, null);
        Produkt cola = Controller.createProdukt("Cola", madOgDrikke, null);
        Produkt nikoline = Controller.createProdukt("Nikoline", madOgDrikke, null);
        Produkt sevenUp = Controller.createProdukt("7-Up", madOgDrikke, null);
        Produkt vand = Controller.createProdukt("Vand", madOgDrikke, null);
        Produkt oelpoelser = Controller.createProdukt("Ølpølser", madOgDrikke, null);

        // Spiritusprodukter
        Produkt Whiskey45pct = Controller.createProdukt("Whiskey 45% Rør", spiritus, halvtredsCl);
        Produkt WhiskeyShot = Controller.createProdukt("Whiskey Shot", spiritus, fireCl);

        // Fredagsbar prisliste
        // flasker
        fredagsbar.createPris(klosterbrygFlaske, 70, 2);
        fredagsbar.createPris(sweetGeorgiaBrown, 70, 2);
        fredagsbar.createPris(extraPilsnerFlaske, 70, 2);
        fredagsbar.createPris(celebrationFlaske, 70, 2);
        fredagsbar.createPris(blondieFlaske, 70, 2);
        fredagsbar.createPris(foraarsbrygFlaske, 70, 2);
        fredagsbar.createPris(indiaPaleAleFlaske, 70, 2);
        fredagsbar.createPris(julebrygFlaske, 70, 2);
        fredagsbar.createPris(juletoenden, 70, 2);
        fredagsbar.createPris(oldStrongAle, 70, 2);
        fredagsbar.createPris(fregattenJylland, 70, 2);
        fredagsbar.createPris(imperialStoutFlaske, 70, 2);
        fredagsbar.createPris(tribute, 70, 2);
        fredagsbar.createPris(blackMonster, 100, 3);
        // fadøl
        fredagsbar.createPris(klosterbrygFadoel, 38, 1);
        fredagsbar.createPris(jazzClassic, 38, 1);
        fredagsbar.createPris(extraPilsnerFadoel, 38, 1);
        fredagsbar.createPris(celebrationFadoel, 38, 1);
        fredagsbar.createPris(blondieFadoel, 38, 1);
        fredagsbar.createPris(foraarsbrygFadoel, 38, 1);
        fredagsbar.createPris(indiaPaleAleFadoel, 38, 1);
        fredagsbar.createPris(julebrygFadoel, 38, 1);
        fredagsbar.createPris(imperialStoutFadoel, 38, 1);
        fredagsbar.createPris(special, 38, 1);
        // mad og drikke
        fredagsbar.createPris(aeblebrus, 15, 0);
        fredagsbar.createPris(chips, 10, 0);
        fredagsbar.createPris(peanuts, 15, 0);
        fredagsbar.createPris(cola, 15, 0);
        fredagsbar.createPris(nikoline, 15, 0);
        fredagsbar.createPris(sevenUp, 15, 0);
        fredagsbar.createPris(vand, 10, 0);
        fredagsbar.createPris(oelpoelser, 30, 1);
        fredagsbar.createPris(Whiskey45pct, 599, 0);
        fredagsbar.createPris(WhiskeyShot, 50, 0);
        // Butiksprisliste:
        // flasker
        butik.createPris(klosterbrygFlaske, 36, 0);
        butik.createPris(sweetGeorgiaBrown, 36, 0);
        butik.createPris(extraPilsnerFlaske, 36, 0);
        butik.createPris(celebrationFlaske, 36, 0);
        butik.createPris(blondieFlaske, 36, 0);
        butik.createPris(foraarsbrygFlaske, 36, 0);
        butik.createPris(indiaPaleAleFlaske, 36, 0);
        butik.createPris(julebrygFlaske, 36, 0);
        butik.createPris(juletoenden, 36, 0);
        butik.createPris(oldStrongAle, 36, 0);
        butik.createPris(fregattenJylland, 36, 0);
        butik.createPris(imperialStoutFlaske, 36, 0);
        butik.createPris(tribute, 36, 0);
        butik.createPris(blackMonster, 60, 0);
        // spiritus
        butik.createPris(Whiskey45pct, 599, 0);

    }

    public static void init() {
        initStorage();
    }
}
