package aarhusBryghus.setup;

import aarhusBryghus.application.controller.Controller;
import aarhusBryghus.application.model.*;
import aarhusBryghus.storage.Storage;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SetupInitializer {

    public static void main(String[] args) {
        initStorage();
    }

    private static void initStorage(){
        Controller controller = Controller.getInstance();
        // Kunder
        Kunde k1 = controller.createKunde("Alfonso","Trendy", 11111111);
        Kunde k2 = controller.createKunde("Broder","Salsa", 22222222);
        Kunde k3 = controller.createKunde("Conan","Lurbakke", 33333333);
        Kunde k4 = controller.createKunde("Jürgen","Halfpipe", 44444444);
        Kunde k5 = controller.createKunde("Preben","Hmph!", 55555555);

        // Prislister
        Prisliste butik = controller.createPrisliste("Butik");
        Prisliste fredagsbar = controller.createPrisliste("Fredagsbar");
        Prisliste reception = controller.createPrisliste("Reception");

        // Produktgrupper
        Produktgruppe klippekort = controller.createProduktGruppe("Klippekort");
        Produktgruppe flaske = controller.createProduktGruppe("Flaske");
        Produktgruppe fadoel = controller.createProduktGruppe("Fadøl");
        Produktgruppe madOgDrikke = controller.createProduktGruppe("Mad og drikke");
        Produktgruppe spiritus = controller.createProduktGruppe("Spiritus");
        Produktgruppe fustage = controller.createProduktGruppe("Fustage");
        Produktgruppe beklaedning = controller.createProduktGruppe("Beklædning");
        Produktgruppe anlaeg = controller.createProduktGruppe("Anlæg");
        Produktgruppe malt = controller.createProduktGruppe("Malt");
        Produktgruppe Kulsyre = controller.createProduktGruppe("Kulsyre");
        Produktgruppe glas = controller.createProduktGruppe("Glas");
        Produktgruppe sampakninger = controller.createProduktGruppe("Sampakninger");
        Produktgruppe rundvisning = controller.createProduktGruppe("Rundvisning");

        // Måleenheder
        Maaleenhed ingen = controller.createMaaleEnhed(null, 0);
        Maaleenhed fireCl = controller.createMaaleEnhed("cl", 4);
        Maaleenhed fyrreCl = controller.createMaaleEnhed("cl", 40);
        Maaleenhed halvtredsCl = controller.createMaaleEnhed("cl", 50);
        Maaleenhed tresCl = controller.createMaaleEnhed("cl", 60);
        Maaleenhed tyveLiter = controller.createMaaleEnhed("liter", 20);
        Maaleenhed seksKilo = controller.createMaaleEnhed("kilo", 6);
        Maaleenhed fireKilo = controller.createMaaleEnhed("kilo", 4);
        Maaleenhed tiKilo = controller.createMaaleEnhed("kilo", 10);
        Maaleenhed femogtyveKilo = controller.createMaaleEnhed("kilo",25);

        // Klippekort produkt
        Produkt klippekortProdukt4 = controller.createKlippekort("Klippekort, 4 klip", klippekort, ingen, 4);
        Produkt klippekortProdukt10 = controller.createKlippekort("Klippekort, 10 klip", klippekort, ingen, 10);
        Produkt klippekortProdukt20 = controller.createKlippekort("Klippekort, 20 klip", klippekort, ingen, 20);

        // Flaske produkt

        Produkt klosterbrygFlaske = controller.createProdukt("Klosterbryg", flaske, tresCl);
        Produkt sweetGeorgiaBrown = controller.createProdukt("Sweet Georgia Brown", flaske, tresCl);
        Produkt extraPilsnerFlaske = controller.createProdukt("Extra Pilsner", flaske, tresCl);
        Produkt celebrationFlaske = controller.createProdukt("Celebration", flaske, tresCl);
        Produkt blondieFlaske = controller.createProdukt("Blondie", flaske, tresCl);
        Produkt foraarsbrygFlaske = controller.createProdukt("Forårsbryg", flaske, tresCl);
        Produkt indiaPaleAleFlaske = controller.createProdukt("India Pale Ale", flaske, tresCl);
        Produkt julebrygFlaske = controller.createProdukt("Julebryg", flaske, tresCl);
        Produkt juletoenden = controller.createProdukt("Juletønden", flaske, tresCl);
        Produkt oldStrongAle = controller.createProdukt("Old Strong Ale", flaske, tresCl);
        Produkt fregattenJylland = controller.createProdukt("Fregatten Jylland", flaske, tresCl);
        Produkt imperialStoutFlaske = controller.createProdukt("Imperial Stout", flaske, tresCl);
        Produkt tribute = controller.createProdukt("Tribute", flaske, tresCl);
        Produkt blackMonster = controller.createProdukt("Black Monster", flaske, tresCl);

        // Fadøl, 40 cl produkter
        Produkt klosterbrygFadoel = controller.createProdukt("Klosterbryg", fadoel, fyrreCl);
        Produkt jazzClassic = controller.createProdukt("Jazz Classic", fadoel, fyrreCl);
        Produkt extraPilsnerFadoel = controller.createProdukt("Extra Pilsner", fadoel, fyrreCl);
        Produkt celebrationFadoel = controller.createProdukt("Celebration", fadoel, fyrreCl);
        Produkt blondieFadoel = controller.createProdukt("Blondie", fadoel, fyrreCl);
        Produkt foraarsbrygFadoel = controller.createProdukt("Forårsbryg", fadoel, fyrreCl);
        Produkt indiaPaleAleFadoel = controller.createProdukt("India Pale Ale", fadoel, fyrreCl);
        Produkt julebrygFadoel = controller.createProdukt("Julebryg", fadoel, fyrreCl);
        Produkt imperialStoutFadoel = controller.createProdukt("Imperial Stout", fadoel, fyrreCl);
        Produkt special = controller.createProdukt("Special", fadoel, fyrreCl);

        // mad og drikke-produkter
        Produkt aeblebrus = controller.createProdukt("Æblebrus", madOgDrikke, ingen);
        Produkt chips = controller.createProdukt("Chips", madOgDrikke, ingen);
        Produkt peanuts = controller.createProdukt("Peanuts", madOgDrikke, ingen);
        Produkt cola = controller.createProdukt("Cola", madOgDrikke, ingen);
        Produkt nikoline = controller.createProdukt("Nikoline", madOgDrikke, ingen);
        Produkt sevenUp = controller.createProdukt("7-Up", madOgDrikke, ingen);
        Produkt vand = controller.createProdukt("Vand", madOgDrikke, ingen);
        Produkt oelpoelser = controller.createProdukt("Ølpølser", madOgDrikke, ingen);

        // Spiritusprodukter
        Produkt whiskey45pct = controller.createProdukt("Whiskey 45% 50 cl rør", spiritus, halvtredsCl);
        Produkt whiskey4Cl = controller.createProdukt("Whiskey 4 cl", spiritus, fireCl);
        Produkt whiskey43Pct = controller.createProdukt("Whisky 43% 50 cl rør", spiritus, halvtredsCl);
        Produkt uEgesplint = controller.createProdukt("u/ egesplint", spiritus, ingen);
        Produkt mEgesplint = controller.createProdukt("m/ egesplint", spiritus, ingen);
        Produkt toWhiskyGlasMedBrikker = controller.createProdukt("2*whisky glas + brikker", spiritus, ingen);
        Produkt liquorOfAarhus = controller.createProdukt("Liquor of Aarhus", spiritus, ingen);
        Produkt lyngGin50Cl = controller.createProdukt("Lyng gin 50 cl", spiritus, ingen);
        Produkt lyngGin4Cl = controller.createProdukt("Lyng gin 4 cl", spiritus, fireCl);

        // beklædning
        Produkt tShirt = controller.createProdukt("T-Shirt", beklaedning, ingen);
        Produkt polo = controller.createProdukt("Polo", beklaedning, ingen);
        Produkt cap = controller.createProdukt("Cap", beklaedning, ingen);

        // PantProdukt
        Produkt klosterbrygFustage = controller.createPantProdukt("Klosterbryg", tyveLiter, 200, fustage);
        Produkt julebrygFustage = controller.createPantProdukt("Julebryg", tyveLiter, 200, fustage);
        Produkt kulsyre6kg = controller.createPantProdukt("Kulsyre, 6 kilo",seksKilo,1000,Kulsyre);
        Produkt kulsyre4kg = controller.createPantProdukt("Kulsyre, 4 kilo",fireKilo,1000,Kulsyre);
        Produkt kulsyre10kg = controller.createPantProdukt("Kulsyre, 10 kilo",tiKilo,1000,Kulsyre);

        // Ingredienser
        Produkt maltsaek = controller.createProdukt("Maltsæk",malt,femogtyveKilo);

        // anlaeg
        Produkt Bar1hane = controller.createProdukt("1-Hane",anlaeg,ingen);
        Produkt Bar2haner = controller.createProdukt("2-Haner",anlaeg,ingen);
        Produkt BarFlerehaner = controller.createProdukt("Bar med flere haner",anlaeg,ingen);
        Produkt LeveringafBar = controller.createProdukt("Levering",anlaeg,ingen);
        Produkt krusTilBar = controller.createProdukt("Krus",anlaeg,ingen);

        // Glas

        Produkt drikkeglas = controller.createProdukt("Glas",glas,ingen);
        // rundvisning
        Produkt rundvisningProdukt = controller.createRundvisning("Rundvisning", rundvisning,ingen, LocalDateTime.of(2022,1,10,12,0));

        fredagsbar.createPris(klippekortProdukt4, 130, 0);
        fredagsbar.createPris(klippekortProdukt10, 250, 0);
        fredagsbar.createPris(klippekortProdukt20, 450, 0);

        // Fredagsbar prisliste
        // klippekort
        fredagsbar.createPris(klippekortProdukt4, 130, 0);
        fredagsbar.createPris(klippekortProdukt10, 250, 0);
        fredagsbar.createPris(klippekortProdukt20, 450, 0);

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

        // fustage
        butik.createPris(klosterbrygFustage, 775, 0);
        butik.createPris(julebrygFustage, 775, 0);

        // fustage
        butik.createPris(klosterbrygFustage, 775, 0);
        butik.createPris(julebrygFustage, 775, 0);

        // kulsyre
        butik.createPris(kulsyre4kg,300,0);
        butik.createPris(kulsyre6kg,400,0);
        butik.createPris(kulsyre10kg,650,0);

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

        butik.createPris(Bar1hane,250,0);
        butik.createPris(Bar2haner,400,0);
        butik.createPris(BarFlerehaner,500,0);
        butik.createPris(LeveringafBar,500,0);
        butik.createPris(krusTilBar,60,0);


        Betalingsform klip1 = new Klip();
        Betalingsform kontant = new Kontant();
        Betalingsform dankort = new Dankort();
        Betalingsform mobilePay = new MobilePay();
        Betalingsform regning = new Regning();

        Ordre ordre1 = controller.createSalg(fredagsbar);
        controller.createOrdrelinjeSalg(ordre1, oldStrongAle, 1, ordre1.getPrisliste());
        controller.createOrdrelinjeSalg(ordre1, blackMonster, 1, ordre1.getPrisliste());
        controller.createOrdrelinjeSalg(ordre1, blondieFlaske, 1, ordre1.getPrisliste());
        controller.createOrdrelinjeSalg(ordre1, oelpoelser, 1, ordre1.getPrisliste());
        ordre1.setAfslutningsDato(LocalDate.now());
        ordre1.setOrdreStatus(true);
        ordre1.setBetalingsform(klip1);

        Ordre ordre2 = controller.createSalg(fredagsbar);
        controller.createOrdrelinjeSalg(ordre2, klippekortProdukt10, 1, fredagsbar);
        controller.createOrdrelinjeSalg(ordre2, whiskey4Cl, 1, ordre2.getPrisliste());
        controller.createOrdrelinjeSalg(ordre2, blackMonster, 2, ordre2.getPrisliste());
        controller.createOrdrelinjeSalg(ordre2, mEgesplint, 3, ordre2.getPrisliste());
        controller.createOrdrelinjeSalg(ordre2, klosterbrygFlaske, 3, ordre2.getPrisliste());
        ordre2.setAfslutningsDato(LocalDate.now());
        ordre2.setOrdreStatus(true);
        ordre2.setBetalingsform(regning);

        Ordre ordre3 = controller.createSalg(fredagsbar);
        controller.createOrdrelinjeSalg(ordre3, klosterbrygFlaske, 3, fredagsbar);
        controller.createOrdrelinjeSalg(ordre3, julebrygFlaske, 6, ordre3.getPrisliste());
        controller.createOrdrelinjeSalg(ordre3, blackMonster, 6, ordre3.getPrisliste());
        ordre3.setAfslutningsDato(LocalDate.now());
        ordre3.setOrdreStatus(true);
        ordre3.setBetalingsform(kontant);

        Ordre ordre4 = controller.createSalg(fredagsbar);
        controller.createOrdrelinjeSalg(ordre4, indiaPaleAleFadoel, 1, ordre4.getPrisliste());
        ordre4.setAfslutningsDato(LocalDate.now());
        ordre4.setOrdreStatus(true);
        ordre4.setBetalingsform(klip1);


        Ordre ordre5 = controller.createUdlejning(butik, k1);
        Ordre ordre6 = controller.createUdlejning(butik, k2);
        Ordrelinje ordrelinje1PaaOrdre5 = controller.createOrdrelinjeUdlejning(ordre5, klosterbrygFustage, 2, butik);
        Ordrelinje ordrelinje2PaaOrdre5 = controller.createOrdrelinjeUdlejning(ordre5, julebrygFustage, 2, butik);
        Ordrelinje ordrelinje3PaaOrdre5 = controller.createOrdrelinjeUdlejning(ordre5, Bar1hane, 1, butik);
        Ordrelinje ordrelinje4PaaOrdre5 = controller.createOrdrelinjeUdlejning(ordre5, LeveringafBar, 1, butik);
        Ordrelinje ordrelinje5PaaOrdre5 = controller.createOrdrelinjeUdlejning(ordre5, kulsyre6kg, 1, butik);

        Ordrelinje ordrelinje1PaaOrdre6 = controller.createOrdrelinjeUdlejning(ordre6, Bar2haner, 1, butik);
        Ordrelinje ordrelinje2PaaOrdre6 = controller.createOrdrelinjeUdlejning(ordre6, julebrygFustage, 2, butik);
        Ordrelinje ordrelinje3PaaOrdre6 = controller.createOrdrelinjeUdlejning(ordre6, kulsyre6kg, 1, butik);
        Ordrelinje ordrelinje4PaaOrdre6 = controller.createOrdrelinjeUdlejning(ordre6, klosterbrygFustage, 2, butik);

        ordre3.setKunde(k3);
        ordre4.setKunde(k4);
        ordre1.setKunde(k2);
        ordre2.setKunde(k2);

        controller.saveStorage();
        System.out.println("Storage kunder: " + Storage.getStorage().getKunder());

        String regex = "\\d+";
        String data = "23343453";
        System.out.println(data.matches(regex));
    }
}
