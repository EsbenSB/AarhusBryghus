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
import javafx.scene.layout.GridPane;

import java.awt.*;

public class KasseapparatPane extends GridPane {
    //TODO Lav tekstfields og andet stuff her:

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


    }

    private void opretProduktAction() {
        ProduktWindow dia= new ProduktWindow("Opret Produkt");
        dia.showAndWait();

        //TODO Lav resten af metoden færdig når knappen skal laves færdig
    }
}
