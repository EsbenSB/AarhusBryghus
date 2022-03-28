package aarhusBryghus.application.model;

public class Pant {
    private int beloeb;

    public Pant(int beloeb) {
        this.beloeb = beloeb;
    }

    public int getBeloeb() {
        return beloeb;
    }

    @Override
    public String toString() {
        return "Pant{" +
                "beloeb=" + beloeb +
                '}';
    }
}
