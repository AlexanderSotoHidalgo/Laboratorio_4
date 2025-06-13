package controlador;

import Modelo.Cliente;
import java.util.Arrays;

public class ClienteControlador {
    private int size;
    private Cliente[] tablaLineal;
    private BST[] tablaArbol;

    public ClienteControlador(int size) {
        this.size = size;
        this.tablaLineal = new Cliente[size];
        this.tablaArbol = new BST[size];
        Arrays.fill(this.tablaArbol, new BST()); 
    }

    private int hash(String clave) {
        return Math.abs(clave.hashCode()) % size;
    }

    private int insertarEnLineal(Cliente cliente) {
        String clave = cliente.getNombreCompleto();
        int indice = hash(clave);
        int pasos = 0;
        int indiceInicial = indice;

        while (tablaLineal[indice] != null) {
            if (tablaLineal[indice].getNombreCompleto().equals(clave)) {
                break; 
            }
            pasos++;
            indice = (indice + 1) % size;
            if (indice == indiceInicial) {
                throw new IllegalStateException("Tabla hash lineal llena");
            }
        }
        tablaLineal[indice] = cliente;
        return pasos;
    }

    private int insertarEnArbol(Cliente cliente) {
        String clave = cliente.getNombreCompleto();
        int indice = hash(clave);
        return tablaArbol[indice].insertar(cliente);
    }

    public String compararTiemposInsercion(Cliente cliente) {
        long inicioLineal = System.nanoTime();
        int pasosLineal = insertarEnLineal(cliente);
        long finLineal = System.nanoTime();
        long tiempoLineal = finLineal - inicioLineal;

        long inicioArbol = System.nanoTime();
        int pasosArbol = insertarEnArbol(cliente);
        long finArbol = System.nanoTime();
        long tiempoArbol = finArbol - inicioArbol;

        return "Reasignación Lineal:\n" +
               "   Tiempo (ns): " + tiempoLineal + "\n" +
               "   Pasos: " + pasosLineal + "\n" +
               "   Complejidad teórica: O(n) en el peor caso, O(1) en caso promedio\n\n" +
               "Encadenamiento con Árbol:\n" +
               "   Tiempo (ns): " + tiempoArbol + "\n" +
               "   Pasos: " + pasosArbol + "\n" +
               "   Complejidad teórica: O(n) en el peor caso (árbol degenerado), O(log n) en caso promedio";
    }

    public Cliente buscarEnLineal(String nombreCompleto) {
        int indice = hash(nombreCompleto);
        int indiceInicial = indice;

        do {
            if (tablaLineal[indice] == null) {
                return null;
            }
            if (tablaLineal[indice].getNombreCompleto().equals(nombreCompleto)) {
                return tablaLineal[indice];
            }
            indice = (indice + 1) % size;
        } while (indice != indiceInicial);
        
        return null;
    }

    public Cliente buscarEnArbol(String nombreCompleto) {
        int indice = hash(nombreCompleto);
        return tablaArbol[indice].buscar(nombreCompleto);
    }

    private static class BST {
        private Nodo raiz;

        private static class Nodo {
            Cliente cliente;
            Nodo izquierdo;
            Nodo derecho;

            Nodo(Cliente cliente) {
                this.cliente = cliente;
            }
        }

        public int insertar(Cliente cliente) {
            String clave = cliente.getNombreCompleto();
            if (raiz == null) {
                raiz = new Nodo(cliente);
                return 0;
            }
            return insertar(raiz, cliente, clave, 0);
        }

        private int insertar(Nodo actual, Cliente cliente, String clave, int pasos) {
            pasos++;
            int comparacion = clave.compareTo(actual.cliente.getNombreCompleto());
            
            if (comparacion == 0) {
                actual.cliente = cliente; 
            } else if (comparacion < 0) {
                if (actual.izquierdo == null) {
                    actual.izquierdo = new Nodo(cliente);
                } else {
                    pasos = insertar(actual.izquierdo, cliente, clave, pasos);
                }
            } else {
                if (actual.derecho == null) {
                    actual.derecho = new Nodo(cliente);
                } else {
                    pasos = insertar(actual.derecho, cliente, clave, pasos);
                }
            }
            return pasos;
        }

        public Cliente buscar(String clave) {
            return buscar(raiz, clave);
        }

        private Cliente buscar(Nodo actual, String clave) {
            if (actual == null) return null;
            
            int comparacion = clave.compareTo(actual.cliente.getNombreCompleto());
            if (comparacion == 0) {
                return actual.cliente;
            } else if (comparacion < 0) {
                return buscar(actual.izquierdo, clave);
            } else {
                return buscar(actual.derecho, clave);
            }
        }
    }
}