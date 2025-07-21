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
    setLayout(new GridLayout(11, 2));
    getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background

    Font labelFont = new Font("Arial", Font.BOLD, 14);
    Font inputFont = new Font("Arial", Font.PLAIN, 14);

    JLabel idLabel = new JLabel("Item ID:");
    idLabel.setFont(labelFont);
    add(idLabel);
    idField = new JTextField();
    idField.setFont(inputFont);
    add(idField);

    JLabel nameLabel = new JLabel("Item Name:");
    nameLabel.setFont(labelFont);
    add(nameLabel);
    nameField = new JTextField();
    nameField.setFont(inputFont);
    add(nameField);

    JLabel qtyLabel = new JLabel("Quantity:");
    qtyLabel.setFont(labelFont);
    add(qtyLabel);
    qtyField = new JTextField();
    qtyField.setFont(inputFont);
    add(qtyField);

    JLabel priceLabel = new JLabel("Price:");
    priceLabel.setFont(labelFont);
    add(priceLabel);
    priceField = new JTextField();
    priceField.setFont(inputFont);
    add(priceField);

    JButton addButton = new JButton("Add Item");
    JButton viewButton = new JButton("View Inventory");
    JButton saveButton = new JButton("Save to File");
    JButton loadButton = new JButton("Load from File");
    JButton updateButton = new JButton("Update Item");
    JButton deleteButton = new JButton("Delete Item");
    JButton searchButton = new JButton("Search Item");
    JButton totalButton = new JButton("Calculate Total Value");

    JButton[] buttons = {addButton, viewButton, saveButton, loadButton, updateButton, deleteButton, searchButton, totalButton};
    for (JButton btn : buttons) {
        btn.setBackground(new Color(100, 149, 237)); // Cornflower blue
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
    }

    outputArea = new JTextArea();
    outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
    outputArea.setLineWrap(true);
    outputArea.setWrapStyleWord(true);
    outputArea.setBackground(new Color(255, 250, 250)); // Light snow color
    JScrollPane scrollPane = new JScrollPane(outputArea);

    add(addButton);
    add(viewButton);
    add(saveButton);
    add(loadButton);
    add(updateButton);
    add(deleteButton);
    add(searchButton);
    add(totalButton);
    add(scrollPane);

    // Button disabling logic remains the same...
    if (!userRole.equalsIgnoreCase("admin")) {
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
        saveButton.setEnabled(false);
        loadButton.setEnabled(false);
        totalButton.setEnabled(false);
    }

    setVisible(true);


    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        qtyField.setText("");
        priceField.setText("");
    }

    public static void main(String[] args) {
    new InventoryGUI("admin"); // You can also use "staff"
    }

}
