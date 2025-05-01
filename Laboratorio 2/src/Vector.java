import java.util.Random;
import java.util.Scanner;

public class Vector {
    private int[] datos;
    private int n;

    public Vector(int n) {
        this.n = n;
        datos = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            datos[i] = random.nextInt(201) - 100;
        }
    }

    public void mostrarArreglo() {
        System.out.println("El arreglo generado es ");
        for (int num : datos) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public void sumaSubsecuenciaMaxima() {
        int suma = 0;
        int sumaParcial = 0;
        int inicio = 0;
        int fin = 0;
        int inicioTemp = 0;
        for (int k = 0; k < n; k++) {
            sumaParcial += datos[k];
            if (sumaParcial < 0) {
                sumaParcial = 0;
                inicioTemp = k + 1;
            } else if (sumaParcial > suma) {
                suma = sumaParcial;
                inicio = inicioTemp;
                fin = k;
            }
        }
        System.out.println("La suma maxima de una subsecuencia es " + suma);
        if (suma > 0) {
            System.out.print("El subarreglo que genera la suma maxima es ");
            for (int i = inicio; i <= fin; i++) {
                System.out.print(datos[i] + " ");
            }
            System.out.println();
              if (inicio == fin) {
              System.out.println("La suma maxima se genero unicamente por el indice " + inicio);
             } else {
               System.out.println("La suma maxima esta en el subarreglo que va desde el indice " + inicio + " hasta el indice " + fin);
         }
        } else {
            System.out.println("La suma de los numeros resulto negativa. Por lo tanto no se eligio ningun subarreglo ya que la suma es 0.");
        }
        System.out.println("Funcion de tiempo: T(n) = C * n");
        System.out.println("Donde C es una constante que representa el numero de operaciones por iteracion.");
        System.out.println("Notacion asintotica (complejidad temporal): O(n)");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Cuantos numeros quieres en el arreglo? ");
        int cantidad = scanner.nextInt();
        Vector v = new Vector(cantidad);
        v.mostrarArreglo();
        v.sumaSubsecuenciaMaxima();
    }
}
