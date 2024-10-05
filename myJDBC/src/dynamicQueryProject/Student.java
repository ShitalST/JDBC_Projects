package dynamicQueryProject;
import java.sql.*;
import java.util.*;
public class Student {
	public static void main(String[]args) {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		PreparedStatement ps1=null,//insert 
				ps2=null,           //update 
				ps3=null,        //delete
		        ps4=null ;       //search
		Scanner sc=new Scanner(System.in);
		String name,city;
		int roll;
		int choice;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dynamicQuery","root","Shital2002");
			stmt=con.createStatement();
			ps1=con.prepareStatement("insert into student values (?,?,?)");
			ps2=con.prepareStatement("update student set name=? where rollNo=?");
			do {
				System.out.println("\n1.Insert Record\n2.View Record\n3.Delete Record\n4.Update record\n5.Search student by name.\n6.Exit");
			    System.out.println("Enter Your Choice: ");
			    choice=sc.nextInt();
			    switch(choice){
			        
			    //insert record
			    case 1:
			    	System.out.println("Enter Roll: ");
			    	roll=sc.nextInt();
			    	System.out.println("Enter Name: ");
			    	name=sc.next();
			    	System.out.println("Enter City: ");
			    	city=sc.next();
			    	 ps1.setInt(1, roll);
					   ps1.setString(2, name);
					   ps1.setString(3, city);
					   ps1.executeUpdate();
					   break;
					   
					 //View Record
					   
			    case 2:
			    	rs=stmt.executeQuery("select * from student");
			    	while(rs.next()) {
			    		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			    	}
			    	break;
			    	
			    	//delete Record
			    	
			    case 3:
			    	System.out.println("Enter roll Number you want to delete record:");
			    	roll=sc.nextInt();
			    	ps3=con.prepareStatement("delete from student where rollNo=?");
			    	ps3.setInt(1,roll);
			    	ps3.executeUpdate();
			    	break;
			    	
			    	
			    	//update record
			    	
			    case 4:
			   	 
					 System.out.println("Enter Roll No for Update City ");
					  roll = sc.nextInt();
					 
					 System.out.println("Enter City For Update");
					  city = sc.next();
					 
					  ps2 = con.prepareStatement("update student set city = ? where rollNo = ?");
					
					 ps2.setString(1, city);
					 ps2.setInt(2, roll);
					 
					  ps2.executeUpdate();
			    	break;
			    	//serch student by name
			    case 5:
			    	System.out.println("Enter name you want to be search:");
			    	name=sc.next();
			    	ps4=con.prepareStatement("select * from student where name like ?");
			    	ps4.setString(1,"%"+name+"%");
			    	rs=ps4.executeQuery();
			    	
			    
			    	if(rs.next())
			    	{
			    		System.out.println("Student Id:"+rs.getInt(1)+" \n "+"Student Name:"+rs.getString(2)+"  \n"+"Student city:"+rs.getString(3));
			    	}
			    	else
			    	{
			    		System.out.println("No Student found with the name");
			    	}
			    }
			}while(choice!=6);
			System.out.println("Thank you...🙂🙂🙂");
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	
}
