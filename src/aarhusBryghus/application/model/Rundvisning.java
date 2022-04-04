package aarhusBryghus.application.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Rundvisning extends Produkt{

    private LocalDateTime tidspunkt;
    public Rundvisning(String navn, Maaleenhed maaleEnhed, LocalDateTime tidspunkt) {


        super(navn, maaleEnhed);
        this.tidspunkt = tidspunkt;
    }

    @Override
    public LocalTime getKlokkesletForRundvisning(LocalDateTime tidspunkt){

        return  tidspunkt.toLocalTime();
    }

    @Override
    public LocalDate getDatoForRundvisning(LocalDateTime tidspunkt){
       return tidspunkt.toLocalDate();

    }

    public LocalDateTime getTidspunkt() {
        return tidspunkt;
    }

    @Override
    public String toString() {
        return "Tidspunkt for rundvisning: " + getDatoForRundvisning(this.tidspunkt) + " " + getKlokkesletForRundvisning(this.tidspunkt);
    }
}
