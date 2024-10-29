import java.util.*;

public class Act2 {

    static class Nodo {
        String nombre;

        Nodo(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public String toString() {
            return nombre;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Nodo)) return false;
            Nodo otro = (Nodo) obj;
            return nombre.equals(otro.nombre);
        }

        @Override
        public int hashCode() {
            return nombre.hashCode();
        }
    }

    static class Grafo {
        private final Map<Nodo, Map<Nodo, Integer>> adyacencias = new HashMap<>();

        void agregarNodo(Nodo nodo) {
            adyacencias.putIfAbsent(nodo, new HashMap<>());
        }

        void agregarArista(Nodo origen, Nodo destino, int costo) {
            adyacencias.get(origen).put(destino, costo);
            // Si es un grafo no dirigido, descomentar la siguiente línea:
            // adyacencias.get(destino).put(origen, costo);
        }

        Set<Nodo> vecinos(Nodo nodo) {
            return adyacencias.get(nodo).keySet();
        }

        int costo(Nodo origen, Nodo destino) {
            return adyacencias.get(origen).get(destino);
        }
    }

    static class ColaPrioridad {
        private final PriorityQueue<Entrada> cola;

        public ColaPrioridad() {
            this.cola = new PriorityQueue<>(Comparator.comparingInt(e -> e.costo));
        }

        public void insertar(Nodo nodo, int costo) {
            cola.add(new Entrada(nodo, costo));
        }

        public Entrada extraer_min() {
            return cola.poll();
        }

        public boolean esta_vacia() {
            return cola.isEmpty();
        }

        private static class Entrada {
            Nodo nodo;
            int costo;

            Entrada(Nodo nodo, int costo) {
                this.nodo = nodo;
                this.costo = costo;
            }
        }
    }

    public static String UCS(Grafo grafo, Nodo nodo_inicial, Nodo nodo_objetivo) {
        // Crear una cola de prioridad para manejar los caminos en función del costo
        ColaPrioridad cola_prioridad = new ColaPrioridad();

        // Insertar el nodo inicial con costo 0
        cola_prioridad.insertar(nodo_inicial, 0);

        // Diccionario para llevar el seguimiento de los costos mínimos hasta cada nodo
        Map<Nodo, Integer> costos_min = new HashMap<>();
        costos_min.put(nodo_inicial, 0);

        // Mientras haya nodos en la cola de prioridad
        while (!cola_prioridad.esta_vacia()) {
            // Extraer el nodo con el menor costo acumulado
            ColaPrioridad.Entrada entrada = cola_prioridad.extraer_min();
            Nodo nodo_actual = entrada.nodo;
            int costo_actual = entrada.costo;

            // Si llegamos al nodo objetivo, devolver el costo
            if (nodo_actual.equals(nodo_objetivo)) {
                return String.valueOf(costo_actual);
            }

            // Explorar los vecinos del nodo actual
            for (Nodo vecino : grafo.vecinos(nodo_actual)) {
                int costo_vecino = costo_actual + grafo.costo(nodo_actual, vecino);

                // Si el costo para llegar al vecino es menor que el conocido
                if (!costos_min.containsKey(vecino) || costo_vecino < costos_min.get(vecino)) {
                    // Actualizar el costo mínimo para llegar al vecino
                    costos_min.put(vecino, costo_vecino);
                    // Insertar o actualizar el vecino en la cola de prioridad con su nuevo costo
                    cola_prioridad.insertar(vecino, costo_vecino);
                }
            }
        }

        // Si se sale del bucle sin haber encontrado el nodo objetivo
        return "No se encontró un camino";


    }

    public static void main(String[] args) {

        Grafo grafo = new Grafo();

        Nodo A = new Nodo("A");
        Nodo B = new Nodo("B");
        Nodo C = new Nodo("C");
        Nodo D = new Nodo("D");
        Nodo E = new Nodo("E");


        grafo.agregarNodo(A);
        grafo.agregarNodo(B);
        grafo.agregarNodo(C);
        grafo.agregarNodo(D);
        grafo.agregarNodo(E);


        grafo.agregarArista(A, B, 1);
        grafo.agregarArista(A, C, 4);
        grafo.agregarArista(B, C, 2);
        grafo.agregarArista(B, D, 5);
        grafo.agregarArista(C, D, 1);
        grafo.agregarArista(C, E, 3);
        grafo.agregarArista(D, E, 2);


        String resultado = UCS(grafo, A, E);
        System.out.println("Costo mínimo desde " + A + " hasta " + E + ": " + resultado);
    }
}
