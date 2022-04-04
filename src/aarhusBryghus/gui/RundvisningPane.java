package aarhusBryghus.gui;

import aarhusBryghus.application.controller.Controller;
import aarhusBryghus.application.model.*;
import aarhusBryghus.storage.Storage;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RundvisningPane extends GridPane {
    //TODO Lav tekstfields og andet stuff her:
    private Button btnBetaling, btnOpretRundvisning;
    private RadioButton r1, r2, r3, r4;
    private final TextField txfFornavn, txfEfternavn, txfTelefonNr, txfAntalDeltagere, txfBeloeb, txfFindKunde, txfTidspunkt, txfPrisPrPerson, txfPlanlagtTidspunkt;
    private final Label lblFornavn, lblEfternavn, lblTelefonNr, lblAntalDeltagere,
            lblBeloeb, lblRundvisninger, lblBetalingsmetoder, lblFindKunde, lblTidspunkt, lblPrisPrPerson, lblDato, lblPlanlagtTidspunkt;
    private ListView<Ordre> lvwRundvisninger;
    private final DatePicker dpDato;



    public RundvisningPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        lblFornavn = new Label("Fornavn:");
        this.add(lblFornavn, 1, 1);
        txfFornavn = new TextField();
        this.add(txfFornavn, 2, 1);
        txfFornavn.setEditable(false);

        lblEfternavn = new Label("Efternavn:");
        this.add(lblEfternavn, 1, 2);
        txfEfternavn = new TextField();
        this.add(txfEfternavn, 2, 2);
        txfEfternavn.setEditable(false);

        lblTelefonNr = new Label("Telefon nummer:");
        this.add(lblTelefonNr, 1, 3);
        txfTelefonNr = new TextField();
        this.add(txfTelefonNr, 2, 3);
        txfTelefonNr.setEditable(false);

        lblPlanlagtTidspunkt = new Label("Planlagt tidspunkt");
        this.add(lblPlanlagtTidspunkt, 1, 4);
        txfPlanlagtTidspunkt = new TextField();
        this.add(txfPlanlagtTidspunkt, 2, 4);

        lblAntalDeltagere = new Label("Antal deltagere:");
        this.add(lblAntalDeltagere, 1, 5);
        txfAntalDeltagere = new TextField();
        this.add(txfAntalDeltagere, 2, 5);

        lblBeloeb = new Label("Samlet Beløb:");
        this.add(lblBeloeb, 1, 6);
        txfBeloeb = new TextField();
        this.add(txfBeloeb, 2, 6);
        txfBeloeb.setEditable(false);

        lblFindKunde = new Label("Søg efter kunde (telefon):");
        this.add(lblFindKunde, 3, 1);
        txfFindKunde = new TextField();
        this.add(txfFindKunde, 4, 1);

        lblDato = new Label("Dato");
        this.add(lblDato, 3, 2);
        dpDato = new DatePicker();
        this.add(dpDato, 4, 2, 1, 1);

        lblTidspunkt = new Label("Klokkeslet:");
        this.add(lblTidspunkt, 3, 3);
        txfTidspunkt = new TextField();
        this.add(txfTidspunkt, 4, 3);


        lblPrisPrPerson = new Label("Pris pr. person:");
        this.add(lblPrisPrPerson, 3, 4);
        txfPrisPrPerson = new TextField();
        this.add(txfPrisPrPerson, 4, 4);

        lblRundvisninger = new Label("Planlagte rundvisninger:");
        this.add(lblRundvisninger, 0, 0);
        lvwRundvisninger = new ListView<>();
        this.add(lvwRundvisninger, 0, 1, 1, 7);
        lvwRundvisninger.setEditable(false);
        lvwRundvisninger.setPrefHeight(150);
        lvwRundvisninger.setPrefWidth(400);
        lvwRundvisninger.getItems().setAll(Controller.getNuværendeRundvisninger());
        ChangeListener<Ordre> rundvisningListener = (ov, gammelRundvisning, nyRundvisning) -> this.selectedRundvisningChanged();
        lvwRundvisninger.getSelectionModel().selectedItemProperty().addListener(rundvisningListener);


        lblBetalingsmetoder = new Label("Betalingsmetoder:");
        this.add(lblBetalingsmetoder, 1, 7);
        r1 = new RadioButton("Kontant");
        r2 = new RadioButton("Mobilepay");
        r3 = new RadioButton("Dankort");
        r4 = new RadioButton("Regning");
        VBox box = new VBox(r1, r2, r3, r4);
        this.add(box, 2, 7, 1,2);

        btnOpretRundvisning = new Button("Opret rundvisning");
        this.add(btnOpretRundvisning, 4, 5);
        btnOpretRundvisning.setOnAction(event -> opretRundvisning());
        btnOpretRundvisning.setAlignment(Pos.TOP_LEFT);

        btnBetaling = new Button("Betal");
        this.add(btnBetaling, 1, 8);








    }

    private void opretRundvisning() {

        Kunde kunde = Controller.findKunde(Integer.parseInt(txfFindKunde.getText()));
        Double pris = Double.parseDouble(txfPrisPrPerson.getText());
        LocalDate date = dpDato.getValue();
        LocalTime tid = LocalTime.parse(txfTidspunkt.getText());

           Ordre rundvisningTilKunde = Controller.createRundvisning(Controller.getButikPrisliste(), kunde,
                 LocalDateTime.of(date,tid), Controller.getRundvisningsProduktgruppe(), pris);
        lvwRundvisninger.getItems().setAll(Controller.getNuværendeRundvisninger());

    }

    private void selectedRundvisningChanged() {

        Ordre rundvisning = lvwRundvisninger.getSelectionModel().getSelectedItem();
        if (rundvisning != null) {

            LocalDateTime tidspunkt = rundvisning.getOrdrelinjer().get(0).getProdukt().getTidspunkt();

            txfFornavn.setText(rundvisning.getKunde().getFornavn());
            txfEfternavn.setText(rundvisning.getKunde().getEfternavn());
            txfTelefonNr.setText(rundvisning.getKunde().getTelefon() + "");
            txfPlanlagtTidspunkt.setText(rundvisning.getOrdrelinjer().get(0).getProdukt().getDatoForRundvisning(tidspunkt) + " " + rundvisning.getOrdrelinjer().get(0).getProdukt().getKlokkesletForRundvisning(tidspunkt));
            //txfBeloeb.setText(rundvisning.getPrisliste().getPriser().get(0) + "");
        }

    }


}

