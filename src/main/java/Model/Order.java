package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order implements Serializable {
    private static int count = 0;
    private int id;
    private String itemName;
    private Double totalPrice;
    private int quantity;
    private Date timestamp;

    public Order(String itemName, Double totalPrice, int quantity) {
        this.itemName = itemName;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.timestamp = new Date();
        this.id = count++;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Order.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTimestamp() {
        return  new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss").format(timestamp) ;
    }

    public Date getTimestampDate() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
