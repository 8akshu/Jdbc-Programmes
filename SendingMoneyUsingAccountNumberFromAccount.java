package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SendingMoneyUsingAccountNumberFromAccount 
{
	static long senderAccoun;
	static long receiverAccount;
	static double transferAmount; 
	
	public static void main(String[] args) throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "C##Ash" ,"akshu");
		Scanner sc = new Scanner(System.in);
			try(con)
			{
				con.setAutoCommit(false);
				
				System.out.println("Enter Sender AccountNumber : ");
				senderAccoun = sc.nextLong();
				
				System.out.println("Enter Reciver AccountNumber : ");
				receiverAccount = sc.nextLong() ;
				 
				System.out.println("Enter transfer Amount : ");
				transferAmount = sc.nextDouble();
				
				double senderBalance = getAccountBalance(senderAccoun, con);
				System.out.println("Sender Account Initial Balance is : "+senderBalance);
				
				if(checkInsufficientFunds(transferAmount, con))
				{
					double newBalnce =  senderBalance - transferAmount;
					if(updateAccountBalance(senderAccoun, newBalnce, con))
					{
						PreparedStatement psReciver = con.prepareStatement("update accounts set balance = ? where account_number = ?");
						
						double reciverBalance = getAccountBalance(receiverAccount, con)+transferAmount;
						
						psReciver.setDouble(1, reciverBalance);
						psReciver.setLong(2, receiverAccount);
						
						if(psReciver.executeUpdate()> 0)
						{
							System.out.println("Money Transfer Successfull... ");
							con.commit();
						}
						else
						{
							con.rollback();
						}
					}
					else
					{
						con.rollback();
					}
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}
	
	public static double getAccountBalance(long accountNumber , Connection con) throws SQLException
	{
		PreparedStatement ps = con.prepareStatement("select balance from Accounts where account_number = ?");
		
		ps.setLong(1, accountNumber);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next())
		{
			double balance = rs.getDouble(1);
			
			return balance;
		}
		else
		{
			return 0.0;
		}
	}
	
	public static boolean checkInsufficientFunds(double sendAmount , Connection con) throws SQLException
	{
		if(getAccountBalance(senderAccoun, con) < sendAmount)
		{
			System.out.println("Insufficient funds in the sender's account.");
			System.exit(0);
		}
		
		return true;
	}
	
	public  static boolean updateAccountBalance(long senderAccount , double newBalance ,Connection con) throws SQLException
	{
		PreparedStatement ps = con.prepareStatement("update accounts set balance = ? where account_number = ? ");
		
		ps.setDouble(1, newBalance);
		
		ps.setLong(2, senderAccount);
		
		if(ps.executeUpdate() > 0)
		{
			System.out.println("Account balance Updated Successfully.......");
			return true;
		}
		else
		{
			return false;
		}
	}
}
