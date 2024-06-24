package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

record Employess(int id,String name,int age ,double salary)
{
	
}

public class AddEmployeeViewEmployeeFilterEmployee 
{
	static Scanner sc = new Scanner(System.in);
	
	static List<Employess> empList = new ArrayList<>(); 
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "C##Ash" , "akshu");
	
		try(con)
		{
			System.out.println(" 1. Add Employee \n 2. View All Employee \n 3. View All Employee age Above  \n 4. Exit");
			int choice = sc.nextInt();
				
			switch(choice)
			{
				case 1 :
				{
					PreparedStatement ps = con.prepareStatement("insert into Employes values(?,?,?,?)");
					
					System.out.print("Enter Emplyee Id(Number) : ");
					int id = sc.nextInt(); 
					ps.setInt(1, id); 
					
					System.out.print("Enter Emplyee Name : ");
					String name = sc.next();
					ps.setString(2, name);
					
					System.out.print("Enter Emplyee Age (Number) : ");
					int age = sc.nextInt();
					ps.setInt(3, age);
					
					System.out.print("Enter Emplyee Salary : ");
					Double salary = sc.nextDouble(); 
					ps.setDouble(4, salary);
					
					if(ps.executeUpdate()> 0) 
					{
						empList.add(new Employess(id, name, age, salary));
						System.out.println("Emplyee added Successfully........");
					}
				}
					break;
					
				case 2 :
				{
					viewAllEmployee(con);
				}
				break;
				case 3 :
				{
					System.out.println("Enter Age criteria ");
					int ageC = sc.nextInt();
					System.out.println("Employee age above "+ageC+" are :");
					empList.stream().filter(e -> e.age() > ageC).forEach(System.out :: println);
				}
				break;
					
				case 4:
				{
					System.out.println("Thank you...");
					System.exit(0);
				}
					break;	
				default :
				{
					System.out.println("Enter Valid Option: ");
				}
					break;		
			}
		}
	}
	
	
	public static void viewAllEmployee(Connection con) throws SQLException
	{
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select * from employes " );
		
		System.out.println("Employee Data:\n==============");
		while(rs.next())
		{
			for(int i = 1 ; i <= rs.getMetaData().getColumnCount() ; i++)
			{
				System.out.print(rs.getString(i)+"\t");
			}
			System.out.println();
		}
	}
}
