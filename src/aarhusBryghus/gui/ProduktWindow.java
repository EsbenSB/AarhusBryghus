package aarhusBryghus.gui;

import aarhusBryghus.application.model.Produkt;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;

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
    //TODO Put textfields og andet under her:
    private Label lblNavn, lblPris, lblError;
    private TextField txfNavn;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lblNavn = new Label("Navn:");
        pane.add(lblNavn,0,0);

        txfNavn = new TextField();
        pane.add(txfNavn,0,1);
        txfNavn.setPrefWidth(200);

        lblPris = new Label("Pris:");
        pane.add(lblPris,0,2);

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
    
    //TODO Lav metode
    private void okAction() {

    }
}
