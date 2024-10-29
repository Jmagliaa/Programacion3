public class Act3 {

    private static void colocarEquipos(char[][] oficina, int filaComputadora, int filaImpresora, int N) {
        if (filaComputadora == N && filaImpresora == N) {
            imprimirOficina(oficina);
            return;
        }

        for (int columnaComputadora = 0; columnaComputadora < N; columnaComputadora++) {
            if (esSeguro(oficina, filaComputadora, columnaComputadora, 'C', N)) {
                oficina[filaComputadora][columnaComputadora] = 'C';

                for (int columnaImpresora = 0; columnaImpresora < N; columnaImpresora++) {
                    if (esSeguro(oficina, filaImpresora, columnaImpresora, 'I', N)) {
                        oficina[filaImpresora][columnaImpresora] = 'I';

                        colocarEquipos(oficina, filaComputadora + 1, filaImpresora + 1, N);

                        oficina[filaImpresora][columnaImpresora] = '.';
                    }
                }
                oficina[filaComputadora][columnaComputadora] = '.';
            }
        }
    }

    private static boolean esSeguro(char[][] oficina, int fila, int columna, char tipo, int N) {
        for (int i = 0; i < N; i++) {
            if (oficina[fila][i] == tipo || oficina[i][columna] == tipo) {
                return false;
            }
        }
        return true;
    }

    private static void imprimirOficina(char[][] oficina) {
        for (char[] fila : oficina) {
            for (char espacio : fila) {
                System.out.print(espacio + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int N = 4;
        char[][] oficina = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                oficina[i][j] = '.';
            }
        }
        System.out.println("Posibles combinaciones de computadoras e impresoras en una oficina de 4x4:");
        colocarEquipos(oficina, 0, 0, N);
    }
}
