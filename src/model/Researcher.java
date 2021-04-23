package model;
import java.sql.*; 
public class Researcher {
	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.cj.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_proj", "root", "1234");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		public String insertResearcher(String r_id,String name, String email, String address, String phone_number)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " insert into researcher(`R__id`,`Name`,`Email`,`Address`,`Phone_number`)"
				 + " values (?, ?, ?, ?, ?)";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(r_id));
				 preparedStmt.setString(2, name);
				 preparedStmt.setString(3, email);
				 preparedStmt.setString(4, address);
				 preparedStmt.setString(5, phone_number);
				// execute the statement
		 
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String readresearcher()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Name</th><th>Email</th>" +
		 "<th>Address</th>" +
		 "<th>Phone_number</th>" +
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from paf_proj.researcher";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String R__id = Integer.toString(rs.getInt("R__id"));
		 String Name  = rs.getString("Name");
		 String Email = rs.getString("Email");
		 String Address = rs.getString("Address");
		 String Phone_number = rs.getString("Phone_number");
		 // Add into the html table
		 output += "<tr><td>" + Name + "</td>";
		 output += "<td>" + Email + "</td>";
		 output += "<td>" + Address + "</td>";
		 output += "<td>" + Phone_number + "</td>";
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"+ "<input name='itemID' type='hidden' value='" + R__id+ "'>" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 e.printStackTrace();
		 }
		 return output;
		 }
		
		public String updateresearcher(String r_id,String name, String email, String address, String phone_number)
		
		{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE researcher SET Name=?,Email=?,Address=?,Phone_number=? WHERE R__id= ? ";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, name);
			 preparedStmt.setString(2, email);
			 preparedStmt.setString(3, address);
			 preparedStmt.setString(4, phone_number);
			 preparedStmt.setInt(5, Integer.parseInt(r_id));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while updating the item.";
			 System.err.println(e.getMessage());
			 e.printStackTrace();
			 }
			 return output;
			 }
		
			public String deleteresearcher(String R__id)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 // create a prepared statement
			 String query = "delete from paf_proj.researcher where R__id=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(R__id));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Deleted successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while deleting the item.";
			 System.err.println(e.getMessage());
			 e.printStackTrace();
			 
			 }
			 return output;
			 }
	
	
	

}
