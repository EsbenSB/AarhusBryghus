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
        Controller.init();
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

        //Laver tab til produkt
        Tab tabProdukt = new Tab("Produkt");
        tabPane.getTabs().add(tabProdukt);

        ProduktPane produktPane = new ProduktPane();
        tabProdukt.setContent(produktPane);

        // Laver tab til salgsstatistik
        Tab tabSalgsstatistik = new Tab("Statistik");
        tabPane.getTabs().add(tabSalgsstatistik);

        SalgsstatistikPane salgsstatestikPane = new SalgsstatistikPane();
        tabSalgsstatistik.setContent(salgsstatestikPane);

        // Laver tab til Rundvisning
        Tab tabRundvisning = new Tab("Rundvisning");
        tabPane.getTabs().add(tabRundvisning);

        RundvisningPane rundvisningPane= new RundvisningPane();
        tabRundvisning.setContent(rundvisningPane);

        // Laver tab til Udlejning
        Tab tabUdlejning = new Tab("Udlejning");
        tabPane.getTabs().add(tabUdlejning);

        UdlejningPane udlejningPane = new UdlejningPane();
        tabUdlejning.setContent(udlejningPane);
    }
}
