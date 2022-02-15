import java.util.ArrayList;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class KioskoCafe {
    ArrayList<Item> menu;
    ArrayList<Order> pedidos;

    public KioskoCafe() {
        menu = new ArrayList<Item>();
        pedidos = new ArrayList<Order>();
    }

    public void addMenuItem(String nombre, double precio) {
        int nextIndex = menu.size();
        Item item = new Item(nextIndex, nombre, precio);
        menu.add(item);
    }

    public void displayMenu() {
        Locale usa = new Locale("en", "US");
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);

        for (Item item : menu) {
            System.out.println(
                MessageFormat.format(
                    "{0}: {1} -- {2}", 
                    item.getIndex(), 
                    item.getName(),
                    dollarFormat.format(item.getPrice())
                )
            );
        }
    }

    public void newOrder() {
        // Mostrar al usuario un aviso de mensaje y luego establece su entrada en una variable, nombrecopy
        System.out.println("Ingrese el nombre del cliente para el nuevo pedido:");
        String name = System.console().readLine();
    
        // Tu código:
        // Crea un nuevo pedido con la cadena de entrada dada
        Order order = new Order(name);
        // Muestra el menú al usuario, para que puedan elegir artículos para agregar
        displayMenu();
        
        String itemNumber = "";
        // Escribir un bucle while para recopilar todos los artículos del pedido del usuario
        while(!itemNumber.equals("q")) {
            // Pedir al usuario que ingrese un número de artículo
            System.out.println("Ingrese un indice de articulo del menu o q para salir:");
            itemNumber = System.console().readLine();

            // Obtén el objeto del artículo del menú y agrega el artículo al pedido
            // Pídales que ingresen un nuevo índice de artículo o q nuevamente, y toma su entrada
            try {
                int index = Integer.parseInt(itemNumber);

                if (index > menu.size() - 1) {
                    System.out.println("Opcion no valida. Intenta nuevamemente");
                    displayMenu();
                } else {
                    Item currentItem = menu.get(index);
                    order.addItem(currentItem);
                    pedidos.add(order);
                }

            } catch(Exception e) {
                // System.out.println("Opción no válida");
            }
        }

        // Después de haber completado su pedido, imprime los detalles del pedido 
        // como el ejemplo de abajo. Puedes utilizar el método de visualización del pedido
        order.display();
    }

    public static void main(String[] args) {
        KioskoCafe kiosko = new KioskoCafe();

        kiosko.addMenuItem("Banana", 1600.0);
        kiosko.addMenuItem("Cafe", 1200.0);
        kiosko.addMenuItem("Latte", 3600.0);
        kiosko.addMenuItem("Capuchino", 2400.0);
        kiosko.addMenuItem("Muffin", 3200.0);

        kiosko.newOrder();
    }
}
