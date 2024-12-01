package Proyecto_Renacer;

public class Medicamento {
    private String nombre;
    private int cantidad;
    private String fechaExpiracion;

    public Medicamento(String nombre, int cantidad, String fechaExpiracion) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fechaExpiracion = fechaExpiracion;
    }

    @Override
    public String toString() {
        return "Medicamento: " + nombre + ", Cantidad: " + cantidad + ", Expiración: " + fechaExpiracion;
    }
}
