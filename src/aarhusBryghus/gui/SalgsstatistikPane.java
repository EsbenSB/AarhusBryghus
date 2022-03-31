package aarhusBryghus.gui;

import aarhusBryghus.application.controller.Controller;
import aarhusBryghus.application.model.Ordre;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;


public class SalgsstatistikPane extends GridPane {

    private final DatePicker dpStartDato = new DatePicker(LocalDate.parse("2022-01-01"));
    private final DatePicker dpSlutDato = new DatePicker(LocalDate.now());
    private final TextField txfSolgteKlip, txfBrugteKlip, txfBetalingsform, txfSamletPris;
    private final Button btnSolgteKlip, btnBrugteKlip, dagensSalg, udlejedeProdukter;
    private final ListView<Ordre> lvwOrdrer;
    private final ListView<String> lvwOrdrelinjer;

    public SalgsstatistikPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        Label lblPrislister= new Label("Vælg startdato:");
        this.add(lblPrislister,0,0);

        Label lblSlutDato = new Label("Vælg slutdato: ");
        this.add(lblSlutDato,1,0,1,1);

        this.add(dpStartDato,0,1);
        this.add(dpSlutDato,1,1);

        dagensSalg = new Button("Dagens Salg");
        this.add(dagensSalg,3,0);
        dagensSalg.setOnAction(event -> hentDagensSalg());

        udlejedeProdukter = new Button("Udlejede Produkter");
        this.add(udlejedeProdukter,4,0);

        btnSolgteKlip = new Button("Solgte Klip");
        this.add(btnSolgteKlip,0,2);

        txfSolgteKlip = new TextField();
        this.add(txfSolgteKlip,0,3);

        txfBrugteKlip = new TextField();
        this.add(txfBrugteKlip,1,3);

        btnBrugteKlip = new Button("Brugte Klip");
        this.add(btnBrugteKlip,1,2);

        Label lblOrdrer = new Label("Ordre: ");
        this.add(lblOrdrer,1,4);

        Label lblOrdrelinjer = new Label("Ordrelinjer: ");
        this.add(lblOrdrelinjer,0,4);

        lvwOrdrer = new ListView<>();
        this.add(lvwOrdrer,0,5,1,5);
        ChangeListener<Ordre> ordreListener = (ov, gammelOrdre, nyOrdre) -> this.selectedOrdreChanged();
        lvwOrdrer.getSelectionModel().selectedItemProperty().addListener(ordreListener);

        lvwOrdrelinjer = new ListView<>();
        this.add(lvwOrdrelinjer,1,5,3,5);
        lvwOrdrelinjer.setPrefWidth(400);
        ChangeListener<String> ordrelinjeListener = (ov, gammelLinje, nyLinje) -> this.selectedOrdreLinjeChanged();
        lvwOrdrelinjer.getSelectionModel().selectedItemProperty().addListener(ordrelinjeListener);

        Label lblBetalingsform = new Label("Betalingsform: ");
        this.add(lblBetalingsform,4,4);

        txfBetalingsform = new TextField("Betalingsform");
        this.add(txfBetalingsform,4,5);

        Label lblSamletPris = new Label("Samlet Pris: ");
        this.add(lblSamletPris,4,6);

        txfSamletPris = new TextField("Samlet pris");
        this.add(txfSamletPris,4,7);
    }

    private void selectedOrdreLinjeChanged() {
        Ordre ordre = lvwOrdrer.getSelectionModel().getSelectedItem();
        txfBetalingsform.setText(ordre.getBetalingsform()+"");
        txfSamletPris.setText(ordre.getSamletPris()+"");
    }

    private void selectedOrdreChanged() {
        Ordre ordre = lvwOrdrer.getSelectionModel().getSelectedItem();
        lvwOrdrelinjer.getItems().setAll(Controller.udprintOrdre(ordre));
        txfBetalingsform.setText(ordre.getBetalingsform()+"");
        txfSamletPris.setText(ordre.getSamletPris()+"");
    }

    private void hentDagensSalg() {
        lvwOrdrer.getItems().setAll(Controller.getDagensSalg());
    }
}