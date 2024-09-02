package Clase2;

public class Actividad_3 {
    
    public static void quickSort(int[] array, int menor, int mayor) {
        if (menor < mayor) {
            // Particionar el array y obtener el índice del pivote
            int indicePivote = particionar(array, menor, mayor);
    
            // Ordenar las sublistas recursivamente
            quickSort(array, menor, indicePivote - 1);
            quickSort(array, indicePivote + 1, mayor);
        }
    }
    
    private static int particionar(int[] array, int menor, int mayor) {
        int pivote = array[mayor]; // asignamos el pivote
        int i = menor - 1; // indice de menores al pivote
    
        for (int j = menor; j < mayor; j++) {
            if (array[j] <= pivote) {
                i++;
                intercambiar(array, i, j); // intercambiamos elementos
            }
        }
    
        intercambiar(array, i + 1, mayor); // intercambiamos el pivote con el elemento en la posición i + 1
        return i + 1; // devolvemos la posición del pivote
    }
    
    private static void intercambiar(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {10, 7, 8, 9, 1, 5};
        int n = array.length;
    
        quickSort(array, 0, n - 1);
    
        System.out.println("Array ordenado: ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
