package aarhusBryghus.gui;

import aarhusBryghus.application.controller.Controller;
import aarhusBryghus.application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.sql.SQLOutput;

public class ProduktPane extends GridPane {

    private ComboBox<String> cbElementTyper = new ComboBox<>();
    private ComboBox<Produktgruppe> cbProduktgrupper = new ComboBox<>();
    private ListView<Produktgruppe> lvwProduktgruppe = new ListView<>();
    private ListView<Produkt> lvwProdukter = new ListView<>();
    private Button btnAccepter = new Button("Acceptér");
    private Label lblProduktType = new Label("Produkttype: ");
    private Label lblProduktnavn = new Label("Produktnavn: ");
    private Label lblProduktMåleenhed = new Label("Måleenhed: ");
    private Label lblPantPris = new Label("Pant: ");
    private Label lblKlipPris = new Label("Klip: ");
    private Label lblPrisliste = new Label("Prisliste: ");
    private Label lblPrisKroner = new Label("Pris: ");
    private Label lblSelectPrisliste = new Label("Vælg Prisliste");

    private TextField txfProduktnavn = new TextField();
    private ComboBox cbVaelgMaaleenhed = new ComboBox();
    private ComboBox cbProduktType = new ComboBox();


    private TextField txfPantPris = new TextField();
    private TextField txfKlipPris = new TextField();
    private TextField txfRigtigPris = new TextField();

    private ListView<Prisliste> lvwProduktetsPrislister = new ListView<>();


    public ProduktPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // indsæt knapper og listview osv her
        Label lblInformationsTekst = new Label("Vælg et element du vil opdatere, eller opret et nyt");
        this.add(lblInformationsTekst, 0, 0);

        cbElementTyper.setPromptText("Vælg element her");
        cbElementTyper.getItems().setAll(Controller.getInstance().getElementTyper());
        this.add(cbElementTyper, 0, 1);
        cbElementTyper.setOnAction(event -> elementTypeSelected());

        cbProduktgrupper.setPromptText("Vælg produktgruppe");
        cbProduktgrupper.getItems().setAll(Controller.getInstance().getProduktGrupper());
        cbProduktgrupper.setOnAction(event -> produktgruppeSelected());

        ChangeListener<Produkt> produktChangeListener = (ov, gammelProdukt, nyProdukt) -> this.selectedProduktChanged();
        lvwProdukter.getSelectionModel().selectedItemProperty().addListener(produktChangeListener);

        cbVaelgMaaleenhed.setPromptText("Vælg måleenhed");


    }

    private void selectedProduktChanged() {
        Produkt produkt = lvwProdukter.getSelectionModel().getSelectedItem();

        if (produkt.getClass().equals(Klippekort.class)) {

        } else if (produkt.getClass().equals(PantProdukt.class)) {
            try{
                this.add(lblProduktType, 1, 3);
                this.add(cbProduktType, 2, 3);
                cbProduktType.setPromptText("Vælg en produkttype");
                cbProduktType.getItems().setAll(Controller.getInstance().getProduktTyper());
                cbProduktType.getSelectionModel().select(PantProdukt.class);
                this.add(lblProduktnavn, 1, 4);
                this.add(txfProduktnavn, 2, 4);
                this.add(lblProduktMåleenhed, 1, 5);
                this.add(cbVaelgMaaleenhed, 2, 5);
                cbVaelgMaaleenhed.getItems().setAll(Controller.getInstance().getMaaleEnheder());
                this.add(lblSelectPrisliste, 1, 6);
                this.add(lvwProduktetsPrislister, 2, 6, 1, 4);
                lvwProduktetsPrislister.getItems().setAll(Controller.getInstance().getAndrePrislister(produkt));
            }
            catch (IllegalArgumentException e) {System.out.print("");
            }
        } else {
            try{
                this.add(lblProduktType, 1, 3);
                this.add(cbProduktType, 2, 3);
                cbProduktType.setPromptText("Vælg en produkttype");
                cbProduktType.getItems().setAll(Controller.getInstance().getProduktTyper());
                this.add(lblProduktnavn, 1, 4);
                this.add(txfProduktnavn, 2, 4);
                this.add(lblProduktMåleenhed, 1, 5);
                this.add(cbVaelgMaaleenhed, 2, 5);
                cbVaelgMaaleenhed.getItems().setAll(Controller.getInstance().getMaaleEnheder());
                this.add(lblSelectPrisliste, 1, 6);
                this.add(lvwProduktetsPrislister, 2, 6, 1, 4);
                lvwProduktetsPrislister.getItems().setAll(Controller.getInstance().getAndrePrislister(produkt));
            }
             catch (IllegalArgumentException e) {System.out.print("");
        }}

    }

    private void produktgruppeSelected() {
        Produktgruppe produktgruppe = cbProduktgrupper.getSelectionModel().getSelectedItem();
        lvwProdukter.getItems().setAll(Controller.getInstance().getAlleProdukter(produktgruppe));
        this.add(lvwProdukter, 0, 3, 1, 7);
    }

    private void elementTypeSelected() {
        if (cbElementTyper.getSelectionModel().getSelectedItem().equalsIgnoreCase("Produkt")) {
            this.add(cbProduktgrupper, 0, 2);
        } else {
            this.add(lvwProdukter, 0, 2, 1, 10);
        }
    }

}
