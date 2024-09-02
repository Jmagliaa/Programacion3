package Clase1.Actividad_1B;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SistemaConMaps {
    private ArrayList<Factura> facturas;

    public SistemaConMaps(ArrayList<Cliente> clientes, ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }

    public ArrayList<ResultadoDTO> getResultados() {
        Map<Integer, Double> sumaPorCliente = new HashMap<>();                                                  // 1

        // asignamos los valores a los clientes
        for (Factura factura : facturas) {                                                                      // 1n
            sumaPorCliente.put(factura.getIdCliente(),                                                          // 2n
            sumaPorCliente.getOrDefault(factura.getIdCliente(), 0.0) + factura.getImporte());      // 5n
        }

        // creamos el resultado
        ArrayList<ResultadoDTO> resultados = new ArrayList<>();                                                 // 1
        for (Map.Entry<Integer, Double> entry : sumaPorCliente.entrySet()) {                                    // 2n
            resultados.add(new ResultadoDTO(entry.getKey(), entry.getValue()));                                 // 3n
        }

        return resultados;                                                                                      // 1
    }                                                                                                           // f(n) = 3 + 13n        tiene un costo lineal

    public static void main(String[] args) {
        // Crear listas de clientes y facturas para probar
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(1, "Cliente A"));
        clientes.add(new Cliente(2, "Cliente B"));
        clientes.add(new Cliente(3, "Cliente C"));

        ArrayList<Factura> facturas = new ArrayList<>();
        facturas.add(new Factura(1, 1, 100.0));
        facturas.add(new Factura(2, 1, 150.0));
        facturas.add(new Factura(3, 2, 200.0));
        facturas.add(new Factura(4, 3, 300.0));
        facturas.add(new Factura(5, 2, 50.0));

        SistemaConMaps sistema = new SistemaConMaps(clientes, facturas);
        ArrayList<ResultadoDTO> resultados = sistema.getResultados();

        // Imprimir resultados
        for (ResultadoDTO resultado : resultados) {
            System.out.println("Cliente ID: " + resultado.getIdCliente() + ", Total Facturas: " + resultado.getTotalFacturas());
        }
    }
}
