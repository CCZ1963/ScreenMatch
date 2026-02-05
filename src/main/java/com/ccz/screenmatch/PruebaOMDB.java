package com.ccz.screenmatch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PruebaOMDB {
    public static void main(String[] args) {
        // 1. Clave API de OMDB
        String apiKey = "aa2f1ec7";

        // 2. Título a buscar
        String titulo = "Dept.+Q";

        // 3. Construir la URL
        String url = "http://www.omdbapi.com/?t=" + titulo + "&apikey=" + apiKey;

        // 4. Crear cliente HTTP
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            // 5. Hacer la llamada
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 6. Mostrar el JSON crudo (para depuración)
            System.out.println("JSON recibido:");
            System.out.println(response.body());
            System.out.println("\n---\n");

            // 7. Convertir con Gson
            // Gson gson = new Gson(); Forma antigua
            Gson gson = new GsonBuilder() // Nueva forma
                    .registerTypeAdapter(Titulo.class, new TituloDeserializador())
                    .create();

            Titulo datos = gson.fromJson(response.body(), Titulo.class);

            // 8. Mostrar resultado
            System.out.println("Título: " + datos.title);
            System.out.println("Año: " + datos.year);
            System.out.println("IMDb: " + datos.imdbRating);
            System.out.println("Duración: " + datos.runtime);
            System.out.println("Director: " + datos.director);

            // 9. Detectar dinámicamente el tipo: Pelicula o Serie
            if (datos instanceof Pelicula pelicula) {
                System.out.println("Es una PELÍCULA");
                System.out.println("Box Office: " + pelicula.getBoxOffice());
            } else if (datos instanceof Serie serie) {
                System.out.println("Es una SERIE");
                System.out.println("Temporadas: " + serie.getTotalSeasons());
            }

            // 10. Recorremos la lista de ratings
            for (Rating r : datos.getRatings()) {
                System.out.println("Crítica: " + r.toString());
                // Imprimirá: "Crítica: Internet Movie Database: 8.8/10"
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}