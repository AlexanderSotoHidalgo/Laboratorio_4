package Proyecto_Renacer;

public class InventarioMedicamentos {
    private Nodo cabeza;

    private class Nodo {
        Medicamento medicamento;
        Nodo siguiente;
        Nodo anterior;

        Nodo(Medicamento medicamento) {
            this.medicamento = medicamento;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    public InventarioMedicamentos() {
        cabeza = null;
    }

    public void agregarMedicamento(Medicamento medicamento) {
        Nodo nuevoNodo = new Nodo(medicamento);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            nuevoNodo.siguiente = cabeza; // Circular
            nuevoNodo.anterior = cabeza;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != cabeza) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevoNodo;
            nuevoNodo.anterior = temp;
            nuevoNodo.siguiente = cabeza;
            cabeza.anterior = nuevoNodo;
        }
        System.out.println("Medicamento agregado al inventario.");
    }

    public void mostrarInventario() {
        if (cabeza == null) {
            System.out.println("No hay medicamentos en el inventario.");
            return;
        }
        Nodo temp = cabeza;
        do {
            System.out.println(temp.medicamento);
            temp = temp.siguiente;
        } while (temp != cabeza);
    }
}
