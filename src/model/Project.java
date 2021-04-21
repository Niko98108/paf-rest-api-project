package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


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


}
