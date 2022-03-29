package aarhusBryghus.gui;

import aarhusBryghus.application.controller.Controller;
import aarhusBryghus.application.model.Prisliste;
import aarhusBryghus.application.model.Produkt;
import aarhusBryghus.application.model.ProduktGruppe;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class KasseapparatPane extends GridPane {
    //TODO Lav tekstfields og andet stuff her:
    private  ListView<Produkt> lvwProdukter;
    private final ComboBox<Prisliste> cbbPrislister;
    private final ComboBox<ProduktGruppe> cbbProduktgrupper;
    private final Button btnBetaling;
    private final RadioButton rbKontant, rbMobilepay, rbDankort, rbKlippekort;
    private final ToggleGroup group = new ToggleGroup();

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
            lvwProdukter.getItems().addAll(produktGruppe.getProdukter());
        });


        Label lblProdukter = new Label("Produkter:");
        this.add(lblProdukter, 0, 2);

        lvwProdukter = new ListView<>();
        this.add(lvwProdukter,1,2);
        lvwProdukter.setPrefHeight(200);
        lvwProdukter.setPrefWidth(200);


        //TODO Lav knap færdig (Hvis det er nødvendigt?)
        btnBetaling = new Button("Betal");
        this.add(btnBetaling,11,12);
        GridPane.setHalignment(btnBetaling, HPos.RIGHT);
        // -------------------------------------------------------------------------------------------------------------
        // RadioButton laves her:
        Label lblBetalingsmetoder = new Label("Betalingsmetoder:");
        this.add(lblBetalingsmetoder,10,9);

        rbKontant = new RadioButton("Kontant");
        this.add(rbKontant,10,10);
        rbKontant.setToggleGroup(group);

        rbMobilepay = new RadioButton("Mobilepay");
        this.add(rbMobilepay,10,11);
        rbMobilepay.setToggleGroup(group);

        rbDankort = new RadioButton("Dankort");
        this.add(rbDankort,10,12);
        rbDankort.setToggleGroup(group);

        rbKlippekort = new RadioButton("Klippekort");
        this.add(rbKlippekort,10,13);
        rbKlippekort.setToggleGroup(group);
        // -------------------------------------------------------------------------------------------------------------
    }

}
