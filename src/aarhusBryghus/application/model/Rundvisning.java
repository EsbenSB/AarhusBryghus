package aarhusBryghus.application.model;

import java.time.LocalDateTime;

public class Rundvisning extends Produkt{

    private LocalDateTime tidspunkt;
    public Rundvisning(String navn, MaaleEnhed maaleEnhed, LocalDateTime tidspunkt) {
        super(navn, maaleEnhed);
        this.tidspunkt = tidspunkt;
    }


}
