package aarhusBryghus.storage;

import aarhusBryghus.application.model.Produkt;

import java.util.ArrayList;

public class Storage {

    private static Storage uniqueInstance;

    public static Storage getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Storage();
        }
        return uniqueInstance;
    }

    private ArrayList<Produkt> produkter = new ArrayList<>();

    // -------------------------------------------------------------------------

    public ArrayList<Produkt> getCompanies() {
        return new ArrayList<Produkt>(produkter);
    }

    public void addCompany(Produkt produkt) {
        produkter.add(produkt);
    }

    public void removeCompany(Produkt produkt) {
        produkter.remove(produkt);
    }

    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
}
