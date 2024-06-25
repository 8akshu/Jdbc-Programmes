package com.akshu.examJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.akshu.examJdbc.FilterTableDataUsingStream.eee;

public class FilterTableDataUsingStreamAndDisplay
{
	static record ppp(Integer product_id, String product_name,Double price, Integer quantity)
	{	
	}
	
	public static void main(String[] args) throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE" , "C##Ash", "akshu");
		
		List<ppp> list = new ArrayList<>();
		
		try(con)
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
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getInt(3)+"\t");
				System.out.print(rs.getString(4)+"\t");
				System.out.println();
				
				list.add(new ppp(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4)));
			}
		}
		
		 System.out.println("After filter :");
				
		list.stream().filter( e -> 
		{
			Integer id = e.product_id();
			
			int num = id;
			int sum = 0;
			int count = 0;
			for(int i = 0 ; num != 0 ; i++)
			{
				count++;
				num /= 10;
			}
			num = id;
			while(num != 0)
			{
				int c1 =0;
				for(int i = 0 ; i < count ; i++)
				{
					c1 = c1 + num%10;
				}
				sum = sum + c1;
				num = num / 10;
			}
			
			return 	sum == id;
			
		}).filter(e -> e.price() > 1000 ).filter(e -> e.price() < 5000).toList().forEach(System.out::println);
		
	}

}
