package aarhusBryghus.application.model;

public class MobilePay implements Betalingsform{
    @Override
    public String betal() {
        return "MobilePay";

    }
}
