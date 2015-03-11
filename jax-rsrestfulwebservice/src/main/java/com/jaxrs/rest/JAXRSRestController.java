package com.jaxrs.rest;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ContextResolver;

import org.codehaus.jackson.map.ObjectMapper;

import com.jaxrs.rest.SampleObject;
import com.jaxrs.rest.Ack;

@Path("/")
public class JAXRSRestController {
	
	SingletonConcurrentHashMap singletonConcurrentHashMapObj = SingletonConcurrentHashMap.getInstance();
	
	@Context
    private ContextResolver<ObjectMapper> mapperResolver;

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
	
	@Path("/xmlRequestProduceJson")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Response submitXmlRequestProduceJson(SampleObject sampleObject, @Context HttpHeaders headers)
	{
		Ack ack = new Ack();
		ack.setUniqueId(sampleObject.getId());
		ack.setType(sampleObject.getName());
		return Response.ok().entity(ack).build();
	}
	
	@Path("/jsonRequest")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response submitJsonRequest(SampleObject sampleObject, @Context HttpHeaders headers)
	{
		Ack ack = new Ack();
		ack.setUniqueId(sampleObject.getId());
		ack.setType(sampleObject.getName());
		return Response.ok().entity(ack).build();
	}
	
	@Path("/jsonRequestProduceJson")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response submitJsonRequestProduceJson(SampleObject sampleObject, @Context HttpHeaders headers)
	{
		Ack ack = new Ack();
		ack.setUniqueId(sampleObject.getId());
		ack.setType(sampleObject.getName());
		return Response.ok().entity(ack).build();
	}
	
	@Path("/xmlListRequest")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response submitXmlListRequest(Athlete athlete, @Context HttpHeaders headers)
	{
		Ack ack = new Ack();
		System.out.println(athlete.getName());
		System.out.println(athlete.getAddress());
		System.out.println(athlete.getAge());
		List<SportsKnown> lists = athlete.getSports();
		for(SportsKnown list : lists)
		{
			System.out.println(list.getSport());
			System.out.println(list.getHandedness());
		}
		return Response.ok().entity(ack).build();
	}
	
	@Path("/jsonListRequest")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response submitJsonListRequest(Athlete athlete, @Context HttpHeaders headers)
	{
		Ack ack = new Ack();
		System.out.println(athlete.getName());
		System.out.println(athlete.getAddress());
		System.out.println(athlete.getAge());
		List<SportsKnown> lists = athlete.getSports();
		for(SportsKnown list : lists)
		{
			System.out.println(list.getSport());
			System.out.println(list.getHandedness());
		}
		return Response.ok().entity(ack).build();
	}
	
	@Path("/singleton")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response singletonMethod(SampleObject sampleObject, @Context HttpHeaders headers)
	{
		Ack ack = new Ack();
		singletonConcurrentHashMapObj.setKeyValue(sampleObject.getName() , Integer.parseInt(sampleObject.getId()));
		if(singletonConcurrentHashMapObj.getValue("name1") != null)
			ack.setUniqueId(singletonConcurrentHashMapObj.getValue("name1").toString());
		return Response.ok().entity(ack).build();
	}
}
