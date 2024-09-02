package Clase1;

public class Actividad_3 {
    public static int sumarNumeros(int n){
        if (n == 0) {
            return 0;
        }
        return (sumarNumeros(n-1) + n);
    }

    public static void main(String[] args) {
        int num = 10;
        System.out.println("La suma de los numeros anteriores a " + num + " da un total de: " + sumarNumeros(num));
    }
}

/*
T(n) = T(n−1)+O(1)
T(n) = (T(n−2)+O(1))+O(1)
T(n) = T(n−2)+2⋅O(1)
T(n) = (T(n−3)+O(1))+2⋅O(1)
T(n) = T(n−3)+3⋅O(1)

T(n) = T(0)+n⋅O(1) 

Sabemos que: T(0) = O(1)    por lo que: T(n) = O(n)
Entonces podemos decir que el caso base se resuelve en tiempo constante, incrementa linealmente en funcion al tamaño de entrada "n" 
*/