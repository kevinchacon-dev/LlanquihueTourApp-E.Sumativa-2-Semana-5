package model;

public class Tour {

    // Atributos del tour
    private String nombre;
    private String tipo;
    private int precio;

    // Constructor con validaciones
    public Tour(String nombre, String tipo, int precio) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre no puede estar vacío.");
        }

        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El tipo no puede estar vacío.");
        }

        if (precio <= 0) {
            throw new IllegalArgumentException(
                    "El precio debe ser mayor que cero.");
        }

        // Eliminamos espacios innecesarios
        this.nombre = nombre.trim();
        this.tipo = tipo.trim();
        this.precio = precio;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPrecio() {
        return precio;
    }

    // Setters con validaciones
    public void setNombre(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre no puede estar vacío.");
        }

        this.nombre = nombre.trim();
    }

    public void setTipo(String tipo) {

        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El tipo no puede estar vacío.");
        }

        this.tipo = tipo.trim();
    }

    public void setPrecio(int precio) {

        if (precio <= 0) {
            throw new IllegalArgumentException(
                    "El precio debe ser mayor que cero.");
        }

        this.precio = precio;
    }

    // Mostrar información del tour
    @Override
    public String toString() {
        return "Tour: " + nombre
                + " | Tipo: " + tipo
                + " | Precio: $" + precio;
    }
}