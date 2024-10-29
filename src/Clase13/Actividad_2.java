public class Act2 {
    private int[][] tablero;

    public Sudoku6x6(int[][] tableroInicial) {
        this.tablero = tableroInicial;
    }

    public void mostrarTablero() {
        for (int fila = 0; fila < 6; fila++) {
            if (fila % 2 == 0 && fila != 0) {
                System.out.println("---------------------");
            }
            for (int col = 0; col < 6; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print(" | ");
                }
                System.out.print(tablero[fila][col] + " ");
            }
            System.out.println();
        }
    }

    public void ingresarValor(int fila, int col, int valor) {
        if (fila >= 0 && fila < 6 && col >= 0 && col < 6 && valor >= 1 && valor <= 6) {
            tablero[fila][col] = valor;
        } else {
            System.out.println("Coordenadas o valor fuera de rango");
        }
    }

    public static void main(String[] args) {
        int[][] tableroInicial = {
                {5, 3, 0, 0, 0, 0},
                {6, 0, 0, 1, 2, 0},
                {0, 0, 2, 0, 0, 0},
                {0, 5, 0, 0, 0, 3},
                {0, 0, 0, 4, 5, 0},
                {0, 0, 0, 0, 6, 0}
        };

        Sudoku6x6 sudoku = new Sudoku6x6(tableroInicial);
        sudoku.mostrarTablero();

        // Ejemplo de cómo ingresar un valor
        sudoku.ingresarValor(0, 2, 4);
        System.out.println("\nTablero después de ingresar un valor:");
        sudoku.mostrarTablero();
    }
}
