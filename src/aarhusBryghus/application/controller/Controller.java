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
    // Produkt. Create, get, delete, update.
    public static Produkt createProdukt(String navn, Produktgruppe produktgruppe, MaaleEnhed maaleEnhed) {
        Produkt produkt = produktgruppe.createProdukt(navn, maaleEnhed);
        return produkt;
    }

    public static ArrayList<Produkt> getAlleProdukter(Produktgruppe produktgruppe) {
        for (Produktgruppe pg: Storage.getProduktGrupper()){
            if(pg == produktgruppe){
                System.out.println("Produktgruppe: " + produktgruppe);
                return pg.getProdukter();
            }
        }
        return null;
    }

    public static void deleteProdukt(Produkt produkt){
        for(Produktgruppe p: Storage.getProduktGrupper()){
            for(Produkt prod: p.getProdukter()){
                if(prod == produkt){
                    p.removeProdukt(produkt);
                }
            }
        }
    }

    // henter produktets produktgruppe
    public static Produktgruppe getProduktGruppe(Produkt produkt){
        for(Produktgruppe pg: Storage.getProduktGrupper()){
            for(Produkt prod: pg.getProdukter()){
                if(prod.equals(produkt)){
                    return pg;
                }
            }
        }
        return null;
    }
    // returnerer den første prisliste, som findes på et produkt
    public static Prisliste getProduktPrisliste(Produkt produkt){
        for(Prisliste pl: Storage.getPrislister()){
            for(Pris pris: produkt.getPriser()){
                if(pris.getPrisliste() == pl){
                    return pl;
                }
            }
        }
        return null;
    }


    //------------------------------------------------------------------------------------------------------------------

    // Klippekort. Create, get, delete, update.
    public static Klippekort createKlippekort(String navn, Produktgruppe produktgruppe, MaaleEnhed maaleEnhed, int antalKlip) {
        Klippekort klippekort = produktgruppe.createKlippekort(navn, maaleEnhed, antalKlip);
        return klippekort;
    }

    //------------------------------------------------------------------------------------------------------------------

    // Rundvisning. Create, get, delete, update.
    public static Rundvisning createRundvisning(String navn, Produktgruppe produktgruppe, MaaleEnhed maaleEnhed, LocalDateTime tidspunkt) {
        Rundvisning rundvisning = produktgruppe.createRundvisning(navn, maaleEnhed, tidspunkt);
        return rundvisning;
    }

    //------------------------------------------------------------------------------------------------------------------

    // Produktgruppe. create, get, delete, update
    public static Produktgruppe createProduktGruppe(String navn) {
        Produktgruppe produktGruppe = new Produktgruppe(navn);
        Storage.addProduktGruppe(produktGruppe);
        return produktGruppe;
    }

    public static ArrayList<Produktgruppe> getProduktGrupper() {
        return Storage.getProduktGrupper();
    }

    public static void deleteProduktgruppe(Produktgruppe produktGruppe){
        Storage.removeProduktGruppe(produktGruppe);
    }

    public static void updateProduktgruppe(Produktgruppe produktGruppe, String navn){
        produktGruppe.setNavn(navn);
    }

    //------------------------------------------------------------------------------------------------------------------

    // Måleenhed. Create, get, delete, update.
    public static MaaleEnhed createMaaleEnhed(String navn, int tal) {
        MaaleEnhed maaleEnhed = new MaaleEnhed(navn, tal);
        Storage.addMaaleEnhed(maaleEnhed);
        return maaleEnhed;
    }

    public static ArrayList<MaaleEnhed> getMaaleEnheder() {
        return Storage.getMaaleEnheder();
    }

    public static void deleteMaaleenhed(MaaleEnhed maaleEnhed){
        Storage.removeMaaleEnhed(maaleEnhed);
    }

    public static void updateMaaleenhed(MaaleEnhed maaleEnhed, String enhed, int tal){
        maaleEnhed.setEnhed(enhed);
        maaleEnhed.setTal(tal);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Prisliste. Create, get, delete, update.
    public static Prisliste createPrisliste(String navn) {
        Prisliste prisliste = new Prisliste(navn);
        Storage.addPrisliste(prisliste);
        return prisliste;
    }

    public static ArrayList<Prisliste> getPrislister() {
        return Storage.getPrislister();
    }

    public static void deletePrisliste(Prisliste prisliste){
        Storage.removePrisliste(prisliste);
    }

    public static void updatePrisliste(Prisliste prisliste, String navn){
        prisliste.setNavn(navn);
    }

    //------------------------------------------------------------------------------------------------------------------

    public static HashSet<Produkt> listeOverProdukterProduktgruppePaaPrisliste(Produktgruppe produktgruppe, Prisliste prisliste) {
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

    public static HashSet<Produktgruppe> listeProduktgrupperTilValgtePrisliste(Prisliste prisliste) {
        HashSet<Produktgruppe> produktGrupper = new HashSet<>();
        for (Produktgruppe g : Storage.getProduktGrupper()) {
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
        Produktgruppe klippekort = Controller.createProduktGruppe("Klippekort");
        Produktgruppe flaske = Controller.createProduktGruppe("Flaske");
        Produktgruppe fadoel = Controller.createProduktGruppe("Fadøl 40 cl");
        Produktgruppe madOgDrikke = Controller.createProduktGruppe("Mad og drikke");
        Produktgruppe spiritus = Controller.createProduktGruppe("Spiritus");
        Produktgruppe fustage = Controller.createProduktGruppe("Fustage");
        Produktgruppe beklaedning = Controller.createProduktGruppe("Beklædning");

        // Måleenheder
        MaaleEnhed fireCl = Controller.createMaaleEnhed("cl", 4);
        MaaleEnhed fyrreCl = Controller.createMaaleEnhed("cl", 40);
        MaaleEnhed halvtredsCl = Controller.createMaaleEnhed("cl", 50);
        MaaleEnhed tresCl = Controller.createMaaleEnhed("cl", 60);
        MaaleEnhed tyveLiter = Controller.createMaaleEnhed("liter", 20);

        // Klippekort produkt
        Produkt klippekortProdukt = Controller.createProdukt("Klippekort, 4 klip", klippekort, null);
        // Flaske produkter todo: er det nok bare at have produktgruppe med som parameter, eller bør den create produktet?
        Produkt klosterbrygFlaske = Controller.createProdukt("Klosterbryg", flaske, tresCl);
        Produkt sweetGeorgiaBrown = Controller.createProdukt("Sweet Georgia Brown", flaske, tresCl);
        Produkt extraPilsnerFlaske = Controller.createProdukt("Extra Pilsner", flaske, tresCl);
        Produkt celebrationFlaske = Controller.createProdukt("Celebration", flaske, tresCl);
        Produkt blondieFlaske = Controller.createProdukt("Blondie", flaske, tresCl);
        Produkt foraarsbrygFlaske = Controller.createProdukt("Forårsbryg", flaske, tresCl);
        Produkt indiaPaleAleFlaske = Controller.createProdukt("India Pale Ale", flaske, tresCl);
        Produkt julebrygFlaske = Controller.createProdukt("Julebryg", flaske, tresCl);
        Produkt juletoenden = Controller.createProdukt("Juletønden", flaske, tresCl);
        Produkt oldStrongAle = Controller.createProdukt("Old Strong Ale", flaske, tresCl);
        Produkt fregattenJylland = Controller.createProdukt("Fregatten Jylland", flaske, tresCl);
        Produkt imperialStoutFlaske = Controller.createProdukt("Imperial Stout", flaske, tresCl);
        Produkt tribute = Controller.createProdukt("Tribute", flaske, tresCl);
        Produkt blackMonster = Controller.createProdukt("Black Monster", flaske, tresCl);

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
        Produkt whiskey45pct = Controller.createProdukt("Whiskey 45% 50 cl rør", spiritus, halvtredsCl);
        Produkt whiskey4Cl = Controller.createProdukt("Whiskey 4 cl", spiritus, fireCl);
        Produkt whiskey43Pct = Controller.createProdukt("Whisky 43% 50 cl rør", spiritus, halvtredsCl);
        Produkt uEgesplint = Controller.createProdukt("u/ egesplint", spiritus, null);
        Produkt mEgesplint = Controller.createProdukt("m/ egesplint", spiritus, null);
        Produkt toWhiskyGlasMedBrikker = Controller.createProdukt("2*whisky glas + brikker", spiritus, null);
        Produkt liquorOfAarhus = Controller.createProdukt("Liquor of Aarhus",spiritus,null);
        Produkt lyngGin50Cl = Controller.createProdukt("Lyng gin 50 cl", spiritus, null);
        Produkt lyngGin4Cl = Controller.createProdukt("Lyng gin 4 cl", spiritus, fireCl);

        // beklædning
        Produkt tShirt = Controller.createProdukt("t-shirt", beklaedning, null);
        Produkt polo = Controller.createProdukt("polo", beklaedning, null);
        Produkt cap = Controller.createProdukt("cap", beklaedning, null);

        // Fredagsbar prisliste
        // klippekort
        fredagsbar.createPris(klippekortProdukt, 130, 0);
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
        // spiritus
        fredagsbar.createPris(whiskey45pct, 599, 0);
        fredagsbar.createPris(whiskey4Cl, 50, 0);
        fredagsbar.createPris(whiskey43Pct, 599, 0);
        fredagsbar.createPris(uEgesplint, 300, 0);
        fredagsbar.createPris(mEgesplint, 350, 0);
        fredagsbar.createPris(toWhiskyGlasMedBrikker, 80, 0);
        fredagsbar.createPris(liquorOfAarhus, 175, 0);
        fredagsbar.createPris(lyngGin50Cl, 350, 0);
        fredagsbar.createPris(lyngGin4Cl, 40, 0);
        // beklædning
        fredagsbar.createPris(tShirt, 70, 0);
        fredagsbar.createPris(polo, 100, 0);
        fredagsbar.createPris(cap, 30, 0);
        // Butiksprisliste:
        // klippekort
        butik.createPris(klippekortProdukt, 130, 0);
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
        butik.createPris(whiskey45pct, 599, 0);
        butik.createPris(whiskey43Pct, 499, 0);
        butik.createPris(uEgesplint, 300, 0);
        butik.createPris(mEgesplint, 350, 0);
        butik.createPris(toWhiskyGlasMedBrikker, 80, 0);
        butik.createPris(liquorOfAarhus, 175, 0);
        butik.createPris(lyngGin50Cl, 350, 0);
        // beklædning
        butik.createPris(tShirt, 70, 0);
        butik.createPris(polo, 100, 0);
        butik.createPris(cap, 30, 0);
    }

    public static void init() {
        initStorage();
    }
}
