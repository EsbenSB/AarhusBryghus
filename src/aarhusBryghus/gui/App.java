package aarhusBryghus.gui;

import aarhusBryghus.application.controller.Controller;
import javafx.application.Application;

public class App {

    public static void main(String[] args) {
        Controller controller = Controller.getInstance();
        controller.loadStorage();
        Application.launch(StartWindow.class);
    }
}
