package jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertImageIntoTable
{
	public static void main(String[] args) throws Exception 
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","C##ash","akshu");
	
		PreparedStatement ps = con.prepareStatement("insert into Player_info values(?,?,?,?)");
		
		ps.setInt(1, 2);
		ps.setString(2, "Akshay");
		ps.setBinaryStream(3, new FileInputStream("C:\\Users\\prash\\OneDrive\\Desktop\\profile.jpg"));
		ps.setString(4, "08-05-2001");
		if(ps.executeUpdate() > 0)
		{
			System.out.println("Success");
		}
	}
}
