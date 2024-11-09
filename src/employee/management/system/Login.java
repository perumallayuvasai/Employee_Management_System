package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    // Declare UI components
    JTextField tfusername;
    JPasswordField tfpassword;
    JCheckBox showPassword;

    // Constructor to set up the GUI for login
    Login() {

        // Set background color for the frame
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Create and set up the Username label
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 30);
        add(lblusername);

        // Create and set up the Username text field
        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 30);
        add(tfusername);

        // Create and set up the Password label
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 70, 100, 30);
        add(lblpassword);

        // Create and set up the Password text field (JPasswordField)
        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 70, 150, 30);
        add(tfpassword);

        // Create and set up the checkbox to show/hide password
        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(150, 100, 150, 30);
        showPassword.setBackground(Color.WHITE);
        showPassword.addActionListener(new ActionListener() {
            // Action to toggle password visibility
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    tfpassword.setEchoChar((char) 0); // Show password as plain text
                } else {
                    tfpassword.setEchoChar('*'); // Hide password with asterisks
                }
            }
        });
        add(showPassword);

        // Create and set up the Login button
        JButton login = new JButton("LOGIN");
        login.setBounds(150, 140, 150, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this); // Add action listener to handle login
        add(login);

        // Add an image to the frame (optional, for UI aesthetics)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 200, 200);
        add(image);

        // Set up frame properties (size, location, visibility)
        setSize(600, 300);
        setLocation(450, 200);
        setVisible(true);
    }

    // Handle login button click event
    public void actionPerformed(ActionEvent ae) {
        try {
            // Get username and password input
            String username = tfusername.getText();
            String password = new String(tfpassword.getPassword());

            // Create a connection to the database
            Conn c = new Conn();

            // SQL query to check if the entered username and password match the records in the database
            String query = "SELECT * FROM login WHERE username = '" + username + "' AND password = '" + password + "'";

            // Execute the query
            ResultSet rs = c.s.executeQuery(query);

            // Check if login is successful
            if (rs.next()) {
                setVisible(false); // Hide the login frame
                new Home(); // Open the home screen after successful login
            } else {
                // Show error message if login fails
                JOptionPane.showMessageDialog(null, "Invalid username or password");
                setVisible(false); // Hide the login frame after failure
            }
        } catch (Exception e) {
            // Print stack trace if there's an exception
            e.printStackTrace();
        }
    }

    // Main method to create an instance of the Login class (entry point)
    public static void main(String[] args) {
        new Login();
    }
}
