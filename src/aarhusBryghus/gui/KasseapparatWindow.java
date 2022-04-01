package aarhusBryghus.gui;

import aarhusBryghus.application.model.Ordre;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KasseapparatWindow extends Stage {
    private Ordre ordre;

    public KasseapparatWindow(String title, Ordre ordre) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.ordre = ordre;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public KasseapparatWindow(String title) {this(title,null);}

    private Button btnLuk;
    private TextField txfPris, txfMetode;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(50);
        pane.setVgap(50);
        pane.setGridLinesVisible(false);

        btnLuk = new Button("luk");
        pane.add(btnLuk, 0, 0);
        btnLuk.setOnAction(event -> lukAction());

        Label lblPris = new Label("Du har betalt:");
        pane.add(lblPris, 0, 1);

        txfPris = new TextField();
        pane.add(txfPris, 1, 1);
        txfPris.setEditable(false);

        Label lblMetode = new Label("Du har betalt med:");
        pane.add(lblMetode, 0, 2);

        txfMetode = new TextField();
        pane.add(txfMetode, 1, 2);
        txfMetode.setEditable(false);
    }

    private void lukAction() {
        this.hide();
    }
}
