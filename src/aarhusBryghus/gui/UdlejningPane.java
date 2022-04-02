package aarhusBryghus.gui;

import aarhusBryghus.application.controller.Controller;
import aarhusBryghus.application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class UdlejningPane extends GridPane {

    private Label lblUdlejninger, lblOrdrelinjer, lblSamletPant, lblSoegEfterKunde, lblSamletPris, lblRetOrdreLinje, lblBetalingsmetoder, lblSucces, lblError;
    private Button btnBetaling, btnBeregnSamletPris, btnSoegEfterKunde, btnRetOrdre;
    private RadioButton rbKontant, rbRegning, rbMobilepay, rbDankort;
    private final ToggleGroup groupBetalingsmetode = new ToggleGroup();
    private ListView<Ordre> lvwUdlejninger = new ListView<Ordre>();
    private ListView<Ordrelinje> lvwOrdrelinjer = new ListView<>();
    private TextField txfSamletPant, txfSoegKunde, txfSamletPris, txfRetAntal;
    private Betalingsform betalingsform;


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
        btnRetOrdre.setOnAction(event -> accepterAntalÆndringerKnap());

        btnSoegEfterKunde = new Button("Find kunde");
        this.add(btnSoegEfterKunde,3,3);
        btnSoegEfterKunde.setOnAction(event -> soegEfterKunde());
        btnBeregnSamletPris = new Button("Beregn Pris");
        this.add(btnBeregnSamletPris,2,4);

        lblUdlejninger = new Label("Udlejninger");
        this.add(lblUdlejninger,0,0);

        this.add(lvwUdlejninger,0,1,1,9);
        lvwUdlejninger.setPrefHeight(200);
        lvwUdlejninger.getItems().setAll(Controller.getNuværendeUdlejninger());
        ChangeListener<Ordre> ordreListener = (ov, gammelOrdre, nyOrdre) -> this.selectedOrdreChanged();
        lvwUdlejninger.getSelectionModel().selectedItemProperty().addListener(ordreListener);

        this.add(lvwOrdrelinjer,1,1,1,9);
        lvwOrdrelinjer.setPrefHeight(200);
        ChangeListener<Ordrelinje> ordreLinjeListener = (ovl, gammelOrdrelinje, nyOrdrelinje) -> this.selectedOrdreLinjeChanged();
        lvwOrdrelinjer.getSelectionModel().selectedItemProperty().addListener(ordreLinjeListener);

        lblOrdrelinjer = new Label("Ordrelinjer");
        this.add(lblOrdrelinjer,1,0);

        lblSamletPant = new Label("Samlet Betalt Pant:");
        this.add(lblSamletPant,2,0);

        lblSoegEfterKunde = new Label("Find Udlejning: ");
        this.add(lblSoegEfterKunde,3,0);

        lblSamletPris = new Label("Samlet Pris:");
        this.add(lblSamletPris,3,4);

        btnBetaling = new Button("Betal");
        this.add(btnBetaling,3,12);
        GridPane.setHalignment(btnBetaling, HPos.RIGHT);
        btnBetaling.setOnAction(event -> betalingAfUdlejningKnap());

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
        groupBetalingsmetode.selectedToggleProperty().addListener((observable, oldVal, newVal) -> betalingsformValgt(newVal));

        rbKontant.setDisable(true);
        rbMobilepay.setDisable(true);
        rbDankort.setDisable(true);
        rbRegning.setDisable(true);

        lblError = new Label();
        this.add(lblError, 0, 13);
        lblError.setStyle("-fx-text-fill: red");

        lblSucces = new Label();
        this.add(lblSucces, 0, 13);
        lblSucces.setStyle("-fx-text-fill: green");

    }

    private void betalingsformValgt(Toggle newVal) {
        Ordre ordre = lvwUdlejninger.getSelectionModel().getSelectedItem();
        if(ordre != null){
            if(newVal.getToggleGroup().getSelectedToggle().equals(rbDankort)){
                System.out.println("Dankort");
                Betalingsform dankort = new Dankort();
                this.betalingsform = dankort;
                ordre.setBetalingsform(dankort);

            } else if (newVal.getToggleGroup().getSelectedToggle().equals(rbMobilepay)){
                System.out.println("Mobilepay");
                Betalingsform mobilepay = new MobilePay();
                this.betalingsform = mobilepay;
                ordre.setBetalingsform(mobilepay);
                Controller.

            } else if (newVal.getToggleGroup().getSelectedToggle().equals(rbRegning)){
                System.out.println("Regning");
                Betalingsform regning = new Regning();
                this.betalingsform = regning;
                ordre.setBetalingsform(regning);
            } else if (newVal.getToggleGroup().getSelectedToggle().equals(rbKontant)){
                System.out.println("kontant");
                Betalingsform kontant = new Kontant();
                this.betalingsform = kontant;
                ordre.setBetalingsform(kontant);
            }
        }

    }

    private void betalingAfUdlejningKnap() {
        Ordre ordre = lvwUdlejninger.getSelectionModel().getSelectedItem();
        Ordrelinje ordrelinje = lvwOrdrelinjer.getSelectionModel().getSelectedItem();
        Controller.lukUdlejningOrdre(ordre,ordre.getBetalingsform());
        lvwUdlejninger.getItems().setAll(Controller.getNuværendeUdlejninger());
    }

    private void accepterAntalÆndringerKnap() {
        int antal = Integer.parseInt(txfRetAntal.getText().trim());
        Ordre ordre = lvwUdlejninger.getSelectionModel().getSelectedItem();
        Ordrelinje ordrelinje = lvwOrdrelinjer.getSelectionModel().getSelectedItem();
        if(antal >= 0 && antal <= 10000){
            if(antal == 0){
                ordre.removeOrdrelinje(ordrelinje);
                lvwOrdrelinjer.getItems().setAll(ordre.getOrdrelinjer());
                txfSamletPris.setText(ordre.getSamletPris()+"");
                lblSucces.setText("Ordrelinje slettet");
                lblError.setText("");
                txfRetAntal.clear();

            } else {
                ordrelinje.setAntal(antal);
                lvwOrdrelinjer.getItems().setAll(ordre.getOrdrelinjer());
                txfSamletPris.setText(ordre.getSamletPris()+"");
                txfRetAntal.clear();
            }
        } else{
            lblError.setText("Antal skal være mellem 0 og 10000");
            lblSucces.setText("");

        }

    }

    private void selectedOrdreChanged() {
        Ordre ordre = lvwUdlejninger.getSelectionModel().getSelectedItem();
        if (ordre != null && ordre.getOrdrelinjer() != null){
            rbKontant.setDisable(false);
            rbMobilepay.setDisable(false);
            rbDankort.setDisable(false);
            rbRegning.setDisable(false);
            lvwOrdrelinjer.getItems().setAll(ordre.getOrdrelinjer());
            txfSamletPant.setText(ordre.getSamletPantPaaOrdre()+"");
            txfSamletPris.setText(ordre.getSamletPris()+"");
        }
    }

    private void selectedOrdreLinjeChanged() {
        Ordre ordre = lvwUdlejninger.getSelectionModel().getSelectedItem();
        Ordrelinje ordrelinje = lvwOrdrelinjer.getSelectionModel().getSelectedItem();
        if(ordre != null && ordrelinje != null){
            txfRetAntal.setText(ordrelinje.getAntal()+"");

        }
    }

    private void soegEfterKunde() {
        try{
            int soegeord = Integer.parseInt(txfSoegKunde.getText());
                Kunde kunde = Controller.findKunde(soegeord);
                    lvwUdlejninger.getItems().setAll(Controller.getKundeUdlejninger(kunde));
                    lvwOrdrelinjer.getItems().clear();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private void soegKundeEvent() {
        txfSoegKunde.clear();
    }

}

