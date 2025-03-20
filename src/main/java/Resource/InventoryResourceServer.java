package Resource;

import Model.*;

import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class InventoryResourceServer extends ResourceServer {
    private ArrayList<InventoryItem> resources;

    public InventoryResourceServer(ResourceType resourceType) {
        super(resourceType);
        if (resourceType == ResourceType.ORDERS)
            System.exit(0);
    }

    @Override
    protected void populateData() {
        resources = new ArrayList<>();

        Random rand = new Random();
        int minQuantity = 1;
        int maxQuantity = 10;

        switch (resourceType) {
            case INVENTORY_CLOTH:
                populateClothData(rand, minQuantity, maxQuantity);
                break;
            case INVENTORY_FOOTWEAR:
                populateFootwearData(rand, minQuantity, maxQuantity);
                break;
            case INVENTORY_ACCESSORIES:
                populateAccessoriesData(rand, minQuantity, maxQuantity);
                break;
        }

        System.out.println(resources);
    }

    private void populateClothData(Random rand, int minQuantity, int maxQuantity) {
        String[] clothNames = {
                "T-Shirt", "Polo Shirt", "Button-Down Shirt", "Dress Shirt", "Flannel Shirt",
                "Henley Shirt", "Oxford Shirt", "Turtleneck", "Crop Top", "Tank Top",
                "Blouse", "Tunic", "Cardigan", "Sweater", "Pullover",
                "Hoodie", "Sweatshirt", "Jacket", "Blazer", "Sport Coat",
                "Suit Jacket", "Denim Jacket", "Leather Jacket", "Bomber Jacket", "Windbreaker",
                "Raincoat", "Trench Coat", "Parka", "Peacoat", "Overcoat",
                "Jeans", "Chinos", "Khakis", "Dress Pants", "Slacks",
                "Cargo Pants", "Track Pants", "Joggers", "Sweatpants", "Leggings",
                "Shorts", "Bermuda Shorts", "Cargo Shorts", "Board Shorts", "Athletic Shorts",
                "Skirt", "Maxi Skirt", "Midi Skirt", "Mini Skirt", "Pencil Skirt",
                "Dress", "Maxi Dress", "Cocktail Dress", "Shift Dress", "Wrap Dress",
                "Jumpsuit", "Romper", "Overalls", "Vest", "Waistcoat"
        };
        double[] prices = {
                12.99, 14.99, 16.99, 17.99, 19.99, 21.99, 22.99, 24.99, 26.99, 27.99,
                29.99, 32.99, 34.99, 36.99, 39.99, 42.99, 44.99, 46.99, 49.99, 52.99,
                54.99, 56.99, 59.99, 62.99, 64.99, 66.99, 69.99, 72.99, 74.99, 76.99,
                79.99, 82.99, 84.99, 86.99, 89.99, 92.99, 94.99, 96.99, 99.99, 109.99,
                119.99, 129.99, 139.99, 149.99, 159.99, 169.99, 179.99, 189.99, 199.99, 209.99,
                219.99, 229.99, 239.99, 249.99, 259.99, 269.99, 279.99, 289.99, 299.99, 349.99
        };

        int numOfClothTypes = rand.nextInt(5, clothNames.length);
        HashSet<Integer> visitedClothNames = new HashSet<>();

        for (int i = 0; i < numOfClothTypes; i++) {
            int nameIndex;

            do {
                nameIndex = rand.nextInt(clothNames.length);
            } while (visitedClothNames.contains(nameIndex));

            visitedClothNames.add(nameIndex);
            double price = prices[rand.nextInt(prices.length)];

            Cloth clothItem = new Cloth(clothNames[nameIndex], price);

            for (Cloth.Size size : Cloth.Size.values()) {
                int quantity = rand.nextInt(minQuantity, maxQuantity + 1);
                clothItem.addQuantity(size, quantity);
            }

            resources.add(clothItem);
        }
    }

    private void populateFootwearData(Random rand, int minQuantity, int maxQuantity) {
        String[] footwearNames = {
                "Running Shoes", "Sneakers", "Boots", "Sandals", "Loafers",
                "Oxford Shoes", "Brogues", "Derby Shoes", "Monk Straps", "Espadrilles",
                "Flip Flops", "Slippers", "Hiking Boots", "Work Boots", "Chelsea Boots",
                "Ankle Boots", "High Heels", "Flats", "Wedges", "Mules",
                "Clogs", "Platform Shoes", "Athletic Shoes", "Basketball Shoes", "Tennis Shoes",
                "Soccer Cleats", "Golf Shoes", "Dress Shoes", "Casual Shoes", "Formal Shoes"
        };

        double[] prices = {
                29.99, 39.99, 49.99, 59.99, 69.99, 79.99, 89.99, 99.99, 109.99, 119.99,
                129.99, 139.99, 149.99, 159.99, 169.99, 179.99, 189.99, 199.99, 209.99, 219.99,
                229.99, 239.99, 249.99, 259.99, 269.99, 279.99, 289.99, 299.99, 349.99, 399.99
        };

        int numOfFootwearTypes = rand.nextInt(5, footwearNames.length);
        HashSet<Integer> visitedFootwearNames = new HashSet<>();

        for (int i = 0; i < numOfFootwearTypes; i++) {
            int nameIndex;

            do {
                nameIndex = rand.nextInt(footwearNames.length);
            } while (visitedFootwearNames.contains(nameIndex));

            visitedFootwearNames.add(nameIndex);
            double price = prices[rand.nextInt(prices.length)];

            Footwear footwearItem = new Footwear(footwearNames[nameIndex], price);

            for (Footwear.Size size : Footwear.Size.values()) {
                int quantity = rand.nextInt(minQuantity, maxQuantity + 1);
                footwearItem.addQuantity(size, quantity);
            }

             resources.add(footwearItem);
        }
    }

    private void populateAccessoriesData(Random rand, int minQuantity, int maxQuantity) {
        Map<Accessory.Type, String[]> typeNames = new HashMap<>();
        typeNames.put(Accessory.Type.WATCH, new String[]{"Leather Watch", "Smart Watch", "Chronograph Watch"});
        typeNames.put(Accessory.Type.SUNGLASSES, new String[]{"Aviator Sunglasses", "Polarized Sunglasses", "Sport Sunglasses"});
        typeNames.put(Accessory.Type.BELT, new String[]{"Leather Belt", "Canvas Belt", "Reversible Belt"});
        // Add more types and names as needed

        double[] prices = {
                29.99, 39.99, 49.99, 59.99, 69.99, 79.99, 89.99, 99.99, 109.99, 119.99,
                129.99, 139.99, 149.99, 159.99, 169.99, 179.99, 189.99, 199.99, 209.99, 219.99
        };


        // Generate 3 items per type (adjust as needed)
        for (Accessory.Type type : Accessory.Type.values()) {
            String[] names = typeNames.get(type);
            if (names == null || names.length == 0) continue;

            for (int i = 0; i < 3; i++) { // Generate 3 items per type
                String name = names[rand.nextInt(names.length)];
                double price = prices[rand.nextInt(prices.length)];
                int quantity = rand.nextInt(maxQuantity - minQuantity + 1) + minQuantity;
                resources.add(new Accessory(name, price, type, quantity));
            }
        }
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
                        c.writeObject(resources);
                        break;
                    case "update":
                        handleUpdate(c);
                        break;
                    case "create":
                        handleCreate(c);
                        break;
                    case "delete":
                        handleDelete(c);
                        break;
                }

                c.disconnect();
            } catch (IOException | ClassNotFoundException e ) {
                System.out.println(e);
            }
        }

        private void handleUpdate(Connection c) throws IOException, ClassNotFoundException {
            InventoryItem item = (InventoryItem) c.in.readObject();
            int idx = resources.indexOf(item);
            if (idx >= 0)
                resources.set(idx, item);
        }

        private void handleCreate(Connection c) throws IOException, ClassNotFoundException {
            switch (resourceType) {
                case INVENTORY_CLOTH -> {
                    Cloth clothItem = (Cloth) c.in.readObject();
                    Cloth newCloth = new Cloth(clothItem.getName(), clothItem.getPrice());
                    resources.add(newCloth);
                }
                case INVENTORY_FOOTWEAR -> {
                    Footwear footwearItem = (Footwear) c.in.readObject();
                    Footwear newFootwear = new Footwear(footwearItem.getName(), footwearItem.getPrice());
                    resources.add(newFootwear);
                }
                case INVENTORY_ACCESSORIES -> {
                    Accessory accessory = (Accessory) c.in.readObject();
                    Accessory newAccessory = new Accessory(accessory.getName(), accessory.getPrice(), accessory.getType(), accessory.getQuantity());
                    resources.add(newAccessory);
                }
            }
        }

        private void handleDelete(Connection c) throws IOException, ClassNotFoundException {
            InventoryItem item = (InventoryItem) c.in.readObject();
            int idx = resources.indexOf(item);
            if (idx >= 0)
                resources.remove(idx);
        }
    }
}
