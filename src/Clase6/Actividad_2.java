public class Actividad_2 {

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

    public static void main(String[] args) {

        int[] peso = {2, 5, 6, 7};
        int[] valor = {4, 2, 1, 6};
        int capacidad = 10;

        int resultado = resolverFuerzaBruta(peso, valor, peso.length, capacidad, 0);
        System.out.println("Valor maximo con Fuerza Bruta: " + resultado);

        resultado = resolverDinamico(peso,valor, capacidad);
        System.out.println("Valor maximo con Dinamico: " + resultado);
    }
}
/*
Valor maximo con Fuerza Bruta: 10
Valor maximo con Dinamico: 10
*/
