package aarhusBryghus.gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;

import java.awt.*;

public class KasseapparatPane extends GridPane {
    //TODO Lav tekstfields og andet stuff her:
    Label lblBetalingsmetoder;

    public KasseapparatPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        //TODO Tilføj stuff til GUI:

        Label lblTest = new Label("Test");

        //TODO Gør så knap virker og sat det rigtige sted
        //Knap til opret produkt
        Button btnOpretProdukt = new Button("Opret Produkt");
        this.add(btnOpretProdukt,0,0);
        GridPane.setHalignment(btnOpretProdukt, HPos.RIGHT);
        btnOpretProdukt.setOnAction(event -> this.opretProduktAction());

        lblBetalingsmetoder = new Label("Betalingsmetoder:");
        this.add(lblBetalingsmetoder,10,9);
        //TODO Går så man kun kan vælge en RadioButton af gangen
        RadioButton r1 = new RadioButton("Kontant");
        this.add(r1,10,10);

        RadioButton r2 = new RadioButton("Mobilepay");
        this.add(r2,10,11);

        RadioButton r3 = new RadioButton("Dankort");
        this.add(r3,10,12);

        RadioButton r4 = new RadioButton("Klippekort");
        this.add(r4,10,13);
    }

    private void opretProduktAction() {
        ProduktWindow dia= new ProduktWindow("Opret Produkt");
        dia.showAndWait();

        //TODO Lav resten af metoden færdig når knappen skal laves færdig
    }
}
