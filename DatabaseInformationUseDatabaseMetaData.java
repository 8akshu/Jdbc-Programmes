package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseInformationUseDatabaseMetaData 
{
	public static void main(String[] args) throws SQLException
	{
		 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##Ash" ,"akshu");
		 
		 DatabaseMetaData md = con.getMetaData();
		 
		 System.out.println(md.getDatabaseProductName());
		 System.out.println(md.getDriverVersion());
		 System.out.println(md.getDriverName());
		 System.out.println(md.getMaxColumnNameLength());
	}
}
