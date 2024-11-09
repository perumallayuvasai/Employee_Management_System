package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ViewEmployee extends JFrame implements ActionListener {

    JTable table;
    Choice cemployeeId;
    JTextField searchField;
    JButton search, print, update, back, nextPage, prevPage;
    JLabel pageLabel;
    int page = 1;
    int pageSize = 10;

    ViewEmployee() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Label for employee ID search
        JLabel searchlbl = new JLabel("Search by Employee Id");
        searchlbl.setBounds(20, 20, 150, 20);
        add(searchlbl);

        // Choice dropdown for selecting employee ID
        cemployeeId = new Choice();
        cemployeeId.setBounds(180, 20, 150, 20);
        add(cemployeeId);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            while(rs.next()) {
                cemployeeId.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Table to display employee data
        table = new JTable();
        updateTable(page); // Initial table load with page 1

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 500);
        add(jsp);

        // Search button to find employee by ID
        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        // Print button to print table content
        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        // Update button to modify selected employee's details
        update = new JButton("Update");
        update.setBounds(220, 70, 80, 20);
        update.addActionListener(this);
        add(update);

        // Back button to return to the previous menu
        back = new JButton("Back");
        back.setBounds(320, 70, 80, 20);
        back.addActionListener(this);
        add(back);

        // Pagination buttons to navigate pages
        prevPage = new JButton("Previous");
        prevPage.setBounds(420, 70, 100, 20);
        prevPage.addActionListener(this);
        add(prevPage);

        nextPage = new JButton("Next");
        nextPage.setBounds(530, 70, 100, 20);
        nextPage.addActionListener(this);
        add(nextPage);

        // Label to display the current page number
        pageLabel = new JLabel("Page 1");
        pageLabel.setBounds(640, 70, 80, 20);
        add(pageLabel);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    // Method to update table data based on the current page number
    public void updateTable(int page) {
        int offset = (page - 1) * pageSize;
        String query = "SELECT * FROM employee LIMIT " + pageSize + " OFFSET " + offset;
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
            pageLabel.setText("Page " + page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            // Search action to filter by employee ID
            String query = "select * from employee where empId = '"+cemployeeId.getSelectedItem()+"'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            // Print action to print table content
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            // Update action to modify employee details
            setVisible(false);
            new UpdateEmployee(cemployeeId.getSelectedItem());
        } else if (ae.getSource() == back) {
            // Back action to return to home
            setVisible(false);
            new Home();
        } else if (ae.getSource() == nextPage) {
            // Next page action
            page++;
            updateTable(page);
        } else if (ae.getSource() == prevPage) {
            // Previous page action
            if (page > 1) {
                page--;
                updateTable(page);
            }
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}
