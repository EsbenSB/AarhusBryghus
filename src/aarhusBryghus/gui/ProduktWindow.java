package aarhusBryghus.gui;

import aarhusBryghus.application.controller.Controller;
import aarhusBryghus.application.model.MaaleEnhed;
import aarhusBryghus.application.model.Produkt;
import aarhusBryghus.storage.Storage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProduktWindow extends Stage {
    private final Produkt produkt;

    public ProduktWindow(String title, Produkt produkt) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.produkt = produkt;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }
    public ProduktWindow(String title) {this(title,null);}

    // -----------------------------------------------------------------------------------------------------------------
    private Label lblNavn, lblProduktgruppe, lblMaaleenhed, lblPrisliste, lblPrislistePris, lblPrislisteAntalKlip, lblAddPrisliste, lblAddNyPris, lblError;
    private TextField txfNavn;
    private ComboBox cbProduktgruppe;
    private ComboBox cbMaaleenhed;
    private ComboBox cbPrisliste;
    private TextField txfPris;
    private TextField txfKlippeKortPris;
    private ListView lvwAndrePrislister;
    private TextField nyPris;
    private Button btnOpdater;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lblNavn = new Label("Produktnavn: ");
        pane.add(lblNavn,0,0);

        lblProduktgruppe = new Label("Produktgruppe: ");
        pane.add(lblProduktgruppe,0,1);

        lblMaaleenhed = new Label("Måleenhed: ");
        pane.add(lblMaaleenhed,0,2);

        lblPrisliste = new Label("Prisliste: ");
        pane.add(lblPrisliste,0,3);

        lblPrislistePris = new Label("Pris: ");
        pane.add(lblPrislistePris,0,4);

        lblPrislisteAntalKlip = new Label("Antal Klip: ");
        pane.add(lblPrislisteAntalKlip,2, 3);

        lblAddPrisliste = new Label("Tilføj produkt: " + produkt.getNavn() + " til ny prisliste: ");
        pane.add(lblAddPrisliste,0,5);

        txfNavn = new TextField();
        pane.add(txfNavn,1,0);
        txfNavn.setPrefWidth(200);
        txfNavn.setText(produkt.getNavn());

        cbProduktgruppe = new ComboBox();
        pane.add(cbProduktgruppe,1,1);
        cbProduktgruppe.setPrefWidth(200);
        cbProduktgruppe.getItems().setAll(Storage.getProduktGrupper());
        cbProduktgruppe.getSelectionModel().select(Controller.getProduktGruppe(produkt));

        cbMaaleenhed = new ComboBox();
        pane.add(cbMaaleenhed,1,2);
        cbMaaleenhed.setPrefWidth(200);
        cbMaaleenhed.getItems().setAll(Storage.getMaaleEnheder());
        cbMaaleenhed.getSelectionModel().select(produkt.getMaaleEnhed());

        cbPrisliste = new ComboBox();
        pane.add(cbPrisliste,1,3);
        cbPrisliste.setPrefWidth(200);
        cbPrisliste.getItems().setAll(Storage.getPrislister());
        cbPrisliste.getSelectionModel().select(Controller.getProduktPrisliste(produkt));

        txfPris = new TextField();
        pane.add(txfPris,1,4);
        txfPris.setPrefWidth(200);
        txfPris.setText(""+produkt.enkeltPris(Controller.getProduktPrisliste(produkt)));

        txfKlippeKortPris = new TextField();
        pane.add(txfKlippeKortPris,2,4);
        txfKlippeKortPris.setPrefWidth(200);
        txfKlippeKortPris.setText(""+Controller.getProduktPrisliste(produkt).getAntalKlip(produkt));

        lvwAndrePrislister = new ListView();
        pane.add(lvwAndrePrislister,1,5,1,2);
        lvwAndrePrislister.setPrefWidth(200);
        lvwAndrePrislister.setPrefHeight(150);
        lvwAndrePrislister.getItems().setAll(Controller.getAndrePrislister(produkt)); // :)

        nyPris = new TextField();
        pane.add(nyPris,2,5);
        nyPris.setPrefWidth(200);

        btnOpdater = new Button("Opdatér");
        pane.add(btnOpdater,2,6);



        //TODO Mangler at okAction er færdig for at virke
        Button btnGem = new Button("Gem");
        pane.add(btnGem, 0, 12);
        GridPane.setHalignment(btnGem, HPos.RIGHT);
        btnGem.setOnAction(event -> this.okAction());

        Button btnAnuller = new Button("Anuller");
        pane.add(btnAnuller, 0, 12);
        GridPane.setHalignment(btnAnuller, HPos.LEFT);
        btnAnuller.setOnAction(event -> this.afbrydAction());

        lblError = new Label();
        pane.add(lblError, 0, 13);
        lblError.setStyle("-fx-text-fill: red");
    }

    private void afbrydAction() {
        this.hide();
    }
    
    private void okAction() {
        String navn = txfNavn.getText().trim();
        MaaleEnhed maaleEnhed = (MaaleEnhed) cbMaaleenhed.getSelectionModel().getSelectedItem();
        if (navn.length() == 0){
            lblError.setText("Navnet er tomt");
        }
        if(maaleEnhed.equals(null)){
            lblError.setText("Der er ikke valgt en måleenhed.");
        }
        else{
            if(produkt != null){
                produkt.setNavn(navn);
                produkt.setMaaleEnhed(maaleEnhed);
            }
        }
    }
}
