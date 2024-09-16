package Clase4;

import java.util.ArrayList;
import java.util.Arrays;

public class Actividad_3 {

    static class Camion {
        double value;
        double weight;
        double ratio;

        Camion(double value, double weight) {
            this.value = value;
            this.weight = weight;
            this.ratio = value / weight;
        }

        @Override
        public String toString() {
            return String.format("Valor: " + this.value + ", Peso: " + this.weight + ", Ratio: " + this.ratio);
        }
    }

    // metodo para resolver el problema de la mochila fraccionaria
    public static ArrayList<Camion> fractional(int capacity, Camion[] camiones) {
        ArrayList<Camion> camionesCargados = new ArrayList<>();

        // ordenamos los objetos por la relación valor/peso en orden descendente
        Arrays.sort(camiones, (a, b) -> Double.compare(b.ratio, a.ratio));

        double maxValue = 0.0;

        for (Camion camion : camiones) {
            if (capacity == 0) break; // si la mochila esta llena, terminamos

            if (camion.weight <= capacity) {    // si el objeto cabe completo en la mochila, lo agregamos completo
                maxValue += camion.value;
                capacity -= camion.weight;
                camionesCargados.add(camion);
            } 
            else {  // si el objeto no cabe completo en la mochila, lo agregamos fraccionado
                maxValue += camion.value * (capacity / camion.weight); 
                capacity = 0; 
                camionesCargados.add(new Camion(camion.value * (capacity / camion.weight), capacity));
            }
        }

        System.out.println("Valor total: " + maxValue);
        return camionesCargados;
    }

    public static void main(String[] args) {
        Camion[] camiones = {
            new Camion(30, 10),
            new Camion(50, 20),
            new Camion(60, 30)
        };

        int capacity = 50; 

        ArrayList<Camion> result = fractional(capacity, camiones);
        System.out.println("Artículos cargados:");
        for (Camion camion : result) {
            System.out.println(camion);
        }
    }
}

