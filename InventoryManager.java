import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManager {
    private ArrayList<Item> itemList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addItem() {
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int qty = scanner.nextInt();git --version

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // clear buffer

        itemList.add(new Item(id, name, qty, price));
        System.out.println("Item added successfully.\n");
    }

    public void viewItems() {
        if (itemList.isEmpty()) {
            System.out.println("Inventory is empty.\n");
            return;
        }

        System.out.println("=== Inventory List ===");
        for (Item item : itemList) {
            System.out.println(item);
        }
        System.out.println();
    }

    public void updateItemQuantity() {
        System.out.print("Enter item ID to update: ");
        String id = scanner.nextLine();

        for (Item item : itemList) {
            if (item.getId().equalsIgnoreCase(id)) {
                System.out.print("Enter new quantity: ");
                int qty = scanner.nextInt();
                scanner.nextLine();
                item.setQuantity(qty);
                System.out.println("Quantity updated.\n");
                return;
            }
        }

        System.out.println("Item not found.\n");
    }

    public void searchItem() {
        System.out.print("Enter item name or ID to search: ");
        String key = scanner.nextLine();

        for (Item item : itemList) {
            if (item.getId().equalsIgnoreCase(key) || item.getName().equalsIgnoreCase(key)) {
                System.out.println("Item found:\n" + item + "\n");
                return;
            }
        }

        System.out.println("Item not found.\n");
    }

    public void deleteItem() {
        System.out.print("Enter item ID to delete: ");
        String id = scanner.nextLine();

        for (Item item : itemList) {
            if (item.getId().equalsIgnoreCase(id)) {
                itemList.remove(item);
                System.out.println("Item deleted.\n");
                return;
            }
        }

        System.out.println("Item not found.\n");
    }
}


