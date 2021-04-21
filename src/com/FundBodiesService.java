package com;

import model.FundBodies;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/FundBodies")
public class FundBodiesService 
{
	FundBodies fundbodiesObj = new FundBodies();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFundBodies()
	{
		 return fundbodiesObj.readFundBodies();
	}
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFundBodies(@FormParam("FundBodiesName") String FundBodiesName,
	@FormParam("FundBodiesAddress") String FundBodiesAddress,
	@FormParam("FundBodiesPhoneNo") String FundBodiesPhoneNo,
	@FormParam("Amount") String Amount)
	{
	String output =fundbodiesObj.insertFundBodies(FundBodiesName, FundBodiesAddress, FundBodiesPhoneNo, Amount );
	return output;
	}
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFundBodies(String FundBodieData)
	{
		//Convert the input string to a JSON object
		JsonObject FundBodiesObject = new JsonParser().parse(FundBodieData).getAsJsonObject();
		
		//Read the values from the JSON object
		String FundBodiesID = FundBodiesObject.get("FundBodiesID").getAsString();
		String FundBodiesName = FundBodiesObject.get("FundBodiesName").getAsString();
		String FundBodiesAddress = FundBodiesObject.get("FundBodiesAddress").getAsString();
		String FundBodiesPhoneNo = FundBodiesObject.get("FundBodiesPhoneNo").getAsString();
		String Amount = FundBodiesObject.get("Amount").getAsString();
		String output = fundbodiesObj.updateFundBodies(FundBodiesID , FundBodiesName, FundBodiesAddress, FundBodiesPhoneNo, Amount);
		return output;
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFundBodies(String FundBodiesData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(FundBodiesData, "", Parser.xmlParser());
	//Read the value from the element <FundBodiesID>
	String FundBodiesID = doc.select("FundBodiesID").text();
	String output = fundbodiesObj.deleteFundBodies(FundBodiesID);
	return output;
	}
	

}
