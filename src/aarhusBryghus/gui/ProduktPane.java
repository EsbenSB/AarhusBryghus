package aarhusBryghus.gui;

import aarhusBryghus.application.controller.Controller;
import aarhusBryghus.application.model.*;
import aarhusBryghus.storage.Storage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class ProduktPane extends GridPane {

    private ListView<ProduktGruppe> lvwProduktgruppe = new ListView<>();
    private Button btnOpdaterProduktgruppe;
    private Button btnSletProduktgruppe;
    private Button btnOpretProduktgruppe;
    private ListView<MaaleEnhed> lvwMaaleenhed = new ListView<>();
    private Button btnOpdaterMaaleenhed;
    private Button btnSletMaaleenhed;
    private Button btnOpretMaaleenhed;
    private ListView<Pant> lvwPant = new ListView<>();
    private Button btnOpdaterPant;
    private Button btnSletPant;
    private Button btnOpretPant;
    private ListView<Produkt> lvwProdukt = new ListView<>();
    private Button btnOpdaterProdukt;
    private Button btnSletProdukt;
    private Button btnOpretProdukt;
    private ListView<Prisliste> lvwPrislister = new ListView<>();
    private Button btnOpdaterPrisliste;
    private Button btnSletPrisliste;
    private Button btnOpretPrisliste;
    private ListView<Produkt> lvwKlippekort = new ListView<>();
    private Button btnOpdaterKlippekort;
    private Button btnSletKlippekort;
    private Button btnOpretKlippekort;

    public ProduktPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblInformation = new Label("Vælg det du vil opdatere, eller tryk på Opret, under den pågældende kategori");
        this.add(lblInformation,0,0,6,1);




        // Produktgruppe
        Label lblProduktgruppe = new Label("Produktgruppe:");
        this.add(lblProduktgruppe,0,1,3,1);

        this.add(lvwProduktgruppe,0,2,3,1);
        lvwProduktgruppe.getItems().addAll(Storage.getProduktGrupper());
        lvwProduktgruppe.setPrefWidth(200);
        lvwProduktgruppe.setPrefHeight(200);

        btnSletProduktgruppe = new Button("Slet");
        this.add(btnSletProduktgruppe,0,3);
        btnSletProduktgruppe.setOnAction(event -> this.sletProduktgruppeAction());

        btnOpdaterProduktgruppe = new Button("Opdatér");
        this.add(btnOpdaterProduktgruppe,1,3);
        btnOpdaterProduktgruppe.setOnAction(event -> this.opdaterProduktgruppeAction());

        btnOpretProduktgruppe = new Button("Opret");
        this.add(btnOpretProduktgruppe,2,3);
        btnOpretProduktgruppe.setOnAction(event -> this.opretProduktgruppeAction());

        // Produkt
        Label lblProdukt = new Label("Produkt:");
        this.add(lblProdukt,3,1,3,1);

        this.add(lvwProdukt,3,2,3,1);
        lvwProdukt.getItems().setAll(Storage.getProduktGrupper().get(0).getProdukter());
        lvwProdukt.setPrefWidth(200);
        lvwProdukt.setPrefHeight(200);

        btnOpretProdukt = new Button("Slet");
        this.add(btnOpretProdukt,3,3);
        btnOpretProdukt.setOnAction(event -> this.sletProduktAction());

        btnOpretProdukt = new Button("Opdatér");
        this.add(btnOpretProdukt,4,3);
        btnOpretProdukt.setOnAction(event -> this.opdaterProduktAction());

        btnOpretProdukt = new Button("Opret");
        this.add(btnOpretProdukt,5,3);
        btnOpretProdukt.setOnAction(event -> this.opretProduktAction());

        // Prislister
        Label lblPrisliste = new Label("Prisliste:");
        this.add(lblPrisliste,6,1,3,1);

        this.add(lvwPrislister,6,2,3,1);
        lvwPrislister.getItems().setAll(Storage.getPrislister());
        lvwPrislister.setPrefWidth(200);
        lvwPrislister.setPrefHeight(200);

        btnOpretPrisliste = new Button("Slet");
        this.add(btnOpretPrisliste,6,3);
        btnOpretPrisliste.setOnAction(event -> this.sletPrislisteAction());

        btnOpretPrisliste = new Button("Opdatér");
        this.add(btnOpretPrisliste,7,3);
        btnOpretPrisliste.setOnAction(event -> this.opdaterPrislisteAction());

        btnOpretPrisliste = new Button("Opret");
        this.add(btnOpretPrisliste,8,3);
        btnOpretPrisliste.setOnAction(event -> this.opretPrislisteAction());

        // Måleenheder
        Label lblMaaleenheder = new Label("Måleenheder:");
        this.add(lblMaaleenheder,0,4,3,1);

        this.add(lvwMaaleenhed,0,5,3,1);
        lvwMaaleenhed.getItems().addAll(Storage.getMaaleEnheder());
        lvwMaaleenhed.setPrefWidth(200);
        lvwMaaleenhed.setPrefHeight(200);

        btnOpretMaaleenhed = new Button("Slet");
        this.add(btnOpretMaaleenhed,0,6);
        btnOpretMaaleenhed.setOnAction(event -> this.sletMaaleenhedAction());

        btnOpretMaaleenhed = new Button("Opdatér");
        this.add(btnOpretMaaleenhed,1,6);
        btnOpretMaaleenhed.setOnAction(event -> this.opdaterMaaleenhedAction());

        btnOpretMaaleenhed = new Button("Opret");
        this.add(btnOpretMaaleenhed,2,6);
        btnOpretMaaleenhed.setOnAction(event -> this.opretMaaleenhedAction());

        // Klippekort
        Label lblKlippekort = new Label("Klippekort:");
        this.add(lblKlippekort,3,4,3,1);
        
        this.add(lvwKlippekort,3,5,3,1);

        lvwKlippekort.getItems().setAll(Storage.getProduktGrupper().get(4).getProdukter());
        lvwKlippekort.setPrefWidth(200);
        lvwKlippekort.setPrefHeight(200);

        btnOpretKlippekort = new Button("Slet");
        this.add(btnOpretKlippekort ,3,6);
        btnOpretKlippekort .setOnAction(event -> this.sletKlippekortAction());

        btnOpretKlippekort  = new Button("Opdatér");
        this.add(btnOpretKlippekort ,4,6);
        btnOpretKlippekort .setOnAction(event -> this.opdaterKlippekortAction());

        btnOpretKlippekort  = new Button("Opret");
        this.add(btnOpretKlippekort ,5,6);
        btnOpretKlippekort .setOnAction(event -> this.opretKlippekortAction());

        // Pant
        Label lblPant = new Label("Pant:");
        this.add(lblPant,6,4,3,1);

        this.add(lvwPant,6,5,3,1);

        lvwPant.getItems().setAll(); //TODO
        lvwPant.setPrefWidth(200);
        lvwPant.setPrefHeight(200);

        btnOpretPant = new Button("Slet");
        this.add(btnOpretPant ,6,6);
        btnOpretPant .setOnAction(event -> this.sletPantAction());

        btnOpretPant = new Button("Opdatér");
        this.add(btnOpretPant,7,6);
        btnOpretPant.setOnAction(event -> this.opdaterPantAction());

        btnOpretPant = new Button("Opret");
        this.add(btnOpretPant,8,6);
        btnOpretPant.setOnAction(event -> this.opretPantAction());
    }

    private void sletPantAction() {
    }

    private void opdaterPantAction() {
    }

    private void opretPantAction() {
    }

    private void sletKlippekortAction() {
    }

    private void opdaterKlippekortAction() {
    }

    private void opretKlippekortAction() {
    }

    private void sletMaaleenhedAction() {
    }

    private void opdaterMaaleenhedAction() {
    }

    private void opretMaaleenhedAction() {
    }

    private void opretPrislisteAction() {
    }

    private void opdaterPrislisteAction() {
    }

    private void sletPrislisteAction() {
    }

    private void opretProduktAction() {
    }

    private void opdaterProduktAction() {
    }

    private void sletProduktAction() {
    }

    private void opretProduktgruppeAction() {
    }

    private void opdaterProduktgruppeAction() {
    }

    private void sletProduktgruppeAction() {
    }

    private void opretAction() {
        ProduktWindow dia= new ProduktWindow("Opret Produkt");
        dia.showAndWait();

        //TODO Lav resten af metoden færdig når knappen skal laves færdig
    }
}
