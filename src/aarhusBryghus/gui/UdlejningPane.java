package aarhusBryghus.gui;

import aarhusBryghus.application.controller.Controller;
import aarhusBryghus.application.model.Kunde;
import aarhusBryghus.application.model.Ordre;
import aarhusBryghus.application.model.Ordrelinje;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class UdlejningPane extends GridPane {

    private Label lblUdlejninger, lblOrdrelinjer, lblSamletPant, lblSoegEfterKunde, lblSamletPris, lblRetOrdreLinje, lblBetalingsmetoder;
    private Button btnBetaling, btnBeregnSamletPris, btnSoegEfterKunde, btnRetOrdre;
    private RadioButton rbKontant, rbRegning, rbMobilepay, rbDankort;
    private final ToggleGroup groupBetalingsmetode = new ToggleGroup();
    private ListView<Ordre> lvwUdlejninger = new ListView<Ordre>();
    private ListView<Ordrelinje> lvwOrdrelinjer = new ListView<>();
    private TextField txfSamletPant, txfSoegKunde, txfSamletPris, txfRetAntal;



    public UdlejningPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        txfRetAntal = new TextField();
        this.add(txfRetAntal,1,10);

        txfSamletPris = new TextField();
        this.add(txfSamletPris,3,5);

        txfSamletPant = new TextField();
        this.add(txfSamletPant,2,1);

        txfSoegKunde = new TextField("Indtast Mobilnummer");
        this.add(txfSoegKunde,3,1);
        txfSoegKunde.setOnMouseClicked(event -> soegKundeEvent()); //Fjerner "indtast mobilnummer" i feltet når man klikker
        Label lblAccepterAendringer = new Label("Juster den antallet på den valgte ordrelinje: ");
        this.add(lblAccepterAendringer,0,10);

        Label lblNote = new Label("NB: Sætter du antallet til 0, slettes linjen helt. ");
        this.add(lblNote,0,11);

        Label lblNote2 = new Label("Kunden afregnes for de produkter som er tilbage, minus hans betalte pant.");
        this.add(lblNote2,0,12);

        btnRetOrdre = new Button("Accepter Ændring");
        this.add(btnRetOrdre,1,11);

        btnSoegEfterKunde = new Button("Find kunde");
        this.add(btnSoegEfterKunde,3,3);
        btnSoegEfterKunde.setOnAction(event -> soegEfterKunde());
        btnBeregnSamletPris = new Button("Beregn Pris");
        this.add(btnBeregnSamletPris,2,4);

        lblUdlejninger = new Label("Udlejninger");
        this.add(lblUdlejninger,0,0);

        this.add(lvwUdlejninger,0,1,1,9);
        lvwUdlejninger.setPrefHeight(200);
        ChangeListener<Ordre> ordreListener = (ov, gammelOrdre, nyOrdre) -> this.selectedOrdreChanged();
        lvwUdlejninger.getSelectionModel().selectedItemProperty().addListener(ordreListener);

        // TODO Ret til korrekt liste
        lvwUdlejninger.getItems().setAll(Controller.getNuværendeUdlejninger());


        this.add(lvwOrdrelinjer,1,1,1,9);
        lvwOrdrelinjer.setPrefHeight(200);

        lblOrdrelinjer = new Label("Ordrelinjer");
        this.add(lblOrdrelinjer,1,0);

        lblSamletPant = new Label("Samlet Pant:");
        this.add(lblSamletPant,2,0);

        lblSoegEfterKunde = new Label("Find Udlejning: ");
        this.add(lblSoegEfterKunde,3,0);

        lblSamletPris = new Label("Samlet Pris:");
        this.add(lblSamletPris,3,4);

        btnBetaling = new Button("Betal");
        this.add(btnBetaling,3,12);
        GridPane.setHalignment(btnBetaling, HPos.RIGHT);

        Label lblBetalingsmetoder = new Label("Betalingsmetoder:");
        this.add(lblBetalingsmetoder,3,7);

        rbKontant = new RadioButton("Kontant");
        this.add(rbKontant,3,11);
        rbKontant.setToggleGroup(groupBetalingsmetode);

        rbMobilepay = new RadioButton("Mobilepay");
        this.add(rbMobilepay,3,8);
        rbMobilepay.setToggleGroup(groupBetalingsmetode);

        rbDankort = new RadioButton("Dankort");
        this.add(rbDankort,3,9);
        rbDankort.setToggleGroup(groupBetalingsmetode);

        rbRegning = new RadioButton("Regning");
        this.add(rbRegning,3,10);
        rbRegning.setToggleGroup(groupBetalingsmetode);



    }

    private void selectedOrdreChanged() {
        Ordre ordre = lvwUdlejninger.getSelectionModel().getSelectedItem();
        lvwOrdrelinjer.getItems().setAll(ordre.getOrdrelinjer());
    }

    private void selectedOrdreLinjeChanged() {
        Ordrelinje ordrelinje = lvwOrdrelinjer.getSelectionModel().getSelectedItem();
        txfRetAntal.setText(ordrelinje.getAntal()+"");

        // TODO - listerner  vil ikk virker - hvad fanden sker der??
    }

    private void soegEfterKunde() {
        try{
            int soegeord = Integer.parseInt(txfSoegKunde.getText());{
                Ordre kundeOrdrer = Controller.getKundeUdlejninger(soegeord);
                if(kunde != null){
                    lvwUdlejninger.getItems().setAll(Controller.getKundeUdlejninger(kunde));
                }
            }
        } catch (Exception e){
            e.getMessage();
        }

    }

    private void soegKundeEvent() {
        txfSoegKunde.clear();
    }

}

