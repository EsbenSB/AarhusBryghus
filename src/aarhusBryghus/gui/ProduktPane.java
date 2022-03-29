package aarhusBryghus.gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ProduktPane extends GridPane {
    private Button btnOpretProdukt;

    public ProduktPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        //TODO Tilføj stuff til GUI:
        //TODO Gør så knap virker og sat det rigtige sted
        //Knap til opret produkt
        btnOpretProdukt = new Button("Opret Produkt");
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
