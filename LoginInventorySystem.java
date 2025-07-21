import javax.swing.*;
import java.io.*;
import java.util.*;

public class LoginInventorySystem {
    public static void main(String[] args) {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(300, 180);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 10, 80, 25);
        loginFrame.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(100, 10, 160, 25);
        loginFrame.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(10, 40, 80, 25);
        loginFrame.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(100, 40, 160, 25);
        loginFrame.add(passField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(10, 70, 80, 25);
        loginFrame.add(roleLabel);

        JTextField roleField = new JTextField();
        roleField.setBounds(100, 70, 160, 25);
        loginFrame.add(roleField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 105, 100, 25);
        loginFrame.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            String role = roleField.getText();

            if (validateLogin(username, password, role)) {
                loginFrame.dispose();
                JOptionPane.showMessageDialog(null, "Welcome, " + role + "!");
                new InventoryGUI(role);  // Launch inventory system
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Login Failed. Check credentials.");
            }
        });

        loginFrame.setVisible(true);
    }

    private static boolean validateLogin(String user, String pass, String role) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    if (parts[0].equals(user) && parts[1].equals(pass) && parts[2].equalsIgnoreCase(role)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users.txt");
        }
        return false;
    }
}
