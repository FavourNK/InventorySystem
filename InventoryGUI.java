import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class InventoryGUI extends JFrame {
    private String userRole;
    private ArrayList<Item> itemList = new ArrayList<>();
    private JTextField idField, nameField, qtyField, priceField;
    private JTextArea outputArea;

    public InventoryGUI(String role) {
        this.userRole = role;

        setTitle("Inventory Manager - " + role.toUpperCase());
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(12, 2));
        getContentPane().setBackground(new Color(144, 238, 144)); // light green background

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font inputFont = new Font("Arial", Font.PLAIN, 14);

        // Labels and input fields
        add(new JLabel("Item ID:")).setFont(labelFont);
        idField = new JTextField(); idField.setFont(inputFont); add(idField);

        add(new JLabel("Item Name:")).setFont(labelFont);
        nameField = new JTextField(); nameField.setFont(inputFont); add(nameField);

        add(new JLabel("Quantity:")).setFont(labelFont);
        qtyField = new JTextField(); qtyField.setFont(inputFont); add(qtyField);

        add(new JLabel("Price:")).setFont(labelFont);
        priceField = new JTextField(); priceField.setFont(inputFont); add(priceField);

        // Buttons
        JButton addButton = new JButton("Add Item");
        JButton viewButton = new JButton("View Inventory");
        JButton saveButton = new JButton("Save to File");
        JButton loadButton = new JButton("Load from File");
        JButton updateButton = new JButton("Update Item");
        JButton deleteButton = new JButton("Delete Item");
        JButton searchButton = new JButton("Search Item");
        JButton totalButton = new JButton("Calculate Total Value");

        // Tooltips
        addButton.setToolTipText("Click to add a new item");
        viewButton.setToolTipText("Click to view all inventory items");
        saveButton.setToolTipText("Save inventory list to a file");
        loadButton.setToolTipText("Load items from saved file");
        updateButton.setToolTipText("Update existing item details");
        deleteButton.setToolTipText("Delete item by ID");
        searchButton.setToolTipText("Search for an item by ID or name");
        totalButton.setToolTipText("Calculate the total inventory value");

        // Styling buttons
        JButton[] buttons = { addButton, viewButton, saveButton, loadButton,
                              updateButton, deleteButton, searchButton, totalButton };
        for (JButton btn : buttons) {
            btn.setBackground(new Color(100, 149, 237)); // Cornflower blue
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Arial", Font.BOLD, 13));
        }

        // Add buttons
        add(addButton); add(viewButton); add(saveButton); add(loadButton);
        add(updateButton); add(deleteButton); add(searchButton); add(totalButton);

        // Output area
        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setBackground(new Color(255, 250, 250));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(new JLabel("Output:"));
        add(scrollPane);

        // Disable some buttons for non-admins
        if (!userRole.equalsIgnoreCase("admin")) {
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
            saveButton.setEnabled(false);
            loadButton.setEnabled(false);
            totalButton.setEnabled(false);
        }

        // --------------------------
        // ✅ Button functionality
        // --------------------------

        // ADD
        addButton.addActionListener(e -> {
            try {
                String id = idField.getText();
                String name = nameField.getText();
                int qty = Integer.parseInt(qtyField.getText());
                double price = Double.parseDouble(priceField.getText());

                if (id.isEmpty() || name.isEmpty()) {
                    outputArea.append("❌ Item ID and Name cannot be empty.\n");
                    return;
                }

                Item item = new Item(id, name, qty, price);
                itemList.add(item);
                outputArea.append("✅ Item '" + name + "' added successfully.\n");
                clearFields();
            } catch (NumberFormatException ex) {
                outputArea.append("❌ Error: Quantity and Price must be valid numbers.\n");
            } catch (Exception ex) {
                outputArea.append("❌ Unexpected error occurred.\n");
            }
        });

        // VIEW
        viewButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("📦 Inventory List:\n");
            for (Item item : itemList) {
                sb.append(item).append("\n");
            }
            outputArea.setText(sb.toString());
        });

        // SAVE
        saveButton.addActionListener(e -> {
            try (FileWriter writer = new FileWriter("inventory.txt")) {
                for (Item item : itemList) {
                    writer.write(item.getId() + "," + item.getName() + "," +
                                 item.getQuantity() + "," + item.getPrice() + "\n");
                }
                outputArea.setText("✅ Inventory saved to inventory.txt\n");
            } catch (IOException ex) {
                outputArea.setText("❌ Failed to save file.\n");
            }
        });

        // LOAD
        loadButton.addActionListener(e -> {
            itemList.clear();
            try (BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        String id = parts[0].trim();
                        String name = parts[1].trim();
                        int qty = Integer.parseInt(parts[2].trim());
                        double price = Double.parseDouble(parts[3].trim());
                        itemList.add(new Item(id, name, qty, price));
                    }
                }
                outputArea.setText("✅ Inventory loaded from inventory.txt\n");
            } catch (IOException ex) {
                outputArea.setText("❌ Failed to load file.\n");
            }
        });

        // UPDATE
        updateButton.addActionListener(e -> {
            String id = idField.getText().trim();
            for (Item item : itemList) {
                if (item.getId().equals(id)) {
                    try {
                        item.setName(nameField.getText().trim());
                        item.setQuantity(Integer.parseInt(qtyField.getText().trim()));
                        item.setPrice(Double.parseDouble(priceField.getText().trim()));
                        outputArea.setText("✅ Item updated successfully!\n");
                        clearFields();
                        return;
                    } catch (Exception ex) {
                        outputArea.setText("❌ Invalid input for update.\n");
                        return;
                    }
                }
            }
            outputArea.setText("❌ Item with ID " + id + " not found.\n");
        });

        // DELETE
        deleteButton.addActionListener(e -> {
            String id = idField.getText();
            int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete item with ID: " + id + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean removed = itemList.removeIf(item -> item.getId().equals(id));
                if (removed) {
                    outputArea.setText("✅ Item deleted.\n");
                    clearFields();
                } else {
                    outputArea.setText("❌ Item not found.\n");
                }
            }
        });

        // SEARCH
        searchButton.addActionListener(e -> {
            String keyword = idField.getText().trim().toLowerCase();
            StringBuilder result = new StringBuilder();
            for (Item item : itemList) {
                if (item.getId().toLowerCase().contains(keyword) || 
                    item.getName().toLowerCase().contains(keyword)) {
                    result.append("🔎 Found: ").append(item).append("\n");
                }
            }
            if (result.length() == 0) {
                outputArea.setText("No matching item found.\n");
            } else {
                outputArea.setText(result.toString());
            }
        });

        // TOTAL VALUE
        totalButton.addActionListener(e -> {
            double total = 0;
            for (Item item : itemList) {
                total += item.getPrice() * item.getQuantity();
            }
            outputArea.setText("💰 Total Inventory Value: ₦" + total + "\n");
        });

        setVisible(true);
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        qtyField.setText("");
        priceField.setText("");
    }

    public static void main(String[] args) {
        new InventoryGUI("admin");  // or "staff"
    }
}
