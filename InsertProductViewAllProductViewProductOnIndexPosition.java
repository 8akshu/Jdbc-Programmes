package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertProductViewAllProductViewProductOnIndexPosition 
{
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "C##Ash", "akshu");
		
		Scanner sc = new Scanner(System.in);
		
		try(con ; sc)
		{
			while(true)
			{
				System.out.println(" 1.Insert productdetails \n 2.Retrieve productdetails in forward \n 3.Retrieve productdetails in reverse \n 4.Retrieve 3rd record from top \n 5.Retrieve 3rd record from bottom \n 6.Exit");
				int choice = sc.nextInt();
				
				switch(choice)
				{
				case 1:
				{
					String insertQuery = " insert into product values(?,?,?,?)";
					PreparedStatement ps = con.prepareStatement(insertQuery);
					
					System.out.println("Enter product ID (1 - 100): ");
					int pId = sc.nextInt();
					ps.setInt(1, pId);
					
					System.out.println("Enter product Name : ");
					String pName = sc.next();
					ps.setString(2, pName);
					
					System.out.println("Enter product  Price : ");
					double pPrice = sc.nextDouble();
					ps.setDouble(3, pPrice);
					
					System.out.println("Enter productQuantity : ");
					int pQuantity = sc.nextInt();
					ps.setInt(4, pQuantity);
					
					if( 0 < ps.executeUpdate())
					{
						System.out.println("Product added Successfully...");
					}
					
				}
				break;
				case 2:
				{
					String retriveQuery = "Select * from product ";
					var ps  = con.prepareStatement(retriveQuery);
					
					ResultSet rs = ps.executeQuery();
					ResultSetMetaData md = rs.getMetaData();
					
					for(int i = 1 ; i <= md.getColumnCount() ; i++)
					{
						System.out.print(md.getColumnName(i)+"\t");
					}
					System.out.println("\n =============================================================================");
					
					while(rs.next())
					{
						for(int i = 1 ; i <= md.getColumnCount() ; i++)
						{
							System.out.print(rs.getString(i)+"\t");
						}
						System.out.println();
					}
				}
					break;
				case 3:
				{
					System.out.println("Product Data in Backward Direction....");
					String retriveRev = "select * from product";
					var s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					
					ResultSet rs = s.executeQuery(retriveRev);
					
					ResultSetMetaData md = rs.getMetaData();
					
					for(int i = 1 ; i <= md.getColumnCount() ; i++)
					{
						System.out.print(md.getColumnName(i)+"\t");
					}
					System.out.println("\n =============================================================================");
					rs.afterLast();
					while(rs.previous())
					{
						for(int i = 1 ; i <= md.getColumnCount() ; i++)
						{
							System.out.print(rs.getString(i)+"\t");
						}
						System.out.println();
					}
				}
				break;
				case 4:
				{
					System.out.println("Third recored from top...");
					
					String retriveRev = "select * from product";
					
					var  s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					
					ResultSet rs = s.executeQuery(retriveRev);
					
					ResultSetMetaData md = rs.getMetaData();
					
					for(int i = 1 ; i <= md.getColumnCount() ; i++)
					{
						System.out.print(md.getColumnName(i)+"\t");
					}
					System.out.println("\n =============================================================================");
					rs.absolute(3);
						for(int i = 1 ; i <= md.getColumnCount() ; i++)
						{
							System.out.print(rs.getString(i)+"\t");
						}
						System.out.println();
				}
				break;
				case 5:
				{
					System.out.println("Third recored from Bottom...");
					
					String retriveRev = "select * from product";
					
					var  s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					
					ResultSet rs = s.executeQuery(retriveRev);
					
					ResultSetMetaData md = rs.getMetaData();
					
					for(int i = 1 ; i <= md.getColumnCount() ; i++)
					{
						System.out.print(md.getColumnName(i)+"\t");
					}
					System.out.println("\n =============================================================================");
					rs.absolute(-3);
						for(int i = 1 ; i <= md.getColumnCount() ; i++)
						{
							System.out.print(rs.getString(i)+"\t");
						}
						System.out.println();
				}
				break;
				case 6:
				{
					System.out.println("Thank you..");
					System.exit(0);
				}
				break;
				default:
				{
					System.out.println("Invalid choice");
				}
				break;
				
				}
			}
		}
		
	}

}
