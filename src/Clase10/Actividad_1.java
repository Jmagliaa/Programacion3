public class Act1 {

    private static void colocarReinas(int[][] tablero, int fila, int columna, int reinasColocadas, int N) {
        if (reinasColocadas == 2) {
            imprimirTablero(tablero);
            return;
        }

        for (int i = fila; i < N; i++) {
            for (int j = (i == fila ? columna : 0); j < N; j++) {

                if (esSeguro(tablero, i, j, N)) {
                    tablero[i][j] = 1;
                    colocarReinas(tablero, i, j + 1, reinasColocadas + 1, N);
                    tablero[i][j] = 0;
                }
            }
        }
    }

    private static boolean esSeguro(int[][] tablero, int fila, int columna, int N) {
        for (int i = 0; i < N; i++) {
            if (tablero[i][columna] == 1) return false;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tablero[i][j] == 1) {
                    if (Math.abs(fila - i) == Math.abs(columna - j)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static void imprimirTablero(int[][] tablero) {
        for (int[] fila : tablero) {
            for (int casilla : fila) {
                System.out.print((casilla == 1 ? "R " : ". "));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int N = 4;
        int[][] tablero = new int[N][N];
        System.out.println("Posiciones válidas para dos reinas en un tablero de 4x4:");
        colocarReinas(tablero, 0, 0, 0, N);
    }
}
