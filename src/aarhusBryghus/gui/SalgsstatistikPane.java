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
    private final TextField txfSolgteKlip, txfBrugteKlip, txfBetalingsform, txfSamletPris, txfDagensOmsaetning;
    private final Button btnSolgteKlip, btnBrugteKlip, btnDagensSalg;
    private final ListView<Ordre> lvwOrdrer;
    private final ListView<String> lvwOrdrelinjer;
    private final TabPane tapPane = new TabPane();

    public SalgsstatistikPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        Label lblPrislister = new Label("Vælg startdato:");
        this.add(lblPrislister, 0, 0, 2, 1);

        Label lblSlutDato = new Label("Vælg slutdato: ");
        this.add(lblSlutDato, 2, 0, 2, 1);

        Label lblSolgteKlip = new Label("Find Antal Solgte Klip i perioden: ");
        this.add(lblSolgteKlip, 0, 2, 2, 1);

        Label lblBrugteKlip = new Label("Find Antal Brugte Klip i perioden: ");
        this.add(lblBrugteKlip, 2, 2, 2, 1);

        this.add(dpStartDato, 0, 1, 2, 1);
        this.add(dpSlutDato, 2, 1, 2, 1);

        Label lblDagensSalg = new Label("Finder alle salg, med dags dato");
        this.add(lblDagensSalg, 4, 0);

        btnDagensSalg = new Button("Dagens Salg");
        this.add(btnDagensSalg, 4, 1);
        btnDagensSalg.setPrefWidth(100);
        btnDagensSalg.setOnAction(event -> hentDagensSalg());

        btnSolgteKlip = new Button("Solgte Klip");
        this.add(btnSolgteKlip, 0, 3);
        btnSolgteKlip.setPrefWidth(100);
        btnSolgteKlip.setOnAction(event -> getAntalSolgteKlip());

        txfSolgteKlip = new TextField();
        this.add(txfSolgteKlip, 1, 3, 1, 1);

        txfBrugteKlip = new TextField();
        this.add(txfBrugteKlip, 3, 3, 1, 1);

        btnBrugteKlip = new Button("Brugte Klip");
        this.add(btnBrugteKlip, 2, 3);
        btnBrugteKlip.setPrefWidth(100);
        btnBrugteKlip.setOnAction(event -> getAntalBrugteKlip());

        Label lblOrdrer = new Label("Ordre: ");
        this.add(lblOrdrer, 0, 4);

        Label lblOrdrelinjer = new Label("Ordrens indhold: ");
        this.add(lblOrdrelinjer, 2, 4);

        lvwOrdrer = new ListView<>();
        this.add(lvwOrdrer, 0, 5, 2, 5);
        ChangeListener<Ordre> ordreListener = (ov, gammelOrdre, nyOrdre) -> this.selectedOrdreChanged();
        lvwOrdrer.getSelectionModel().selectedItemProperty().addListener(ordreListener);

        lvwOrdrelinjer = new ListView<>();
        this.add(lvwOrdrelinjer, 2, 5, 3, 5);
        lvwOrdrelinjer.setPrefWidth(400);
        ChangeListener<String> ordrelinjeListener = (ov, gammelLinje, nyLinje) -> this.selectedOrdreLinjeChanged();
        lvwOrdrelinjer.getSelectionModel().selectedItemProperty().addListener(ordrelinjeListener);

        Label lblBetalingsform = new Label("Betalingsform: ");
        this.add(lblBetalingsform, 5, 5);

        txfBetalingsform = new TextField("Betalingsform");
        this.add(txfBetalingsform, 5, 6);

        Label lblSamletPris = new Label("Samlet Pris: ");
        this.add(lblSamletPris, 5, 7);

        txfSamletPris = new TextField("Samlet pris");
        this.add(txfSamletPris, 5, 8);

        Label lblDagensOmsaetning = new Label("Dagens Omsætning: ");
        this.add(lblDagensOmsaetning, 4, 10);

        txfDagensOmsaetning = new TextField();
        this.add(txfDagensOmsaetning, 5, 10);
    }

    private void selectedOrdreLinjeChanged() {
        Ordre ordre = lvwOrdrer.getSelectionModel().getSelectedItem();
        txfBetalingsform.setText(ordre.getBetalingsform()+"");
        if(ordre.getBetalingsform().getType().equals("Klip")){
            txfSamletPris.setText(ordre.getSamletPrisKlip()+" Klip");
        } else{
            txfSamletPris.setText("Kr. " + ordre.getSamletPris());
            txfDagensOmsaetning.setText(Controller.getSamletSumDagensSalg(LocalDate.now())+"");
        }
    }

    private void selectedOrdreChanged() {
        Ordre ordre = lvwOrdrer.getSelectionModel().getSelectedItem();
        lvwOrdrelinjer.getItems().setAll(Controller.udprintOrdre(ordre));
        txfBetalingsform.setText(ordre.getBetalingsform()+"");
        if(ordre.getBetalingsform().getType().equals("Klip")){
            txfSamletPris.setText(ordre.getSamletPrisKlip()+" Klip");
        } else{
            txfSamletPris.setText("Kr. " + ordre.getSamletPris());
            txfDagensOmsaetning.setText(Controller.getSamletSumDagensSalg(LocalDate.now())+"");

        }
    }

    private void hentDagensSalg() {
        lvwOrdrer.getItems().setAll(Controller.getDagensSalg());
        txfDagensOmsaetning.setText(Controller.getSamletSumDagensSalg(LocalDate.now())+"");

    }

    private void getAntalSolgteKlip(){
        LocalDate startDato = dpStartDato.getValue();
        LocalDate slutDato = dpSlutDato.getValue();
        txfSolgteKlip.setText(""+Controller.getAntalSolgteKlip(startDato,slutDato));
    }
    private void getAntalBrugteKlip(){
        LocalDate startDato = dpStartDato.getValue();
        LocalDate slutDato = dpSlutDato.getValue();
        txfBrugteKlip.setText(""+Controller.getAntalForbrugteKlip(startDato,slutDato));
    }
}