package Resource;

import Model.Endpoint;
import Model.ResourceType;

import java.net.*;
import java.io.*;
import java.util.*;

abstract public class ResourceServer {
    public static final HashMap<ResourceType, Endpoint> ENDPOINTS = new HashMap<>(Map.of(
            ResourceType.INVENTORY_CLOTH, new Endpoint("localhost", 5000),
            ResourceType.INVENTORY_ACCESSORIES, new Endpoint("localhost", 5100),
            ResourceType.INVENTORY_FOOTWEAR, new Endpoint("localhost", 5200),
            ResourceType.ORDERS, new Endpoint("localhost", 6000)
    ));

    private ServerSocket ss = null;
    protected ResourceType resourceType;

    public ResourceServer(ResourceType resourceType) {
        this.resourceType = resourceType;
        populateData();
        bindServer();

        if (ss != null)
            startServer();
    }

    protected abstract void populateData();

    private void bindServer() {
        try {
            ss = new ServerSocket(ENDPOINTS.get(resourceType).port);
            System.out.println("Bound " + resourceType + " server to port: " + ENDPOINTS.get(resourceType).port);
        } catch (IOException ex) {
            ss = null;
            System.out.println("Could not bind " + resourceType + " server to port: " + ENDPOINTS.get(resourceType).port);
        }
    }

    private void startServer() {
        System.out.println(resourceType + " server listening");
        while (true) {
            try {
                Socket connection = ss.accept();
                Thread thread = new Thread(getSocketHandler(connection));
                thread.start();
            } catch (IOException ex) {
                System.out.println("Error accepting connection");
            }
        }
    }

    protected abstract Runnable getSocketHandler(Socket socket);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("1: Cloth Inventory server\n2: Footwear Inventory server\n3: Accessories Inventory server\n4: Orders server");
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                new InventoryResourceServer(ResourceType.INVENTORY_CLOTH);
                break;
            case 2:
                new InventoryResourceServer(ResourceType.INVENTORY_FOOTWEAR);
                break;
            case 3:
                new InventoryResourceServer(ResourceType.INVENTORY_ACCESSORIES);
                break;
            case 4:
                new OrdersResourceServer();
                break;
            default:
                break;
        }
    }
}
