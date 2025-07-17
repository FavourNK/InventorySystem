import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Developed by Nkasiobi Favour
        InventoryManager manager = new InventoryManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=== Inventory Management ===");
            System.out.println("1. Add Item");
            System.out.println("2. View Items");
            System.out.println("3. Update Item Quantity");
            System.out.println("4. Search Item");
            System.out.println("5. Delete Item");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // clear input buffer

            switch (choice) {
                case 1 -> manager.addItem();
                case 2 -> manager.viewItems();
                case 3 -> manager.updateItemQuantity();
                case 4 -> manager.searchItem();
                case 5 -> manager.deleteItem();
                case 6 -> System.out.println("Exiting program...");
                default -> System.out.println("Invalid option. Try again.\n");
            }
        } while (choice != 6);
    }
}

