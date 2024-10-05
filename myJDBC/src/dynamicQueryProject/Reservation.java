package dynamicQueryProject;
import java.util.*;
import java.sql.*;
public class Reservation {

	public static void main(String[] args) {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		PreparedStatement psmt=null;
		Scanner sc=new Scanner(System.in);
		int rId;
		int choice;
		int roomNo;
		String name,contact,ch;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dynamicQuery","root","Shital2002");
			stmt=con.createStatement();
			do {
				System.out.println("\n1.Reserve Room\n2.View Reservation\n3.Get Room Number\n4.Update Reservation\n5.Delete Reservation");
				System.out.println("Enter your choice:");
				choice=sc.nextInt();
				switch(choice) {
				//Reserve room
				case 1:
					System.out.println("Enter Id:");
			          rId=sc.nextInt();
			          System.out.println("Enter name:");
			          name=sc.next();
			          System.out.println("Enter Room Number");
			          roomNo=sc.nextInt();
			          System.out.println("Enter Contact Number");
			          contact=sc.next();
			          
			          psmt=con.prepareStatement("insert into reservation values(?,?,?,?)");
			          psmt.setInt(1,rId);
			          psmt.setString(2,name);
			          psmt.setInt(3, roomNo);
			          psmt.setString(4, contact);
			          
			          int cnt=psmt.executeUpdate();
			          if(cnt>0)
			          {
			        	  System.out.println("Reservation done successfully");
			          }
			          else
			          {
			        	  System.out.println("Reservation failed");
			          }
			          
			      break;
				
			      
			      //View Reservation
				case 2:
					   stmt.execute("alter table reservation order by rId Asc");
					rs=stmt.executeQuery("select * from reservation");
					System.out.println("Current reservation.....");

     
					  System.out.println("+----------------+-----------------+---------------+----------------------+");
			            System.out.println("| Reservation ID | Guest           | Room Number   | Contact Number       |");
			            System.out.println("+----------------+-----------------+---------------+----------------------+");

			            while (rs.next()) {
			                 rId = rs.getInt(1);
			                name = rs.getString(2);
			                 roomNo = rs.getInt(3);
			                contact = rs.getString(4);

			                
			                System.out.printf("| %-14d | %-15s | %-13d | %-18s   |\n",
			                        rId, name, roomNo, contact);
			            }

			            System.out.println("+----------------+-----------------+---------------+----------------------+");
					   

			         
			            rs=stmt.executeQuery("select count(rId) from reservation");
				       rs.next();
				       int total=rs.getInt(1);
			            System.out.println("Total Number of Room Reserve: "+total);				       
			            System.out.println("\n");
			            
			            break;
		
			            //get Room Number
				case 3:
					System.out.println("Enter reservation Id:");
					rId=sc.nextInt();
				    System.out.println("Enter user Name");
				    name=sc.next();
				    String sql = "SELECT roomNo FROM reservation " +
		                    "WHERE rId = " + rId +
		                    " AND name = '" + name + "'";
				    
				    stmt = con.createStatement();
	                 rs = stmt.executeQuery(sql);
	                 if (rs.next()) {
	                      roomNo = rs.getInt(1);
	                     System.out.println(" Reservation ID is" + rId +
	                             " and Guest " + name + "'s Room Number" + roomNo);
	                 } else {
	                     System.out.println("Reservation not found for the given ID and guest name.");
	                 }
				   
				   break;
				   
				   
				   //Update Reservation
				case 4:
		          	System.out.println("Enter reservation Id  for update room Number:");
		          	rId=sc.nextInt();
		          	
		           System.out.println("Enter room number for update: ");
		           roomNo=sc.nextInt();
		            psmt=con.prepareStatement("update reservation set roomNo = ? where rId = ?");
                    
		            psmt.setInt(1, roomNo);
					 psmt.setInt(2, rId);
					 
					  psmt.executeUpdate();
					  break;
					  
					  //Delete Reservation
				case 5:
					  System.out.println("Enter reservation Id you want to delete record:");
				    	rId=sc.nextInt();
				    	psmt=con.prepareStatement("delete from student where rId=?");
				    	psmt.setInt(1,rId);
				    	int ct=psmt.executeUpdate();
				    	if(ct>0)
				    	{
				    		System.out.println("Delete record successfully"); 
				    	}
				    	else
				    	{
				    		System.out.println("Not Found Reservation Id...");
				    	}
				    	break;
					  
					  
				}
				System.out.println("Do you want to continue.....(Y/N)");
				ch=sc.next();
				
			}while(ch.equalsIgnoreCase("y"));
			System.out.println("Thank you for using Reservation System");
		}catch(Exception e)
		{
			System.out.println(e);
		}

	}

}
