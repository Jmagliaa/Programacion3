package Clase2;

public class Actividad_2 {
    public static int busquedaBinaria(int[] array, int numero) {
        int izquierda = 0;
        int derecha = array.length - 1;

        while (izquierda <= derecha) {
            int mitad = izquierda + (derecha - izquierda) / 2; // calcula la mitad del array

            if (array[mitad] == numero) { // comprueba si el numero buscado está en la mitad
                return mitad; // retorna el índice del numero buscado
            }

            if (array[mitad] < numero) { // en caso de que el numero buscado sea mayor, se "elimina" la parte izquierda
                izquierda = mitad + 1;
            } 

            else { // en caso de que el numero buscado sea menor, se "elimina" la parte derecha
                derecha = mitad - 1;
            }
        }

        return -1; // retorna -1 si el numero no se encuentra en el array
    }

    public static void main(String[] args) {
        int[] array = {1,3,5,7,9};
        int numero = 7;
        int resultado = busquedaBinaria(array, numero);

        if (resultado == -1) {
            System.out.println("El elemento no fue encontrado en el array!");
        } 
        else{
            System.out.println("El indice del elemento es " + resultado);
        }
    }
}
