package aarhusBryghus.storage;

import aarhusBryghus.application.model.MaaleEnhed;
import aarhusBryghus.application.model.Prisliste;
import aarhusBryghus.application.model.Produkt;
import aarhusBryghus.application.model.ProduktGruppe;

import java.util.ArrayList;

public class Storage {

    private static Storage uniqueInstance;
    public static ArrayList<Produkt> produkter = new ArrayList<>();
    public static ArrayList<ProduktGruppe> produktGrupper = new ArrayList<>();
    public static ArrayList<MaaleEnhed> maaleEnheder = new ArrayList<>();
    public static ArrayList<Prisliste> prislister = new ArrayList<>();

    public static Storage getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Storage();
        }
        return uniqueInstance;
    }



    // -------------------------------------------------------------------------

    public static ArrayList<Produkt> getProdukter() {
        return new ArrayList<>(produkter);
    }

    public static void addProdukt(Produkt produkt) {
        produkter.add(produkt);
    }

    public static void removeProdukt(Produkt produkt) {
        produkter.remove(produkt);
    }

    // -------------------------------------------------------------------------

    public static ArrayList<ProduktGruppe> getProduktGrupper() {
        return new ArrayList<>(produktGrupper);
    }

    public static void addProduktGruppe(ProduktGruppe produktGruppe) {
        produktGrupper.add(produktGruppe);
    }

    public static void removeProduktGruppe(ProduktGruppe produktGruppe) {
        produktGrupper.remove(produktGruppe);
    }

    // -------------------------------------------------------------------------

    public static ArrayList<MaaleEnhed> getMaaleEnheder() {
        return new ArrayList<>(maaleEnheder);
    }

    public static void addMaaleEnhed(MaaleEnhed maaleEnhed) {
        maaleEnheder.add(maaleEnhed);
    }

    public static void removeMaaleEnhed(MaaleEnhed maaleEnhed) {
        maaleEnheder.remove(maaleEnhed);
    }

    // -------------------------------------------------------------------------

    public static ArrayList<Prisliste> getPrislister() {
        return new ArrayList<>(prislister);
    }

    public static void addPrisliste(Prisliste prisliste) {
        prislister.add(prisliste);
    }

    public static void removePrisliste(Prisliste prisliste) {
        prislister.remove(prisliste);
    }
}
