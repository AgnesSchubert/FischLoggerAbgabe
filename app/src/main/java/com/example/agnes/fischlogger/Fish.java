package com.example.agnes.fischlogger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Agnes on 12.06.2017.
 */

class Fish {
    private String art;
    private double laenge;
    private Seite bpa;
    private Seite sv;
    private boolean haematom;
    private String heamatom_stelle;
    private boolean schuerfung;
    private String schuerfung_stelle;
    private boolean schuerfung_verpilzt;
    private boolean ow;
    private String ow_stelle;
    private boolean ow_verpilzt;
    private boolean ta;
    private String ta_stelle;
    private boolean totaldurchtrennung;
    private boolean verpilzung;
    private String bemerkung;
    private long datum;
    private Long id;


    Fish(
            String art,
            double laenge,
            Seite bpa,
            Seite sv,
            boolean haematom,
            String heamatom_stelle,
            boolean schuerfung,
            String schuerfung_stelle,
            boolean schuerfung_verpilzt,
            boolean ow,
            String ow_stelle,
            boolean ow_verpilzt,
            boolean ta,
            String ta_stelle,
            boolean totaldurchtrennung,
            boolean verpilzung,
            String bemerkung,
            long datum,
            Long id
    ) {
        this.art = art;
        this.laenge = laenge;
        this.bpa = bpa;
        this.sv = sv;
        this.haematom = haematom;
        this.heamatom_stelle = heamatom_stelle;
        this.schuerfung = schuerfung;
        this.schuerfung_stelle = schuerfung_stelle;
        this.schuerfung_verpilzt = schuerfung_verpilzt;
        this.ow = ow;
        this.ow_stelle = ow_stelle;
        this.ow_verpilzt = ow_verpilzt;
        this.ta = ta;
        this.ta_stelle = ta_stelle;
        this.totaldurchtrennung = totaldurchtrennung;
        this.verpilzung = verpilzung;
        this.bemerkung = bemerkung;
        this.datum = datum;
        this.id = id;
    }

    // Getter & Setter
    public String getArt() {return art;}
    public double getLaenge() {return laenge;}
    public Seite getBpa() {return bpa;}
    public Seite getSv() {return sv;}
    public boolean getHaematom() {return haematom;}
    public String getHaematomStelle() {return heamatom_stelle;}
    public boolean getSchuerfung() {return schuerfung;}
    public String getSchuerfungStelle() {return schuerfung_stelle;}
    public boolean getSchuerfungVerpilzt() {return schuerfung_verpilzt;}
    public boolean getOw() {return ow;}
    public String getOwStelle() {return ow_stelle;}
    public boolean getOwVerpilzt() {return ow_verpilzt;}
    public boolean getTa() {return ta;}
    public String getTaStelle() {return ta_stelle;}
    public boolean getTotaldurchtrennung() {return totaldurchtrennung;}
    public boolean getVerpilzung() {return verpilzung;}
    public String getBemerkung() {return bemerkung;}
    public long getDatum() {return datum;}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd.MM.yy HH:mm");
        return art + " : " + laenge + " (" + df.format(datum) + ")";
    }

}
