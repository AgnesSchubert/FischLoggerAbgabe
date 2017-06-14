package com.example.agnes.fischlogger;

/**
 * Created by Agnes on 12.06.2017.
 */

public enum Seite {
    BOTH("beidseitig"), ONE("einseitig"), NONE("keine");

    private final String name;
    Seite(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public static Seite toSeite(String name){
        switch (name){
            case "beidseitig": return BOTH;
            case "einseitig": return ONE;
            case "keine": return NONE;
        }
        return null;
    }
}
