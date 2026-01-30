import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MainSistemaPedidos {

  
    private static final double IVA = 0.21;
    private static final double DESCUENTO = 0.05;
    private static final double LIMITE_DESCUENTO = 3000.0;

    public static void main(String[] args) {

        Cliente cliente1 = new Cliente(
                "TechSolutions SL",
                "B12345678",
                "Calle Industria 55, Madrid"
        );

        Pedido pedido1 = new Pedido(cliente1, List.of(
                new Producto("Servidor Dell PowerEdge", 2500.00),
                new Producto("Licencia Windows Server", 800.00),
                new Producto("Cableado Estructurado", 250.50)
        ));

        procesarPedido(pedido1);

        Cliente cliente2 = new Cliente(
                "Libreria Moderna",
                "A98765432",
                "Av. Diagonal 200, Barcelona"
        );

        Pedido pedido2 = new Pedido(cliente2, List.of(
                new Producto("Pack Libros Escolares", 1200.00),
                new Producto("Estantería Metálica", 300.00)
        ));

        procesarPedido(pedido2);
    }


    private static void procesarPedido(Pedido pedido) {
        imprimirFactura(pedido);

        double subtotal = calcularSubtotal(pedido);
        double totalConDescuento = aplicarDescuento(subtotal);
        double totalConIVA = calcularTotalConIVA(totalConDescuento);

        guardarFactura(pedido, totalConIVA);
    }

    private static double calcularSubtotal(Pedido pedido) {
        return pedido.getProductos()
                     .stream()
                     .mapToDouble(Producto::getPrecio)
                     .sum();
    }

    private static double aplicarDescuento(double subtotal) {
        if (subtotal > LIMITE_DESCUENTO) {
            System.out.println("Aplica descuento por gran volumen (5%)");
            return subtotal * (1 - DESCUENTO);
        }
        return subtotal;
    }

    private static double calcularTotalConIVA(double total) {
        return total * (1 + IVA);
    }

  

    private static void imprimirFactura(Pedido pedido) {
        System.out.println("Procesando pedido para: " + pedido.getCliente().getNombre());
        System.out.println("ID Cliente: " + pedido.getCliente().getId());

        int i = 1;
        for (Producto p : pedido.getProductos()) {
            System.out.println("Item " + i++ + ": " + p.getNombre()
                    + " - " + p.getPrecio() + " EUR");
        }

        double subtotal = calcularSubtotal(pedido);
        double totalConDescuento = aplicarDescuento(subtotal);
        double totalConIVA = calcularTotalConIVA(totalConDescuento);

        System.out.println("Total Neto: " + totalConDescuento);
        System.out.println("Total con IVA (" + (IVA * 100) + "%): " + totalConIVA);
        System.out.println("--------------------------------------------------");
    }



    private static void guardarFactura(Pedido pedido, double totalConIVA) {
        String nombreArchivo = "pedido_" + pedido.getCliente().getId() + ".txt";

        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write("FACTURA\n");
            writer.write("Cliente: " + pedido.getCliente().getNombre() + "\n");
            writer.write("Direccion: " + pedido.getCliente().getDireccion() + "\n");
            writer.write("Total a pagar: " + totalConIVA + "\n");
            System.out.println("Archivo guardado correctamente: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo");
            e.printStackTrace();
        }
    }
}
