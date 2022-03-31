package aarhusBryghus.application.controller;

import aarhusBryghus.application.model.*;
import aarhusBryghus.storage.Storage;

import java.time.LocalDate;
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

    // createSalg er metoden som bruges, til af kasseapperatet. de oprettes altid med fast dato, og som lukkede.
    public static Ordre createSalg(Prisliste prisliste) {
        Ordre ordre = new Ordre("Salg", true, LocalDate.now(), prisliste);
        Storage.addOrdre(ordre);
        return ordre;
    }

    // createOrdrelinjeSalg opretter enkelte salgslinjer, til en ordre, men KUN i "Kasseapparat" tabben!
    public static Ordrelinje createOrdrelinjeSalg(Ordre ordre, Produkt produkt, int antal, Prisliste prisliste) {
        Ordrelinje ordrelinje = ordre.createOrdrelinje(antal, produkt);
        if (prisliste.getAntalKlip(produkt) > 0) {
            ordrelinje.setKlip(prisliste.getAntalKlip(produkt));
        }
        return ordrelinje;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Produkt. Create, get, delete, update.
    public static Produkt createProdukt(String navn, Produktgruppe produktgruppe, MaaleEnhed maaleEnhed) {
        Produkt produkt = produktgruppe.createProdukt(navn, maaleEnhed);
        return produkt;
    }

    public static ArrayList<Produkt> getAlleProdukter(Produktgruppe produktgruppe) {
        for (Produktgruppe pg : Storage.getProduktGrupper()) {
            if (pg == produktgruppe) {
                System.out.println("Produktgruppe: " + produktgruppe);
                return pg.getProdukter();
            }
        }
        return null;
    }

    public static void deleteProdukt(Produkt produkt) {
        for (Produktgruppe p : Storage.getProduktGrupper()) {
            for (Produkt prod : p.getProdukter()) {
                if (prod == produkt) {
                    p.removeProdukt(produkt);
                }
            }
        }
    }

    // henter produktets produktgruppe
    public static Produktgruppe getProduktGruppe(Produkt produkt) {
        for (Produktgruppe pg : Storage.getProduktGrupper()) {
            for (Produkt prod : pg.getProdukter()) {
                if (prod.equals(produkt)) {
                    return pg;
                }
            }
        }
        return null;
    }

    // returnerer den første prisliste, som findes på et produkt
    public static Prisliste getProduktPrisliste(Produkt produkt) {
        for (Prisliste pl : Storage.getPrislister()) {
            for (Pris pris : produkt.getPriser()) {
                if (pris.getPrisliste() == pl) {
                    return pl;
                }
            }
        }
        return null;
    }

    //Henter de prislister, som produktet i parameteren IKKE har. // todo brug sortering/compareTo i stedet for
    public static ArrayList<Prisliste> getAndrePrislister(Produkt produkt) {
        ArrayList<Prisliste> liste = new ArrayList<>();
        ArrayList<Prisliste> listeNy = Storage.getPrislister();
        for (Pris pris : produkt.getPriser()) {
            liste.add(pris.getPrisliste());
        }
        for (int i = 0; i < Storage.getPrislister().size(); i++) {
            for (int j = 0; j < liste.size(); j++) {
                if (Storage.getPrislister().get(i) == liste.get(j)) {
                    listeNy.remove(liste.get(j));
                    j = liste.size();
                }
            }
        }
        return listeNy;
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

    public static void deleteProduktgruppe(Produktgruppe produktGruppe) {
        Storage.removeProduktGruppe(produktGruppe);
    }

    public static void updateProduktgruppe(Produktgruppe produktGruppe, String navn) {
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

    public static void deleteMaaleenhed(MaaleEnhed maaleEnhed) {
        Storage.removeMaaleEnhed(maaleEnhed);
    }

    public static void updateMaaleenhed(MaaleEnhed maaleEnhed, String enhed, int tal) {
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

    public static void deletePrisliste(Prisliste prisliste) {
        Storage.removePrisliste(prisliste);
    }

    public static void updatePrisliste(Prisliste prisliste, String navn) {
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

    public static ArrayList<Ordre> getDagensSalg() {
        ArrayList<Ordre> alleSalg = new ArrayList<>();
        for (int i = 0; i < Storage.getOrdrer().size(); i++) {
            if (Storage.getOrdrer().get(i).getAfslutningsDato().equals(LocalDate.now())) {
                alleSalg.add(Storage.getOrdrer().get(i));
            }
        }
        return alleSalg;
    }

    public static ArrayList<String> udprintOrdre(Ordre ordre) {
        ArrayList<String> alleSalg = new ArrayList<>();
        for (int j = 0; j < ordre.getOrdrelinjer().size(); j++) {
            Ordrelinje ol = ordre.getOrdrelinjer().get(j);
            if (ordre.getBetalingsform().getType().equals("Klip")) {
                alleSalg.add("Navn: " + ol.getProdukt() + ", antal: " + ol.getAntal() + ", klip pr. styk: " + ol.getKlip() + ", samlet klippepris: "
                        + ol.getSamletPrisKlip() + ", betalingsform: " + ordre.getBetalingsform());
            } else {
                alleSalg.add("Navn: " + ol.getProdukt() + ", antal: " + ol.getAntal() + ", pris pr. styk: " + ol.getPris() + ", samlet pris: "
                        + ol.getSamletPris() + ", betalingsform: " + ordre.getBetalingsform());
            }

        }
        return alleSalg;
    }

    public static int getAntalSolgteKlip(LocalDate startdato, LocalDate slutdato) {
        int solgteKlip = 0;
        for (int i = 0; i < Storage.getOrdrer().size(); i++) {
            Ordre o = Storage.getOrdrer().get(i);
            if (o.getAfslutningsDato().isBefore(slutdato) && o.getAfslutningsDato().isAfter(startdato) ||
                    o.getAfslutningsDato().equals(startdato) || o.getAfslutningsDato().equals(slutdato)) {
                for (int j = 0; j < o.getOrdrelinjer().size(); j++) {
                    solgteKlip += o.getOrdrelinjer().get(j).getProdukt().getAntalKlip();

                }
            }
        }
        return solgteKlip;
    }

    public static int getAntalForbrugteKlip(LocalDate startdato, LocalDate slutdato) {
        int forbrugteKlip = 0;
        for (int i = 0; i < Storage.getOrdrer().size(); i++) {
            Ordre o = Storage.getOrdrer().get(i);
            if (o.getAfslutningsDato().isBefore(slutdato) && o.getAfslutningsDato().isAfter(startdato) ||
                    o.getAfslutningsDato().equals(startdato) || o.getAfslutningsDato().equals(slutdato)) {
                for (int j = 0; j < o.getOrdrelinjer().size(); j++) {
                    forbrugteKlip += o.getOrdrelinjer().get(j).getSamletPrisKlip();
                }
            }
        }
        return forbrugteKlip;
    }

    public static void lukSalg(Ordre ordre, Prisliste prisliste, LocalDate afslutDato, boolean status, Betalingsform betalingsform) {
        ordre.setAfslutningsDato(afslutDato);
        ordre.setOrdreStatus(status);
        ordre.setBetalingsform(betalingsform);
        if(betalingsform.getType().equals("Klip")){
            for(Ordrelinje ol: ordre.getOrdrelinjer()){
                ol.setKlip(ol.getProdukt().getklippekortPris(prisliste));
            }
        }
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
        MaaleEnhed ingen = Controller.createMaaleEnhed(null, 0);
        MaaleEnhed fireCl = Controller.createMaaleEnhed("cl", 4);
        MaaleEnhed fyrreCl = Controller.createMaaleEnhed("cl", 40);
        MaaleEnhed halvtredsCl = Controller.createMaaleEnhed("cl", 50);
        MaaleEnhed tresCl = Controller.createMaaleEnhed("cl", 60);
        MaaleEnhed tyveLiter = Controller.createMaaleEnhed("liter", 20);

        // Klippekort produkt
        Produkt klippekortProdukt4 = Controller.createKlippekort("Klippekort, 4 klip", klippekort, ingen, 4);
        Produkt klippekortProdukt10 = Controller.createKlippekort("Klippekort, 10 klip", klippekort, ingen, 10);
        Produkt klippekortProdukt20 = Controller.createKlippekort("Klippekort, 20 klip", klippekort, ingen, 20);

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
        Produkt aeblebrus = Controller.createProdukt("Æblebrus", madOgDrikke, ingen);
        Produkt chips = Controller.createProdukt("Chips", madOgDrikke, ingen);
        Produkt peanuts = Controller.createProdukt("Peanuts", madOgDrikke, ingen);
        Produkt cola = Controller.createProdukt("Cola", madOgDrikke, ingen);
        Produkt nikoline = Controller.createProdukt("Nikoline", madOgDrikke, ingen);
        Produkt sevenUp = Controller.createProdukt("7-Up", madOgDrikke, ingen);
        Produkt vand = Controller.createProdukt("Vand", madOgDrikke, ingen);
        Produkt oelpoelser = Controller.createProdukt("Ølpølser", madOgDrikke, ingen);

        // Spiritusprodukter
        Produkt whiskey45pct = Controller.createProdukt("Whiskey 45% 50 cl rør", spiritus, halvtredsCl);
        Produkt whiskey4Cl = Controller.createProdukt("Whiskey 4 cl", spiritus, fireCl);
        Produkt whiskey43Pct = Controller.createProdukt("Whisky 43% 50 cl rør", spiritus, halvtredsCl);
        Produkt uEgesplint = Controller.createProdukt("u/ egesplint", spiritus, ingen);
        Produkt mEgesplint = Controller.createProdukt("m/ egesplint", spiritus, ingen);
        Produkt toWhiskyGlasMedBrikker = Controller.createProdukt("2*whisky glas + brikker", spiritus, ingen);
        Produkt liquorOfAarhus = Controller.createProdukt("Liquor of Aarhus", spiritus, ingen);
        Produkt lyngGin50Cl = Controller.createProdukt("Lyng gin 50 cl", spiritus, ingen);
        Produkt lyngGin4Cl = Controller.createProdukt("Lyng gin 4 cl", spiritus, fireCl);

        // beklædning
        Produkt tShirt = Controller.createProdukt("t-shirt", beklaedning, ingen);
        Produkt polo = Controller.createProdukt("polo", beklaedning, ingen);
        Produkt cap = Controller.createProdukt("cap", beklaedning, ingen);

        // Fredagsbar prisliste
        // klippekort
        fredagsbar.createPris(klippekortProdukt4, 130, 4);
        fredagsbar.createPris(klippekortProdukt10, 250, 10);
        fredagsbar.createPris(klippekortProdukt20, 450, 20);

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
        butik.createPris(klippekortProdukt4, 130, 0);
        butik.createPris(klippekortProdukt10, 250, 0);
        butik.createPris(klippekortProdukt20, 450, 0);

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

        Betalingsform klip1 = new Klip();
        Betalingsform kontant = new Kontant();
        Betalingsform dankort = new Dankort();
        Betalingsform mobilePay = new MobilePay();
        Betalingsform regning = new Regning();


        Ordre ordre1 = Controller.createSalg(fredagsbar);
        Controller.createOrdrelinjeSalg(ordre1,oldStrongAle,1,ordre1.getPrisliste());
        Controller.createOrdrelinjeSalg(ordre1,blackMonster,2,ordre1.getPrisliste());
        Controller.createOrdrelinjeSalg(ordre1,blondieFlaske,2,ordre1.getPrisliste());
        Controller.createOrdrelinjeSalg(ordre1,oelpoelser,2,ordre1.getPrisliste());
        lukSalg(ordre1,fredagsbar, LocalDate.now(), true, klip1);

        Ordre ordre2 = Controller.createSalg(fredagsbar);
        Controller.createOrdrelinjeSalg(ordre2,whiskey4Cl,2,ordre1.getPrisliste());
        Controller.createOrdrelinjeSalg(ordre2,blackMonster,3,ordre1.getPrisliste());
        Controller.createOrdrelinjeSalg(ordre2,mEgesplint,5,ordre1.getPrisliste());
        Controller.createOrdrelinjeSalg(ordre2,lyngGin4Cl,9,ordre1.getPrisliste());
        lukSalg(ordre2, fredagsbar, LocalDate.now(), true, dankort);


    }

    public static void init() {
        initStorage();
    }

    public static void removePrislisteOgProduktFraPris(Prisliste prisliste, Produkt produkt) {
        for (Pris p : prisliste.getPriser()) {
            if (p.getProdukt() == produkt) {
                p.setPrisliste(null);
                p.setProdukt(null);
            }
        }

    }
}
