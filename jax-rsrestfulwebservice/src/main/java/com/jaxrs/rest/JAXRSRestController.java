package com.jaxrs.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jaxrs.rest.SampleObject;
import com.jaxrs.rest.Ack;

@Path("/")
public class JAXRSRestController {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String intialPage()
	{
		return "Welcome to JAX-RS Restful Layer";
	}
	
	@Path("/getxmlObject")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getxmlObject()
	{
		SampleObject sampleObject = new SampleObject();
		sampleObject.setId("1");
		sampleObject.setName("XML");
		return Response.ok().entity(sampleObject).build();
	}
	
	@Path("/getjsonObject")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getjsonObject()
	{
		SampleObject sampleObject = new SampleObject();
		sampleObject.setId("1");
		sampleObject.setName("JSON");
		return Response.ok().entity(sampleObject).build();
	}
	
	@Path("/plainObject")
	@POST
	public Response getPlainObject(String str)
	{
		Ack ack = new Ack();
		ack.setUniqueId(str);
		ack.setType("plain");
		return Response.ok().entity(ack).build();
	}
	
	@Path("/xmlRequest")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response submitXmlRequest(SampleObject sampleObject, @Context HttpHeaders headers)
	{
		Ack ack = new Ack();
		ack.setUniqueId(sampleObject.getId());
		ack.setType(sampleObject.getName());
		return Response.ok().entity(ack).build();
	}
	
	//not working as expected
	@Path("/jsonRequest")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response submitJsonRequest(SampleObject sampleObject, @Context HttpHeaders headers)
	{
		System.out.println("VALUE = "+sampleObject.getId());
		Ack ack = new Ack();
		ack.setUniqueId(sampleObject.getId());
		ack.setType(sampleObject.getName());
		return Response.ok().entity(ack).build();
	}
}
