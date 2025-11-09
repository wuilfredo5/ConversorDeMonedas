package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Historial {
    private List<Conversion> conversiones;
    private static final int MAX_HISTORIAL = 10;

    public Historial() {
        this.conversiones = new ArrayList<>();
    }

    public void agregarConversion(Conversion conversion) {
        // Si llegamos al máximo, eliminar la más antigua
        if (conversiones.size() >= MAX_HISTORIAL) {
            conversiones.remove(0);
        }
        conversiones.add(conversion);
    }

    public void mostrarHistorial() {
        if (conversiones.isEmpty()) {
            System.out.println("📝 El historial está vacío.");
            return;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("📊 HISTORIAL DE ÚLTIMAS CONVERSIONES");
        System.out.println("=".repeat(70));

        for (int i = 0; i < conversiones.size(); i++) {
            Conversion conversion = conversiones.get(i);
            System.out.printf("%d. %s\n", i + 1, conversion.toString());
        }
        System.out.println("=".repeat(70));
    }

    public boolean estaVacio() {
        return conversiones.isEmpty();
    }

    public int cantidadConversiones() {
        return conversiones.size();
    }
}
