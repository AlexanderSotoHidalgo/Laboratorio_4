package Proyecto_Renacer;

public class Cita {
    private String nombrePaciente;
    private String fecha;
    private String hora;
    private Medico medico;

    public Cita(String nombrePaciente, String fecha, String hora, Medico medico) {
        this.nombrePaciente = nombrePaciente;
        this.fecha = fecha;
        this.hora = hora;
        this.medico = medico;
    }

    @Override
    public String toString() {
        return "Cita para " + nombrePaciente + " el " + fecha + " a las " + hora + ", Médico: " + medico.getNombre();
    }
}
