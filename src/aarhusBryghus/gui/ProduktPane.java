package aarhusBryghus.gui;

import aarhusBryghus.application.controller.Controller;
import aarhusBryghus.application.model.*;
import aarhusBryghus.storage.Storage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

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
    private ComboBox cbProduktGrp = new ComboBox();
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
        // overskriften i vinduet
        Label lblInformation = new Label("Vælg det du vil opdatere/slette og tryk på den pågældende knap. Eller tryk Opret, under den pågældende kategori");
        this.add(lblInformation, 0, 0, 6, 1);

        // Produktgruppe
        Label lblProduktgruppe = new Label("Produktgruppe:");
        this.add(lblProduktgruppe, 0, 1, 3, 1);

        this.add(lvwProduktgruppe, 0, 2, 3, 2);
        lvwProduktgruppe.getItems().addAll(Storage.getProduktGrupper());
        lvwProduktgruppe.setPrefWidth(200);
        lvwProduktgruppe.setPrefHeight(200);

        btnSletProduktgruppe = new Button("Slet");
        this.add(btnSletProduktgruppe, 0, 4);
        btnSletProduktgruppe.setOnAction(event -> this.sletProduktgruppeAction());

        btnOpdaterProduktgruppe = new Button("Opdatér");
        this.add(btnOpdaterProduktgruppe, 1, 4);
        btnOpdaterProduktgruppe.setOnAction(event -> this.opdaterProduktgruppeAction());

        btnOpretProduktgruppe = new Button("Opret");
        this.add(btnOpretProduktgruppe, 2, 4);
        btnOpretProduktgruppe.setOnAction(event -> this.opretProduktgruppeAction());

        // Produkt
        Label lblProdukt = new Label("Produkt:");
        this.add(lblProdukt, 3, 1, 3, 1);

        this.add(cbProduktGrp,3,2,3,1);
        cbProduktGrp.getItems().setAll(Storage.getProduktGrupper());
        this.add(lvwProdukt, 3, 3, 3, 1);
        lvwProdukt.setPrefWidth(200);
        lvwProdukt.setPrefHeight(150);
        cbProduktGrp.setOnAction(event -> this.updateProduktliste());


        btnOpretProdukt = new Button("Slet");
        this.add(btnOpretProdukt, 3, 4);
        btnOpretProdukt.setOnAction(event -> this.sletProduktAction());

        btnOpretProdukt = new Button("Opdatér");
        this.add(btnOpretProdukt, 4, 4);
        btnOpretProdukt.setOnAction(event -> this.opdaterProduktAction());

        btnOpretProdukt = new Button("Opret");
        this.add(btnOpretProdukt, 5, 4);
        btnOpretProdukt.setOnAction(event -> this.opretProduktAction());

        // Prislister
        Label lblPrisliste = new Label("Prisliste:");
        this.add(lblPrisliste, 6, 1, 3, 1);

        this.add(lvwPrislister, 6, 2, 3, 2);
        lvwPrislister.getItems().setAll(Storage.getPrislister());
        lvwPrislister.setPrefWidth(200);
        lvwPrislister.setPrefHeight(200);

        btnOpretPrisliste = new Button("Slet");
        this.add(btnOpretPrisliste, 6, 4);
        btnOpretPrisliste.setOnAction(event -> this.sletPrislisteAction());

        btnOpretPrisliste = new Button("Opdatér");
        this.add(btnOpretPrisliste, 7, 4);
        btnOpretPrisliste.setOnAction(event -> this.opdaterPrislisteAction());

        btnOpretPrisliste = new Button("Opret");
        this.add(btnOpretPrisliste, 8, 4);
        btnOpretPrisliste.setOnAction(event -> this.opretPrislisteAction());

        // Måleenheder
        Label lblMaaleenheder = new Label("Måleenheder:");
        this.add(lblMaaleenheder, 0, 5, 3, 1);

        this.add(lvwMaaleenhed, 0, 6, 3, 1);
        lvwMaaleenhed.getItems().setAll(Storage.getMaaleEnheder());
        lvwMaaleenhed.setPrefWidth(200);
        lvwMaaleenhed.setPrefHeight(200);

        btnOpretMaaleenhed = new Button("Slet");
        this.add(btnOpretMaaleenhed, 0, 7);
        btnOpretMaaleenhed.setOnAction(event -> this.sletMaaleenhedAction());

        btnOpretMaaleenhed = new Button("Opdatér");
        this.add(btnOpretMaaleenhed, 1, 7);
        btnOpretMaaleenhed.setOnAction(event -> this.opdaterMaaleenhedAction());

        btnOpretMaaleenhed = new Button("Opret");
        this.add(btnOpretMaaleenhed, 2, 7);
        btnOpretMaaleenhed.setOnAction(event -> this.opretMaaleenhedAction());

        // Klippekort
        Label lblKlippekort = new Label("Klippekort:");
        this.add(lblKlippekort, 3, 5, 3, 1);

        this.add(lvwKlippekort, 3, 6, 3, 1);

        lvwKlippekort.getItems().setAll(Storage.getProduktGrupper().get(4).getProdukter());
        lvwKlippekort.setPrefWidth(200);
        lvwKlippekort.setPrefHeight(200);

        btnOpretKlippekort = new Button("Slet");
        this.add(btnOpretKlippekort, 3, 7);
        btnOpretKlippekort.setOnAction(event -> this.sletKlippekortAction());

        btnOpretKlippekort = new Button("Opdatér");
        this.add(btnOpretKlippekort, 4, 7);
        btnOpretKlippekort.setOnAction(event -> this.opdaterKlippekortAction());

        btnOpretKlippekort = new Button("Opret");
        this.add(btnOpretKlippekort, 5, 7);
        btnOpretKlippekort.setOnAction(event -> this.opretKlippekortAction());

        // Pant
        Label lblPant = new Label("Pant:");
        this.add(lblPant, 6, 5, 3, 1);

        this.add(lvwPant, 6, 6, 3, 1);

        lvwPant.getItems().setAll(); //TODO
        lvwPant.setPrefWidth(200);
        lvwPant.setPrefHeight(200);

        btnOpretPant = new Button("Slet");
        this.add(btnOpretPant, 6, 7);
        btnOpretPant.setOnAction(event -> this.sletPantAction());

        btnOpretPant = new Button("Opdatér");
        this.add(btnOpretPant, 7, 7);
        btnOpretPant.setOnAction(event -> this.opdaterPantAction());

        btnOpretPant = new Button("Opret");
        this.add(btnOpretPant, 8, 7);
        btnOpretPant.setOnAction(event -> this.opretPantAction());
    }

    private void updateProduktliste() {
        ProduktGruppe produktGruppe = (ProduktGruppe) cbProduktGrp.getSelectionModel().getSelectedItem();
        if (produktGruppe != null) {
            lvwProdukt.getItems().setAll(Controller.getAlleProdukter(produktGruppe));
        } else {

        }
    }

    private void sletPantAction() {
    }

    private void opdaterPantAction() {
        ProduktWindow dia = new ProduktWindow("Opret Produkt");
        dia.showAndWait();
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
        ProduktGruppe produktGruppe = (ProduktGruppe) cbProduktGrp.getSelectionModel().getSelectedItem();
        Produkt produkt = lvwProdukt.getSelectionModel().getSelectedItem();
        if(produkt != null){
            ProduktWindow dia = new ProduktWindow("Opdatér Produkt: " + produkt.getNavn(),produkt);
            dia.showAndWait();
            // Wait for the window to close
            int selectIndex = lvwProdukt.getSelectionModel().getSelectedIndex();
            lvwProdukt.getItems().setAll(Controller.getAlleProdukter(produktGruppe));
            lvwProdukt.getSelectionModel().select(selectIndex);
        }
    }

    private void sletProduktAction() {
        ProduktGruppe produktGruppe = (ProduktGruppe) cbProduktGrp.getSelectionModel().getSelectedItem();
        Produkt produkt = lvwProdukt.getSelectionModel().getSelectedItem();
        if (produkt != null) {
            Alert SletProduktAlert = new Alert(Alert.AlertType.CONFIRMATION);
            SletProduktAlert.setTitle("Slet produkt");
            // alert.setContentText("Are you sure?");
            SletProduktAlert.setHeaderText("Er du sikker?");
            Optional<ButtonType> result = SletProduktAlert.showAndWait();
            // Wait for the modal dialog to close
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                Controller.deleteProdukt(produkt);
                lvwProdukt.getItems().setAll(Controller.getAlleProdukter(produktGruppe));
            }
        }
    }

    private void opretProduktgruppeAction(){

    }

    private void opdaterProduktgruppeAction(){

    }

    /*
    sletProduktgruppeAction.
    Sletter en produktgruppe, men kun hvis den ikke har nogen produkter forbundet.
    Giver dog ingen fejl i gui, når mna trykker slet, på en produktgruppe, som ikke er tom.
     */
    private void sletProduktgruppeAction() {
        ProduktGruppe produktGruppe = lvwProduktgruppe.getSelectionModel().getSelectedItem();
        if (produktGruppe != null && produktGruppe.getProdukter().isEmpty()) {
            Alert sletProduktGruppeAlert = new Alert(Alert.AlertType.CONFIRMATION);
            sletProduktGruppeAlert.setTitle("Slet Produktgruppe");
            // alert.setContentText("Are you sure?");
            sletProduktGruppeAlert.setHeaderText("Er du sikker?");
            Optional<ButtonType> result = sletProduktGruppeAlert.showAndWait();
            // Wait for the modal dialog to close
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                Controller.deleteProduktgruppe(produktGruppe);
                if(Storage.produktGrupper != null){
                    lvwProduktgruppe.getItems().setAll(Storage.produktGrupper);
                    cbProduktGrp.getItems().setAll(Storage.produktGrupper);

                }

            }
        }
    }
}
