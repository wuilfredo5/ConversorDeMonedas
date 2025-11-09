package controller;

import com.google.gson.Gson;
import models.Conversion;
import models.DatosApi;
import view.Consola;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorController {

    private Consola consola;
    private static final String API_KEY = "e8721399269536d7565890fc";

    public ConversorController() {
        this.consola = new Consola();
    }

    public void iniciar() {
        try {
            ejecutarConversor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            consola.cerrar();
        }
    }

    private void ejecutarConversor() throws IOException, InterruptedException {
        consola.mostrarBienvenida();

        // Obtener datos del usuario
        String monedaOrigen = consola.leerMonedaOrigen();
        String monedaDestino = consola.leerMonedaDestino();
        double cantidad = consola.leerCantidad();

        // Realizar la conversión
        Conversion resultado = convertirMoneda(monedaOrigen, monedaDestino, cantidad);

        // Mostrar resultado
        consola.mostrarResultado(resultado.toString());
    }

    private Conversion convertirMoneda(String monedaOrigen, String monedaDestino, double cantidad)
            throws IOException, InterruptedException {

        String url = construirURL(monedaOrigen, monedaDestino, cantidad);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        Gson gson = new Gson();
        DatosApi datosApi = gson.fromJson(json, DatosApi.class);

        return new Conversion(datosApi, cantidad);
    }

    private String construirURL(String monedaOrigen, String monedaDestino, double cantidad) {
        return "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" +
                monedaOrigen + "/" + monedaDestino + "/" + cantidad;
    }

    public static void main(String[] args) {
        new ConversorController().iniciar();
    }
}
