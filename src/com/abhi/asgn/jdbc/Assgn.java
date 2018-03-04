package com.abhi.asgn.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Assgn 
{
	private static Scanner sc;

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException
	{
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream("db.properties");
		p.load(fis);
		String url = p.getProperty("url");
		String usnm = p.getProperty("usnm");
		String pswd = p.getProperty("pswd");
		Connection con = DriverManager.getConnection(url,usnm,pswd);
		sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("Type the operation you want to perform (Select/Add/Update/Delete/Search)");
			String option = sc.next();
		
			if(option.equalsIgnoreCase("select"))
			{
				String select = String.format("SELECT * FROM EMPLOYEES");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(select);
				System.out.println("..........\n");
				System.out.println("ENO\tENAME\t\tESAL\t\tEADDR\n");
				System.out.println(".............................................................\n");
				while(rs.next())
				{
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getFloat(3)+"\t\t"+rs.getString(4));
				}
			}
		
			else if(option.equalsIgnoreCase("add"))
			{
				while(true)
				{	
					String adding = "\nINSERT INTO EMPLOYEES VALUES(?,?,?,?)";
					PreparedStatement pst2 = con.prepareStatement(adding);
					System.out.println("\nEnter the EMP No. : ");
					int eno = sc.nextInt();
					System.out.println("\nEnter the EMP NAME : ");
					String ename = sc.next();
					System.out.println("\nEnter the EMP Salary : ");
					Float esal = sc.nextFloat();
					System.out.println("\nEnter the EMP City : ");
					String eaddr = sc.next();
					pst2.setInt(1, eno);
					pst2.setString(2,ename);
					pst2.setFloat(3,esal);
					pst2.setString(4,eaddr);
					pst2.executeUpdate();
					System.out.println("\nRecord inserted!!!!");
					System.out.println("\nDo you want to add more records (yes/no) : ");
					String option1 = sc.next();
					if(option1.equalsIgnoreCase("no"))
						break;
				}	
			}
		
			else if(option.equalsIgnoreCase("update"))
			{
				while(true)
				{	
					System.out.println("\nEnter what you wanna update (keep '' for string values): ");
					String set = sc.next();
					System.out.println("\nEnter the condition (keep '' for string values): ");
					String cond = sc.next();
					String update = String.format("\nUPDATE EMPLOYEES SET %s WHERE %s",set,cond); 
					PreparedStatement pst3 = con.prepareStatement(update);
					pst3.executeUpdate();
					System.out.println("\nRecord Updated!!!\n");
					System.out.println("\nDo you want to update more records (yes/no) : ");
					String option2 = sc.next();
					if(option2.equalsIgnoreCase("no"))
						break;
				}				
			}
		
			else if(option.equalsIgnoreCase("delete"))
			{
				while(true)
				{	
					System.out.println("\nEnter the condition of deleting a record : ");
					String del = sc.next();
					String delete = String.format("\nDELETE FROM EMPLOYEES WHERE %s",del); 
					System.out.println(delete);
					PreparedStatement pst4 = con.prepareStatement(delete);
					pst4.executeUpdate();
					System.out.println("\nRecord Deleted!!!\n");
					System.out.println("\nDo you want to delete more records (yes/no) : ");
					String option3 = sc.next();
					if(option3.equalsIgnoreCase("no"))
						break;
				}
			}
			
			else if(option.equalsIgnoreCase("search"))
			{
				while(true)
				{
					System.out.println("\nEnter the condition for searching a record : ");
					String ser = sc.next();
					String search = String.format("\nSELECT * FROM EMPLOYEES WHERE %s",ser);
					PreparedStatement pst5 = con.prepareStatement(search);
					ResultSet rs1 = pst5.executeQuery();
					System.out.println("..........\n");
					System.out.println("ENO\tENAME\t\tESAL\t\tEADDR\n");
					System.out.println(".............................................................\n");
					while(rs1.next())
					{
						System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t\t"+rs1.getFloat(3)+"\t\t"+rs1.getString(4));
					}
					System.out.println("\nDo you want to search more records (yes/no) : ");
					String option4 = sc.next();
					if(option4.equalsIgnoreCase("no"))
						break;
				}	
			}
			
			System.out.println("\nDo you want to perform more operations (yes/no) : ");
			String op = sc.next();
			if(op.equalsIgnoreCase("no"))
				break;
		}
		
		con.close();
		System.out.println("\n\nThank You!!!!");
	}
}
