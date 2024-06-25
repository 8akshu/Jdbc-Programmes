package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DescTableAndInsertStudentDynamically
{
	public static void main(String[] args) throws Exception
	{
		 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##Ash" ,"akshu");
		 
		 Statement s = con.createStatement();
		 
		 ResultSet rs = s.executeQuery("Select * from Student_info ");
		 
		 ResultSetMetaData md = rs.getMetaData();
		 
		 System.out.println("Table Structure :\n==========================");
		 System.out.println("number of column"+md.getColumnCount()+"\n=============================");
		 
		 for(int i =1 ; i <= md.getColumnCount(); i++)
		 {
			 System.out.println(md.getColumnName(i) +"    "+md.getColumnTypeName(i));
		 }
		 
		 insertData(con);
	}
	
	public static void insertData(Connection con) throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Add Student:\n ============ ");
		PreparedStatement ps = con.prepareStatement("insert into student_info values(?,?,?,?,?)");
		System.out.print("Enter Student Id :");
		ps.setInt(1, sc.nextInt());
		
		System.out.print("Enter Student Name :");
		ps.setString(2, sc.next());
		
		System.out.print("Enter Student Roll number :");
		ps.setInt(3, sc.nextInt());
		
		System.out.print("Enter Student Address :");
		ps.setString(4, sc.next());
			
		ps.setDate(5, new Date(0, 0, 0));
		
		if(ps.executeUpdate() > 0)
		{
			System.out.println("Data Entered Successfully.....");
		}
		else
		{
			System.out.println("Row not Inserted.....");
		}
	}
}
