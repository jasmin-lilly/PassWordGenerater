import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PasswordGenerator {

    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Password Generator");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Create components
        JLabel label = new JLabel("Enter password length:");
        JTextField lengthField = new JTextField(10);
        JButton generateButton = new JButton("Generate Password");
        JTextArea passwordArea = new JTextArea(1, 20);
        passwordArea.setEditable(false);
        JButton copyButton = new JButton("Copy to Clipboard");

        // Add components to frame
        frame.add(label);
        frame.add(lengthField);
        frame.add(generateButton);
        frame.add(new JScrollPane(passwordArea));
        frame.add(copyButton);

        // Action for Generate Button
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int length = Integer.parseInt(lengthField.getText());
                    if (length < 4) {
                        JOptionPane.showMessageDialog(frame, "Password length must be at least 4", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        passwordArea.setText(generatePassword(length));
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action for Copy Button
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordArea.getText();
                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No password to copy", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(password), null);
                    JOptionPane.showMessageDialog(frame, "Password copied to clipboard!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    // Method to generate password
    private static String generatePassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        StringBuilder password = new StringBuilder();

        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }
}
