package model;

import java.sql.*;

public class customer 
{		//A common method to connect to the DB
		private Connection connect()
		{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "1234");
			}
			catch (Exception e)
			{e.printStackTrace();}
			
			return con;
		}

		public String insertcustomer( String name, String address, String phoneNo, String email )
		{
				String output = "";
				try
				{
					Connection con = connect();
		
					if (con == null)
					{return "Error while connecting to the database for inserting."; }
		
					// create a prepared statement
					String query = " insert into customer(`customerID`,`customerName`,`customerAddress`,`customerPhoneNo`,`customeremail`)"
									+ " values (?, ?, ?, ?, ?)";
		
					PreparedStatement preparedStmt = con.prepareStatement(query);
		
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, name);
					preparedStmt.setString(3, address);
					preparedStmt.setString(4, phoneNo);
					preparedStmt.setString(5, email);	
		
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Inserted successfully";
				}
				catch (Exception e)
				{
					output = "Error while inserting the customer.";
					System.err.println(e.getMessage());
				}
				return output;
		}
		
		public String readcustomer()
		{
			String output = "";
			try
			{
					Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for reading."; }
					
					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>customerName</th><th>customerAddress</th>" +
							"<th>customerPhoneNo</th>" +
							"<th>customeremail</th>" +
							"<th>Update</th><th>Delete</th></tr>";
					
					String query = "select * from paf.customer";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					// iterate through the rows in the result set
					while (rs.next())
					{
						String customerID = Integer.toString(rs.getInt("customerID"));
						String customerName = rs.getString("customerName");
						String customerAddress = rs.getString("customerAddress");
						String customerPhoneNo = rs.getString("customerPhoneNo");
						String customeremail = rs.getString("customeremail");
					
						// Add into the html table
						output += "<tr><td>" + customerName + "</td>";
						output += "<td>" + customerAddress + "</td>";
						output += "<td>" + customerPhoneNo + "</td>";
						output += "<td>" + customeremail + "</td>";
						
						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='customer.jsp'>"+ "<input name='btnDelete' type='submit' value='Delete' class='btn btn-danger'>"+ "<input name='customerID' type='hidden' value='" + customerID+ "'>" + "</form></td></tr>";
					}
					con.close();
					
					// Complete the html table
					output += "</table>";
			}
			catch (Exception e)
			{
					output = "Error while reading the customers.";
					System.err.println(e.getMessage());
					e.printStackTrace();
			}
			
			return output;
		}
		
		public String updatecustomer(String ID, String name, String address, String phoneNo, String email)
		{
			String output = "";
			
			try
			{
					Connection con = connect();
					
					if (con == null)
					{return "Error while connecting to the database for updating."; }
					
					// create a prepared statement
					String query = "UPDATE customer SET customerName=?,customerAddress=?,customerPhoneNo=?,customeremail=? WHERE customerID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
			
					// binding values
					preparedStmt.setString(1, name);
					preparedStmt.setString(2, address);
					preparedStmt.setString(3, phoneNo);
					preparedStmt.setString(4, email);
					preparedStmt.setInt(5, Integer.parseInt(ID));
			
					// execute the statement
					preparedStmt.execute();
					con.close();
			
					output = "Updated successfully";
			}
			catch (Exception e)
			{
					output = "Error while updating the customer.";
					System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		public String deletecustomer(String customerID)
		{
				String output = "";
				try
				{
						Connection con = connect();
						if (con == null)
						{return "Error while connecting to the database for deleting."; }
						
						// create a prepared statement
						String query = "delete from customer where customerID=?";
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(customerID));
						
						// execute the statement
						preparedStmt.execute();
						con.close();
						
						output = "Deleted successfully";
				}
				catch (Exception e)
				{
					output = "Error while deleting the customer .";
					System.err.println(e.getMessage());
				}
				
				return output;
		}
}