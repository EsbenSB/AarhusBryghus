package aarhusBryghus.application.model;

public class Regning implements Betalingsform {
    @Override
    public String betal() {
        return "Regning";
    }
}
