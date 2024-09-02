package Clase3;

import java.util.ArrayList;

public class Actividad_1 {
    class Cliente{
        private int id;
        private String nombre;
        private double Scoring;

        // constructor
        public Cliente(int id, String nombre, double scoring) {
            this.id = id;
            this.nombre = nombre;
            Scoring = scoring;
        }

        // getters
        public int getId() {
            return id;
        }
        public String getNombre() {
            return nombre;
        }
        public double getScoring() {
            return Scoring;
        }

        @Override
        public String toString() {
            return "Id: " + id + "\nNombre: " + nombre + "\nScoring: " + Scoring;
        }
    }

    private static Cliente scoringMaximo(ArrayList<Cliente> clientes){
        return scoringMaximo(clientes, 0, clientes.size());
    }

    private static Cliente scoringMaximo(ArrayList<Cliente> clientes, int inicio, int fin) {
        if (inicio == fin -1) {
            return clientes.get(inicio);
        }

        int mitad = (inicio + fin) / 2;
        Cliente izq = scoringMaximo(clientes, inicio, mitad);
        Cliente der = scoringMaximo(clientes, mitad, fin);

        if (izq.getScoring() > der.getScoring()) {
            return izq;
        }
        else{
            return der;
        }
    }

    public static void main(String[] args) {
        Actividad_1 actividad = new Actividad_1();
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(actividad.new Cliente(1, "Juan", 0.8));
        clientes.add(actividad.new Cliente(2, "Pedro", 0.9));
        clientes.add(actividad.new Cliente(3, "Maria", 0.7));
        clientes.add(actividad.new Cliente(4, "Ana", 0.6));
        clientes.add(actividad.new Cliente(5, "Luis", 0.5));

        Cliente clienteMax = scoringMaximo(clientes);
        System.out.println("El cliente con mayor scoring es:\n" + clienteMax); // debería imprimir Pedro
    }
}

/*
Caso base:
Si la lista tiene un solo elemento (inicio == fin - 1), el tiempo de ejecución es constante O(1).

Paso recursivo:
Para dividir la lista en dos mitades, se realiza una llamada recursiva sobre cada mitad. 
Esto se hace repetidamente hasta que solo quede un elemento en cada sublista. 
Como se divide la lista en dos en cada paso, hay log(n) niveles en el árbol de recursión.

Para combinar los resultados, se compara un elemento de cada mitad, lo que toma tiempo O(1) por nivel de la recursión. 
Aunque hay log(n) niveles, el número total de comparaciones a lo largo de todos los niveles es proporcional al número de elementos, 
ya que cada elemento es comparado al menos una vez.

Complejidad total:
Sumando los tiempos de todas las operaciones, la complejidad es O(n).
*/
