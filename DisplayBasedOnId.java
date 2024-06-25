package com.akshu.examJdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.akshu.examJdbc.DisplayStudentHavingPallindromeNameAndAge.std;

public class DisplayBasedOnId 
{
	static record order(int order_id, String order_name)
	{
	}
	
	public static void main(String[] args) throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE" , "C##Ash", "akshu");
		
		List<order> list = new ArrayList<>();
		
		String retriveQuery = "Select * from orders ";
		
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
			System.out.println();
			
			list.add(new order(rs.getInt(1), rs.getString(2)));
		}
		
		System.out.println("\n After Filter: ");
		
		Object[] array = list.stream().sorted((o1 , o2) -> Integer.compare(o2.order_id(), o1.order_id())).toArray();
		
		for(int i =0 ; i < 3 ; i++)
		{
			System.out.println(array[i]);
		}
		
	}
}
