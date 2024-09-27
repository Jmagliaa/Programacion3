import java.util.Arrays;
import java.util.Comparator;

public class Actividad_3 {

    static class Proyecto {
        int costo;
        int beneficio;
        double promedio;

        public Proyecto(int peso, int valor) {
            this.costo = peso;
            this.beneficio = valor;
            this.promedio = (double) valor/peso;
        }
    }

    static int maxValor = 0;

    public static int resolverFuerzaBruta(int[] pesos, int[] valores, int indice, int capacidad, int valorActual) {

        if (indice == 0 || capacidad == 0) {
            if (valorActual > maxValor) {
                maxValor = valorActual;
            }
            return maxValor;
        }

        if (pesos[indice - 1] <= capacidad) {
            resolverFuerzaBruta(pesos, valores, indice - 1, capacidad - pesos[indice - 1], valorActual + valores[indice - 1]);
        }
        resolverFuerzaBruta(pesos, valores, indice - 1, capacidad, valorActual);

        return maxValor;
    }

    public static int resolverDinamico(int[] pesos, int[] valores, int capacidad) {
        int indice = valores.length;
        int[][] dp = new int[indice + 1][capacidad + 1];

        for (int i = 1; i <= indice; i++) {
            for (int j = 0; j <= capacidad; j++) {
                if (pesos[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - pesos[i - 1]] + valores[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[indice][capacidad];
    }

    public static int resolverGreedy(Proyecto[] objs, int capacidad){
        Arrays.sort(objs, Comparator.comparingDouble(j -> -j.promedio));

        int beneficioTotal = 0;
        int costoTotal = 0;

        for (Proyecto objeto : objs) {
            if (costoTotal + objeto.costo <= capacidad) {
                costoTotal += objeto.costo;
                beneficioTotal += objeto.beneficio;
            }
        }

        return beneficioTotal;
    }

    public static void main(String[] args) {

        int[] costo = {10, 15, 20, 25};
        int[] beneficio = {100 , 200, 150, 300};
        int presupuesto = 40;

        Proyecto[] proyectos = {
                new Proyecto(12, 150),
                new Proyecto(20, 200),
                new Proyecto(15,100),
                new Proyecto(25,300)
        };

        int resultado = resolverFuerzaBruta(costo, beneficio, costo.length, presupuesto, 0);
        System.out.println("Valor maximo con Fuerza Bruta: " + resultado);

        resultado = resolverDinamico(costo,beneficio, presupuesto);
        System.out.println("Valor maximo con Dinamico: " + resultado);

        resultado = resolverGreedy(proyectos, presupuesto);
        System.out.println("Valor maximo con Greedy: " + resultado);
    }
}
/*
Valor maximo con Fuerza Bruta: 500
Valor maximo con Dinamico: 500
Valor maximo con Greedy: 450

Complejidades:
Fuerza Bruta: O(2^n)
Dinámica: O(n.W) donde n es el número de proyectos y W el presupuesto.
Greedy: O(n log n)
*/

