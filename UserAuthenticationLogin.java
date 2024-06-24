package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserAuthenticationLogin 
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "C##Ash", "akshu");
		Scanner sc = new Scanner(System.in);
		
		try(con ; sc)
		{
			while(true)
			{
				System.out.println("Welcome To Login System :");
				System.out.println(" 1. Login \n 2. Exit");
				int choice = sc.nextInt();
				
				switch(choice)
				{
				case 1 :
				{
					String loginQuery = "select * from entry where userName like ? AND password like ? ";
					PreparedStatement ps = con.prepareStatement(loginQuery);
					
					System.out.print("Enter UserName :");
					String userName = sc.next();
					ps.setString(1, userName);
					
					System.out.print("Enter password :");
					String password = sc.next();
					ps.setString(2, password);
					
					ResultSet rs = ps.executeQuery();
					
					if(rs.next())
					{
						System.out.println("Login SuccessFul");
					}
					else
					{
						System.out.println("invalid user");
					}
				}
				break;
				case 2 :
				{
					System.out.println("Thank you....\n Exiting....");
					System.exit(0);
				}
				break;
				default :
				{
					System.out.println("Enter Valid Option ");
				}
				break;
					
				}
			}
		}
	}
}
