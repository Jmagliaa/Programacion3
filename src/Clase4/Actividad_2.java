package Clase4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Actividad_2 {
    static class cambio{
        private List<Integer> monedas;
        private int cantidad;


        public cambio(){
            this.monedas = new ArrayList<>();
            this.cantidad = 0;
        }

        public void agregarMonedas(int moneda, int cantidad) {
            for (int i = 0; i < cantidad; i++) {
                this.monedas.add(moneda);  // Añadir la moneda a la lista
            }
            this.cantidad += cantidad;  // Actualizar la cantidad total de monedas
        }

        public String toString(){
            return "Moneda: " + monedas + " Cantidad: " + cantidad;
        }
    }

    private static cambio minimoMonedasCambio(int[] comprobantes, int monto) {
        Arrays.sort(comprobantes);                                              // nlog(n)                                
        cambio monedasUsadas = new cambio();                                      // 1          

        for (int i = comprobantes.length - 1; i >= 0; i--) {                    // 1 + 2n + 2n 
            if (monto >= comprobantes[i]) {                                     // 2n
                int cantidadMonedas = monto / comprobantes[i];                  // 2n
                monedasUsadas.agregarMonedas(comprobantes[i], cantidadMonedas); // 2n
                monto -= comprobantes[i] * cantidadMonedas;                     // 2n
            }
            if (monto == 0) {                                                   // n
                return monedasUsadas;                                           // 1
            }
        }
        return null;                                                            // 1
    }                                                                           // f(n) = 4 + 14n + 2nlog(n) tiene un costo lineal

    public static void main(String[] args) {
        int[] comprobantes = {100, 50, 20, 10, 5};
        int monto = 225;
        cambio resultado = minimoMonedasCambio(comprobantes, monto);
        System.out.println("Se utilizaron " + resultado.cantidad + " monedas: " + resultado.monedas ); // deberia mostrar 4 monedas: [100, 100, 20, 5]
    }
}
