package Proyecto_Renacer;

import java.util.ArrayList;
import java.util.List;

public class CentroMedico {
    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<Cita> citas;
    public InventarioMedicamentos inventario;

    public CentroMedico() {
        pacientes = new ArrayList<>();
        medicos = new ArrayList<>();
        citas = new ArrayList<>();
        inventario = new InventarioMedicamentos();
    }

    public void registrarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public void registrarMedico(Medico medico) {
        medicos.add(medico);
    }

    public void agendarCita(Cita cita) {
        citas.add(cita);
    }

    public void mostrarCitas() {
        for (Cita cita : citas) {
            System.out.println(cita);
        }
    }

    public void mostrarPacientes() {
        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
        }
    }

    public void mostrarMedicos() {
        for (Medico medico : medicos) {
            System.out.println(medico);
        }
    }

    public void mostrarInventario() {
        inventario.mostrarInventario();
    }

    public void generarReporte() {
        System.out.println("Reporte de Actividad:");
        System.out.println("Pacientes registrados: " + pacientes.size());
        System.out.println("Citas programadas: " + citas.size());
        System.out.println("Medicamentos en inventario:");
        inventario.mostrarInventario();
    }
}
