package Proyecto_Renacer;

import java.util.LinkedList;

public class RegistroMedicos {
    private LinkedList<Medico> medicos;

    public RegistroMedicos() {
        medicos = new LinkedList<>();
    }

    public void agregarMedico(Medico medico) {
        medicos.add(medico);
        System.out.println("Médico registrado.");
    }

    public void mostrarMedicos() {
        if (medicos.isEmpty()) {
            System.out.println("No hay médicos registrados.");
            return;
        }
        for (Medico medico : medicos) {
            System.out.println(medico);
        }
    }
}
