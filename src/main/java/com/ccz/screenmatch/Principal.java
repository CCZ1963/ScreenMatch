package com.ccz.screenmatch;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsumoAPI consumoApi = new ConsumoAPI();

        System.out.println("=== 🎬 SCREEN MATCH ===");

        while (true) {
            System.out.println("\n1. Buscar Película por título");
            System.out.println("2. Buscar Serie por título");
            System.out.println("3. Buscar por Actor o Término");
            System.out.println("4. Buscar por imdbID");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");

            int opcion = lectura.nextInt();
            lectura.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título de la Película: ");
                    String titulo = lectura.nextLine();
                    Titulo resultadoPelicula = consumoApi.buscarTitulo(titulo); // A ConsumoAPI.java

                    if (resultadoPelicula != null) {
                        mostrarInformacion(resultadoPelicula);
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el título de la Serie: ");
                    String serie = lectura.nextLine();
                    Titulo resultadoSerie = consumoApi.buscarTitulo(serie); // A ConsumoAPI.java

                    if (resultadoSerie != null) {
                        mostrarInformacion(resultadoSerie);
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el nombre del actor o término: ");
                    String termino = lectura.nextLine();
                    ResultadosBusqueda resultados = consumoApi.buscarPorTermino(termino);

                    if (resultados != null && !resultados.getResultados().isEmpty()) {
                        System.out.println("\n✅ Encontrados " + resultados.getTotalResultados() + " resultados:");
                        for (TituloResumen item : resultados.getResultados()) {
                            System.out.println("  • " + item);
                        }
                        boolean salirMenuInterno = false; // Variable de control

                        System.out.println("\n=== 🎬 ¿Desea ver detalles de alguna? ===");
                        while (!salirMenuInterno) {
                            System.out.println("\n1. Buscar imdbID");
                            System.out.println("2. Volver al Menú Principal");
                            System.out.print("Elija una opción: ");

                            int opcion1 = lectura.nextInt();
                            lectura.nextLine(); // Consumir el salto de línea

                            switch (opcion1) {
                                case 1:
                                    buscarPorImdbID(lectura, consumoApi);
                                    break;

                                case 2:
                                    salirMenuInterno = true; // Cambiamos a true para romper el while
                                    break;

                                default:
                                    System.out.println("Opción no válida. Intente de nuevo.");
                            }
                        }
                    }
                    break;

                case 4:
                    buscarPorImdbID(lectura, consumoApi);
                    break;

                case 5:
                    System.out.println("¡Gracias por usar Screen Match! 👋");
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void buscarPorImdbID(Scanner lectura, ConsumoAPI consumoApi) {
        System.out.print("Ingrese el imdbID a buscar: ");
        String imdbID = lectura.nextLine();
        Titulo resultadoImdbID = consumoApi.buscarImdbID(imdbID);

        if (resultadoImdbID != null) {
            mostrarInformacion(resultadoImdbID);
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

