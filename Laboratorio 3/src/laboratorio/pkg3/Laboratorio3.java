/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package laboratorio.pkg3;


public class Laboratorio3 {
    public static int ackermann(int m, int n) {
        int[] pila; 
        pila = new int[100000];
        int tope = 0;

        pila[tope++] = m;

        while (tope > 0) {
            m = pila[--tope];

            if (m == 0) {
                n = n + 1;
            } else if (n == 0) {
                pila[tope++] = m - 1;
                n = 1;
            } else {
                pila[tope++] = m - 1;
                pila[tope++] = m;
                n = n - 1;
            }
        }

        return n;
    }

    public static void main(String[] args) {
        System.out.println("A(2,1) = " + ackermann(2, 1)); 
        System.out.println("A(3,2) = " + ackermann(3, 2)); 
        System.out.println("A(4,1) = " + ackermann(4, 1));
    }

    /*
      FUNCIÓN DE TIEMPO Y NOTACIÓN ASINTÓTICA
      - La funcion de Ackermann crece mas rapido que cualquier funcion primitiva recursiva.
      - Su tiempo de ejecucion esta fuertemente ligado al valor retornado: T(m, n) = Ω(A(m, n)).
      
      Ejemplos:
       - A(2,1) = 5          → tiempo razonable
       - A(3,2) = 29         → aun esta manejable
       - A(4,1) = 65533      → computacionalmente muy costoso
      
      Notacion asintotica: 
       - A(m, n) ∉ PR (no pertenece al conjunto de funciones primitivas recursivas).
       - Tiempo: Hiperenorme (comparable a funciones de la jerarquía de fast-growing functions).
       - A(m, n) domina cualquier función de la forma O(f(n)) donde f es recursiva primitiva.
     */
}

