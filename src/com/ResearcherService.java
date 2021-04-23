package com;
import model.Researcher;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/researcher")

public class ResearcherService {
	
	Researcher researcherObj = new Researcher();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readresearcher()
	 {
//		return "Hello";
		return researcherObj.readresearcher();
	 }
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertproducts(
	 @FormParam("R__id") String R__id,
	 @FormParam("Name") String Name,
	 @FormParam("Email") String Email,
	 @FormParam("Address") String Address,
	 @FormParam("Phone_number") String Phone_number)
	{
	 String output = researcherObj.insertResearcher(R__id, Name, Email, Address, Phone_number);
	return output;
	}
	
	


}
