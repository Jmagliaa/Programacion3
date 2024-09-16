package Clase5;

public class Actividad_2 {
    private int[][] matrizAdyacencia;
    private int numVertices;

    // Constructor para inicializar el grafo con un número fijo de vértices
    public Actividad_2(int numVertices) {
        this.numVertices = numVertices;
        matrizAdyacencia = new int[numVertices][numVertices];
    }

    // Método para agregar una arista
    public void agregarArista(int origen, int destino) {
        if (origen >= 0 && origen < numVertices && destino >= 0 && destino < numVertices) {
            matrizAdyacencia[origen][destino] = 1; // Representa la presencia de una arista
        }
    }

    // Método para eliminar una arista
    public void eliminarArista(int origen, int destino) {
        if (origen >= 0 && origen < numVertices && destino >= 0 && destino < numVertices) {
            matrizAdyacencia[origen][destino] = 0; // Elimina la arista
        }
    }

    // Método para verificar si existe una arista entre dos vértices
    public boolean existeArista(int origen, int destino) {
        if (origen >= 0 && origen < numVertices && destino >= 0 && destino < numVertices) {
            return matrizAdyacencia[origen][destino] == 1;
        }
        return false;
    }

    // Método para listar los adyacentes de un vértice dado
    public void listarAdyacentes(int vertice) {
        if (vertice >= 0 && vertice < numVertices) {
            System.out.print("Vértices adyacentes a " + vertice + ": ");
            for (int i = 0; i < numVertices; i++) {
                if (matrizAdyacencia[vertice][i] == 1) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }
    }

    // Método para contar el grado de salida de un vértice
    public int contarGradoSalida(int vertice) {
        int gradoSalida = 0;
        if (vertice >= 0 && vertice < numVertices) {
            for (int i = 0; i < numVertices; i++) {
                if (matrizAdyacencia[vertice][i] == 1) {
                    gradoSalida++;
                }
            }
        }
        return gradoSalida;
    }

    // Método para contar el grado de entrada de un vértice
    public int contarGradoEntrada(int vertice) {
        int gradoEntrada = 0;
        if (vertice >= 0 && vertice < numVertices) {
            for (int i = 0; i < numVertices; i++) {
                if (matrizAdyacencia[i][vertice] == 1) {
                    gradoEntrada++;
                }
            }
        }
        return gradoEntrada;
    }

    public static void main(String[] args) {
        Actividad_2 grafo = new Actividad_2(4);

        // Agregar aristas
        grafo.agregarArista(0, 1); // A - B
        grafo.agregarArista(0, 2); // A - C
        grafo.agregarArista(1, 3); // B - D
        grafo.agregarArista(2, 3); // C - D

        // Verificar aristas
        System.out.println("Existe arista entre 0 y 1: " + grafo.existeArista(0, 1)); // true

        // Listar adyacentes
        grafo.listarAdyacentes(0); // Vértices adyacentes a 0: 1 2

        // Contar grado de salida
        System.out.println("Grado de salida del vértice 0: " + grafo.contarGradoSalida(0)); // 2

        // Contar grado de entrada
        System.out.println("Grado de entrada del vértice 3: " + grafo.contarGradoEntrada(3)); // 2

        // Eliminar arista
        grafo.eliminarArista(0, 1);
        System.out.println("Existe arista entre 0 y 1 después de eliminar: " + grafo.existeArista(0, 1)); // false
    }
}

/*
Caso base:
El acceso a la matriz es O(1), pero recorrer filas o columnas es O(n).

1. *agregarArista(int origen, int destino)*: 
   - O(1), ya que accede y modifica directamente la matriz.

2. *eliminarArista(int origen, int destino)*: 
   - O(1), al igual que agregar, cambia un valor en la matriz.

3. *existeArista(int origen, int destino)*: 
   - O(1), verifica un valor en la matriz.

4. *listarAdyacentes(int vertice)*: 
   - O(n), recorre la fila del vértice en la matriz.

5. *contarGradoSalida(int vertice)* y *contarGradoEntrada(int vertice)*: 
   - O(n), recorren fila o columna completas.

Complejidad total:
Principalmente O(1) excepto las funciones que recorren filas o columnas, que son O(n).
*/