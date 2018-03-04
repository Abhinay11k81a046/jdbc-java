package com.abhi.asgn.jdbc;

import java.sql.*;

public class CreateTable 
{
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##ABHINAY","Cogent123");
		
		Statement st = con.createStatement();
		
		//try
		{
			st.executeUpdate("CREATE TABLE EMPLOYEES ( ENO NUMBER, ENAME VARCHAR2(50), ESAL FLOAT, EADDR VARCHAR2(20))");
			System.out.println("Table Created");
		}//catch (Exception e) {System.out.println("Error Creating a table");}
		con.close();
	}
}
