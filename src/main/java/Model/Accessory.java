package Model;

public class Accessory extends InventoryItem {
    public enum Type {
        WATCH, SUNGLASSES, BELT, HAT, SCARF, GLOVES, WALLET, BAG, JEWELRY, TIE
    }

    private final Type type; // Each accessory has a specific type
    private int quantity; // Single quantity for this item

    public Accessory(String name, double price, Type type, int quantity) {
        super(name, price);
        this.type = type;
        this.quantity = quantity;
    }

    public Type getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void removeQuantity(int quantity) {
        this.quantity -= quantity;
    }

    @Override
    public String toString() {
        return "Accessories{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", quantity=" + quantity +
                '}';
    }
}