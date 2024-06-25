package com.akshu.examJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.akshu.basic_Operations.CommonOpration;

public class StreamOnTabledata 
{
	static record inventory(Integer product_id, Integer  quantity, Double price)
	{
	}
	
	public static void main(String[] args) throws Exception 
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE" , "C##Ash", "akshu");
		
		List<inventory> list = new ArrayList<>();
		
		String retriveQuery = "Select * from inventory ";
			
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
			System.out.println();
			
			list.add(new inventory(rs.getInt(1), rs.getInt(2), rs.getDouble(3)));
		}
		
		System.out.println("\n After Filter: ");
		
		list.stream().filter( p -> p.price() < 25000).filter(p ->  CommonOpration.isPrime(p.price())).forEach(System.out::println);
		
	}

	}

