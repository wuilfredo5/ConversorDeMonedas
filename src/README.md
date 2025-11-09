# 💱 Conversor de Monedas en java

![Java](https://img.shields.io/badge/Java-21-orange)
![License](https://img.shields.io/badge/License-MIT-blue)
![Version](https://img.shields.io/badge/Version-1.0-green)

Una aplicación de consola en Java para conversión de monedas en tiempo real, con interfaz intuitiva y historial de conversiones.

## ✨ Características

- 💰 **161 monedas disponibles** de todo el mundo
- 🎯 **Menú interactivo** con selección numérica
- 📊 **Historial** de las últimas 10 conversiones
- ⚡ **Tiempo real** usando API externa
- 🛡️ **Validaciones robustas** y manejo de errores
- 💫 **Interfaz amigable** con emojis y formato mejorado

## 🏗️ Arquitectura

src/
├── controller/ # Lógica de control
│ └── ConversorController.java
├── models/ # Modelos de datos
│ ├── Conversion.java
│ ├── DatosApi.java
│ └── Historial.java
├── service/ # Servicios externos
│ └── ApiService.java
└── view/ # Interfaz de usuario
└── Consola.java



## 🚀 Instalación y Ejecución

### Prerrequisitos
- Java 21 o superior
- Conexión a internet
- API Key de [ExchangeRate-API](https://www.exchangerate-api.com/)

### Pasos para ejecutar
1. Clona el repositorio:
```bash
git clone https://github.com/wuilfredo5/conversor-monedas.git
cd conversor-monedas

### Compila el proyecto

javac -d out -cp "lib/*" src/**/*.java

### Ejecuta la aplicación

java -cp "out:lib/*" controller.ConversorController

### Uso

Ejecuta la aplicación

Selecciona moneda origen del menú (1-14)

Selecciona moneda destino del menú

Ingresa la cantidad a convertir

Visualiza el resultado

Consulta el historial (opción 15)

=== MENÚ PRINCIPAL ===
1. USD → Dólar Estadounidense (Estados Unidos)
2. EUR → Euro (Unión Europea)
...
15. 📊 Ver historial de conversiones

Elige: 1

=== SELECCIONA MONEDA DESTINO ===
... (mismo menú)
Elige: 2

Cantidad: 100

💱 RESULTADO: 100.00 USD → 85.50 EUR


📊### Monedas Soportadas
Código	Moneda	País
USD	Dólar Estadounidense	Estados Unidos
EUR	Euro	Unión Europea
GBP	Libra Esterlina	Reino Unido
JPY	Yen Japonés	Japón
ARS	Peso Argentino	Argentina
VES	Bolívar Soberano	Venezuela
COP	Peso Colombiano	Colombia
BRL	Real Brasileño	Brasil
MXN	Peso Mexicano	México
CLP	Peso Chileno	Chile
PEN	Sol Peruano	Perú
CNY	Yuan Chino	China
KRW	Won Surcoreano	Corea del Sur
Y 148 monedas adicionales mediante código manual


### Tecnologías Utilizadas
Java 21 - Lenguaje de programación

Gson - Manipulación de JSON

HTTP Client - Comunicación con API

ExchangeRate-API - Datos de tasas de cambio


// Ejemplo de endpoint
https://v6.exchangerate-api.com/v6/API_KEY/pair/USD/EUR/100


🧪 Pruebas
La aplicación incluye validaciones para:

✅ Cantidades positivas

✅ Códigos de moneda válidos (3 letras)

✅ Monedas diferentes en origen y destino

✅ Errores de conexión a internet

✅ Respuestas de error de la API

### Mejoras Futuras
Persistencia del historial en archivo

Conversión múltiple simultánea

Gráficos de tendencia de tasas

Modo offline con cache

Interfaz gráfica (GUI)


📄 Licencia
Este proyecto está bajo la Licencia MIT - ver el archivo LICENSE para detalles.

🙏 Agradecimientos
ExchangeRate-API por proveer los datos de conversión

Comunidad de Java por las librerías y herramientas


