package dynamicQueryProject;
import java.sql.*;
import java.util.*;
public class ElectricBillSystem {
	
	
    private static final String url = "jdbc:mysql://localhost:3306/electricitybill";
    private static final String user = "username";
    private static final String pass = "password";
    static final double rate = 5.0; // Rate per unit for bill calculation

    static Scanner sc = new Scanner(System.in);
   
    // Method  database connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,pass);
    }

    public static void main(String[] args) {
       
        String ch;
        // Main loop to display menu and execute user commands
        do {
            System.out.println("\n---- Electricity Billing System ----");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Record Usage");
            System.out.println("4. Calculate Bill");
            System.out.println("5. Display Bill");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character
            
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    viewCustomers();
                    break;
                case 3:
                    recordUsage();
                    break;
                case 4:
                    calculateBill();
                    break;
                case 5:
                    displayBill();
                    break;
                case 6:
                    System.out.println("Exiting the system...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
            System.out.println("Do you want to continue....");
            ch=sc.next();
        }while(ch.equalsIgnoreCase("y"));
        
        System.out.println("\nThank you using this system.🙂🙂🙂");
    }

   
    // Method to add a new customer to the database
    private static void addCustomer() {
        try  {
        	Connection conn = getConnection();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Address: ");
            String address = sc.nextLine();
            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();

            String query = "INSERT INTO customer (cname, address, contact) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, phone);
            stmt.executeUpdate();

            System.out.println("Customer added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding customer: " + e.getMessage());
        }
    }

    // Method to view all customers from the database
    private static void viewCustomers() {
        try {
        	 Connection conn = getConnection();
            String query = "SELECT * FROM customer";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n--- Customers List ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("cid"));
                System.out.println("Name: " + rs.getString("cname"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("Phone: " + rs.getString("contact"));
                System.out.println("----------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving customers: " + e.getMessage());
        }
    }

    // Method to record monthly usage for a customer
    private static void recordUsage() {
        try {
        	Connection conn = getConnection();
            System.out.print("Enter Customer ID: ");
            int customerId = sc.nextInt();
            sc.nextLine(); // Consume newline character
            System.out.print("Enter Month: ");
            String month = sc.nextLine();
            System.out.print("Enter Units Consumed: ");
            int units = sc.nextInt();

            String query = "INSERT INTO lightuse (cid, month, units) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerId);
            stmt.setString(2, month);
            stmt.setInt(3, units);
            stmt.executeUpdate();

            System.out.println("Usage recorded successfully!");
        } catch (SQLException e) {
            System.err.println("Error recording usage: " + e.getMessage());
        }
    }

    // Method to calculate the bill for a customer based on usage
    private static void calculateBill() {
        try {
        	 Connection conn = getConnection();
            System.out.print("Enter Customer ID: ");
            int customerId = sc.nextInt();
            sc.nextLine(); // Consume newline character
            System.out.print("Enter Month: ");
            String month = sc.nextLine();

            String bQuery = "SELECT units FROM lightuse WHERE cid = ? AND month = ?";
            PreparedStatement bStmt = conn.prepareStatement(bQuery);
            bStmt.setInt(1, customerId);
            bStmt.setString(2, month);
            ResultSet rs = bStmt.executeQuery();

            if (rs.next()) {
                int units = rs.getInt("units");
                double amount = units * rate;

                String billQuery = "INSERT INTO bill (cid, month, units, amount) VALUES (?, ?, ?, ?)";
                PreparedStatement billStmt = conn.prepareStatement(billQuery);
                billStmt.setInt(1, customerId);
                billStmt.setString(2, month);
                billStmt.setInt(3, units);
                billStmt.setDouble(4, amount);
                billStmt.executeUpdate();

                System.out.println("Bill calculated: Amount = " + amount);
            } else {
                System.out.println("No usage data found for the specified customer and month.");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating bill: " + e.getMessage());
        }
    }

    // Method to display the bill for a customer
    private static void displayBill() {
        try {
        	 Connection conn = getConnection();
            System.out.print("Enter Customer ID: ");
            int customerId = sc.nextInt();
            sc.nextLine(); // Consume newline character
            System.out.print("Enter Month: ");
            String month = sc.nextLine();

            String query = "SELECT * FROM bill WHERE cid = ? AND month = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerId);
            stmt.setString(2, month);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- Bill Details ---");
                System.out.println("Customer ID: " + rs.getInt("cid"));
                System.out.println("Month: " + rs.getString("month"));
                System.out.println("Units Consumed: " + rs.getInt("units"));
                System.out.println("Amount: " + rs.getDouble("amount"));
            } else {
                System.out.println("No bill found for the specified customer and month.");
            }
        } catch (SQLException e) {
            System.err.println("Error displaying bill: " + e.getMessage());
        }
    }
}
