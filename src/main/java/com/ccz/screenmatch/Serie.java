package com.ccz.screenmatch;

public class Serie extends Titulo {

    private String totalSeasons; // Ej: "5"

    public String getTotalSeasons() {
        return totalSeasons;
    }

    @Override
    public String toString() {
        return super.toString() + " – Temporadas: " + totalSeasons;
    }
}

