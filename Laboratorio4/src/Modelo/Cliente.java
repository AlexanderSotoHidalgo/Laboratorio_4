package Modelo;

public class Cliente {
    private String codigo;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String direccion;
    private String postal;

    public Cliente(String codigo, String nombre, String apellido, String telefono, 
                   String correo, String direccion, String postal) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.postal = postal;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + "\n" +
               "Nombre: " + nombre + "\n" +
               "Apellido: " + apellido + "\n" +
               "Teléfono: " + telefono + "\n" +
               "Correo: " + correo + "\n" +
               "Dirección: " + direccion + "\n" +
               "Código Postal: " + postal;
    }
}