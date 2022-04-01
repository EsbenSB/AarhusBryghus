package aarhusBryghus.gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;

public class RundvisningPane extends GridPane {
    //TODO Lav tekstfields og andet stuff her:
    Label lblBetalingsmetoder;
    Button btnBetaling;
    RadioButton r1, r2, r3, r4;

    public RundvisningPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        //TODO Gør så knap virker og sat det rigtige sted
        //Knap til opret produkt
        Button btnOpretProdukt = new Button("Opret Produkt");
        this.add(btnOpretProdukt,0,0);
        GridPane.setHalignment(btnOpretProdukt, HPos.RIGHT);
        btnOpretProdukt.setOnAction(event -> this.opretProduktAction());
        //TODO Lav knap færdig
        btnBetaling = new Button("Betal");
        this.add(btnBetaling,11,12);
        GridPane.setHalignment(btnBetaling, HPos.RIGHT);

        lblBetalingsmetoder = new Label("Betalingsmetoder:");
        this.add(lblBetalingsmetoder,10,9);
        //TODO Går så man kun kan vælge en RadioButton af gangen
        r1 = new RadioButton("Kontant");
        this.add(r1,10,10);

        r2 = new RadioButton("Mobilepay");
        this.add(r2,10,11);

        r3 = new RadioButton("Dankort");
        this.add(r3,10,12);

        r4 = new RadioButton("Klippekort");
        this.add(r4,10,13);
    }

    private void opretProduktAction() {


    }
}

