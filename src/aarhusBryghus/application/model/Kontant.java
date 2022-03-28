package aarhusBryghus.application.model;

public class Kontant implements Betalingsform{
    @Override
    public String betal() {
        return "Kontant";

    }
}
