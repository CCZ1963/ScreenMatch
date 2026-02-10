package com.ccz.screenmatch;

import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsumoAPI consumoApi = new ConsumoAPI();
        GestorHistorial gestorHistorial = new GestorHistorial();

        System.out.println("=== 🎬 SCREEN MATCH ===");

        while (true) {
            System.out.println("\n1. Buscar Película por título");
            System.out.println("2. Buscar Serie por título");
            System.out.println("3. Buscar por Actor o Término");
            System.out.println("4. Buscar por imdbID");
            System.out.println("5. Ver historial (últimas 5)");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");

            /* Anulada porque al ingresar letras daba error
            Esperaba números, no letras, se cambio por leerOpcion{[}...}
            int opcion = lectura.nextInt();
            lectura.nextLine(); // Consumir el salto de línea
            */
            int opcion = leerOpcion(lectura);

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título de la Película: ");
                    String titulo = lectura.nextLine();
                    Titulo resultadoPelicula = consumoApi.buscarTitulo(titulo); // A ConsumoAPI.java

                    if (resultadoPelicula != null) {
                        if (resultadoPelicula instanceof Pelicula) {
                            mostrarInformacion(resultadoPelicula);
                            gestorHistorial.agregarBusqueda(resultadoPelicula);
                        } else {
                            System.out.println("❌ El resultado es una SERIE, no una película.");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el título de la Serie: ");
                    String serie = lectura.nextLine();
                    Titulo resultadoSerie = consumoApi.buscarTitulo(serie); // A ConsumoAPI.java

                    if (resultadoSerie != null) {
                        if (resultadoSerie instanceof Serie) {
                            mostrarInformacion(resultadoSerie);
                            gestorHistorial.agregarBusqueda(resultadoSerie);
                        } else {
                            System.out.println("❌ El resultado es una PELÍCULA, no una serie.");
                        }
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
                            System.out.println("\n1. Buscar por imdbID");
                            System.out.println("2. Volver al Menú Principal");
                            System.out.print("Elija una opción: ");

                            int opcion1 = leerOpcion(lectura);

                            switch (opcion1) {
                                case 1:
                                    Titulo resultadoImdb = buscarPorImdbID(lectura, consumoApi);
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
                    Titulo resultadoImdbID = buscarPorImdbID(lectura, consumoApi);
                    gestorHistorial.agregarBusqueda(resultadoImdbID); // ← añadir al historial
                    break;

                case 5:
                    if (gestorHistorial.estaVacio()) {
                        System.out.println("📭 El historial está vacío.");
                    } else {
                        System.out.println("\n=== 📜 ÚLTIMAS BÚSQUEDAS ===");
                        List<Titulo> ultimas = gestorHistorial.obtenerUltimas(5);
                        for (int i = ultimas.size() - 1; i >= 0; i--) { // más reciente primero
                            Titulo t = ultimas.get(i);
                            System.out.println((ultimas.size() - i) + ". " + t.getTitle() + " (" + t.getYear() + ")");
                        }
                    }
                    break;

                case 6:
                    gestorHistorial.guardarHistorial();
                    System.out.println("¡Gracias por usar Screen Match! 👋");
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    //En Java, todo metodo debe declarar su tipo de retorno (incluso si es void).
    //Como tu método devuelve un Titulo, debes escribirlo explícitamente.
    private static Titulo buscarPorImdbID(Scanner lectura, ConsumoAPI consumoApi) {
        System.out.print("Ingrese el imdbID a buscar: ");
        String imdbID = lectura.nextLine();
        Titulo resultadoImdbID = consumoApi.buscarImdbID(imdbID);

        if (resultadoImdbID != null) {
            mostrarInformacion(resultadoImdbID);
        }

        return resultadoImdbID;
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

    // Validar la entrada por teclado
    private static int leerOpcion(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("❌ Entrada inválida. Por favor, ingrese un número: ");
            }
        }
    }
}

