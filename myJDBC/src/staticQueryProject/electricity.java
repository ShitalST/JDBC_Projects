package staticQueryProject;

import java.sql.*;
import java.util.*;
public class electricity{

static   Scanner sc=new Scanner(System.in);
static String url="jdbc:mysql://localhost:3306/electricitybill";
static String pass="password";
static String user="root";

  private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }


public static void main(String[]args)
{
String ch;
do{
int choice;
System.out.println("Welcome to electric bill System");
System.out.println("\n1.Add Customer\n2.View Customer\n3.Usages Recrd\n4.Calculate bill\n5.display Bill");
System.out.println("Enter your choice:");
choice=sc.nextInt();
switch(choice)
{
case 1:
addcustomer(sc);
break;
case 2:
  viewcustomer();
break;
case 3:
usagesrecord();
break;
case 4:
calculateBill();
break;
case 5:displayBill();
break;
default:
System.out.println("Invalid choice....");
}


System.out.println("Do you want to continue:..");
  ch=sc.next();

}while(ch.equalsIgnoreCase("y"));

System.out.println("Thank you for using this System");

}

//addcustomer method
 private static void addcustomer(Scanner sc) {
        try {
        	Connection conn = getConnection();
            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.print("Enter Address: ");
            String address = sc.next();
            System.out.print("Enter Phone: ");
            String phone = sc.next();

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

//view customer
public static void viewcustomer()
{
try{
Connection con=getConnection();
PreparedStatement psmt=con.prepareStatement("select * from customer");
ResultSet rs=psmt.executeQuery();
while(rs.next())
{
  System.out.println("ID: " + rs.getInt("cid"));
                System.out.println("Name: " + rs.getString("cname"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("Phone: " + rs.getString("contact"));
                System.out.println("----------------------");
}

}catch(Exception e){
System.out.println(e);
}
}

//usage record
public static void usagesrecord(){
try{


}catch(Exception e)
{
System.out.println(e);
}
}

//
public static void calculateBill()
{
try{
System.out.println("Total Bill ");
}catch(Exception e)
{
System.out.println(e);
}

}


//display bill
public static void displayBill()
{
try{
System.out.println("Bill table");
}catch(Exception e)
{
System.out.println(e);
}
} 
}
