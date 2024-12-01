package Proyecto_Renacer;

public class Paciente {
    private String DNI;
    private String nombre;
    private String apellido;
    private int edad;
    private String historial;
    private int prioridad;  // 0 para leve, 1 para urgente
    private int id;  // Para mantener el orden de llegada

    // Constructor con prioridad y un id de llegada
    public Paciente(String DNI, String nombre, String apellido, int edad, String historial, int prioridad) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.historial = historial;
        this.prioridad = prioridad;
    }

    // Métodos getters y setters
    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getHistorial() {
        return historial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DNI: " + DNI + ", Nombre: " + nombre + " " + apellido + ", Edad: " + edad + 
               ", Prioridad: " + (prioridad == 1 ? "Urgente" : "Leve") + ", Historial: " + historial;
    }
}
