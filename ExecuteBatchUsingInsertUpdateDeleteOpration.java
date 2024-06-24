package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteBatchUsingInsertUpdateDeleteOpration 
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "C##Ash" , "akshu");
		
		Statement s = con.createStatement();
		s.addBatch("insert into EMployee_Info values(111, 'Akshay Waghle' , 50000.0 ,'Ameerpeth' , 'Akshu@gmail.com' , 123456)");
		s.addBatch("insert into EMployee_Info values(222, 'Karan Wanjari' , 60000.0 ,'Pune' , 'karan@gmail.com' , 444456)");
		s.addBatch("insert into EMployee_Info values(333, 'Sandesh patil' , 65000.0 ,'Bhandara' , 'Sandesh@gmail.com' , 888456)");
		
		s.addBatch("update  EMployee_Info set empSalary = 55000.0 where empid = 111");
		
		s.addBatch("delete from Employee_info where empSalary =  (select max(empSalary) from employee_info) ");
		
		s.executeBatch();
		 
		 System.out.println("Batch Updated Successfully.........");
		
		 
	}

}
