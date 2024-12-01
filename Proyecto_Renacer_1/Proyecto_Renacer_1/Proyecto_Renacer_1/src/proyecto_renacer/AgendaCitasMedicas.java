package Proyecto_Renacer;

public class AgendaCitasMedicas {
    private Nodo cabeza;

    private class Nodo {
        Cita cita;
        Nodo siguiente;

        Nodo(Cita cita) {
            this.cita = cita;
            this.siguiente = null;
        }
    }

    public void agregarCita(Cita cita) {
        Nodo nuevoNodo = new Nodo(cita);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            nuevoNodo.siguiente = cabeza; // Circular
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != cabeza) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevoNodo;
            nuevoNodo.siguiente = cabeza;
        }
    }

    public void mostrarCitas() {
        if (cabeza == null) {
            System.out.println("No hay citas programadas.");
            return;
        }
        Nodo temp = cabeza;
        do {
            System.out.println(temp.cita);
            temp = temp.siguiente;
        } while (temp != cabeza);
    }
}
