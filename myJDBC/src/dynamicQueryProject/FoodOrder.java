package dynamicQueryProject;

import java.sql.*;

import java.util.*;

public class FoodOrder {
	static final  Scanner sc=new Scanner(System.in);
//	static Connection con=null;
//	static Statement stmt=null;
//	static ResultSet rs=null;
//	static PreparedStatement psmt=null;
	static String url="jdbc:mysql://localhost:3306/foodOrder";
	static String user="root";
	static String pass="Shital2002";
	  private static Connection getConnection() throws SQLException {
		  
	        return DriverManager.getConnection(url, user,pass);
	    }
	public static void main(String[] args) {
		
		
       int choice;
       String ch;
		try {
			
			
			do {
				System.out.println("\n1.Login as user\n2.Login as Admin");
				System.out.println("Enter your choice:");
				choice=sc.nextInt();
				switch(choice) {
				
				  case 1:
	                    userMenu();
	                    break;
	              case 2:
	                    adminMenu();
	                    break;
	              default:
	                    System.out.println("Invalid choice, please try again.");
	            }
	                    	
				
				System.out.println("\n");
				System.out.println("Do you want to continue.....(Y/N)");
				ch=sc.next();
				
			}while(ch.equalsIgnoreCase("y"));
			
			System.out.println("\nThank you for using Restaurant System...🙂🙂");
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
		
		
		}
	
	  private static void userMenu() {
	        while (true) {
	            System.out.println("\nUser Menu");
	            System.out.println("1. View Menu");
	            System.out.println("2. Place Order");
	            System.out.println("3. Back to Main Menu");
	            System.out.print("Choose an option: ");
	            int choice = sc.nextInt();

	            switch (choice) {
	                case 1:
	                    viewMenu();
	                    break;
	                case 2:
	                    placeOrder();
	                    break;
	                case 3:
	                    return; // Go back to the main menu
	                default:
	                    System.out.println("Invalid choice, please try again.");
	            }
	        }
	    }
	

	  
	// Admin menu: 
	    private static void adminMenu() {
	        while (true) {
	            System.out.println("\nAdmin Menu");
	            System.out.println("1. Add Food Item");
	            System.out.println("2. Update Food Item");
	            System.out.println("3. Delete Food Item");
	            System.out.println("4. Back to Main Menu");
	            System.out.print("Choose an option: ");
	            int choice = sc.nextInt();

	            switch (choice) {
	                case 1:
	                    addFoodItem();
	                    break;
	                case 2:
	                    updateFoodItem();
	                    break;
	                case 3:
	                    deleteFoodItem();
	                    break;
	                case 4:
	                    return; // Go back to the main menu
	                default:
	                    System.out.println("Invalid choice, please try again.");
	            }
	        }
	    }

	    
	    
	    
	    //user menu method
	    // Method to view the menu
	    private static void viewMenu() {
	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM fooditem")) {
	            ResultSet rs = stmt.executeQuery();
	            System.out.println("\nMenu:");
	            while (rs.next()) {
	                System.out.printf("%d. %s - $%.2f - %s\n", rs.getInt("fid"), rs.getString("fname"), rs.getDouble("price"), rs.getString("description"));
	            }
	        } catch (SQLException e) {
	            System.out.println("Error fetching menu: " + e.getMessage());
	        }
	    }

	    // Method to place an order
	    private static void placeOrder() {
	        System.out.print("Enter food item ID to order: ");
	        int foodItemId = sc.nextInt();
	        System.out.print("Enter quantity: ");
	        int quantity = sc.nextInt();
	        int userId = 1; 

	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement("INSERT INTO orders (uid, fid, quantity) VALUES (?, ?, ?)")) {
	            stmt.setInt(1, userId);
	            stmt.setInt(2, foodItemId);
	            stmt.setInt(3, quantity);
	            stmt.executeUpdate();
	            System.out.println("Order placed successfully.");
	        } catch (SQLException e) {
	            System.out.println("Error placing order: " + e.getMessage());
	        }
	    }

	    
	    //admin method
	    // Method to add a food item
	    private static void addFoodItem() {
	        System.out.print("Enter food name: ");
	        String name = sc.next();
	        System.out.print("Enter price: ");
	        double price = sc.nextDouble();
	        System.out.print("Enter description: ");
	        String description = sc.next();

	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement("INSERT INTO fooditem (fname, price, description) VALUES (?, ?, ?)")) {
	            stmt.setString(1, name);
	            stmt.setDouble(2, price);
	            stmt.setString(3, description);
	            stmt.executeUpdate();
	            System.out.println("Food item added successfully.");
	        } catch (Exception e) {
	            System.out.println("Error adding food item: " + e.getMessage());
	        }
	    }

	    // Method to update a food item
	    private static void updateFoodItem() {
	        System.out.print("Enter food item ID to update: ");
	        int id = sc.nextInt();
	        System.out.print("Enter new food name: ");
	        String name = sc.next();
	        System.out.print("Enter new price: ");
	        double price = sc.nextDouble();
	        System.out.print("Enter new description: ");
	        String description = sc.next();

	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement("UPDATE fooditems SET fname = ?, price = ?, description = ? WHERE fid = ?")) {
	            stmt.setString(1, name);
	            stmt.setDouble(2, price);
	            stmt.setString(3, description);
	            stmt.setInt(4, id);
	            int cnt = stmt.executeUpdate();
	            if (cnt > 0) {
	                System.out.println("Food item updated successfully.");
	            } else {
	                System.out.println("Food item not found.");
	            }
	        } catch (SQLException e) {
	            System.out.println("Error updating food item: " + e.getMessage());
	        }
	    }

	    // Method to delete a food item
	    private static void deleteFoodItem() {
	        System.out.print("Enter food item ID to delete: ");
	        int id = sc.nextInt();

	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement("DELETE FROM fooditems WHERE fid = ?")) {
	            stmt.setInt(1, id);
	            int cnt = stmt.executeUpdate();
	            if (cnt > 0) {
	                System.out.println("Food item deleted successfully.");
	            } else {
	                System.out.println("Food item not found.");
	            }
	        } catch (SQLException e) {
	            System.out.println("Error deleting food item: " + e.getMessage());
	        }
	    }

	    
	  
}


