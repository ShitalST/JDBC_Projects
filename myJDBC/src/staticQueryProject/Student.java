package staticQueryProject;

import java.sql.*;
public class Student {

	
		public static void main(String[] args) {
		
			try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/staticQuery","root","Shital2002");
			Statement stmt=con.createStatement();
			stmt.executeUpdate("insert into student values(7,'Pritam','Pune')");
			stmt.executeUpdate("delete from student where rollNo=2");
			stmt.executeUpdate("delete from student where rollNo=7");
			stmt.executeUpdate("update  student set city='Jalna' where rollNo=4");
			stmt.executeUpdate("insert into student values(6,'Pritam','Pune')");
			stmt.executeUpdate("insert into student values(7,'Tushar','Solapur')");
			ResultSet rs=stmt.executeQuery("select * from Student");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
				
				
				
			}
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		}

	}


