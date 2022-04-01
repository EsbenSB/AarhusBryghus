package aarhusBryghus.gui;

import aarhusBryghus.application.controller.Controller;
import aarhusBryghus.application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class KasseapparatPane extends GridPane {
    //TODO Lav tekstfields og andet stuff her:
    private final TextField txfValgteProdukt, txfAntalValgte, txfSamletPrisProdukt, txfStandardPris;
    private final TextField txfKlippekortPris, txfCustomPris, txfRabatPris, txfTotalPris;
//    private final TextArea txaKurv;
    private  ListView<Produkt> lvwProdukter, lvwKurv;
    private final ComboBox<Prisliste> cbbPrislister;
    private final ComboBox<Produktgruppe> cbbProduktgrupper;
    private final Button btnBetaling, btnTilKurv, btnBeregnPris, btnToemKurv, btnFjernProdukt;
    private final RadioButton rbStandardPris, rbCustomPris, rbRabat, rbKlippeKortPris;
    private final RadioButton rbKontant, rbMobilepay, rbDankort, rbKlippekort;
    private final ToggleGroup groupBetalingsmetode = new ToggleGroup();
    private final ToggleGroup groupAnvendtPris = new ToggleGroup();
    private Label lblError;

    public KasseapparatPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblPrislister= new Label("Prislister:");
        this.add(lblPrislister,0,0);

        cbbPrislister = new ComboBox<>();
        this.add(cbbPrislister, 1, 0);
        cbbPrislister.getItems().addAll(Controller.getPrislister());
        cbbPrislister.setOnAction(event -> this.opdaterSelectedPrisliste());
        cbbPrislister.setOnAction(event -> this.opdaterProduktGruppeliste());
        cbbPrislister.setPrefWidth(200);

        Label lblProduktgruppe = new Label("Produktgrupper:");
        this.add(lblProduktgruppe,0,1);

        cbbProduktgrupper = new ComboBox<>();
        this.add(cbbProduktgrupper,1,1);
        cbbProduktgrupper.setOnAction(event -> this.opdaterSelectedPrisliste());
        cbbProduktgrupper.setPrefWidth(200);
        cbbProduktgrupper.setDisable(true);

        Label lblProdukter = new Label("Produkter:");
        this.add(lblProdukter, 0, 2);
        GridPane.setValignment(lblProdukter, VPos.TOP);

        lvwProdukter = new ListView<>();
        this.add(lvwProdukter,1,2);
        lvwProdukter.setPrefHeight(150);
        lvwProdukter.setPrefWidth(200);

        ChangeListener<Produkt> produktChangeListener = (ov, gammelProdukt, nyProdukt) -> this.selectedProduktChanged();
        lvwProdukter.getSelectionModel().selectedItemProperty().addListener(produktChangeListener);

        Label lblValgteProdukt = new Label("Valgte produkt:");
        this.add(lblValgteProdukt,0,3);

        txfValgteProdukt = new TextField();
        this.add(txfValgteProdukt,1,3);
        txfValgteProdukt.setEditable(false);
        txfValgteProdukt.setDisable(true);

        Label lblAntalValgte = new Label("Angiv antal:");
        this.add(lblAntalValgte,0,4);

        txfAntalValgte = new TextField();
        this.add(txfAntalValgte,1,4);

        rbStandardPris = new RadioButton("Anvend standard pris");
        this.add(rbStandardPris, 0,5);
        rbStandardPris.setToggleGroup(groupAnvendtPris);
        rbStandardPris.setOnAction(event -> setStandardPris());
        rbStandardPris.fire();

        txfStandardPris = new TextField();
        this.add(txfStandardPris, 1, 5);
        txfStandardPris.setEditable(false);
        txfStandardPris.setDisable(false);

        rbKlippeKortPris = new RadioButton("Anvend klippekort pris");
        this.add(rbKlippeKortPris, 0, 6);
        rbKlippeKortPris.setToggleGroup(groupAnvendtPris);
        rbKlippeKortPris.setOnAction(event -> setKlippekortPris());

        txfKlippekortPris = new TextField();
        this.add(txfKlippekortPris, 1, 6);
        txfKlippekortPris.setEditable(false);
        txfKlippekortPris.setDisable(true);

        rbCustomPris = new RadioButton("Anvend custom pris");
        this.add(rbCustomPris,0,7);
        rbCustomPris.setToggleGroup(groupAnvendtPris);
        rbCustomPris.setOnAction(event -> setCustomPris());

        txfCustomPris = new TextField();
        this.add(txfCustomPris, 1, 7);
        txfCustomPris.setEditable(false);
        txfCustomPris.setDisable(true);

        rbRabat = new RadioButton("Anvend rabat");
        this.add(rbRabat, 0, 8);
        rbRabat.setToggleGroup(groupAnvendtPris);
        rbRabat.setOnAction(event -> setRabatPris());

        txfRabatPris = new TextField();
        this.add(txfRabatPris, 1, 8);
        txfRabatPris.setEditable(false);
        txfRabatPris.setDisable(true);

        Label lblSamletPrisProdukt = new Label("Samlet pris for valgte produkt:");
        this.add(lblSamletPrisProdukt,0,9);

        txfSamletPrisProdukt = new TextField();
        this.add(txfSamletPrisProdukt,1,9);
        txfSamletPrisProdukt.setEditable(false);
        txfSamletPrisProdukt.setDisable(true);

        //TODO Lav knap færdig
        btnBeregnPris = new Button("Beregn pris");
        this.add(btnBeregnPris, 1, 10);
        btnBeregnPris.setOnAction(event -> beregnProduktPris());
        GridPane.setHalignment(btnBeregnPris, HPos.LEFT);

        //TODO Lav knap færdig
        btnTilKurv = new Button("Tilføj til kurv");
        this.add(btnTilKurv,1,10);
        btnTilKurv.setOnAction(event -> addProduktKurv());
        GridPane.setHalignment(btnTilKurv, HPos.RIGHT);

        Label lblKurv = new Label("Kurv:");
        this.add(lblKurv, 3, 2);
        GridPane.setValignment(lblKurv, VPos.TOP);

        lvwKurv = new ListView<>();
        this.add(lvwKurv, 4, 2);
        lvwKurv.setEditable(false);
        lvwKurv.setPrefHeight(150);

        Label lblTotalPris = new Label("Total pris:");
        this.add(lblTotalPris, 3, 3);

        txfTotalPris = new TextField();
        this.add(txfTotalPris, 4, 3);
        txfTotalPris.setEditable(false);
        txfTotalPris.setDisable(true);

        //TODO Lav knap færdig
        btnBetaling = new Button("Betal");
        this.add(btnBetaling,4,8);
        GridPane.setHalignment(btnBetaling, HPos.RIGHT);

        //TODO Lav knap færdig
        btnToemKurv = new Button("Tøm kurv");
        this.add(btnToemKurv, 4, 8);
        btnToemKurv.setOnAction(event -> toemKurv());
        GridPane.setHalignment(btnToemKurv, HPos.CENTER);

        btnFjernProdukt = new Button("Fjern produkt");
        this.add(btnFjernProdukt, 4, 8);
        btnFjernProdukt.setOnAction(event -> fjernProduktKurv());
        GridPane.setHalignment(btnFjernProdukt, HPos.LEFT);
        // -------------------------------------------------------------------------------------------------------------
        // RadioButton laves her:
        Label lblBetalingsmetoder = new Label("Betalingsmetoder:");
        this.add(lblBetalingsmetoder,3,4);

        rbKontant = new RadioButton("Kontant");
        this.add(rbKontant,4,4);
        rbKontant.setToggleGroup(groupBetalingsmetode);

        rbMobilepay = new RadioButton("Mobilepay");
        this.add(rbMobilepay,4,5);
        rbMobilepay.setToggleGroup(groupBetalingsmetode);

        rbDankort = new RadioButton("Dankort");
        this.add(rbDankort,4,6);
        rbDankort.setToggleGroup(groupBetalingsmetode);

        rbKlippekort = new RadioButton("Klippekort");
        this.add(rbKlippekort,4,7);
        rbKlippekort.setToggleGroup(groupBetalingsmetode);

        lblError = new Label();
        this.add(lblError, 4, 0);
        lblError.setStyle("-fx-text-fill: red");
        // -------------------------------------------------------------------------------------------------------------
    }

    public void opdaterSelectedPrisliste() {

        Prisliste prisliste = cbbPrislister.getSelectionModel().getSelectedItem();

        Produktgruppe produktGruppe = cbbProduktgrupper.getSelectionModel().getSelectedItem();
        if (produktGruppe != null) {
            lvwProdukter.getItems().setAll(Controller.listeOverProdukterProduktgruppePaaPrisliste(produktGruppe, prisliste));
        } else {
            lvwProdukter.getItems().clear();
        }

    }

    public void opdaterProduktGruppeliste() {
        if(cbbPrislister.getSelectionModel().getSelectedItem() != null){
            Prisliste prisliste = cbbPrislister.getSelectionModel().getSelectedItem();
            cbbProduktgrupper.getItems().setAll(Controller.listeProduktgrupperTilValgtePrisliste(prisliste));
            lvwProdukter.getItems().clear();
            cbbProduktgrupper.setDisable(false);
        }

    }

    public void selectedProduktChanged() {
        this.updateControls();
    }

    //TODO Der skal måske tilføjes mere
    public void updateControls() {
        Produkt produkt = lvwProdukter.getSelectionModel().getSelectedItem();
        Prisliste prisliste = cbbPrislister.getSelectionModel().getSelectedItem();
        if (produkt != null) {
            txfValgteProdukt.setText(produkt.getNavn());
            txfValgteProdukt.setDisable(false);
            txfStandardPris.setText(produkt.enkeltPris(prisliste) + " Kr.");
            if (produkt.getklippekortPris(prisliste) == 0) {
                txfKlippekortPris.setText("Klippekort kan ikke anvendes");
                txfKlippekortPris.setDisable(true);
                rbKlippeKortPris.setDisable(true);
                rbStandardPris.fire();
                txfStandardPris.setDisable(false);
            } else {
                txfKlippekortPris.setText(produkt.getklippekortPris(prisliste) + " Klip");
            }
        } else {
            txfValgteProdukt.clear();
            txfValgteProdukt.setDisable(true);
            txfStandardPris.clear();
            txfKlippekortPris.clear();
            rbKlippeKortPris.setDisable(false);
        }
    }

    public void setStandardPris() {
        if (rbStandardPris.isArmed()) {
            txfStandardPris.setDisable(false);
            txfKlippekortPris.setDisable(true);
            txfCustomPris.clear();
            txfCustomPris.setEditable(false);
            txfCustomPris.setDisable(true);
            txfRabatPris.clear();
            txfRabatPris.setEditable(false);
            txfRabatPris.setDisable(true);
        }
    }

    public void setKlippekortPris() {
        if (rbKlippeKortPris.isArmed()) {
            txfKlippekortPris.setDisable(false);
            txfStandardPris.setDisable(true);
            txfCustomPris.clear();
            txfCustomPris.setEditable(false);
            txfCustomPris.setDisable(true);
            txfRabatPris.clear();
            txfRabatPris.setEditable(false);
            txfRabatPris.setDisable(true);
        }
    }

    public void setCustomPris() {
        if (rbCustomPris.isArmed()) {
            txfCustomPris.setEditable(true);
            txfCustomPris.setDisable(false);
            txfStandardPris.setDisable(true);
            txfKlippekortPris.setDisable(true);
            txfRabatPris.clear();
            txfRabatPris.setEditable(false);
            txfRabatPris.setDisable(true);
        }
    }

    public void setRabatPris() {
        if (rbRabat.isArmed()) {
            txfRabatPris.setEditable(true);
            txfRabatPris.setDisable(false);
            txfStandardPris.setDisable(true);
            txfKlippekortPris.setDisable(true);
            txfCustomPris.clear();
            txfCustomPris.setEditable(false);
            txfCustomPris.setDisable(true);
        }
    }

    //TODO Lav færdig
    public void beregnProduktPris() {
        Produkt produkt = lvwProdukter.getSelectionModel().getSelectedItem();
        Prisliste prisliste = cbbPrislister.getSelectionModel().getSelectedItem();

        String valgteProdukt = txfValgteProdukt.getText().trim();
        if (valgteProdukt.length() == 0) {
            lblError.setText("Vælg et produkt");
        } else {
            int antalValgte = -1;
            try {
                antalValgte = Integer.parseInt(txfAntalValgte.getText().trim());
            } catch (NumberFormatException ex) {
                // Do nothing
            }
            if (antalValgte < 1) {
                lblError.setText("Vælg et antal");
            } else {
                if (rbStandardPris.isSelected()) {
                    Ordrelinje ordrelinje = new Ordrelinje(produkt, antalValgte, prisliste);
                    txfSamletPrisProdukt.setText(ordrelinje.getSamletPris()+" Kr.");
                    lblError.setText("");
                } else {
                    if (rbKlippeKortPris.isSelected()) {
                        Ordrelinje ordrelinje2 = new Ordrelinje(produkt, antalValgte, prisliste);
                        txfSamletPrisProdukt.setText(ordrelinje2.getSamletPrisKlip()+" Klip");
                        lblError.setText("");
                    } else {
                        if (rbCustomPris.isSelected()) {
                            int customPris = -1;
                            try {
                                customPris = Integer.parseInt(txfCustomPris.getText().trim());
                                Ordrelinje ordrelinje3 = new Ordrelinje(produkt, antalValgte, prisliste);
                                ordrelinje3.setAftaltPris(customPris);
                                txfSamletPrisProdukt.setText(ordrelinje3.getSamletPris()+" Kr.");
                                lblError.setText("");
                            } catch (NumberFormatException ex) {
                                // Do nothing
                            }
                            if (customPris <1) {
                                lblError.setText("CustomPris er tom");
                            }
                        } else {
                            double rabatPris = -1;
                            try {
                                rabatPris = Integer.parseInt(txfRabatPris.getText().trim());
                                Ordrelinje ordrelinje4 = new Ordrelinje(produkt, antalValgte, prisliste);
                                ordrelinje4.setPrisMedProcentRabat(rabatPris);
                                txfSamletPrisProdukt.setText(ordrelinje4.getSamletPris()+" Kr.");
                                lblError.setText("");
                            } catch (NumberFormatException ex) {
                                // Do nothing
                            }
                            if (rabatPris < 0) {
                                lblError.setText("Ugyldig rabat");
                            } else if (rabatPris > 100) {
                                lblError.setText("For høj rabat");
                            }
                        }
                    }
                }
            }
        }
    }

    //TODO Lav færdig
    public void addProduktKurv() {
        Produkt produkt = lvwProdukter.getSelectionModel().getSelectedItem();
        Prisliste prisliste = cbbPrislister.getSelectionModel().getSelectedItem();
        Ordre ordre = new Ordre("køb", false, LocalDate.now(),prisliste);
        double totalPris = 0;

        String valgteProdukt = txfValgteProdukt.getText().trim();
        if (valgteProdukt.length() == 0) {
            lblError.setText("Vælg et produkt");
        } else {
            int antalValgte = -1;
            try {
                antalValgte = Integer.parseInt(txfAntalValgte.getText().trim());
            } catch (NumberFormatException ex) {
                // Do nothing
            }
            if (antalValgte < 1) {
                lblError.setText("Vælg et antal");
            } else {
                if (rbStandardPris.isSelected()) {
                    Ordrelinje ordrelinje1 = Controller.createOrdrelinjeSalg(ordre, produkt, antalValgte, prisliste);
                    txfSamletPrisProdukt.setText(ordrelinje1.getSamletPris()+" Kr.");
                    lvwKurv.getItems().add(produkt);
                    cbbPrislister.setDisable(true);
                    txfTotalPris.setDisable(false);
                    // TODO Vi skal have en ordre ind  - så kan vi køre ordre.getsamletpris(), eller hvad den nu hed
                    ordre.createOrdrelinje(antalValgte, produkt);
                    System.out.println(ordre.getOrdrenummer());
                    txfTotalPris.setText(ordre.getSamletPris() + " Kr.");
                    lblError.setText("");
                } else {
                    if (rbKlippeKortPris.isSelected()) {
                        Ordrelinje ordrelinje2 = new Ordrelinje(produkt, antalValgte, prisliste);
                        txfSamletPrisProdukt.setText(ordrelinje2.getSamletPrisKlip()+" Klip");
                        lvwKurv.getItems().add(produkt);
                        cbbPrislister.setDisable(true);
                        txfTotalPris.setDisable(false);
                        // TODO Vi skal have en ordre ind  - så kan vi køre ordre.getsamletpris(), eller hvad den nu hed
                        ordre.createOrdrelinje(antalValgte, produkt);
                        txfTotalPris.setText(ordre.getSamletPris() + " Kr.");
                        lblError.setText("");
                    } else {
                        if (rbCustomPris.isSelected()) {
                            int customPris = -1;
                            try {
                                customPris = Integer.parseInt(txfCustomPris.getText().trim());
                                Ordrelinje ordrelinje3 = new Ordrelinje(produkt, antalValgte, prisliste);
                                ordrelinje3.setAftaltPris(customPris);
                                txfSamletPrisProdukt.setText(ordrelinje3.getSamletPris()+" Kr.");
                                lvwKurv.getItems().add(produkt);
                                cbbPrislister.setDisable(true);
                                txfTotalPris.setDisable(false);
                                // TODO Vi skal have en ordre ind  - så kan vi køre ordre.getsamletpris(), eller hvad den nu hed
                                ordre.createOrdrelinje(antalValgte, produkt);
                                txfTotalPris.setText(ordre.getSamletPris() + " Kr.");
                                lblError.setText("");
                            } catch (NumberFormatException ex) {
                                // Do nothing
                            }
                            if (customPris <1) {
                                lblError.setText("CustomPris er tom");
                            }
                        } else {
                            double rabatPris = -1;
                            try {
                                rabatPris = Integer.parseInt(txfRabatPris.getText().trim());
                                Ordrelinje ordrelinje4 = new Ordrelinje(produkt, antalValgte, prisliste);
                                ordrelinje4.setPrisMedProcentRabat(rabatPris);
                                txfSamletPrisProdukt.setText(ordrelinje4.getSamletPris()+" Kr.");
                                lvwKurv.getItems().add(produkt);
                                cbbPrislister.setDisable(true);
                                txfTotalPris.setDisable(false);
                                // TODO Vi skal have en ordre ind  - så kan vi køre ordre.getsamletpris(), eller hvad den nu hed
                                ordre.createOrdrelinje(antalValgte, produkt);
                                txfTotalPris.setText(ordre.getSamletPris() + " Kr.");
                                lblError.setText("");
                            } catch (NumberFormatException ex) {
                                // Do nothing
                            }
                            if (rabatPris < 0) {
                                lblError.setText("Ugyldig rabat");
                            } else if (rabatPris > 100) {
                                lblError.setText("For høj rabat");
                            }
                        }
                    }
                }
            }
        }
    }

    //TODO Lav knap færdig
    public void fjernProduktKurv() {
        Produkt produkt = lvwKurv.getSelectionModel().getSelectedItem();
        if (produkt != null) {
            lvwKurv.getItems().remove(produkt);

            if (lvwKurv.getSelectionModel().isEmpty()) {
                cbbPrislister.setDisable(false);
                txfTotalPris.setDisable(true);
            }
        }
    }

    public void toemKurv() {
        lvwKurv.getItems().clear();
        txfTotalPris.clear();
        cbbPrislister.setDisable(false);
        txfTotalPris.setDisable(true);
    }
}
