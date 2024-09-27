import java.util.*;

class Nodo {
    private int origen;
    private int destino;
    private int peso;

    public Nodo(int origen, int destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public int getOrigen() {
        return origen;
    }

    public int getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return "Origen: " + origen + ", Destino: " + destino + ", Peso: " + peso;
    }
}

public class Actividad_1 {
    private static final int INF = 9999; // Valor infinito para representar la ausencia de conexión
    private int[][] distancias;
    private int numeroNodos;

    // Constructor
    public Actividad_1(int numeroNodos) {
        this.numeroNodos = numeroNodos;
        this.distancias = new int[numeroNodos][numeroNodos];

        // Inicializar la matriz de distancias con infinito
        for (int i = 0; i < numeroNodos; i++) {
            Arrays.fill(distancias[i], INF);
            distancias[i][i] = 0; // La distancia a sí mismo es 0
        }
    }

    // Agregar una conexión entre nodos
    public void agregarConexion(int origen, int destino, int peso) {
        distancias[origen - 1][destino - 1] = peso;
    }

    // Aplicar el algoritmo de Floyd-Warshall
    public void resolverFloydWarshall() {
        for (int k = 0; k < numeroNodos; k++) {
            for (int i = 0; i < numeroNodos; i++) {
                for (int j = 0; j < numeroNodos; j++) {
                    if (distancias[i][j] > distancias[i][k] + distancias[k][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                    }
                }
            }
        }

        // Imprimir la matriz final de distancias mínimas
        imprimirSolucion();
    }

    // Mostrar la matriz de distancias mínimas
    private void imprimirSolucion() {
        System.out.println("Matriz de distancias mínimas entre todos los pares de nodos:");
        for (int i = 0; i < numeroNodos; i++) {
            for (int j = 0; j < numeroNodos; j++) {
                if (distancias[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(distancias[i][j] + "   ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Número de nodos en el grafo
        Actividad_1 floydWarshall = new Actividad_1(4);

        // Agregar aristas con sus respectivos pesos
        floydWarshall.agregarConexion(1, 2, 2); // Nodo 1 a Nodo 2 con peso 2
        floydWarshall.agregarConexion(2, 4, 4); // Nodo 2 a Nodo 4 con peso 4
        floydWarshall.agregarConexion(1, 4, 5); // Nodo 1 a Nodo 4 con peso 5
        floydWarshall.agregarConexion(4, 3, 2); // Nodo 4 a Nodo 3 con peso 2

        // Resolver el problema con Floyd-Warshall
        floydWarshall.resolverFloydWarshall();
    }
}
