package aarhusBryghus.application.model;

public class Dankort implements Betalingsform{

    @Override
    public String getType() {
        return "Dankort";
    }

    public String toString() {
        return "Dankort";
    }
}
