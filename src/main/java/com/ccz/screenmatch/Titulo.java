package com.ccz.screenmatch;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public abstract class Titulo {

    @SerializedName("Title")
    protected String title;

    @SerializedName("Year")
    protected String year;

    @SerializedName("Rated")
    protected String rated;

    @SerializedName("Released")
    protected String released;

    @SerializedName("Runtime")
    protected String runtime;

    @SerializedName("Genre")
    protected String genre;

    @SerializedName("Director")
    protected String director; // "Christopher Nolan" o "A, B"

    @SerializedName("Writer")
    protected String writer; // "Matt Duffer, Ross Duffer",

    @SerializedName("Actors")
    protected String actors; // "Matt Duffer, Ross Duffer",

    @SerializedName("Plot")
    protected String plot;

    @SerializedName("Language")
    protected String language; // "English, French"

    @SerializedName("Country")
    protected String country; // "English, French"

    @SerializedName("imdbRating") // Asi en el JSON pero mejor ser explicitos
    protected String imdbRating;

    @SerializedName("Type")
    protected String type;

    @SerializedName("Ratings")
    protected List<Rating> ratings;

    // Getters (sin setters, ya que vienen de la API y no se modifican)
    public String getTitle() { return title; }
    public String getYear() { return year; }
    public String getRated() { return rated; }
    public String getReleased() { return released; }
    public String getRuntime() { return runtime; }
    public String getGenre() { return genre; }
    public String getDirector() { return director; }
    public String getWriter() { return writer; }
    public String getActors() { return actors; }
    public String getPlot() { return plot; }
    public String getLanguage() { return language; }
    public String getCountry() { return country; }
    public String getImdbRating() { return imdbRating; }
    public String getType() { return type; }
    public List<Rating> getRatings() {
        return ratings != null ? ratings : new ArrayList<>();
        // Usamos new ArrayList<>() en el getter para evitar NullPointerException si no hay ratings.
    }

    @Override
    // Imprime los resultados de la búsqueda
    public String toString() {
        return title + " (" + year + ") – IMDb: " + imdbRating;
    }
}

