package Clase2;

public class Actividad_4 {

    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return; // ya está ordenado
        }

        int mitad = array.length / 2;

        // dividimos el array en dos mitades
        int[] izquierda = new int[mitad];
        int[] derecha = new int[array.length - mitad];

        // copiamos los elementos en las mitades
        for (int i = 0; i < mitad; i++) {
            izquierda[i] = array[i];
        }
        for (int i = mitad; i < array.length; i++) {
            derecha[i - mitad] = array[i];
        }

        // recurssion para ordenar las mitades
        mergeSort(izquierda);
        mergeSort(derecha);

        // mezclar las mitades ordenadas
        merge(array, izquierda, derecha);
    }

    // mezclamos dos mitades ordenadas
    private static void merge(int[] array, int[] izquierda, int[] derecha) {
        int i = 0, j = 0, k = 0; // indices para recorrer las mitades. i izquierda, j derecha, k array

        // mezclar los elementos en orden
        while (i < izquierda.length && j < derecha.length) {
            if (izquierda[i] <= derecha[j]) {
                array[k++] = izquierda[i++];
            } 
            else{
                array[k++] = derecha[j++];
            }
        }

        // copiar los elementos restantes deizquierda
        while (i < izquierda.length) {
            array[k++] = izquierda[i++];
        }

        // copiar los elementos restantes de derecha
        while (j < derecha.length) {
            array[k++] = derecha[j++];
        }
    }

    public static void main(String[] args) {
        int[] array = { 12, 11, 13, 5, 6, 7 };

        mergeSort(array);

        System.out.println("Array ordenado: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
