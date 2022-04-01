package aarhusBryghus.gui;

import aarhusBryghus.application.controller.Controller;
import aarhusBryghus.application.model.Kunde;
import aarhusBryghus.application.model.Ordre;
import aarhusBryghus.application.model.Produkt;
import aarhusBryghus.storage.Storage;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.Optional;
public class KundePane extends GridPane {

    private final TextField txfFornavn, txfEfternavn, txfTelefonNr, txfIndtjeningFraKunde;
    private final Label lblFornavn, lblEfternavn, lblTelefonNr, lblIndtjeningFraKunde, lblKunder, lblOrdrer;
    private  ListView<Kunde> lvwKunder;
    private  ListView<Ordre> lvwOrdrer;
    private final Button btnOpdater, btnOpret;

    public KundePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        lblFornavn = new Label("Fornavn");
        this.add(lblFornavn, 1, 1);
        txfFornavn = new TextField();
        this.add(txfFornavn, 2, 1);

        lblEfternavn = new Label("Efternavn");
        this.add(lblEfternavn, 1, 2);
        txfEfternavn = new TextField();
        this.add(txfEfternavn, 2, 2);

        lblTelefonNr = new Label("Telefon nummer");
        this.add(lblTelefonNr, 1, 3);
        txfTelefonNr = new TextField();
        this.add(txfTelefonNr, 2, 3);

        lblIndtjeningFraKunde = new Label("Indtjening fra kunde");
        this.add(lblIndtjeningFraKunde, 3, 6);
        txfIndtjeningFraKunde = new TextField();
        this.add(txfIndtjeningFraKunde, 3, 7);

        lblKunder = new Label("Kunder");
        this.add(lblKunder, 0, 0);
        lvwKunder = new ListView<>();
        this.add(lvwKunder, 0, 1, 1,5);
        lvwKunder.setEditable(false);
        lvwKunder.setPrefHeight(150);
        lvwKunder.getItems().setAll(Storage.getKunder());
        ChangeListener<Kunde> kundeListener = (ov, gammelKunde, nyKunde) -> this.selectedKundeChanged();
        lvwKunder.getSelectionModel().selectedItemProperty().addListener(kundeListener);

        lblOrdrer = new Label("Kundens ordrer");
        this.add(lblOrdrer, 3, 0);
        lvwOrdrer = new ListView<>();
        this.add(lvwOrdrer, 3, 1,1,5);
        lvwOrdrer.setEditable(false);
        lvwOrdrer.setPrefHeight(150);

        btnOpdater = new Button("Opdater");
        this.add(btnOpdater, 2, 4);
        btnOpdater.setOnAction(event -> opdaterKunde());


        btnOpret = new Button("Opret");
        this.add(btnOpret, 2, 5);
        btnOpret.setOnAction(event -> opretKunde());

    }

    private void opdaterKunde() { // todo: tjek for at parametre ikke eksisterer på en anden kunde

    }
    private void opretKunde() { // todo: tjek for at parametre ikke eksisterer på en anden kunde
        String fornavn = txfFornavn.getText();
        String efternavn = txfEfternavn.getText();
        int telefonNr = Integer.parseInt(txfTelefonNr.getText());

        Kunde kunde = Controller.createKunde(fornavn, efternavn, telefonNr);
        lvwKunder.getItems().setAll(Storage.getKunder());
        lvwKunder.getSelectionModel().select(kunde);

    }



    private void selectedKundeChanged() {
        Kunde kunde = lvwKunder.getSelectionModel().getSelectedItem();
        if(kunde != null) {
            lvwOrdrer.getItems().setAll(Controller.getKundensSamledeOrdre(kunde));
            txfFornavn.setText(kunde.getFornavn());
            txfEfternavn.setText(kunde.getEfternavn());
            txfTelefonNr.setText(kunde.getTelefon() + "");
        }

    }


}
