package com.project;
import model.Project;

//for Rest Services
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

//For JSON
import com.google.gson.*;
//for XML
//import org.jsoup.*;
//import org.jsoup.parser.*;
//import org.jsoup.nodes.Document;

@Path("/project")
public class ProjectService {
	Project projectObj = new Project();
	
	// Insert Data
		@POST
		@Path("/insert") 
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String insertProject(String projectData) {
				//convert the input string to a JSON object
				JsonObject itemObject = new JsonParser().parse(projectData).getAsJsonObject();
				
				//Read the values from the JSON object
				
				String projectName = itemObject.get("projectName").getAsString();
				String projectDesc= itemObject.get("projectDesc").getAsString();
				String projectType= itemObject.get("projectType").getAsString();
				String managerId = itemObject.get("managerId").getAsString();
				String startDate = itemObject.get("startDate").getAsString();
				String endDate = itemObject.get("endDate").getAsString();
			
				
				String output = projectObj.insertProject(projectName, projectDesc, projectType, managerId, startDate, endDate) ;
				return output;
			}
		//Update Project data
		@PUT
		@Path("/update")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String updateProject(String projectData) {
			//convert the input string to a JSON object
			JsonObject itemObject = new JsonParser().parse(projectData).getAsJsonObject();
			
			//Read the values from the JSON object
			String projectId = itemObject.get("projectId").getAsString();
			String projectName = itemObject.get("projectName").getAsString();
			String projectDesc= itemObject.get("projectDesc").getAsString();
			String projectType= itemObject.get("projectType").getAsString();
			String managerId = itemObject.get("managerId").getAsString();
			String startDate = itemObject.get("startDate").getAsString();
			String endDate = itemObject.get("endDate").getAsString();
			
			String output = projectObj.updateProject(projectId, projectName, projectDesc, projectType, managerId, startDate, endDate) ;
			return output;
			
		}
		
		// Delete project Data
		@DELETE
		@Path("/delete")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String deleteItems(String projectData) {
			//convert the input string to an XML document
			Document doc = Jsoup.parse(projectData,"",Parser.xmlParser());
			
			//Read the value from the element <itemID>
			String projectId = doc.select("projectId").text();
			
			String output = projectObj.deleteProject(projectId);
			
			return output;
			
		}
		// Read Project Data
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		
		public String readItems() {

			return projectObj.readProject();
		}
	
		}

