package view;

import java.util.Scanner;

public class Consola {
    private Scanner scanner;

    public Consola() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarBienvenida() {
        String bienvenida = """
            ⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐
            🎯          BIENVENIDO AL CONVERSOR DE MONEDAS PLUS          🎯
            💰          161 monedas disponibles - Conversión instantánea 💰
            ⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐
            """;
        System.out.println(bienvenida);
    }
    public int mostrarMenuPrincipal() {
        String menu = """
            \n=== MENÚ PRINCIPAL ===
            1.  USD → Dólar Estadounidense (Estados Unidos)
            2.  EUR → Euro (Unión Europea)
            3.  GBP → Libra Esterlina (Reino Unido)
            4.  JPY → Yen Japonés (Japón)
            5.  ARS → Peso Argentino (Argentina)
            6.  VES → Bolívar Soberano (Venezuela)
            7.  COP → Peso Colombiano (Colombia)
            8.  BRL → Real Brasileño (Brasil)
            9.  MXN → Peso Mexicano (México)
            10. CLP → Peso Chileno (Chile)
            11. PEN → Sol Peruano (Perú)
            12. CNY → Yuan Chino (China)
            13. KRW → Won Surcoreano (Corea del Sur)
            14. Otra moneda (ingresar manualmente)
            15. 📊 Ver historial de conversiones
            0.  Salir
            
            Elige una opción (0-15):""";

        System.out.println(menu);

        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            return opcion;
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar buffer en caso de error
            return -1; // Indicar opción inválida
        }
    }

    public String obtenerMonedaPorOpcion(int opcion, String tipo) {
        return switch (opcion) {
            case 1 -> "USD";
            case 2 -> "EUR";
            case 3 -> "GBP";
            case 4 -> "JPY";
            case 5 -> "ARS";
            case 6 -> "VES";
            case 7 -> "COP";
            case 8 -> "BRL";
            case 9 -> "MXN";
            case 10 -> "CLP";
            case 11 -> "PEN";
            case 12 -> "CNY";
            case 13 -> "KRW";
            case 14 -> leerMonedaManual(tipo);
            default -> null;
        };
    }

    private String leerMonedaManual(String tipo) {
        while (true) {
            System.out.println("Ingresa el código de 3 letras para la moneda " + tipo + " (ej: USD, EUR):");
            String codigo = scanner.nextLine().trim().toUpperCase();

            if (codigo.length() == 3 && codigo.matches("[A-Z]{3}")) {
                return codigo;
            } else {
                System.out.println("❌ Código inválido. Debe ser exactamente 3 letras (ej: USD, EUR). Intenta nuevamente.");
            }
        }
    }

    public double leerCantidad() {
        while (true) {
            try {
                System.out.println("Escribe la cantidad a convertir:");
                double cantidad = scanner.nextDouble();
                scanner.nextLine(); // Limpiar buffer

                if (cantidad > 0) {
                    return cantidad;
                } else {
                    System.out.println("❌ La cantidad debe ser mayor a 0. Intenta nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("❌ Entrada inválida. Debe ser un número (ej: 100, 50.25). Intenta nuevamente.");
                scanner.nextLine(); // Limpiar buffer en caso de error
            }
        }
    }

    public void mostrarResultado(String resultado) {
        System.out.println("\n" + "⭐".repeat(60));
        System.out.println("💱 CONVERSIÓN EXITOSA!");
        System.out.println("➡️  " + resultado);
        System.out.println("⭐".repeat(60) + "\n");
    }

    public void mostrarError(String mensaje) {
        System.out.println("❌ ERROR: " + mensaje);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public boolean preguntarContinuar() {
        System.out.println("¿Deseas hacer otra conversión? (s/n):");
        String respuesta = scanner.nextLine().toLowerCase();
        return respuesta.equals("s") || respuesta.equals("si");
    }

    public void cerrar() {
        scanner.close();
        System.out.println("¡Hasta pronto! 👋");
    }
}