import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class SistemaPedidos {
 // Variables globales mal ubicadas y poco descriptivas
 public static double v = 0.21; // Esto es el IVA
 public static String f = "reporte_pedidos.txt"; // Archivo de salida
 public static void main(String[] args) {
 System.out.println("INICIANDO SISTEMA DE PEDIDOS v1.0...");
 // --- CLIENTE 1: Datos dispersos ---
 String c1_n = "TechSolutions SL";
 String c1_id = "B12345678";
 String c1_d = "Calle Industria 55, Madrid";

 // --- CLIENTE 1: Lista de productos (Arrays paralelos, mala práctica) ---
 ArrayList<String> p_n = new ArrayList<>(); // Nombres productos
 p_n.add("Servidor Dell PowerEdge");
 p_n.add("Licencia Windows Server");
 p_n.add("Cableado Estructurado");

 ArrayList<Double> p_p = new ArrayList<>(); // Precios productos
 p_p.add(2500.00);
 p_p.add(800.00);
 p_p.add(250.50);
 // --- CLIENTE 1: CÁLCULOS (Mezclados con impresión) ---0
 double t1 = 0; // total
 System.out.println("Procesando pedido para: " + c1_n);
 System.out.println("ID Cliente: " + c1_id);

 for (int i = 0; i < p_n.size(); i++) {
 t1 = t1 + p_p.get(i);
 System.out.println("Item " + (i+1) + ": " + p_n.get(i) + " - " +
p_p.get(i) + " EUR");
 }
 // Lógica de descuento "hardcodeada"
 if (t1 > 3000) {
 System.out.println("Aplica descuento por gran volumen (5%)");
 t1 = t1 * 0.95;
 }

 double t1_iva = t1 + (t1 * v);
 System.out.println("Total Neto: " + t1);
 System.out.println("Total con IVA (" + (v*100) + "%): " + t1_iva);
 System.out.println("--------------------------------------------------");
 // --- CLIENTE 1: GUARDADO EN ARCHIVO (Responsabilidad mezclada) ---
 try {
 FileWriter myWriter = new FileWriter("pedido_" + c1_id + ".txt");
 myWriter.write("FACTURA\n");
 myWriter.write("Cliente: " + c1_n + "\n");
 myWriter.write("Direccion: " + c1_d + "\n");
 myWriter.write("Total a pagar: " + t1_iva + "\n");
 myWriter.close();
 System.out.println("Archivo guardado correctamente.");
 } catch (IOException e) {
 System.out.println("An error occurred.");
 e.printStackTrace();
 }
 System.out.println("\n\n"); // Espacios feos
 // ====================================================================
 // --- CLIENTE 2: COPY-PASTE DEL CÓDIGO ANTERIOR (El horror) ---
 // ====================================================================

 String c2_n = "Libreria Moderna";
 String c2_id = "A98765432";
 String c2_d = "Av. Diagonal 200, Barcelona";

 ArrayList<String> p_n2 = new ArrayList<>();
 p_n2.add("Pack Libros Escolares");
 p_n2.add("Estantería Metálica");

 ArrayList<Double> p_p2 = new ArrayList<>();
 p_p2.add(1200.00);
 p_p2.add(300.00);
 double t2 = 0;
 System.out.println("Procesando pedido para: " + c2_n);
 System.out.println("ID Cliente: " + c2_id);

 for (int i = 0; i < p_n2.size(); i++) {
 t2 = t2 + p_p2.get(i);
 System.out.println("Item " + (i+1) + ": " + p_n2.get(i) + " - " +
p_p2.get(i) + " EUR");
 }

 if (t2 > 3000) {
 System.out.println("Aplica descuento por gran volumen (5%)");
 t2 = t2 * 0.95;

}
double t2_iva = t2 + (t2 * v);
System.out.println("Total Neto: " + t2);
System.out.println("Total con IVA (" + (v*100) + "%): " + t2_iva);
System.out.println("--------------------------------------------------");
try {
FileWriter myWriter = new FileWriter("pedido_" + c2_id + ".txt");
myWriter.write("FACTURA\n");
myWriter.write("Cliente: " + c2_n + "\n");
myWriter.write("Direccion: " + c2_d + "\n");
myWriter.write("Total a pagar: " + t2_iva + "\n");
myWriter.close();
System.out.println("Archivo guardado correctamente.");
} catch (IOException e) {
System.out.println("An error occurred.");
e.printStackTrace();
}
}
}
