package model;

import java.util.ArrayList;

public class AgenciaTuristica {

    // Atributos
    private String nombre;
    private ArrayList<Tour> tours;

    // Constructor con validaciones
    public AgenciaTuristica(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre de la agencia no puede estar vacío.");
        }

        this.nombre = nombre.trim();
        this.tours = new ArrayList<>();
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public ArrayList<Tour> getTours() {
        // Devolvemos una copia de la lista original
        return new ArrayList<>(tours);
    }

    // Setter con validación
    public void setNombre(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre de la agencia no puede estar vacío.");
        }

        this.nombre = nombre.trim();
    }

    // Agregar un tour a la agencia
    public void agregarTour(Tour tour) {

        if (tour == null) {
            throw new IllegalArgumentException(
                    "No se puede agregar un tour vacío a la agencia.");
        }

        // Verificamos que el tour no esté duplicado
        for (Tour existente : tours) {

            if (existente.getNombre().equalsIgnoreCase(
                    tour.getNombre().trim())) {

                throw new IllegalArgumentException(
                        "El tour ya existe en la agencia.");
            }
        }

        tours.add(tour);
    }

    // Mostrar información de la agencia
    @Override
    public String toString() {
        return "Agencia: " + nombre
                + " | Cantidad de tours: " + tours.size();
    }
}