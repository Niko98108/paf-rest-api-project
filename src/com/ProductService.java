package com;

import model.Product;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/products")

public class ProductService {
	Product productsObj = new Product();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readproducts()
	 {
//		return "Hello";
		return productsObj.readproducts();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertproducts(
	 @FormParam("productID") String productID,
	 @FormParam("productCode") String productCode,
	 @FormParam("productName") String productName,
	 @FormParam("productPrice") String productPrice,
	 @FormParam("productDes") String productDes)
	{
	 String output = productsObj.insertProduct(productID, productCode, productName, productPrice, productDes);
	return output;
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateproducts(String productData)
	{
	//Convert the input string to a JSON object
	 JsonObject productObject = new JsonParser().parse(productData).getAsJsonObject();
	//Read the values from the JSON object
	 String productID = productObject.get("productID").getAsString();
	 String productCode = productObject.get("productCode").getAsString();
	 String productName = productObject.get("productName").getAsString();
	 String productPrice = productObject.get("productPrice").getAsString();
	 String productDes = productObject.get("productDes").getAsString();
	 String output = productsObj.updateproducts(productID, productCode, productName, productPrice, productDes);
	return output;
	}

	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteproducts(String productData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(productData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String productID = doc.select("productID").text();
	 String output = productsObj.deleteproducts(productID); 
	 return output;
	}


}
