package Clase3;

import java.util.ArrayList;

public class Actividad_2 {


    public static int[] encontrarMayores(ArrayList<Integer> array){
        return encontrarDosMayores(array, 0, array.size());
    }

    private static int[] encontrarDosMayores(ArrayList<Integer> array, int inicio, int fin) {
        if (fin - inicio == 1) {
            // si hay un solo elemento, lo devolvemos como los dos mayores (mismo número dos veces)
            return new int[]{array.get(inicio), Integer.MIN_VALUE};
        } else if (fin - inicio == 2) {
            // si hay dos elementos, devolvemos ambos en orden
            int a = array.get(inicio);
            int b = array.get(inicio + 1);
            return new int[]{Math.max(a, b), Math.min(a, b)};
        }

        int mitad = (inicio + fin) / 2;

        // recurrsion para ordenar cada lado del array
        int[] izq = encontrarDosMayores(array, inicio, mitad);
        int[] der = encontrarDosMayores(array, mitad, fin);

        int[] mayores = new int[2];
        mayores[0] = Math.max(izq[0], der[0]); // el mayor de todos
        mayores[1] = Math.max(Math.min(izq[0], der[0]), Math.max(izq[1], der[1])); // el segundo mayor

        return mayores;
    }

    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        array.add(5);
        array.add(3);
        array.add(8);
        array.add(1);
        array.add(9);
        array.add(2);
        array.add(7);
        array.add(4);
        array.add(6);

        int[] dosMayores = encontrarDosMayores(array, 0, array.size());
        System.out.println("Los dos mayores números son: " + dosMayores[0] + " y " + dosMayores[1]); // debería imprimir 9 y 8
    }    
}

/*
Caso Base:
Si el array tiene un solo elemento (fin - inicio == 1), el tiempo de ejecución es constante O(1).
Si el array tiene dos elementos (fin - inicio == 2), el tiempo de ejecución es O(1) ya que solo se 
realiza una comparación entre los dos elementos.

Paso Recursivo:
En cada llamada recursiva, se divide el array en dos mitades. Esto se hace hasta que cada subarray tiene uno o dos elementos.
Por lo tanto, hay log(n) niveles en el árbol de recursión (donde n es el número total de elementos en el array).

Combinación:
En cada nivel de la recursión, los dos elementos más grandes de las dos mitades se combinan 
para obtener los dos elementos más grandes de la sección actual del array.
La combinación en sí toma tiempo O(1) ya que solo se realizan un par de comparaciones por nivel.

Complejidad Total:
Número de comparaciones: En cada nivel de la recursión, el número de comparaciones realizadas es constante (O(1)), 
pero como hay log(n) niveles, el número total de comparaciones realizadas en todo el proceso es O(log(n)).
Número total de operaciones: Aunque el número de comparaciones es O(log(n)), cada elemento se considera una vez en cada nivel, 
y como hay n elementos y log(n) niveles, la complejidad en general es O(n log(n)).
 */

 