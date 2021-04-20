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
	
	

}
