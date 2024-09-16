package Clase5;

import java.util.*;

class Grafo {
    private int numVertices;
    private LinkedList<int[]>[] listaAdyacencia; // Lista de adyacencia para representar el grafo

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        listaAdyacencia = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            listaAdyacencia[i] = new LinkedList<>();
        }
    }

    // Método para agregar una arista al grafo
    public void agregarArista(int origen, int destino, int peso) {
        listaAdyacencia[origen].add(new int[]{destino, peso});
        listaAdyacencia[destino].add(new int[]{origen, peso}); // Para grafo no dirigido
    }

    // Algoritmo de Prim para encontrar el Árbol de Recubrimiento Mínimo
    public void primMST() {
        boolean[] inMST = new boolean[numVertices]; // Array para verificar si el vértice está en el MST
        int[] key = new int[numVertices]; // Peso mínimo para llegar a un vértice
        int[] parent = new int[numVertices]; // Para almacenar el MST
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // Cola de prioridad

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0; // Comenzar desde el primer vértice
        pq.offer(new int[]{0, 0}); // (vértice, peso)
        parent[0] = -1; // El primer nodo no tiene padre

        while (!pq.isEmpty()) {
            int u = pq.poll()[0];
            inMST[u] = true; // Incluir u en el MST

            // Procesar los vértices adyacentes de u
            for (int[] vecino : listaAdyacencia[u]) {
                int v = vecino[0];
                int peso = vecino[1];

                // Si v no está en MST y el peso de (u, v) es menor que key[v]
                if (!inMST[v] && peso < key[v]) {
                    key[v] = peso;
                    pq.offer(new int[]{v, key[v]});
                    parent[v] = u;
                }
            }
        }

        // Mostrar el Árbol de Recubrimiento Mínimo y su costo total
        int costoTotal = 0;
        System.out.println("Aristas en el MST:");
        for (int i = 1; i < numVertices; i++) {
            System.out.println(parent[i] + " - " + i + " con peso: " + key[i]);
            costoTotal += key[i];
        }
        System.out.println("Costo total del MST: " + costoTotal);
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo(5);
        grafo.agregarArista(0, 1, 2);
        grafo.agregarArista(0, 3, 6);
        grafo.agregarArista(1, 2, 3);
        grafo.agregarArista(1, 3, 8);
        grafo.agregarArista(1, 4, 5);
        grafo.agregarArista(2, 4, 7);
        grafo.agregarArista(3, 4, 9);

        grafo.primMST();
    }
}


/*
Caso base:
Las operaciones de inicialización, agregar y eliminar aristas, verificar la existencia de una arista, 
listar adyacentes, y contar los grados de entrada y salida se basan en el acceso a elementos 
específicos de la matriz, lo que tiene una complejidad de O(1).

Paso recursivo:
No hay recursión en este código, ya que todas las operaciones se realizan de manera directa sobre la 
matriz de adyacencia. La complejidad se mantiene constante O(1) para las operaciones que acceden a elementos específicos.

Complejidad total:
- *Inicialización del grafo:* O(n^2), ya que se debe crear una matriz de adyacencia de tamaño n x n.
- *Agregar arista:* O(1), porque se accede y modifica un elemento específico de la matriz.
- *Eliminar arista:* O(1), similar al caso de agregar arista.
- *Verificar la existencia de una arista:* O(1), porque se accede a un elemento específico de la matriz.
- *Listar adyacentes:* O(n), ya que se recorre una fila completa de la matriz para encontrar los vértices adyacentes.
- *Contar grado de salida:* O(n), porque se recorre la fila correspondiente al vértice para contar las aristas.
- *Contar grado de entrada:* O(n), porque se recorre la columna correspondiente al vértice para contar las aristas.
*/
