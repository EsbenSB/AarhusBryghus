package aarhusBryghus.application.controller;

import aarhusBryghus.application.model.*;
import aarhusBryghus.storage.Storage;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

public class Controller {


    private static Controller uniqueInstance;
    private Storage storage;

    public static Controller getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new Controller();
        }
        return uniqueInstance;
    }


    private Controller(){
        storage = aarhusBryghus.storage.Storage.getInstance();
    }

    // metode, til combobox i "produktTab"
    public ArrayList<String> getElementTyper(){
        ArrayList<String> elementTyper = new ArrayList<>();
        elementTyper.add("Produkt");
        elementTyper.add("Produktgruppe");
        elementTyper.add("Måleenhed");
        elementTyper.add("Prisliste");
        return elementTyper;
    }

    public ArrayList<String> getProduktTyper(){
        ArrayList<String> produkttyper = new ArrayList<>();
        produkttyper.add("Produkt");
        produkttyper.add("PantProdukt");
        produkttyper.add("Klippekort");
        return produkttyper;
    }

    // METODER TIL UDLEJNING
    public Kunde findKunde(int mobilnummer) {
        Kunde kunde = null;
        int i = 0;
        while (kunde == null && i < Storage.getInstance().getKunder().size()) {
            if (Storage.getInstance().getKunder().get(i).getTelefon() == mobilnummer) {
                kunde = Storage.getInstance().getKunder().get(i);
            } else {
                i++;
            }
        }
        return kunde;
    }

    //Virker kun med udlejninger!
    // skal køres umiddelbart efter ordren er oprettet og panten er betalt
    public void setPrisMinusPant(Ordre ordre) {
        if (ordre.getType().equalsIgnoreCase("Udlejning")) {
            for (Ordrelinje ol : ordre.getOrdrelinjer()) {
                if (ol.getSamletPant() != 0) {
                    ol.setPris(ol.getPris() - ol.getProdukt().getPant());
                }
            }
        }
    }

    public ArrayList<Ordre> getKundeUdlejninger(Kunde kunde) {
        ArrayList<Ordre> kundensUdlejninger = new ArrayList<>();
        for (int i = 0; i < Storage.getInstance().getOrdrer().size(); i++) {
            Ordre o = Storage.getInstance().getOrdrer().get(i);
                if (kunde.equals(o.getKunde()) && o.getType().equalsIgnoreCase("udlejning")) {
                    if (!kundensUdlejninger.contains(o)){
                        kundensUdlejninger.add(o);
                    }
                }
        }
        return kundensUdlejninger;
    }

    public ArrayList<Ordre> getKundensSamledeOrdre(Kunde kunde) {
        ArrayList<Ordre> kundensSamledeOrdre = new ArrayList<>();
        for (int i = 0; i < Storage.getInstance().getOrdrer().size(); i++) {
            Ordre o = Storage.getInstance().getOrdrer().get(i);
            if (o.isErOrdrenLukket()) {
                if (kunde.equals(o.getKunde())) {
                    if (!kundensSamledeOrdre.contains(o))
                        kundensSamledeOrdre.add(o);
                }
            }
        }
        return kundensSamledeOrdre;
    }

    public double getKundesSamledeKoeb(ArrayList<Ordre> ordrer) {
        double sum = 0.0;
        for (Ordre o : ordrer) {
            sum += o.getSamletPris();
        }
        return sum;
    }

    // returnerer en liste med alle Ordre, som indeholder typen "udlejning", og ikke er lukket.
    public ArrayList<Ordre> getNuværendeUdlejninger() {
        ArrayList<Ordre> nuværendeUdlejninger = new ArrayList<>();
        for (Ordre o : Storage.getInstance().getOrdrer()) {
            if (o.getType().equals("Udlejning") && !o.erOrdrenLukket())
                nuværendeUdlejninger.add(o);
        }
        return new ArrayList<>(nuværendeUdlejninger);
    }

    // Returnerer samlet sum, for alle dagens salg (ikke klip!) - dagens "totalte omsætning"
    public double getSamletSumDagensSalg(LocalDate localDate) {
        double sum = 0;
        for (Ordre o : Storage.getInstance().getOrdrer()) {
            if (o.getAfslutningsDato() != null && o.getAfslutningsDato().equals(localDate) && !o.getBetalingsform().getType().equals("Klip")) {
                sum += o.getSamletPris();
            }
        }
        return sum;
    }

    // create pant produkt
    public Produkt createPantProdukt(String navn, Maaleenhed maaleEnhed, int pant, Produktgruppe produktgruppe) {
        Produkt produkt = produktgruppe.createPantProdukt(navn, maaleEnhed, pant);
        return produkt;
    }

    // createSalg er metoden som bruges, til af kasseapperatet. de oprettes altid med fast dato, og som lukkede.
    public Ordre createSalg(Prisliste prisliste) {
        Ordre ordre = new Ordre("Salg", true, LocalDate.now(), prisliste);
        Storage.getInstance().addOrdre(ordre);
        return ordre;
    }

    public Ordre createRundvisning (Prisliste prisliste, Kunde kunde, LocalDateTime tidspunkt, Produktgruppe produktgruppe, double prisPerPerson) {
        Ordre rundvisning = new Ordre("Rundvisning", false, LocalDate.now(), prisliste);
        rundvisning.setKunde(kunde);
        Rundvisning rundvisningProdukt = produktgruppe.createRundvisning("Rundvisning",Controller.getInstance().getEmptyMaaleenhed(),tidspunkt);
        prisliste.createPris(rundvisningProdukt,prisPerPerson, 0);
        rundvisning.createOrdrelinje(1,rundvisningProdukt);
        Storage.getInstance().addOrdre(rundvisning);
        return rundvisning;
    }

    public ArrayList<Ordre> getNuværendeRundvisninger() {
        ArrayList<Ordre> nuværendeRundvisninger = new ArrayList<>();
        for (Ordre o : Storage.getInstance().getOrdrer()) {
            if (o.getType().equalsIgnoreCase("Rundvisning") && !o.erOrdrenLukket())
                nuværendeRundvisninger.add(o);
        }
        return new ArrayList<>(nuværendeRundvisninger);
    }

    public Produktgruppe getRundvisningsProduktgruppe() {
        Produktgruppe produktgruppe = null;
        for (Produktgruppe p : Storage.getInstance().getProduktGrupper()) {
            if (p.getNavn().equalsIgnoreCase("rundvisning")){
                produktgruppe = p;
            }
        }
        return produktgruppe;
    }

    public Prisliste getButikPrisliste() {
        Prisliste prisliste = null;
        for (Prisliste p : Storage.getInstance().getPrislister()) {
            if (p.getNavn().equalsIgnoreCase("Butik")){
                prisliste = p;
            }
        }
        return prisliste;
    }
