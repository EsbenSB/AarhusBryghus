package aarhusBryghus.gui;

import aarhusBryghus.application.model.Kunde;
import aarhusBryghus.application.model.Rundvisning;
import aarhusBryghus.storage.Storage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class RundvisningPane extends GridPane {
    //TODO Lav tekstfields og andet stuff her:
    private Button btnBetaling, btnOpretRundvisning;
    private RadioButton r1, r2, r3;
    private final TextField txfFornavn, txfEfternavn, txfTelefonNr, txfAntalDeltagere, txfBeloeb;
    private final Label lblFornavn, lblEfternavn, lblTelefonNr, lblAntalDeltagere, lblBeloeb, lblRundvisninger, lblBetalingsmetoder;
    private ListView<Rundvisning> lvwRundvisninger;

    public RundvisningPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        lblFornavn = new Label("Fornavn:");
        this.add(lblFornavn, 1, 1);
        txfFornavn = new TextField();
        this.add(txfFornavn, 2, 1);

        lblEfternavn = new Label("Efternavn:");
        this.add(lblEfternavn, 1, 2);
        txfEfternavn = new TextField();
        this.add(txfEfternavn, 2, 2);

        lblTelefonNr = new Label("Telefon nummer:");
        this.add(lblTelefonNr, 1, 3);
        txfTelefonNr = new TextField();
        this.add(txfTelefonNr, 2, 3);

        lblAntalDeltagere = new Label("Antal deltagere:");
        this.add(lblAntalDeltagere, 1, 4);
        txfAntalDeltagere = new TextField();
        this.add(txfAntalDeltagere, 2, 4);

        lblBeloeb = new Label("Samlet Beløb:");
        this.add(lblBeloeb, 1, 5);
        txfBeloeb = new TextField();
        this.add(txfBeloeb, 2, 5);

        lblRundvisninger = new Label("Planlagte rundvisninger:");
        this.add(lblRundvisninger, 0, 0);
        lvwRundvisninger = new ListView<>();
        this.add(lvwRundvisninger, 0, 1, 1,7);
        lvwRundvisninger.setEditable(false);
        lvwRundvisninger.setPrefHeight(150);
        //lvwRundvisninger.getItems().setAll(Storage.getProduktGrupper().get(0).getProdukter());


        lblBetalingsmetoder = new Label("Betalingsmetoder:");
        this.add(lblBetalingsmetoder,1,6);
        r1 = new RadioButton("Kontant");
        //this.add(r1,10,10);
        r2 = new RadioButton("Mobilepay");
        // this.add(r2,10,11);
        r3 = new RadioButton("Dankort");
        // this.add(r3,10,12);
        VBox box = new VBox(r1,r2,r3);
        this.add(box, 2, 6);

        btnOpretRundvisning = new Button("Opret rundvisning");
        this.add(btnOpretRundvisning,5,5);


        btnBetaling = new Button("Betal");
        this.add(btnBetaling,1,7);






    }

    private void opretRundvisning() {


    }
}

