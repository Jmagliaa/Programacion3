import java.util.*;

public class Actividad_3 {
    private static final int INF = 9999; // Valor infinito para representar rutas no existentes
    private int[][] tiemposViaje; // Matriz de tiempos de viaje entre centros de distribución
    private int numeroCentros;    // Número de centros de distribución
    private int[][] caminos;      // Matriz de caminos para recuperar rutas

    // Constructor
    public Actividad_3(int numeroCentros) {
        this.numeroCentros = numeroCentros;
        this.tiemposViaje = new int[numeroCentros][numeroCentros];
        this.caminos = new int[numeroCentros][numeroCentros];

        // Inicializar matrices de tiempos y caminos
        for (int i = 0; i < numeroCentros; i++) {
            Arrays.fill(tiemposViaje[i], INF);
            Arrays.fill(caminos[i], -1);  // Inicializar caminos intermedios con -1
            tiemposViaje[i][i] = 0;  // La distancia de un centro a sí mismo es 0
        }
    }

    // Agregar una ruta entre dos centros de distribución
    public void agregarRuta(int origen, int destino, int tiempo) {
        tiemposViaje[origen - 1][destino - 1] = tiempo;
        caminos[origen - 1][destino - 1] = origen - 1; // Inicializar el camino directo
    }

    // Aplicar el algoritmo de Floyd-Warshall para encontrar los tiempos mínimos de entrega
    public void optimizarRutas() {
        for (int k = 0; k < numeroCentros; k++) {
            for (int i = 0; i < numeroCentros; i++) {
                for (int j = 0; j < numeroCentros; j++) {
                    if (tiemposViaje[i][k] + tiemposViaje[k][j] < tiemposViaje[i][j]) {
                        tiemposViaje[i][j] = tiemposViaje[i][k] + tiemposViaje[k][j];
                        caminos[i][j] = caminos[k][j]; // Actualizar el camino intermedio
                    }
                }
            }
        }
    }

    // Recuperar el camino más corto entre dos centros de distribución
    public void mostrarCamino(int origen, int destino) {
        origen--; destino--;
        if (tiemposViaje[origen][destino] == INF) {
            System.out.println("No hay ruta posible de " + (origen + 1) + " a " + (destino + 1));
            return;
        }
        List<Integer> ruta = new ArrayList<>();
        ruta.add(destino + 1);
        while (caminos[origen][destino] != origen) {
            destino = caminos[origen][destino];
            ruta.add(destino + 1);
        }
        ruta.add(origen + 1);
        Collections.reverse(ruta);
        System.out.println("El camino más corto es: " + ruta);
    }

    // Verificar si existen ciclos negativos
    public void verificarCiclosNegativos() {
        for (int i = 0; i < numeroCentros; i++) {
            if (tiemposViaje[i][i] < 0) {
                System.out.println("Se ha detectado un ciclo negativo en el centro: " + (i + 1));
                return;
            }
        }
        System.out.println("No se han detectado ciclos negativos.");
    }

    public static void main(String[] args) {
        // Crear un escáner para obtener entradas del usuario
        Scanner scanner = new Scanner(System.in);

        // Número de centros de distribución
        Actividad_3 floydWarshall = new Actividad_3(4);

        // Agregar rutas entre centros de distribución con sus tiempos en minutos
        floydWarshall.agregarRuta(1, 2, 3);  // Centro 1 a Centro 2 con 3 minutos
        floydWarshall.agregarRuta(1, 3, 7);  // Centro 1 a Centro 3 con 7 minutos
        floydWarshall.agregarRuta(2, 3, 1);  // Centro 2 a Centro 3 con 1 minuto
        floydWarshall.agregarRuta(3, 4, 2);  // Centro 3 a Centro 4 con 2 minutos
        floydWarshall.agregarRuta(1, 4, 10); // Centro 1 a Centro 4 con 10 minutos

        // Optimizar rutas
        floydWarshall.optimizarRutas();

        // Verificar ciclos negativos
        floydWarshall.verificarCiclosNegativos();

        // Solicitar origen y destino al usuario
        System.out.print("Ingrese el centro de origen: ");
        int origen = scanner.nextInt();
        System.out.print("Ingrese el centro de destino: ");
        int destino = scanner.nextInt();

        // Mostrar el camino más corto entre los centros de distribución ingresados
        floydWarshall.mostrarCamino(origen, destino);
    }
}
