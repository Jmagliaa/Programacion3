package Clase1;

public class Actividad_1A{

    public int calcularMaximo (int[] array){                       
        int maximo = array[0];                      // 2

        for(int i = 1; i < array.length; i++) {     // 3 + 3n
            if (array[i] > maximo) {                // 2n
                maximo = array[i];                  // 2n
            }
        }

        return maximo;                              // 1                                              
    }                                               // f(n) = 5 + 7n        tiene un costo lineal
    
    public static void main(String[] args) {
        Actividad_1A prueba = new Actividad_1A();
        System.out.println(prueba.calcularMaximo (new int[]{80,-32,41,1,0,12,14}));
    }
}
