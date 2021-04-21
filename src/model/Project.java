package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.JSONObject;




public class Project {
	

	
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
	public String insertProject(String projectName, String projectDesc,String projectType, String managerId, String startDate,String endDate)
	{
	 String output = "";
	try
	 {
	 Connection con = getconnection();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	 // create a prepared statement
	 String query = " INSERT INTO paf_rest_api.project(`project_id`,`projectName`,`projectDesc`,`projectType`,`manager_id`,`startDate`,`endDate`)"
	 + " values (?, ?, ?, ?, ?,?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, projectName);
	 preparedStmt.setString(3, projectDesc);
	 preparedStmt.setString(4, projectType);
	 preparedStmt.setString(5,managerId);// preparedStmt.setDouble(4, Double.parseDouble(price));
	 preparedStmt.setString(6, startDate); 
	 preparedStmt.setString(7, endDate); 

	
	//execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = " successfully Inserted to Project Table";
	 }
	catch (Exception e)
	 {
	 output = "Error While Inserting";
	 System.err.println(e.getMessage());
	 e.printStackTrace();
	 }
	return output;
	}
	
	//update Project data function
	public String updateProject(String projectId,String projectName, String projectDesc,String projectType, String managerId, String startDate,String endDate)
	{
	 String output = "";
	try
	 {
	 Connection con = getconnection();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	 // create a prepared statement
	 String query = "UPDATE project SET  projectName=?,  projectDesc=?, projectType=?, manager_id=?, startDate=? ,endDate=? WHERE project_id='"+projectId+"' ";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 
	 preparedStmt.setString(1, projectName);
	 preparedStmt.setString(2, projectDesc);
	 preparedStmt.setString(3, projectType);
	 preparedStmt.setInt(4, Integer.parseInt(managerId));
	 preparedStmt.setString(5, startDate);
	 preparedStmt.setString(6, endDate); 
	
	 
	//execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Successfully Updated Project Table";
	 }
	catch (Exception e)
	 {
	 output = "Error while updating";
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
	 output = "Error while reading the Project.";
	 System.err.println(e.getMessage());
	 e.printStackTrace();
	 }
	return output;
	}
	
	
	
	public String projectSearch(int id)
	{
	 String output = "";
	try
	 {
	 Connection con = getconnection();
	 if (con == null)
	 {
	 return "Error while connecting to the Database.";
	 }

	 
	 String query = "SELECT * FROM project WHERE project_id= '"+id+"'";
	 
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
	 
	 JSONObject obj = new JSONObject();

     obj.put("projectId", projectId);
     obj.put("projectName",projectName );
     obj.put("projectDesc",projectDesc );
     obj.put("projectType",projectType );
     obj.put("managerId",managerId );
     obj.put("startDate",startDate);
     obj.put("endDate", endDate);
	 
	 output = " '"+obj+"' ";

	 }
	 con.close();

	 }
	catch (Exception e)
	 {
	 output = "Error while reading the Project.";
	 System.err.println(e.getMessage());
	 e.printStackTrace();
	 }
	return output;
	}




}
