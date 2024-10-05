package dynamicQueryProject;
import java.util.*;
import java.sql.*;
public class DigitalVotingSystem {
	  private static final String url = "jdbc:mysql://localhost:3306/digitalvotingsystem";
	    private static final String user = "root";
	    private static final String pass = "Shital2002";
	  
	    static Scanner sc = new Scanner(System.in);
	   
	    // Method  database connection
	    private static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(url,user,pass);
	    }
	public static void main(String[] args) {
	
		int choice;
		String ch;
		try {
		do {
			System.out.println("Welcome to Digital voting System..🙂🙂🙂");
			System.out.println("\n1.Manage Election\n2.Manage Candidates\n3.vote\n4.Result");
			System.out.println("Enter your choice: ");
			choice=sc.nextInt();
			switch(choice) {
			case 1:
				elections();
				break;
			case 2:
				candidates();
				break;
			case 3:
				vote();
				break;
			case 4:
				result();
				break;
				default:
					System.out.println("Invalid choice....");
			
			}
			System.out.println("Do you want to continue....");
			ch=sc.next();
		}while(ch.equalsIgnoreCase("y"));
		
		System.out.println("Thank you for using voting System..🙏🙏🙏");
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	
	
	//start all about election manages
	public static void elections()
	{
		int ch;
		
		try{
		while(true)
		{
			System.out.println("\nManage Elections...");
			System.out.println("\n1.create Elections\n2.Update Elections\n3.View Elections\n4.Delete Elections\n5.Bck to main");
		    System.out.println("Choose any option...");
		    ch=sc.nextInt();
		    switch(ch)
		    {
		    case 1:
		    	addElection();
		    	break;
		    case 2:
		    	updateElection();
		    	break;
		    case 3:
		    	viewElection();
		    	break;
		    case 4:
		    	deleteElection();
		    case 5:
		    	return;
		    	default:
		    		System.out.println("Invalid Choice pls try again..");
		    }
		}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//election methods..
	//1.create election;
	public static void addElection()
	{
		try {
			
			Connection con=getConnection();
			System.out.println("Enter election name:");
			String name=sc.next();
			System.out.println("Enter Start date..(yyyy-MM-DD)");
			String sdate=sc.next();
			System.out.println("Enter End date...(yyyy-MM-DD)");
			String edate=sc.next();
			System.out.println("Enter Status: (Active,complete)");
			String status=sc.next();
			PreparedStatement psmt=con.prepareStatement("insert into elections(ename,sdate,edate,status)values(?,?,?,?)");
			psmt.setString(1, name);
			psmt.setString(2, sdate);
			psmt.setString(3, edate);
			psmt.setString(4, status);
			int cnt=psmt.executeUpdate();
			if(cnt>0)
			{
				System.out.println("Election added Successfully...");
			}
			else
			{
				System.out.println("Failed to add elections try again");
			}
					
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//2.update election
	
	public static void updateElection()
	{
	try {
		Connection con=getConnection();
		System.out.println("Enter election id want to update");
		int eid=sc.nextInt();
		System.out.println("Enter election name ");
		String name=sc.next();
		System.out.println("Enter satrt date (yyyy-mm-dd)");
		String sdate=sc.next();
		System.out.println("Enter end date: (yyyy-mm-dd)");
		String edate=sc.next();
		PreparedStatement psmt=con.prepareStatement("UPDATE elections SET ename = ?, sdate = ?, edate = ? WHERE eid = ?");
		psmt.setString(1,name);
		psmt.setString(2, sdate);
		psmt.setString(3, edate);
		psmt.setInt(4, eid);
		int cnt=psmt.executeUpdate();
		if(cnt>0)
		{
			System.out.println("Record updated Successfully..");
		}
		else
		{
			System.out.println("failed to update  record..");
		}
	}	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	
	
	//3.view election
	public static void viewElection()
	{
		try {
			   Connection con=getConnection();
			
			 Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from elections");

	            System.out.println("\nElection Records:");
	            while (rs.next()) {
	                System.out.println("\n\nID: " + rs.getInt("eid") +
	                        "\nName: " + rs.getString("ename") +
	                        "\nStart Date: " + rs.getDate("sdate") +
	                        "\nEnd Date: " + rs.getDate("edate") +
	                        "\nStatus: " + rs.getString("status"));
	            }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//4.delete election
	public static void deleteElection()
	{
		try {
			
			Connection con=getConnection();
			 System.out.print("Enter Election ID to Delete: ");
	            int eId = sc.nextInt();
	            PreparedStatement stmt = con.prepareStatement("DELETE FROM Elections WHERE eid = ?");
	            stmt.setInt(1, eId);
	            int cnt = stmt.executeUpdate();
	            if (cnt > 0) {
	                System.out.println("Election deleted successfully!");
	            } else {
	                System.out.println("Election not found.");
	            }
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//end all election
	
	
	//start candiadte management
	public static void candidates()
	{
       int ch;
		
		try{
		while(true)
		{
			System.out.println("\nManage Candidates...");
			System.out.println("\n1.add Candidates\n2.Update candidates\n3.View Candidates\n4.Delete candidates\n5.Bck to main");
		    System.out.println("Choose any option...");
		    ch=sc.nextInt();
		    switch(ch)
		    {
		    case 1:
		    	addCandidate();
		    	break;
		    case 2:
		    	updateCandidate();
		    	break;
		    case 3:
		    	viewCandidate();
		    	break;
		    case 4:
		    	deleteCandidate();
		    case 5:
		    	return;
		    	default:
		    		System.out.println("Invalid Choice pls try again..");
		    }
		}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//1.method of candidate
	public static void addCandidate()
	{
		try {
			Connection con=getConnection();
			System.out.println("Enter election id:  ");
			int eid=sc.nextInt();
			System.out.println("Enter Candidate Name: ");
			String name=sc.next();
			PreparedStatement psmt=con.prepareStatement("insert into Candidates (eid,cname)values(?,?)");
			psmt.setInt(1,eid);
			psmt.setString(2, name);
			int cnt=psmt.executeUpdate();
			if(cnt>0)
			{
				System.out.println("Candidate added Successfully");
			}
			else
			{
				System.out.println("Failed to add candidate");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//2.update candidate
	public static void updateCandidate()
	{
		 try {
			 Connection con=getConnection();
	            System.out.print("Enter Candidate ID to Update: ");
	            int cId = sc.nextInt();
	            System.out.print("Enter New Candidate Name: ");
	            sc.nextLine(); // consume newline
	            String name = sc.next();

	            PreparedStatement stmt = con.prepareStatement("UPDATE Candidates SET name = ? WHERE cid = ?");
	            stmt.setString(1, name);
	            stmt.setInt(2, cId);
	            int cnt = stmt.executeUpdate();
	            if (cnt > 0) {
	                System.out.println("Candidate updated successfully!");
	            } else {
	                System.out.println("Candidate not found.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	//3.view candidate
	public static void viewCandidate()
	{
		  try {
			  Connection con=getConnection();
	            System.out.print("Enter Election ID to View Candidates: ");
	            int eId = sc.nextInt();
	            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Candidates WHERE eid = ?");
	            stmt.setInt(1, eId);
	            ResultSet rs = stmt.executeQuery();

	            System.out.println("\nCandidate Records:");
	            while (rs.next()) {
	                System.out.println("\nID: " + rs.getInt("cid") +
	                        "\nName: " + rs.getString("cname"));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	//4.delete candidate
	public static void deleteCandidate()
	{
		 try {
			 Connection con=getConnection();
	            System.out.print("Enter Candidate ID to Delete: ");
	            int cId = sc.nextInt();
	            PreparedStatement stmt = con.prepareStatement( "DELETE FROM Candidates WHERE cid = ?");
	            stmt.setInt(1, cId);
	            int cnt = stmt.executeUpdate();
	            if (cnt > 0) {
	                System.out.println("Candidate deleted successfully!");
	            } else {
	                System.out.println("Candidate not found.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	//end candidate
	
	public static void vote()
	{
		
		   try {
			   Connection con=getConnection();
			   
	            System.out.print("Enter Election ID: ");
	            int eId = sc.nextInt();
	            System.out.print("Enter Candidate ID: ");
	            int cId = sc.nextInt();
	            System.out.print("Enter Your Voter ID: ");
	            sc.nextLine(); // consume newline
	            String voterId = sc.next();
	            PreparedStatement stmt = con.prepareStatement("INSERT INTO Votes (eid, cid, voterid) VALUES (?, ?, ?)");
	            stmt.setInt(1, eId);
	            stmt.setInt(2, cId);
	            stmt.setString(3, voterId);
	            stmt.executeUpdate();
	            System.out.println("Vote cast successfully!");

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	public static void result()
	{
		  try {
			  Connection con=getConnection();
	            System.out.print("Enter Election ID to View Results: ");
	            int eId = sc.nextInt();

	            String sql = "SELECT c.cname, COUNT(v.vid) AS vcount " +
	                         "FROM Votes v " +
	                         "JOIN Candidates c ON v.cid = c.cid " +
	                         "WHERE v.eid = ? " +
	                         "GROUP BY c.cname";
	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setInt(1, eId);
	            ResultSet rs = stmt.executeQuery();

	            System.out.println("\nElection Results:");
	            while (rs.next()) {
	                System.out.println("\nCandidate: " + rs.getString("cname") +
	                        "\nVotes: " + rs.getInt("vcount"));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
}
