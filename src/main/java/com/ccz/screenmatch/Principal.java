package com.ccz.screenmatch;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsumoAPI consumoApi = new ConsumoAPI();

        System.out.println("=== 🎬 SCREEN MATCH ===");

        while (true) {
            System.out.println("\n1. Buscar película o serie por título");
            System.out.println("2. Salir");
            System.out.print("Elija una opción: ");

            int opcion = lectura.nextInt();
            lectura.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título: ");
                    String titulo = lectura.nextLine();
                    Titulo resultado = consumoApi.obtenerDatos(titulo); // A ConsumoAPI.java

                    if (resultado != null) {
                        mostrarInformacion(resultado);
                    }
                    break;

                case 2:
                    System.out.println("¡Gracias por usar Screen Match! 👋");
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void mostrarInformacion(Titulo titulo) {
        System.out.println("\n✅ Resultado:");
        System.out.println("Título: " + titulo.getTitle());
        System.out.println("Año: " + titulo.getYear());
        System.out.println("Género: " + titulo.getGenre());
        System.out.println("IMDb: " + titulo.getImdbRating());

        if (titulo instanceof Pelicula pelicula) {
            System.out.println("Tipo: 🎥 Película");
            System.out.println("Box Office: " + pelicula.getBoxOffice());
        } else if (titulo instanceof Serie serie) {
            System.out.println("Tipo: 📺 Serie");
            System.out.println("Temporadas: " + serie.getTotalSeasons());
        }

        System.out.println("\nCríticas:");
        for (Rating r : titulo.getRatings()) {
            System.out.println("  • " + r);
        }
    }
}

