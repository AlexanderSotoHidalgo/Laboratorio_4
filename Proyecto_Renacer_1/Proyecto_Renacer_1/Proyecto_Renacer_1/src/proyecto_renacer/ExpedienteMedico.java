package Proyecto_Renacer;

import java.util.LinkedList;

public class ExpedienteMedico {
    private LinkedList<EventoMedico> historial;

    public ExpedienteMedico() {
        historial = new LinkedList<>();
    }

    public void agregarEvento(String fecha, String diagnostico, String tratamiento, String observaciones) {
        EventoMedico evento = new EventoMedico(fecha, diagnostico, tratamiento, observaciones);
        historial.add(evento);
        System.out.println("Evento médico agregado.");
    }

    public void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("No se han registrado eventos médicos.");
            return;
        }
        for (EventoMedico evento : historial) {
            System.out.println(evento);
        }
    }

    // Clase interna para representar un evento médico
    private class EventoMedico {
        private String fecha;
        private String diagnostico;
        private String tratamiento;
        private String observaciones;

        public EventoMedico(String fecha, String diagnostico, String tratamiento, String observaciones) {
            this.fecha = fecha;
            this.diagnostico = diagnostico;
            this.tratamiento = tratamiento;
            this.observaciones = observaciones;
        }

        @Override
        public String toString() {
            return "Fecha: " + fecha + ", Diagnóstico: " + diagnostico + ", Tratamiento: " + tratamiento + ", Observaciones: " + observaciones;
        }
    }
}
