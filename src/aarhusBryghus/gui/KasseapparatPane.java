package aarhusBryghus.gui;

import aarhusBryghus.application.model.Prisliste;
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
//    private final ListView<Prisliste> lvwPrislister;
    private final Button btnBetaling;
    private final RadioButton r1, r2, r3, r4;

    public KasseapparatPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        //TODO Lav knap færdig
        btnBetaling = new Button("Betal");
        this.add(btnBetaling,11,12);
        GridPane.setHalignment(btnBetaling, HPos.RIGHT);

        Label lblBetalingsmetoder = new Label("Betalingsmetoder:");
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

}
