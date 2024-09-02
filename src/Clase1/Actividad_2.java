package Clase1;

import java.math.BigInteger;

public class Actividad_2 {
    public static int factorialInt(int n) {
        if (n == 0) { 
            return 1;
        } 
        else { 
            return n * factorialInt(n - 1);
        }
    }
    
    public static long factorialLong(long n) {
        if (n == 0) {
            return 1;
        } 
        else {
            return n * factorialLong(n - 1);
        }
    }

    public static BigInteger factorialBigInteger(BigInteger n) {
        if (n.equals(BigInteger.ZERO)) { 
            return BigInteger.ONE;
        } 
        else { 
            return n.multiply(factorialBigInteger(n.subtract(BigInteger.ONE)));
        }
    }

    public static void main(String[] args) {
        // Prueba con int
        int intN = 50; 
        int intResultado = factorialInt(intN);
        System.out.println("Factorial (int) de " + intN + " es " + intResultado);

        // Prueba con long
        long longN = 50; 
        long longResultado = factorialLong(longN);
        System.out.println("Factorial (long) de " + longN + " es " + longResultado);

        // Prueba con BigInteger
        int bigN = 50;
        BigInteger bigResultado = factorialBigInteger(BigInteger.valueOf(bigN));
        System.out.println("Factorial (BigInteger) de " + bigN + " es " + bigResultado);
    }
}
