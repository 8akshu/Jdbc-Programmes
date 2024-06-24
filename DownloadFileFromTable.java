package jdbc;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DownloadFileFromTable 
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException 
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE" , "C##ASh" ,"akshu");
		
		Scanner sc = new Scanner(System.in);
		
		try(con ; sc)
		{
			Statement s = con.createStatement();
			
			ResultSet rs = s.executeQuery("select * from emp_Info");
			
			while(rs.next())
			{
				Reader path = rs.getCharacterStream(6);
				
				var fout = new FileOutputStream("D://Love//aky.txt");
				
				int k=0;
				
				while((k=path.read())!=-1)
				{
				 fout.write(k);
				}
			}
			
			System.out.println("Successfulll.........");
		}
	}
}
