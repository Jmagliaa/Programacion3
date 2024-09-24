import java.util.*;

class PaqueteInversion {
    private int costo;
    private int ganancia;

    public PaqueteInversion(int costo, int ganancia) {
        this.costo = costo;
        this.ganancia = ganancia;
    }

    public int getCosto() {
        return costo;
    }

    public int getGanancia() {
        return ganancia;
    }

    @Override
    public String toString() {
        return "Costo: " + costo + ", Ganancia: " + ganancia;
    }
}

public class Actividad_4 {
    private List<PaqueteInversion> paquetes;
    private int presupuestoMaximo;

    // Constructor
    public Actividad_4(int presupuestoMaximo) {
        this.presupuestoMaximo = presupuestoMaximo;
        this.paquetes = new ArrayList<>();
    }

    // Agregar paquete a la lista
    public void agregarPaquete(int costo, int ganancia) {
        PaqueteInversion nuevoPaquete = new PaqueteInversion(costo, ganancia);
        paquetes.add(nuevoPaquete);
    }

    // Resolver el problema de selección de paquetes de inversión con programación dinámica
    public void resolverPaquetes() {
        int n = paquetes.size();
        int[][] dp = new int[n + 1][presupuestoMaximo + 1];

        // Llenar la tabla de programación dinámica
        for (int i = 1; i <= n; i++) {
            for (int p = 0; p <= presupuestoMaximo; p++) {
                PaqueteInversion paqueteActual = paquetes.get(i - 1);
                if (paqueteActual.getCosto() <= p) {
                    dp[i][p] = Math.max(dp[i - 1][p], dp[i - 1][p - paqueteActual.getCosto()] + paqueteActual.getGanancia());
                } else {
                    dp[i][p] = dp[i - 1][p];
                }
            }
        }

        // Imprimir la ganancia máxima que se puede obtener
        System.out.println("La ganancia máxima que se puede obtener es: " + dp[n][presupuestoMaximo]);

        // Mostrar los paquetes seleccionados
        mostrarPaquetesSeleccionados(dp, n, presupuestoMaximo);
    }

    // Mostrar los paquetes seleccionados en la solución óptima
    private void mostrarPaquetesSeleccionados(int[][] dp, int n, int presupuestoMaximo) {
        System.out.println("Paquetes seleccionados:");
        int p = presupuestoMaximo;
        for (int i = n; i > 0; i--) {
            if (dp[i][p] != dp[i - 1][p]) {
                PaqueteInversion paqueteSeleccionado = paquetes.get(i - 1);
                System.out.println(paqueteSeleccionado);
                p -= paqueteSeleccionado.getCosto();
            }
        }
    }

    public static void main(String[] args) {
        // Presupuesto máximo para la Actividad 4
        Actividad_4 seleccionPaquetes = new Actividad_4(35);

        // Agregar paquetes de inversión con costo y ganancia
        seleccionPaquetes.agregarPaquete(12, 150); // Paquete 1
        seleccionPaquetes.agregarPaquete(20, 200); // Paquete 2
        seleccionPaquetes.agregarPaquete(15, 100); // Paquete 3
        seleccionPaquetes.agregarPaquete(25, 300); // Paquete 4

        // Resolver el problema de selección de paquetes de inversión
        seleccionPaquetes.resolverPaquetes();
    }
}

/*
Este código sigue la estructura de los ejemplos anteriores y aplica programación dinámica para seleccionar los paquetes de inversión:
1. Se modela cada paquete con un costo y una ganancia mediante la clase PaqueteInversion.
2. La clase actividad4 se encarga de gestionar la lista de paquetes y resolver el problema de optimización para seleccionar los paquetes que maximizan las ganancias sin exceder el presupuesto.
3. La tabla de programación dinámica (array dp) se utiliza para calcular la ganancia máxima posible y se imprime la combinación óptima de paquetes seleccionados.

Complejidad:
La complejidad del algoritmo es O(n * presupuesto), donde n es el número de paquetes y presupuesto es el presupuesto máximo disponible.
*/