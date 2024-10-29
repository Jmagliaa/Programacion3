public class Act2 {

    private static void colocarEscritoriosYSillas(char[][] habitacion, int filaEscritorio, int filaSilla, int N) {
        if (filaEscritorio == N && filaSilla == N) {
            imprimirHabitacion(habitacion);
            return;
        }

        for (int columnaEscritorio = 0; columnaEscritorio < N; columnaEscritorio++) {
            if (esSeguro(habitacion, filaEscritorio, columnaEscritorio, 'E', N)) {
                habitacion[filaEscritorio][columnaEscritorio] = 'E';

                for (int columnaSilla = 0; columnaSilla < N; columnaSilla++) {
                    if (esSeguro(habitacion, filaSilla, columnaSilla, 'S', N)) {
                        habitacion[filaSilla][columnaSilla] = 'S';

                        colocarEscritoriosYSillas(habitacion, filaEscritorio + 1, filaSilla + 1, N);

                        habitacion[filaSilla][columnaSilla] = '.';
                    }
                }
                habitacion[filaEscritorio][columnaEscritorio] = '.';
            }
        }
    }

    private static boolean esSeguro(char[][] habitacion, int fila, int columna, char tipo, int N) {
        for (int i = 0; i < N; i++) {
            if (habitacion[fila][i] == tipo || habitacion[i][columna] == tipo) {
                return false;
            }
        }
        return true;
    }

    private static void imprimirHabitacion(char[][] habitacion) {
        for (char[] fila : habitacion) {
            for (char espacio : fila) {
                System.out.print(espacio + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int N = 4;
        char[][] habitacion = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                habitacion[i][j] = '.';
            }
        }
        System.out.println("Posibles combinaciones de escritorios y sillas en una habitación de 4x4:");
        colocarEscritoriosYSillas(habitacion, 0, 0, N);
    }
}
