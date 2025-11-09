package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Conversion {
    private String monedaDeOrigen;
    private String monedaDeDestino;
    private double cantidadConvertida;
    private double cantidadConvertir;
    private LocalDateTime fechaHora;

    public Conversion(DatosApi importeApi, double cantidadConvertir) {
        this.monedaDeOrigen = importeApi.base_code();
        this.monedaDeDestino = importeApi.target_code();
        this.cantidadConvertida = importeApi.conversion_result();
        this.cantidadConvertir = cantidadConvertir;
        this.fechaHora = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("%,.2f %s → %,.2f %s",
                cantidadConvertir, monedaDeOrigen,
                cantidadConvertida, monedaDeDestino);
    }

    // Para el historial detallado (opcional)
    public String toStringDetallado() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("%,.2f %s → %,.2f %s (%s)",
                cantidadConvertir, monedaDeOrigen,
                cantidadConvertida, monedaDeDestino,
                fechaHora.format(formatter));
    }
}