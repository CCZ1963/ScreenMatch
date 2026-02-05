package com.ccz.screenmatch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

    private static final String API_KEY = "aa2f1ec7"; // 1. Clave API
    private static final String URL_BASE = "http://www.omdbapi.com/?"; // Enlace principal

    private final Gson gson;

    public ConsumoAPI() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Titulo.class, new TituloDeserializador())
                .create();
    }

    public Titulo buscarTitulo(String titulo){
        try{
            // Construimos la URL
            String url = URL_BASE + "t=" + titulo.replace(" ", "+") + "&apikey=" + API_KEY;
            // Creamos la URL
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Realiza la llamada
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar si la API devolvió un error
            if (response.body().contains("\"Response\":\"False\"")) {
                System.out.println("❌ No se encontró la película o serie: " + titulo);
                return null;
            }

            // Retorna el Gson
            return gson.fromJson(response.body(), Titulo.class);

        } catch (IOException | InterruptedException e) {
            System.err.println("⚠️ Error de conexión: " + e.getMessage());
            return null;
        }
    }

    public ResultadosBusqueda buscarPorTermino(String termino) {
        try {
            String url = URL_BASE + "s=" + termino.replace(" ", "+") + "&apikey=" + API_KEY;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body().contains("\"Response\":\"False\"")) {
                System.out.println("❌ No se encontraron resultados para: " + termino);
                return null;
            }

            return gson.fromJson(response.body(), ResultadosBusqueda.class);

        } catch (IOException | InterruptedException e) {
            System.err.println("⚠️ Error de conexión: " + e.getMessage());
            return null;
        }
    }

    public Titulo buscarImdbID(String imdbID) {
        try {
            String url = URL_BASE + "i=" + imdbID + "&apikey=" + API_KEY;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body().contains("\"Response\":\"False\"")) {
                System.out.println("❌ No se encontraron resultados para: " + imdbID);
                return null;
            }

            return gson.fromJson(response.body(), Titulo.class);

        } catch (IOException | InterruptedException e) {
            System.err.println("⚠️ Error de conexión: " + e.getMessage());
            return null;
        }
    }
    /*
    public Titulo obtenerDatos(String titulo) {
        try {
            String url;
            if (entero == 1) {
                // Construimos la URL
                url = URL_BASE + "t=" + titulo.replace(" ", "+") + "&apikey=" + API_KEY;
            } else {
                // Construimos la URL
                url = URL_BASE + "s=" + titulo.replace(" ", "+") + "&apikey=" + API_KEY;
            }
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Realiza la llamada
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar si la API devolvió un error
            if (response.body().contains("\"Response\":\"False\"")) {
                System.out.println("❌ No se encontró la película o serie: " + titulo);
                return null;
            }

            // Retorna el Gson
            return gson.fromJson(response.body(), Titulo.class);

        } catch (IOException | InterruptedException e) {
            System.err.println("⚠️ Error de conexión: " + e.getMessage());
            return null;
        }
    }
    */
}

