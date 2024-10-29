import java.util.*;
public class Act3 {

    static class Almacen {
        private int id;
        private String nombre;

        public Almacen(int id, String nombre) {
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
            return nombre;
        }
    }


    static class Grafo {
        private final Map<Almacen, List<Almacen>> adyacencias = new HashMap<>();

        // Método para agregar un almacén al grafo
        public void agregarAlmacen(Almacen almacen) {
            adyacencias.putIfAbsent(almacen, new ArrayList<>());
        }

        // Método para conectar dos almacenes entre sí (crear una ruta directa)
        public void conectarAlmacenes(Almacen origen, Almacen destino) {
            adyacencias.getOrDefault(origen, new ArrayList<>()).add(destino);
            adyacencias.getOrDefault(destino, new ArrayList<>()).add(origen);
        }

        // Método DFS para realizar un recorrido en profundidad desde un almacén de inicio
        public void DFS(Almacen inicio) {
            Set<Almacen> visitado = new HashSet<>();
            System.out.println("Recorrido DFS desde el almacén " + inicio.getNombre() + ":");
            DFSRecursivo(inicio, visitado);
            System.out.println();
        }

        // Método recursivo para DFS
        private void DFSRecursivo(Almacen actual, Set<Almacen> visitado) {
            visitado.add(actual);
            System.out.print(actual + " -> ");

            for (Almacen vecino : adyacencias.getOrDefault(actual, new ArrayList<>())) {
                if (!visitado.contains(vecino)) {
                    DFSRecursivo(vecino, visitado);
                }
            }
        }

        // Método BFS para realizar un recorrido en anchura desde un almacén de inicio
        public void BFS(Almacen inicio) {
            Set<Almacen> visitado = new HashSet<>();
            Queue<Almacen> cola = new LinkedList<>();

            visitado.add(inicio);
            cola.add(inicio);

            System.out.println("Recorrido BFS desde el almacén " + inicio.getNombre() + ":");

            while (!cola.isEmpty()) {
                Almacen actual = cola.poll();
                System.out.print(actual + " -> ");

                for (Almacen vecino : adyacencias.getOrDefault(actual, new ArrayList<>())) {
                    if (!visitado.contains(vecino)) {
                        visitado.add(vecino);
                        cola.add(vecino);
                    }
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Grafo redAlmacenes = new Grafo();


        Almacen almacen1 = new Almacen(1, "Almacen A");
        Almacen almacen2 = new Almacen(2, "Almacen B");
        Almacen almacen3 = new Almacen(3, "Almacen C");
        Almacen almacen4 = new Almacen(4, "Almacen D");
        Almacen almacen5 = new Almacen(5, "Almacen E");


        redAlmacenes.agregarAlmacen(almacen1);
        redAlmacenes.agregarAlmacen(almacen2);
        redAlmacenes.agregarAlmacen(almacen3);
        redAlmacenes.agregarAlmacen(almacen4);
        redAlmacenes.agregarAlmacen(almacen5);


        redAlmacenes.conectarAlmacenes(almacen1, almacen2);
        redAlmacenes.conectarAlmacenes(almacen1, almacen3);
        redAlmacenes.conectarAlmacenes(almacen2, almacen4);
        redAlmacenes.conectarAlmacenes(almacen3, almacen5);
        redAlmacenes.conectarAlmacenes(almacen4, almacen5);


        redAlmacenes.DFS(almacen1);
        redAlmacenes.BFS(almacen1);
    }
}
