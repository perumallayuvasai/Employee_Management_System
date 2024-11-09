package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    // Declare buttons for the various actions
    JButton view, add, update, remove;

    // Constructor to set up the home screen GUI
    Home() {

        // Set layout for the frame
        setLayout(null);

        // Load and set the background image for the home screen
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1120, 630);
        add(image);

        // Add the main heading to the home screen
        JLabel heading = new JLabel("Employee Management System");
        heading.setBounds(620, 20, 400, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        image.add(heading);

        // Create and set up the "Add Employee" button
        add = new JButton("Add Employee");
        add.setBounds(650, 80, 150, 40);
        add.addActionListener(this);  // Add action listener for button click
        image.add(add);

        // Create and set up the "View Employees" button
        view = new JButton("View Employees");
        view.setBounds(820, 80, 150, 40);
        view.addActionListener(this);  // Add action listener for button click
        image.add(view);

        // Create and set up the "Update Employee" button
        update = new JButton("Update Employee");
        update.setBounds(650, 140, 150, 40);
        update.addActionListener(this);  // Add action listener for button click
        image.add(update);

        // Create and set up the "Remove Employee" button
        remove = new JButton("Remove Employee");
        remove.setBounds(820, 140, 150, 40);
        remove.addActionListener(this);  // Add action listener for button click
        image.add(remove);

        // Set size, location, and visibility of the home frame
        setSize(1120, 630);
        setLocation(250, 100);
        setVisible(true);
    }

    // Handle button click events
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            setVisible(false);  // Hide the current frame
            new AddEmployee();  // Open the AddEmployee screen
        } else if (ae.getSource() == view) {
            setVisible(false);  // Hide the current frame
            new ViewEmployee();  // Open the ViewEmployee screen
        } else if (ae.getSource() == update) {
            setVisible(false);  // Hide the current frame
            new ViewEmployee();  // Open the ViewEmployee screen for updating
        } else {
            setVisible(false);  // Hide the current frame
            new RemoveEmployee();  // Open the RemoveEmployee screen
        }
    }

    // Main method to launch the home screen
    public static void main(String[] args) {
        new Home();  // Create and display the home screen
    }
}
