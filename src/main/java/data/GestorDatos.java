package data;

import model.AgenciaTuristica;
import model.Tour;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.Locale;

public class GestorDatos {

    private ArrayList<Tour> listaTours = new ArrayList<>();
    private HashMap<String, Tour> mapaTours = new HashMap<>();
    private Stack<Tour> historialConsultas = new Stack<>();

    private AgenciaTuristica agencia =
            new AgenciaTuristica("Llanquihue Tour");

    private final String[] categoriasPermitidas = {
            "aventura", "lacustre", "gastronomico", "cultural"
    };

    // Metodo para mostrar errores de forma ordenada
    private void mostrarErrorFila(int numeroFila,
                                  String titulo,
                                  String detalle) {

        System.err.println();
        System.err.println("[!] Fila " + numeroFila
                + " | " + titulo);
        System.err.println("    " + detalle);
    }

    public ArrayList<Tour> cargarTours(String rutaArchivo)
            throws IOException {

        listaTours.clear();
        mapaTours.clear();
        historialConsultas.clear();

        agencia = new AgenciaTuristica("Llanquihue Tour");

        DataFormatter formateador = new DataFormatter();

        int registrosOmitidos = 0;

        System.out.println();
        System.out.println("==========================================");
        System.out.println("          CARGANDO DATOS DE TOURS");
        System.out.println("==========================================");

        try (FileInputStream archivo =
                     new FileInputStream(rutaArchivo);
             Workbook libro = new XSSFWorkbook(archivo)) {

            Sheet hoja = libro.getSheetAt(0);

            for (int i = 1; i <= hoja.getLastRowNum(); i++) {

                Row fila = hoja.getRow(i);

                if (fila == null) {
                    continue;
                }

                try {

                    // Verificamos que existan las tres columnas
                    if (fila.getCell(0) == null
                            || fila.getCell(1) == null
                            || fila.getCell(2) == null) {

                        mostrarErrorFila(
                                i + 1,
                                "Datos incompletos",
                                "Faltan datos en una o más columnas."
                        );

                        registrosOmitidos++;
                        continue;
                    }

                    // Obtenemos los datos del Excel
                    String nombre = formateador.formatCellValue(
                            fila.getCell(0)).trim();

                    String tipo = formateador.formatCellValue(
                            fila.getCell(1)).trim();

                    String precioTexto = formateador.formatCellValue(
                            fila.getCell(2)).trim();

                    // Normalizamos el nombre para evitar duplicados
                    String nombreNormalizado =
                            nombre.toLowerCase(Locale.ROOT);

                    if (mapaTours.containsKey(nombreNormalizado)) {

                        mostrarErrorFila(
                                i + 1,
                                "Registro duplicado",
                                "El tour ya existe en el sistema."
                        );

                        registrosOmitidos++;
                        continue;
                    }

                    // Validamos la categoría
                    String tipoNormalizado =
                            tipo.toLowerCase(Locale.ROOT);

                    boolean categoriaValida = false;

                    for (String categoria : categoriasPermitidas) {

                        if (tipoNormalizado.equals(categoria)) {
                            categoriaValida = true;
                            break;
                        }
                    }

                    if (!categoriaValida) {

                        mostrarErrorFila(
                                i + 1,
                                "Categoría inválida",
                                "La categoría ingresada no está permitida."
                        );

                        registrosOmitidos++;
                        continue;
                    }

                    // Validamos que el precio sea un número entero
                    if (!precioTexto.matches("[0-9]+")) {

                        mostrarErrorFila(
                                i + 1,
                                "Precio inválido",
                                "El precio debe ser un número entero positivo."
                        );

                        registrosOmitidos++;
                        continue;
                    }

                    int precio = Integer.parseInt(precioTexto);

                    // Creamos el objeto Tour
                    Tour tour = new Tour(nombre, tipo, precio);

                    // Guardamos el tour en nuestras estructuras
                    listaTours.add(tour);

                    mapaTours.put(nombreNormalizado, tour);

                    agencia.agregarTour(tour);

                } catch (IllegalArgumentException e) {

                    mostrarErrorFila(
                            i + 1,
                            "Error en los datos",
                            e.getMessage()
                    );

                    registrosOmitidos++;
                }
            }
        }

        // Resumen final de la carga
        System.out.println();
        System.out.println("==========================================");
        System.out.println("             RESUMEN DE CARGA");
        System.out.println("==========================================");
        System.out.println("Tours incorporados: " + listaTours.size());
        System.out.println("Registros omitidos: " + registrosOmitidos);
        System.out.println("==========================================");
        System.out.println();

        return listaTours;
    }

    // Busca un tour por su nombre con el stack y hashmap
    public Tour buscarTour(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }

        String nombreNormalizado =
                nombre.trim().toLowerCase(Locale.ROOT);

        Tour tour = mapaTours.get(nombreNormalizado);

        if (tour != null) {
            historialConsultas.push(tour);
        }

        return tour;
    }

    // Obtiene la última consulta sin retirarla con el stack y hashmap
    public Tour obtenerUltimaConsulta() {

        if (historialConsultas.isEmpty()) {
            return null;
        }

        return historialConsultas.peek();
    }

    // Retira la última consulta del historial con stack
    public Tour retirarUltimaConsulta() {

        if (historialConsultas.isEmpty()) {
            return null;
        }

        return historialConsultas.pop();
    }

    // Devuelve la cantidad de consultas registradas
    public int cantidadConsultas() {
        return historialConsultas.size();
    }

    // Devuelve la agencia turística
    public AgenciaTuristica getAgencia() {

        return agencia;
    }
}