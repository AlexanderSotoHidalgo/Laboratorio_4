package Proyecto_Renacer;

public class Medico {
    private String nombre;
    private String especialidad;
    private String horario;

    public Medico(String nombre, String especialidad, String horario) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.horario = horario;
    }
    public String getNombre() {
        return nombre;
    }
    public String getEspecialidad() {
        return especialidad;
    }

    public String getHorario() {
        return horario;
    }

    @Override
    public String toString() {
        return nombre + " - Especialidad: " + especialidad + " - Horario: " + horario;
    }
}
