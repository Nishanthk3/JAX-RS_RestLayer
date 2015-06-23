package com.jaxrs.rest.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.jaxrs.rest.Ack;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

public class JAXRSRestControllerTest 
{
	private static final String TARGET_URL = "http://localhost:8080/upload";
	private static final String fileName = "robot.py";
	private static final String uniqueId = "35748657";

	@Test
	public void testCase()
	{
		try{
			final Client client = Client.create();
			final WebResource resource = client.resource(TARGET_URL);

			FormDataMultiPart form = new FormDataMultiPart();
			form.field("fileName", fileName);
			form.field("uniqueId",uniqueId);
			FormDataBodyPart fdp = new FormDataBodyPart("content",new FileInputStream("/Users/Nishanth/Downloads/simplebot.py"), MediaType.APPLICATION_OCTET_STREAM_TYPE);
			form.bodyPart(fdp);	     
			ClientResponse response  = resource.type(MediaType.MULTIPART_FORM_DATA).post(ClientResponse.class, form);

			if(response.getStatus() == 200){
				Ack ack = response.getEntity(Ack.class);
				assertEquals(uniqueId, ack.getUniqueId());
			}else if(response.getStatus() == 204){
				System.out.println("Did not get 200 Response");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
