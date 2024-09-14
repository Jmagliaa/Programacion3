package Clase3;

import java.util.ArrayList;

public class Actividad_3 {
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
            return "\tId: " + id + "\n\tNombre: " + nombre + "\n\ttScoring: " + Scoring;
        }
    }

    private static Cliente[] dosScoringMaximo(ArrayList<Cliente> clientes){
        return dosScoringMaximo(clientes, 0, clientes.size());
    }

    private static Cliente[] dosScoringMaximo(ArrayList<Cliente> clientes, int inicio, int fin) {
        if (inicio == fin -1) {
            return new Cliente[]{clientes.get(inicio), null};
        }
        else if (inicio == fin - 2) {
            Cliente a = clientes.get(inicio);
            Cliente b = clientes.get(fin - 1);

            if (a.getScoring() > b.getScoring()) {
                return new Cliente[]{a, b};
            }
            else{
                return new Cliente[]{b, a};
            }
        }

        int mitad = (inicio + fin) / 2;

        Cliente[] izq = dosScoringMaximo(clientes, inicio, mitad);
        Cliente[] der = dosScoringMaximo(clientes, mitad, fin);

        Cliente[] mayores = new Cliente[2];

        if (izq[0].getScoring() > der[0].getScoring()) {
            mayores[0] = izq[0];

            if (izq[1] == null || der[0].getScoring() > izq[1].getScoring()) {
                mayores[1] = der[0];
            }
            else{
                mayores[1] = izq[1];
            }
        }
        else{
            mayores[0] = der[0];

            if (der[1] == null || izq[0].getScoring() > der[1].getScoring()) {
                mayores[1] = izq[0];
            }
            else{
                mayores[1] = der[1];
            }
        }

        return mayores;
    }

    public static void main(String[] args) {
        Actividad_3 actividad = new Actividad_3();
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(actividad.new Cliente(1, "Juan", 0.8));
        clientes.add(actividad.new Cliente(2, "Pedro", 0.9));
        clientes.add(actividad.new Cliente(3, "Maria", 0.7));
        clientes.add(actividad.new Cliente(4, "Ana", 0.6));
        clientes.add(actividad.new Cliente(5, "Luis", 0.5));
        Cliente[] clientesMax = dosScoringMaximo(clientes);
        System.out.println("El cliente con mayor scoring es:\n" + clientesMax[0]); // debería imprimir Pedro
        System.out.println("El segundo cliente con mayor scoring es:\n" + clientesMax[1]); // debería imprimir Juan
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
