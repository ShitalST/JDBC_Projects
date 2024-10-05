package dynamicQueryProject;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class StudentManagementSystem {
	static final String url = "jdbc:mysql://localhost:3306/studentmanagement";
     static final String user = "root";
     static final String pass = "Shital2002";
    
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
			System.out.println("Welcome to Student Management System..🙂🙂🙂");
			System.out.println("\n1.Manage Students\n2.Manage Course\n3.Manage Attendance\n4. Result");
			System.out.println("Enter your choice: ");
			choice=sc.nextInt();
			switch(choice) {
			case 1:
				student();
				break;
			case 2:
				course();
				break;
			case 3:
				attendance();
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

    
    
    //all about manage students

	   public static void student()
		{
			int ch;
			
			try{
			while(true)
			{
				
				System.out.println("\nManage Students...");
				System.out.println("\n1.Add Students\n2.Update Students\n3.View All Students\n4.Back to main menu");

				System.out.println("Choose any option...");
			    ch=sc.nextInt();
			    switch(ch)
			    {
			    case 1:
			    	addStudent();
			    	break;
			    case 2:
			    	updateStudent();
			    	break;
//			    case 3:
//			    	deleteStudent();
//			    	break;
			    case 3:
			    	
			    	viewStudent();
			    case 4:
			    	return;
			    	default:
			    		System.out.println("Invalid Choice pls try again..");
			    }
			}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	   
	   
	   //1.addstudent
	   public static void addStudent()
	   {
		   try {
			   Connection con=getConnection();
			   System.out.print("Enter name: ");
		        String name = sc.next();
		       
		        System.out.print("Enter phone: ");
		        String phone = sc.next();
		        System.out.print("Enter address: ");
		        String address = sc.next();
		        PreparedStatement stmt = con.prepareStatement("INSERT INTO student (sname,contact, address) VALUES ( ?, ?, ?)");
		        stmt.setString(1, name);
	           
	            stmt.setString(2, phone);
	            stmt.setString(3, address);
	            int cnt=stmt.executeUpdate();
	            if(cnt>0) {
	            System.out.println("Student added successfully.");
	            }
	            else
	            {
	            	System.out.println("Failed to add student...try again");
	            }
		   }catch(Exception e)
		   {
			   e.printStackTrace();;
		   }
	   }
    
	   //2.updateStudent method
	   public static void updateStudent()
	   {
		   try {
			   Connection con=getConnection();
			   
			   System.out.print("Enter student ID to update: ");
		        int id = sc.nextInt();
		        System.out.print("Enter new name: ");
		        String name = sc.next();
		    
		        System.out.print("Enter new contact: ");
		        String phone = sc.next();
		        System.out.print("Enter new address: ");
		        String address = sc.next();

		      
		             PreparedStatement stmt = con.prepareStatement("UPDATE student SET sname = ?,  contact = ?, address = ? WHERE sid = ?");
		            stmt.setString(1, name);
		          
		            stmt.setString(2, phone);
		            stmt.setString(3, address);
		            stmt.setInt(4, id);
		            int cnt = stmt.executeUpdate();
		            if (cnt > 0) {
		                System.out.println("Student updated successfully.");
		            } else {
		                System.out.println("Student not found.");
		            }
		        
			   
			   
		   }catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		   
	   }
	   
	   //3.deleteStudent method
//	   public static void deleteStudent() {
//		  try {
//			  Connection con=getConnection();
//			  System.out.print("Enter student ID to delete: ");
//		        int id = sc.nextInt();
//
//		       
//		             PreparedStatement stmt = con.prepareStatement("DELETE FROM student WHERE sid = ?");
//		            stmt.setInt(1, id);
//		            int cnt = stmt.executeUpdate();
//		            if (cnt > 0) {
//		                System.out.println("Student deleted successfully.");
//		            } else {
//		                System.out.println("Student not found.");
//		            }
//		        
//			  
//		  } catch(Exception e) {
//			  e.printStackTrace();
//		  }
//		   
//	   }
//	   
	   //4.viewStudent method
	   
	   public static void viewStudent()
	   {
		   try {
			   Connection con=getConnection();
			 
		             Statement stmt = con.createStatement();
		             ResultSet rs = stmt.executeQuery("SELECT * FROM student");

		            System.out.println("\n--- Student List ---");
		            while (rs.next()) {
		                int id = rs.getInt("sid");
		                String name = rs.getString("sname");
		              
		                String phone = rs.getString("contact");
		                String address = rs.getString("address");
		                System.out.printf("\nID: %d, Name: %s, Contact: %s, Address: %s%n",
		                                  id, name, phone, address);
		            }
		        
			   
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
		   
	   }
	   
	   //All about course
	   
	   public static void course()
		{
			int ch;
			
			try{
			while(true)
			{
				
				System.out.println("\nManage Courses...");
				System.out.println("\n1.Add Course\n2.Update Course\n3.Delete Course\n4.View All Course\n5.Back to main menu");

				System.out.println("Choose any option...");
			    ch=sc.nextInt();
			    switch(ch)
			    {
			    case 1:
			    	addCourse();
			    	break;
			    case 2:
			    	updateCourse();
			    	break;
			    case 3:
			    	deleteCourse();
			    	break;
			    case 4:
			    
			    	viewCourse();
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
	   //course method
	   //1.add course
	   public static void addCourse()
	   {
		   try {
			   Connection con=getConnection();
		   
			   System.out.print("Enter course name: ");
		        String courseName = sc.next();
		        
		        System.out.print("Enter credits: ");
		        int credits = sc.nextInt();

		     
		             PreparedStatement stmt = con.prepareStatement("INSERT INTO course (cname, credits) VALUES (?, ?)");
		            stmt.setString(1, courseName);
		            stmt.setInt(2, credits);
		           int cnt=  stmt.executeUpdate();
		           if(cnt>0)
		           {
		           System.out.println("Course added successfully.");
		           }else
		           {
		        	   System.out.println("Failed to add course...try again");
		           }
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	   
	   //2.update course
	   public static void updateCourse()
	   {
		   try {
			   Connection con=getConnection();
			   System.out.print("Enter course ID to update: ");
		        int id = sc.nextInt();
		        System.out.print("Enter new course name: ");
		        String courseName = sc.next();
		        System.out.print("Enter new credits: ");
		        int credits = sc.nextInt();
		        PreparedStatement stmt = con.prepareStatement("UPDATE course SET cname = ?,  credits = ? WHERE cid = ?");
		            stmt.setString(1, courseName);
		            
		            stmt.setInt(2, credits);
		            stmt.setInt(3, id);
		            int cnt = stmt.executeUpdate();
		            if (cnt > 0) {
		                System.out.println("Course updated successfully.");
		            } else {
		                System.out.println("Course not found.");
		            }
		        
			   
			   
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
         //3/delete course
	   public static void deleteCourse()
	   {
		   try {
			   Connection con=getConnection();
		           System.out.print("Enter course ID to delete: ");
		        int id = sc.nextInt();
		             PreparedStatement stmt = con.prepareStatement("DELETE FROM course WHERE cid = ?");
		            stmt.setInt(1, id);
		            int cnt = stmt.executeUpdate();
		            if (cnt > 0) {
		                System.out.println("Course deleted successfully.");
		            } else {
		                System.out.println("Course not found.");
		            }
		        
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	   //4.View all course
	   public static void viewCourse()
	   {
		   try {
			   Connection con=getConnection(); 
		             Statement stmt = con.createStatement();
		             ResultSet rs = stmt.executeQuery("SELECT * FROM course");

		            System.out.println("\n--- Course List ---");
		            while (rs.next()) {
		                int id = rs.getInt("cid");
		                String courseName = rs.getString("cname");
		                int credits = rs.getInt("credits");
		                System.out.printf("ID: %d, Name: %s,  Credits: %d%n",
		                                  id, courseName,  credits);
		            }
		        
			   
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	   
	   //manage attendance
	   public static void attendance()
	 		{
	 			int ch;
	 			
	 			try{
	 			while(true)
	 			{
	 				
	 				System.out.println("\nManage Attendance...");
	 				System.out.println("\n1.Record Attendance\n2.View Attedance\n3.back to main menu");

	 				System.out.println("Choose any option...");
	 			    ch=sc.nextInt();
	 			    switch(ch)
	 			    {
	 			    case 1:
	 			    	recordAttendance();
	 			    	break;
	 			    case 2:
	 			    	viewAttendance();
	 			    	break;
	 			    case 3:
	 			    	return;
	 			   
	 			    	default:
	 			    		System.out.println("Invalid Choice pls try again..");
	 			    }
	 			}
	 				
	 			}catch(Exception e) {
	 				e.printStackTrace();
	 			}
	 			
	 		}
	   
	   //All about Attendance
	   public static void recordAttendance()
	   {
		   
		   try {
			   
			   Connection con=getConnection();
			   System.out.print("Enter student ID: ");
		        int studentId = sc.nextInt();
		        System.out.print("Enter course ID: ");
		        int courseId = sc.nextInt();
		        System.out.print("Enter status (Present, Absent, Leave): ");
		        String status = sc.next();

		             PreparedStatement stmt = con.prepareStatement("INSERT INTO attendence (sid, cid, adate, status) VALUES (?, ?, CURDATE(), ?)");
		            stmt.setInt(1, studentId);
		            stmt.setInt(2, courseId);
		            stmt.setString(3, status);
		            stmt.executeUpdate();
		            System.out.println("Attendance recorded successfully.");
		        
		   }catch(Exception e)
		   {
			   e.printStackTrace();
		   }
	   }
	   
	   public static void viewAttendance()
	   {

		   try {
			   Connection con=getConnection();
			   
			   String query = "SELECT a.aid, s.sname AS sname, c.cname, a.adate, a.status FROM attendence a " +
                       "JOIN student s ON a.aid = s.sid " +
                       "JOIN course c ON a.aid = c.cid";
       
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query);

            System.out.println("\n--- Attendance List ---");
            while (rs.next()) {
                int id = rs.getInt("aid");
                String studentName = rs.getString("sname");
                String courseName = rs.getString("cname");
                Date attendanceDate = rs.getDate("adate");
                String status = rs.getString("status");
                System.out.printf("ID: %d, Student: %s, Course: %s, Date: %s, Status: %s%n",
                                  id, studentName, courseName, attendanceDate, status);
            }
        
		   }catch(Exception e)
		   {
			   e.printStackTrace();
		   }
	   }
	   
	   //all about result
	   public static void result()
		{
			int ch;
			
			try{
			while(true)
			{
				
				System.out.println("\nManage Result...");
				System.out.println("\n1.Record Grade\n2.View Grades\n3.back to main menu");

				System.out.println("Choose any option...");
			    ch=sc.nextInt();
			    switch(ch)
			    {
			    case 1:
			    	recordGrade();
			    	break;
			    case 2:
			    	viewGrade();
			    	break;
			    case 3:
			    	return;
			   
			    	default:
			    		System.out.println("Invalid Choice pls try again..");
			    }
			}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	   
	   public static void recordGrade()
	   {
		   try {
			   Connection con=getConnection();
			   System.out.print("Enter student ID: ");
		        int studentId = sc.nextInt();
		        System.out.print("Enter course ID: ");
		        int courseId = sc.nextInt();
		        System.out.print("Enter grade (e.g., A, B, C): ");
		        String grade = sc.next();

		             PreparedStatement stmt = con.prepareStatement("INSERT INTO grade (sid, cid, grade) VALUES (?, ?, ?)");
		            stmt.setInt(1, studentId);
		            stmt.setInt(2, courseId);
		            stmt.setString(3, grade);
		            stmt.executeUpdate();
		            System.out.println("Grade recorded successfully.");
		        
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	   
	   public static void viewGrade()
	   {
		   try {
			   Connection con=getConnection();
			   String query = "SELECT g.gid, s.sname AS sname, c.cname, g.grade FROM grade g " +
                       "JOIN student s ON g.gid = s.sid " +
                       "JOIN course c ON g.gid = c.cid";
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query);

            System.out.println("\n--- Grades List ---");
            while (rs.next()) {
                int id = rs.getInt("gid");
                String studentName = rs.getString("sname");
                String courseName = rs.getString("cname");
                String grade = rs.getString("grade");
                System.out.printf("ID: %d, Student: %s, Course: %s, Grade: %s%n",
                                  id, studentName, courseName, grade);
            }
           
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
 }
