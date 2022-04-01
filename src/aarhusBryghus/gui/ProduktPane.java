package aarhusBryghus.gui;

import aarhusBryghus.application.controller.Controller;
import aarhusBryghus.application.model.*;
import aarhusBryghus.storage.Storage;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.util.Optional;

public class ProduktPane extends GridPane {

    private ComboBox<String> cbElementTyper = new ComboBox<>();
    private ListView<Produktgruppe> lvwProduktgruppe = new ListView<>();
    private Button btnAccepter = new Button("Acceptér");

    public ProduktPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // indsæt knapper og listview osv her
        Label lblInformationsTekst = new Label("Vælg et element du vil opdatere, eller opret et nyt");
        this.add(lblInformationsTekst,0,0,3,1);





    }

}
