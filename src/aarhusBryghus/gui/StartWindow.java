package aarhusBryghus.gui;

import aarhusBryghus.application.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartWindow extends Application {

    @Override
    public void init() {
        Controller.initStorage();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Aarhus Bryghus");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    // -----------------------------------------------------------------------------------------------------------------

    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTapPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTapPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Laver tab til kasseapparatet
        Tab tabKasseapparat = new Tab("Kasseapparat");
        tabPane.getTabs().add(tabKasseapparat);

        KasseapparatPane kasseapparatPane = new KasseapparatPane();
        tabKasseapparat.setContent(kasseapparatPane);

        // Laver tab til salgsstatistik
        Tab tabSalgsstatistik = new Tab("Salgsstatistik");
        tabPane.getTabs().add(tabSalgsstatistik);

        SalgsstatistikPane salgsstatestikPane = new SalgsstatistikPane();
        tabSalgsstatistik.setContent(salgsstatestikPane);
    }
}
