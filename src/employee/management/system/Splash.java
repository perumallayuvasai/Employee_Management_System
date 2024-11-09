package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Splash extends JFrame implements ActionListener {

    // Constructor for Splash screen
    Splash() {
        // Set the background color of the content pane to white
        getContentPane().setBackground(Color.WHITE);
        // Set layout to null for custom positioning of elements
        setLayout(null);

        // Heading label with text for the application title
        JLabel heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(80, 30, 1200, 60); // Position and size of the heading
        heading.setFont(new Font("serif", Font.PLAIN, 60)); // Font style and size for heading
        heading.setForeground(Color.RED); // Font color for heading text
        add(heading); // Add heading to the frame

        // Load and scale the image to fit within the splash screen window
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/front.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1100, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2); // Creating an ImageIcon to display
        JLabel image = new JLabel(i3); // Image label for display
        image.setBounds(50, 100, 1050, 500); // Position and size for the image
        add(image); // Add image to the frame

        // Button to continue to the next screen
        JButton clickhere = new JButton("CLICK HERE TO CONTINUE");
        clickhere.setBounds(400, 400, 300, 70); // Position and size for the button
        clickhere.setBackground(Color.BLACK); // Button background color
        clickhere.setForeground(Color.WHITE); // Button text color
        clickhere.addActionListener(this); // Set action listener for button click
        image.add(clickhere); // Add button on top of image

        // Set the size and location of the splash screen window
        setSize(1170, 650);
        setLocation(200, 50);
        setVisible(true); // Display the splash screen

        // Blinking effect for the heading text
        while (true) {
            heading.setVisible(false); // Hide heading
            try {
                Thread.sleep(500); // Pause for 500ms
            } catch (Exception e) {
                e.printStackTrace();
            }

            heading.setVisible(true); // Show heading again
            try {
                Thread.sleep(500); // Pause for 500ms
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Event handler for button click
    public void actionPerformed(ActionEvent ae) {
        setVisible(false); // Hide the splash screen
        new Login(); // Open the login screen
    }

    // Main method to launch the Splash screen
    public static void main(String args[]) {
        new Splash(); // Create an instance of Splash (calls constructor)
    }
}
