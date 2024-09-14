package Clase3;

import java.util.ArrayList;
import java.util.Collections;

public class Actividad_4 {

    public static ArrayList<Integer> encontrarNMayores(ArrayList<Integer> array, int n) {
        return encontrarNMayores(array, 0, array.size(), n);
    }

    private static ArrayList<Integer> encontrarNMayores(ArrayList<Integer> array, int inicio, int fin, int n) {
        // si el rango tiene menos o igual a n elementos, devolvemos la lista ordenada
        if (fin - inicio <= n) {
            ArrayList<Integer> subArray = new ArrayList<>(array.subList(inicio, fin));
            Collections.sort(subArray, Collections.reverseOrder());
            return subArray;
        }

        // dividimos la lista en dos partes
        int mitad = (inicio + fin) / 2;

        // llamamos recursivamente para cada mitad
        ArrayList<Integer> mayoresIzq = encontrarNMayores(array, inicio, mitad, n);
        ArrayList<Integer> mayoresDer = encontrarNMayores(array, mitad, fin, n);

        // combinamos los resultados
        return combinar(mayoresIzq, mayoresDer, n);
    }

    private static ArrayList<Integer> combinar(ArrayList<Integer> izq, ArrayList<Integer> der, int n) {
        // combinar ambas listas
        ArrayList<Integer> combinacion = new ArrayList<>(izq);
        combinacion.addAll(der);

        // ordenar en orden descendente
        Collections.sort(combinacion, Collections.reverseOrder());

        // devolver los primeros n elementos más grandes
        return new ArrayList<>(combinacion.subList(0, n));
    }

    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        array.add(3);
        array.add(8);
        array.add(2);
        array.add(10);
        array.add(7);
        array.add(5);

        int n = 10;
        ArrayList<Integer> nMayores = encontrarNMayores(array, n);

        System.out.println("Los " + n + " elementos más grandes son: " + nMayores);
    }
}


/*
Caso base:
Si la lista tiene menos de n elementos, el algoritmo ordena la lista y la devuelve directamente, 
lo que tiene una complejidad de O(n log n) debido al uso de un algoritmo de ordenamiento.

Paso recursivo:
La lista se divide en dos mitades, lo que implica dos llamadas recursivas a encontrarNMayores. 
El costo de estas llamadas es O(T(n/2)).
Después, las dos mitades se combinan y se ordenan, lo que tiene un costo de O(n log n) 
en el peor de los casos (si utilizamos un algoritmo eficiente de ordenamiento).

Complejidad total:
La recurrencia es T(n) = 2T(n/2) + O(n log n).
La complejidad es O(n log² n), debido a que estamos ordenando las dos listas combinadas en cada nivel.
 */