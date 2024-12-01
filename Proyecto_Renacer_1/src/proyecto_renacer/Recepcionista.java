/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto_Renacer;

import java.util.ArrayList;
import java.util.List;

public class Recepcionista {

    private String usuario;
    private String contrasena;

    // Lista estática de recepcionistas
    private static List<Recepcionista> listaRecepcionistas = new ArrayList<>();

    // Constructor
    public Recepcionista(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        listaRecepcionistas.add(this); // Se agrega a la lista al crear el objeto
    }

    // Método para validar el login con los datos de la lista
    public static boolean validarLogin(String usuarioIngresado, String contrasenaIngresada) {
        for (Recepcionista recepcionista : listaRecepcionistas) {
            if (recepcionista.usuario.equals(usuarioIngresado) && recepcionista.contrasena.equals(contrasenaIngresada)) {
                return true; // Si se encuentra un match, el login es exitoso
            }
        }
        return false; // Si no se encuentra el recepcionista
    }

    // Método para obtener la lista de recepcionistas
    public static List<Recepcionista> getListaRecepcionistas() {
        return listaRecepcionistas;
    }
}
