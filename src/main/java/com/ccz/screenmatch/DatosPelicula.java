package com.ccz.screenmatch;

import com.google.gson.annotations.SerializedName;

public class DatosPelicula {
    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("imdbRating")
    public String imdbRating;

    @SerializedName("BoxOffice")
    public String boxOffice;

    @SerializedName("Type")
    public String type;
}
