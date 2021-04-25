package com;

import model.customer;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/customer")
public class customerService 
{
	customer customerObj = new customer();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readcustomer()
	{
		 return customerObj.readcustomer();
	}
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertcustomer(@FormParam("customerName") String customerName,
	@FormParam("customerAddress") String customerAddress,
	@FormParam("customerPhoneNo") String customerPhoneNo,
	@FormParam("customeremail") String customeremail)
	{
	String output =customerObj.insertcustomer(customerName, customerAddress, customerPhoneNo, customeremail );
	return output;
	}
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatecustomer(String customerData)
	{
		//Convert the input string to a JSON object
		JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject();
		
		//Read the values from the JSON object
		String customerID = customerObject.get("customerID").getAsString();
		String customerName = customerObject.get("customerName").getAsString();
		String customerAddress = customerObject.get("customerAddress").getAsString();
		String customerPhoneNo = customerObject.get("customerPhoneNo").getAsString();
		String customeremail = customerObject.get("customeremail").getAsString();
		String output = customerObj.updatecustomer(customerID , customerName, customerAddress, customerPhoneNo, customeremail);
		return output;
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletecustomer(String customerData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());
	//Read the value from the element <customerID>
	String customerID = doc.select("customerID").text();
	String output = customerObj.deletecustomer(customerID);
	return output;
	}
	

}
