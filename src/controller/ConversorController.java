package controller;

import models.Conversion;
import models.DatosApi;
import service.ApiService;
import view.Consola;

public class ConversorController {

    private Consola consola;
    private ApiService apiService;

    public ConversorController() {
        this.consola = new Consola();
        this.apiService = new ApiService();
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

    private void ejecutarAplicacion() {
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

    private void procesarConversion(int opcionMonedaOrigen) {
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
            consola.mostrarError("❌ Las monedas de origen y destino no pueden ser iguales. Selecciona monedas diferentes.");
            return;
        }

        // Obtener cantidad con validación
        double cantidad = consola.leerCantidad();

        // Realizar conversión con manejo de errores
        try {
            DatosApi datosApi = apiService.realizarConversion(monedaOrigen, monedaDestino, cantidad);
            Conversion resultado = new Conversion(datosApi, cantidad);
            consola.mostrarResultado(resultado.toString());

        } catch (ApiService.ApiException e) {
            consola.mostrarError("Error en la conversión: " + e.getMessage());
        } catch (Exception e) {
            consola.mostrarError("Error de conexión: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ConversorController().iniciar();
    }
}