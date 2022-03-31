package aarhusBryghus.application.model;

public class Klip implements Betalingsform{

    @Override
    public String getType() {
        return "Klip";
    }

    public String toString() {
        return "Klippekort";
    }
}
