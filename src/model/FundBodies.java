package model;

import java.sql.*;

public class FundBodies 
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

		public String insertFundBodies( String name, String address, String phoneNo, String FundBodyValue )
		{
				String output = "";
				try
				{
					Connection con = connect();
		
					if (con == null)
					{return "Error while connecting to the database for inserting."; }
		
					// create a prepared statement
					String query = " insert into FundBodies(`FundBodiesID`,`FundBodiesName`,`FundBodiesAddress`,`FundBodiesPhoneNo`,`FundBodyValue`)"
									+ " values (?, ?, ?, ?, ?)";
		
					PreparedStatement preparedStmt = con.prepareStatement(query);
		
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, name);
					preparedStmt.setString(3, address);
					preparedStmt.setString(4, phoneNo);
					preparedStmt.setDouble(5, Double.parseDouble(FundBodyValue));	
		
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Inserted successfully";
				}
				catch (Exception e)
				{
					output = "Error while inserting the FundBodies.";
					System.err.println(e.getMessage());
				}
				return output;
		}
		
		public String readFundBodies()
		{
			String output = "";
			try
			{
					Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for reading."; }
					
					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>FundBodiesName</th><th>FundBodiesAddress</th>" +
							"<th>FundBodiesPhoneNo</th>" +
							"<th>FundBodyValue</th>" +
							"<th>Update</th><th>Delete</th></tr>";
					
					String query = "select * from paf.fundbodies";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					// iterate through the rows in the result set
					while (rs.next())
					{
						String FundBodiesID = Integer.toString(rs.getInt("FundBodiesID"));
						String FundBodiesName = rs.getString("FundBodiesName");
						String FundBodiesAddress = rs.getString("FundBodiesAddress");
						String FundBodiesPhoneNo = rs.getString("FundBodiesPhoneNo");
						String FundBodyValue = Double.toString(rs.getDouble("FundBodyValue"));
					
						// Add into the html table
						output += "<tr><td>" + FundBodiesName + "</td>";
						output += "<td>" + FundBodiesAddress + "</td>";
						output += "<td>" + FundBodiesPhoneNo + "</td>";
						output += "<td>" + FundBodyValue + "</td>";
						
						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='FundBodies.jsp'>"+ "<input name='btnDelete' type='submit' value='Delete' class='btn btn-danger'>"+ "<input name='FundBodiesID' type='hidden' value='" + FundBodiesID+ "'>" + "</form></td></tr>";
					}
					con.close();
					
					// Complete the html table
					output += "</table>";
			}
			catch (Exception e)
			{
					output = "Error while reading the FundBodies.";
					System.err.println(e.getMessage());
					e.printStackTrace();
			}
			
			return output;
		}
		
		public String updateFundBodies(String ID, String name, String address, String phoneNo, String FundBodyValue)
		{
			String output = "";
			
			try
			{
					Connection con = connect();
					
					if (con == null)
					{return "Error while connecting to the database for updating."; }
					
					// create a prepared statement
					String query = "UPDATE FundBodies SET FundBodiesName=?,FundBodiesAddress=?,FundBodiesPhoneNo=?,FundBodyValue=? WHERE FundBodiesID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
			
					// binding values
					preparedStmt.setString(1, name);
					preparedStmt.setString(2, address);
					preparedStmt.setString(3, phoneNo);
					preparedStmt.setDouble(4, Double.parseDouble(FundBodyValue));
					preparedStmt.setInt(5, Integer.parseInt(ID));
			
					// execute the statement
					preparedStmt.execute();
					con.close();
			
					output = "Updated successfully";
			}
			catch (Exception e)
			{
					output = "Error while updating the FundBodies.";
					System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		public String deleteFundBodies(String FundBodiesID)
		{
				String output = "";
				try
				{
						Connection con = connect();
						if (con == null)
						{return "Error while connecting to the database for deleting."; }
						
						// create a prepared statement
						String query = "delete from FundBodies where FundBodiesID=?";
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(FundBodiesID));
						
						// execute the statement
						preparedStmt.execute();
						con.close();
						
						output = "Deleted successfully";
				}
				catch (Exception e)
				{
					output = "Error while deleting the FundBodies .";
					System.err.println(e.getMessage());
				}
				
				return output;
		}
}
		
		
		
		
					
		
		
		
		
		
		
		