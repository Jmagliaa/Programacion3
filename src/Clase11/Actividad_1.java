import java.util.*;

public class Act1 {
    static class Grafo {
        private final Map<Integer, List<Integer>> adyacencias = new HashMap<>();

        // Agrega un nodo al grafo si no existe y una arista entre dos nodos
        public void agregarArista(int origen, int destino) {
            adyacencias.putIfAbsent(origen, new ArrayList<>());
            adyacencias.putIfAbsent(destino, new ArrayList<>());
            adyacencias.get(origen).add(destino);
            // Si el grafo es no dirigido, descomentar la siguiente línea
            // adyacencias.get(destino).add(origen);
        }

        // Método para obtener los vecinos de un nodo
        public List<Integer> obtenerVecinos(int nodo) {
            return adyacencias.getOrDefault(nodo, new ArrayList<>());
        }
    }
    // Método principal que inicia el DFS
    public static void DFS(Grafo grafo, int nodoInicial) {
        Set<Integer> visitado = new HashSet<>();
        DFS_recursivo(grafo, nodoInicial, visitado);
    }

    // Método recursivo de DFS
    private static void DFS_recursivo(Grafo grafo, int nodo, Set<Integer> visitado) {
        visitado.add(nodo);
        procesar(nodo); // Procesar el nodo (ej. imprimirlo)

        // Explorar los vecinos no visitados
        for (int vecino : grafo.obtenerVecinos(nodo)) {
            if (!visitado.contains(vecino)) {
                DFS_recursivo(grafo, vecino, visitado);
            }
        }
    }

    // Método de procesamiento del nodo
    private static void procesar(int nodo) {
        System.out.println("Visitando nodo: " + nodo);
    }

    public static void main(String[] args) {

        Grafo grafo = new Grafo();
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(1, 4);
        grafo.agregarArista(2, 5);
        grafo.agregarArista(3, 6);
        grafo.agregarArista(4, 7);
        grafo.agregarArista(4, 8);


        System.out.println("Recorrido DFS desde el nodo 0:");
        DFS(grafo, 0);
    }
}
