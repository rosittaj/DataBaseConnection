package database;

import java.sql.*;
import java.util.*;

public class EmployeeDetails {
	public static void main(String[] args) {
		Credential dbase = new Credential();
		// Driver and Database connection.
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", dbase.username,
				dbase.pword); Statement stmt = con.createStatement();) {
			if (con == null) {
				String sql = "CREATE DATABASE Employee";// create database
				stmt.executeUpdate(sql);
				System.out.println("Database created successfully...");
				sql = "create table details(name varchar(20),address varchar(30))";// create table
				stmt.executeUpdate(sql);
				System.out.println("Table created successfully...");
			} else {
				System.out.println("\nDatabase/table already created!!!");
			}
			// User input the values into table
			Scanner sr = new Scanner(System.in);
			while (true) {
				System.out.println("\nEnter one of the following choices:");
				System.out.println("Your choices :\n 1-Insert data \n 2-Read data \n 3-Exit");
				int choice = sr.nextInt();
				if (choice == 1) {
					System.out.println("Enter employee address :\n");
					String address = sr.nextLine();
					System.out.println("Enter employee address name:");
					String name = sr.nextLine();
					String sql = "insert into details(name,address) values(?,?)";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, name);
					ps.setString(2, address);
					ps.executeUpdate();
				} else if (choice == 2) {
					// update empty column with office address.
					String sql = "UPDATE details SET address='Kochi' WHERE address='' ";
					stmt.executeUpdate(sql);
					// Delete empty column.
					sql = "delete from details where address=''";
					stmt.executeUpdate(sql);
					//Show all the Data in a specific table.
					ResultSet rs = stmt.executeQuery("select * from details");
					while (rs.next()) {
						System.out.println("---------------------------");
						System.out.println("    " + rs.getString(1) + " " + rs.getString(2));
					}
					System.out.println("---------------------------");
				} else if (choice == 3) {
					System.out.println("Thank you!!");
					break;
				} else {
					System.out.println("Sorry please enter a valid number \n");
				}
			}
			con.close();//Close the Database connection.
		}

		catch (Exception e) {
			System.out.println("Sorry!!You are enterd a wrong value.Try again!!");
		}
	}
}
