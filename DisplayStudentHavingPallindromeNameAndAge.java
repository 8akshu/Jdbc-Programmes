package com.akshu.examJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.akshu.examJdbc.FilterTableDataUsingStreamAndDisplay.ppp;

public class DisplayStudentHavingPallindromeNameAndAge
{
	static record std(int student_id, String student_name, int student_age, String department)
	{
	}
	
	public static void main(String[] args) throws Exception 
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE" , "C##Ash", "akshu");
		
		List<std> list = new ArrayList<>();
		
		String retriveQuery = "Select * from students ";
		
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
			
			list.add(new std(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
		}
		
		
		System.out.println("\n After Filter: ");
		
		list.stream().filter( s -> isPallindrom(s.student_age) ).filter(s -> isPallindrom(s.student_name)).forEach(System.out::println);
		
	}
	
	public static boolean isPallindrom(String str)
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i = str.length()-1 ; i >= 0 ; i--)
		{
			sb = sb.append(str.charAt(i));
		}
		return str.equals(sb.toString());	
	}
	
	public static boolean isPallindrom(int num)
	{
		int id = num;
		int sum = 0;
		
		while(num != 0)
		{
			sum = (sum*10) + num % 10;
			num /= 10;
		}
		return sum == id;
	}
}
