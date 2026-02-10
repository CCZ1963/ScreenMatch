package com.ccz.screenmatch;

import com.google.gson.*;
import java.lang.reflect.Type;

public class TituloDeserializador implements JsonDeserializer<Titulo> {

    @Override
    public Titulo deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {

        // Convertir el elemento JSON a un objeto (porque esperamos un objeto, no un array)
        JsonObject jsonObject = json.getAsJsonObject();

        // Verificar si la respuesta es un error
        if (jsonObject.has("Response") && "False".equals(jsonObject.get("Response").getAsString())) {
            throw new JsonParseException("OMDB API Error: " + jsonObject.get("Error").getAsString());
        }

        //  Obtener el valor del campo "Type"
        JsonElement typeElement = jsonObject.get("Type");
        if (typeElement == null || typeElement.isJsonNull()) {
            throw new JsonParseException("Campo 'Type' no encontrado en la respuesta de OMDB");
        }

        String tipo = typeElement.getAsString();

        // Decidir qué clase usar según el valor de "Type"
        if ("movie".equals(tipo)) {
            // Le pedimos al contexto que convierta el JSON completo a Pelicula
            return context.deserialize(json, Pelicula.class);
        } else if ("series".equals(tipo)) {
            // Lo mismo, pero para Serie
            return context.deserialize(json, Serie.class);
        } else {
            // Lo mismo, pero para Serie
            throw new JsonParseException("Tipo desconocido: " + tipo);
        }
    }
}

