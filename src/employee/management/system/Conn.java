package employee.management.system;

import java.sql.*;

public class Conn {

    // Declare connection and statement variables
    Connection c;
    Statement s;

    // Constructor to establish connection to the database
    public Conn() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database (using JDBC URL, username, and password)
            c = DriverManager.getConnection("jdbc:mysql:///employeemanagementsystem", "root", "yuva");

            // Create a statement object to execute SQL queries
            s = c.createStatement();

        } catch (Exception e) {
            // Print stack trace in case of an exception (e.g., database connection failure)
            e.printStackTrace();
        }
    }
}
