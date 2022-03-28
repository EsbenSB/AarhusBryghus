package aarhusBryghus.application.model;

public class Dankort implements Betalingsform{
    @Override
    public String betal() {
        return "Dankort";
    }
}
