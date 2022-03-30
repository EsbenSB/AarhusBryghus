package aarhusBryghus.storage;

import aarhusBryghus.application.model.*;

import java.util.ArrayList;

public class Storage {

    private static Storage uniqueInstance;
    public static ArrayList<Produktgruppe> produktGrupper = new ArrayList<>();
    public static ArrayList<MaaleEnhed> maaleEnheder = new ArrayList<>();
    public static ArrayList<Prisliste> prislister = new ArrayList<>();
    public static ArrayList<Ordre> ordrer = new ArrayList<>();

    public static Storage getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Storage();
        }
        return uniqueInstance;
    }

    // -------------------------------------------------------------------------

    public static ArrayList<Produktgruppe> getProduktGrupper() {
        return new ArrayList<>(produktGrupper);
    }

    public static void addProduktGruppe(Produktgruppe produktGruppe) {
        produktGrupper.add(produktGruppe);
    }

    public static void removeProduktGruppe(Produktgruppe produktGruppe) {
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

    // -------------------------------------------------------------------------

    public static ArrayList<Ordre> getOrdrer() {
        return new ArrayList<>(ordrer);
    }

    public static void addOrdre(Ordre ordre) {
        ordrer.add(ordre);
    }

    public static void removeOrdre(Ordre ordre) {
        ordrer.remove(ordre);
    }
}
