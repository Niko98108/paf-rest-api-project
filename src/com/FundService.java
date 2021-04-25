package com;

import model.Fund;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Fund")
public class FundService
{
	Fund fundObj = new Fund();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFund()
	{
		 return fundObj.readFund();
	}
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFund(@FormParam("FundType") String FundType,
	@FormParam("Description") String Description)
	{
	String output =fundObj.insertFund(FundType,Description );
	return output;
	}
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFund(String FundData)
	{
		//Convert the input string to a JSON object
		JsonObject FundObject = new JsonParser().parse(FundData).getAsJsonObject();
		
		//Read the values from the JSON object
		String FundID = FundObject.get("FundID").getAsString();
		String FundType = FundObject.get("FundType").getAsString();
		String Description = FundObject.get("Description").getAsString();
		String output = fundObj.updateFund(FundID , FundType, Description);
		return output;
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFund(String FundData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(FundData, "", Parser.xmlParser());
	//Read the value from the element <FundID>
	String FundID = doc.select("FundID").text();
	String output = fundObj.deleteFund(FundID);
	return output;
	}
	

}
