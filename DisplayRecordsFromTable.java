package com.akshu.examJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DisplayRecordsFromTable 
{
	public static void main(String[] args) throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE" , "C##Ash", "akshu");
		
		displayRecord(con);
	}
	
	public static void displayRecord(Connection con) throws SQLException
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
}
