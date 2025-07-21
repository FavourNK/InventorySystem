import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginGUI() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        add(loginButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("admin") && password.equals("admin123")) {
                dispose(); // close login window
                new InventoryGUI("admin"); // open admin GUI
            } else if (username.equals("user") && password.equals("user123")) {
                dispose();
                new InventoryGUI("user"); // open user GUI
            } else {
                JOptionPane.showMessageDialog(this, "Login failed. Check credentials.");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginGUI(); // Start with the login window
    }

 
    
}
