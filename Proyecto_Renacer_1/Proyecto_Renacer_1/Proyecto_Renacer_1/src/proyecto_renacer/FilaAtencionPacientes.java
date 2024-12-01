package Proyecto_Renacer;

import java.util.PriorityQueue;

public class FilaAtencionPacientes {
    private PriorityQueue<Paciente> fila;
    private int contador; // Este contador asegura que el orden de llegada se respete

    // Constructor
    public FilaAtencionPacientes() {
        this.contador = 0;
        fila = new PriorityQueue<>((p1, p2) -> {
            // Primero comparo la prioridad (urgente primero)
            if (p1.getPrioridad() != p2.getPrioridad()) {
                return Integer.compare(p2.getPrioridad(), p1.getPrioridad());
            }
            // Si la prioridad es la misma, comparo el orden de llegada (por el contador)
            return Integer.compare(p1.getId(), p2.getId());
        });
    }

    // Método para agregar un paciente a la fila
    public void agregarPaciente(Paciente paciente) {
        paciente.setId(contador++);  // Asigno un id único (por orden de llegada)
        fila.offer(paciente);  // Se añade el paciente y se mantiene el orden
        System.out.println("Paciente registrado en la fila de atención: " + paciente.getNombre() + " " + paciente.getApellido());
    }

    // Mostrar todos los pacientes en espera, ordenados por prioridad y llegada
    public void mostrarPacientes() {
        if (fila.isEmpty()) {
            System.out.println("No hay pacientes en espera.");
            return;
        }
        for (Paciente paciente : fila) {
            System.out.println(paciente);
        }
    }

    // Método para atender al siguiente paciente (se elimina de la fila)
    public Paciente atenderPaciente() {
        Paciente paciente = fila.poll();  // El paciente con mayor prioridad será atendido
        if (paciente != null) {
            System.out.println("Paciente atendido: " + paciente.getNombre() + " " + paciente.getApellido());
        } else {
            System.out.println("No hay pacientes en espera.");
        }
        return paciente;
    }
}
