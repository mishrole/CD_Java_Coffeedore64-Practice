import java.util.ArrayList;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Order {
    private String name;
    private boolean isReady;
    private ArrayList<Item> items;

    public Order() {
        this.name = "Invitado";
        this.items = new ArrayList<Item>();
    }

    public Order(String name) {
        this.name = name;
        this.items = new ArrayList<Item>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsReady() {
        return this.isReady;
    }

    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public double getOrderTotal() {
        double total = 0.0;

        for (Item item : this.getItems()) {
            total += item.getPrice();
        }

        return total;
    }

    public String getStatusMessage() {
        String message = MessageFormat.format("Gracias por esperar, {0}. ¡Tu orden estará lista pronto!", this.getName());

        if (this.getIsReady()) {
            message = MessageFormat.format("¡Tu orden está lista, {0}!", this.getName());
        }

        return message;
    }

    public void display() {
        System.out.println(MessageFormat.format("Gracias, {0}. Aqui estan los detalles de tu orden:", this.getName()));
        System.out.println(MessageFormat.format("Nombre de Cliente: {0}", this.getName()));

        Locale usa = new Locale("en", "US");
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);

        for (Item item : this.getItems()) {
            System.out.println(MessageFormat.format("{0} - {1}", item.getName(), dollarFormat.format(item.getPrice())));
        }

        System.out.println(MessageFormat.format("Total: {0}", dollarFormat.format(this.getOrderTotal())));
        System.out.println("---------------------------- \n");
    }

}
