package Clase4;

import java.util.Arrays;

public class Actividad_1 {
    private static boolean tieneCambio(int[] monedas, int monto) {
        Arrays.sort(monedas);                                       // nlog(n)                                
        String monedasUsadas = "";                                  // 1             

        for (int i = monedas.length - 1; i >= 0; i--) {             // 1 + 2n + 2n 
            if (monto >= monedas[i]) {                              // 2n
                monto -= monedas[i];                                // 2n  
                monedasUsadas += monedas[i] + " ";                  // 2n
            }
            if (monto == 0) {                                       // n
                System.out.println("Cambio: " + monedasUsadas);     // 1
                return true;                                        // 1
            }
        }
        return false;                                               // 1
    }                                                               // f(n) = 5 + 11n + 2nlog(n) tiene un costo lineal

    public static void main(String[] args) {
        int[] monedas = {100, 50, 50, 20, 10, 10, 5};
        int monto = 125;
        System.out.println(tieneCambio(monedas,monto));
    }
}
