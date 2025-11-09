package models;

public record DatosApi(String base_code, String target_code, double conversion_result) {


    public int getCantidadConvertir() {
        return 0;
    }
}
