package models;

public class Conversion {
    private String monedaDeOrigen;
    private String monedaDeDestino;
    private double cantidadConvertida;
    private double cantidadConvertir;

    public Conversion(DatosApi importeApi, double cantidadConvertir) {
        this.monedaDeOrigen = importeApi.base_code();
        this.monedaDeDestino = importeApi.target_code();
        this.cantidadConvertida = importeApi.conversion_result();
        this.cantidadConvertir = cantidadConvertir; // Esto debe recibir el valor del usuario
    }

    @Override
    public String toString() {
        return "tus " + cantidadConvertir + " " + monedaDeOrigen + " equivalen a " + cantidadConvertida + " " + monedaDeDestino;
    }
}