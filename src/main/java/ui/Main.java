package ui;

import data.GestorDatos;
import model.Tour;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Ruta del archivo Excel
        String ruta = "src/main/resources/tours.xlsx";

        // Creamos el gestor de datos
        GestorDatos gestor = new GestorDatos();

        try {

            // Cargamos los tours desde Excel
            ArrayList<Tour> tours = gestor.cargarTours(ruta);

            // Mostramos el encabezado del programa
            System.out.println("================================");
            System.out.println("       LLANQUIHUE TOUR");
            System.out.println("================================");

            // Mostramos la cantidad de tours cargados
            System.out.println("Cantidad de tours: " + tours.size());

            // Verificamos si existen tours cargados
            if (tours.isEmpty()) {
                System.out.println("\nNo existen tours disponibles.");
                return;
            }

            // Mostramos información de la agencia
            System.out.println("\n--- INFORMACION DE LA AGENCIA ---");
            System.out.println(gestor.getAgencia());

            // Mostramos el listado de tours
            System.out.println("\n--- LISTADO DE TOURS ---");

            for (Tour tour : tours) {
                System.out.println(tour);
            }

            // Buscamos un tour utilizando HashMap
            System.out.println("\n--- BUSQUEDA DE TOUR ---");

            // Prueba con minúsculas y espacios adicionales
            Tour resultado1 =
                    gestor.buscarTour("   ruta DE los VOLCANES   ");

            if (resultado1 != null) {
                System.out.println("Tour encontrado:");
                System.out.println(resultado1);
            } else {
                System.out.println("No se encontro el tour.");
            }

            // Realizamos una segunda búsqueda
            Tour resultado2 =
                    gestor.buscarTour("Tour Puerto Varas");

            if (resultado2 != null) {
                System.out.println("\nSegunda busqueda:");
                System.out.println(resultado2);
            } else {
                System.out.println("No se encontro el tour.");
            }

            // Consultamos el historial Stack
            System.out.println("\n--- HISTORIAL DE CONSULTAS ---");

            System.out.println("Cantidad de consultas: "
                    + gestor.cantidadConsultas());

            System.out.println("Ultimo tour consultado:");

            Tour ultimaConsulta = gestor.obtenerUltimaConsulta();

            if (ultimaConsulta != null) {
                System.out.println(ultimaConsulta);
            } else {
                System.out.println("No existen consultas registradas.");
            }

            // Retiramos la última consulta
            System.out.println("\n--- RETIRAR ULTIMA CONSULTA ---");

            Tour retirado = gestor.retirarUltimaConsulta();

            System.out.println("Tour retirado del historial:");

            if (retirado != null) {
                System.out.println(retirado);
            } else {
                System.out.println("No existen consultas para retirar.");
            }

            System.out.println("Consultas restantes: "
                    + gestor.cantidadConsultas());

        } catch (IOException e) {

            System.out.println("Error al leer el archivo Excel:");
            System.out.println(e.getMessage());

        } catch (IllegalArgumentException e) {

            System.out.println("Error en los datos del programa:");
            System.out.println(e.getMessage());

        }
    }
}