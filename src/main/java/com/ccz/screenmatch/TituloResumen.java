package com.ccz.screenmatch;

import com.google.gson.annotations.SerializedName;

public class TituloResumen {

    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("imdbID")
    private String imdbId;

    @SerializedName("Type")
    private String type;

    @SerializedName("Poster")
    private String poster;

    // Getters
    public String getTitle() { return title; }
    public String getYear() { return year; }
    public String getImdbId() { return imdbId; }
    public String getType() { return type; }
    public String getPoster() { return poster; }

    @Override
    public String toString() {
        // Imprime los resultados de la búsqueda
        return title + " (" + year + ") - [" + type + "] → imdbID: " + imdbId;
    }
}

