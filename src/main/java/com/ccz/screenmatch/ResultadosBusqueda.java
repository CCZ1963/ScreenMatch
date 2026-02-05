package com.ccz.screenmatch;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResultadosBusqueda {

    @SerializedName("Search")
    private List<TituloResumen> resultados;

    @SerializedName("totalResults")
    private String totalResultados;

    // Getter
    public List<TituloResumen> getResultados() {
        return resultados != null ? resultados : java.util.Collections.emptyList();
    }

    public String getTotalResultados() {
        return totalResultados;
    }
}

