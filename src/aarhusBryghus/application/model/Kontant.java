package aarhusBryghus.application.model;

public class Kontant implements Betalingsform{

    @Override
    public String getType() {
        return "Kontant";
    }
}
