package com.project;
import model.Manager;

//for Rest Services
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//for XML
//import org.jsoup.*;
//import org.jsoup.parser.*;
//import org.jsoup.nodes.Document;

@Path("/Manager")
public class ManagerService {
	
	Manager managerObj = new Manager();
	// Read Data
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		
		public String readItems() {

			return "Hello Manager";
		}
// Insert Manager Details
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertItem(String managerData) {
				//convert the input string to a JSON object
				JsonObject itemObject = new JsonParser().parse(managerData).getAsJsonObject();
				
				//Read the values from the JSON object
				
				String managerName = itemObject.get("managerName").getAsString();
				String email= itemObject.get("email").getAsString();
				String managerType = itemObject.get("managerType").getAsString();
				String phoneNo = itemObject.get("phoneNo").getAsString();
				
				{ 
				 String output = managerObj.insertManager(managerName, email, managerType, phoneNo); 
				return output; 
				}
		
		}
}
