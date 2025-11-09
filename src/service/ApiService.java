package service;

import com.google.gson.Gson;
import models.DatosApi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ApiService {
    private static final String API_KEY = "e8721399269536d7565890fc";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private final HttpClient client;
    private final Gson gson;

    public ApiService() {
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.gson = new Gson();
    }

    public DatosApi realizarConversion(String monedaOrigen, String monedaDestino, double cantidad)
            throws IOException, InterruptedException, ApiException {

        String url = construirURL(monedaOrigen, monedaDestino, cantidad);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(15))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Verificar código de respuesta HTTP
        if (response.statusCode() != 200) {
            throw new ApiException("Error HTTP " + response.statusCode() + ": " + response.body());
        }

        String json = response.body();

        // Verificar si la API devolvió un error en el JSON
        if (json.contains("\"error-type\"")) {
            manejarErrorAPI(json);
        }

        return gson.fromJson(json, DatosApi.class);
    }

    private String construirURL(String monedaOrigen, String monedaDestino, double cantidad) {
        return BASE_URL + API_KEY + "/pair/" + monedaOrigen + "/" + monedaDestino + "/" + cantidad;
    }

    private void manejarErrorAPI(String json) throws ApiException {
        if (json.contains("unsupported-code")) {
            throw new ApiException("Código de moneda no soportado por la API.");
        } else if (json.contains("malformed-request")) {
            throw new ApiException("Solicitud mal formada a la API.");
        } else if (json.contains("invalid-key")) {
            throw new ApiException("Clave de API inválida.");
        } else if (json.contains("quota-reached")) {
            throw new ApiException("Límite de consultas alcanzado. Intenta más tarde.");
        } else if (json.contains("inactive-account")) {
            throw new ApiException("Cuenta de API inactiva.");
        } else {
            throw new ApiException("Error desconocido de la API.");
        }
    }

    // Clase de excepción personalizada para errores de API
    public static class ApiException extends Exception {
        public ApiException(String message) {
            super(message);
        }
    }
}
