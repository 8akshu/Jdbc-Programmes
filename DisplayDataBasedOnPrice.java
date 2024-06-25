package com.akshu.examJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DisplayDataBasedOnPrice 
{
	static class book
	{
		Integer book_id ; String title; String author; Double price;
		
		public book(Integer book_id, String title, String author, Double price) 
		{
			super();
			this.book_id = book_id;
			this.title = title;
			this.author = author;
			this.price = price;
		}

		public void setBook_id(Integer book_id) {
			this.book_id = book_id;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		@Override
		public String toString() {
			return "book [book_id=" + book_id + ", title=" + title + ", author=" + author + ", price=" + price + "]";
		}
	}
	
	public static void main(String[] args) throws Exception 
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "C##Ash" ,"akshu");
		
		List<book> list = new ArrayList<>();
		
		Statement s = con.createStatement();
		
		ResultSet rs = s.executeQuery("select * from books");
		
		ResultSetMetaData md = rs.getMetaData();
		for(int i = 1 ; i <= md.getColumnCount(); i++)
		{
			System.out.print(md.getColumnName(i)+"\t");
		}
		System.out.println("\n"+"=================================================");
		
		while(rs.next())
		{
			System.out.print(rs.getString(1)+"\t");
			System.out.print(rs.getString(2)+"\t");
			System.out.print(rs.getString(3)+"\t");
			System.out.println(rs.getString(4)+"\t");
			
			list.add(new book(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getDouble(4)));
		}
		
		System.out.println("\n \n After Opration :- ");
		list.stream().map( b ->
		{
			StringBuilder sb = new StringBuilder(b.author);
			sb = sb.reverse();
			char ch = sb.charAt(0);
			if(ch >= 'a' && ch <= 'z')
			{
				ch = (char)(ch-32);
				sb.setCharAt(0, ch);
			}
			
			ch =  sb.charAt(sb.length()-1);
			if(ch >= 'a' && ch <= 'z')
			{
				ch = (char)(ch-32);
				sb.setCharAt(sb.length()-1, ch);
			}
			
			b.setAuthor(sb.toString());
			
			return b;
		}).sorted((b1, b2) -> Double.compare(b1.price, b2.price)).forEach(System.out :: println);
	}
}




















