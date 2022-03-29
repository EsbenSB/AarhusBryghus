package aarhusBryghus.gui;

import aarhusBryghus.application.controller.Controller;
import aarhusBryghus.application.model.Prisliste;
import aarhusBryghus.application.model.Produkt;
import aarhusBryghus.application.model.ProduktGruppe;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class KasseapparatPane extends GridPane {
    //TODO Lav tekstfields og andet stuff her:
    private final TextField txfValgteProdukt, txfAntalValgte, txfSamletPrisProdukt, txfStandardPris;
    private final TextField txfCustomPris, txfRabatPris, txfTotalPris;
    private final TextArea txaKurv;
    private  ListView<Produkt> lvwProdukter;
    private final ComboBox<Prisliste> cbbPrislister;
    private final ComboBox<ProduktGruppe> cbbProduktgrupper;
    private final Button btnBetaling, btnTilKurv;
    private final RadioButton rbStandardPris, rbCustomPris, rbRabat, rbKontant, rbMobilepay, rbDankort, rbKlippekort;
    private final ToggleGroup groupBetalingsmetode = new ToggleGroup();
    private final ToggleGroup groupAnvendtPris = new ToggleGroup();

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

        Label lblProduktgruppe = new Label("Produktgrupper:");
        this.add(lblProduktgruppe,0,1);

        cbbProduktgrupper = new ComboBox<>();
        this.add(cbbProduktgrupper,1,1);
        cbbProduktgrupper.getItems().addAll(Controller.getProduktGrupper());
        cbbProduktgrupper.setOnAction(event -> {
            if (cbbProduktgrupper.getSelectionModel().getSelectedItem() != null);
            ProduktGruppe produktGruppe = cbbProduktgrupper.getSelectionModel().getSelectedItem();
            lvwProdukter.getItems().setAll(produktGruppe.getProdukter());
        });


        Label lblProdukter = new Label("Produkter:");
        this.add(lblProdukter, 0, 2);
        GridPane.setValignment(lblProdukter, VPos.TOP);

        lvwProdukter = new ListView<>();
        this.add(lvwProdukter,1,2);
        lvwProdukter.setPrefHeight(200);
        lvwProdukter.setPrefWidth(200);

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

        txfStandardPris = new TextField();
        this.add(txfStandardPris, 1, 5);
        txfStandardPris.setEditable(false);
        txfStandardPris.setDisable(true);

        rbCustomPris = new RadioButton("Anvend custom pris");
        this.add(rbCustomPris,0,6);
        rbCustomPris.setToggleGroup(groupAnvendtPris);
        rbCustomPris.setOnAction(event -> setCustomPris());

        txfCustomPris = new TextField();
        this.add(txfCustomPris, 1, 6);
        txfCustomPris.setEditable(false);
        txfCustomPris.setDisable(true);

        rbRabat = new RadioButton("Anvend rabat");
        this.add(rbRabat, 0, 7);
        rbRabat.setToggleGroup(groupAnvendtPris);
        rbRabat.setOnAction(event -> setRabatPris());

        txfRabatPris = new TextField();
        this.add(txfRabatPris, 1, 7);
        txfRabatPris.setEditable(false);
        txfRabatPris.setDisable(true);

        Label lblSamletPrisProdukt = new Label("Samlet pris for valgte produkt:");
        this.add(lblSamletPrisProdukt,0,8);

        txfSamletPrisProdukt = new TextField();
        this.add(txfSamletPrisProdukt,1,8);
        txfSamletPrisProdukt.setEditable(false);
        txfSamletPrisProdukt.setDisable(true);

        //TODO Lav knap færdig
        btnTilKurv = new Button("Tilføj til kurv");
        this.add(btnTilKurv,1,9);

        Label lblKurv = new Label("Kurv:");
        this.add(lblKurv, 3, 2);
        GridPane.setValignment(lblKurv, VPos.TOP);

        txaKurv = new TextArea();
        this.add(txaKurv, 4, 2);
        txaKurv.setEditable(false);

        Label lblTotalPris = new Label("Total pris:");
        this.add(lblTotalPris, 3, 3);

        txfTotalPris = new TextField();
        this.add(txfTotalPris, 4, 3);
        txfTotalPris.setEditable(false);
        txfTotalPris.setDisable(true);

        //TODO Lav knap færdig (Hvis det er nødvendigt?)
        btnBetaling = new Button("Betal");
        this.add(btnBetaling,4,8);
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
        // -------------------------------------------------------------------------------------------------------------
    }

    public void setStandardPris() {
        if (rbStandardPris.isArmed()) {
            txfStandardPris.setDisable(false);
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
            txfStandardPris.clear();
            txfStandardPris.setEditable(false);
            txfStandardPris.setDisable(true);
            txfRabatPris.clear();
            txfRabatPris.setEditable(false);
            txfRabatPris.setDisable(true);
        }
    }

    public void setRabatPris() {
        if (rbRabat.isArmed()) {
            txfRabatPris.setEditable(true);
            txfRabatPris.setDisable(false);
            txfStandardPris.clear();
            txfStandardPris.setEditable(false);
            txfStandardPris.setDisable(true);
            txfCustomPris.clear();
            txfCustomPris.setEditable(false);
            txfCustomPris.setDisable(true);
        }
    }

}
