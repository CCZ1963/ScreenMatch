package com.ccz.screenmatch;

import com.google.gson.annotations.SerializedName;

public class Pelicula extends Titulo {

    @SerializedName("BoxOffice")
    private String boxOffice; // Ej: "$292,587,330"

    public String getBoxOffice() {
        return boxOffice;
    }

    @Override
    public String toString() {
        return super.toString() + " – Box Office: " + boxOffice;
    }
}

