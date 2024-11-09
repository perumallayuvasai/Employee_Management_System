package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class RemoveEmployee extends JFrame implements ActionListener {

    // Declare UI components
    Choice cEmpId;  // Drop-down for selecting Employee ID
    JButton delete, back;  // Buttons for deleting employee and going back

    // Constructor to set up the Remove Employee form
    RemoveEmployee() {
        getContentPane().setBackground(Color.WHITE);  // Set background color
        setLayout(null);  // Set layout manager to null for absolute positioning

        // Employee ID label and drop-down
        JLabel labelempId = new JLabel("Employee Id");
        labelempId.setBounds(50, 50, 100, 30);  // Set position and size
        add(labelempId);

        cEmpId = new Choice();  // Initialize the drop-down
        cEmpId.setBounds(200, 50, 150, 30);
        add(cEmpId);

        // Fetch all employee IDs from the database and populate the drop-down
        try {
            Conn c = new Conn();
            String query = "select * from employee";  // SQL query to fetch employee data
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                cEmpId.add(rs.getString("empId"));  // Add employee IDs to the drop-down
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Labels for displaying employee details
        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 100, 100, 30);
        add(labelname);

        JLabel lblname = new JLabel();
        lblname.setBounds(200, 100, 100, 30);
        add(lblname);

        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(50, 150, 100, 30);
        add(labelphone);

        JLabel lblphone = new JLabel();
        lblphone.setBounds(200, 150, 100, 30);
        add(lblphone);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50, 200, 100, 30);
        add(labelemail);

        JLabel lblemail = new JLabel();
        lblemail.setBounds(200, 200, 100, 30);
        add(lblemail);

        // Fetch employee details based on selected Employee ID
        try {
            Conn c = new Conn();
            String query = "select * from employee where empId = '" + cEmpId.getSelectedItem() + "'";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                lblname.setText(rs.getString("name"));
                lblphone.setText(rs.getString("phone"));
                lblemail.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add an ItemListener to update employee details when a new Employee ID is selected
        cEmpId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from employee where empId = '" + cEmpId.getSelectedItem() + "'";
                    ResultSet rs = c.s.executeQuery(query);
                    while (rs.next()) {
                        lblname.setText(rs.getString("name"));
                        lblphone.setText(rs.getString("phone"));
                        lblemail.setText(rs.getString("email"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Delete Button to remove employee record from database
        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100, 30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        // Back Button to return to the Home screen
        back = new JButton("Back");
        back.setBounds(220, 300, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        // Display a background image (optional)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 600, 400);
        add(image);

        // Set frame properties
        setSize(1000, 400);
        setLocation(300, 150);
        setVisible(true);
    }

    // Handle button actions
    public void actionPerformed(ActionEvent ae) {
        // If "Delete" button is clicked
        if (ae.getSource() == delete) {
            try {
                Conn c = new Conn();
                String query = "delete from employee where empId = '" + cEmpId.getSelectedItem() + "'";  // SQL query to delete employee
                c.s.executeUpdate(query);  // Execute the query
                JOptionPane.showMessageDialog(null, "Employee Information Deleted Successfully");
                setVisible(false);  // Hide the current frame
                new Home();  // Show the Home screen
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // If "Back" button is clicked, return to the Home screen
            setVisible(false);
            new Home();
        }
    }

    // Main method to launch the Remove Employee form
    public static void main(String[] args) {
        new RemoveEmployee();  // Create and display the Remove Employee form
    }
}
