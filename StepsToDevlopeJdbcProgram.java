package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StepsToDevlopeJdbcProgram 
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		//Loading A JDBC driver class 
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//Establish Connection using getConnection() method of  DrivaerManager class 
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "C##ash", "akshu");
		
		//Peform a Task using Statement Interface 
		Statement st = conn.createStatement();
		
		try 
		{
		boolean execute = st.execute("Select * from ex");
		
			if(execute)
			{
				System.out.println("Task Complited...");
			}
			else
			{	
				System.err.println("Session Failed...");
			}
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		//Closing Connection
		conn.close();
	}
}
