package Clase1.Actividad_1B;

import java.util.ArrayList;

public class SistemaSinMaps {
    private ArrayList<Cliente> clientes;
    private ArrayList<Factura> facturas;

    public SistemaSinMaps(ArrayList<Cliente> clientes, ArrayList<Factura> facturas) {
        this.clientes = clientes;
        this.facturas = facturas;
    }

    public ArrayList<ResultadoDTO> getResultados() {
        ArrayList<ResultadoDTO> resultados = new ArrayList<>();             // 1

        for (Cliente cliente : clientes) {                                  // 1n
            int idCliente = cliente.getIdCliente();                         // 2n
            double sumaImportes = 0;                                        // 1n

            for (Factura factura : facturas){                               // 1n * 1n
                if (factura.getIdCliente() == idCliente) {                  // 1n * 2n
                    sumaImportes += factura.getImporte();                   // 1n * 2n
                }
            }

            resultados.add(new ResultadoDTO(idCliente, sumaImportes));      // 1
        }

        return resultados;                                                  // 1
    }                                                                       // f(n) = 3 + 4n + 5n^2             tiene un costo cuadratico

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

        SistemaSinMaps sistema = new SistemaSinMaps(clientes, facturas);
        ArrayList<ResultadoDTO> resultados = sistema.getResultados();

        // Imprimir resultados
        for (ResultadoDTO resultado : resultados) {
            System.out.println("Cliente ID: " + resultado.getIdCliente() + ", Total Facturas: " + resultado.getTotalFacturas());
        }
    }
}
