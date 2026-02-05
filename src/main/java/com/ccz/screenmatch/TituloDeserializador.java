package com.ccz.screenmatch;

import com.google.gson.*;
import java.lang.reflect.Type;

public class TituloDeserializador implements JsonDeserializer<Titulo> {

    @Override
    public Titulo deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {

        // 1. Convertir el elemento JSON a un objeto (porque esperamos un objeto, no un array)
        JsonObject jsonObject = json.getAsJsonObject();

        // 2. Obtener el valor del campo "Type"
        String tipo = jsonObject.get("Type").getAsString();

        // 3. Decidir qué clase usar según el valor de "Type"
        if ("movie".equals(tipo)) {
            // Le pedimos al contexto que convierta el JSON completo a Pelicula
            return context.deserialize(json, Pelicula.class);
        } else if ("series".equals(tipo)) {
            // Lo mismo, pero para Serie
            return context.deserialize(json, Serie.class);
        } else {
            // Opcional: manejar otros tipos (como "episode") o lanzar error
            throw new JsonParseException("Tipo desconocido: " + tipo);
        }
    }
}
