package Proyecto_Renacer;

import java.io.*;
import java.util.*;

public class RegistroPaciente {
    private List<Paciente> pacientes;
    private static final String ARCHIVO_PACIENTES = "pacientes.txt";

    public RegistroPaciente() {
        pacientes = new ArrayList<>();
        cargarPacientesDesdeArchivo();
    }

    // Método para registrar un paciente
    public void registrarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        guardarPacientesEnArchivo();  // Guardar la lista actualizada de pacientes
    }

    // Método para guardar todos los pacientes en el archivo
    private void guardarPacientesEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_PACIENTES))) {
            for (Paciente paciente : pacientes) {
                writer.write(paciente.getDNI() + ";" +
                             paciente.getNombre() + ";" +
                             paciente.getApellido() + ";" +
                             paciente.getEdad() + ";" +
                             paciente.getHistorial() + ";" +
                             paciente.getPrioridad());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los pacientes en el archivo: " + e.getMessage());
        }
    }

    // Método para cargar los pacientes desde el archivo
    private void cargarPacientesDesdeArchivo() {
        File archivo = new File(ARCHIVO_PACIENTES);
        if (!archivo.exists()) {
            return;  // Si el archivo no existe, no hacemos nada
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_PACIENTES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 6) {  // Verificamos que la línea tenga los 6 campos necesarios
                    String DNI = datos[0];
                    String nombre = datos[1];
                    String apellido = datos[2];
                    int edad = Integer.parseInt(datos[3]);
                    String historial = datos[4];
                    int prioridad = Integer.parseInt(datos[5]);

                    Paciente paciente = new Paciente(DNI, nombre, apellido, edad, historial, prioridad);
                    pacientes.add(paciente);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los pacientes desde el archivo: " + e.getMessage());
        }
    }
    // Método para mostrar todos los pacientes registrados, ordenados por prioridad y orden de llegada
    public void mostrarPacientesOrdenados() {
        // Ordenamos los pacientes por prioridad y luego por el ID (orden de llegada)
        pacientes.sort((p1, p2) -> {
            // Primero comparo la prioridad (urgente primero)
            if (p1.getPrioridad() != p2.getPrioridad()) {
                return Integer.compare(p2.getPrioridad(), p1.getPrioridad());
            }
            // Si la prioridad es la misma, comparo el orden de llegada (por el ID)
            return Integer.compare(p1.getId(), p2.getId());
        });

        // Mostramos los pacientes ordenados
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
        }
    }
    // Método para buscar un paciente por DNI
    public Paciente buscarPacientePorDNI(String DNI) {
        for (Paciente paciente : pacientes) {
            if (paciente.getDNI().equalsIgnoreCase(DNI)) {
                return paciente;
            }
        }
        return null;  // Si no se encuentra, retorna null
    }

    // Método para mostrar todos los pacientes registrados
    public void mostrarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
        }
    }

    // Método para obtener la lista de pacientes
    public List<Paciente> obtenerListaPacientes() {
        return pacientes;
    }
    
}
