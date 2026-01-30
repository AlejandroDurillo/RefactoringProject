import java.util.List;

public class Pedido {
    private static final double IVA = 0.21;
    private static final double DESCUENTO = 0.05;

    private Cliente cliente;
    private List<Producto> productos;

    public Pedido(Cliente cliente, List<Producto> productos) {
        this.cliente = cliente;
        this.productos = productos;
    }

    public double calcularTotalNeto() {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }

        if (total > 3000) {
            total *= (1 - DESCUENTO);
        }
        return total;
    }

    public double calcularTotalConIVA() {
        double neto = calcularTotalNeto();
        return neto + (neto * IVA);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
