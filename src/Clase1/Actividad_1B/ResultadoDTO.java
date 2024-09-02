package Clase1.Actividad_1B;

public class ResultadoDTO {
    private int idCliente;
    private double totalFacturas;

    public ResultadoDTO(int idCliente, double totalFacturas) {
        this.idCliente = idCliente;
        this.totalFacturas = totalFacturas;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getTotalFacturas() {
        return totalFacturas;
    }

    public void setTotalFacturas(double totalFacturas) {
        this.totalFacturas = totalFacturas;
    }

    
}
