import java.util.*;

class Proyecto {
    private int costo;
    private int beneficio;

    public Proyecto(int costo, int beneficio) {
        this.costo = costo;
        this.beneficio = beneficio;
    }

    public int getCosto() {
        return costo;
    }

    public int getBeneficio() {
        return beneficio;
    }

    @Override
    public String toString() {
        return "Costo: " + costo + ", Beneficio: " + beneficio;
    }
}

public class Actividad_3 {
    private List<Proyecto> proyectos;
    private int presupuestoMaximo;

    // Constructor
    public Actividad_3(int presupuestoMaximo) {
        this.presupuestoMaximo = presupuestoMaximo;
        this.proyectos = new ArrayList<>();
    }

    // Agregar proyecto a la lista
    public void agregarProyecto(int costo, int beneficio) {
        Proyecto nuevoProyecto = new Proyecto(costo, beneficio);
        proyectos.add(nuevoProyecto);
    }

    // Resolver el problema de selección de proyectos con programación dinámica
    public void resolverProyectos() {
        int n = proyectos.size();
        int[][] dp = new int[n + 1][presupuestoMaximo + 1];

        // Llenar la tabla de programación dinámica
        for (int i = 1; i <= n; i++) {
            for (int p = 0; p <= presupuestoMaximo; p++) {
                Proyecto proyectoActual = proyectos.get(i - 1);
                if (proyectoActual.getCosto() <= p) {
                    dp[i][p] = Math.max(dp[i - 1][p], dp[i - 1][p - proyectoActual.getCosto()] + proyectoActual.getBeneficio());
                } else {
                    dp[i][p] = dp[i - 1][p];
                }
            }
        }

        // Imprimir el beneficio máximo que se puede obtener
        System.out.println("El beneficio máximo que se puede obtener es: " + dp[n][presupuestoMaximo]);

        // Mostrar los proyectos seleccionados
        mostrarProyectosSeleccionados(dp, n, presupuestoMaximo);
    }

    // Mostrar los proyectos seleccionados en la solución óptima
    private void mostrarProyectosSeleccionados(int[][] dp, int n, int presupuestoMaximo) {
        System.out.println("Proyectos seleccionados:");
        int p = presupuestoMaximo;
        for (int i = n; i > 0; i--) {
            if (dp[i][p] != dp[i - 1][p]) {
                Proyecto proyectoSeleccionado = proyectos.get(i - 1);
                System.out.println(proyectoSeleccionado);
                p -= proyectoSeleccionado.getCosto();
            }
        }
    }

    public static void main(String[] args) {
        // Presupuesto máximo para la Actividad 3
        Actividad_3 seleccionProyectos = new Actividad_3(40);

        // Agregar proyectos con costo y beneficio
        seleccionProyectos.agregarProyecto(10, 100); // Proyecto 1
        seleccionProyectos.agregarProyecto(15, 200); // Proyecto 2
        seleccionProyectos.agregarProyecto(20, 150); // Proyecto 3
        seleccionProyectos.agregarProyecto(25, 300); // Proyecto 4

        // Resolver el problema de selección de proyectos
        seleccionProyectos.resolverProyectos();
    }
}

/*
Este código sigue la estructura de los ejemplos anteriores y aplica programación dinámica para seleccionar proyectos.
1. Se modela cada proyecto con un costo y un beneficio mediante la clase Proyecto.
2. La clase actividad3 se encarga de gestionar la lista de proyectos y resolver el problema de optimización para seleccionar los proyectos que maximizan el beneficio sin exceder el presupuesto.
3. La tabla de programación dinámica (array dp) se utiliza para calcular el beneficio máximo posible y se imprime la combinación óptima de proyectos seleccionados.

Complejidad:
La complejidad del algoritmo es O(n * presupuesto), donde n es el número de proyectos y presupuesto es el presupuesto máximo disponible.
*/