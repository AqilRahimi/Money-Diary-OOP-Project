import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.border.LineBorder;

public class MoneyDiaryApp {
    private JFrame frame;
    private JTextField emailField;
    private JPasswordField passwordField;
    private HashMap<String, String> userDatabase = new HashMap<>();

    public MoneyDiaryApp() {
        frame = new JFrame("Money Diary - Secure Login & Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel logoLabel = new JLabel(new ImageIcon("logo.png")); // Add logo image (logo.png should be in the project directory)
        JLabel titleLabel = new JLabel("Money Diary");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(34, 45, 65));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel taglineLabel = new JLabel("Track your finances with ease");
        taglineLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        taglineLabel.setForeground(new Color(85, 105, 135));
        taglineLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        emailField = new JTextField(20);
        emailField.setBorder(new LineBorder(new Color(150, 150, 150), 1));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordField = new JPasswordField(20);
        passwordField.setBorder(new LineBorder(new Color(150, 150, 150), 1));

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        registerButton.setFont(new Font("Arial", Font.BOLD, 18));

        loginButton.setBackground(new Color(72, 133, 237));
        loginButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(52, 168, 83));
        registerButton.setForeground(Color.WHITE);

        loginButton.setPreferredSize(new Dimension(150, 40));
        registerButton.setPreferredSize(new Dimension(150, 40));

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(logoLabel, gbc);

        gbc.gridy = 1;
        panel.add(titleLabel, gbc);

        gbc.gridy = 2;
        panel.add(taglineLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(loginButton, gbc);

        gbc.gridx = 1;
        panel.add(registerButton, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void login() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (userDatabase.containsKey(email) && userDatabase.get(email).equals(hashPassword(password))) {
            JOptionPane.showMessageDialog(frame, "Login successful! Welcome back to Money Diary.");
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid email or password. Please try again.");
        }
    }

    private void register() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (userDatabase.containsKey(email)) {
            JOptionPane.showMessageDialog(frame, "Email already registered. Please use a different email.");
        } else if (!email.contains("@") || password.length() < 6) {
            JOptionPane.showMessageDialog(frame, "Invalid email format or password too short (min 6 characters).");
        } else {
            userDatabase.put(email, hashPassword(password));
            JOptionPane.showMessageDialog(frame, "Registration successful! Welcome to Money Diary.");
        }
    }

    private String hashPassword(String password) {
        return Integer.toHexString(password.hashCode());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MoneyDiaryApp());
    }
}
