package com.akshu.examJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class InsertValuesIntoTable
{
	public static void main(String[] args) throws Exception 
	{	
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE" , "C##Ash", "akshu");
		
		 Statement s = con.createStatement();
		
		 if(0 < s.executeUpdate("insert into eee values(99 , 'aaa' , 31, 'Meta' )  ") )
		 {
			 System.out.println("Succesful");
				
		 }
		 
	}

}
