package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginRegisterViewUpdateDeleteOpration 
{
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException  
	{
		Scanner sc = new Scanner(System.in);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "C##Ash" , "akshu");
		
		System.out.println(" 1. Register \n 2. Login");
		int choice = sc.nextInt();
		
		try(con ; sc)
		{
			switch(choice)
			{
			case 1: 
			{
				
				String insertQuery = "insert into customer values (?,?,?,?,?,?,?)" ;
				PreparedStatement ps = con.prepareStatement(insertQuery);
				
				System.out.print("Enter Customer Id : ");
				Integer customerId = sc.nextInt();
				ps.setInt(1, customerId);
				
				System.out.print("Enter Customer name : ");
				String name = sc.next();
				ps.setString(2, name);
				
				System.out.print("Enter Customer password : ");
				String password = sc.next();
				ps.setString(3, password);
				
				System.out.print("Enter Customer FName : ");
				String FName = sc.next();
				ps.setString(4, FName);
				
				System.out.print("Enter Customer LName : ");
				String LName = sc.next();
				ps.setString(5, LName);
				
				System.out.print("Enter Customer mailId : ");
				String mailId = sc.next();
				ps.setString(6, mailId);
				
				System.out.print("Enter Customer ph_No : ");
				String ph_No = sc.next();
				ps.setString(7, ph_No);
				
				PreparedStatement idValidation = con.prepareStatement("select * from customer where customerId = ? ");
				idValidation.setInt(1, customerId);
				
				ResultSet rs = idValidation.executeQuery();
				if(rs.next())
				{
					System.out.println("Customer id Already talken by other...");
				}
				else
				{
					if(0 < ps.executeUpdate())
					{
						System.out.println("Register Success....");
					}
				}	
			}
				break;
				
			case 2: 
			{
				PreparedStatement login = con.prepareStatement("select * from customer where CustomerId = ? AND password like ?");
				
				System.out.println("Enter Customer CustomerId : ");
				Integer customerId = sc.nextInt(); 
				login.setInt(1, customerId);
				
				System.out.println("Enter password : ");
				String password = sc.next();
				login.setString(2, password);
				
				ResultSet rs = login.executeQuery();
				
				if(rs.next())
				{
					ResultSet userInfo = login.executeQuery();
					
					ResultSetMetaData md = userInfo.getMetaData();
					
					userInfo.next();
					for(int i = 1 ; i <= md.getColumnCount() ; i++)
					{
						System.out.println(md.getColumnName(i) +" : "+userInfo.getString(i));
					}
					
					boolean flag = true;
					while(flag)
					{
						System.out.println("\n\n 1.Show All Customer \n 2.Update Customer MailId & ph_No "); 
						System.out.println(" 3.Delete Customer Name Start With \n 4.Show Even Id Customers \n 5.Exit");
						int option = sc.nextInt();
						
						switch(option)
						{
							case 1 :
							{
								showAllCustomer(con);
							}
								break;
							case 2 :
							{
								updateCustomer(con, customerId);
							}
								break;
							case 3 :
							{
								System.out.println("Enter letter to delete name start with : ");
								String preffix = sc.next();
								deleteCustomerNameStartWith(con, preffix);
							}
								break;
							case 4 :
							{
								showEvenIdCustomers(con);
							}
								break;
							case 5 :
							{
								flag = false;
								System.out.println("Thank you ....\n Exiting.........");
								System.exit(0);	
							}
							break;
							default :
							{
								System.out.println("InValid Option ....");
							}	
						}
					}
				}
				else
				{
					System.out.println("Invalid Process");
				}
			}
			break;
			default: 
			{
				System.out.println("Invalid Option !!!");
			}
				break;	
			}
		}
		
	}
	
	public static void showAllCustomer(Connection con) throws SQLException
	{
		System.out.println("Customes Available Are :- ");
		
		Statement s = con.createStatement();
		
		ResultSet rs = s.executeQuery("Select  customerId, FName, LName from customer ");
		
		ResultSetMetaData md = rs.getMetaData();
		for(int i = 1 ;  i<=  md.getColumnCount() ; i++)
		{
			System.out.print(md.getColumnName(i)+"\t");
		}
		System.out.println();
		
		while(rs.next())
		{
			for(int i =1 ; i <= md.getColumnCount() ; i++)
			{
				System.out.print(rs.getString(i)+"\t");
			}
			System.out.println();
		}
		
	}
	
	public static void updateCustomer(Connection con ,Integer customerId) throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		String updateQuery = "update customer set mailId = ? , ph_no = ? where customerId = ? ";
		PreparedStatement ps = con.prepareStatement(updateQuery);
		
		System.out.println("Enter new MailId : ");
		String mailId = sc.next();
		ps.setString(1, mailId);
		
		System.out.println("Enter new pn_No : ");
		String ph_No = sc.next();
		ps.setString(2, ph_No);
		
		ps.setInt(3, customerId);
		
		if(0 < ps.executeUpdate())
		{
			System.out.println("Mail-Id and PhoneNumber Updated SuccessFully....");
		}
	}
	
	public static void deleteCustomerNameStartWith(Connection con ,String preffix) throws SQLException
	{
		String daleteQuery = "delete from customer where name like ? ";
		preffix=preffix+"%";
		PreparedStatement ps = con.prepareStatement(daleteQuery);
		ps.setString(1, preffix);
		if(ps.executeUpdate() > 0)
		{
			System.out.println("Customer Deleted Successfully......");
		}
	}
	
	public static void showEvenIdCustomers(Connection con) throws SQLException
	{
		Statement s = con.createStatement();
		
		ResultSet rs = s.executeQuery("select customerId, FName, LName from customer where MOD(customerId , 2) = 0");
		
		ResultSetMetaData md = rs.getMetaData();
		
		for(int i = 1 ;  i<=  md.getColumnCount() ; i++)
		{
			System.out.print(md.getColumnName(i)+"\t");
		}
		System.out.println();
		
		while(rs.next())
		{
			for(int i =1 ; i <= md.getColumnCount() ;  i++)
			{
				System.out.print(rs.getString(i)+"\t");
			}
			System.out.println();
		}
	}
}
