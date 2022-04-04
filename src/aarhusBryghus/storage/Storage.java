package aarhusBryghus.storage;

import aarhusBryghus.application.model.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Storage implements Serializable {

    private static Storage uniqueInstance;
    public ArrayList<Produktgruppe> produktGrupper = new ArrayList<>();
    public ArrayList<Maaleenhed> maaleEnheder = new ArrayList<>();
    public ArrayList<Prisliste> prislister = new ArrayList<>();
    public ArrayList<Ordre> ordrer = new ArrayList<>();
    public ArrayList<Kunde> kunder = new ArrayList<>();

    public static Storage getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Storage();
        }
        return uniqueInstance;
    }

    // -------------------------------------------------------------------------

    public ArrayList<Kunde> getKunder() {
        return new ArrayList<>(kunder);
    }

    public void addKunde(Kunde kunde) {
        kunder.add(kunde);
    }

    public void removeKunde(Kunde kunde) {
        kunder.remove(kunde);
    }

    // -------------------------------------------------------------------------

    public ArrayList<Produktgruppe> getProduktGrupper() {
        return new ArrayList<>(produktGrupper);
    }

    public void addProduktGruppe(Produktgruppe produktGruppe) {
        produktGrupper.add(produktGruppe);
    }

    public void removeProduktGruppe(Produktgruppe produktGruppe) {
        produktGrupper.remove(produktGruppe);
    }

    // -------------------------------------------------------------------------

    public ArrayList<Maaleenhed> getMaaleEnheder() {
        return new ArrayList<>(maaleEnheder);
    }

    public void addMaaleEnhed(Maaleenhed maaleEnhed) {
        maaleEnheder.add(maaleEnhed);
    }

    public void removeMaaleEnhed(Maaleenhed maaleEnhed) {
        maaleEnheder.remove(maaleEnhed);
    }

    // -------------------------------------------------------------------------

    public  ArrayList<Prisliste> getPrislister() {
        return new ArrayList<>(prislister);
    }

    public void addPrisliste(Prisliste prisliste) {
        prislister.add(prisliste);
    }

    public void removePrisliste(Prisliste prisliste) {
        prislister.remove(prisliste);
    }

    // -------------------------------------------------------------------------

    public ArrayList<Ordre> getOrdrer() {
        return new ArrayList<>(ordrer);
    }

    public void addOrdre(Ordre ordre) {
        ordrer.add(ordre);
    }

    public void removeOrdre(Ordre ordre) {
        ordrer.remove(ordre);
    }
}
