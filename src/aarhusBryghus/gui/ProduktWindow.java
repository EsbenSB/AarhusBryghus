package aarhusBryghus.gui;

import aarhusBryghus.application.controller.Controller;
import aarhusBryghus.application.model.*;
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
    private ListView lvwPrislister;
    private Button btnFjernFraPrisliste;
    private TextField txfPris;
    private TextField txfKlippeKortPris;
    private ListView lvwAndrePrislister;
    private TextField nyPris;
    private Button btnGem;
    private Button btnOpdater;
    private Button btnAfbryd;

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

        lblPrisliste = new Label("Prislister: ");
        pane.add(lblPrisliste,0,3);

        lblPrislistePris = new Label("Pris: ");
        pane.add(lblPrislistePris,0,4);

        lblPrislisteAntalKlip = new Label("Antal Klip: ");
        pane.add(lblPrislisteAntalKlip,2, 3);

        lblAddPrisliste = new Label("Tilføj produkt: \n" + produkt.getNavn() + "\ntil ny prisliste: ");
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

        lvwPrislister = new ListView<>();
        pane.add(lvwPrislister,1,3);
        lvwPrislister.setPrefWidth(200);
        lvwPrislister.setPrefHeight(100);
        lvwPrislister.getItems().setAll(Storage.getPrislister());
        if(Controller.getProduktPrisliste(produkt) != null){
            lvwPrislister.getSelectionModel().select(Controller.getProduktPrisliste(produkt));
        }

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

        lblAddNyPris = new Label("Indtast Pris, til den nye prisliste:");
        pane.add(lblAddNyPris,2,5);
        nyPris = new TextField();
        pane.add(nyPris,2,6);
        nyPris.setPrefWidth(200);

        btnFjernFraPrisliste = new Button("Fjern prisliste");
        pane.add(btnFjernFraPrisliste,2,3);
        btnFjernFraPrisliste.setOnAction(event -> this.fjernFraPrislisteAction());
        btnGem = new Button("Gem");
        pane.add(btnGem,2,7);

        Button btnOpdater = new Button("Opdatér");
        pane.add(btnOpdater, 1, 12);
        GridPane.setHalignment(btnOpdater, HPos.RIGHT);
        btnOpdater.setOnAction(event -> this.opdaterProduktAction());

        Button btnAfbryd = new Button("Anuller");
        pane.add(btnAfbryd, 0, 12);
        GridPane.setHalignment(btnAfbryd, HPos.LEFT);
        btnAfbryd.setOnAction(event -> this.afbrydAction());

        lblError = new Label();
        pane.add(lblError, 2, 13);
        lblError.setStyle("-fx-text-fill: red");
    }

    private void fjernFraPrislisteAction() {
        Prisliste prisliste = (Prisliste) lvwPrislister.getSelectionModel().getSelectedItem();
        Controller.removePrislisteOgProduktFraPris(prisliste, produkt);
    }

    private void afbrydAction() {
        this.hide();
    }
    
    private void opdaterProduktAction() {
        String navn = txfNavn.getText().trim();
        MaaleEnhed maaleEnhed = (MaaleEnhed) cbMaaleenhed.getSelectionModel().getSelectedItem();
        Produktgruppe produktgruppe = (Produktgruppe) cbProduktgruppe.getSelectionModel().getSelectedItem();

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
        hide();
    }
}
