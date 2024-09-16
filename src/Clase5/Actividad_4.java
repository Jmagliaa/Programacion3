package Clase5;

import java.util.*;


class CentroDistribucion {
    private int id;
    private String nombre;

    public CentroDistribucion(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " (ID: " + id + ")";
    }
}


public class Actividad_4 {
    private HashMap<Integer, CentroDistribucion> centros; // Diccionario para almacenar los centros de distribución
    private HashMap<Integer, List<Arista>> grafo; // Lista de adyacencia para representar el grafo


    class Arista {
        int destino, peso;
        public Arista(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    // Constructor
    public Actividad_4() {
        centros = new HashMap<>();
        grafo = new HashMap<>();
    }


    public void agregarCentro(int id, String nombre) {
        if (!centros.containsKey(id)) {
            CentroDistribucion nuevoCentro = new CentroDistribucion(id, nombre);
            centros.put(id, nuevoCentro);
            grafo.put(id, new ArrayList<>()); // Inicializar la lista de adyacencia
        }
    }


    public void agregarRuta(int idOrigen, int idDestino, int peso) {
        if (centros.containsKey(idOrigen) && centros.containsKey(idDestino)) {
            grafo.get(idOrigen).add(new Arista(idDestino, peso));
            grafo.get(idDestino).add(new Arista(idOrigen, peso)); // grafo no dirigido
        }
    }

    // Método para aplicar el algoritmo de Dijkstra desde un centro de origen
    public void dijkstra(int idOrigen) {
        int numVertices = centros.size();
        int[] dist = new int[numVertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[idOrigen] = 0;

        PriorityQueue<Arista> pq = new PriorityQueue<>(Comparator.comparingInt(arista -> arista.peso));
        pq.add(new Arista(idOrigen, 0));

        while (!pq.isEmpty()) {
            Arista arista = pq.poll();
            int u = arista.destino;

            for (Arista adjArista : grafo.get(u)) {
                int v = adjArista.destino;
                int peso = adjArista.peso;
                if (dist[u] + peso < dist[v]) {
                    dist[v] = dist[u] + peso;
                    pq.add(new Arista(v, dist[v]));
                }
            }
        }

        // Mostrar los tiempos mínimos de entrega
        System.out.println("Tiempos mínimos de entrega desde el centro de origen (" + centros.get(idOrigen) + "):");
        for (int i = 0; i < numVertices; i++) {
            System.out.println( centros.get(i) + ": " + dist[i] + " minutos");
        }
    }

    public static void main(String[] args) {
        Actividad_4 logistica = new Actividad_4();


        logistica.agregarCentro(0, "Centro A");
        logistica.agregarCentro(1, "Centro B");
        logistica.agregarCentro(2, "Centro C");
        logistica.agregarCentro(3, "Centro D");


        logistica.agregarRuta(0, 1, 10);
        logistica.agregarRuta(0, 2, 5);
        logistica.agregarRuta(1, 2, 2);
        logistica.agregarRuta(1, 3, 4);
        logistica.agregarRuta(2, 3, 8);

        // Aplicar Dijkstra desde el centro de distribución A (id = 0)
        logistica.dijkstra(0);
    }
}

/*
Caso base:
Inicializar el array de distancias toma O(V), donde V es el número de centros.

Paso recursivo:
Cada vez que se extrae el vértice de menor distancia de la cola de prioridad, 
se relajan las aristas adyacentes, lo que toma O(log V) para la operación de extracción 
y O(E) para la relajación, donde E es el número de rutas.

Complejidad total:
El algoritmo de Dijkstra tiene una complejidad de O((V + E) log V), ya que se realizan 
operaciones sobre la cola de prioridad y se relajan aristas para cada vértice.
*/