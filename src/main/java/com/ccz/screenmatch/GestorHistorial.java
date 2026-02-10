package com.ccz.screenmatch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorHistorial {

    private static final String ARCHIVO_HISTORIAL = "historial.json";
    private final List<Titulo> historial;
    private final Gson gson;

    public GestorHistorial() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Titulo.class, new TituloDeserializador())
                .setPrettyPrinting()
                .create();
        this.historial = cargarHistorial();
    }

    private List<Titulo> cargarHistorial() {
        try {
            if (Files.exists(Paths.get(ARCHIVO_HISTORIAL))) {
                String json = Files.readString(Paths.get(ARCHIVO_HISTORIAL));
                Type tipoLista = new TypeToken<List<JsonObject>>(){}.getType(); // ← cambia a JsonObject

                List<JsonObject> objetosJson = gson.fromJson(json, tipoLista);
                List<Titulo> validos = new ArrayList<>();

                if (objetosJson != null) {
                    for (JsonObject obj : objetosJson) {
                        try {
                            // Intentar deserializar cada objeto individualmente
                            Titulo titulo = gson.fromJson(obj, Titulo.class);
                            if (titulo != null) {
                                validos.add(titulo);
                            }
                        } catch (Exception e) {
                            System.err.println("⚠️ Entrada inválida en historial ignorada.");
                            // Opcional: imprimir el JSON problemático
                            // System.out.println("JSON inválido: " + obj);
                        }
                    }
                }
                return validos;
            }
        } catch (IOException e) {
            System.err.println("⚠️ No se pudo cargar el historial: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public void guardarHistorial() {
        try {
            String json = gson.toJson(historial);
            Files.writeString(Paths.get(ARCHIVO_HISTORIAL), json);
            System.out.println("✅ Historial guardado.");
        } catch (IOException e) {
            System.err.println("⚠️ No se pudo guardar el historial: " + e.getMessage());
        }
    }

    public void agregarBusqueda(Titulo titulo) {
        if (titulo != null) {
            historial.add(titulo);
        }
    }

    public List<Titulo> obtenerUltimas(int cantidad) {
        int inicio = Math.max(0, historial.size() - cantidad);
        return new ArrayList<>(historial.subList(inicio, historial.size()));
    }

    public boolean estaVacio() {
        return historial.isEmpty();
    }
}

