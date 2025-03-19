package Branch;

import Coordinator.Coordinator;
import Model.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Branch {

    public static void releaseResource(ResourceType resourceType, CoordinatorRequest.AccessMode accessMode) throws IOException {
        Connection c = new Connection("localhost", Coordinator.PORT);
        c.writeObject(new CoordinatorRequest(CoordinatorRequest.OperationMode.RELEASE, accessMode, resourceType));
        c.disconnect();
    }

    public static Endpoint acquireResource(ResourceType resourceType, CoordinatorRequest.AccessMode accessMode) throws IOException, ClassNotFoundException {
        Connection c = new Connection("localhost", Coordinator.PORT);

        Endpoint endpoint = null;
        c.writeObject(new CoordinatorRequest(CoordinatorRequest.OperationMode.ACQUIRE, accessMode, resourceType));
        endpoint = (Endpoint) c.in.readObject();

        System.out.println("Granted " + accessMode + " access at " + LocalDateTime.now());

        c.disconnect();

        return endpoint;
    }

    public static void createResource(ResourceType resourceType, InventoryItem inventoryItem) {
        try {
            Endpoint resourceEndpoint = acquireResource(resourceType, CoordinatorRequest.AccessMode.WRITE);

            Connection c = new Connection(resourceEndpoint);

            c.writeUTF("create");
            c.writeObject(inventoryItem);

            c.disconnect();


            releaseResource(resourceType, CoordinatorRequest.AccessMode.WRITE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void updateResource(ResourceType resourceType, InventoryItem inventoryItem) {
        try {
            Endpoint resourceEndpoint = acquireResource(resourceType, CoordinatorRequest.AccessMode.WRITE);

            Connection c = new Connection(resourceEndpoint);

            c.writeUTF("update");
            c.writeObject(inventoryItem);

            c.disconnect();


            releaseResource(resourceType, CoordinatorRequest.AccessMode.WRITE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<InventoryItem> getResourceList(ResourceType resourceType) {
        try {
            Endpoint resourceEndpoint = acquireResource(resourceType, CoordinatorRequest.AccessMode.READ);

            Connection c = new Connection(resourceEndpoint);

            c.writeUTF("list");
            ArrayList<InventoryItem> items = (ArrayList<InventoryItem>) c.in.readObject();

            c.disconnect();


            releaseResource(resourceType, CoordinatorRequest.AccessMode.READ);

            return items;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void removeResource(ResourceType resourceType, InventoryItem inventoryItem) {
        try {
            Endpoint resourceEndpoint = acquireResource(resourceType, CoordinatorRequest.AccessMode.WRITE);

            Connection c = new Connection(resourceEndpoint);

            c.writeUTF("delete");
            c.writeObject(inventoryItem);

            c.disconnect();


            releaseResource(resourceType, CoordinatorRequest.AccessMode.WRITE);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
