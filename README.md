# 🎬 Screen Match – Consulta de Películas y Series con OMDB API

Una aplicación de consola en Java que permite buscar y consultar información detallada sobre películas y series utilizando la API de [OMDb (Open Movie Database)](https://www.omdbapi.com/).

Desarrollada como parte del desafío **Screen Match**, con enfoque en:
- Consumo de APIs REST
- Modelado orientado a objetos
- Deserialización inteligente con Gson
- Persistencia de historial en JSON
- Experiencia de usuario robusta

---

## ✨ Funcionalidades

- 🔍 **Búsqueda por título**: obtén información completa de películas o series.
- 👥 **Búsqueda por actor o término**: lista resultados relacionados.
- 🆔 **Búsqueda por IMDb ID**: acceso directo a cualquier título.
- 📜 **Historial persistente**: guarda y recupera tus búsquedas en `historial.json`.
- ⚠️ **Manejo de errores amigable**: mensajes claros ante entradas inválidas o títulos no encontrados.
- 🔄 **Validación por tipo**: si buscas una película, solo se muestran películas (y viceversa).

---

## 🛠️ Tecnologías utilizadas

- **Java 17+**
- **Gson** – para serialización/deserialización JSON
- **HttpClient** – para consumo de API REST
- **Maven** – gestión de dependencias
- **OMDb API** – fuente de datos cinematográficos

---

## 📦 Estructura del proyecto

```textline
screenmatch/
├── src/main/java/com/ccz/screenmatch/
│ ├── Principal.java # Menú interactivo
│ ├── ConsumoAPI.java # Lógica de consumo de OMDB
│ ├── GestorHistorial.java # Persistencia en JSON
│ ├── Titulo.java # Clase base abstracta
│ ├── Pelicula.java # Subclase: película
│ ├── Serie.java # Subclase: serie
│ ├── Rating.java # Críticas (IMDb, Rotten Tomatoes, etc.)
│ ├── TituloResumen.java # Resultados de búsqueda múltiple
│ └── TituloDeserializador.java # Deserialización condicional
└── pom.xml # Dependencias (Gson)
```

---

## 🚀 Cómo ejecutar

1. Obtén una clave de API gratuita** en [OMDb API](https://www.omdbapi.com/apikey.aspx).

2. Clona el repositorio:
```bash
git clone https://github.com/tu-usuario/screenmatch.git
cd screenmatch
```
   
   3. Reemplaza tu API key en ConsumoAPI.java:
```java
private static final String API_KEY = "TU_API_KEY_AQUI";
```

4. Compila y ejecuta con Maven:
```bash
mvn compile exec:java -Dexec.mainClass="com.ccz.screenmatch.Principal"
```

## 📸 Vista previa

```textline
=== 🎬 SCREEN MATCH ===

1. Buscar Película por título
2. Buscar Serie por título
3. Buscar por Actor o Término
4. Buscar por imdbID
5. Ver historial (últimas 5)
6. Salir
Elija una opción: 1

Ingrese el título de la Película: Inception

✅ Resultado:
Título: Inception
Año: 2010
Género: Action, Adventure, Sci-Fi
IMDb: 8.8
Tipo: 🎥 Película
Box Office: $292,587,330

Críticas:
  • Internet Movie Database: 8.8/10
  • Rotten Tomatoes: 87%
  • Metacritic: 74/100
```

