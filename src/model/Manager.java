package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Manager {
	public Connection getconnection() {
		
		Connection con = null;

		 try
		 {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_rest_api","root", "");
		 
		 //For testing

		 System.out.print("Successfully connected to Database");
		 }
		 catch(Exception e){
		 e.printStackTrace();
		 }

		 return con;
	}
	public String insertManager(String managerName, String email,String managerType, int phoneNo)
	{
	 String output = "";
	try
	 {
	 Connection con = getconnection();
	 if (con == null)
	 {
	 return "check your database connection";
	 }
	 // create a prepared statement
	 String query = " INSERT INTO paf_rest_api.projectmanager(`manager_id`,`managerName`,`email`,`managerType`,`mobile`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, managerName);
	 preparedStmt.setString(3, email);
	 preparedStmt.setString(4, managerType);
	 preparedStmt.setInt(5,phoneNo);
	
	
	//execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "successfully Inserted to ProjectManager Table";
	 }
	catch (Exception e)
	 {
	 output = "Error While Inserting";
	 System.err.println(e.getMessage());
	 e.printStackTrace();
	 }
	return output;
	}
	
	//update ProjectManager data function
	public String updateProjectManager(int managerId,String managerName, String email,String managerType, int mobile)
	{
	 String output = "";
	try
	 {
	 Connection con = getconnection();
	 if (con == null)
	 {
	 return "check your database connection";
	 }
	 // create a prepared statement
	 String query = "UPDATE projectmanager SET  managerName=?,  email=?, managerType=?, mobile=? WHERE projectmanager.manager_id='"+managerId+"' ";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 
	 preparedStmt.setString(1, managerName);
	 preparedStmt.setString(2, email);
	 preparedStmt.setString(3, managerType);
	 preparedStmt.setInt(4,mobile );

	
	
	 
	//execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Successfully Updated Project Manager Table";
	 }
	catch (Exception e)
	 {
	 output = "Error while updating Project Manager Table";
//	 System.err.println(e.getMessage());
	 e.printStackTrace();
	 }
	return output;
	}
	
	//Delete project data
	public String deleteProject(String projectId)
	{
	 String output = "";
	try
	 {
	 Connection con = getconnection();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	//create a prepared statement
	 String query = "DELETE FROM project WHERE project_id=?" ;
	 
	 PreparedStatement preparedStatement = con.prepareStatement(query);
	 
	 //binding Values
	 preparedStatement.setInt(1, Integer.parseInt(projectId));
	 
	 //execute the statement
	 
	 preparedStatement.execute();
	 
	 con.close();
	 output = "Successfully Deleted from Project Table";

	 
	 }
	catch (Exception e)
	 {
	 output = "Error while Deleting";
	 System.err.println(e.getMessage());
	 e.printStackTrace();
	 }
	return output;
	}
	
	//Read Project Data
	public String readProject()
	{
	 String output = "";
	try
	 {
	 Connection con = getconnection();
	 if (con == null)
	 {
	 return "Error while connecting to the Database.";
	 }
	 // Prepare the HTML table to be displayed
	 
//	 output = "<table border='1'><tr><th>Item Code</th>"
//	 +"<th>Item Name</th><th>Item Price</th>"
//	 + "<th>Item Description</th>"
//	 + "<th>Update</th><th>Remove</th></tr>";
	 
	 output = "<table style=\"border-collapse: collapse;border: 1px solid black\">\r\n"
	 		+ "  <thead>\r\n"
	 		+ "    <tr style=\"border: 1px solid black;background-color: lightgray\">\r\n"
	 		+ "      <th style=\"border: 1px solid black\" scope=\"col\">Project ID</th>\r\n"
	 		+ "      <th style=\"border: 1px solid black\"scope=\"col\">Project Name</th>\r\n"
	 		+ "      <th style=\"border: 1px solid black\"scope=\"col\">Project Descripton</th>\r\n"
	 		+ "      <th style=\"border: 1px solid black\"scope=\"col\">Project Type</th>\r\n"
	 		+ "      <th style=\"border: 1px solid black\"scope=\"col\">Project Manager ID</th>\r\n"
	 		+ "      <th style=\"border: 1px solid black\"scope=\"col\">Start Data</th>\r\n"
	 		+ "      <th style=\"border: 1px solid black\"scope=\"col\">End Data</th>\r\n"
	 		+ "    </tr>\r\n"
	 		+ "  </thead>\r\n"
	 		+ "</table";
	 
	 String query = "SELECT * FROM project";
	 
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String projectId = Integer.toString(rs.getInt("project_id"));
	 String projectName = rs.getString("projectName");
	 String projectDesc = rs.getString("projectDesc");
	 String projectType = rs.getString("projectType") ;
	 String managerId = rs.getString("manager_id");
	 String startDate = rs.getString("startDate");
	 String endDate = rs.getString("endDate");
	 
	 // Add a row into the HTML table
	 output += "<tr style=\"border: 1px solid black\"><td>" + projectId + "</td>";
	 output += "<td style=\"border: 1px solid black\">" + projectName + "</td>";
	 output += "<td style=\"border: 1px solid black\">" + projectDesc + "</td>"; 
	 output += "<td style=\"border: 1px solid black\">" + projectType + "</td>"; 
	 output += "<td style=\"border: 1px solid black\">" + managerId + "</td>"; 
	 output += "<td style=\"border: 1px solid black\">" + startDate + "</td>"; 
	 output += "<td style=\"border: 1px solid black\">" + endDate + "</td>";

	 }
	 con.close();
	 // Complete the HTML table
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


}
