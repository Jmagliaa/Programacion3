import java.util.*;

class Item {
    private int peso;
    private int valor;

    public Item(int peso, int valor) {
        this.peso = peso;
        this.valor = valor;
    }

    public int getPeso() {
        return peso;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Peso: " + peso + ", Valor: " + valor;
    }
}

public class Actividad_2 {
    private List<Objeto> objetos;
    private int capacidadMaxima;

    // Constructor
    public Actividad_2(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.objetos = new ArrayList<>();
    }

    // Agregar objeto a la lista de objetos disponibles
    public void agregarObjeto(int peso, int valor) {
        Objeto nuevoObjeto = new Objeto(peso, valor);
        objetos.add(nuevoObjeto);
    }

    // Resolver el problema de la mochila con programación dinámica
    public void resolverMochila() {
        int n = objetos.size();
        int[][] dp = new int[n + 1][capacidadMaxima + 1];

        // Llenar la tabla de programación dinámica
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacidadMaxima; w++) {
                Objeto objetoActual = objetos.get(i - 1);
                if (objetoActual.getPeso() <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - objetoActual.getPeso()] + objetoActual.getValor());
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Imprimir el valor máximo que se puede obtener
        System.out.println("El valor máximo que se puede obtener es: " + dp[n][capacidadMaxima]);

        // Mostrar los objetos seleccionados
        mostrarObjetosSeleccionados(dp, n, capacidadMaxima);
    }

    // Mostrar los objetos seleccionados en la solución óptima
    private void mostrarObjetosSeleccionados(int[][] dp, int n, int capacidadMaxima) {
        System.out.println("Objetos seleccionados:");
        int w = capacidadMaxima;
        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                Objeto objetoSeleccionado = objetos.get(i - 1);
                System.out.println(objetoSeleccionado);
                w -= objetoSeleccionado.getPeso();
            }
        }
    }

    public static void main(String[] args) {
        // Capacidad máxima de la mochila para la Actividad 2
        Actividad_2 mochila = new Actividad_2(10);

        // Agregar objetos con peso y valor
        mochila.agregarObjeto(2, 4); // Objeto 1
        mochila.agregarObjeto(5, 2); // Objeto 2
        mochila.agregarObjeto(6, 1); // Objeto 3
        mochila.agregarObjeto(7, 6); // Objeto 4

        // Resolver el problema de la mochila
        mochila.resolverMochila();
    }
}

/*
Este código sigue la misma estructura que el ejemplo anterior para la *Actividad 2*:
1. Se representa cada objeto con su peso y valor mediante la clase Item.
2. La clase Actividad_2 maneja la lógica de agregar objetos y resolver el problema de la mochila mediante programación dinámica.
3. Se incluye un método para mostrar los objetos seleccionados que maximizan el valor dentro de la capacidad de la mochila.

Complejidad:
La complejidad de este algoritmo es O(n * capacidad), donde n es el número de objetos y capacidad es la capacidad máxima de la mochila.
*/