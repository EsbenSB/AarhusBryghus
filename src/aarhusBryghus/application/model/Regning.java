package aarhusBryghus.application.model;

public class Regning implements Betalingsform {

    @Override
    public String getType() {
        return "Regning";
    }

    @Override
    public String toString() {
        return "Regning";
    }
}
