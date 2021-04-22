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
				JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject();
				
				//Read the values from the JSON object
				
				String projectName = projectObject.get("projectName").getAsString();
				String projectDesc= projectObject.get("projectDesc").getAsString();
				String projectType= projectObject.get("projectType").getAsString();
				String managerId = projectObject.get("managerId").getAsString();
				String startDate = projectObject.get("startDate").getAsString();
				String endDate = projectObject.get("endDate").getAsString();
			
				
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
			JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject();
			
			//Read the values from the JSON object
			String projectId = projectObject.get("projectId").getAsString();
			String projectName = projectObject.get("projectName").getAsString();
			String projectDesc= projectObject.get("projectDesc").getAsString();
			String projectType= projectObject.get("projectType").getAsString();
			String managerId = projectObject.get("managerId").getAsString();
			String startDate = projectObject.get("startDate").getAsString();
			String endDate = projectObject.get("endDate").getAsString();
			
			String output = projectObj.updateProject(projectId, projectName, projectDesc, projectType, managerId, startDate, endDate) ;
			return output;
			
		}
		
		// Delete project Data
		@DELETE
		@Path("/delete")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String deleteProject(String projectData) {
			//convert the input string to an XML document
			Document doc = Jsoup.parse(projectData,"",Parser.xmlParser());
			
			//Read the value from the element <Project>
			String projectId = doc.select("projectId").text();
			
			String output = projectObj.deleteProject(projectId);
			
			return output;
			
		}
		
		// Read Project Data
		@GET
		@Path("/view")
		@Produces(MediaType.TEXT_HTML)
		
		public String readProject() {

			return projectObj.readProject();
		}
		
		//return JSON Object
		@GET
		@Path("/search/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public String getDetails(@PathParam("id") int id){
			
			 return projectObj.projectSearch(id) ;
			
		}
	
		}

