package view;

import java.util.Scanner;

public class Consola {
    private Scanner scanner;

    public Consola() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarBienvenida() {
        String bienvenida = """
                ************ Bienvenido al sistema de gestión de conversion de monedas ************
                ************ Admitimos 161 monedas de circulacion mundial **************
                
                Debe ingresar la moneda de origen y la moneda destino 
                Principales monedas soportadas: USD (eeuu), EUR(europa), GBP(reino unido), JPY(japon), ARS(argentina), VES(venezuela), COP(colombia)
                """;
        System.out.println(bienvenida);
    }

    public String leerMonedaOrigen() {
        System.out.println("Escribe la moneda de origen:");
        return scanner.nextLine().toUpperCase();
    }

    public String leerMonedaDestino() {
        System.out.println("Escribe la moneda destino:");
        return scanner.nextLine().toUpperCase();
    }

    public double leerCantidad() {
        System.out.println("Escribe la cantidad a convertir:");
        double cantidad = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer
        return cantidad;
    }

    public void mostrarResultado(String resultado) {
        System.out.println(resultado);
    }

    public void cerrar() {
        scanner.close();
    }
}