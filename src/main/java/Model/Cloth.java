package Model;

import java.util.HashMap;

public class Cloth extends InventoryItem {
    public enum Size {
        SMALL,
        MEDIUM,
        LARGE
    } ;

    private final HashMap<Size, Integer> quantity;

    public Cloth(String name, double price) {
        super(name, price);
        this.quantity = new HashMap<>();
        for (Size s : Size.values()) {
            quantity.put(s, 0);
        }
    }

    public int getTotalQuantity() {
        return quantity.values().stream().mapToInt(i -> i).sum();
    }

    public int getQuantity(Size size) {
        return quantity.get(size);
    }

    public void addQuantity(Size size, int quantity) {
        this.quantity.put(size, this.quantity.get(size) + quantity);
    }

    public void removeQuantity(Size size, int quantity) {
        this.quantity.put(size, this.quantity.get(size) - quantity);
    }

    @Override
    public String toString() {
        return "Cloth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
