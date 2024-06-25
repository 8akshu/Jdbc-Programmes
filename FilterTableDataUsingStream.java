package com.akshu.examJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;


public class FilterTableDataUsingStream
{
	static record eee(Integer id , String name , Integer age , String department)
	{
		
	}
	public static void main(String[] args) throws Exception 
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE" , "C##Ash", "akshu");
		
		List<eee> list = new ArrayList<>();
		
		String retriveQuery = "Select * from eee ";
		
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
			System.out.print(rs.getInt(1)+"\t");
			System.out.print(rs.getString(2)+"\t");
			System.out.print(rs.getInt(3)+"\t");
			System.out.print(rs.getString(4)+"\t");
			System.out.println();
			
			list.add(new eee(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
		}
		
		List<eee> list2 = list.stream().filter( e -> {
			
			if(e.age() > 25)
			{
				Integer age = e.age();
				
				int count  = 0;
				for(int i = 2 ; i <= age/2 ; i++ )
				{
					if(age % i == 0) count++;
				}
				
				if(count == 0)
				{
					if(e.name().endsWith("A") || e.name().endsWith("K") ||e.name().endsWith("E")) 
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				else
				{
					return false;
				}
			}
			else
			{
			return false;	
			}
			
		}).toList();
		System.out.println("After filter :");
		
		
		list2.forEach(System.out::println);
	}	
}


