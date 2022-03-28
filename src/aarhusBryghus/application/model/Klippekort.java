package aarhusBryghus.application.model;

public class Klippekort implements Betalingsform{
    @Override
    public String betal() {
        return "Klippekort";

    }
}
