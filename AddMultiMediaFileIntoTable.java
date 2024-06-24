package jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddMultiMediaFileIntoTable
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE" , "C##ASh" ,"akshu");
		
		Scanner sc = new Scanner(System.in);
		
		try(con; sc)
		{
			String insertQuery = "insert into emp_info values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(insertQuery);
			
			System.out.print("Enter EMPID (1-99): ");
			int empId = sc.nextInt();  
			ps.setInt(1, empId);
			
			System.out.print("Enter EMP Name : ");
			String empName = sc.next();
			ps.setString(2, empName);
			
			System.out.print("Enter EMP Address : ");
			String empAdd = sc.next();
			ps.setString(3, empAdd);
			
			System.out.print("Enter EMP EMail : ");
			String empEmail = sc.next();
			ps.setString(4, empEmail);
			
			System.out.print("Enter EMP Phone Number : ");
			Long empNo = sc.nextLong();
			ps.setLong(5, empNo);
			
			//System.out.print("Enter EMP Resume Path : ");
			String empResume = "C:\\Users\\prash\\OneDrive\\Documents\\LOGICAL\\akshay Waghle.txt";
			
			var fin = new FileReader(empResume);
			
			ps.setClob(6, fin);
			
			if(0 < ps.executeUpdate())
			{
				System.out.println("Data Stored Successfully....");
			}	
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
