package aarhusBryghus.application.model;

public class MaaleEnhed {
    private String enhed;
    private int tal;

    // opretter en måleenhed, som skal bruges til produkterne
    // produkterne har forskellige måleenheder
    // fx. centiliter, liter, eller kilo.
    public MaaleEnhed(String navn, int tal) {
        this.enhed = navn;
        this.tal = tal;
    }

    public String getEnhed() {
        return enhed;
    }

    public int getTal() {
        return tal;
    }

    public void setEnhed(String enhed) {
        this.enhed = enhed;
    }

    public void setTal(int tal) {
        this.tal = tal;
    }

    @Override
    public String toString() {
        return tal + " " + enhed;
    }
}
