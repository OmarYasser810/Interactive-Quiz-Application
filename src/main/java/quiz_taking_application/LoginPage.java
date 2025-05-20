package quiz_taking_application;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LoginPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    // Simulated users (can be loaded from a database or list)
    private ArrayList<User> users;

    public LoginPage() {
        setTitle("Login Page");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center the window

        users = new ArrayList<>();
        loadSampleUsers();

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // empty space
        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(e -> authenticate());
    }

    private void loadSampleUsers() {
        users.add(new Admin("admin1", "adminpass"));
        users.add(new Teacher("teach1", "teachpass", 35, "Math", "T001"));
        users.add(new Student("stud1", "studpass", 20, "S001", "CS"));
    }

    private void authenticate() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                String role = user.getRole();

                JOptionPane.showMessageDialog(this,
                        "Login successful! Welcome, " + role + ": " + username,
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                // Redirect to role-based panel
                if (user instanceof Admin) {
                    Admin adminUser = (Admin) user;
                    new AdminPage(adminUser).setVisible(true);
                }

                // You can add similar blocks for TeacherPanel or StudentPanel

                return;
            }
        }

        JOptionPane.showMessageDialog(this,
                "Invalid username or password",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
