package aarhusBryghus.gui;

import aarhusBryghus.application.model.Produkt;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
    }
}
