import java.util.Arrays;
import java.util.Comparator;

public class Actividad_4 {

    static class paquete {
        int costo;
        int ganancia;
        double promedio;

        public paquete(int peso, int valor) {
            this.costo = peso;
            this.ganancia = valor;
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

    public static int resolverGreedy(paquete[] paquetes, int capacidad){
        Arrays.sort(paquetes, Comparator.comparingDouble(j -> -j.promedio));

        int gananciaTotal = 0;
        int costoTotal = 0;

        for (paquete objeto : paquetes) {
            if (costoTotal + objeto.costo <= capacidad) {
                costoTotal += objeto.costo;
                gananciaTotal += objeto.ganancia;
            }
        }

        return gananciaTotal;
    }

    public static void main(String[] args) {

        int[] costo = {12, 20, 15, 25};
        int[] ganancias = {150 , 200, 100, 300};
        int presupuesto = 35;

        paquete[] paquetes = {
                new paquete(12, 150),
                new paquete(20, 200),
                new paquete(15,100),
                new paquete(25,300)
        };

        int resultado = resolverFuerzaBruta(costo, ganancias, costo.length, presupuesto, 0);
        System.out.println("Valor maximo con Fuerza Bruta: " + resultado);

        resultado = resolverDinamico(costo,ganancias, presupuesto);
        System.out.println("Valor maximo con Dinamico: " + resultado);

        resultado = resolverGreedy(paquetes, presupuesto);
        System.out.println("Valor maximo con Greedy: " + resultado);
    }
}

/*
Valor maximo con Fuerza Bruta: 350
Valor maximo con Dinamico: 350
Valor maximo con Greedy: 350

Complejidades:
Fuerza Bruta: O(2^n)
Dinámica: O(n.W) donde n es el número de paquetes de inversión y W el
presupuesto.
Greedy: O(n log n)
 */

