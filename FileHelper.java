import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class FileHelper {
    private static final String FILE_NAME = "inventory_data.txt";

    public static void saveToFile(List<Item> items) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Item item : items) {
                writer.println(item.getId() + "," + item.getName() + "," + item.getQuantity() + "," + item.getPrice());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Item> loadFromFile() {
        List<Item> items = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return items;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);
                    items.add(new Item(id, name, quantity, price));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
}