// todo: tvivler på at nedenstående skal bruges
    /*public static ArrayList<String> getNuværendeRundvisninger() {
        ArrayList<String> nuværendeRundvisninger = new ArrayList<>();
        for (int i = 0; i < Storage.getOrdrer().size(); i++) {
            Ordre o = Storage.getOrdrer().get(i);
            if (o.getType().equals("Rundvisning") && !o.erOrdrenLukket()) {
                for (int j = 0; j < o.getOrdrelinjer().size(); j++) {
                    nuværendeRundvisninger.add(o.getKunde().getFornavn() + " " + o.getKunde().getEfternavn()
                            + ", " + o.getKunde().getTelefon() + ", " + o.getOrdrelinjer().get(j).getProdukt());
                }
            }
        }
        return new ArrayList<>(nuværendeRundvisninger);
    }*/

    public Ordre createUdlejning (Prisliste prisliste, Kunde kunde) {
        Ordre udlejning = new Ordre("Udlejning", false, LocalDate.now(), prisliste);
        udlejning.setKunde(kunde);
        Storage.getInstance().addOrdre(udlejning);
        return udlejning;
    }

    // createOrdrelinjeSalg opretter enkelte salgslinjer, til en ordre, men KUN i "Kasseapparat" tabben!
    public Ordrelinje createOrdrelinjeSalg(Ordre ordre, Produkt produkt, int antal, Prisliste prisliste) {
        Ordrelinje ordrelinje = ordre.createOrdrelinje(antal, produkt);
        if (prisliste.getAntalKlip(produkt) > 0) {
            ordrelinje.setKlip(prisliste.getAntalKlip(produkt));
        }
        return ordrelinje;
    }
    //Opretter en ordrelinje, på ordren, som udlejning. Sætter prisen, på
    // ordrelinjen, til at være = panten på det tilføjede produkt
    // NB: Produkter uden pant, bliver tilføjet normalt, men prisen sættes til 0! (fordi den endelig afregning sker ved afslutning)
    public Ordrelinje createOrdrelinjeUdlejning(Ordre ordre, Produkt produkt, int antal, Prisliste prisliste){
        Ordrelinje ordrelinje = ordre.createOrdrelinje(antal, produkt);
        return ordrelinje;
    }
    //Lukker rundvisningen - ligesom en udlejning
    public void lukRundvisningOrdre(Ordre ordre, Betalingsform betalingsform){
        Controller.getInstance().lukUdlejningOrdre(ordre,betalingsform);
    }

    //Lukker ordren, hvis den ikke er lukket
    public void lukUdlejningOrdre(Ordre ordre, Betalingsform betalingsform){
        if(!ordre.erOrdrenLukket()){
            ordre.setAfslutningsDato(LocalDate.now());
            ordre.setOrdreStatus(true);
            ordre.setBetalingsform(betalingsform);
        }
    }

    public Maaleenhed getEmptyMaaleenhed(){
        for(Maaleenhed me: Controller.getInstance().getMaaleEnheder()){
            if(me.getEnhed() == null){
                return me;
            }
        }
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Produkt. Create, get, delete, update.
    public Produkt createProdukt(String navn, Produktgruppe produktgruppe, Maaleenhed maaleEnhed) {
        Produkt produkt = produktgruppe.createProdukt(navn, maaleEnhed);
        return produkt;
    }

    public Kunde createKunde(String fornavn, String efternavn, int telefon) {
        Kunde kunde = new Kunde(fornavn, efternavn, telefon);
        Storage.getInstance().addKunde(kunde);
        return kunde;
    }

    public void updateKunde(Kunde kunde, String fornavn, String efternavn, int telefon) {
        kunde.setFornavn(fornavn);
        kunde.setEfternavn(efternavn);
        kunde.setTelefon(telefon);
    }

    public ArrayList<Produkt> getAlleProdukter(Produktgruppe produktgruppe) {
        for (Produktgruppe pg : Storage.getInstance().getProduktGrupper()) {
            if (pg == produktgruppe) {
                System.out.println("Produktgruppe: " + produktgruppe);
                return pg.getProdukter();
            }
        }
        return null;
    }

    public void deleteProdukt(Produkt produkt) {
        for (Produktgruppe p : Storage.getInstance().getProduktGrupper()) {
            for (Produkt prod : p.getProdukter()) {
                if (prod == produkt) {
                    p.removeProdukt(produkt);
                }
            }
        }
    }

    // henter produktets produktgruppe
    public Produktgruppe getProduktGruppe(Produkt produkt) {
        for (Produktgruppe pg : Storage.getInstance().getProduktGrupper()) {
            for (Produkt prod : pg.getProdukter()) {
                if (prod.equals(produkt)) {
                    return pg;
                }
            }
        }
        return null;
    }

    // returnerer den første prisliste, som findes på et produkt
    public Prisliste getProduktPrisliste(Produkt produkt) {
        for (Prisliste pl : Storage.getInstance().getPrislister()) {
            for (Pris pris : produkt.getPriser()) {
                if (pris.getPrisliste() == pl) {
                    return pl;
                }
            }
        }
        return null;
    }

    //Henter de prislister, som produktet i parameteren IKKE har. // todo brug sortering/compareTo i stedet for
    public ArrayList<Prisliste> getAndrePrislister(Produkt produkt) {
        ArrayList<Prisliste> liste = new ArrayList<>();
        ArrayList<Prisliste> listeNy = Storage.getInstance().getPrislister();
        for (Pris pris : produkt.getPriser()) {
            liste.add(pris.getPrisliste());
        }
        for (int i = 0; i < Storage.getInstance().getPrislister().size(); i++) {
            for (int j = 0; j < liste.size(); j++) {
                if (Storage.getInstance().getPrislister().get(i) == liste.get(j)) {
                    listeNy.remove(liste.get(j));
                    j = liste.size();
                }
            }
        }
        return listeNy;
    }


    //------------------------------------------------------------------------------------------------------------------

    // Klippekort. Create, get, delete, update.
    public Klippekort createKlippekort(String navn, Produktgruppe produktgruppe, Maaleenhed maaleEnhed, int antalKlip) {
        Klippekort klippekort = produktgruppe.createKlippekort(navn, maaleEnhed, antalKlip);
        return klippekort;
    }

    //------------------------------------------------------------------------------------------------------------------

    // Rundvisning. Create, get, delete, update.
    public Rundvisning createRundvisning(String navn, Produktgruppe produktgruppe, Maaleenhed maaleEnhed, LocalDateTime tidspunkt) {
        Rundvisning rundvisning = produktgruppe.createRundvisning(navn, maaleEnhed, tidspunkt);
        return rundvisning;
    }

    //------------------------------------------------------------------------------------------------------------------

    // Produktgruppe. create, get, delete, update
    public Produktgruppe createProduktGruppe(String navn) {
        Produktgruppe produktGruppe = new Produktgruppe(navn);
        Storage.getInstance().addProduktGruppe(produktGruppe);
        return produktGruppe;
    }

    public ArrayList<Produktgruppe> getProduktGrupper() {
        return Storage.getInstance().getProduktGrupper();
    }

    public void deleteProduktgruppe(Produktgruppe produktGruppe) {
        Storage.getInstance().removeProduktGruppe(produktGruppe);
    }

    public void updateProduktgruppe(Produktgruppe produktGruppe, String navn) {
        produktGruppe.setNavn(navn);
    }

    //------------------------------------------------------------------------------------------------------------------

    // Måleenhed. Create, get, delete, update.
    public Maaleenhed createMaaleEnhed(String navn, int tal) {
        Maaleenhed maaleEnhed = new Maaleenhed(navn, tal);
        Storage.getInstance().addMaaleEnhed(maaleEnhed);
        return maaleEnhed;
    }

    public ArrayList<Maaleenhed> getMaaleEnheder() {
        return Storage.getInstance().getMaaleEnheder();
    }

    public void deleteMaaleenhed(Maaleenhed maaleEnhed) {
        Storage.getInstance().removeMaaleEnhed(maaleEnhed);
    }

    public void updateMaaleenhed(Maaleenhed maaleEnhed, String enhed, int tal) {
        maaleEnhed.setEnhed(enhed);
        maaleEnhed.setTal(tal);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Prisliste. Create, get, delete, update.
    public Prisliste createPrisliste(String navn) {
        Prisliste prisliste = new Prisliste(navn);
        Storage.getInstance().addPrisliste(prisliste);
        return prisliste;
    }

    public ArrayList<Prisliste> getPrislister() {
        return Storage.getInstance().getPrislister();
    }

    public void deletePrisliste(Prisliste prisliste) {
        Storage.getInstance().removePrisliste(prisliste);
    }

    public void updatePrisliste(Prisliste prisliste, String navn) {
        prisliste.setNavn(navn);
    }

    //------------------------------------------------------------------------------------------------------------------

    public HashSet<Produkt> listeOverProdukterProduktgruppePaaPrisliste(Produktgruppe produktgruppe, Prisliste prisliste) {
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

    public HashSet<Produktgruppe> listeProduktgrupperTilValgtePrisliste(Prisliste prisliste) {
        HashSet<Produktgruppe> produktGrupper = new HashSet<>();
        for (Produktgruppe g : Storage.getInstance().getProduktGrupper()) {
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

    public ArrayList<Ordre> getDagensSalg() {
        ArrayList<Ordre> alleSalg = new ArrayList<>();
        for (int i = 0; i < Storage.getInstance().getOrdrer().size(); i++) {
            if (Storage.getInstance().getOrdrer().get(i).getAfslutningsDato() != null && Storage.getInstance().getOrdrer().get(i).getAfslutningsDato().equals(LocalDate.now())) {
                alleSalg.add(Storage.getInstance().getOrdrer().get(i));
            }
        }
        return alleSalg;
    }

    public ArrayList<String> udprintOrdre(Ordre ordre) {
        ArrayList<String> alleSalg = new ArrayList<>();
        for (int j = 0; j < ordre.getOrdrelinjer().size(); j++) {
            Ordrelinje ol = ordre.getOrdrelinjer().get(j);
            if (ordre.getBetalingsform().getType().equals("Klip")) {
                alleSalg.add(ol.getAntal() + " x " + ol.getProdukt() + ", Klip pr. styk: " + ol.getKlip() + ", samlet Klip: "
                        + ol.getSamletPrisKlip());
            } else {
                alleSalg.add(ol.getAntal() + " x " + ol.getProdukt() + ", pris pr. styk: " + ol.getPris() + ", samlet pris: "
                        + ol.getSamletPris());
            }

        }
        return alleSalg;
    }

    public int getAntalSolgteKlip(LocalDate startdato, LocalDate slutdato) {
        int solgteKlip = 0;
        for (int i = 0; i < Storage.getInstance().getOrdrer().size(); i++) {
            Ordre o = Storage.getInstance().getOrdrer().get(i);
            if (o.getAfslutningsDato() != null) {
                if (o.getAfslutningsDato().isBefore(slutdato) && o.getAfslutningsDato().isAfter(startdato) ||
                        o.getAfslutningsDato().equals(startdato) || o.getAfslutningsDato().equals(slutdato)) {
                    for (int j = 0; j < o.getOrdrelinjer().size(); j++) {
                        solgteKlip += o.getOrdrelinjer().get(j).getProdukt().getAntalKlip();

                    }
                }
            }

        }
        return solgteKlip;
    }

    public int getAntalForbrugteKlip(LocalDate startdato, LocalDate slutdato) {
        int forbrugteKlip = 0;
        for (int i = 0; i < Storage.getInstance().getOrdrer().size(); i++) {
            Ordre o = Storage.getInstance().getOrdrer().get(i);
            if (o.getAfslutningsDato() != null) {
                if (o.getAfslutningsDato().isBefore(slutdato) && o.getAfslutningsDato().isAfter(startdato) ||
                        o.getAfslutningsDato().equals(startdato) || o.getAfslutningsDato().equals(slutdato)) {
                    if (o.getBetalingsform().getType().equals("Klip")) {
                        for (int j = 0; j < o.getOrdrelinjer().size(); j++) {
                            forbrugteKlip += o.getOrdrelinjer().get(j).getSamletPrisKlip();
                        }
                    }
                }
            }
        }
        return forbrugteKlip;
    }

    public void lukSalg(Ordre ordre, Prisliste prisliste, LocalDate afslutDato, boolean status, Betalingsform betalingsform) {
        if(!ordre.erOrdrenLukket()){
            ordre.setAfslutningsDato(afslutDato);
            ordre.setOrdreStatus(status);
            ordre.setBetalingsform(betalingsform);
        }
        if (betalingsform.getType().equals("Klip")) {
            for (Ordrelinje ol : ordre.getOrdrelinjer()) {
                ol.setKlip(ol.getProdukt().getklippekortPris(prisliste));
            }
        }
    }


    private void initStorage() {
       loadStorage();
    }



    public void init() {
        initStorage();
    }

    public void removePrislisteOgProduktFraPris(Prisliste prisliste, Produkt produkt) {
        //TODO Burde måske kun være prislisten, som slettes?
        //TODO eller måske kun produktet
        for (Pris p : prisliste.getPriser()) {
            if (p.getProdukt() == produkt) {
                p.setPrisliste(null);
                p.setProdukt(null);
            }
        }

    }
    public void loadStorage() {
        try (FileInputStream fileIn = new FileInputStream("storage_gruppe6.ser")) {
            try (ObjectInputStream in = new ObjectInputStream(fileIn);) {
                storage = (aarhusBryghus.storage.Storage) in.readObject();
                System.out.println("Storage loaded from file storage_gruppe6.ser.");

            } catch (ClassNotFoundException ex) {
                System.out.println("Error loading storage object.");
                throw new RuntimeException(ex);
            }
        } catch (IOException ex) {
            System.out.println("Error loading storage object.");
            throw new RuntimeException(ex);
        }

    }

    public void saveStorage() {
        try (FileOutputStream fileOut = new FileOutputStream("storage_gruppe6.ser")) {
            try (ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(storage);
                System.out.println("Storage saved in file storage_gruppe6.ser.");
            }
        } catch (IOException ex) {
            System.out.println("Error saving storage object.");
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<Kunde> getKunder() {
        return Storage.getInstance().getKunder();
    }
}
