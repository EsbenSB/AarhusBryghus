package aarhusBryghus.application.controller;

import aarhusBryghus.storage.Storage;

public class ControlleApp {
    public static void main(String[] args) {
        Controller.init();
        System.out.println(Storage.produktGrupper.get(0).getProdukter());
    }
}
