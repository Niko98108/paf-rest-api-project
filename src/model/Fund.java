package model;

import java.sql.*;

public class Fund
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

		public String insertFund( String type, String description )
		{
				String output = "";
				try
				{
					Connection con = connect();
		
					if (con == null)
					{return "Error while connecting to the database for inserting."; }
		
					// create a prepared statement
					String query = " insert into Fund(`FundID`,`FundType`,`Description`)"
									+ " values (?, ?, ?)";
		
					PreparedStatement preparedStmt = con.prepareStatement(query);
		
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, type);
					preparedStmt.setString(3, description);
					
				
		
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Inserted successfully";
				}
				catch (Exception e)
				{
					output = "Error while inserting the Fund.";
					System.err.println(e.getMessage());
				}
				return output;
		}
		
		public String readFund()
		{
			String output = "";
			try
			{
					Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for reading."; }
					
					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>FundType</th><th>Description</th>" +
							"<th>Update</th><th>Delete</th></tr>";
					
					String query = "select * from paf.fund";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					// iterate through the rows in the result set
					while (rs.next())
					{
						String FundID = Integer.toString(rs.getInt("FundID"));
						String FundType = rs.getString("FundType");
						String Description = rs.getString("Description");
						
						
					
						// Add into the html table
						output += "<tr><td>" + FundType + "</td>";
						output += "<td>" + Description + "</td>";
						
						
						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='Fund.jsp'>"+ "<input name='btnDelete' type='submit' value='Delete' class='btn btn-danger'>"+ "<input name='FundID' type='hidden' value='" + FundID+ "'>" + "</form></td></tr>";
					}
					con.close();
					
					// Complete the html table
					output += "</table>";
			}
			catch (Exception e)
			{
					output = "Error while reading the Fund.";
					System.err.println(e.getMessage());
					e.printStackTrace();
			}
			
			return output;
		}
		
		public String updateFund(String ID, String type, String description)
		{
			String output = "";
			
			try
			{
					Connection con = connect();
					
					if (con == null)
					{return "Error while connecting to the database for updating."; }
					
					// create a prepared statement
					String query = "UPDATE Fund SET FundType=?,Description=? WHERE FundID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
			
					// binding values
					preparedStmt.setString(1, type);
					preparedStmt.setString(2, description);
					preparedStmt.setInt(5, Integer.parseInt(ID));
			
					// execute the statement
					preparedStmt.execute();
					con.close();
			
					output = "Updated successfully";
			}
			catch (Exception e)
			{
					output = "Error while updating the Fund.";
					System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		public String deleteFund(String FundID)
		{
				String output = "";
				try
				{
						Connection con = connect();
						if (con == null)
						{return "Error while connecting to the database for deleting."; }
						
						// create a prepared statement
						String query = "delete from FundBodies where FundID=?";
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(FundID));
						
						// execute the statement
						preparedStmt.execute();
						con.close();
						
						output = "Deleted successfully";
				}
				catch (Exception e)
				{
					output = "Error while deleting the Fund .";
					System.err.println(e.getMessage());
				}
				
				return output;
		}
}
		
		
		
		
					
		
		
		
		
		
		
