package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertUserSearchUserMenu {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "C##Ash", "akshu");

		Statement s = con.createStatement();

		try (con) {
//			String createTable = " create table entry(userName varchar2(15), password varchar2(10), uFName varchar2(10) , uLName varchar2(10), mailId varchar2(26), phNumber varchar2(10))";
//		  	s.execute(createTable);

			while (true) {
				System.out.println(" 1. Add User \n 2. Search User Info \n 3. Exit");
				int choice = sc.nextInt();

				switch (choice) {
				case 1: {
					String insertQuery = "insert into entry values(?,?,?,?,?,?) ";
					PreparedStatement ips = con.prepareStatement(insertQuery);
					System.out.print("Enter UserName: ");
					String userName = sc.next();
					ips.setString(1, userName);

					System.out.print("Enter password: ");
					String password = sc.next();
					ips.setString(2, password);

					System.out.print("Enter Frist name: ");
					String uFName = sc.next();
					ips.setString(3, uFName);

					System.out.print("Enter Last Name : ");
					String uLName = sc.next();
					ips.setString(4, uLName);

					System.out.print("Enter Email: ");
					String emailId = sc.next();
					ips.setString(5, emailId);

					System.out.print("Enter phone Number: ");
					String phNumber = sc.next();
					ips.setString(6, phNumber);

					try {
						ips.executeUpdate();
						System.out.println("User registration Successfull");
					} catch (Exception e) {
						System.err.println("Invalid Process");
					}
				}
					break;
				case 2: {
					System.out.print("Enter userName to Search : ");
					String userName = sc.next();

					String searchQuery = "select * from entry where userName = ?";
					PreparedStatement ips = con.prepareStatement(searchQuery);

					ips.setString(1, userName);

					ResultSet rs = ips.executeQuery();

					ResultSetMetaData md = rs.getMetaData();
					int columnCount = md.getColumnCount();
					for (int i = 1; i <= columnCount; i++) {
						System.out.print(md.getColumnName(i) + "  ");
					}
					System.out.println();

					while (rs.next()) {
						for (int i = 1; i <= columnCount; i++) {
							System.out.print(rs.getString(i) + "    ");
						}
						System.out.println();
					}
				}
					break;
				case 3: {
					System.out.println("Thank you");
					System.exit(0);
				}
					break;
				default: {
					System.out.println("Enter Valid Choice...");
				}
					break;
				}
			}
		}
	}
}
