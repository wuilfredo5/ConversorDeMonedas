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
            ejecutarAplicacion();
        } catch (Exception e) {
            consola.mostrarError("Error inesperado: " + e.getMessage());
        } finally {
            consola.cerrar();
        }
    }

    private void ejecutarAplicacion() throws IOException, InterruptedException {
        consola.mostrarBienvenida();

        boolean continuar = true;
        while (continuar) {
            int opcion = consola.mostrarMenuPrincipal();

            if (opcion == 0) {
                consola.mostrarMensaje("¡Gracias por usar el conversor! 👋");
                break;
            }

            if (opcion < 0 || opcion > 14) {
                consola.mostrarError("Opción no válida. Por favor, elige entre 0 y 14.");
                continue;
            }

            procesarConversion(opcion);
            continuar = consola.preguntarContinuar();
        }
    }

    private void procesarConversion(int opcionMonedaOrigen) throws IOException, InterruptedException {
        // Seleccionar moneda origen
        String monedaOrigen = consola.obtenerMonedaPorOpcion(opcionMonedaOrigen, "origen");
        if (monedaOrigen == null) {
            consola.mostrarError("Opción de moneda origen no válida");
            return;
        }

        // Mostrar menú para moneda destino
        consola.mostrarMensaje("\n=== SELECCIONA MONEDA DESTINO ===");
        int opcionMonedaDestino = consola.mostrarMenuPrincipal();

        if (opcionMonedaDestino == 0) {
            return;
        }

        String monedaDestino = consola.obtenerMonedaPorOpcion(opcionMonedaDestino, "destino");
        if (monedaDestino == null) {
            consola.mostrarError("Opción de moneda destino no válida");
            return;
        }

        // Verificar que no sean la misma moneda
        if (monedaOrigen.equals(monedaDestino)) {
            consola.mostrarError("Las monedas de origen y destino no pueden ser iguales");
            return;
        }

        // Obtener cantidad
        double cantidad = consola.leerCantidad();

        // Realizar conversión
        Conversion resultado = convertirMoneda(monedaOrigen, monedaDestino, cantidad);
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