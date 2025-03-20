package Resource;

import Model.*;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class OrdersResourceServer extends ResourceServer {
    ArrayList<Order> orders;

    public OrdersResourceServer() {
        super(ResourceType.ORDERS);
    }

    @Override
    protected void populateData() {
        orders = new ArrayList<>();
    }

    @Override
    protected Runnable getSocketHandler(Socket socket) {
        return new SocketHandler(socket);
    }

    private class SocketHandler implements Runnable {
        Socket socket;
        Connection c;

        public SocketHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                c = new Connection(socket);
                System.out.println("Connection Established");

                String request = c.in.readUTF();
                System.out.println(request);

                switch (request) {
                    case "list":
                        c.writeObject(orders);
                        break;
                    case "create":
                        handleCreate(c);
                        break;
                }

                c.disconnect();
            } catch (IOException | ClassNotFoundException e ) {
                System.out.println("Unable to establish connection\nReason: " + e.getMessage());
            }
        }

        private void handleCreate(Connection c) throws IOException, ClassNotFoundException {
            Order o = (Order) c.in.readObject();
            orders.add(new Order(o.getItemName(), o.getTotalPrice(), o.getQuantity()));
            orders.sort((a,b) -> b.getTimestampDate().compareTo(a.getTimestampDate()));
        }

//        private void handleDelete(Connection c) throws IOException, ClassNotFoundException {
//            InventoryItem item = (InventoryItem) c.in.readObject();
//            int idx = resources.indexOf(item);
//            if (idx >= 0)
//                resources.remove(idx);
//        }
    }
}
